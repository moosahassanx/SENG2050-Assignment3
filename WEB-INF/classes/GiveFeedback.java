/*
    Assignment 3: GiveFeedback.java
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
    -----------------------------------------------------
    Purpose: This servlet will be used to relay the feedback for an assignment
    to the DB. 
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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String split = request.getParameter("SID");
        String[] splitter = split.split("::");
        int SID = Integer.parseInt(splitter[0]);
        int MID = Integer.parseInt(splitter[1]);
        HttpSession session = request.getSession();
        session.setAttribute("SID", SID);
        session.setAttribute("MID", MID);
        RequestDispatcher rd = request.getRequestDispatcher("feedback.jsp"); //Prepares to forward
        rd.forward(request,response); //Gets forwarded. 
        return;

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession(); //Grabs sessions and gets the data that is required.
        String comment = request.getParameter("subComment");
        int mark = Integer.parseInt(request.getParameter("mark"));
        int SID = (int)session.getAttribute("SID");
        int MID = (int)session.getAttribute("MID");
        try 
        {
            DBAccess.giveFeedback(SID, MID, comment, mark); // Runs the function to write it to the DB
        }
        catch(Exception e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("teacherhub.jsp"); //Prepares to forward
        rd.forward(request,response); //Gets forwarded. 
        return;
    }

    private DBAccess DDB;
}