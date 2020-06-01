
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FileDAO{

    private String jdbcURL;
    private String jdbcUserName;
    private String jdbcPassword;


    public FileDAO(){
        
        //InitialContext ctx = new InitialContext();
        //DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        //Connection conn = ds.getConnection();

    }


    public void closeConnection() throws SQLException{
        
    }

    public boolean uploadFile(){

        return false;
    }



























}