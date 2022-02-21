
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class welcomeuser extends javax.swing.JFrame {

    String email;
    int y = 10;

    public welcomeuser(String email) {
        initComponents();
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setSize(width - 10, height - 50);

        this.email = email;

        jpWelcome.setText("Welcome : " + email);

        CreatePanels obj = new CreatePanels();
        Thread t = new Thread(obj);
        t.start();

    }

    class CreatePanels implements Runnable {

        public void run() {
            final PanelSlider42<JPanel> slider = new PanelSlider42<JPanel>(jp2);
            JPanel basePanel = slider.getBasePanel();
            String images[] = {"/slider/a1.jpg", "/slider/a2.png", "/slider/a3.jpg", "/slider/a4.jpg"};

            for (int i = 0; i < images.length; i++) {
                try {
                    JLabel lb = new JLabel();

                    BufferedImage image = ImageIO.read(getClass().getResource(images[i]));
                    BufferedImage img = resize(image, 1360,270);
                    lb.setIcon(new ImageIcon(img));
                    slider.addComponent(lb);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            slider.test();
            jp2.add(basePanel);
            jScrollPane1.setSize(1360, 600);

            jP3.setPreferredSize(new Dimension(1360, 2000));

            SinglePanel sp[] = new SinglePanel[Data.categories.length];
            for (int i = 0; i < Data.categories.length; i++) {
                try {
                    sp[i] = new SinglePanel();
                    sp[i].setBounds(10, y, 1300, 200);
                    jP3.add(sp[i]);
                    sp[i].jL1.setText("Videos From : " + Data.categories[i]);
                    y = y + 220;

                    HttpResponse<String> httpresponse = Unirest.get("http://"+Credentials.ServerIP+":8888/GetCoursesOfCat")
                            .queryString("category", Data.categories[i])
                            .asString();
                    String ans = httpresponse.getBody();
                    System.out.println(ans);
                    StringTokenizer st = new StringTokenizer(ans, "~");
                    int count = st.countTokens();
                    SingleCoursePanel[] ob2 = new SingleCoursePanel[count];
                    int x = 20;
                    for (int j = 0; j < count; j++) {
                        String course = st.nextToken();
                        System.out.println(count);
                        StringTokenizer st1 = new StringTokenizer(course, ";");

                        final String title = st1.nextToken();
                        int amount = Integer.parseInt(st1.nextToken());
                        String photo = st1.nextToken();

                        ob2[j] = new SingleCoursePanel();
                        ob2[j].setBounds(x, 35, 180, 160);
                        ob2[j].lbName.setText(title);

                        BufferedImage bimg = ImageIO.read(new URL("http://"+Credentials.ServerIP+":8888/GetResource/" + photo));
                        BufferedImage newimg = resize(bimg, ob2[j].lbIcon.getWidth(),ob2[j].lbIcon.getHeight());

                        ob2[j].addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                ViewFullCourse obj = new ViewFullCourse(title,email);
                                obj.setVisible(true);

                            }
                        });

                        ob2[j].lbIcon.setIcon(new ImageIcon(newimg));
                        ob2[j].lbAmount.setText("Rs.  " + amount + "");
                        x += 200;
                        sp[i].add(ob2[j]);

                        repaint();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(welcomeuser.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jp1 = new javax.swing.JPanel();
        jpWelcome = new javax.swing.JLabel();
        jLogOut = new javax.swing.JButton();
        jChangePassword = new javax.swing.JButton();
        jViewProfile = new javax.swing.JButton();
        jp2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jP3 = new javax.swing.JPanel();

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

        jp1.setBackground(new java.awt.Color(61, 95, 65));
        jp1.setBorder(new javax.swing.border.MatteBorder(null));
        jp1.setLayout(null);

        jpWelcome.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jpWelcome.setForeground(new java.awt.Color(255, 255, 255));
        jpWelcome.setText("Welcome :");
        jp1.add(jpWelcome);
        jpWelcome.setBounds(10, 10, 270, 20);

        jLogOut.setBackground(new java.awt.Color(255, 255, 153));
        jLogOut.setText("Log Out");
        jLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLogOutActionPerformed(evt);
            }
        });
        jp1.add(jLogOut);
        jLogOut.setBounds(1140, 10, 130, 20);

        jChangePassword.setBackground(new java.awt.Color(255, 255, 153));
        jChangePassword.setText("Change Password");
        jChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChangePasswordActionPerformed(evt);
            }
        });
        jp1.add(jChangePassword);
        jChangePassword.setBounds(990, 10, 140, 23);

        jViewProfile.setBackground(new java.awt.Color(255, 255, 153));
        jViewProfile.setText("View Profile");
        jViewProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jViewProfileActionPerformed(evt);
            }
        });
        jp1.add(jViewProfile);
        jViewProfile.setBounds(840, 10, 140, 23);

        getContentPane().add(jp1);
        jp1.setBounds(0, 0, 1730, 40);

        jp2.setBackground(new java.awt.Color(204, 204, 204));
        jp2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jp2.setLayout(null);
        getContentPane().add(jp2);
        jp2.setBounds(0, 40, 1590, 270);

        jP3.setBackground(new java.awt.Color(255, 255, 153));
        jP3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jP3.setLayout(null);
        jScrollPane1.setViewportView(jP3);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 310, 1590, 490);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLogOutActionPerformed
        // TODO add your handling code here:
        
        
        this.dispose();
        new userlogin().setVisible(true);
        
    }//GEN-LAST:event_jLogOutActionPerformed

    private void jViewProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jViewProfileActionPerformed

        MyProfile obj4=new MyProfile(email);
        obj4.setVisible(true);
        
    }//GEN-LAST:event_jViewProfileActionPerformed

    private void jChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChangePasswordActionPerformed

        ChangePassword obj5= new ChangePassword(email);
        obj5.setVisible(true);
        
    }//GEN-LAST:event_jChangePasswordActionPerformed

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
            java.util.logging.Logger.getLogger(welcomeuser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(welcomeuser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(welcomeuser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(welcomeuser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new welcomeuser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jChangePassword;
    private javax.swing.JButton jLogOut;
    private javax.swing.JPanel jP3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jViewProfile;
    private javax.swing.JPanel jp1;
    private javax.swing.JPanel jp2;
    private javax.swing.JLabel jpWelcome;
    // End of variables declaration//GEN-END:variables

    BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }

}