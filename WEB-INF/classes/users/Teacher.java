import java.sql.*;
import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Teacher extends User
{
    public Teacher()
    {
        staffNumber = "";
        name = "";
        phoneNumber = 0;
        role = "";
    }

    public Teacher(String name, int phoneNumber, String role, String staffNumber)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.staffNumber = staffNumber;
    }

    public void setStaffNumber(String staffNumber)
    {
        this.staffNumber = staffNumber;
    }

    public String getStaffNumber()
    {
        return staffNumber;
    }

    private String staffNumber;







    public boolean getTeacherUser(String username, String password) throws NamingException, SQLException {

        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG_Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
            // Selecting all data from the website_user table ** Note - only gives username/passwords
            String query = "SELECT * from website_users";
            ResultSet rs = stmt.executeQuery(query);
            PreparedStatement ps = null;

                // Moving the curser over each tuple
                while(rs.next()){
                    String user = rs.getString("username");
                    
                }






     return true;
    }





}