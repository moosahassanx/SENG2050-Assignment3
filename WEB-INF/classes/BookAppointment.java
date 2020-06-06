/*
    Assignment 3: User.java
    Josh R(c3324541), Moosa H (), Keeylan H ()
    -----------------------------------------------------
    Purpose: this will be the main bean of the server. It holds all the user's
    information as well as connects to the DB. 
*/
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


@WebServlet(urlPatterns = { "/BookAppointment" })
public class BookAppointment extends HttpServlet 
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        User user = (User)request.getAttribute("user");
        int teachID = Integer.parseInt("teachID");
        session.setAttribute("teachID", teachID);
        /*try 
        {
            APB.writeAppointments();
        } 
        catch (SQLException | NamingException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } */
        //session = APB.getSession();
        RequestDispatcher rd = request.getRequestDispatcher("bookappointment.jsp");
        rd.forward(request,response);
        return;
    }
}