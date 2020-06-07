/*
    Assignment 3: JoinGroup.java
    Josh R(c3324541), Moosa H (c3324541), Keeylan H ()
    -----------------------------------------------------
    Purpose: This servlet will be used to allow a user 
    to join a group that they have chosen.
*/
//package WEB-INF.classes;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import userpackage.User;
import userpackage.DBAccess;
import java.sql.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.servlet.annotation.WebServlet;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = { "/joinGroup" })
public class JoinGroup extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("\n*****Join Group Servlet Initialized.*****");

        // retrieving data from previous jsp user inputs
        HttpSession session = request.getSession();
        String groupName = request.getParameter("groupName");
        User theUser =((User)session.getAttribute("user")); 

        // the user is a teacher
        if(theUser.isStudent() == false) {
            System.out.println("Group Name: " + groupName);

            // running method
            try {
                DDB.groupDetails(session, groupName);
            }
            catch (SQLException | NamingException e) {
                e.printStackTrace();
            }

            // redirect user
            RequestDispatcher rd = request.getRequestDispatcher("overviewgroup.jsp");
            rd.forward(request,response);
            return;
        }
        
        // the user is a student
        else{
            // case: user has already joined a group
            if(theUser.hasGroup()){
                // running method
                try {
                    DDB.showJoinedGroup(session, groupName, theUser);
                }
                catch (SQLException | NamingException e) {
                    e.printStackTrace();
                }

                System.out.println("User " + theUser.getName() + " has already joined the group: " + theUser.getGroup());
            }
            
            // case: user has not joined a group yet, assign to group
            else{
                // running method
                try {
                    DDB.joinGroup(session, groupName, theUser);
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
    }

    // controller
    private DBAccess DDB;
}