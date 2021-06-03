<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <title>Vocabulary Tracker App</title>

</head>

<body>

<div id="wrapper">
    <div id="header" style="margin-left: 200px;">
        <h2>Trung Anh Web</h2>
    </div>
</div>

<div id="container">

    <div id="content">

        <input type="button" value="Add Employee"
               onclick="window.location.href='employee.jsp'; return false;"
               class="add-employee-button" style="margin-left: 300px;"
        /><br/>

        <table style="text-align: center; margin-left: 300px;">

            <tr>
                <th>Full Name</th>
                <th>Birthday</th>
                <th>Address</th>
                <th>Position</th>
                <th>Department</th>
            </tr>

            <c:forEach var="tempEmployee" items="${EMPLOYEE_LIST}">

                <c:url var="tempLink" value="EmployeeControllerServlet">
                    <c:param name="command" value="LOAD"/>
                    <c:param name="employeeId" value="${tempEmployee.id}"/>
                </c:url>

                <tr>
                    <td> ${tempEmployee.fullname} </td>
                    <td> ${tempEmployee.birthday} </td>
                    <td> ${tempEmployee.address} </td>
                    <td> ${tempEmployee.position} </td>
                    <td> ${tempEmployee.department} </td>
                </tr>

            </c:forEach>

        </table>

    </div>

</div>
</body>


</html>

