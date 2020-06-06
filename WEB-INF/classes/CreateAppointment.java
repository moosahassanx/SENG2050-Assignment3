/*
    Assignment 3: CreateAppointment.java
    Josh R(c3324541), Moosa H (), Keeylan H ()
    -----------------------------------------------------
    Purpose: This servlet will call a function to create a new appointment and it will also get the correct teacher
    name when it comes to making an appointment. 
*/
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import userpackage.User;
import userpackage.AppointmentsDB;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.servlet.annotation.WebServlet;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;


@WebServlet(urlPatterns = { "/CreateAppointment" })
public class CreateAppointment extends HttpServlet 
{
    //This function is used to grab the correct user Idea when a link is clicked on the page.
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession(); //Gets the current session. 
        int teachID = Integer.parseInt(request.getParameter("teachID"));
        ArrayList<String> TN = (ArrayList<String>)session.getAttribute("teacherNames");
        String teachName = TN.get(teachID-1);
        session.setAttribute("teachName", teachName); //Writes to the session to be used at a later point. 
        RequestDispatcher rd = request.getRequestDispatcher("bookappointment.jsp"); //Gets ready to forward to a new page. 
        rd.forward(request,response);
        return;
    }
    //This function will be used to gather the correct data then write it to an appointment in the DB. 
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession(); //Gets the current session. 
        User user = (User)session.getAttribute("user"); //Gathers the data from servlet and the request. 
        String desc = request.getParameter("description");
        String teachName = (String)session.getAttribute("teachName");
        String dateBackwards = request.getParameter("date"); //Gets the date
        String[] dateSplit = dateBackwards.split("-"); //Splits the date.

        String dateAt = dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0]; //Flips it so it is in our format
        String timeAt = request.getParameter("time");
        AppointmentsDB APB = new AppointmentsDB(); //New appointment is created. 
        APB.setUsername(user.getName()); //has the respective data set. 
        APB.setSession(session);
        try
        {
            APB.writeAppointments(desc, teachName, user, dateAt, timeAt); //Will write the appointment to the database. 
        }
        catch (SQLException | NamingException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        session = APB.getSession(); //Makes the session equal to the session changes (not sure if this is needed.)
        RequestDispatcher rd = request.getRequestDispatcher("appointments.jsp"); //Goes forward to the next page. 
        rd.forward(request,response);
        return;
    }
}