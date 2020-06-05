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
        System.out.println("*****Join Group Servlet Loaded.*****");

        // retrieving data from previous jsp user inputs
        HttpSession session = request.getSession();
        String groupName = request.getParameter("groupName");
        User theUser =((User)session.getAttribute("user"));

        // case: user has already joined a group
        if(theUser.hasGroup()){
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
            while(rs.next()) {
                String GroupName = rs.getString("group_name");
                GroupNames.add(GroupName);
            }

            // displaying group names in terminal
            for(int i = 0; i < GroupNames.size(); i++){
                System.out.println("Group Name " + i + ": " + GroupNames.get(i));
            }

            // closing
            session.setAttribute("GroupNames", GroupNames);
            conn.close();


            System.out.println("User " + theUser.getName() + " has already joined the group: " + theUser.getGroup());
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
        ps.setString(1,user.getName());
        ps.setString(2,groupName);
        ps.executeUpdate();

        System.out.println("USER " + user.getName() + " JOINED " + groupName);

        // closing connection
        conn.close();
    }
}