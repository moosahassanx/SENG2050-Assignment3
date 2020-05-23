public class Teacher extends User
{
    public Teacher()
    {
        staffNumber = "";
        name = "";
        phoneNumber = 0;
        role = "";
    }

    public Teacher(String name, int phoneNumber, String role, String staffNumber)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.staffNumber = staffNumber;
    }

    public void setStaffNumber(String staffNumber)
    {
        this.staffNumber = staffNumber;
    }

    public String getStaffNumber()
    {
        return staffNumber;
    }

    private String staffNumber;
}