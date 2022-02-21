
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

class Video {

    int videoId, duration, lectureNo;
    String title;

    public Video(int videoId, int duration, int lectureNo, String title) {
        this.videoId = videoId;
        this.duration = duration;
        this.lectureNo = lectureNo;
        this.title = title;
    }
}

public class ManageVideos extends javax.swing.JFrame {

    File selectVideo;
    ArrayList<Video> videoList;
    VideoModel videoModel;

    public ManageVideos() {
        initComponents();
          setSize(930,700 );
         int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = (width / 2) - (this.getWidth()/2);
            int y = (height / 2) - (this.getHeight()/2);
            setLocation(x, y);
         
        getContentPane().setBackground(new Color(61,95,65));
        for (int i = 0; i < Data.categories.length; i++) {
            cbCategories.addItem(Data.categories[i]);

        }
        

        videoList = new ArrayList<Video>();
        videoModel = new VideoModel();
        tableVideo.setModel(videoModel);

    }

    class FetchVideoJob implements Runnable {

        public void run() {
            try {
                videoList.clear();
                System.out.println("Selected Item" + cbCourse.getSelectedItem());
                HttpResponse<String> videoResponse = Unirest.get("http://"+Credentials.ServerIP+":8888/ViewVideos")
                        .queryString("course", cbCourse.getSelectedItem())
                        .asString();
                String videoStr = videoResponse.getBody();
                System.out.println("Client =" + videoStr);
                StringTokenizer st = new StringTokenizer(videoStr, "~");
                while (st.hasMoreTokens()) {
                    String videoRow = st.nextToken();
                    StringTokenizer st1 = new StringTokenizer(videoRow, ";");
                    while (st1.hasMoreTokens()) {
                        int videoId = Integer.parseInt(st1.nextToken());
                        String title = st1.nextToken();
                        int lectureNo = Integer.parseInt(st1.nextToken());
                        int duration = Integer.parseInt(st1.nextToken());
                        videoList.add(new Video(videoId, duration, lectureNo, title));
                    }
                }
                videoModel.fireTableDataChanged();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    class VideoModel extends AbstractTableModel {

        String columnName[] = {"title", "lectureNo", "duration"};

        @Override
        public String getColumnName(int column) {
            return columnName[column];
        }

        public int getRowCount() {
            return videoList.size();
        }

        public int getColumnCount() {
            return columnName.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Video videoRow = videoList.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return videoRow.title;
                case 1:
                    return videoRow.lectureNo;
                case 2:
                    return videoRow.duration;
                default:
                    return "";
            }
        }
    }

    class DeleteVideo implements Runnable {

        public void run() {
            int i = tableVideo.getSelectedRow();
            if (i == -1) {
                JOptionPane.showMessageDialog(rootPane, "Please select a Course");

            } else {
                try {
                    int id = videoList.get(i).videoId;
                    HttpResponse<String> httpResponse = Unirest.get("http://"+Credentials.ServerIP+":8888/DeleteVideo")
                            .queryString("id", id)
                            .asString();
                    String ans = httpResponse.getBody();
                    if (ans.equalsIgnoreCase("Success")) {
                        Thread t = new Thread(new FetchVideoJob());
                        t.start();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, ans);
                    }
                } catch (UnirestException ex) {
                    Logger.getLogger(ViewCourses.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbCategories = new javax.swing.JComboBox();
        cbCourse = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btAdd = new javax.swing.JButton();
        tfVideoTitle = new javax.swing.JTextField();
        tfLec = new javax.swing.JTextField();
        tfDuration = new javax.swing.JTextField();
        tfVideos = new javax.swing.JTextField();
        btBrowse = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        tf_thumbnail = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableVideo = new javax.swing.JTable();
        btDelete = new javax.swing.JButton();
        btFetch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("         Manage Videos");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(380, 10, 220, 24);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 51));
        jLabel2.setText("Select Categories");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(120, 50, 112, 31);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 51));
        jLabel3.setText("Select Course");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(120, 90, 94, 28);

        cbCategories.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCategoriesItemStateChanged(evt);
            }
        });
        getContentPane().add(cbCategories);
        cbCategories.setBounds(360, 50, 360, 30);

        cbCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCourseActionPerformed(evt);
            }
        });
        getContentPane().add(cbCourse);
        cbCourse.setBounds(360, 90, 360, 30);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(null);

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("         Add  New Videos");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 20, 310, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setText("Enter Video Title");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 70, 110, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setText("Enter Lecture No.");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 100, 100, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setText("Enter Duration");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 140, 110, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 153));
        jLabel8.setText("Select Videos");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(20, 190, 120, 30);

        btAdd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btAdd.setForeground(new java.awt.Color(255, 51, 0));
        btAdd.setText("ADD");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });
        jPanel1.add(btAdd);
        btAdd.setBounds(140, 320, 70, 23);
        jPanel1.add(tfVideoTitle);
        tfVideoTitle.setBounds(169, 70, 170, 30);
        jPanel1.add(tfLec);
        tfLec.setBounds(169, 110, 170, 30);
        jPanel1.add(tfDuration);
        tfDuration.setBounds(170, 150, 170, 30);

        tfVideos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfVideosActionPerformed(evt);
            }
        });
        jPanel1.add(tfVideos);
        tfVideos.setBounds(170, 190, 170, 30);

        btBrowse.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btBrowse.setText("Browse");
        btBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBrowseActionPerformed(evt);
            }
        });
        jPanel1.add(btBrowse);
        btBrowse.setBounds(260, 230, 80, 23);

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 153));
        jLabel9.setText("Select Thumbail");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(20, 270, 120, 20);
        jPanel1.add(tf_thumbnail);
        tf_thumbnail.setBounds(170, 270, 170, 30);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(260, 310, 80, 23);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(30, 140, 360, 380);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setForeground(new java.awt.Color(204, 255, 204));
        jPanel2.setLayout(null);

        tableVideo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableVideo);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(20, 20, 420, 290);

        btDelete.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btDelete.setForeground(new java.awt.Color(255, 51, 0));
        btDelete.setText("Delete");
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });
        jPanel2.add(btDelete);
        btDelete.setBounds(160, 323, 130, 30);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(420, 140, 470, 370);

        btFetch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btFetch.setForeground(new java.awt.Color(255, 51, 0));
        btFetch.setText("Fetch ");
        btFetch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFetchActionPerformed(evt);
            }
        });
        getContentPane().add(btFetch);
        btFetch.setBounds(743, 100, 150, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbCategoriesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCategoriesItemStateChanged

        Thread t = new Thread(new FetchCourseJob());
        t.start();
    }//GEN-LAST:event_cbCategoriesItemStateChanged

    private void tfVideosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfVideosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfVideosActionPerformed

    private void btBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBrowseActionPerformed
        JFileChooser jfc = new JFileChooser(System.getProperty("user.home") + File.separator + "Videos");
        int r = jfc.showOpenDialog(this);
        if (r == JFileChooser.APPROVE_OPTION) {
            selectVideo = jfc.getSelectedFile();
            tfVideos.setText(selectVideo.getPath());
        }
    }//GEN-LAST:event_btBrowseActionPerformed

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        AddVideoJob ob1 = new AddVideoJob();
        Thread t1 = new Thread(ob1);
        t1.start();


    }//GEN-LAST:event_btAddActionPerformed

    private void btFetchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFetchActionPerformed

        if (cbCourse.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(rootPane, "Please Select Course");
        } else {
            Thread t = new Thread(new FetchVideoJob());
            t.start();
        }


    }//GEN-LAST:event_btFetchActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed

        Thread t = new Thread(new DeleteVideo());
        t.start();

    }//GEN-LAST:event_btDeleteActionPerformed

    private void cbCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCourseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCourseActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser jfc = new JFileChooser(System.getProperty("user.home") + File.separator + "Videos");
        int r = jfc.showOpenDialog(this);
        if (r == JFileChooser.APPROVE_OPTION) {
            File thumbnail = jfc.getSelectedFile();
            tf_thumbnail.setText(thumbnail.getPath());
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    class AddVideoJob implements Runnable {

        @Override
        public void run() {
            String videoTitle = tfVideoTitle.getText();
            String courseName = (String) cbCourse.getSelectedItem();
            try {
                int duration = Integer.parseInt(tfDuration.getText());
                int LectureNo = Integer.parseInt(tfLec.getText());
                if (videoTitle.equals("") || tfVideos.getText().equals("")) {

                    JOptionPane.showMessageDialog(rootPane, "All Fields must be Filled");
                } else {
                    HttpResponse<String> httpResponse = Unirest.post("http://"+Credentials.ServerIP+":8888/AddVideo")
                            .queryString("videoTitle", videoTitle)
                            .queryString("duration", duration)
                            .queryString("lectureNo", LectureNo)
                            .queryString("courseName", courseName)
                            .field("selectVideo", selectVideo)
                            .field("thumbnail", new File(tf_thumbnail.getText()))
                            .asString();
                    String result = httpResponse.getBody();
                    JOptionPane.showMessageDialog(rootPane, result);

                    FetchVideoJob obj = new FetchVideoJob();
                    Thread t = new Thread(obj);
                    t.start();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Invalid Fields");
                ex.printStackTrace();
            }

        }
    }

    class FetchCourseJob implements Runnable {

        public void run() {
            try {

                String category = (String) cbCategories.getSelectedItem();

                HttpResponse<String> httpResponse = Unirest.get("http://"+Credentials.ServerIP+":8888/FetchCourses")
                        .queryString("category", category)
                        .asString();
                String result = httpResponse.getBody();
                StringTokenizer st = new StringTokenizer(result, ";");
                cbCourse.removeAllItems();
                while (st.hasMoreTokens()) {
                    String title = st.nextToken();
                    cbCourse.addItem(title);
                }
            } catch (UnirestException ex) {

                Logger.getLogger(ManageVideos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

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
            java.util.logging.Logger.getLogger(ManageVideos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageVideos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageVideos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageVideos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageVideos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btBrowse;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btFetch;
    private javax.swing.JComboBox<String> cbCategories;
    private javax.swing.JComboBox<String> cbCourse;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableVideo;
    private javax.swing.JTextField tfDuration;
    private javax.swing.JTextField tfLec;
    private javax.swing.JTextField tfVideoTitle;
    private javax.swing.JTextField tfVideos;
    private javax.swing.JTextField tf_thumbnail;
    // End of variables declaration//GEN-END:variables
}
