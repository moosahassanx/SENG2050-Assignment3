/*
    Assignment 3: CreateDiscussionThread.java
    Josh R(c3324541), Moosa H (), Keeylan H ()
    -----------------------------------------------------
    Purpose: This servlet will be used whenever a new reply is made on a discussions
    thread. 
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


@WebServlet(urlPatterns = { "/createDiscussionThread" })
public class CreateDiscussionThread extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession(); //Grabs the Session and necessary data from session/request.
        String desc = request.getParameter("description");
        int id = (int)session.getAttribute("DIT");
        User theUser =((User)session.getAttribute("user"));
        try 
        {
            DDB.createDiscussionsThread(session, desc, theUser, id); //Calls the function to create the reply to the discussion. 
        } 
        catch (SQLException | NamingException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("discussions.jsp"); //Prepares to forward to the next page.
        rd.forward(request,response); //Forwards to the next page. 
        return;
    }
    private DBAccess DDB;
}