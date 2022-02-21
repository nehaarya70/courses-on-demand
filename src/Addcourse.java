
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Addcourse extends javax.swing.JFrame {

    File widePhoto, squarePhoto, sampleVideo;

    public Addcourse() {
        initComponents();

        cbCat.removeAllItems();

        for (int i = 0; i < Data.categories.length; i++) {
            cbCat.addItem(Data.categories[i]);

        }
        setSize(600, 650);

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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tfTitle = new javax.swing.JTextField();
        tfWide = new javax.swing.JTextField();
        tfSquare = new javax.swing.JTextField();
        tfVideo = new javax.swing.JTextField();
        tfAmount = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDesc = new javax.swing.JTextArea();
        br1 = new javax.swing.JButton();
        br2 = new javax.swing.JButton();
        br3 = new javax.swing.JButton();
        cbCat = new javax.swing.JComboBox();
        btOk = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taPreReq = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel1.setText("        Add Course");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(200, 20, 180, 29);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 102));
        jLabel2.setText("Enter Title");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 80, 170, 30);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 102));
        jLabel3.setText("Enter Description");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 150, 180, 24);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 102));
        jLabel4.setText("Select Category");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 210, 180, 30);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 102));
        jLabel5.setText("Select Wide Photo");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 270, 200, 24);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 102));
        jLabel6.setText("Select  Square Photo");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(20, 310, 200, 20);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 102));
        jLabel7.setText("Select Sample Video");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(20, 360, 200, 20);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 102));
        jLabel8.setText("Enter Amount");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(20, 500, 180, 20);

        tfTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTitleActionPerformed(evt);
            }
        });
        getContentPane().add(tfTitle);
        tfTitle.setBounds(220, 80, 200, 30);
        getContentPane().add(tfWide);
        tfWide.setBounds(220, 260, 210, 30);
        getContentPane().add(tfSquare);
        tfSquare.setBounds(220, 310, 210, 30);
        getContentPane().add(tfVideo);
        tfVideo.setBounds(220, 360, 210, 30);
        getContentPane().add(tfAmount);
        tfAmount.setBounds(220, 500, 230, 30);

        taDesc.setColumns(20);
        taDesc.setRows(5);
        jScrollPane1.setViewportView(taDesc);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(220, 130, 210, 70);

        br1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        br1.setText("Browser");
        br1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                br1ActionPerformed(evt);
            }
        });
        getContentPane().add(br1);
        br1.setBounds(440, 270, 90, 23);

        br2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        br2.setText("Browser");
        br2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                br2ActionPerformed(evt);
            }
        });
        getContentPane().add(br2);
        br2.setBounds(440, 310, 90, 23);

        br3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        br3.setText("Browser");
        br3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                br3ActionPerformed(evt);
            }
        });
        getContentPane().add(br3);
        br3.setBounds(440, 370, 90, 23);

        cbCat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCatActionPerformed(evt);
            }
        });
        getContentPane().add(cbCat);
        cbCat.setBounds(220, 210, 210, 30);

        btOk.setText("OK");
        btOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOkActionPerformed(evt);
            }
        });
        getContentPane().add(btOk);
        btOk.setBounds(500, 560, 60, 23);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 102));
        jLabel9.setText("Pre-requesites");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(20, 410, 190, 30);

        taPreReq.setColumns(20);
        taPreReq.setRows(5);
        jScrollPane2.setViewportView(taPreReq);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(220, 410, 230, 70);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTitleActionPerformed

    }//GEN-LAST:event_tfTitleActionPerformed

    private void br1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_br1ActionPerformed
        JFileChooser jfc = new JFileChooser();
        int r = jfc.showOpenDialog(this);
        if (r == JFileChooser.APPROVE_OPTION) {
            widePhoto = jfc.getSelectedFile();
            tfWide.setText(widePhoto.getPath());

        }
    }//GEN-LAST:event_br1ActionPerformed

    private void br2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_br2ActionPerformed
        // TODO add your handling code here:
        JFileChooser jfc = new JFileChooser();
        int r = jfc.showOpenDialog(this);
        if (r == JFileChooser.APPROVE_OPTION) {
            squarePhoto = jfc.getSelectedFile();
            tfSquare.setText(squarePhoto.getPath());
        }
    }//GEN-LAST:event_br2ActionPerformed

    private void br3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_br3ActionPerformed
        // TODO add your handling code here:
        JFileChooser jfc = new JFileChooser();
        int r = jfc.showOpenDialog(this);
        if (r == JFileChooser.APPROVE_OPTION) {
            sampleVideo = jfc.getSelectedFile();
            tfVideo.setText(sampleVideo.getPath());
        }
    }//GEN-LAST:event_br3ActionPerformed

    private void btOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkActionPerformed
        // TODO add your handling code here:
        String widePhotoPath = tfWide.getText();
        String squarePhotoPath = tfSquare.getText();
        String sampleVideoPath = tfVideo.getText();
        String title = tfTitle.getText();
        String desc = taDesc.getText().trim();
        String preReq = taPreReq.getText().trim();
        String category = (String) cbCat.getSelectedItem();
        int amount = 0;
        try {
            amount = Integer.parseInt(tfAmount.getText());
            if (title.equals("") || squarePhotoPath.equals("") || sampleVideoPath.equals("") || desc.equals("") || category.equals("") || widePhotoPath.equals("")) {
                JOptionPane.showMessageDialog(rootPane, "All Fields must be Filled");
            } else {
                HttpResponse<String> httpResponse = Unirest.post("http://"+Credentials.ServerIP+":8888/AddCourse")
                        .queryString("title", title)
                        .queryString("desc", desc)
                        .queryString("amount", amount)
                        .queryString("category", category)
                        .queryString("pre-req", preReq)
                        .field("widePhoto", widePhoto)
                        .field("squarePhoto", squarePhoto)
                        .field("sampleVideo", sampleVideo)
                        .asString();
                String result = httpResponse.getBody();
                JOptionPane.showMessageDialog(rootPane, result);
                
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Invalid Fields");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btOkActionPerformed

    private void cbCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCatActionPerformed

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
            java.util.logging.Logger.getLogger(Addcourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Addcourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Addcourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Addcourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Addcourse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton br1;
    private javax.swing.JButton br2;
    private javax.swing.JButton br3;
    private javax.swing.JButton btOk;
    private javax.swing.JComboBox<String> cbCat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea taDesc;
    private javax.swing.JTextArea taPreReq;
    private javax.swing.JTextField tfAmount;
    private javax.swing.JTextField tfSquare;
    private javax.swing.JTextField tfTitle;
    private javax.swing.JTextField tfVideo;
    private javax.swing.JTextField tfWide;
    // End of variables declaration//GEN-END:variables
}
