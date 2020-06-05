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
@WebServlet(urlPatterns = { "/joinGroup" })
public class JoinGroup extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("\n*****Join Group Servlet Loaded.*****");

        // retrieving data from previous jsp user inputs
        HttpSession session = request.getSession();
        String groupName = request.getParameter("groupName");
        User theUser =((User)session.getAttribute("user"));

        // case: user has already joined a group
        if(theUser.hasGroup()){
            // running method
            try {
                showJoinedGroup(session, groupName, theUser);
            }
            catch (SQLException | NamingException e) {
                e.printStackTrace();
            }

            System.out.println("JAVA VERSION: User " + theUser.getName() + " has already joined the group: " + theUser.getGroup());
        }
        
        // case: user has not joined a group yet, assign to group
        else{
            // running method
            try {
                joinGroup(session, groupName, theUser);
            }
            catch (SQLException | NamingException e) {
                e.printStackTrace();
            }
        }

        // redirect user
        RequestDispatcher rd = request.getRequestDispatcher("hub.jsp");
        rd.forward(request,response);
        return;
    }

    public void joinGroup(HttpSession session, String groupName, User user) throws SQLException, NamingException {
        // setting up connection
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();

        // database inserting
        String query = "INSERT INTO user_groups VALUES(?, ?)";
        PreparedStatement ps = null;
        ps = conn.prepareStatement(query);
        ps.setString(1, user.getName());
        ps.setString(2, groupName);
        ps.executeUpdate();

        user.setGroup(groupName);

        System.out.println("USER " + user.getName() + " JOINED " + user.getGroup());

        // closing connection
        conn.close();
    }

    public void showJoinedGroup(HttpSession session, String groupName, User user) throws SQLException, NamingException {
        System.out.println("theUser.hasGroup() = true");

        // setting up connection
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();

        // get data from groups table in db
        String query = "SELECT * FROM user_groups WHERE username=";
        query += "'" + user.getName() + "'";
        System.out.println("Querying: " + query);
        ResultSet rs = stmt.executeQuery(query);

        // build list of group names
        if(rs.next()) {
            String GroupName = rs.getString("group_name");
            System.out.println("SQL VERSION: User " + user.getName() + " has already joined the group: " + GroupName);
        }

        // closing connection
        conn.close();
    }
}