import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import userpackage.User;
import userpackage.File;

import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;

@WebServlet("/upload")
@MultipartConfig(maxFileSize = 10485760)
public class UploadServlet extends HttpServlet {
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buttonPressed = request.getParameter("list");
        System.out.println(buttonPressed);
        if(buttonPressed.equals("list")){
            HttpSession session = request.getSession();

            User user = (User) session.getAttribute("user");
            File uploadFile = new File();

            List<File> file = uploadFile.getAllFiles(uploadFile, user.getGroup());

            session.setAttribute("list", file);

            RequestDispatcher rd = request.getRequestDispatcher("files.jsp"); 
            rd.forward(request, response);

        }else{

        String fileDescription = request.getParameter("description");
        String userUploaded = request.getParameter("userUploaded");
        String groupName = request.getParameter("userGroup");
        Part filePart = request.getPart("myfile");
        String fileName = filePart.getSubmittedFileName();                          
        InputStream fileBytes = filePart.getInputStream();
        byte[] bytes = fileBytes.readAllBytes(); 

        System.out.println(fileDescription);
        System.out.println(groupName);
        System.out.println(userUploaded);
        System.out.println(fileName);

        // Creating the file object
        File uploadFile = new File(); 
        // Setting files parameters, loaded constructor wouldnt work?
        uploadFile.setUserUploaded(userUploaded);
        uploadFile.setDescription(fileDescription);
        uploadFile.setFileName(fileName);
        uploadFile.setFileData(bytes);
        // Sending file to upload method
            try{

                uploadFile.uploadFile(bytes,userUploaded, fileDescription, fileName, groupName);


            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Handle the download of files

    }





}