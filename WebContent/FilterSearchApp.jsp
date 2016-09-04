<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="it.mf.i18n.Message" var="lang" />

<html>
<head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="Filter.Para.Title" bundle="${lang}"/></title>
        
		<script type="text/javascript">
  			$(function() {
    		$( "#dataAppello" ).datepicker({ dateFormat: 'dd/mm/yy', minDate: 'today', maxDate: 'today+1Y' });
  			});
		</script>

</head>
<body>
	
		<form method="POST" action='AppelloController' name="frmFilterSearchApp">
		    <% String action = request.getParameter("action");
                System.out.println(action);
            %>
		
		<input type="hidden" id="action" name="action" value="AppelloList"/>
		<input type="hidden" name="id" id="id" value="<c:out value="${bean.id}" />" />
		
		<div><label for="adId"><fmt:message key="appello.label.adId" bundle="${lang}" />:</label> 
			<select name="adId" id="adId">
					<option value="0">NULL</option>
	    		<c:forEach var="item" items="${adList}">
	        		<option value="${item.id}" ${item.id == bean.adId ? 'selected="selected"' : ''}>${item.descrizione}</option>
	    		</c:forEach>
			</select>
		</div>
		
	<!-- 	<div><label for="inizioIscrizione"><fmt:message key="appello.label.inizioIscrizione" bundle="${lang}" />:</label> <input type="text" name="inizioIscrizione" id="inizioIscrizione" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${bean.inizioIscrizione}" />" > </div>
		<div><label for="fineIscrizione"><fmt:message key="appello.label.fineIscrizione" bundle="${lang}" />:</label> <input type="text" name="fineIscrizione" id="fineIscrizione" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${bean.fineIscrizione}" />" > </div> -->
		<div><label for="dataAppello"><fmt:message key="appello.label.dataAppello" bundle="${lang}" />:</label> <input type="text" name="dataAppello" id="dataAppello" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${bean.dataAppello}" />" > </div>
		<div><label for="ora"><fmt:message key="appello.label.ora" bundle="${lang}" />:</label> <input type="text" name="ora" id="ora" value="<c:out value="${bean.ora}" />" > </div>
		<div><label for="tipo"><fmt:message key="appello.label.tipo" bundle="${lang}" />:</label> <input type="text" name="tipo" id="tipo" value="<c:out value="${bean.tipo}" />" > </div>
		
		<div><label for="docenteId"><fmt:message key="appello.label.docenteId" bundle="${lang}" />:</label> 
			<select name="docenteId" id="docenteId">
					<option value="0">NULL</option>
	    		<c:forEach var="item" items="${docenteList}">
	        		<option value="${item.id}" ${item.id == bean.docenteId ? 'selected="selected"' : ''}>${item.nomeCompleto}</option>
	    		</c:forEach>
			</select>
		</div>
		
		<div><label for="cdsId"><fmt:message key="appello.label.cdsId" bundle="${lang}" />:</label> 
			<select name="cdsId" id="cdsId">
					<option value="0">NULL</option>
	    		<c:forEach var="item" items="${cdsList}">
	        		<option value="${item.id}" ${item.id == bean.cdsId ? 'selected="selected"' : ''}>${item.descrizione}</option>
	    		</c:forEach>
			</select>
		</div>
		
		<div><label for="facoltaId"><fmt:message key="appello.label.facoltaId" bundle="${lang}" />:</label> 
			<select name="facoltaId" id="facoltaId">
					<option value="0">NULL</option>
	    		<c:forEach var="item" items="${facoltaList}">
	        		<option value="${item.id}" ${item.id == bean.facoltaId ? 'selected="selected"' : ''}>${item.descrizione}</option>
	    		</c:forEach>
			</select>
		</div>
		
		
		<input type="submit" value="<fmt:message key="submit" bundle="${lang}" />" />
		</form>
	<p><a href="AppelloController?action=insert">Inserisci nuovo appello</a></p>
</body>
</html>