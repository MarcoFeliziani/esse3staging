package it.mf.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//import it.mf.dao.AteneoDao;
import it.mf.dao.DocenteDao;
import it.mf.model.Docente;
import it.mf.util.Utility;

/**
 * Servlet implementation class DocenteController
 */
@WebServlet("/DocenteController")
public class DocenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/docente.jsp";
	private static String LIST = "/listdocente.jsp";
	private DocenteDao dao;
	
	public DocenteController() {
		super();
		dao = new DocenteDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		if (action == null)
			action = "list";
		
		if (action.equalsIgnoreCase("edit")) {
			Integer id = getId(request);
//			Integer ateneoId = getAteneoId(request);
			Docente docente = dao.getById(id);
//			Docente docente = dao.getByIdTwo(id,ateneoId);
			forward = INSERT_OR_EDIT;
//			setAteneoList(request);
			request.setAttribute("bean", docente);
		} else if (action.equalsIgnoreCase("delete")) {
			Integer id = getId(request);
			dao.delete(id); 
			forward = LIST;
			request.setAttribute("beans", dao.getAll());
		} else if (action.equalsIgnoreCase("list")) {
			forward = LIST;
			request.setAttribute("beans", dao.getAll());
		} else {
			//setAteneoList(request);
			forward = INSERT_OR_EDIT;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		Integer id = getId(request);
		
		Docente retValue = new Docente();
		
		retValue.setId(id);
//		retValue.setAteneoId(Utility.parseInteger(request.getParameter("ateneoId")));
		retValue.setNome(request.getParameter("nome"));
		retValue.setCognome(request.getParameter("cognome"));
		retValue.setTelefono(request.getParameter("telefono"));
		retValue.setMail(request.getParameter("mail"));
		retValue.setPsw(request.getParameter("psw"));
		retValue.setUtente(request.getParameter("utente"));
		retValue.setRuolo(request.getParameter("ruolo"));
		retValue.setSesso(request.getParameter("sesso"));
		
		if (id == null || id == 0) {
			dao.add(retValue);
		} else {
			dao.check(retValue);
		}
		
		RequestDispatcher view = request.getRequestDispatcher(LIST);
		request.setAttribute("beans", dao.getAll());
		view.forward(request, response);
	}
	
	private Integer getId(HttpServletRequest request) {
		return Utility.getInteger(request.getParameter("id"));
	}
	
//	private Integer getAteneoId(HttpServletRequest request) {
//		return Utility.getInteger(request.getParameter("ateneoId"));
//	}
	
	/*Tira fuori l'elenco degli atenei*/
	/*private void setAteneoList(HttpServletRequest request) {
		request.setAttribute("ateneoList", new AteneoDao().getAllOrdered());
	}*/
		
}


