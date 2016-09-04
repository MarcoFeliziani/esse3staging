<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="it.mf.i18n.Message" var="lang" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add new ateneo</title>

    </head>
    <body>
        <form method="POST" action='AteneoController' name="frmAddAteneo">
            <% String action = request.getParameter("action");
                System.out.println(action);
            %>
            
            <input type="hidden" name="id" id="id" value="<c:out value="${bean.id}" />" />
            
				<div>
					<label for="descrizione"><fmt:message key="ateneo.label.descrizione" bundle="${lang}" />:</label>
					<input type="text" name="descrizione" id="descrizione" value="<c:out value="${bean.descrizione}" />" placeholder="Descrizione" required>
				</div>
				

<div><label for="citta"><fmt:message key="ateneo.label.citta" bundle="${lang}" />:</label> <input type="text" name="citta" id="citta" value="<c:out value="${bean.citta}" />" > </div>
<div><label for="via"><fmt:message key="ateneo.label.via" bundle="${lang}" />:</label> <input type="text" name="via" id="via" value="<c:out value="${bean.via}" />" > </div>
<div><label for="prov"><fmt:message key="ateneo.label.prov" bundle="${lang}" />:</label> <input type="text" name="prov" id="prov" value="<c:out value="${bean.prov}" />" > </div>
<div><label for="telefono"><fmt:message key="ateneo.label.telefono" bundle="${lang}" />:</label> <input type="text" name="telefono" id="telefono" value="<c:out value="${bean.telefono}" />" > </div>
<div><label for="email"><fmt:message key="ateneo.label.email" bundle="${lang}" />:</label> <input type="text" name="email" id="email" value="<c:out value="${bean.email}" />" > </div>



			<input type="submit" value="<fmt:message key="submit" bundle="${lang}" />" />
			
			<input type="button" onclick="location.href='<%=request.getContextPath() %>/AteneoController?action=delete&id=${bean.id}';" value="<fmt:message key="delete" bundle="${lang}"/>" />
	</form>
    </body>
</html>