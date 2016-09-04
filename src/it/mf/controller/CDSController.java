package it.mf.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import it.mf.dao.AteneoDao;
//import it.mf.dao.FacoltaDao;
import it.mf.dao.CDSDao;
import it.mf.model.CDS;
import it.mf.util.Utility;

/**
 * Servlet implementation class CDSController
 */
@WebServlet("/CDSController")
public class CDSController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/CDS.jsp";
	private static String LIST = "/listCDS.jsp";
	private CDSDao dao;
       

    public CDSController() {
        super();
        dao = new CDSDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String forward = "";
		String action = request.getParameter("action");
		if (action == null)
			action = "list";
		
		if (action.equalsIgnoreCase("edit")) {
			Integer id = getId(request);
//			Integer ateneoId = getAteneoId(request);
//			Integer facoltaId = getFacoltaId(request);
//			CDS cds = dao.getByIdCDS(id,ateneoId,facoltaId);
			CDS cds = dao.getById(id);
			forward = INSERT_OR_EDIT;
//			setAteneoList(request);
//			setFacoltaList(request);
			request.setAttribute("bean", cds);
		} else if (action.equalsIgnoreCase("delete")) {
			Integer id = getId(request);
			dao.delete(id);
			forward = LIST;
			request.setAttribute("beans", dao.getAll());
		} else if (action.equalsIgnoreCase("list")) {
			forward = LIST;
			request.setAttribute("beans", dao.getAll());
		} else {
//			setAteneoList(request);
//			setFacoltaList(request);
			forward = INSERT_OR_EDIT;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer id = getId(request);
		
		CDS retValue = new CDS();
		
		retValue.setId(id);
//		retValue.setFacoltaId(Utility.parseInteger(request.getParameter("facoltaId")));
//		retValue.setAteneoId(Utility.parseInteger(request.getParameter("ateneoId")));
		retValue.setDescrizione(request.getParameter("descrizione"));
		retValue.setDurata(request.getParameter("durata"));
		retValue.setCrediti(Utility.parseInteger(request.getParameter("crediti")));
		
		
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
//	
//	private Integer getFacoltaId(HttpServletRequest request) {
//		return Utility.getInteger(request.getParameter("facoltaId"));
//	}
	
	/*Tira fuori l'elenco degli atenei*/
//	private void setAteneoList(HttpServletRequest request) {
//		request.setAttribute("ateneoList", new AteneoDao().getAllOrdered());
//	}
//
//	/*Tira fuori l'elenco delle facoltà*/
//	private void setFacoltaList(HttpServletRequest request) {
//		request.setAttribute("facoltaList", new FacoltaDao().getAllOrdered());
//	}
}
