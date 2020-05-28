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

//This is just a template login, we will still need to change this a bit and still need to make it better

@WebServlet(urlPatterns = { "/discussions" })
public class Discussions extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        try 
        {
            getDiscussions(session);
        } 
        catch (SQLException | NamingException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("discussions.jsp");
        rd.forward(request,response);
        return;
    }
    public void getDiscussions(HttpSession session) throws SQLException, NamingException
    {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        String query = "SELECT * from discussions";
        ResultSet rs = stmt.executeQuery(query);
        ArrayList<String> discussionTitles = new ArrayList<String>();
        ArrayList<String> discussionID = new ArrayList<String>();

        while(rs.next())
        {
            String discussionTitle = rs.getString("title");
            String discussID = rs.getString("discussionID");
            discussionTitles.add(discussionTitle);
            discussionID.add(discussID);
        }
        session.setAttribute("discussionTitles", discussionTitles);
        session.setAttribute("discussionID", discussionID);
    }
}