//package WEB-INF.classes;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import userpackage.User;

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
        
        String buttonClicked = request.getParameter("button");
        System.out.println(buttonClicked);
        //User has done the login form



        if(buttonClicked.equals("Login")){

            String loginID = request.getParameter("username"); //Grabs the parameter of name passed through
            String password = request.getParameter("password");


            System.out.println(loginID);
            System.out.println(password);
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

}