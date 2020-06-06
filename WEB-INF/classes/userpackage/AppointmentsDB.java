/*
    Assignment 3: AppointmentsDB.java
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
    -----------------------------------------------------
    Purpose: This file is used to handle writing and receiving appointments
    from the database. It stores a username and session that it will access in order
    to obtain any relevant data from the server. 
*/
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

    //This function will be used to return the appointments that are currently within the System as well as get the teachers that are within the system to book an appointment with. 
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
        query = "SELECT * FROM website_user_roles WHERE role = ?"; //Repeats the process above but instead grabs the teacher instead so it can allow students to book appointments with teachers
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

    //This function will be used to write new appointments to the Database. 
    public void writeAppointments(String desc, String teacher, User user, String date, String time) throws SQLException, NamingException
    {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        String query = "INSERT INTO appointments VALUES(?,?,?,?,?)"; //Prepares query to place all the data within
        PreparedStatement ps = null;
        ps = conn.prepareStatement(query); //prepares the statement
        ps.setString(1, teacher); //Sets up all the strings to be placed within the DB
        ps.setString(2, user.getName());
        ps.setString(3,desc);
        ps.setString(4, time);
        ps.setString(5, date);
        ps.executeUpdate(); //Executes it and closes the connection. 

        conn.close();

    }

    public void getTeacherAppointments() throws SQLException, NamingException 
    {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        
        PreparedStatement ps = null;
        String query = "SELECT * FROM appointments WHERE teacher = ?"; //getting the data from the ongoing appointments
        ps = conn.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery(); //Sets strings that are required and creates arraylists to hold the returned values
        ArrayList<String> appointmentDesc = new ArrayList<String>();
        ArrayList<String> appointmentDate = new ArrayList<String>();
        ArrayList<String> appointmentTime = new ArrayList<String>();

        while(rs.next()) //Will run through and appoint all the rows into the array lists.
        {
            appointmentDesc.add(rs.getString("description"));
            appointmentTime.add(rs.getString("timeDue"));
            appointmentDate.add(rs.getString("dateDue"));
        }

        session.setAttribute("appointmentDesc", appointmentDesc); //Sets all the attributes in session. 
        session.setAttribute("appointmentDate", appointmentDate);
        session.setAttribute("appointmentTime", appointmentTime);

    }

    private String username;
    private HttpSession session;
}