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
        <title>Aggiungi nuovo professore</title>


    </head>
    <body>
        <form method="POST" action='DocenteController' name="frmAddDocente">
            <% String action = request.getParameter("action");
                System.out.println(action);             
            %>
            
            <input type="hidden" name="id" id="id" value="<c:out value="${bean.id}" />" />
            

            	<!-- <div><label for="ateneoId"><fmt:message key="docente.label.ateneoId" bundle="${lang}" />:</label> 

					<select name="ateneoId" id="ateneoId">
	    			<c:forEach var="item" items="${ateneoList}">
	        			<option value="${item.id}" ${item.id == bean.ateneoId ? 'selected="selected"' : ''}>${item.descrizione}</option>
	    			</c:forEach>
					</select>

				</div>-->

				<div>
					<label for="nome"><fmt:message key="docente.label.nome" bundle="${lang}" />:</label>
					<input type="text" name="nome" id="nome" value="<c:out value="${bean.nome}" />" placeholder="Nome" required>
				</div>
<div><label for="cognome"><fmt:message key="docente.label.cognome" bundle="${lang}" />:</label> <input type="text" name="cognome" id="cognome" value="<c:out value="${bean.cognome}" />" > </div>
<div><label for="telefono"><fmt:message key="docente.label.telefono" bundle="${lang}" />:</label> <input type="text" name="telefono" id="telefono" value="<c:out value="${bean.telefono}" />" > </div>
<div><label for="mail"><fmt:message key="docente.label.mail" bundle="${lang}" />:</label> <input type="text" name="mail" id="mail" value="<c:out value="${bean.mail}" />" > </div>
<div><label for="sesso"><fmt:message key="docente.label.sesso" bundle="${lang}" />:</label> 
	<select  name='sesso'>
	    <option value="M" ${bean.sesso == 'M' ? 'selected' : ''}><fmt:message key="sesso.maschio" bundle="${lang}" /></option>
	    <option value="F" ${bean.sesso == 'F' ? 'selected' : ''}><fmt:message key="sesso.femmina" bundle="${lang}" /></option>
	</select>
</div>


<c:if test="${'A' == ruolo}">
	<div><label for="utente"><fmt:message key="docente.label.utente" bundle="${lang}" />:</label> <input type="text" name="utente" id="utente" value="<c:out value="${bean.utente}" />" > </div>
	<div><label for="psw"><fmt:message key="docente.label.psw" bundle="${lang}" />:</label> <input type="password" name="psw" id="psw" value="<c:out value="${bean.psw}" />" > </div>
	<div><label for="ruolo"><fmt:message key="docente.label.ruolo" bundle="${lang}" />:</label> <input type="text" name="ruolo" id="ruolo" value="<c:out value="${bean.ruolo}" />" > </div>
</c:if>

			<input type="submit" value="<fmt:message key="submit" bundle="${lang}" />" />
			
			<input type="button" onclick="location.href='<%=request.getContextPath() %>/DocenteController?action=delete&id=${bean.id}';" value="<fmt:message key="delete" bundle="${lang}"/>" />
	</form>
    </body>
</html>