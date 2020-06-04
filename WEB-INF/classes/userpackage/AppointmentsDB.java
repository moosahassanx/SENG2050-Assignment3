import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

package classes.userpackage;

public class AppointmentsDB 
{
    public AppointmentsDB()
    {
        username = "";
        session = null;
    }

    public String getUsername() 
    {
        return this.username;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public HttpSession getSession() 
    {
        return this.session;
    }

    public void setSession(HttpSession session) 
    {
        this.session = session;
    }

    public void getAppointments() throws SQLException, NamingException
    {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        PreparedStatement ps = null;
        String query = "SELECT * FROM appointments WHERE username = ?";
        ps = conn.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        ArrayList<String> appointmentIDs = new ArrayList<String>();
        ArrayList<String> appointmentDesc = new ArrayList<String>();
        ArrayList<String> appointmentTeacher = new ArrayList<String>();

        while(rs.next())
        {
            appointmentIDs.add(rs.getString("appointmentID"));
            appointmentDesc.add(rs.getString("description"));
            appointmentTeacher.add(rs.getString("teacher"));
        }

        session.setAttribute("appointmentIDs", appointmentIDs);
        session.setAttribute("appointmentDesc", appointmentDesc);
        session.setAttribute("appointmentTeacher", appointmentTeacher);

        query = "SELECT * FROM website_user_roles WHERE role = ?";
        ps = conn.prepareStatement(query);
        ps.setString(1, "teacher");
        rs = ps.executeQuery();

        ArrayList<String> teacherNames = new ArrayList<String>();

        while(rs.next())
        {
            teacherNames.add(rs.getString("username"));
        }

        session.setAttribute("teacherNames", teacherNames);

    }

    public void writeAppointments(String desc, String teacher) throws SQLException, NamingException
    {

    }
    private String username;
    private HttpSession session;
}