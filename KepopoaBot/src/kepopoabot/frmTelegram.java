/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kepopoabot;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.logging.BotLogger;
import org.telegram.telegrambots.logging.BotsFileHandler;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Asus
 */
public class frmTelegram extends javax.swing.JFrame {

    /**
     * Creates new form frmTelegram
     */
    Connection Con = login.Koneksi.getConnection();
    Statement st;
    ResultSet rs;
    PreparedStatement ps;
    
    
    public frmTelegram() {
        initComponents();
        showTable();
        //Image frameIcon = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resouce/laravel.png"));
        //this.setIconImage(frameIcon);
    }
    
    String name;
    String adminName;
    
    public String test1(String name){
        return name;
    }
    
    public void showTable(){
        DefaultTableModel model = (DefaultTableModel) tbDatamember.getModel();
        try {
            String query = "SELECT * FROM tb_data";
            st = Con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {                
                model.addRow(new Object[] {rs.getString("username"), rs.getString("noHp"), rs.getString("chatId")});
            }
            tbDatamember.setModel(model);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void enData(){
        String query = "INSERT INTO `tb_data` (`id_data`, `username`, `noHp`, `chatId`) VALUES (NULL, ?, ?, ?)";
        try {
            ps = Con.prepareStatement(query);
            ps.setString(1, txtUsername.getText());
            ps.setString(2, txtNohp.getText());
            ps.setString(3, txtIdTelegram.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // dari keyword
    public static void getMsg(String id, String msg, String name){
        MyProjectHandler Send = new MyProjectHandler();
        Send.sendPesan(id, msg);
        lstPesan.add("Bot: " + msg);
    }
    
    public void editForm(int index){
        txtUsername.setText(tbDatamember.getModel().getValueAt(index, 0).toString());
        txtNohp.setText(tbDatamember.getModel().getValueAt(index, 1).toString());
        txtIdTelegram.setText(tbDatamember.getModel().getValueAt(index, 2).toString());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        lstPesan = new java.awt.List();
        txtIdTelegram = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtNohp = new javax.swing.JTextField();
        btnDaftar = new javax.swing.JButton();
        btnClean = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDatamember = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPesan = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jDesktopPane1.add(lstPesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 802, 140));
        jDesktopPane1.add(txtIdTelegram, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 310, 30));

        btnSend.setText("Broadcast");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });
        jDesktopPane1.add(btnSend, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 320, 100, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jDesktopPane1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 361, 843, -1));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("HISTORY PESAN");
        jDesktopPane1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, -1, 21));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BROADCAST PESAN");
        jDesktopPane1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID Chat");
        jDesktopPane1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 113, -1));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Username");
        jDesktopPane1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 113, -1));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("No Handphone");
        jLabel5.setMaximumSize(new java.awt.Dimension(62, 24));
        jLabel5.setMinimumSize(new java.awt.Dimension(62, 24));
        jDesktopPane1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 113, -1));
        jDesktopPane1.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 310, 30));

        txtNohp.setMinimumSize(new java.awt.Dimension(7, 24));
        jDesktopPane1.add(txtNohp, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 310, 30));

        btnDaftar.setText("DAFTAR");
        btnDaftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaftarActionPerformed(evt);
            }
        });
        jDesktopPane1.add(btnDaftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 77, -1));

        btnClean.setText("CLEAN");
        btnClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanActionPerformed(evt);
            }
        });
        jDesktopPane1.add(btnClean, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 77, -1));

        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jDesktopPane1.add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, 77, -1));

        btnEdit.setText("EDIT");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jDesktopPane1.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 77, -1));

        btnRefresh.setText("REFRESH");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jDesktopPane1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, -1, -1));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jDesktopPane1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 854, -1));

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("KELOLA MEMBER");
        jDesktopPane1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 199, 31));

        tbDatamember.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "No. Hp", "Id Chat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDatamember.setEditingRow(-2);
        tbDatamember.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDatamemberMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDatamember);

        jDesktopPane1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 450, 116));

        txtPesan.setColumns(20);
        txtPesan.setRows(5);
        jScrollPane2.setViewportView(txtPesan);

        jDesktopPane1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(514, 100, 310, 207));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Clara - 13374");
        jDesktopPane1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, -1, -1));

        jMenu1.setText("Menu");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8_key_30px.png"))); // NOI18N
        jMenuItem1.setText("Add Keyword");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8_shutdown_40px.png"))); // NOI18N
        jMenuItem2.setText("LOGOUT");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
        // broadcast
        MyProjectHandler Send = new MyProjectHandler();
        Send.sendPesan(txtIdTelegram.getText(), txtPesan.getText());
        lstPesan.add("Send to " + txtUsername.getText() + " with ID: " + txtIdTelegram.getText() +", Pesan : " + txtPesan.getText());
         
        // broadcast_log
        String penerima = txtUsername.getText();
        String pesan = txtPesan.getText();

        String insertQuery = "INSERT INTO broadcast_log (username, pesan, timestamp) VALUES (?, ?, CURRENT_TIMESTAMP())";

        try (PreparedStatement preparedStatement = Con.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, penerima);
            preparedStatement.setString(2, pesan);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        txtPesan.setText("");
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnDaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaftarActionPerformed
        enData();
    }//GEN-LAST:event_btnDaftarActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbDatamember.getModel();
        model.setRowCount(0);
        showTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void tbDatamemberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDatamemberMouseClicked
        txtIdTelegram.setEditable(false);
        int index = tbDatamember.getSelectedRow();
        editForm(index);
    }//GEN-LAST:event_tbDatamemberMouseClicked

    private void btnCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanActionPerformed
        txtIdTelegram.setEditable(true);
        txtUsername.setText("");
        txtNohp.setText("");
        txtIdTelegram.setText("");
    }//GEN-LAST:event_btnCleanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        String query = "DELETE FROM tb_data WHERE chatId='"+ txtIdTelegram.getText().toString() +"'";
        try{
            ps = Con.prepareStatement(query);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil dihapus!");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error delCheckIn() = " + e.getMessage());
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        String query = "UPDATE `tb_data` SET `username` =?, `noHp` =? WHERE `chatId` =?";
        try{
            ps = Con.prepareStatement(query);
            ps.setString(1, txtUsername.getText());
            ps.setString(2, txtNohp.getText());
            ps.setString(3, txtIdTelegram.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil diubah!");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error delCheckIn() = " + e.getMessage());
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new Keyword().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        new P_login().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmTelegram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new P_login().setVisible(true);
                BotLogger.setLevel(Level.ALL);
                BotLogger.registerLogger(new ConsoleHandler());
                try {
                    BotLogger.registerLogger(new BotsFileHandler());
                    } catch (IOException ex) {
                        Logger.getLogger(frmTelegram.class.getName()).log(Level.SEVERE, null,ex);
                }
                try {
                    TelegramBotsApi telegramBotsApi = createTelegramBotsApi();
                    try {
                        telegramBotsApi.registerBot(new MyProjectHandler(){
                        
                        });
                    } catch (TelegramApiException e){
                        
                    }
                } catch (Exception e){
                    
                }
            }
        });
    }
    
    private static TelegramBotsApi createTelegramBotsApi() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi;
        telegramBotsApi = createlongPollingTelegramBotsApi();
        return telegramBotsApi;
        
    }
    
    private static TelegramBotsApi createlongPollingTelegramBotsApi() {
        return new TelegramBotsApi();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClean;
    private javax.swing.JButton btnDaftar;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSend;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    public static java.awt.List lstPesan;
    private javax.swing.JTable tbDatamember;
    private javax.swing.JTextField txtIdTelegram;
    private javax.swing.JTextField txtNohp;
    private javax.swing.JTextArea txtPesan;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}