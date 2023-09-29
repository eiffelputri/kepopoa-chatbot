/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kepopoabot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import static kepopoabot.frmTelegram.getMsg;

/**
 *
 * @author Asus
 */
public class MyProjectHandler  extends TelegramLongPollingBot {

    public String msg = null;
    public String id = null;
    
    Connection Con = login.Koneksi.getConnection();
    Statement st;
    ResultSet rs;
    PreparedStatement ps;
    
    public MyProjectHandler() {
    }
    
    // broadcast
    public void sendPesan (String id, String pesan) {
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(id);
        sendMessageRequest.setText(pesan);
        try {
            sendMessage(sendMessageRequest);
        } catch (TelegramApiException e) {
            
        }
    }

    @Override
    public String getBotToken() {
        return BotConfig.TOKENMYPROJECT;
    }
    
    
    // dari keyword
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()) {
            Message message = update.getMessage();
            if(message.hasText()) {
                System.out.println(message.getChat().getFirstName());        
                String pesanM = message.getChatId()+ " : " + message.getText();
                frmTelegram.lstPesan.add(pesanM);
                msg = message.getText();
                id = message.getChatId().toString();
                String name = message.getChat().getUserName();
//                frmTelegram.getMsg(id, msg, name); // td dikomen
                MyProjectHandler a = new MyProjectHandler();
                a.dapatkanPesan(id, msg, name);
            }
        }
    }
    
    public void dapatkanPesan(String id, String msg, String name) {
        String pesan = null;
        String query = "SELECT * FROM `tb_keyword` WHERE `keyword`='" + msg + "'";

        // memeriksa keanggotaan pengguna di 'tb_data'
        String memberQuery = "SELECT * FROM `tb_data`";

        try {
            st = Con.createStatement();
            rs = st.executeQuery(memberQuery);
            boolean isMember = false;  // Tambahkan variabel isMember

            while (rs.next()) {
                String memberId = rs.getString("chatId");
                String memberName = rs.getString("username");

                // Jika pengguna adalah anggota
                if (memberId.equals(id)) {
                    isMember = true;  // Setel isMember ke true
                    try {
                        Statement stKeyword = Con.createStatement();
                        ResultSet rsKeyword = stKeyword.executeQuery(query);
                        if (rsKeyword.next()) {
                            System.out.println("Masuk!!!");
                            pesan = rsKeyword.getString("answer");
                        }
                        rsKeyword.close();
                        stKeyword.close();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    String insertQuery = "INSERT INTO keyword_log (username, pesan, timestamp) VALUES (?, ?, CURRENT_TIMESTAMP())";

                    try (PreparedStatement preparedStatement = Con.prepareStatement(insertQuery)) {
                        preparedStatement.setString(1, name);
                        preparedStatement.setString(2, pesan);

                        preparedStatement.executeUpdate();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    frmTelegram.getMsg(memberId, pesan, memberName);
                    break; // Keluar dari perulangan setelah menemukan anggota yang cocok
                }
            }
            rs.close();
            st.close();

            // kondisi jika pengguna adalah anggota namun keyword tidak ditemukan
            if (isMember && pesan == null) {
                pesan = "Terima kasih atas pesan ini!\n" + "\n" + "Maaf, kami tidak dapat membalas ke pengguna secara individu dari akun ini.\n" +"\n" +"Namun jangan khawatir. Kami akan mengirimi Anda pesan lebih banyak lagi segera!\n" +"\n" +"Untuk lihat daftar keyword, silahkan ketik /help";
                sendPesan(id, pesan);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    
    @Override
    public String getBotUsername() {
        return BotConfig.USERNAMEMYPROJECT;
    }   
    
}