/*
    Assignment 3: User.java
    Josh R(c3324541), Moosa H (c3324541), Keeylan H ()
    -----------------------------------------------------
    Purpose: this will be the main bean of the server. It holds all the user's
    information as well as connects to the DB. 
*/
package userpackage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class User
{
    public User()
    {
        name = "";
        phoneNumber = 0;
        role = "";
        password = "";
    }

    public User(String name, int phoneNumber, String role, boolean student)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.student = student;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPhoneNumber(int phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getName()
    {
        return name;
    }

    public int phoneNumber()
    {
        return phoneNumber;
    }

    public String getRole()
    {
        return role;
    }

    public void setPassword(String p){
        this.password = p;
    }

    public void setStudent(boolean student)
    {
        this.student = student;
    }

    public boolean isStudent()
    {
        return student;
    }

    public boolean hasGroup()
    {
        if (group == "")
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void setGroup(String group)
    {
        this.group = group;
    }

    public String getGroup()
    {
        return group;
    }
    
    private String group;
    protected String name;
    protected int phoneNumber;
    protected String role;
    protected String password;
    private boolean student;

    // NOTE - Will need to comment this out until fixing the database connection or wont compile
    // Need to fix to be generic for either Student and a Teacher

    //This function returns an int and checks if the user is a Student. 
    public int getStudentUser(String username, String password) throws NamingException, SQLException {        

        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        try{
        
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            // Selecting all data from the website_user table ** Note - only gives username/passwords
            String query = "SELECT * from website_users";
            ResultSet rs = stmt.executeQuery(query);
            PreparedStatement ps = null;

            // Moving the curser over each tuple
            while(rs.next()){
                String user = rs.getString("username");
                if(user.equalsIgnoreCase(username)){
                    String userPass = rs.getString("password");
                    if(userPass.equals(password)){
                        // Credentials correct now check what role they are
                        query = "SELECT * from website_user_roles WHERE username = ?";
                        ps = conn.prepareStatement(query);
                        ps.setObject(1, user);
                        rs = ps.executeQuery();
                        // Checks what role the user has
                        while(rs.next()){

                            // user is a student
                            if(rs.getString("role").equalsIgnoreCase("Student")){
                                rs.close();
                                conn.close();
                                return 1;
                            }
                            // user is a teacher
                            else{
                                rs.close();
                                conn.close();
                                return 2;
                            }
                        }
                    }
                }
            }
            rs.close();
            conn.close();
            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 7;
    }
    
    //This function will return whether it is the student's first time logging in or not.
    public boolean getStudentInitialLogin(String username) throws NamingException, SQLException {

        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        PreparedStatement ps = null;
		
        String query = "SELECT * from initial_login WHERE username = ?";
        ps = conn.prepareStatement(query);
        ps.setObject(1, username);
        ResultSet rs = stmt.executeQuery(query);
        
        while(rs.next()){
            int initialLogin = rs.getInt("LoginTimes");
            if(username.equalsIgnoreCase(username) && initialLogin == 0){
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

    // Populates a List of usernames currently in the group passed in
    public static List<String> getUserList(String groupName) throws NamingException, SQLException {

        List<String> list = new ArrayList<String>();
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select * from user_groups where group_name = ?");
        ps.setString(1, groupName);

        ResultSet rs = ps.executeQuery();

         while(rs.next()){
            String username = rs.getString("username");
            list.add(username);
        }

        return list;
    }
}