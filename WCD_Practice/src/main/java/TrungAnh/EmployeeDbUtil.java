package TrungAnh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class EmployeeDbUtil {

    private DataSource dataSource;

    public EmployeeDbUtil(DataSource theDataSource) { dataSource = theDataSource; }

    public List<Employee> getEmployees() throws Exception{
        List<Employee> employees = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();

            String sql = "select * from employee order by fullname";

            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {
                int id = myRs.getInt("id");
                String fullname = myRs.getString("fullname");
                String birthday = myRs.getString("birthday");
                String address = myRs.getString("address");
                String position = myRs.getString("position");
                String department = myRs.getString("department");

                Employee tempEmployee = new Employee(id, fullname, birthday, address, position, department);

                employees.add(tempEmployee);
            }
            return employees;
        }
        finally {
            close(myConn, myStmt, myRs);
        }
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void addEmployee(Employee theEmployee) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();

            String sql = "insert into employee "
                    + "(fullname, birthday, address, position, department)"
                    + "value (?, ?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, theEmployee.getFullname());
            myStmt.setString(2, theEmployee.getBirthday());
            myStmt.setString(3, theEmployee.getAddress());
            myStmt.setString(4, theEmployee.getPosition());
            myStmt.setString(5, theEmployee.getDepartment());


            myStmt.execute();
        }
        finally {
            close(myConn, myStmt, null);
        }

    }

    public Employee getEmployee(String theEmployeeId) throws Exception {

        Employee theEmployee = null;

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int employeeId;

        try {
            employeeId = Integer.parseInt(theEmployeeId);

            myConn = dataSource.getConnection();

            String sql = "select * from employee where id=?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, employeeId);

            myRs = myStmt.executeQuery();

            if (myRs.next()) {
                String fullname = myRs.getString("fullname");
                String birthday = myRs.getString("birthday");
                String address = myRs.getString("address");
                String position = myRs.getString("position");
                String department = myRs.getString("department");

                Employee tempEmployee = new Employee( fullname, birthday, address, position, department );
            }
            else {
                throw new Exception("Could not find employee id: " + employeeId);
            }

            return theEmployee;
        }
        finally {
            close(myConn, myStmt, myRs);
        }
    }

}
