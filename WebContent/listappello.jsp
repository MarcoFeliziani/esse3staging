<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista appelli</title>
</head>
<body>
<%
Integer userId = 0;
userId = (Integer)session.getAttribute("userId");
 %>
<table border=1>
        <thead>
            <tr>
            	<th>codice appello</th>
            	<th>facoltà</th>
            	<th>corso di studio</th>
               	<th>attività didattica</th>
				<th>data appello</th>
				<th>ora</th>
				<th>tipo</th>
				<th>docente</th>
            </tr>
        </thead>
        <tbody>
			<c:set var="count" value="0" scope="page" />
			<c:forEach items="${beans}" var="bean">
			<tr>
					<c:choose>
						<c:when test="${(userId == bean.docenteId)&&(bean.tipoRecord==1)}">
							<td>
                			<a href="AppelloController?action=edit&id=<c:out value="${bean.appelloId}"/>">
                			<c:out value="${bean.appelloId}" /></a></td>
                			<td><c:out value="${bean.facoltaDescrizione}" /></td>
							<td><c:out value="${bean.cdsDescrizione}" /></td>
                			<td><c:out value="${bean.adDescrizione}" /></td>
							<td><c:out value="${bean.dataAppello}" /></td>
							<td><c:out value="${bean.ora}" /></td>
							<td><c:out value="${bean.tipo}" /></td>
							<td><c:out value="${bean.nomeCompleto}" /></td>
						</c:when>
						<c:when test="${bean.tipoRecord==1}">
							<td><c:out value="${bean.appelloId}" /></td>
							<td><c:out value="${bean.facoltaDescrizione}" /></td>
							<td><c:out value="${bean.cdsDescrizione}" /></td>
                			<td><c:out value="${bean.adDescrizione}" /></td>
							<td><c:out value="${bean.dataAppello}" /></td>
							<td><c:out value="${bean.ora}" /></td>
							<td><c:out value="${bean.tipo}" /></td>
							<td><c:out value="${bean.nomeCompleto}" /></td>	
						</c:when>
                		<c:otherwise>
                			<td colspan="7"></td><td><c:out value="${bean.nomeCompleto}" /></td>
							<c:set var="count" value="${count + 1}" scope="page"/>
                		</c:otherwise>
                	</c:choose>
			</tr>
			</c:forEach>
        </tbody>
</table>
<p><a href="<%=request.getContextPath()%>/AppelloController">Home Page</a></p>
</body>
</html>