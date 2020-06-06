/*
    Assignment 3: UploadServlet.java
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
    -----------------------------------------------------
    Purpose: This servlet is used for the file upload and download. 
    It ensures that the file is written to the DB or taken from the DB. 
*/
import java.io.*;
import java.sql.Blob;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import userpackage.File;
import userpackage.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;

@WebServlet(urlPatterns = { "/upload"})
@MultipartConfig(maxFileSize = 10485760)
public class UploadServlet extends HttpServlet {

    private final int BUFFER_SIZE = 4096; // Buffer for downloading file

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buttonPressed = request.getParameter("list");
        String userUploaded = request.getParameter("userUploaded");
        String groupName = request.getParameter("userGroup");
        String fileDescription = request.getParameter("description");
        
            if(buttonPressed.equals("list")){
                
                // Redundant make a random file here to access the file methods - Could maybe use an interface instead?
                File uploadFile = new File();
                List<File> file = uploadFile.getAllFiles(groupName);

                HttpSession session = request.getSession();
                session.setAttribute("list",file);
                
                RequestDispatcher rd = request.getRequestDispatcher("files.jsp");
                rd.forward(request,response);
            }
            else if(buttonPressed.equals("Versions")){

                String fileName = request.getParameter("fileName");
                
                HttpSession session = request.getSession();
                session.setAttribute("fileName", fileName);

                try{
                    List<File> versionList = File.getVersionFile(fileName);
                    session.setAttribute("versionList",versionList);

                    RequestDispatcher rd = request.getRequestDispatcher("fileVersion.jsp");
                    rd.forward(request,response);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            else{
                
                Part filePart = request.getPart("myfile");
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
                uploadFile.addGroupName(groupName);
                // Sending file to upload method

                try{
                    // Checking if file is already in the DB
                    if(uploadFile.checkDB(fileName, bytes)){

                        // File uploaded is already there - Add to the versionFile table instead for version
                        File.addVersionFile(bytes, userUploaded, fileDescription, fileName, groupName);

                        RequestDispatcher rd = request.getRequestDispatcher("files.jsp"); 
                        rd.forward(request, response);
                    }
                    else{
                    uploadFile.uploadFile(bytes,userUploaded, fileDescription, fileName, groupName);
                    RequestDispatcher rd = request.getRequestDispatcher("files.jsp"); 
                    rd.forward(request, response);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Need to make static method
        File downloadFile = new File();
        String fileName = request.getParameter("id");

        try{
            Blob fileData = downloadFile.downloadFile(fileName);
            InputStream inputStream = fileData.getBinaryStream();

            response.setContentType("application/force-download");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Disposition","attachment; filename=\"" + fileName + "\"");

            OutputStream os = response.getOutputStream();
            byte [] buffer = new byte[BUFFER_SIZE];
            int bytesread = -1;
            
            // Outputs the bytes until inputstream sends back -1 indicating theres no more
            while((bytesread = inputStream.read(buffer)) != -1){
                os.write(buffer, 0, bytesread);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}