<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="it.mf.i18n.Message" var="lang" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new CDS</title>
</head>
<body>
	<form method="POST" action='CDSController' name="frmAddCDS">
            <% String action = request.getParameter("action");
                System.out.println(action);             
            %>
            
            <input type="hidden" name="id" id="id" value="<c:out value="${bean.id}" />" />
            

            	<!-- <div><label for="ateneoId"><fmt:message key="cds.label.ateneoId" bundle="${lang}" />:</label> 

					<select name="ateneoId" id="ateneoId">
	    			<c:forEach var="item" items="${ateneoList}">
	        			<option value="${item.id}" ${item.id == bean.ateneoId ? 'selected="selected"' : ''}>${item.descrizione}</option>
	    			</c:forEach>
					</select>

				</div>
				
				<div><label for="facoltaId"><fmt:message key="cds.label.facoltaId" bundle="${lang}" />:</label> 

					<select name="facoltaId" id="facoltaId">
	    			<c:forEach var="item" items="${facoltaList}">
	        			<option value="${item.id}" ${item.id == bean.facoltaId ? 'selected="selected"' : ''}>${item.descrizione}</option>
	    			</c:forEach>
					</select>

				</div>-->

				<div>
					<label for="descrizione"><fmt:message key="cds.label.descrizione" bundle="${lang}" />:</label>
					<input type="text" name="descrizione" id="descrizione" value="<c:out value="${bean.descrizione}" />" placeholder="Descrizione" required>
				</div>
				
				<div>
					<label for="durata"><fmt:message key="cds.label.durata" bundle="${lang}" />:</label>
					<input type="text" name="durata" id="durata" value="<c:out value="${bean.durata}" />" required>
				</div>
				
				<div>
					<label for="crediti"><fmt:message key="cds.label.crediti" bundle="${lang}" />:</label>
					<input type="text" name="crediti" id="crediti" value="<c:out value="${bean.crediti}" />" required>
				</div>
				
			<input type="submit" value="<fmt:message key="submit" bundle="${lang}" />" />
			
			<input type="button" onclick="location.href='<%=request.getContextPath() %>/CDSController?action=delete&id=${bean.id}';" value="<fmt:message key="delete" bundle="${lang}"/>" />
	</form>

</body>
</html>