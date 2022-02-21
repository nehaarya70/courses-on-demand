
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Twinkle
 */
public class ChangePassword extends javax.swing.JFrame {

    String email;

    public ChangePassword(String email) {

        initComponents();
        setSize(500, 450);
        this.email = email;
        jEmail.setText(email);
          int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = (width / 2) - (this.getWidth()/2);
            int y = (height / 2) - (this.getHeight()/2);
            setLocation(x, y);
         
        getContentPane().setBackground(new Color(61,95,65));

    }

    private ChangePassword() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class UpdateJob implements Runnable {

        String oldPass = "", newPass = "", confirmPass = "";

        public void run() {

            oldPass = jOld.getText();
            newPass = jNew.getText();
            confirmPass = jConfirm.getText();
            if (oldPass.equals("") || newPass.equals("") || confirmPass.equals("")) {
                JOptionPane.showMessageDialog(rootPane, "All Fields are required");
            } else if (newPass.equals(confirmPass)) {
                try {
                    HttpResponse<String> httpresponse = Unirest.get("http://"+Credentials.ServerIP+":8888/ChangePassword")
                            .queryString("email", email)
                            .queryString("oldPass", oldPass)
                            .queryString("newPass", newPass)
                            .asString();
                    String ans = httpresponse.getBody();
                    JOptionPane.showMessageDialog(rootPane, ans);
                    if(ans.equalsIgnoreCase("success"))
                    {
                        dispose();
                    }
                } catch (UnirestException ex) {
                    Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
                } 
            } else {
                JOptionPane.showMessageDialog(rootPane, "New Password and Confirm Password must match");
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jEmail = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btUpdate = new javax.swing.JButton();
        jOld = new javax.swing.JPasswordField();
        jNew = new javax.swing.JPasswordField();
        jConfirm = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        jTextField1.setText("jTextField1");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setForeground(new java.awt.Color(255, 255, 51));
        jLabel1.setText("Email");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(36, 85, 137, 34);

        jEmail.setForeground(new java.awt.Color(255, 255, 51));
        getContentPane().add(jEmail);
        jEmail.setBounds(261, 85, 164, 34);

        jLabel2.setForeground(new java.awt.Color(255, 255, 51));
        jLabel2.setText("Old Password");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(36, 150, 172, 34);

        jLabel3.setForeground(new java.awt.Color(255, 255, 51));
        jLabel3.setText("New Password");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(36, 215, 172, 35);

        jLabel5.setForeground(new java.awt.Color(255, 255, 51));
        jLabel5.setText("Confirm Password");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(36, 282, 172, 38);

        btUpdate.setForeground(new java.awt.Color(255, 0, 0));
        btUpdate.setText("Update");
        btUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btUpdate);
        btUpdate.setBounds(190, 350, 92, 34);
        getContentPane().add(jOld);
        jOld.setBounds(260, 150, 180, 30);
        getContentPane().add(jNew);
        jNew.setBounds(260, 220, 180, 30);
        getContentPane().add(jConfirm);
        jConfirm.setBounds(261, 280, 180, 30);

        jLabel6.setFont(new java.awt.Font("Tw Cen MT", 3, 25)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Change Password");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jLabel6);
        jLabel6.setBounds(150, 30, 180, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateActionPerformed

        Thread th = new Thread(new UpdateJob());
        th.start();


    }//GEN-LAST:event_btUpdateActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangePassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JPasswordField jConfirm;
    private javax.swing.JLabel jEmail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jNew;
    private javax.swing.JPasswordField jOld;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
