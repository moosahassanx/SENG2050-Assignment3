// COPIED OFF JOSH LMAO BUT I JUST NEEDED A START TO MY SERVLET

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
@WebServlet(urlPatterns = { "/joingroup" })
public class JoinGroups extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Testing JoinGroups.java servlet.");

        HttpSession session = request.getSession();
        try {
            getGroups(session);
        } 
        catch (SQLException | NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RequestDispatcher rd = request.getRequestDispatcher("hub.jsp");
        rd.forward(request,response);
        return;
    }

    // definetely not needed cos im lazy and fat i just want this to compile so im not gay
    public void getGroups(HttpSession session) throws SQLException, NamingException {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        String query = "SELECT * from groups";          // get data from groups
        ResultSet rs = stmt.executeQuery(query);
        ArrayList<String> GroupsTitles = new ArrayList<String>();
        ArrayList<String> GroupsID = new ArrayList<String>();

        while(rs.next()) {
            String discussID = rs.getString("GroupsID");
            String GroupsTitle = rs.getString("title");
            GroupsTitles.add(GroupsTitle);
            GroupsID.add(discussID);
        }

        session.setAttribute("GroupsTitles", GroupsTitles);
        session.setAttribute("GroupsID", GroupsID);
    }
}