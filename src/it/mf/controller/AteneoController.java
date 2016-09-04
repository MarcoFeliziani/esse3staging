package it.mf.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.mf.dao.AteneoDao;
import it.mf.model.Ateneo;
import it.mf.util.Utility;

/**
 * Servlet implementation class AteneoController
 */
@WebServlet("/AteneoController")
public class AteneoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/ateneo.jsp";
	private static String LIST = "/listateneo.jsp";
	private AteneoDao dao;
       

    public AteneoController() {
		super();
		dao = new AteneoDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String forward = "";
		String action = request.getParameter("action");
		if (action == null)
			action = "list";
		
		if (action.equalsIgnoreCase("edit")) {
			Integer id = getId(request);
			Ateneo ateneo = dao.getById(id);
			forward = INSERT_OR_EDIT;
			request.setAttribute("bean", ateneo);
		} else if (action.equalsIgnoreCase("delete")) {
			Integer id = getId(request);
			dao.delete(id);
			forward = LIST;
			request.setAttribute("beans", dao.getAll());
		} else if (action.equalsIgnoreCase("list")) {
			forward = LIST;
			request.setAttribute("beans", dao.getAll());
		} else {
			forward = INSERT_OR_EDIT;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer id = getId(request);
		
		Ateneo retValue = new Ateneo();

		
		retValue.setId(id);
		retValue.setDescrizione(request.getParameter("descrizione"));
		retValue.setCitta(request.getParameter("citta"));
		retValue.setVia(request.getParameter("via"));
		retValue.setProv(request.getParameter("prov"));
		retValue.setTelefono(request.getParameter("telefono"));
		retValue.setEmail(request.getParameter("email"));
		
		
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
	
}

