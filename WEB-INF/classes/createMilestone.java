/*
    Assignment 3: CreateMilestone.java
    Josh R(c3324541), Moosa H (), Keeylan H ()
    -----------------------------------------------------
    Purpose: This Servlet will be used whenever a Milestone is created
    off of a JSP. 
*/
//package WEB-INF.classes;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import userpackage.DBAccess;
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

@WebServlet(urlPatterns = { "/CreateMilestone" })
public class CreateMilestone extends HttpServlet 
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession(); //Grabs session, and other required data
        String title = request.getParameter("milestoneTitle");
        String desc = request.getParameter("description");
        int id = (int)session.getAttribute("DIT");
        User theUser = session.getAttribute("user"); 
        try 
        {
            DBA.createMilestoneInDB(session, desc, theUser, id); //Runs the function in the DBAccess to write it to the DB
        } 
        catch (SQLException | NamingException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("login"); //prepares to forward
        rd.forward(request,response); //forwards
        return;
    }


    private DBAccess DBA; 
}