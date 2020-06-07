/*
    Assignment 3: CreateDiscussions.java
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
    -----------------------------------------------------
    Purpose: This servlet will be responsible for creating the discussions when 
    the call has been made. 
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


@WebServlet(urlPatterns = { "/GiveFeedback" })
public class GiveFeedback extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession(); //Grabs sessions and gets the data that is required.
        String comment = request.getParameter("comment");
        int mark = Integer.parseInt(request.getParameter("mark"));
        User theUser = ((User)session.getAttribute("user"));

        // running method
        try {
            DDB.giveFeedback(comment, mark); //Runs the function to write it to the DB
        }
        catch (SQLException | NamingException e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = request.getRequestDispatcher("creatediscussion.jsp"); //Prepares to forward
        rd.forward(request,response); //Gets forwarded. 
        return;
    }

    private DBAccess DDB;
}