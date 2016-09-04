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
<title>Add new AD</title>
</head>
<body>
	<form method="POST" action='ADController' name="frmAddAD">
            <% String action = request.getParameter("action");
                System.out.println(action);             
            %>
            
            <input type="hidden" name="id" id="id" value="<c:out value="${bean.id}" />" />
            

           <!--  	<div><label for="cdsId"><fmt:message key="ad.label.cdsId" bundle="${lang}" />:</label> 

					<select name="cdsId" id="cdsId">
	    			<c:forEach var="item" items="${cdsList}">
	        			<option value="${item.id}" ${item.id == bean.cdsId ? 'selected="selected"' : ''}>${item.descrizione}</option>
	    			</c:forEach>
					</select>

				</div>-->
            	
            	<!-- <div><label for="ateneoId"><fmt:message key="ad.label.ateneoId" bundle="${lang}" />:</label> 

					<select name="ateneoId" id="ateneoId">
	    			<c:forEach var="item" items="${ateneoList}">
	        			<option value="${item.id}" ${item.id == bean.ateneoId ? 'selected="selected"' : ''}>${item.descrizione}</option>
	    			</c:forEach>
					</select>

				</div>
				
				<div><label for="facoltaId"><fmt:message key="ad.label.facoltaId" bundle="${lang}" />:</label> 

					<select name="facoltaId" id="facoltaId">
	    			<c:forEach var="item" items="${facoltaList}">
	        			<option value="${item.id}" ${item.id == bean.facoltaId ? 'selected="selected"' : ''}>${item.descrizione}</option>
	    			</c:forEach>
					</select>

				</div> -->

				<div>
					<label for="descrizione"><fmt:message key="ad.label.descrizione" bundle="${lang}" />:</label>
					<input type="text" name="descrizione" id="descrizione" value="<c:out value="${bean.descrizione}" />" placeholder="Descrizione" required>
				</div>
				
				<div>
					<label for="crediti"><fmt:message key="ad.label.crediti" bundle="${lang}" />:</label>
					<input type="text" name="crediti" id="crediti" value="<c:out value="${bean.crediti}" />" placeholder="Crediti" required>
				</div>
				
			<input type="submit" value="<fmt:message key="submit" bundle="${lang}" />" />
			
			<input type="button" onclick="location.href='<%=request.getContextPath() %>/ADController?action=delete&id=${bean.id}';" value="<fmt:message key="delete" bundle="${lang}"/>" />
	</form>

</body>
</html>