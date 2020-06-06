/*
    Assignment 3: User.java
    Josh R(c3324541), Moosa H (), Keeylan H ()
    -----------------------------------------------------
    Purpose: this will be the main bean of the server. It holds all the user's
    information as well as connects to the DB. 
*/

import java.io.*;
import java.sql.Blob;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

import userpackage.File;
import userpackage.User;
import userpackage.responsibility;
import userpackage.DBAccess;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = { "/responsibility"})
public class responsibilityController extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        String userName = request.getParameter("userName"); 
        String description = request.getParameter("description");
        String dateComplete = request.getParameter("date");
        String userGroup = request.getParameter("userGroup");

        // Setting the insert date
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("YYYY-dd-MM");
        String insertDate = df.format(date);

            try{
                HttpSession session = request.getSession();

                // Adding data to DB
                DBAccess.addResponsibility(userName, description, insertDate, dateComplete, userGroup);

                // Populating list
                List<responsibility> responsList = DBAccess.responsibilityList(userGroup);
                session.setAttribute("responseList", responsList);

                RequestDispatcher rd = request.getRequestDispatcher("responsibility.jsp"); 
                rd.forward(request, response);
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String groupName = request.getParameter("id");

        if(groupName != null){
            try{
                List<String> list = User.getUserList(groupName);

                HttpSession session = request.getSession();
                session.setAttribute("groupList",list);


                List<responsibility> responsList = DBAccess.responsibilityList(groupName);
                session.setAttribute("responseList", responsList);

                RequestDispatcher rd = request.getRequestDispatcher("responsibility.jsp"); 
                rd.forward(request, response);

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            RequestDispatcher rd = request.getRequestDispatcher("responsibility.jsp"); 
            rd.forward(request, response);
        }
    }
}