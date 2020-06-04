
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import userpackage.*;


import userpackage.User;
import javax.servlet.annotation.WebServlet;


@WebServlet("/upload")
//@MultipartConfig
public class UploadServlet extends HttpServlet {
    //private FileDAO fileDao;                 



  //  public void init(){
    //    fileDao = new FileDAO();    //Opening a Database object conneection
    //}









    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userUploaded = request.getParameter("");
        String fileDescription = request.getParameter("description");
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();                          

        // Should turn file into byte stream, upload onto db
        InputStream fileBytes = filePart.getInputStream();
        byte[] bytes = fileBytes.readAllBytes(); 




    }



}