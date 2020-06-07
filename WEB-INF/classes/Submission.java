/*
    Assignment 3: Submission.java
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
    -----------------------------------------------------
    Purpose: This servlet will be used whenever there is a submission
    made by the Student. 
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


@WebServlet(urlPatterns = { "/Submission" })
public class Submission extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        int MID = Integer.parseInt(request.getParameter("MID"));
        session.setAttribute("MID", MID);
        RequestDispatcher rd = request.getRequestDispatcher("submission.jsp"); //Prepares to forward to the next page.
        rd.forward(request,response); //Forwards to the next page. 
        return;
    }    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession(); //Grabs the Session and necessary data from session/request.
        String desc = request.getParameter("subComment");
        int id = (int)session.getAttribute("MID");
        User theUser =((User)session.getAttribute("user"));
        String group = theUser.getGroup();
        try 
        {
            DDB.writeSubmission(group, id, desc); //Calls the function to create the reply to the discussion. 
        } 
        catch (SQLException | NamingException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("viewmilestones.jsp"); //Prepares to forward to the next page.
        rd.forward(request,response); //Forwards to the next page. 
        return;
    }
    private DBAccess DDB;
}