/*
    Assignment 3: responsibilityController.java
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
    -----------------------------------------------------
    Purpose: This servlet will be used to complete everything relating
    to the responsibilities on the server. 
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

        // GroupName is the group when originally going into responsibilities.jsp - After clicking the completion link it changes to either yes or no
        // Doing this to save making an extra servlet just for another doGet method
        String groupName = request.getParameter("id");
        System.out.println(groupName);

        if(groupName != null && !"No".equals(groupName)){
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
        else if(groupName.equals("No")){

            try{
                // User has clicked to mark they have finished a responsibility
                String responsName = request.getParameter("name");
                String responseID = request.getParameter("name2");
                String group = request.getParameter("name3");

                // Sets the responsibility to completed
                DBAccess.setResponseCompletion(responseID);

                // Removing old response list
                HttpSession session = request.getSession();
                session.removeAttribute("responseList");

                // Re-populating the response list
                List<responsibility> responsList = DBAccess.responsibilityList(group);
                session.setAttribute("responseList", responsList);

                RequestDispatcher rd = request.getRequestDispatcher("responsibility.jsp"); 
                rd.forward(request, response); 
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            RequestDispatcher rd = request.getRequestDispatcher("responsibility.jsp"); 
            rd.forward(request, response);
        }
    }
}