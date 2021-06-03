package TrungAnh;

public class Employee {

    private int id;
    private String fullname;
    private String birthday;
    private String address;
    private String position;
    private String department;

    public Employee(String fullname, String birthday, String address, String position, String department) {
        this.fullname = fullname;
        this.birthday = birthday;
        this.address = address;
        this.position = position;
        this.department = department;
    }

    public Employee(int id, String fullname, String birthday, String address, String position, String department) {
        this.id = id;
        this.fullname = fullname;
        this.birthday = birthday;
        this.address = address;
        this.position = position;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname( String fullname) {
        this.fullname = fullname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition(){
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment(){
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", fullname=" + fullname + ", birthday=" + birthday + ", address=" + address + ", position" + position + ", department" + department + "]";
    }

}
