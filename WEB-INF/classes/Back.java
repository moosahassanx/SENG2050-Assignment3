//package WEB-INF.classes;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import userpackage.User;
import userpackage.DiscussionsDB;
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
        System.out.println("\n*****Back Servlet Initialized.*****");

        // retrieving data from previous jsp user inputs
        HttpSession session = request.getSession();
        User theUser =((User)session.getAttribute("user"));

        System.out.println("User " + theUser.getName() + " is student: " + theUser.isStudent());

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
            RequestDispatcher rd = request.getRequestDispatcher("teacherhub.jsp");
            rd.forward(request,response);
            return;
        }
    }
}