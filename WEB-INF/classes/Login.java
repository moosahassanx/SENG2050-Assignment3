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

import userpackage.User;

import javax.servlet.annotation.WebServlet;

import java.util.ArrayList;
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
        
        String buttonClicked = request.getParameter("button");
        System.out.println(buttonClicked);
        //User has done the login form



        if(buttonClicked.equals("Login")){

            String loginID = request.getParameter("username"); //Grabs the parameter of name passed through
            String password = request.getParameter("password");


            System.out.println("LoginID: " + loginID);
            System.out.println("Password" + password);
            User user = new User();
            try{
            // Checking if credentials exist

                int test = user.getStudentUser(loginID, password);

                if(test == 1){

                    System.out.println("database = student");

                    user.setName(loginID);
                    user.setPassword(password);
                    user.setRole("Student");
                    user.setGroup("YS Clan");

                    HttpSession session = request.getSession(); //gets the session
                    session.setAttribute("user", user); //sets the bean into the session

                    getMilestoneList(user, session);

                    RequestDispatcher rd = request.getRequestDispatcher("hub.jsp"); //Redirects to the next page. 
                    rd.forward(request, response);
                }
                else if(test == 2){

                    user.setName(loginID);
                    user.setPassword(password);
                    user.setRole("Teacher");

                    HttpSession session = request.getSession(); //gets the session
                    session.setAttribute("user", user); //sets the bean into the session
                    
                    RequestDispatcher rd = request.getRequestDispatcher("hub.jsp"); //Redirects to the next page. 
                    rd.forward(request, response);
                }
                else{
                    // Kinda cheating probs not the best way to do it
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Password is incorrect');");
                    out.println("location='Login.jsp';");
                    out.println("</script>");
                }
            }
            catch (Exception e){

            }

        }else{ // The user has filled out the registration form
            String loginID = request.getParameter("username2");
            String password = request.getParameter("password2");








        }

        System.out.println("outside");
    }

    public void getMilestoneList(User user, HttpSession session) throws SQLException, NamingException
    {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        PreparedStatement ps = null;
        String query = "SELECT * from discussions WHERE groupName = ?";
        ps = conn.prepareStatement(query);
        ps.setString(1, user.getGroup());
        ResultSet rs = ps.executeQuery(query);
        ArrayList<String> milestoneStudentNames = new ArrayList<String>();
        ArrayList<String> milestoneDescriptions = new ArrayList<String>();
        ArrayList<Date> milestoneDates = new ArrayList<Date>();

        while(rs.next())
        {
            String milestoneStudent = rs.getString("username");
            String milestoneDescription = rs.getString("descriptions");
            Date date = rs.getDate("date");
            milestoneStudentNames.add(milestoneStudent);
            milestoneDescriptions.add(milestoneDescription);
            milestoneDates.add(date);
        }
        session.setAttribute("milestoneStudentNames", milestoneStudentNames);
        session.setAttribute("milestoneDescriptions", milestoneDescriptions); 
        session.setAttribute("milestoneDates", milestoneDates); 
    }
}