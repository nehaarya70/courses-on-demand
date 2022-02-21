
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
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
public class DownloadFrame extends javax.swing.JFrame {

    String path;

    public DownloadFrame(String path) {
        initComponents();
        setSize(500,389);
        this.path = path;
        downloadJob obj=new downloadJob();
        Thread t=new Thread (obj);
         t.start();  
           int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = (width / 2) - (this.getWidth()/2);
            int y = (height / 2) - (this.getHeight()/2);
            setLocation(x, y);
         
        getContentPane().setBackground(new Color(61,95,65));

    }

    class downloadJob implements Runnable {

        public void run() {

//To change body of generated methods, choose Tools | Templates.
            String name = path.substring(path.lastIndexOf("/"));
            try {
                HttpResponse<InputStream> httpresponse = Unirest.get("http://"+Credentials.ServerIP+":8888/GetResource/" + path).asBinary();

                //File Download Code
                InputStream is = httpresponse.getBody();
                FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + "/Downloads/"+name);

                long contentlength = Integer.parseInt(httpresponse.getHeaders().getFirst("Content-Length"));
                byte b[] = new byte[10000];
                int r;
                long count = 0;

                while (true) {
                    r = is.read(b, 0, 10000);
                    fos.write(b, 0, r);
                    count = count + r;
                    int per = (int) (count * 100 / contentlength);
                    jProgressBar.setValue(per);
                    jProgressBar.setString(per + "%");
                    if (count == contentlength)
                    {
                        break;
                    }
                }

                fos.close();
                
            int reply=JOptionPane.showConfirmDialog(rootPane, "File Downloaded, Do You Wish to open it?");
            if(reply==JOptionPane.YES_OPTION)
            {    
                File f=new File (System.getProperty("user.home") + "/Downloads/"+name);
                dispose();
                
                Desktop.getDesktop().open(f);
                
                
                
            }
            
 
            
            } catch (Exception ex) {
                ex.printStackTrace();

            }

            
            
            
            
        }

//To change body of generated methods, choose Tools | Templates.
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
        jProgressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 153));
        setPreferredSize(new java.awt.Dimension(300, 300));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("  This Video will be downloaded to your download folder");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 10, 370, 60);

        jProgressBar.setStringPainted(true);
        getContentPane().add(jProgressBar);
        jProgressBar.setBounds(46, 74, 270, 20);

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
            java.util.logging.Logger.getLogger(DownloadFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DownloadFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DownloadFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DownloadFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//0                new DownloadFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    public javax.swing.JProgressBar jProgressBar;
    // End of variables declaration//GEN-END:variables
}