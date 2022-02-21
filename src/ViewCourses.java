
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

class Courses {

    public String courseName, preReq, desc;
    public int amount;

    public Courses(String courseName, String preReq, String desc, int amount) {
        this.courseName = courseName;
        this.preReq = preReq;
        this.desc = desc;
        this.amount = amount;
    }

}

public class ViewCourses extends javax.swing.JFrame {

    ArrayList<Courses> coursesList;
    CoursesModel coursesModel;

    public ViewCourses() {
        initComponents();
        setSize(700, 500);
        coursesList = new ArrayList<Courses>();
        coursesModel = new CoursesModel();
        tableCourses.setModel(coursesModel);
        Thread t = new Thread(new Job());
        t.start();
         int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            int x = (width / 2) - (this.getWidth()/2);
            int y = (height / 2) - (this.getHeight()/2);
            setLocation(x, y);
         
        getContentPane().setBackground(new Color(61,95,65));

    }

    class Job implements Runnable {

        public void run() {
            try {
                coursesList.clear();
                HttpResponse<String> coursesResponse = Unirest.get("http://"+Credentials.ServerIP+":8888/ViewCourses")
                        .asString();
                String coursesStr = coursesResponse.getBody();
                System.out.println("String from server" + coursesStr);
                StringTokenizer st = new StringTokenizer(coursesStr, "~");
                while (st.hasMoreTokens()) {
                    String course = st.nextToken();
                    StringTokenizer st1 = new StringTokenizer(course, ";");
                    while (st1.hasMoreTokens()) {
                        String title = st1.nextToken();
                        String desc = st1.nextToken();
                        String preReq = st1.nextToken();
                        int amount = Integer.parseInt(st1.nextToken());
                        coursesList.add(new Courses(title, preReq, desc, amount));
                    }
                }
                coursesModel.fireTableDataChanged();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    class CoursesModel extends AbstractTableModel {

        String columnName[] = {"Title", "Description", "Pre-requisites", "Amount"};

        @Override
        public String getColumnName(int column) {
            return columnName[column];
        }

        public int getRowCount() {
            return coursesList.size();
        }

        public int getColumnCount() {
            return columnName.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Courses courseRow = coursesList.get(rowIndex);
            System.out.println(courseRow.courseName + "--");
            switch (columnIndex) {
                case 0:
                    return courseRow.courseName;
                case 1:
                    return courseRow.desc;
                case 2:
                    return courseRow.preReq;
                case 3:
                    return courseRow.amount;
                default:
                    return "";
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCourses = new javax.swing.JTable();
        btDelete = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText(" Courses ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(290, 30, 120, 31);

        tableCourses.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tableCourses);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(151, 104, 375, 275);

        btDelete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btDelete.setForeground(new java.awt.Color(255, 0, 0));
        btDelete.setText("Delete Course");
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btDelete);
        btDelete.setBounds(290, 400, 130, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed

        Thread t=new Thread(new DeleteCourse());
        t.start();
    }//GEN-LAST:event_btDeleteActionPerformed

    class DeleteCourse implements Runnable {

        public void run() {
            int i = tableCourses.getSelectedRow();
            if (i == -1) {
                JOptionPane.showMessageDialog(rootPane, "Please select a Course");

            } else {
                try {
                    String title = coursesList.get(i).courseName;
                    HttpResponse<String> httpResponse = Unirest.get("http://"+Credentials.ServerIP+":8888/DeleteCourse")
                            .queryString("title", title)
                            .asString();
                    String ans = httpResponse.getBody();
                    if (ans.equals("success")) {
                        Thread t = new Thread(new Job());
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

     
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewCourses().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDelete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tableCourses;
    // End of variables declaration//GEN-END:variables
}
