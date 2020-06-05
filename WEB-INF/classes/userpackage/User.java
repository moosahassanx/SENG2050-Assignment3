package userpackage;

import java.sql.*;
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

    // groups control - FOR MOOSA
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

    public int getStudentUser(String username, String password) throws NamingException, SQLException {        

        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        try{

        
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        System.out.println("Inside db method2");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        System.out.println("Inside db method3");
            // Selecting all data from the website_user table ** Note - only gives username/passwords
            String query = "SELECT * from website_users";
            ResultSet rs = stmt.executeQuery(query);
            PreparedStatement ps = null;

                // Moving the curser over each tuple
                while(rs.next()){
                    String user = rs.getString("username");
                    System.out.println(user);
                    if(user.equalsIgnoreCase(username)){
                        String userPass = rs.getString("password");
                        System.out.println(userPass);
                        if(userPass.equals(password)){
                            System.out.println("im in lol");
                            // Credentials correct now check what role they are
                            query = "SELECT * from website_user_roles WHERE username = ?";
                            System.out.println("2");
                            ps = conn.prepareStatement(query);
                            System.out.println("3");
                            ps.setObject(1, user);
                            System.out.println("4");
                            rs = ps.executeQuery();
                                // Checks what role the user has
                                while(rs.next()){
                                    System.out.println("kek");
                                    System.out.println(rs.getString("role"));
                                    if(rs.getString("role").equalsIgnoreCase("Student")){
                                        rs.close();
                                        conn.close();
                                        return 1;
                                    }else{
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
}