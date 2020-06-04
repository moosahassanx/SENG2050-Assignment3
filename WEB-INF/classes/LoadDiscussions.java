//package WEB-INF.classes;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import userpackage.User;
import userpackage.DiscussionsDB;
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

@WebServlet(urlPatterns = { "/discussionsThread" })
public class LoadDiscussions extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("DIT"));
        session.setAttribute("DIT", id);
        try 
        {
            DDB.getDiscussionsThread(session, id);
        } 
        catch (SQLException | NamingException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("viewdiscussion.jsp");
        rd.forward(request,response);
        return;
    }
    public void getDiscussionsThread(HttpSession session, int id) throws SQLException, NamingException
    {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        PreparedStatement ps = null;
        String query = "SELECT * from discussions WHERE discussionID = ?";
        ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String discussionTitle = rs.getString("title");
        String discussionDesc = rs.getString("description");
        String discussionUsername = rs.getString("username");

        query = "Select * from discussionsThread WHERE discussionID = ?";
        ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        ArrayList<String> threadIDs = new ArrayList<String>();
        ArrayList<String> threadUsernames = new ArrayList<String>();
        ArrayList<String> threadDesc = new ArrayList<String>();

        while(rs.next())
        {
            threadIDs.add(rs.getString("threadID"));
            threadUsernames.add(rs.getString("username"));
            threadDesc.add(rs.getString("description"));
        }

        session.setAttribute("discussionUsername", discussionUsername);
        session.setAttribute("discussionTitle", discussionTitle);
        session.setAttribute("discussionDesc", discussionDesc);
        session.setAttribute("threadIDs", threadIDs);
        session.setAttribute("threadUsernames", threadUsernames);
        session.setAttribute("threadDesc",threadDesc);
        conn.close();
    }
    private DiscussionsDB DDB;
}