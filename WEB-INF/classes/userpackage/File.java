package userpackage;


import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class File {

    private String userUploaded;
    private String fileName;
    private String description;
    private byte[] fileData;
    private String groupName;

    // Default constructor
    public File() {

    }

    public File(String userUploaded, String fileName, String description, byte[] fileData) {
        this.userUploaded = userUploaded;
        this.fileName = fileName;
        this.description = description;
        this.fileData = fileData;
    }

    public String getGroupName(){
        return groupName;
    }

    public void addGroupName(String groupName){
        this.groupName = groupName;
    }
    public String getUserUploaded() {
        return userUploaded;
    }

    public String getFileName() {
        return fileName;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setUserUploaded(String userUploaded) {
        this.userUploaded = userUploaded;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public boolean uploadFile(byte[] bytes, String uploadedName, String description, String fileName, String groupName) throws NamingException, SQLException {

        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        PreparedStatement ps = null;

            String query = "INSERT into files VALUES (?,?,?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setObject(1,bytes);
            ps.setString(2, fileName );
            ps.setString(3, uploadedName);
            ps.setString(4, description);
            ps.setString(5, groupName);
            ps.executeUpdate();

        return true;
    }

    public List<File> getAllFiles(){

        // Storing all the files in an arraylist from the database
        List<File> list = new ArrayList<File>();

            try{
                InitialContext ctx = new InitialContext();
                DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
                Connection conn = ds.getConnection();
                PreparedStatement ps = conn.prepareStatement("Select * from files where group_name = ?");
                ResultSet rs = ps.executeQuery();

                // Running through the files table and populating the list
                while(rs.next()){
                String fileName = rs.getString("file_name");
                String userUploaded = rs.getString("uploaded_name");
                String description = rs.getString("file_description");
                byte[] fileData = rs.getBytes("binary_file");
                File file = new File(userUploaded, fileName, description,fileData);
                list.add(file);
                }
            
            }catch(Exception e){
                e.printStackTrace();
            }
        return list;
    }


    public Blob downloadFile(String fileName) throws SQLException, NamingException {
        Blob data = null;
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select * from files where file_name = ?");

        ps.setString(1, fileName);
        ResultSet rs = ps.executeQuery();
            while(rs.next()){
                data = rs.getBlob("binary_file");
            }      
        return data;
    }

}