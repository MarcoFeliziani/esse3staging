package it.mf.controller;

import java.io.IOException;
//import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import it.mf.util.Utility;
import it.mf.dao.ADDao;
import it.mf.dao.AppelloDao;
import it.mf.dao.CDSDao;
import it.mf.dao.DocenteDao;
import it.mf.dao.FacoltaDao;
import it.mf.model.Appello;

/**
 * Servlet implementation class AppelloController
 */
@WebServlet("/AppelloController")
public class AppelloController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/appello.jsp";
	private static String LIST_APPELLO = "/listappello.jsp";
	private static String LIST_APPELLO_FILTER = "/FilterSearchApp.jsp";
	private AppelloDao dao;
       

    public AppelloController() {
    	super();
		dao = new AppelloDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String forward = "";
		String action = request.getParameter("action");
		if (action == null)
			action = "list_app_filter";
		
		if (action.equalsIgnoreCase("edit")) {
			Integer id = getId(request);
			Appello appello = dao.getById(id);
			forward = INSERT_OR_EDIT;
			setAdList(request);
			setDocenteList(request);
			setCdsList(request);
			setFacoltaList(request);
			request.setAttribute("action", "edit"); //NUOVO, SERVE PER IL DOCENTE HIDDEN
			request.setAttribute("bean", appello);
		} else if (action.equalsIgnoreCase("delete")) {
			Integer id = getId(request);
			dao.delete(id);
			request.setAttribute("action", "AppelloList");
			forward = LIST_APPELLO_FILTER;
			//request.setAttribute("beans", dao.getAll());
		} else if(action.equalsIgnoreCase("insert")){
			setAdList(request);
			setDocenteList(request);
			setCdsList(request);
			setFacoltaList(request);
			request.setAttribute("action", "insert");
			forward = INSERT_OR_EDIT;	
		} else {
			setAdList(request);
			setDocenteList(request);
			setCdsList(request);
			setFacoltaList(request);
			request.setAttribute("action", "AppelloList");
			forward = LIST_APPELLO_FILTER;
		}
	
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);		
	}

	//HO APPENA INSERITO L'ACTION APPELLO LIST PER VISUALIZZARE LA LISTA APPELLI TRAMITE FILTRO
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer id = getId(request);
		String forward = LIST_APPELLO_FILTER;
		String action = request.getParameter("action");
		Integer risCheckbox = Utility.parseInteger(request.getParameter("risultato"));
		//Integer checkbox = Utility.parseInteger(request.getParameter("docenteME"));
		String[] checkedString = (request.getParameterValues("docenteME"));
		
		Appello retValue = new Appello();
		
		retValue.setId(id);
		retValue.setAdId(Utility.parseInteger(request.getParameter("adId")));
		retValue.setInizioIscr(Utility.parseDate(request.getParameter("inizioIscrizione")));
		retValue.setFineIscr(Utility.parseDate(request.getParameter("fineIscrizione")));
		retValue.setDataAppello(Utility.parseDate(request.getParameter("dataAppello")));
		retValue.setOra(request.getParameter("ora"));
		retValue.setTipo(request.getParameter("tipo"));
		retValue.setDocenteId(Utility.parseInteger(request.getParameter("docenteId")));
		retValue.setCdsId(Utility.parseInteger(request.getParameter("cdsId")));
		retValue.setFacoltaId(Utility.parseInteger(request.getParameter("facoltaId")));
		retValue.setTipoRecord(Utility.parseInteger(request.getParameter("tipoRecord")));
		retValue.setAppTr1(Utility.parseInteger(request.getParameter("appTr1")));
		
		if (action != null && action.equalsIgnoreCase("AppelloList")){

			Integer adId = Utility.parseInteger(request.getParameter("adId"));
			Date dataAppello = Utility.parseDate(request.getParameter("dataAppello"));
			String ora = request.getParameter("ora");
			String tipo = request.getParameter("tipo");
			Integer docenteId = Utility.parseInteger(request.getParameter("docenteId"));
			Integer cdsId = Utility.parseInteger(request.getParameter("cdsId"));
			Integer facoltaId = Utility.parseInteger(request.getParameter("facoltaId"));
			//Integer tipoRecord = Utility.parseInteger(request.getParameter("tipoRecord"));
			
			forward = LIST_APPELLO;
			request.setAttribute("action", "edit"); //NUOVO, SERVE PER IL DOCENTE HIDDEN
			request.setAttribute("beans", dao.getListaAppelli(adId, dataAppello, ora, tipo, docenteId, cdsId, facoltaId));
			
		} else{
			
			if (id == null || id == 0) {
				
				dao.add(retValue);
			} 
//			else if(risCheckbox > 3){
//
//				PrintWriter out = response.getWriter();
//				out.println("<font color=red>Hai inserito più di 3 docenti</font>");
//				getServletContext().getRequestDispatcher("/FilterSearchApp.jsp").forward(request, response);
//			}
			else if(risCheckbox > 0){ //Per l'inserimento del tipo record 2
				retValue.setTipoRecord(2);
				dao.add2(retValue,checkedString);	
			} 
			else {
				dao.check(retValue);
			}
		}
		
		setAdList(request);
		setDocenteList(request);
		setCdsList(request);
		setFacoltaList(request);
		RequestDispatcher view = request.getRequestDispatcher(forward);
		request.setAttribute("action", "AppelloList");
		view.forward(request, response);
		
	}
	
	private Integer getId(HttpServletRequest request) {
		return Utility.getInteger(request.getParameter("id"));
	}
	
	private void setAdList(HttpServletRequest request) {
		request.setAttribute("adList", new ADDao().getAllOrdered());	
	}
	
	private void setDocenteList(HttpServletRequest request) {
		request.setAttribute("docenteList", new DocenteDao().getAllOrdered());	
	}
	
	private void setCdsList(HttpServletRequest request) {
		request.setAttribute("cdsList", new CDSDao().getAllOrdered());	
	}
	
	private void setFacoltaList(HttpServletRequest request) {
		request.setAttribute("facoltaList", new FacoltaDao().getAllOrdered());	
	}

}
