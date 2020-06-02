import java.io.*;


import javax.servlet.*;
import javax.servlet.http.*;

import userpackage.User;
import userpackage.File;

import javax.servlet.annotation.WebServlet;


@WebServlet("/upload")
//@MultipartConfig
public class UploadServlet extends HttpServlet {
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Extracting file data from the submitted form / file picked
        String fileDescription = request.getParameter("description");
        String groupName = request.getParameter("groupName");
        String userUploaded = request.getParameter("userUploaded");
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();                          
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

            uploadFile.uploadFile(uploadFile);


        }catch (Exception e){
            e.printStackTrace();
        }
        
    }




}