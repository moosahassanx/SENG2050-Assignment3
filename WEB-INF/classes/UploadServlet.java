
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

<<<<<<< HEAD
            //User user = (User) session.getAttribute("user");
            File uploadFile = new File();

            List<File> file = uploadFile.getAllFiles(uploadFile);
=======

>>>>>>> 0e349f706dad87619926e02c2cbaaa923e98730a







    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userUploaded = request.getParameter("");
        String fileDescription = request.getParameter("description");
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();                          

        // Should turn file into byte stream, upload onto db
        InputStream fileBytes = filePart.getInputStream();
        byte[] bytes = fileBytes.readAllBytes(); 

<<<<<<< HEAD
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

                uploadFile.uploadFile(bytes,userUploaded, fileDescription, fileName);
                RequestDispatcher rd = request.getRequestDispatcher("files.jsp"); 
                rd.forward(request, response);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
=======
>>>>>>> 0e349f706dad87619926e02c2cbaaa923e98730a



    }



}