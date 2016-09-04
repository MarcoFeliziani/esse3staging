<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show All</title>
</head>
<body>
    <table border=1>
        <thead>
            <tr>
               	<th>nome</th>
				<th>cognome</th>
				<th>telefono</th>
				<th>ruolo</th>
				<th>sesso</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${beans}" var="bean">
                <tr>
                	<td>
                	<a href="DocenteController?action=edit&id=<c:out value="${bean.id}"/>">
                	<c:out value="${bean.nome}" /></a></td>
					<td><c:out value="${bean.cognome}" /></td>
					<td><c:out value="${bean.telefono}" /></td>
					<td><c:out value="${bean.ruolo}" /></td>
					<td><c:out value="${bean.sesso}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="DocenteController?action=insert">Add</a></p>
</body>
</html>
