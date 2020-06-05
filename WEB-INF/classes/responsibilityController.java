import java.io.*;
import java.sql.Blob;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import userpackage.File;
import userpackage.User;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = { "/responsibility"})

public class responsibilityController extends HttpServlet {





    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String groupName = request.getParameter("id");

        if(groupName != null){
            try{
                List<String> list = User.getUserList(groupName);
                HttpSession session = request.getSession();
                session.setAttribute("groupList",list);

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