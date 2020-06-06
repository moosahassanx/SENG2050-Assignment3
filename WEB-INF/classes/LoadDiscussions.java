/*
    Assignment 3: LoadDiscussions
    Josh R(c3324541), Moosa H (), Keeylan H ()
    -----------------------------------------------------
    Purpose: This servlet will be used to get the discussions thread that 
    you have currently clicked on.
    
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

@WebServlet(urlPatterns = { "/discussionsThread" })
public class LoadDiscussions extends HttpServlet { //This will be used to return the discussions to the required JSP. 
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession(); //Grabs the session to pass through. 
        int id = Integer.parseInt(request.getParameter("DIT"));
        session.setAttribute("DIT", id); //sets the ID to an attribute in the session
        try 
        {
            DDB.getDiscussionsThread(session, id); //calls the DB object in order to get the discussion thread
        } 
        catch (SQLException | NamingException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("viewdiscussion.jsp"); //gets the data prepared to pass to the next page
        rd.forward(request,response); //Forwards to the next page
        return;
    }
    private DBAccess DDB;
}