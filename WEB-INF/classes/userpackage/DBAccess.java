/*
    Assignment 3: DBAccess.java
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
    -----------------------------------------------------
    Purpose: This file will be used to access the Database at any time
    it is required. There are multiple functions in here that are used in many
    servlets to receive data from database. 
*/
package userpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import userpackage.User;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.*;


public class DBAccess {

    //This function will be used to grab all discussions that are currently within the Database. 
    public static void getDiscussions(HttpSession session) throws SQLException, NamingException
    {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        String query = "SELECT * from discussions";
        ResultSet rs = stmt.executeQuery(query); //Returns a result set from the executed query
        ArrayList<String> discussionTitles = new ArrayList<String>(); //Creates new Array lists in order to store the information that will be obtained from database. 
        ArrayList<String> discussionID = new ArrayList<String>();

        while(rs.next())
        {
            String discussID = rs.getString("discussionID"); //Runs through the results, taking all the IDs and titles and placing them into the Array Lists.
            String discussionTitle = rs.getString("title");
            discussionTitles.add(discussionTitle);
            discussionID.add(discussID);
        }
        session.setAttribute("discussionTitles", discussionTitles); //Array lists are added to the session and the connection is closed.
        session.setAttribute("discussionID", discussionID);
        conn.close();
    }

    //When a discussion is chosen, this function will load the respective thread of that function. 
    public static void getDiscussionsThread(HttpSession session, int id) throws SQLException, NamingException
    {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        PreparedStatement ps = null;
        String query = "SELECT * from discussions WHERE discussionID = ?";
        ps = conn.prepareStatement(query); //prepares the query and places the id at that position
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String discussionTitle = rs.getString("title"); //Grabs the title, description and the original poster
        String discussionDesc = rs.getString("description");
        String discussionUsername = rs.getString("username");

        query = "Select * from discussionsThread WHERE discussionID = ?";
        ps = conn.prepareStatement(query); //Prepares a new query to get the threads
        ps.setInt(1, id);
        rs = ps.executeQuery();
        ArrayList<String> threadIDs = new ArrayList<String>(); //Creates new Array Lists to hold all the responses IDs, Usernames and Descriptions. 
        ArrayList<String> threadUsernames = new ArrayList<String>();
        ArrayList<String> threadDesc = new ArrayList<String>();

        while(rs.next())
        {
            threadIDs.add(rs.getString("threadID")); // Adds all the respective column data from each row to each respective ArrayList
            threadUsernames.add(rs.getString("username"));
            threadDesc.add(rs.getString("description"));
        }

        session.setAttribute("discussionUsername", discussionUsername); //Places all the new objects into their own attribute in the session to be accessed in the JSP and then the connection is closed. 
        session.setAttribute("discussionTitle", discussionTitle);
        session.setAttribute("discussionDesc", discussionDesc);
        session.setAttribute("threadIDs", threadIDs);
        session.setAttribute("threadUsernames", threadUsernames);
        session.setAttribute("threadDesc",threadDesc);
        conn.close();
    }

    //This functions is used to create a new discussion on the discussions page. 
    public static void createDiscussions(HttpSession session, String title, String desc, User user) throws SQLException, NamingException
    {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        //Grab from the Database first and check it to see if something with that name ALREADY exists FROM that user!!
        String query = "INSERT INTO discussions VALUES(?,?,?)"; //preps the query to have the data set
        PreparedStatement ps = null;
        ps = conn.prepareStatement(query); //prepared statement prepared and strings are set
        ps.setString(1,title);
        ps.setString(2, user.getName());
        ps.setString(3,desc);
        ps.executeUpdate(); //executed and connections closed. 

        conn.close();
    }

    //This function will be used to create a reply to a specific dicussion thread on the page. 
    public static void createDiscussionsThread(HttpSession session, String desc, User user, int id) throws SQLException, NamingException
    {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        String query = "INSERT INTO discussionsThread VALUES(?,?,?)"; //Query prepped
        PreparedStatement ps = null;
        ps = conn.prepareStatement(query); //prepared query
        ps.setInt(1,id); //Data is set in its respective places
        ps.setString(2, user.getName());
        ps.setString(3,desc);
        ps.executeUpdate(); //Executed and connection closed.

        conn.close();
    }

