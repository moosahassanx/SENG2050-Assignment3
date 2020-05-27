package userpackage;

public class User
{
    public User()
    {
        name = "";
        phoneNumber = 0;
        role = "";
        password = "";
    }

    public User(String name, int phoneNumber, String role)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPhoneNumber(int phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getName()
    {
        return name;
    }

    public int phoneNumber()
    {
        return phoneNumber;
    }

    public String getRole()
    {
        return role;
    }

    public void setPassword(String p){
        this.password = p;
    }


    protected String name;
    protected int phoneNumber;
    protected String role;
    protected String password;
}