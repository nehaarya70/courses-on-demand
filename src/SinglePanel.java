/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Twinkle
 */
public class SinglePanel extends javax.swing.JPanel {

    /**
     * Creates new form SinglePanel
     */
    public SinglePanel() {
        initComponents();
        
        setSize(1000, 300);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jL1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(61, 95, 65));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setLayout(null);

        jL1.setBackground(new java.awt.Color(255, 255, 255));
        jL1.setFont(new java.awt.Font("Monotype Corsiva", 3, 18)); // NOI18N
        jL1.setForeground(new java.awt.Color(255, 255, 255));
        jL1.setText("Courses Under:");
        add(jL1);
        jL1.setBounds(20, 10, 420, 30);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jL1;
    // End of variables declaration//GEN-END:variables
}
