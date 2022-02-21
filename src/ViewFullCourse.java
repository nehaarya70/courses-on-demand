
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.Path;
import javax.imageio.ImageIO;
import javax.security.auth.callback.ConfirmationCallback;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Twinkle
 */
public class ViewFullCourse extends javax.swing.JFrame {

    String courseName, sampleVideo, email, status;

    int amount;

    public ViewFullCourse(String courseName, String email) {
        initComponents();
        this.courseName = courseName;
        this.email = email;

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setSize(width - 10, height - 50);
        ViewFullCourseJob ob2 = new ViewFullCourseJob();
        Thread t = new Thread(ob2);
        t.start();

    }

    class ViewFullCourseJob implements Runnable {

        public void run() {

            try {
                jPanel1.removeAll();
                jPanel1.repaint();
                HttpResponse<String> httpresponse = Unirest.get("http://"+Credentials.ServerIP+":8888/ViewFullCourse")
                        .queryString("courseName", courseName)
                        .queryString("email", email)
                        .asString();
                String ans = httpresponse.getBody();
                StringTokenizer st = new StringTokenizer(ans, ";");
                while (st.hasMoreTokens()) {

                    final String category = st.nextToken();
                    amount = Integer.parseInt(st.nextToken());
                    String pre_requisite = st.nextToken();
                    String widePhoto = st.nextToken();
                    String squarePhoto = st.nextToken();
                    sampleVideo = st.nextToken();
                    status = st.nextToken();
                    System.out.println(status + "----------------------");
                    if (status.equalsIgnoreCase("yes")) {
                        btSubs.setEnabled(false);
                    } else {
                        btSubs.setEnabled(true);
                    }
                    BufferedImage bimg = ImageIO.read(new URL("http://"+Credentials.ServerIP+":8888/GetResource/" + widePhoto));
                    BufferedImage newimg = resize(bimg, lbWide.getWidth(), lbWide.getHeight());
                    lbWide.setIcon(new ImageIcon(newimg));

                    bimg = ImageIO.read(new URL("http://"+Credentials.ServerIP+":8888/GetResource/" + squarePhoto));
                    newimg = resize(bimg, lbSquare.getWidth(), lbSquare.getHeight());
                    lbSquare.setIcon(new ImageIcon(newimg));
                    lbCourse.setText(courseName);
                    lbCategory.setText(category);
                    lbPre.setText(pre_requisite);
                    lbAmount.setText("Rs. " + amount);
                }
                HttpResponse<String> response = Unirest.get("http://"+Credentials.ServerIP+":8888/FetchAllVideos")
                        .queryString("courseName", courseName)
                        .asString();
                String answer = response.getBody();
                StringTokenizer st1 = new StringTokenizer(answer, "~");
                int count = st1.countTokens();
                SingleVideoPanel[] svp = new SingleVideoPanel[count];
                int x = 10, y = 10;
                jPanel1.setPreferredSize(new Dimension(1360, 660));
                for (int i = 0; i < svp.length; i++) {
                    String videoRow = st1.nextToken();
                    StringTokenizer st2 = new StringTokenizer(videoRow, ";");
                    String title = st2.nextToken();
                    int lecture_no = Integer.parseInt(st2.nextToken());
                    int duration = Integer.parseInt(st2.nextToken());
                    String thumbnail = st2.nextToken();
                    final String path = st2.nextToken();
                    svp[i] = new SingleVideoPanel();

                    if (status.equalsIgnoreCase("no")) {
                        svp[i].btPlay.setEnabled(false);
                        svp[i].btDownload.setEnabled(false);

                    } else {
                        svp[i].btPlay.setEnabled(true);
                        svp[i].btDownload.setEnabled(true);

                    }
                    svp[i].btDownload.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {

                            int r = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to download it?");

                            if (r == (JOptionPane.YES_OPTION)) {
                                DownloadFrame obj = new DownloadFrame(path);
                                obj.setVisible(true);
                            }

                        }
                    });
                    svp[i].btPlay.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {

                            JavaFXMediaPlayerFrame obj = new JavaFXMediaPlayerFrame(path);
                            obj.setVisible(true);
                        }
                    });

                    svp[i].setBounds(x, y, 325, 185);
                    if (x > 809) {
                        x = 10;
                        y += 195;
                    } else {
                        x += 330;

                    }
                    System.out.println("x = " + x + " , y = " + y);
                    svp[i].lbTitle.setText(title);
                    svp[i].lbLectureno.setText("Lecture No. " + lecture_no + "");
                    svp[i].lbDuration.setText((duration / 60) + " min");
                    BufferedImage bimg = ImageIO.read(new URL("http://"+Credentials.ServerIP+":8888/GetResource/" + thumbnail));
                    BufferedImage newImg = resize(bimg, svp[i].lbThumbnail.getWidth(), svp[i].lbThumbnail.getHeight());
                    svp[i].lbThumbnail.setIcon(new ImageIcon(newImg));