    //This function will be used to create new groups into the DB. 
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

    //Function to show if a user has already joined a group. 
    public static void showJoinedGroup(HttpSession session, String groupName, User user) throws SQLException, NamingException {
        System.out.println("theUser.hasGroup() = true");

        // setting up connection
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();

        // get data from groups table in db
        String query = "SELECT * FROM user_groups WHERE username = ?";
        PreparedStatement ps = null;
        ps = conn.prepareStatement(query);
        ps.setString(1, user.getName());
        ResultSet rs = ps.executeQuery();

        // build list of group names
        if(rs.next()) {
            String GroupName = rs.getString("group_name");
        }
        // closing connection
        conn.close();
    }

    // User logs in and the database checks if the user has already joined a group before.
    public static void loginGroup(HttpSession session, String groupName, User user) throws SQLException, NamingException {
        // setting up connection
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();

        // get data from groups table in db
        String query = "SELECT * FROM user_groups WHERE username = ?";
        PreparedStatement ps = null;
        ps = conn.prepareStatement(query);
        ps.setString(1, user.getName());
        ResultSet rs = ps.executeQuery();
        System.out.println("Querying... " + query + " for: " + user.getName());

        // db found a pre-existing group
        if(rs.next()){
            String GroupName = rs.getString("group_name");
            System.out.println("Group name found: " + GroupName);
            user.setGroup(GroupName);
        }
        // db could not find a pre-existing group
        else{
            System.out.println("Group name not found.");
            user.setGroup("");
        }

        // closing connection
        conn.close();
    }

    //Function that will let a user join a group. 
    public static void joinGroup(HttpSession session, String groupName, User user) throws SQLException, NamingException {
        // setting up connection
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();

        // group name from session is not nothing
        if(groupName != ""){
            // database inserting
            String query = "INSERT INTO user_groups VALUES(?, ?)";
            PreparedStatement ps = null;
            ps = conn.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, groupName);
            ps.executeUpdate();
        }

        // set group in session
        user.setGroup(groupName);

