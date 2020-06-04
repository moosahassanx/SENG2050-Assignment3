
package userpackage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import userpackage.User;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.*;


public class DiscussionsDB {

    //private String title;
    //private 

    public static void getDiscussions(HttpSession session) throws SQLException, NamingException
    {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        String query = "SELECT * from discussions";
        ResultSet rs = stmt.executeQuery(query);
        ArrayList<String> discussionTitles = new ArrayList<String>();
        ArrayList<String> discussionID = new ArrayList<String>();

        while(rs.next())
        {
            String discussID = rs.getString("discussionID");
            String discussionTitle = rs.getString("title");
            discussionTitles.add(discussionTitle);
            discussionID.add(discussID);
        }
        session.setAttribute("discussionTitles", discussionTitles);
        session.setAttribute("discussionID", discussionID);
        conn.close();
    }

    public static void getDiscussionsThread(HttpSession session, int id) throws SQLException, NamingException
    {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        PreparedStatement ps = null;
        String query = "SELECT * from discussions WHERE discussionID = ?";
        ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String discussionTitle = rs.getString("title");
        String discussionDesc = rs.getString("description");
        String discussionUsername = rs.getString("username");

        query = "Select * from discussionsThread WHERE discussionID = ?";
        ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        ArrayList<String> threadIDs = new ArrayList<String>();
        ArrayList<String> threadUsernames = new ArrayList<String>();
        ArrayList<String> threadDesc = new ArrayList<String>();

        while(rs.next())
        {
            threadIDs.add(rs.getString("threadID"));
            threadUsernames.add(rs.getString("username"));
            threadDesc.add(rs.getString("description"));
        }

        session.setAttribute("discussionUsername", discussionUsername);
        session.setAttribute("discussionTitle", discussionTitle);
        session.setAttribute("discussionDesc", discussionDesc);
        session.setAttribute("threadIDs", threadIDs);
        session.setAttribute("threadUsernames", threadUsernames);
        session.setAttribute("threadDesc",threadDesc);
        conn.close();
    }

    public static void createDiscussions(HttpSession session, String title, String desc, User user) throws SQLException, NamingException
    {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        //Grab from the Database first and check it to see if something with that name ALREADY exists FROM that user!!
        String query = "INSERT INTO discussions VALUES(?,?,?)";
        PreparedStatement ps = null;
        ps = conn.prepareStatement(query);
        ps.setString(1,title);
        ps.setString(2, user.getName());
        ps.setString(3,desc);
        ps.executeUpdate();

        conn.close();
    }

    public static void createDiscussionsThread(HttpSession session, String desc, User user, int id) throws SQLException, NamingException
    {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        //Grab from the Database first and check it to see if something with that name ALREADY exists FROM that user!!
        String query = "INSERT INTO discussionsThread VALUES(?,?,?)";
        PreparedStatement ps = null;
        ps = conn.prepareStatement(query);
        ps.setInt(1,id);
        ps.setString(2, user.getName());
        ps.setString(3,desc);
        ps.executeUpdate();

        conn.close();
    }

}