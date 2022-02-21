
public class SingleCoursePanel extends javax.swing.JPanel {

    public SingleCoursePanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbName = new javax.swing.JLabel();
        lbAmount = new javax.swing.JLabel();
        lbIcon = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 102), 2));
        setLayout(null);

        lbName.setFont(new java.awt.Font("Monotype Corsiva", 3, 24)); // NOI18N
        lbName.setForeground(new java.awt.Color(61, 95, 65));
        lbName.setText("Video Name");
        add(lbName);
        lbName.setBounds(10, 90, 210, 30);

        lbAmount.setText("Amount");
        add(lbAmount);
        lbAmount.setBounds(10, 120, 120, 20);

        lbIcon.setText("jLabel1");
        add(lbIcon);
        lbIcon.setBounds(2, 0, 180, 90);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel lbAmount;
    public javax.swing.JLabel lbIcon;
    public javax.swing.JLabel lbName;
    // End of variables declaration//GEN-END:variables
}
