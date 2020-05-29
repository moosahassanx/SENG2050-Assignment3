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
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); //Might work - found fix on stack but may need @multipartconfig

        // Should turn file into byte stream, upload onto db
        InputStream fileBytes = filePart.getInputStream();
        byte[] bytes = fileBytes.readAllBytes(); 
    }







}