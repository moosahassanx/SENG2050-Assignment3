
import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import userpackage.File;
import userpackage.User;


import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buttonPressed = request.getParameter("list");
            if(buttonPressed.equals("list")){
                
                // Redundant make a random file here to access the file methods - Could maybe use an interface instead?
                File uploadFile = new File();
                List<File> file = uploadFile.getAllFiles();

                HttpSession session = request.getSession();
                session.setAttribute("list",file);

                RequestDispatcher rd = request.getRequestDispatcher("files.jsp");
                rd.forward(request,response);

            }
            else{
            String userUploaded = request.getParameter("userUploaded");
            String groupName = request.getParameter("userGroup");
            String fileDescription = request.getParameter("description");
            Part filePart = request.getPart("file");
            String fileName = filePart.getSubmittedFileName();                          

            // Should turn file into byte stream, upload onto db
            InputStream fileBytes = filePart.getInputStream();
            byte[] bytes = fileBytes.readAllBytes(); 

            // Creating the file object
            File uploadFile = new File();

            // Setting files parameters, loaded constructor wouldnt work?
            uploadFile.setUserUploaded(userUploaded);
            uploadFile.setDescription(fileDescription);
            uploadFile.setFileName(fileName);
            uploadFile.setFileData(bytes);
            // Sending file to upload method

                try{

                    uploadFile.uploadFile(bytes,userUploaded, fileDescription, fileName);
                    RequestDispatcher rd = request.getRequestDispatcher("files.jsp"); 
                    rd.forward(request, response);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
    }
}