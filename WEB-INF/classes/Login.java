//package WEB-INF.classes;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import userpackage.User;
import userpackage.Student;
import userpackage.Teacher;

import javax.servlet.annotation.WebServlet;
import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;

//This is just a template login, we will still need to change this a bit and still need to make it better
//T = Teacher, S = Student when logging in.

@WebServlet(urlPatterns = { "/login"})
public class Login extends HttpServlet 
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String loginID = request.getParameter("username"); //Grabs the parameter of name passed through
        String password = request.getParameter("password");
        //pass in user name and password up here somewhere? 
        if (loginID.charAt(0) == 'T')
        {
            //if Login already exists in the db
            User teacher = new Teacher();
            teacher.setName(loginID);
            teacher.setPassword(password);

            HttpSession session = request.getSession(); //gets the session
            session.setAttribute("teacher", teacher); //sets the bean into the session
            
            RequestDispatcher rd = request.getRequestDispatcher("hub.jsp"); //Redirects to the next page. 
            rd.forward(request, response);
        }
        else
        {
            User student = new Student();
            student.setName(loginID);
            student.setPassword(password);

            HttpSession session = request.getSession(); //gets the session
            session.setAttribute("student", student); //sets the bean into the session
            
            RequestDispatcher rd = request.getRequestDispatcher("minesweeperGame.jsp"); //Redirects to the next page. 
            rd.forward(request, response);
        }
    }
}