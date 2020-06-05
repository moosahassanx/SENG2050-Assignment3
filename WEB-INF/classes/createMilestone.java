//package WEB-INF.classes;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import userpackage.User;
import java.sql.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.servlet.annotation.WebServlet;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = { "/createMilestone" })
public class CreateMilestone extends HttpServlet 
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        String title = request.getParameter("milestoneTitle");
        String desc = request.getParameter("description");
        int id = (int)session.getAttribute("DIT");
        User theUser =((User)session.getAttribute("user"));
        try 
        {
            createMilestoneInDB(session, desc, theUser, id);
        } 
        catch (SQLException | NamingException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("login");
        rd.forward(request,response);
        return;
    }

    // Gotta take this out of the servlet
    public void createMilestoneInDB(HttpSession session, String desc, User user, int id) throws SQLException, NamingException
    {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        //Grab from the Database first and check it to see if something with that name ALREADY exists FROM that user!!
        String query = "INSERT INTO milestones VALUES(?,?,?)";
        PreparedStatement ps = null;
        ps = conn.prepareStatement(query);
        ps.setString(1,desc);
        ps.setString(2,user.getName());
        ps.setString(3,user.getGroup());
        ps.executeUpdate();

        conn.close(); 
    }
}