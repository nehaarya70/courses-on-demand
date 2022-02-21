
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

public class userlogin extends javax.swing.JFrame {

    public userlogin() {
        initComponents();
        setSize(500, 389);

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        int x = (width / 2) - (this.getWidth() / 2);
        int y = (height / 2) - (this.getHeight() / 2);
        setLocation(x, y);

        getContentPane().setBackground(new Color(61, 95, 65));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        jpPassowrd = new javax.swing.JPasswordField();
        btLogin = new javax.swing.JButton();
        jForgot = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 255));
        getContentPane().setLayout(null);

        jLabel3.setBackground(new java.awt.Color(255, 204, 204));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 102));
        jLabel3.setText("           Login Here");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(130, 20, 250, 30);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 102));
        jLabel1.setText("Enter Email");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 90, 150, 40);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 102));
        jLabel2.setText("Enter password");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 150, 150, 30);

        tfEmail.setBackground(new java.awt.Color(240, 240, 240));
        tfEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEmailActionPerformed(evt);
            }
        });
        getContentPane().add(tfEmail);
        tfEmail.setBounds(190, 90, 230, 30);

        jpPassowrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpPassowrdActionPerformed(evt);
            }
        });
        getContentPane().add(jpPassowrd);
        jpPassowrd.setBounds(190, 150, 230, 30);

        btLogin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btLogin.setText("LOGIN");
        btLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btLogin);
        btLogin.setBounds(210, 210, 70, 23);

        jForgot.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jForgot.setForeground(new java.awt.Color(255, 255, 102));
        jForgot.setText("Forgot Password? Click Here...");
        jForgot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jForgotMouseClicked(evt);
            }
        });
        getContentPane().add(jForgot);
        jForgot.setBounds(70, 240, 370, 30);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 102));
        jLabel4.setText("Not a User? Click Here");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4);
        jLabel4.setBounds(120, 280, 310, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEmailActionPerformed

    }//GEN-LAST:event_tfEmailActionPerformed

    private void btLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLoginActionPerformed

        userLoginjob ob2 = new userLoginjob();
        Thread t = new Thread(ob2);
        t.start();


    }//GEN-LAST:event_btLoginActionPerformed

    private void jForgotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jForgotMouseClicked
        Forgotpassword ob2 = new Forgotpassword();
        ob2.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jForgotMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked

        usersignup u = new usersignup();
        u.setVisible(true);

    }//GEN-LAST:event_jLabel4MouseClicked

    private void jpPassowrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpPassowrdActionPerformed
        // TODO add your handling code here:
        userLoginjob ob2 = new userLoginjob();
        Thread t = new Thread(ob2);
        t.start();

    }//GEN-LAST:event_jpPassowrdActionPerformed
    class userLoginjob implements Runnable {

        public void run() {
            String email = tfEmail.getText();
            String password = jpPassowrd.getText();
            if (email.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(rootPane, "All Fields are required");

            } else {
                try {
                    HttpResponse<String> httpresponse = Unirest.get("http://"+Credentials.ServerIP+":8888/CheckUserLogin")
                            .queryString("email", email)
                            .queryString("password", password)
                            .asString();
                    String ans = httpresponse.getBody();

                    if (ans.equals("Login Success")) {
                        dispose();
                        //JOptionPane.showMessageDialog(rootPane, ans);
                        welcomeuser ob1 = new welcomeuser(email);
                        ob1.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(rootPane, ans);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new userlogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLogin;
    private javax.swing.JLabel jForgot;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jpPassowrd;
    private javax.swing.JTextField tfEmail;
    // End of variables declaration//GEN-END:variables
}