                    jPanel1.add(svp[i]);
                    jPanel1.repaint();
                }
                repaint();
            } catch (Exception ex) {
                Logger.getLogger(ViewFullCourse.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    class subscribeJob implements Runnable {

        public void run() {

            try {
                HttpResponse<String> response = Unirest.get("http://"+Credentials.ServerIP+":8888/Subscribe")
                        .queryString("courseName", courseName)
                        .queryString("email", email)
                        .asString();
                String res = response.getBody();
                JOptionPane.showMessageDialog(rootPane, res);

                new Thread(new ViewFullCourseJob()).start();
            } catch (UnirestException ex) {
                Logger.getLogger(ViewFullCourse.class.getName()).log(Level.SEVERE, null, ex);
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

        lbWide = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        lbSquare = new javax.swing.JLabel();
        lbCourse = new javax.swing.JLabel();
        lbCategory = new javax.swing.JLabel();
        lbPre = new javax.swing.JLabel();
        btSubs = new javax.swing.JButton();
        btSample = new javax.swing.JButton();
        lbAmount = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1360, 540));
        getContentPane().setLayout(null);

        lbWide.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(lbWide);
        lbWide.setBounds(0, 0, 1360, 230);

        panel.setBackground(new java.awt.Color(61, 95, 65));
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel.setLayout(null);

        lbSquare.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel.add(lbSquare);
        lbSquare.setBounds(10, 10, 110, 110);

        lbCourse.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        lbCourse.setForeground(new java.awt.Color(255, 255, 255));
        lbCourse.setText("Loading...");
        panel.add(lbCourse);
        lbCourse.setBounds(130, 10, 330, 30);

        lbCategory.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbCategory.setForeground(new java.awt.Color(255, 255, 255));
        panel.add(lbCategory);
        lbCategory.setBounds(130, 50, 240, 30);

        lbPre.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbPre.setForeground(new java.awt.Color(255, 255, 255));
        panel.add(lbPre);
        lbPre.setBounds(130, 84, 190, 30);

        btSubs.setBackground(new java.awt.Color(255, 255, 255));
        btSubs.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btSubs.setText("Pay To Subscibe");
        btSubs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSubsActionPerformed(evt);
            }
        });
        panel.add(btSubs);
        btSubs.setBounds(1040, 50, 290, 30);

        btSample.setBackground(new java.awt.Color(255, 255, 255));
        btSample.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btSample.setText("Click To View Sample Video");
        btSample.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSampleActionPerformed(evt);
            }
        });
        panel.add(btSample);
        btSample.setBounds(1040, 83, 290, 30);

        lbAmount.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lbAmount.setForeground(new java.awt.Color(255, 255, 255));
        lbAmount.setText("Loading...");
        panel.add(lbAmount);
        lbAmount.setBounds(1110, 10, 180, 30);

        getContentPane().add(panel);
        panel.setBounds(0, 230, 1360, 130);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1388, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(2, 362, 1360, 350);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSampleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSampleActionPerformed
        JavaFXMediaPlayerFrame ob3 = new JavaFXMediaPlayerFrame(sampleVideo);
        ob3.setVisible(true);
    }//GEN-LAST:event_btSampleActionPerformed

    private void btSubsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSubsActionPerformed

        PaymentDemo obj = new PaymentDemo();
        obj.setVisible(true);


    }//GEN-LAST:event_btSubsActionPerformed

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
            java.util.logging.Logger.getLogger(ViewFullCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewFullCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewFullCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewFullCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSample;
    private javax.swing.JButton btSubs;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAmount;
    private javax.swing.JLabel lbCategory;
    private javax.swing.JLabel lbCourse;
    private javax.swing.JLabel lbPre;
    private javax.swing.JLabel lbSquare;
    private javax.swing.JLabel lbWide;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
 BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }

    public class PaymentDemo extends javax.swing.JFrame {

        public PaymentDemo() {
            initComponents();
            Browser browser = new Browser();
            BrowserView view = new BrowserView(browser);
            add(view, BorderLayout.CENTER);
            view.setBounds(0, 0, 700, 700);

//        String path = System.getProperty("user.home");
//        path = path.replaceAll("\\\\", "/");
//        browser.loadURL("file:///"+path+"/payment1.jsp?amount=700");
            browser.loadHTML("<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "    <head>\n"
                    + "        <title>TODO supply a title</title>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "\n"
                    + "        <h1>VMM Payment Gateway Demo</h1>\n"
                    + "\n"
                    + "        <form action=\"\" method=\"POST\">\n"
                    + "            <!-- Note that the amount is in paise = 50 INR -->\n"
                    + "            <script\n"
                    + "                src=\"https://checkout.razorpay.com/v1/checkout.js\"\n"
                    + "                data-key=\"rzp_test_96HeaVmgRvbrfT\"\n"
                    + "                data-amount=\"" + amount * 100 + "\"\n"
                    + "                data-buttontext=\"Pay with Razorpay\"\n"
                    + "                data-name=\"VMM Education\"\n"
                    + "                data-description=\"Thank You for purchaing Silver Package.\"\n"
                    + "                data-image=\"http://vmmeducation.com/images/newlogo.png\"\n"
                    + "                data-prefill.name=\"Amrinder Singh\"\n"
                    + "                data-prefill.email=\"amrinder@vmmeducation.com\"\n"
                    + "                data-theme.color=\"#F3EE54\"\n"
                    + "            ></script>\n"
                    + "            <input type=\"hidden\" value=\"Hidden Element\" name=\"hidden\">\n"
                    + "        </form>\n"
                    + "\n"
                    + "   <h1>Please close the page to proceed</h1> </body>\n"
                    + "</html>\n"
                    + "");

            setSize(700, 700);
            setVisible(true);

        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
        private void initComponents() {

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent evt) {
                    formWindowClosing(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 400, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 300, Short.MAX_VALUE)
            );

            pack();
        }// </editor-fold>                        

        private void formWindowClosing(java.awt.event.WindowEvent evt) {
            subscribeJob ob3 = new subscribeJob();
            Thread t1 = new Thread(ob3);
            t1.start();
        }

        // Variables declaration - do not modify                     
        // End of variables declaration                   
    }

}
