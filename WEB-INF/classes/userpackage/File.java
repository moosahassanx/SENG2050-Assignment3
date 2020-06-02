package userpackage;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public boolean uploadFile(File file) throws NamingException, SQLException {


        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = (Statement) conn.createStatement();
        PreparedStatement ps = null;









        return false;
    }






}