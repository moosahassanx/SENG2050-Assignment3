/*
    Assignment 3: AddMilestones.jsp
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
    -----------------------------------------------------
    Purpose: This is the bean that will hold all the data relating to any files that are uploaded
    to the server. 
*/

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
    private String version;

    // Default constructor
    public File() {

    }

    public File(String userUploaded, String fileName, String description, byte[] fileData) {
        this.userUploaded = userUploaded;
        this.fileName = fileName;
        this.description = description;
        this.fileData = fileData;
    }

    public File(String userUploaded, String fileName, String description, byte[] fileData, String version) {
        this.userUploaded = userUploaded;
        this.fileName = fileName;
        this.description = description;
        this.fileData = fileData;
        this.version = version;
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

    public void setVersion(String version){
        this.version = version;
    }

    public String getVersion(){
        return version;
    }

    //This function will place the file that has been 'uploaded' into the DB. 
    public boolean uploadFile(byte[] bytes, String uploadedName, String description, String fileName, String groupName) throws NamingException, SQLException {

        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        PreparedStatement ps = null;

            // Inserts the file in the main files table
            String query = "INSERT into files VALUES (?,?,?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setObject(1,bytes);
            ps.setString(2, fileName );
            ps.setString(3, uploadedName);
            ps.setString(4, description);
            ps.setString(5, groupName);
            ps.executeUpdate();

            // Inserts file as the first version in versionfile table
            query = "INSERT INTO versionFiles VALUES (?,?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, fileName);
            ps.setObject(2, bytes);
            ps.setString(3, description);
            ps.setString(4, uploadedName);
            ps.executeUpdate();
            conn.close();
            return true;
    }

    //This function will return all the files currently stored within the DB
    public List<File> getAllFiles(String groupName){

        // Storing all the files in an arraylist from the database
        List<File> list = new ArrayList<File>();

            try{
                InitialContext ctx = new InitialContext();
                DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
                Connection conn = ds.getConnection();
                PreparedStatement ps = conn.prepareStatement("Select * from files where groupName = ?");
                ps.setString(1, groupName);
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
            conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            
        return list;
    }

    //This function will allow the user to download the file that they have selected on the files page. 
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
            conn.close();
        return data;
    }


    public boolean checkDB(String fileName, byte[] bytes) throws SQLException, NamingException{

        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select * from files where file_name = ? AND binary_file = ?");

        ps.setString(1, fileName);
        ps.setBytes(2, bytes);

        ResultSet rs = ps.executeQuery();

        // File exists already in the db & its the same fileName and bytes
        if(rs.next()){
            conn.close();
            return true;
        }
        else{
            conn.close();
            return false;
        }
    }


    public static void addVersionFile(byte[] bytes, String uploadedName, String description, String fileName, String groupName) throws SQLException, NamingException{
        
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT into versionFiles VALUES (?,?,?,?)");

        ps.setString(1, fileName);
        ps.setBytes(2, bytes);
        ps.setString(3, description);
        ps.setString(4, uploadedName);
        ps.executeUpdate();

        conn.close();

    }




    public static List<File> getVersionFile(String fileName) throws SQLException, NamingException{

        List<File> versionList = new ArrayList<File>();

        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select * from versionfiles where file_name = ? ORDER BY file_version"); // might have to check by group as well
        ps.setString(1, fileName);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            String userUploaded = rs.getString("uploadedName");
            String description = rs.getString("file_desc");
            byte[] fileData = rs.getBytes("binary_file");
            String version = rs.getString("file_version");
            File file = new File(userUploaded,fileName,description, fileData, version);

            versionList.add(file);
            
        }
        conn.close();
        return versionList;
    }

}