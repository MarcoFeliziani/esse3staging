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
<title>Appello</title>

		<script type="text/javascript">
			$(function() {
    		$( "#inizioIscrizione" ).datepicker({ dateFormat: 'dd/mm/yy', minDate: 'today', maxDate: 'today+1Y' });
  			});
  			$(function() {
    		$( "#fineIscrizione" ).datepicker({ dateFormat: 'dd/mm/yy', minDate: 'inizioIscrizione+3D', maxDate: 'today+1Y' });
  			});
  			$(function() {
    		$( "#dataAppello" ).datepicker({ dateFormat: 'dd/mm/yy', minDate: 'fineIscrizione+2D', maxDate: 'today+1Y' });
  			});
		</script>
		<script>
		var quanti = 0;
		function Quanti(elemento)
		{
  			if (elemento.checked){ 
      		quanti += 1;
  			}
  			else{
      		quanti -= 1;
  			}
  			document.frmAddAppello.risultato.value=quanti;
		}
		</script>

</head>
<body>
<%
Integer userId = 0;
userId = (Integer)session.getAttribute("userId");
 %>
	<form method="POST" action='AppelloController' name="frmAddAppello">
            <% String action = request.getParameter("action");
                System.out.println(action);
            %>
            
        	<input type="hidden" name="id" id="id" value="<c:out value="${bean.id}" />" />
            
            <div><label for="adId"><fmt:message key="appello.label.adId" bundle="${lang}" />:</label> 
			<select name="adId" id="adId">
	    		<c:forEach var="item" items="${adList}">
	        		<option value="${item.id}" ${item.id == bean.adId ? 'selected="selected"' : ''}>${item.descrizione}</option>
	    		</c:forEach>
			</select>
			</div>
		
			<div><label for="inizioIscrizione"><fmt:message key="appello.label.inizioIscrizione" bundle="${lang}" />:</label> <input type="text" name="inizioIscrizione" id="inizioIscrizione" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${bean.inizioIscr}" />" > </div>
			<div><label for="fineIscrizione"><fmt:message key="appello.label.fineIscrizione" bundle="${lang}" />:</label> <input type="text" name="fineIscrizione" id="fineIscrizione" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${bean.fineIscr}" />" > </div>
			<div><label for="dataAppello"><fmt:message key="appello.label.dataAppello" bundle="${lang}" />:</label> <input type="text" name="dataAppello" id="dataAppello" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${bean.dataAppello}" />" > </div>
			<div><label for="ora"><fmt:message key="appello.label.ora" bundle="${lang}" />:</label> <input type="text" name="ora" id="ora" value="<c:out value="${bean.ora}" />" > </div>
			<div><label for="tipo"><fmt:message key="appello.label.tipo" bundle="${lang}" />:</label> <input type="text" name="tipo" id="tipo" value="<c:out value="${bean.tipo}" />" > </div>
		
		<!-- 	<div><label for="action"><fmt:message key="appello.label.azione" bundle="${lang}" />:</label> <input type="text" name="action" id="action" value="<c:out value="${action}" />" > </div> -->
			<c:choose>
			<c:when test="${action == null}">
			<div><label for="docenteId"><fmt:message key="appello.label.docenteId" bundle="${lang}" />:</label> 
				<select name="docenteId" id="docenteId">
	    			<c:forEach var="item" items="${docenteList}">
	        			<option value="${item.id}" ${item.id == bean.docenteId ? 'selected="selected"' : ''}>${item.nomeCompleto}</option>
	    			</c:forEach>
				</select>
			</div>
			</c:when>
			<c:otherwise>
			<input type="hidden" name="docenteId" id="docenteId" value="<c:out value="${userId}" />" />
			</c:otherwise>
			</c:choose>
			<c:if test="${action == 'edit'}">
				<br />
				<label for="docenteME">docenti membri effettivi (non più di tre selezioni): </label>
				<br />
				<c:forEach var="item" items="${docenteList}">
				<c:if test="${userId != item.id}">
					<input type="checkbox" id="docenteME" name="docenteME" value="${item.id}" onclick="Quanti(this)"/>${item.nomeCompleto}<br/> 
				</c:if>
				</c:forEach>
				<br />
				<br />
			</c:if>
			<div><label for="cdsId"><fmt:message key="appello.label.cdsId" bundle="${lang}" />:</label> 
				<select name="cdsId" id="cdsId">
	    			<c:forEach var="item" items="${cdsList}">
	        			<option value="${item.id}" ${item.id == bean.cdsId ? 'selected="selected"' : ''}>${item.descrizione}</option>
	    			</c:forEach>
				</select>
			</div>
		
			<div><label for="facoltaId"><fmt:message key="appello.label.facoltaId" bundle="${lang}" />:</label> 
				<select name="facoltaId" id="facoltaId">
	    			<c:forEach var="item" items="${facoltaList}">
	        			<option value="${item.id}" ${item.id == bean.facoltaId ? 'selected="selected"' : ''}>${item.descrizione}</option>
	    			</c:forEach>
				</select>
			</div>
			<input type="hidden" id="risultato" name="risultato" />
	 	 	<input type="hidden" name="appTr1" id="appTr1" value="<c:out value="${bean.appTr1}" />" /> 
			<br/>
           <input type="submit" value="<fmt:message key="submit" bundle="${lang}" />" onclick="alert('Selezionati: ' + quanti)" />
          <c:if test="${action == 'edit'}">
           <input type="button" onclick="location.href='<%=request.getContextPath() %>/AppelloController?action=delete&id=${bean.id}';" value="<fmt:message key="delete" bundle="${lang}"/>" />
		  </c:if>
		  
	</form>           

</body>
</html>