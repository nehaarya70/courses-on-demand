
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SplashScreenAdmin extends javax.swing.JFrame {

    public SplashScreenAdmin() {
        setUndecorated(true);
        initComponents();
        
             setSize(761, 337);
            
            int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = (width / 2) - (this.getWidth()/2);
            int y = (height / 2) - (this.getHeight()/2);
            setLocation(x, y);
            new Thread(new progress()).start();
    }

    public class progress implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                if (i < 30) {
                    jLabel2.setText("Loading");
                    jProgressBar1.setValue(i);
                } else if (i < 60) {
                    jLabel2.setText("Initializing");
                    jProgressBar1.setValue(i);
                } else if (i < 90) {
                    jLabel2.setText("Preparing Modules");
                    jProgressBar1.setValue(i);
                } else {
                    jLabel2.setText("Opening Projects");
                    jProgressBar1.setValue(i);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            dispose();

            new serverGUI().setVisible(true);

        }

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jProgressBar1.setStringPainted(true);
        getContentPane().add(jProgressBar1);
        jProgressBar1.setBounds(0, 320, 760, 20);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 0, 0));
        jLabel3.setText("Welcome To Admin Panel");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(500, 0, 290, 60);

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 230, 170, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/logo_final.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 760, 320);

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
            java.util.logging.Logger.getLogger(SplashScreenAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SplashScreenAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SplashScreenAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SplashScreenAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SplashScreenAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
