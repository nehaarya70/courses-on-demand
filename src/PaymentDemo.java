import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.FailLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.FrameLoadEvent;
import com.teamdev.jxbrowser.chromium.events.LoadEvent;
import com.teamdev.jxbrowser.chromium.events.LoadListener;
import com.teamdev.jxbrowser.chromium.events.ProvisionalLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.StartLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.StatusEvent;
import com.teamdev.jxbrowser.chromium.events.StatusListener;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import java.awt.BorderLayout;

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

        int amount = 900000;
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
                + "                data-amount=\"" + amount + "\"\n"
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
        
          setSize(700,700);
        setVisible(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
//        Success obj = new Success();
//        obj.setVisible(true);
//        obj.setSize(500, 500);
                
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(PaymentDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaymentDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaymentDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaymentDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaymentDemo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
