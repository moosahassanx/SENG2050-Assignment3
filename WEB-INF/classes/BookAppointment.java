/*
    Assignment 3:BookAppointment.java
    Josh R(c3324541), Moosa H (), Keeylan H ()
    -----------------------------------------------------
    Purpose: This Servlet is used to hold data from a link that was clicked on from Appointments to allow
    Them to be taken to the correct booking page, and it holds the ID of the page to ensure the correct 
    teacher ID is corrected. 
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
    //Will get the correct ID from the link clicked then will go to the next page. 
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        User user = (User)request.getAttribute("user");
        int teachID = Integer.parseInt("teachID");
        session.setAttribute("teachID", teachID);
        RequestDispatcher rd = request.getRequestDispatcher("bookappointment.jsp");
        rd.forward(request,response);
        return;
    }
}