        // closing connection
        conn.close();
    }

    //This function will add a responsibility to the DB. 
    public static void addResponsibility(String userName, String description, String dateInserted, String dateDue, String userGroup) throws SQLException, NamingException{

        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO responsibilities VALUES (?,?,?,?,?,?)");
        ps.setString(1, userName);
        ps.setString(2, description);
        ps.setString(3, dateInserted);
        ps.setString(4, dateDue);
        ps.setString(5, userGroup);
        ps.setString(6, "N");

        ps.executeUpdate();

        conn.close();

    }

    // Populating the list of responsibilities in the DB to display in the JSP table
    public static List<responsibility> responsibilityList(String groupName) throws SQLException, NamingException{

        List<responsibility> responseList = new ArrayList<responsibility>();

        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select * from responsibilities where userGroup = ?");
        ps.setString(1, groupName);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            String responsible = rs.getString("username");
            String description = rs.getString("description");
            String dateInserted = rs.getString("dateInsert");
            String dateDue = rs.getString("dateDue");
            String responseID = rs.getString("responseID");

            if(rs.getString("completed").equals("Y")){
                boolean completed = true;
                responsibility responsObj = new responsibility(responsible, description, dateInserted, dateDue, completed);
                responsObj.setResponseID(responseID);
                responseList.add(responsObj);

            }else{
                boolean completed = false;
                responsibility responsObj = new responsibility(responsible, description, dateInserted, dateDue, completed);
                responsObj.setResponseID(responseID);
                responseList.add(responsObj);
            }
        }
        conn.close();
        return responseList;
    }

    public static void setResponseCompletion(String responseID) throws SQLException, NamingException { // Need to add versionID when added

        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE responsibilities set completed = ? WHERE responseID = ?");
        ps.setString(1, "Y");
        ps.setString(2, responseID);
        ps.executeUpdate();

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
        query += "'" + groupName + "'";                                     // use ps.setString instead of forcing the query
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

    public static void createMilestoneInDB(String desc, String title, String dateDue) throws SQLException, NamingException
    {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        //Grab from the Database first and check it to see if something with that name ALREADY exists FROM that user!!
        String query = "INSERT INTO milestones VALUES(?,?,?)";
        PreparedStatement ps = null;
        ps = conn.prepareStatement(query);
        ps.setString(1,title);
        ps.setString(2,desc);
        ps.setString(3,dateDue);
        ps.executeUpdate();

        conn.close();
    }

    public static void getMilestoneList(User user, HttpSession session) throws SQLException, NamingException
    {
        try{
            InitialContext ctx = new InitialContext();
            // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
            Connection conn = ds.getConnection();
            // Selecting all data from the website_user table ** Note - only gives username/passwords
            PreparedStatement ps = null;
            String query = "SELECT * from milestones";
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<String> milestoneTitles = new ArrayList<String>();
            ArrayList<String> milestoneDescriptions = new ArrayList<String>();
            ArrayList<String> milestoneDates = new ArrayList<String>();

            while(rs.next())
            {
                String milestoneTitle = rs.getString("milestoneTitle");
                String milestoneDescription = rs.getString("description");
                String date = rs.getString("dateDue");
                milestoneTitles.add(milestoneTitle);
                milestoneDescriptions.add(milestoneDescription);
                milestoneDates.add(date);
            }
            session.setAttribute("milestoneTitles", milestoneTitles);
            session.setAttribute("milestoneDescriptions", milestoneDescriptions); 
            session.setAttribute("milestoneDates", milestoneDates); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        conn.close();
    }

    public static void giveFeedback(int SID, int MID, String comment, int mark) throws SQLException, NamingException
    {
        try{
            InitialContext ctx = new InitialContext();
            // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
            Connection conn = ds.getConnection();
            // Selecting all data from the website_user table ** Note - only gives username/passwords
            PreparedStatement ps = null;
            String query = "UPDATE submissions SET mark = ? AND feedback = ? WHERE submissionID = ? AND milestoneID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, mark);
            ps.setString(2, comment);
            ps.setInt(3, SID);
            ps.setInt(4, MID);
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        conn.close();
    }

    public static void writeSubmission(String group, int MID, String desc) throws SQLException, NamingException
    {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        //Grab from the Database first and check it to see if something with that name ALREADY exists FROM that user!!
        String query = "INSERT INTO submissions VALUES(?,?,?)";
        PreparedStatement ps = null;
        ps = conn.prepareStatement(query);
        ps.setString(1, group);
        ps.setInt(2,MID);
        ps.setString(3, desc);
        ps.executeUpdate();

        conn.close();
    }

    public static void getFeedback(HttpSession session, String groupName) throws SQLException, NamingException
    {
        InitialContext ctx = new InitialContext();
        // Path to the datasource, SENG_Assignment3 is the main folder, collabDB is the DB name
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/SENG2050-Assignment3/collabDB");
        Connection conn = ds.getConnection();
        // Selecting all data from the website_user table ** Note - only gives username/passwords
        PreparedStatement ps = null;
        String query = "SELECT * from submission WHERE groupName = ?";
        ps = conn.prepareStatement(query);
        ps.setString(1, groupName);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int milestoneID = rs.getInt("milestoneID");
        String subComments = rs.getString("comments");
        int subMark = rs.getInt("mark");
        query = "SELECT * from milestones where milestoneID = ?";
        ps = conn.prepareStatement(query);
        ps.setInt(1, milestoneID);
        rs = ps.executeQuery();
        rs.next();
        String subTitle = rs.getString("milestoneTitle");

        session.setAttribute("subTitle", subTitle);
        session.setAttribute("subComments", subComments); 
        session.setAttribute("subMark", subMark); 

    }
} 