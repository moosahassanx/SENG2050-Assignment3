
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

    public static void newGroup(HttpSession session, String groupName, String subject, User user) throws SQLException, NamingException {
        // setting up connection
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();

        // database inserting
        String query = "INSERT INTO groups VALUES(?)";
        PreparedStatement ps = null;
        ps = conn.prepareStatement(query);
        ps.setString(1,groupName);
        ps.executeUpdate();

        // closing connection
        conn.close();
    }

    // method to get all the group names
    public static void getGroups(HttpSession session) throws SQLException, NamingException {
        // connection
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();

        // get data from groups table in db
        String query = "SELECT * FROM groups";
        ResultSet rs = stmt.executeQuery(query);
        ArrayList<String> GroupNames = new ArrayList<String>();

        // build list of group names
        while(rs.next()) {
            String GroupName = rs.getString("group_name");
            GroupNames.add(GroupName);
        }

        // displaying group names in terminal
        for(int i = 0; i < GroupNames.size(); i++){
            System.out.println("Group Name " + i + ": " + GroupNames.get(i));
        }

        // closing
        session.setAttribute("GroupNames", GroupNames);
        conn.close();
    }

    public static void showJoinedGroup(HttpSession session, String groupName, User user) throws SQLException, NamingException {
        System.out.println("theUser.hasGroup() = true");

        // setting up connection
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();

        // get data from groups table in db
        String query = "SELECT * FROM user_groups WHERE username=";
        query += "'" + user.getName() + "'";
        System.out.println("Querying: " + query);
        ResultSet rs = stmt.executeQuery(query);

        // build list of group names
        if(rs.next()) {
            String GroupName = rs.getString("group_name");
            System.out.println("SQL VERSION:\t User " + user.getName() + " has already joined the group: " + GroupName);
        }

        // closing connection
        conn.close();
    }

    public static void joinGroup(HttpSession session, String groupName, User user) throws SQLException, NamingException {
        // setting up connection
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();

        // database inserting
        String query = "INSERT INTO user_groups VALUES(?, ?)";
        PreparedStatement ps = null;
        ps = conn.prepareStatement(query);
        ps.setString(1, user.getName());
        ps.setString(2, groupName);
        ps.executeUpdate();

        user.setGroup(groupName);

        System.out.println("User " + user.getName() + " has joined the group: " + user.getGroup());

        // closing connection
        conn.close();
    }

    public static void groupDetails(HttpSession session, String groupName) throws SQLException, NamingException {
        // connection
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();

        // get data from groups table in db
        String query = "SELECT * FROM user_groups WHERE group_name = ";
        query += "'" + groupName + "'";
        ResultSet rs = stmt.executeQuery(query);
        ArrayList<String> groupMembers = new ArrayList<String>();

        // build list of group members
        while(rs.next()) {
            String groupMember = rs.getString("username");
            groupMembers.add(groupMember);
        }

        for(int i = 0; i < groupMembers.size(); i++){
            System.out.println("Group member " + i + ": " + groupMembers.get(i));
        }

        // closing
        session.setAttribute("groupName", groupName);
        session.setAttribute("groupMembers", groupMembers);
        conn.close();
    }
}