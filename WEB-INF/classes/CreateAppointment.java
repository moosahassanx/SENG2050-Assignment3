
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import userpackage.User;
import userpackage.AppointmentsDB;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.servlet.annotation.WebServlet;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;


@WebServlet(urlPatterns = { "/CreateAppointment" })
public class CreateAppointment extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        int teachID = Integer.parseInt(request.getParameter("teachID"));
        ArrayList<String> TN = (ArrayList<String>)session.getAttribute("teacherNames");
        String teachName = TN.get(teachID-1);
        session.setAttribute("teachName", teachName);
        RequestDispatcher rd = request.getRequestDispatcher("bookappointment.jsp");
        rd.forward(request,response);
        return;
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String desc = request.getParameter("description");
        String teachName = (String)session.getAttribute("teachName");
        String dateBackwards = request.getParameter("date");
        String[] dateSplit = dateBackwards.split("-");

        String dateAt = dateSplit[2] + dateSplit[1] + dateSplit[0];
        String timeAt = request.getParameter("time");
        AppointmentsDB APB = new AppointmentsDB();
        APB.setUsername(user.getName());
        APB.setSession(session);
        try
        {
            APB.writeAppointments(desc, teachName, user, dateAt, timeAt);
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