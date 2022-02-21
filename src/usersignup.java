
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JFileChooser;
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
public class usersignup extends javax.swing.JFrame {

    File photo;

    public usersignup() {
        initComponents();
        setSize(900,700);
         int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = (width / 2) - (this.getWidth()/2);
            int y = (height / 2) - (this.getHeight()/2);
            setLocation(x, y);
         
        getContentPane().setBackground(new Color(61,95,65));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        tfName = new javax.swing.JTextField();
        tfQuest = new javax.swing.JTextField();
        tfPhone = new javax.swing.JTextField();
        tfPass = new javax.swing.JPasswordField();
        tfAns = new javax.swing.JTextField();
        tfConfirm = new javax.swing.JPasswordField();
        tfPhoto = new javax.swing.JTextField();
        btBrowse = new javax.swing.JButton();
        btAdd = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 255));
        getContentPane().setLayout(null);

        jLabel1.setBackground(new java.awt.Color(255, 102, 102));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("             USER SIGNUP");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(250, 30, 300, 32);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 51));
        jLabel2.setText("Enter Email");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 130, 180, 30);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 51));
        jLabel3.setText("Enter Name");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 170, 110, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 51));
        jLabel5.setText("Confirm Passowrd");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(10, 260, 150, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 51));
        jLabel4.setText("Enter Password");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(10, 220, 140, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 51));
        jLabel6.setText("Enter PhoneNo");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(10, 310, 130, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 102));
        jLabel7.setText("Enter Security Question");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(10, 350, 150, 20);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 51));
        jLabel8.setText("Enter Security Answer");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(10, 390, 210, 20);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 51));
        jLabel9.setText("Select Photo");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(10, 450, 160, 20);
        getContentPane().add(tfEmail);
        tfEmail.setBounds(270, 120, 280, 30);
        getContentPane().add(tfName);
        tfName.setBounds(270, 160, 280, 30);

        tfQuest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfQuestActionPerformed(evt);
            }
        });
        getContentPane().add(tfQuest);
        tfQuest.setBounds(270, 340, 280, 30);
        getContentPane().add(tfPhone);
        tfPhone.setBounds(270, 290, 280, 30);
        getContentPane().add(tfPass);
        tfPass.setBounds(270, 210, 280, 30);
        getContentPane().add(tfAns);
        tfAns.setBounds(270, 380, 280, 40);
        getContentPane().add(tfConfirm);
        tfConfirm.setBounds(270, 250, 280, 30);
        getContentPane().add(tfPhoto);
        tfPhoto.setBounds(270, 450, 270, 30);

        btBrowse.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btBrowse.setForeground(new java.awt.Color(255, 51, 0));
        btBrowse.setText("Browse");
        btBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBrowseActionPerformed(evt);
            }
        });
        getContentPane().add(btBrowse);
        btBrowse.setBounds(550, 450, 90, 23);

        btAdd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btAdd.setForeground(new java.awt.Color(255, 51, 51));
        btAdd.setText("ADD");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });
        getContentPane().add(btAdd);
        btAdd.setBounds(300, 510, 90, 23);

        jLabel10.setForeground(new java.awt.Color(255, 153, 51));
        getContentPane().add(jLabel10);
        jLabel10.setBounds(0, 0, 220, 540);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfQuestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfQuestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfQuestActionPerformed

    private void btBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBrowseActionPerformed

        JFileChooser jfc = new JFileChooser(System.getProperty("user.home") + File.separator + "Videos");
        int r = jfc.showOpenDialog(this);
        if (r == JFileChooser.APPROVE_OPTION) {
            photo = jfc.getSelectedFile();
            tfPhoto.setText(photo.getPath());

        }

    }//GEN-LAST:event_btBrowseActionPerformed

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        Thread t = new Thread(new SignUp());
        t.start();
    }//GEN-LAST:event_btAddActionPerformed

    class SignUp implements Runnable {

        public void run() {

            String email = tfEmail.getText();
            String name = tfName.getText();
            String pass = tfPass.getText();
            String confirm = tfConfirm.getText();
            String question = tfQuest.getText();
            String answer = tfAns.getText();
            long phone = Long.parseLong(tfPhone.getText());
            String photoPath = tfPhoto.getText();
            try {

                if (email.equals("") || name.equals("") || pass.equals("") || confirm.equals("") || question.equals("") || answer.equals("") || tfPhone.getText().equals("") || photoPath.equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "All Fields must be Filled");
                } else if (!pass.equals(confirm)) {
                    JOptionPane.showMessageDialog(rootPane, "Password does not match");
                } else {
                    HttpResponse<String> httpResponse = Unirest.post("http://"+Credentials.ServerIP+":8888/SignUp")
                            .queryString("email", email)
                            .queryString("name", name)
                            .queryString("password", pass)
                            .queryString("question", question)
                            .queryString("answer", answer)
                            .queryString("phone", phone)
                            .field("photo", photo)
                            .asString();
                    String result = httpResponse.getBody();
                    JOptionPane.showMessageDialog(rootPane, result);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Invalid Fields");
                ex.printStackTrace();
            }
        }

    }

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
            java.util.logging.Logger.getLogger(usersignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(usersignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(usersignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(usersignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new usersignup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btBrowse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField tfAns;
    private javax.swing.JPasswordField tfConfirm;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfName;
    private javax.swing.JPasswordField tfPass;
    private javax.swing.JTextField tfPhone;
    private javax.swing.JTextField tfPhoto;
    private javax.swing.JTextField tfQuest;
    // End of variables declaration//GEN-END:variables
}
