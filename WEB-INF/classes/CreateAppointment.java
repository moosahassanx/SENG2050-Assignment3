
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


@WebServlet(urlPatterns = { "/CreateAppointment" })
public class CreateAppointment extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        int teachID = (int)request.getAttribute("teachID");
        ArrayList<String> TN = (ArrayList<String>)request.getAttribute("teacherNames");
        String teachName = TN.get(teachID);
        session.setAttribute("teachName", teachName);
        RequestDispatcher rd = request.getRequestDispatcher("bookappointment.jsp");
        rd.forward(request,response);
        return;
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        User user = (User)request.getAttribute("user");
        int teachID = (int)request.getAttribute("teachID");
        String desc = request.getParameter("description");
        ArrayList<String> teacherNames = (ArrayList<String>)request.getAttribute("appointmentTeacher");
        String teachName = teacherNames.get(teachID);
        AppointmentsDB APB = new AppointmentsDB();
        APB.setUsername(user.getName());
        APB.setSession(session);
        try
        {
            APB.writeAppointments(desc, teachName, user);
        }
        catch (SQLException | NamingException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        session = APB.getSession();
        RequestDispatcher rd = request.getRequestDispatcher("appointments.jsp");
        rd.forward(request,response);
        return;
    }
}