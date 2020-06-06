/*
    Assignment 3: LoadAppointment.java
    Josh R(c3324541), Moosa H (), Keeylan H ()
    -----------------------------------------------------
    Purpose: This servlet will be used to load up the appointments. 
    This will get the appointments in the DB as well as the teachers. 
*/
//package WEB-INF.classes;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import userpackage.User;
import userpackage.AppointmentsDB;
import java.sql.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.servlet.annotation.WebServlet;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = { "/LoadAppointments" })
public class LoadAppointments extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession(); //Gets the session and required data. 
        User user = session.getAttribute("user");
        AppointmentsDB APB = new AppointmentsDB(); //Creates a new AppointmentsDB and sets attributes for them. 
        APB.setUsername(user.getName());
        APB.setSession(session);
        try 
        {
            APB.getAppointments(); //Runs the function to get all the appointments and write them to session attributes. 
        } 
        catch (SQLException | NamingException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        session = APB.getSession(); //Sets the session back. 
        RequestDispatcher rd = request.getRequestDispatcher("appointments.jsp"); //prepares the forward
        rd.forward(request,response); //Forwards
        return;
    }
}