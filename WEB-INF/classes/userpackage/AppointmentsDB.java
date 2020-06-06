package userpackage;

import userpackage.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;



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
        
        PreparedStatement ps = null;
        String query = "SELECT * FROM appointments WHERE username = ?"; //getting the data from the ongoing appointments
        ps = conn.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery(); //Sets strings that are required and creates arraylists to hold the returned values
        ArrayList<String> appointmentIDs = new ArrayList<String>();
        ArrayList<String> appointmentDesc = new ArrayList<String>();
        ArrayList<String> appointmentTeacher = new ArrayList<String>();
        ArrayList<String> appointmentDate = new ArrayList<String>();
        ArrayList<String> appointmentTime = new ArrayList<String>();

        while(rs.next()) //Will run through and appoint all the rows into the array lists.
        {
            appointmentIDs.add(rs.getString("appointmentID"));
            appointmentDesc.add(rs.getString("description"));
            appointmentTeacher.add(rs.getString("teacher"));
            appointmentTime.add(rs.getString("timeDue"));
            appointmentDate.add(rs.getString("dateDue"));
        }

        session.setAttribute("appointmentIDs", appointmentIDs); //Sets them as attributes to be accessible in a JSP/other files
        session.setAttribute("appointmentDesc", appointmentDesc);
        session.setAttribute("appointmentTeacher", appointmentTeacher);
        session.setAttribute("appointmentDate", appointmentDate);
        session.setAttribute("appointmentTime", appointmentTime);
        System.out.println("lol");
        query = "SELECT * FROM website_user_roles WHERE role = ?";
        ps = conn.prepareStatement(query);
        ps.setString(1, "teacher");
        rs = ps.executeQuery();

        ArrayList<String> teacherNames = new ArrayList<String>();

        while(rs.next())
        {
            System.out.println("xd");
            teacherNames.add(rs.getString("username"));
        }

        session.setAttribute("teacherNames", teacherNames);

    }

    public void writeAppointments(String desc, String teacher, User user, String date, String time) throws SQLException, NamingException
    {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        //Grab from the Database first and check it to see if something with that name ALREADY exists FROM that user!!
        String query = "INSERT INTO appointments VALUES(?,?,?,?,?)";
        PreparedStatement ps = null;
        ps = conn.prepareStatement(query);
        ps.setString(1, teacher);
        ps.setString(2, user.getName());
        ps.setString(3,desc);
        ps.setString(4, time);
        ps.setString(5, date);
        ps.executeUpdate();

        conn.close();

    }
    private String username;
    private HttpSession session;
}