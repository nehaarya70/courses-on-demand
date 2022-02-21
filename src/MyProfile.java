
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MyProfile extends javax.swing.JFrame {

    String email;

    public MyProfile(String email) {
        initComponents();
        this.email = email;
        setSize(950, 450);
        getContentPane().setBackground(Color.white);
        Thread th = new Thread(new MyProfileJob());
        th.start();
         int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = (width / 2) - (this.getWidth()/2);
            int y = (height / 2) - (this.getHeight()/2);
            setLocation(x, y);
         
        getContentPane().setBackground(new Color(61,95,65));

    }

    class MyProfileJob implements Runnable {

        public void run() {

            try {
                HttpResponse<String> httpresponse = Unirest.get("http://"+Credentials.ServerIP+":8888/FetchMyProfile")
                        .queryString("email", email)
                        .asString();
                String ans = httpresponse.getBody();
                String photo = "", name = "";
                long phone = 0;

                StringTokenizer st = new StringTokenizer(ans, ";");

                while (st.hasMoreTokens()) {

                    name = st.nextToken();
                    phone = Long.parseLong(st.nextToken());
                    photo = st.nextToken();
                }

                jName.setText(name);
                jPhone.setText(phone + "");
                jMail.setText(email);
                BufferedImage bimg = ImageIO.read(new URL("http://"+Credentials.ServerIP+":8888/GetResource/" + photo));
                BufferedImage newimg = resize(bimg, jPhoto.getWidth(), jPhoto.getHeight());
                jPhoto.setIcon(new ImageIcon(newimg));

                HttpResponse<String> response = Unirest.get("http://"+Credentials.ServerIP+":8888/FetchSubs")
                        .queryString("email", email)
                        .asString();
                ans = response.getBody();
                if (!ans.equals("")) {
                    System.out.println("response ="+ans);
                    st = new StringTokenizer(ans, "~");
                    int x = 10, y = 10;
                    while (st.hasMoreTokens()) {
                        StringTokenizer st2=new StringTokenizer(st.nextToken(), ";");
                        String course_name = st2.nextToken();
                        String square_photo=st2.nextToken();
                        SingleSubs sb=new SingleSubs();
                        sb.setBounds(x, y, 191, 135);
                       bimg= ImageIO.read(new URL("http://"+Credentials.ServerIP+":8888/GetResource/"+square_photo));
                        newimg=resize(bimg, sb.lbPhoto.getWidth(), sb.lbPhoto.getHeight());
                        sb.lbPhoto.setIcon(new ImageIcon(newimg));
                        sb.lbName.setText(course_name);
                        jPanel2.add(sb);
                        x+=200;
                    }
                    jPanel2.repaint();
                }

            } catch (Exception ex) {
                ex.printStackTrace();

            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPhoto = new javax.swing.JLabel();
        jName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMail = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPhone = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jPhoto.setBackground(new java.awt.Color(255, 255, 255));
        jPhoto.setForeground(new java.awt.Color(255, 255, 102));
        jPhoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jPhoto);
        jPhoto.setBounds(440, 10, 120, 100);

        jName.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jName.setForeground(new java.awt.Color(255, 255, 51));
        jName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jName.setText("jLabel1");
        getContentPane().add(jName);
        jName.setBounds(420, 110, 150, 37);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Email");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(400, 150, 104, 26);

        jMail.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jMail.setForeground(new java.awt.Color(255, 255, 51));
        jMail.setText("Email");
        getContentPane().add(jMail);
        jMail.setBounds(550, 150, 173, 26);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Phone");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(400, 170, 104, 36);

        jPhone.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jPhone.setForeground(new java.awt.Color(255, 255, 51));
        jPhone.setText("jLabel3");
        getContentPane().add(jPhone);
        jPhone.setBounds(550, 170, 146, 36);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(null);
        getContentPane().add(jPanel2);
        jPanel2.setBounds(110, 210, 790, 190);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Subscriptions");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 210, 90, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MyProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyProfile("test").setVisible(true);
            }
        });
    }

    BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jMail;
    private javax.swing.JLabel jName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jPhone;
    private javax.swing.JLabel jPhoto;
    // End of variables declaration//GEN-END:variables
}
