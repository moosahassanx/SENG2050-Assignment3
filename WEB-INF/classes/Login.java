/*
    Assignment 3: Login.java
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
    -----------------------------------------------------
    Purpose: This servlet is responsible for dealing with the login of a user or a teacher.
    This will ensure the correct user is taken to the correct website. 
*/
//package WEB-INF.classes;

import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;

import userpackage.AppointmentsDB;
import userpackage.DBAccess;
import userpackage.User;

import javax.servlet.annotation.WebServlet;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;


// Need to hash password - & dont pull password from the DB, just check its correct with query
@WebServlet(urlPatterns = { "/login"})
public class Login extends HttpServlet
{


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        HttpSession session = request.getSession();
        session.invalidate();

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String buttonClicked = request.getParameter("button");

        if(buttonClicked.equals("Login")){

            //Grabs the parameter of name passed through
            String loginID = request.getParameter("username");
            String password = request.getParameter("password");

            User user = new User();
            try{
            // Checking if credentials exist

                int test = user.getStudentUser(loginID, password);
                // student login
                if(test == 1){

                    user.setName(loginID);
                    user.setPassword(password);
                    user.setRole("Student");
                    user.setStudent(true);

                    HttpSession session = request.getSession(); //gets the session
                    session.setAttribute("user", user); //sets the bean into the session

                    // running method
                    try {
                        DBA.loginGroup(session, user.getGroup(), user);
                    }
                    catch (SQLException | NamingException e) {
                        e.printStackTrace();
                    }

                    DBA.getMilestoneList(user, session);
                    DBA.getSubmissions(session, user.getGroup());

                    RequestDispatcher rd = request.getRequestDispatcher("hub.jsp"); //Redirects to the next page.
                    rd.forward(request, response);
                }

                // teacher login
                else if(test == 2){
                    user.setName(loginID);
                    user.setPassword(password);
                    user.setRole("Teacher");
                    user.setStudent(false);

                    HttpSession session = request.getSession(); //gets the session
                    session.setAttribute("user", user); //sets the bean into the session
                    
                    AppointmentsDB ADB = new AppointmentsDB();
                    ADB.setUsername(loginID);
                    ADB.setSession(request.getSession());
                    ADB.getTeacherAppointments();   

                    RequestDispatcher rd = request.getRequestDispatcher("teacherhub.jsp"); //Redirects to the next page.
                    rd.forward(request, response);
                }
                else{
                    // Kinda cheating probs not the best way to do it
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Password is incorrect');");
                    out.println("location='Login.jsp';");
                    out.println("</script>");
                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp"); //Redirects to the next page.
                    rd.forward(request, response);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }else{ // The user has filled out the registration form
            try{
           

            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            String fName = request.getParameter("FirstName");
            String lName = request.getParameter("LastName");
            String phoneNoStr = request.getParameter("PhoneNo");
            String studentIdStr = request.getParameter("studentID");

            // Converting phoneNostr and StudentIDSTR to integers
            int phoneNo = Integer.parseInt(phoneNoStr);
            int studentId = Integer.parseInt(studentIdStr);

            // implementing Student User
            String studentName = fName + lName;
            User user = new User(studentName, phoneNo, studentId, "Student", true);
            
            user.registerStudent(userName, password, fName, lName, phoneNo, studentId);

            // HttpSession session = request.getSession(); //gets the session
            // session.setAttribute("user", user); //sets the bean into the session

            RequestDispatcher rd = request.getRequestDispatcher("index.jsp"); //Redirects to the next page.
            rd.forward(request, response);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private DBAccess DBA;
}