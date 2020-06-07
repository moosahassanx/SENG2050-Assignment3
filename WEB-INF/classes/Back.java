/*
    Assignment 3: Back.java
    Josh R(c3324541), Moosa H (c3324541), Keeylan H ()
    -----------------------------------------------------
    Purpose: When going back from pages, it will check if the user is a teacher or a 
    student, to ensure that they are taken to the right page.
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

@WebServlet(urlPatterns = { "/back" })
public class Back extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // retrieving data from previous jsp user inputs
        HttpSession session = request.getSession();
        User theUser =((User)session.getAttribute("user"));

        // the user is a teacher
        if(theUser.isStudent() == false){
            // redirect user to hub.jsp
            RequestDispatcher rd = request.getRequestDispatcher("teacherhub.jsp");
            rd.forward(request,response);
            return;
        }

        // the user is a student
        else{
            // redirect user - teacherhub.jsp
            RequestDispatcher rd = request.getRequestDispatcher("hub.jsp");
            rd.forward(request,response);
            return;
        }
    }
}