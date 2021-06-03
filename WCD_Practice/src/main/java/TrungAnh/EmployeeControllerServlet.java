package TrungAnh;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeControllerServlet", value = "/EmployeeControllerServlet")
public class EmployeeControllerServlet extends HttpServlet {

    private EmployeeDbUtil employeeDbUtil;
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        Context initContext = null;
        try {
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/TestDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }


        try {
            employeeDbUtil = new EmployeeDbUtil(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String theCommand = request.getParameter("command");

            if (theCommand == null) {
                theCommand = "LIST";
            }

            switch (theCommand) {
                case "LIST":
                    listEmployee(request, response);
                    break;
                case "ADD":
                    addEmployee(request, response);
                    break;
                case "LOAD":
                    loadEmployee(request, response);
                    break;
                default:
                    listEmployee(request, response);
            }

        } catch (Exception exc) {
            throw new ServletException(exc);
        }

    }

    private void loadEmployee(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String theEmployeeId = request.getParameter("vocabularyId");

        Employee theEmployee = employeeDbUtil.getEmployee(theEmployeeId);

        request.setAttribute("THE_EMPLOYEE", theEmployee);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/update-vocabulary-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {


        String fullname = request.getParameter("fullname");
        String birthday = request.getParameter("birthday");
        String address = request.getParameter("address");
        String position = request.getParameter("position");
        String department = request.getParameter("department");

        Employee theEmployee = new Employee(fullname, birthday, address, position, department);

        // add the student to the database
        employeeDbUtil.addEmployee(theEmployee);

        // send back to main page (the student list)
        listEmployee(request, response);
    }

    private void listEmployee(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<Employee> vocabularies = employeeDbUtil.getEmployees();

        request.setAttribute("EMPLOYEE_LIST", vocabularies);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/list.jsp");
        dispatcher.forward(request, response);
    }

}

