<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
Integer userId = 0;
//Integer ateneoId = 0;
String nome = "";
if (session.getAttribute("userId") != null) {
	userId = (Integer)session.getAttribute("userId");
	nome = (String) session.getAttribute("nome");
	//ateneoId = (Integer)session.getAttribute("ateneoId");
	}
%>

<style type="text/css">
  table.header {
    width: 100%;
    border=0; 
    cellspacing=0;
    cellpadding=0;
/*     overflow: hidden; */
/*     display: inline-block; */
    white-space: nowrap;
/*     text-align: center; */
    }
  table.header th {
    width: 30px;
/*     overflow: hidden; */
    display: inline-block;
    white-space: nowrap;
    text-align: center;
    }
  table.header td {
    width: 30px;
/*     overflow: hidden; */
    display: inline-block;
    white-space: nowrap;
    text-align: center;
}

	body { 


		/*background-image: url('images/logoUNICAM.jpg'); 
		width:      80%;
		height:     80%; 
		position:   relative;
		background-size:         100% auto; 
		-moz-background-size:	 100% auto;
		-webkit-background-size: 100% auto;
		-ms-background-size: 	 100% auto;
		-o-background-size: 	 100% auto;
		margin:     10px auto 0;*/

		}  

#locale A { padding:10px 0 1px 0; margin-left: 2px; display:block; float:left; }
#locale A.current { padding:2px 0 1px 0; }
</style>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="page"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="it.mf.i18n.Message" var="lang"/>
		
<table class="header">
		<tr>
			<td>
				<div id="bookingLogo" align="left" >
				<a href="<%=request.getContextPath()%>">
					<img src="<%=request.getContextPath()%>/images/home.png" alt="Booking"/>
				</a>
				</div>
			</td>
		
<c:if test="${userId > 0}">

			<td>
				<div align="center">
				  <div class="dropdown">
				    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
				    	<span class="caret"></span>
				    </button>
				    <ul class="dropdown-menu">
				      <li><a href="<%=request.getContextPath()%>/DocenteController">Docenti</a></li>
				      <li><a href="<%=request.getContextPath()%>/FacoltaController">Facoltà</a></li>
				      <li><a href="<%=request.getContextPath()%>/CDSController">Corsi di studio</a></li>
				      <li><a href="<%=request.getContextPath()%>/ADController">Attività didattiche</a></li>
				    </ul>
				  </div>
				</div>
			</td>
	
	<c:choose>
	<c:when test="${'A' == ruolo}">
			<td>
				<div id="wellcome" align="right">
					<fmt:message key="login.label.amministratore" bundle="${lang}"/>:
					<a href="<%=request.getContextPath()%>/DocenteController?action=edit&id=<%=userId %>">
						<%=nome%>
					</a>
				</div>			
			</td>
	</c:when>
	<c:otherwise>
			<td>
				<div id="wellcome" align="right">
					<fmt:message key="login.label.username" bundle="${lang}"/>:
					<a href="<%=request.getContextPath()%>/DocenteController?action=edit&id=<%=userId %>">
						<%=nome%>
					</a>
				</div>			
			</td>
	</c:otherwise>
	</c:choose>
			
			<td>
				<div id="checkout">
					<a href="<%=request.getContextPath()%>/LogoutServlet">
					<img src="<%=request.getContextPath()%>/images/shutdown.png" alt="Uscita"/>
					</a>
				</div>
			</td>
	</c:if>			
			
		</tr>
</table>
