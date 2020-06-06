/*
    Assignment 3: Groups.java
    Josh R(c3324541), Moosa H (c3324541), Keeylan H ()
    -----------------------------------------------------
    Purpose: This servlet will be used to load up all the Groups
    that are currently within the Database.
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

@WebServlet(urlPatterns = { "/groups" })
public class Groups extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("\n*****Groups Servlet Initialized.*****");

        // attempt to run method
        HttpSession session = request.getSession();
        try {
            DDB.getGroups(session);
        } 
        catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

        // redirect user to groups.jsp page
        RequestDispatcher rd = request.getRequestDispatcher("groups.jsp");
        rd.forward(request,response);
        return;
    }
    
    // controller
    private DBAccess DDB;
}