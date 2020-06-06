/*
    Assignment 3: User.java
    Josh R(c3324541), Moosa H (), Keeylan H ()
    -----------------------------------------------------
    Purpose: this will be the main bean of the server. It holds all the user's
    information as well as connects to the DB. 
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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        
        String buttonClicked = request.getParameter("button");
        
        System.out.println(buttonClicked);

        if(buttonClicked.equals("Login")){

            //Grabs the parameter of name passed through
            String loginID = request.getParameter("username");
            String password = request.getParameter("password");

            System.out.println("LoginID: " + loginID);
            System.out.println("Password" + password);
            User user = new User();
            try{
            // Checking if credentials exist

                int test = user.getStudentUser(loginID, password);
                // student login
                if(test == 1){

                    System.out.println("database = student");

                    user.setName(loginID);
                    user.setPassword(password);
                    user.setRole("Student");
                    user.setStudent(true);
                    
                    // running method
                    try {
                        joinGroup(user);
                    }
                    catch (SQLException | NamingException e) {
                        e.printStackTrace();
                    }                    

                    HttpSession session = request.getSession(); //gets the session
                    session.setAttribute("user", user); //sets the bean into the session

                    getMilestoneList(user, session);

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
            String loginID = request.getParameter("username2");
            String password = request.getParameter("password2");
        }

        System.out.println("outside");
    }

    public void getMilestoneList(User user, HttpSession session) throws SQLException, NamingException
    {
        try{
            InitialContext ctx = new InitialContext();
            // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
            Connection conn = ds.getConnection();
            // Selecting all data from the website_user table ** Note - only gives username/passwords
            PreparedStatement ps = null;
            String query = "SELECT * from milestones WHERE groupName = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, user.getGroup());
            ResultSet rs = ps.executeQuery();
            ArrayList<String> milestoneStudentNames = new ArrayList<String>();
            ArrayList<String> milestoneDescriptions = new ArrayList<String>();
            //ArrayList<Date> milestoneDates = new ArrayList<Date>();

            while(rs.next())
            {
                String milestoneStudent = rs.getString("username");
                String milestoneDescription = rs.getString("description");
                //Date date = rs.getDate("date");
                milestoneStudentNames.add(milestoneStudent);
                milestoneDescriptions.add(milestoneDescription);
                //milestoneDates.add(date);
            }
            session.setAttribute("milestoneStudentNames", milestoneStudentNames);
            session.setAttribute("milestoneDescriptions", milestoneDescriptions); 
            //session.setAttribute("milestoneDates", milestoneDates); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void joinGroup(User user) throws SQLException, NamingException{
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        PreparedStatement ps = null;
        String query = "SELECT * FROM user_groups WHERE username = ?";
        ps = conn.prepareStatement(query);
        ps.setString(1, user.getName());
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            String groupName = rs.getString("group_name");
            user.setGroup(groupName);
        }
        else{
            user.setGroup("");
        }
    }
}