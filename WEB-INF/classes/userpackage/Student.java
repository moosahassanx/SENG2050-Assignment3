package userpackage;

import java.sql.*;
import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Student extends User
{
    public Student()
    {
        studentNumber = "";
        averageMark = 0;
        name = "";
        phoneNumber = 0;
        role = "";
    }

    public Student(String name, int phoneNumber, String role, String studentNumber, float averageMark)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.studentNumber = studentNumber;
        this.averageMark = averageMark;
    }

    public void setStudentNumber(String studentNumber)
    {
        this.studentNumber = studentNumber;
    }

    public void setAverageMark(float averageMark)
    {
        this.averageMark = averageMark;
    }

    public String getStudentNumber()
    {
        return studentNumber;
    }

    public float getAverageMark()
    {
        return averageMark;
    }

    public void setGroup(String groupName)
    {
        this.groupName = groupName;
    }

    public String getGroup()
    {
        return groupName;
    }
    
    private String groupName;
    private String studentNumber;
    private float averageMark;

}
