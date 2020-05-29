import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import userpackage.User;
import javax.servlet.annotation.WebServlet;


@WebServlet("/upload")
//@MultipartConfig
public class UploadServlet extends HttpServlet {
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fileDescription = request.getParameter("description");
        Part filePart = request.getPart("file");
        String fileName = getSubmittedFileName(filePart); 

        // Should turn file into byte stream, upload onto db
        InputStream fileBytes = filePart.getInputStream();
        byte[] bytes = fileBytes.readAllBytes(); 
    }




    // Legit copied straight from stack, we aren't on servlet 3.1 so getting the file name is iffy, unsure if it workds
    // but it compiles
    private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); 
            }
        }
        return null;
    }

}