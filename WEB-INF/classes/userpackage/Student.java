package userpackage;

import java.sql.*;
import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Student extends User
{
    public Student()
    {
        studentNumber = "";
        averageMark = 0;
        name = "";
        phoneNumber = 0;
        role = "";
    }

    public Student(String name, int phoneNumber, String role, String studentNumber, float averageMark)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.studentNumber = studentNumber;
        this.averageMark = averageMark;
    }

    public void setStudentNumber(String studentNumber)
    {
        this.studentNumber = studentNumber;
    }

    public void setAverageMark(float averageMark)
    {
        this.averageMark = averageMark;
    }

    public String getStudentNumber()
    {
        return studentNumber;
    }

    public float getAverageMark()
    {
        return averageMark;
    }

    public void setGroup(String groupName)
    {
        this.groupName = groupName;
    }

    public String getGroup()
    {
        return groupName;
    }
    
    private String groupName;
    private String studentNumber;
    private float averageMark;

    // NOTE - Will need to comment this out until fixing the database connection or wont compile

    public boolean getStudentUser(String username, String password) throws NamingException, SQLException {

        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG_Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
            // Selecting all data from the website_user table ** Note - only gives username/passwords
            String query = "SELECT * from website_users";
            ResultSet rs = stmt.executeQuery(query);

                // Moving the curser over each tuple
                while(rs.next()){
                    String user = rs.getString("username");
                    if(user.equalsIgnoreCase(username)){
                        String userPass = rs.getString("password");
                        if(userPass.equals(password)){
                          rs.close();
                          conn.close();
                          return true;  
                        }
                    }
                }
    rs.close();
    conn.close();
    return false;
    }


    public boolean getStudentInitialLogin(String username){

        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG_Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        PreparedStatement ps = null;
            query = "SELECT * from initial_login WHERE username = ?";
            ps = conn.prepareStatement(query);
            ps.setObject(1, username);
                while(rs.next()){
                    int initialLogin = rs.getInt("LoginTimes");
                    if(user.equalsIgnoreCase(username) && initialLogin == 0){
                        //Students first login
                        rs.close();
                        conn.close();
                        return true;       
                    }
                }
    rs.close();
    conn.close();
    return false;
    }

}