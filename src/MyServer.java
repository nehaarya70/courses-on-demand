
import com.vmm.JHTTPServer;
import com.vmm.NanoHTTPD;
import static com.vmm.NanoHTTPD.HTTP_OK;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MyServer extends JHTTPServer {

    DbLoader dbLoader;

    public MyServer(int port) throws IOException {
        super(port);
        dbLoader = new DbLoader();
    }

    @Override
    public NanoHTTPD.Response serve(String uri, String method, Properties header, Properties parms, Properties files) {

        NanoHTTPD.Response res = null;

        if (uri.contains("/GetResource")) {
            uri = uri.substring(1);
            uri = uri.substring(uri.indexOf("/") + 1);
            res = sendCompleteFile(uri);
        } else if (uri.contains("/StreamMedia")) {
            uri = uri.substring(1);
            uri = uri.substring(uri.indexOf("/") + 1);
            res = streamFile(uri, method, header);
        } else if (uri.contains("/CheckAdminLogin")) {

            String email = parms.getProperty("email");
            String pass = parms.getProperty("pass");

            String ans = "";
            ResultSet result = dbLoader.getResultSet("select * from admin where email='" + email + "' && password='" + pass + "'");
            try {
                if (result.next()) {
                    ans = "Login Success";
                } else {
                    ans = "Login Failed";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            res = new Response(HTTP_OK, "text/plain", ans);

        } else if (uri.contains("AddCourse")) {
            try {
                String title = parms.getProperty("title");
                String desc = parms.getProperty("desc");
                String category = parms.getProperty("category");
                int amount = Integer.parseInt(parms.getProperty("amount"));
                String preReq = parms.getProperty("pre-req");
                String widePhotoName = saveFileOnServerWithRandomName(files, parms, "widePhoto", "src/content");
                String squarePhotoName = saveFileOnServerWithRandomName(files, parms, "squarePhoto", "src/content");
                String sampleVideoName = saveFileOnServerWithRandomName(files, parms, "sampleVideo", "src/content");

                ResultSet resultSet = dbLoader.getResultSet("select * from Courses where course_name='" + title + "'");

                if (resultSet.next()) {
                    res = new Response(HTTP_OK, "text/plain", "Course already exist");
                } else {
                    resultSet.moveToInsertRow();
                    resultSet.updateString("course_name", title);
                    resultSet.updateString("desc", desc);
                    resultSet.updateString("category", category);
                    resultSet.updateString("wide_photo", "src/content/" + widePhotoName);
                    resultSet.updateString("square_photo", "src/content/" + squarePhotoName);
                    resultSet.updateString("sample_video", "src/content/" + sampleVideoName);
                    resultSet.updateString("pre_requisite", preReq);
                    resultSet.updateInt("amount", amount);
                    resultSet.insertRow();
                    res = new Response(HTTP_OK, "text/plain", "Course Inserted");

                }
            } catch (SQLException ex) {
                Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.contains("ViewCourses")) {
            try {
                String result = "";
                ResultSet resultSet = dbLoader.getResultSet("select * from courses");
                while (resultSet.next()) {
                    result += resultSet.getString("course_name") + ";" + resultSet.getString("desc") + ";" + resultSet.getString("pre_requisite") + ";" + resultSet.getInt("amount") + "~";
                }
                res = new Response(HTTP_OK, "text/plain", result);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (uri.contains("DeleteCourse")) {
            String re = "";
            String title = parms.getProperty("title");
            ResultSet rs = dbLoader.getResultSet("select * from courses where course_name='" + title + "'");
            try {
                if (rs.next()) {
                    rs.deleteRow();
                    re = "success";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                re = "failed";

            }
            res = new Response(HTTP_OK, "text/plain", re);
        } else if (uri.contains("FetchCourses")) {
            String result = "";
            try {
                String category = parms.getProperty("category");
                ResultSet rs = dbLoader.getResultSet("select * from courses where category='" + category + "'");

                while (rs.next()) {
                    result += rs.getString("course_name") + ";";
                }
            } catch (SQLException ex) {
                Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            res = new Response(HTTP_OK, "text/plain", result);
        } else if (uri.contains("AddVideo")) {
            try {
                String videoTitle = parms.getProperty("videoTitle");
                String courseName = parms.getProperty("courseName");
                int duration = Integer.parseInt(parms.getProperty("duration"));
                int lectureNo = Integer.parseInt(parms.getProperty("lectureNo"));

                String selectVideo = saveFileOnServerWithRandomName(files, parms, "selectVideo", "src/videos");
                String thumbnail = saveFileOnServerWithRandomName(files, parms, "thumbnail", "src/videos");

                ResultSet resultSet = dbLoader.getResultSet("select * from videos where lecture_no =" + lectureNo + " and title ='" + videoTitle + "'");

                if (resultSet.next()) {
                    res = new Response(HTTP_OK, "text/plain", "Video already exist");
                } else {
                    resultSet.moveToInsertRow();
                    resultSet.updateString("title", videoTitle);
                    resultSet.updateInt("duration", duration);
                    resultSet.updateInt("lecture_no", lectureNo);
                    resultSet.updateString("video_path", "src/videos/" + selectVideo);
                    resultSet.updateString("video_thumbnail", "src/videos/" + thumbnail);
                    resultSet.updateString("course_name", courseName);
                    resultSet.insertRow();
                    res = new Response(HTTP_OK, "text/plain", "Video Inserted");

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (uri.contains("ViewVideos")) {
            String result = "";
            try {
                String course = parms.getProperty("course");
                ResultSet resultSet = dbLoader.getResultSet("select * from videos where course_name=(select course_name from courses where course_name='" + course + "')");

                while (resultSet.next()) {
                    result += resultSet.getInt("video_id") + ";" + resultSet.getString("title") + ";" + resultSet.getInt("lecture_no") + ";" + resultSet.getInt("duration") + "~";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            res = new Response(HTTP_OK, "text/plain", result);

        } else if (uri.contains("DeleteVideo")) {
            try {
                int id = Integer.parseInt(parms.getProperty("id"));
                ResultSet resultSet = dbLoader.getResultSet("select * from videos where video_id =" + id);
                if (resultSet.next()) {
                    resultSet.deleteRow();
                    res = new Response(HTTP_OK, "text/plain", "Success");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                res = new Response(HTTP_OK, "text/plain", "Failed");
            }
        } else if (uri.contains("SignUp")) {
            try {
                String email = parms.getProperty("email");
                String name = parms.getProperty("name");
                String password = parms.getProperty("password");
                String question = parms.getProperty("question");
                String answer = parms.getProperty("answer");
                long phone = Long.parseLong(parms.getProperty("phone"));
                String fileName = saveFileOnServerWithRandomName(files, parms, "photo", "src/pictures");

                ResultSet resultSet = dbLoader.getResultSet("select * from user where email='" + email + "'");
                if (resultSet.next()) {
                    res = new Response(HTTP_OK, "text/plain", "User already exist");
                } else {
                    resultSet.moveToInsertRow();

                    resultSet.updateString("email", email);
                    resultSet.updateString("name", name);
                    resultSet.updateString("password", password);
                    resultSet.updateString("question", question);
                    resultSet.updateString("answer", answer);
                    resultSet.updateString("photo", "src/pictures/" + fileName);

                    resultSet.updateLong("phone", phone);
                    resultSet.insertRow();
                    res = new Response(HTTP_OK, "text/plain", "Success");
                }
            } catch (SQLException ex) {
                Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.contains("CheckUserLogin")) {
            String email = parms.getProperty("email");
            String password = parms.getProperty("password");

            String ans = "";
            ResultSet result = dbLoader.getResultSet("select * from user where email='" + email + "' and password='" + password + "'");
            try {
                if (result.next()) {
                    ans = "Login Success";
                } else {
                    ans = "Login Failed";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            res = new Response(HTTP_OK, "text/plain", ans);

        } else if (uri.contains("GetCoursesOfCat")) {
            try {
                String category = parms.getProperty("category");
                ResultSet resultSet = dbLoader.getResultSet("select * from courses where category='" + category + "'");
                String ans = "";
                while (resultSet.next()) {
                    ans += resultSet.getString("course_name") + ";" + resultSet.getInt("amount") + ";" + resultSet.getString("square_photo") + "~";
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (SQLException ex) {
                Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.contains("ViewFullCourse")) {
            try {

                String courseName = parms.getProperty("courseName");
                String email = parms.getProperty("email");

                ResultSet resultSet = dbLoader.getResultSet("select * from courses where course_name='" + courseName + "'");
                String ans = "";
                if (resultSet.next()) {

                    ans += resultSet.getString("category") + ";" + resultSet.getInt("amount") + ";" + resultSet.getString("pre_requisite") + ";" + resultSet.getString("wide_photo") + ";" + resultSet.getString("square_photo") + ";" + resultSet.getString("sample_video");
                }

                resultSet = dbLoader.getResultSet("select * from user_subscription where course_name='" + courseName + "' and user_email='" + email + "'");
                if (resultSet.next()) {

                    ans = ans + ";yes";

                } else {
                    ans = ans + ";no";
                }

                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (SQLException ex) {
                Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (uri.contains("FetchAllVideos")) {

            try {
                String courseName = parms.getProperty("courseName");
                ResultSet resultSet = dbLoader.getResultSet("select * from videos where course_name='" + courseName + "'");
                String ans = "";
                while (resultSet.next()) {
                    ans += resultSet.getString("title") + ";" + resultSet.getInt("lecture_no") + ";" + resultSet.getInt("duration") + ";" + resultSet.getString("video_thumbnail") + ";" + resultSet.getString("video_path") + "~";
                }

                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception ex) {
                Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.contains("Subscribe")) {
            try {
                String courseName = parms.getProperty("courseName");
                String email = parms.getProperty("email");
                ResultSet resultSet = dbLoader.getResultSet("select * from user_subscription ");
                resultSet.moveToInsertRow();
                resultSet.updateString("user_email", email);
                resultSet.updateString("course_name", courseName);
                resultSet.insertRow();
                res = new Response(HTTP_OK, "text/plain", " course subscribed");
            } catch (SQLException ex) {
                Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.contains("FetchMyProfile")) {
            String ans = "";
            try {
                String email = parms.getProperty("email");
                ResultSet resultSet = dbLoader.getResultSet("select * from user  where email='" + email + "'");

                if (resultSet.next()) {

                    ans += resultSet.getString("name") + ";" + resultSet.getLong("phone") + ";" + resultSet.getString("photo");
                }
            } catch (SQLException ex) {
                Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            res = new Response(HTTP_OK, "text/plain", ans);

        }
        else if (uri.contains("ChangePasswordAdmin")) {
            String ans = "";
            try {
                String email = parms.getProperty("email");
                String oldPass = parms.getProperty("oldPass");
                String newPass = parms.getProperty("newPass");
                ResultSet resultSet = dbLoader.getResultSet("select * from admin where email='" + email + "' and password='" + oldPass + "'");
                if (resultSet.next())
                {

                    resultSet.updateString("password", newPass);
                    resultSet.updateRow();
                    res = new Response(HTTP_OK, "text/plain", "Success");

                } 
                else {
                    res = new Response(HTTP_OK, "text/plain", "Wrong Old Password entered");

                }

            } catch (Exception ex) {
                Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        } 
        
        else if (uri.contains("ChangePassword")) {
            String ans = "";
            try {
                String email = parms.getProperty("email");
                String oldPass = parms.getProperty("oldPass");
                String newPass = parms.getProperty("newPass");
                ResultSet resultSet = dbLoader.getResultSet("select * from user where email='" + email + "' and password='" + oldPass + "'");
                if (resultSet.next()) {

                    resultSet.updateString("password", newPass);
                    resultSet.updateRow();
                    res = new Response(HTTP_OK, "text/plain", "Success");

                } else {
                    res = new Response(HTTP_OK, "text/plain", "Wrong Old Password entered");

                }

            } catch (Exception ex) {
                Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (uri.contains("FetchSubs")) {
            String email = parms.getProperty("email");
            String ans = "";
            ResultSet resultSet = dbLoader.getResultSet("select course_name,square_photo from courses where course_name IN (select course_name from user_subscription where user_email='" + email + "')");
            try {
                while (resultSet.next()) {
                    ans += resultSet.getString("course_name") + ";" + resultSet.getString("square_photo") + "~";
                }

                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (uri.contains("ForgotPassword")) {
            String email = parms.getProperty("email");
            ResultSet resultSet = dbLoader.getResultSet("select * from user where email='" + email + "'");
            String ans = "";
            try {
                if (resultSet.next()) {
                    ans = resultSet.getString("question") + ";" + resultSet.getString("answer") + ";" + resultSet.getString("phone") + ";" + resultSet.getString("password");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            res = new Response(HTTP_OK, "text/plain", ans);
        }
         
        
         
        return res;
    }
}
