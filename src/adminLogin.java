
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

public class adminLogin extends javax.swing.JFrame {
  
    public adminLogin() {
        initComponents();
        setSize(500, 389);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        int x = (width / 2) - (this.getWidth() / 2);
        int y = (height / 2) - (this.getHeight() / 2);
        setLocation(x, y);
    }

    class job implements Runnable {
      String email;
        public void run() {
            
             this.email = tf1.getText();
            String pass = tf2.getText();
            if (email.equals("") || pass.equals("")) {
                JOptionPane.showMessageDialog(rootPane, "All fields are necessary");
            } else {
                try {
                    HttpResponse<String> httpresponse = Unirest.get("http://"+Credentials.ServerIP+":8888/CheckAdminLogin")
                            .queryString("email", email)
                            .queryString("pass", pass)
                            .asString();
                    String ans = httpresponse.getBody();

                    if (ans.equals("Login Success")) {
                        dispose();
                        welcomeAdmin obj1 = new welcomeAdmin(email);
                        obj1.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, ans);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tf2 = new javax.swing.JPasswordField();
        b1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 102));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(61, 95, 65));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Admin Login");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(150, 20, 160, 28);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 51));
        jLabel2.setText("Email");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(50, 90, 80, 17);
        jPanel1.add(tf1);
        tf1.setBounds(210, 80, 180, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 51));
        jLabel3.setText("Password");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(50, 160, 110, 17);
        jPanel1.add(tf2);
        tf2.setBounds(210, 140, 180, 30);

        b1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        b1.setForeground(new java.awt.Color(255, 0, 0));
        b1.setText("Login");
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
        jPanel1.add(b1);
        b1.setBounds(160, 230, 110, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 750, 390);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        job obj = new job();
        Thread th = new Thread(obj);
        th.start();

    }//GEN-LAST:event_b1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminLogin( ).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tf1;
    private javax.swing.JPasswordField tf2;
    // End of variables declaration//GEN-END:variables
}
