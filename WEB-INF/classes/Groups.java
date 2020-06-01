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
@WebServlet(urlPatterns = { "/groups" })
public class Groups extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // checking servlet initializing
        System.out.println("*****Groups Servlet Loaded.*****");

        // attempt to run method
        HttpSession session = request.getSession();
        try {
            getGroups(session);
        } 
        catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

        // redirect user to groups.jsp page
        RequestDispatcher rd = request.getRequestDispatcher("groups.jsp");
        rd.forward(request,response);
        return;
    }

    // method to get all the group names
    public void getGroups(HttpSession session) throws SQLException, NamingException {
        // connection
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();

        // get data from groups table in db
        String query = "SELECT * FROM groups";
        ResultSet rs = stmt.executeQuery(query);
        ArrayList<String> GroupNames = new ArrayList<String>();

        // build list of group names
        //int counter = 0;
        while(rs.next()) {
            String GroupName = rs.getString("group_name");
            //System.out.println("GroupName: " + GroupNames);
            GroupNames.add(GroupName);
            
            //counter++;
            //System.out.println("inloop Counter: " + counter);
        }
        /*System.out.println("Counter: " + counter);

        // testing array indexing
        System.out.println("GroupNames.size(): " + GroupNames.size());

        for(int i = 0; i < GroupNames.size(); i++){
            System.out.println("Group Name " + i + ": " + GroupNames.get(i));
        }*/

        session.setAttribute("GroupNames", GroupNames);
    }
}