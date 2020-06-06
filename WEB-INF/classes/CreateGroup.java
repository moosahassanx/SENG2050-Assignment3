/*
    Assignment 3: CreateGroup.java
    Josh R(c3324541), Moosa H (c3324541), Keeylan H ()
    -----------------------------------------------------
    Purpose: This servlet will be called when a group is created. This will 
    run the function in another file to write it to the DB. 
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

//This is just a template login, we will still need to change this a bit and still need to make it better

@WebServlet(urlPatterns = { "/createGroup" })
public class CreateGroup extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("*****Create Group Servlet Loaded.*****");

        // retrieving data from previous jsp user inputs
        HttpSession session = request.getSession();
        String groupName = request.getParameter("groupNameInput");
        String subject = request.getParameter("subjectInput");
        User theUser =((User)session.getAttribute("user"));

        // display group name and subject in terminal
        System.out.println("groupName: " + groupName);
        System.out.println("Subject: " + subject);

        // running method
        try {
            DDB.newGroup(session, groupName, subject, theUser);
        }
        catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

        // redirect user
        RequestDispatcher rd = request.getRequestDispatcher("hub.jsp");
        rd.forward(request,response);
        return;
    }

    // controller
    private DBAccess DDB;
}