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

    public User(String name, int phoneNumber, String role)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
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


    protected String name;
    protected int phoneNumber;
    protected String role;
    protected String password;


    // NOTE - Will need to comment this out until fixing the database connection or wont compile
    // Need to fix to be generic for either Student and a Teacher

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
    
    public boolean getStudentInitialLogin(String username) throws NamingException, SQLException {

        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG_Assignment3/collabDB");
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