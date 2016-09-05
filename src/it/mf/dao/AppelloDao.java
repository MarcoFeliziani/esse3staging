package it.mf.dao;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
//import java.util.Hashtable;
import java.util.List;
//import java.util.TreeSet;


import it.mf.model.Appello;
import it.mf.modelView.AppelloList;


public class AppelloDao extends Dao {
	
	private static final String TABLE_NAME = "mf_appello";

	public AppelloDao() {
		super();
	}
	
	@Override
	String getTableName() {
		return TABLE_NAME;
	}
	
	//Ho aggiunto il metodo check2 per l'INSERT, aggiungendo String[] checkedString (dove risiedono i risultati delle checkbox)
	public void add2(Appello appello, String[] checkedString) {
		for (String i : checkedString) {
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO mf_appello "); 
		sb.append("(ad_id ,inizio_iscr ,fine_iscr ,data_appello ,ora ,tipo ,docente_id ,cds_id ,facolta_id ,tipo_record ,app_tr_1) VALUES "); 
		sb.append("(?     ,?       	   ,?         ,?            ,?   ,?    ,?          ,?      ,?          ,?           ,?) ");
		//          1      2        	3      	   4             5    6     7     	    8       9           10           11
		
		try {
			PreparedStatement stmt = getConnection().prepareStatement(sb.toString());
			
			stmtPara(stmt, 1, Types.INTEGER, appello.getAdId());
			stmtPara(stmt, 2, Types.DATE, 	 new java.sql.Date(appello.getInizioIscr().getTime()));
			stmtPara(stmt, 3, Types.DATE,    new java.sql.Date(appello.getFineIscr().getTime()));                              
			stmtPara(stmt, 4, Types.DATE,    new java.sql.Date(appello.getDataAppello().getTime()));                              
			stmtPara(stmt, 5, Types.VARCHAR, appello.getOra());
			stmtPara(stmt, 6, Types.VARCHAR, appello.getTipo());
			stmtPara(stmt, 7, Types.VARCHAR, i);
			stmtPara(stmt, 8, Types.INTEGER, appello.getCdsId() );
			stmtPara(stmt, 9, Types.INTEGER, appello.getFacoltaId());
			stmtPara(stmt, 10, Types.INTEGER,appello.getTipoRecord());
			stmtPara(stmt, 11, Types.INTEGER,appello.getId()); 

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}
	
	
	public void check(Appello appello) {
		try {
			PreparedStatement ps = getConnection().prepareStatement("select * from mf_appello where id = ?");
			ps.setInt(1, appello.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				update(appello);	
			} else {
				add(appello);
			}
		} catch (SQLException ex) {
			System.out.println("Error in check() -->" + ex.getMessage());
		}
	}
	
	public void add(Appello appello) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO mf_appello "); 
		sb.append("(ad_id ,inizio_iscr ,fine_iscr ,data_appello ,ora ,tipo    ,docente_id ,cds_id ,facolta_id ) VALUES "); 
		sb.append("(?     ,?       	   ,?         ,?            ,?   ,?       ,?          ,?      ,?) ");
		//          1      2        	3      	   4             5    6        7     	   8       9           
		
		try {
			PreparedStatement stmt = getConnection().prepareStatement(sb.toString());
			
			stmtPara(stmt, 1, Types.INTEGER, appello.getAdId());
			stmtPara(stmt, 2, Types.DATE, 	 new java.sql.Date(appello.getInizioIscr().getTime()));
			stmtPara(stmt, 3, Types.DATE,    new java.sql.Date(appello.getFineIscr().getTime()));                              
			stmtPara(stmt, 4, Types.DATE,    new java.sql.Date(appello.getDataAppello().getTime()));                              
			stmtPara(stmt, 5, Types.VARCHAR, appello.getOra());
			stmtPara(stmt, 6, Types.VARCHAR, appello.getTipo());
			stmtPara(stmt, 7, Types.INTEGER, appello.getDocenteId());
			stmtPara(stmt, 8, Types.INTEGER, appello.getCdsId() );
			stmtPara(stmt, 9, Types.INTEGER, appello.getFacoltaId());
			//stmtPara(stmt, 10, Types.INTEGER, appello.getId());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//PER ESTRARRE L'ID DELL'ULTIMA RIGA INSERITA
		Integer lastId = lastRecordId();
		//INSERISCI L'ID DELL'ULTIMA RIGA INSERITA IN APP_TR_1
		updateAppTr1(lastId);
	}
	
	public void update(Appello appello) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE mf_appello SET "); 
		sb.append("ad_id=?, "); 			//1
		sb.append("inizio_iscr=?, "); 		//2
		sb.append("fine_iscr=?, "); 		//3
		sb.append("data_appello=?, "); 		//4
		sb.append("ora=?, "); 				//5
		sb.append("tipo=?, "); 				//6
		//sb.append("docente_id=?, "); 		//7
		sb.append("cds_id=?, "); 			//8
		sb.append("facolta_id=? "); 		//9
		sb.append("WHERE ");
		//sb.append("id = ? ");				//10
		sb.append("app_tr_1 = ? ");			//10
		
		try {
			
			PreparedStatement stmt = getConnection().prepareStatement(sb.toString());
			
			stmtPara(stmt, 1, Types.INTEGER, appello.getAdId());
			stmtPara(stmt, 2, Types.DATE, 	 new java.sql.Date(appello.getInizioIscr().getTime()));
			stmtPara(stmt, 3, Types.DATE,    new java.sql.Date(appello.getFineIscr().getTime()));                              
			stmtPara(stmt, 4, Types.DATE,    new java.sql.Date(appello.getDataAppello().getTime()));                              
			stmtPara(stmt, 5, Types.VARCHAR, appello.getOra());
			stmtPara(stmt, 6, Types.VARCHAR, appello.getTipo());
			//stmtPara(stmt, 7, Types.INTEGER, appello.getDocenteId());
			stmtPara(stmt, 7, Types.INTEGER, appello.getCdsId() );
			stmtPara(stmt, 8, Types.INTEGER, appello.getFacoltaId());
			//stmtPara(stmt, 10, Types.INTEGER, appello.getId());
			stmtPara(stmt, 9, Types.INTEGER, appello.getAppTr1());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateAppTr1(Integer lastId){
		
		StringBuffer sbb = new StringBuffer();
		sbb.append("UPDATE mf_appello SET ");
		sbb.append("app_tr_1=? ");   //1
		sbb.append("WHERE ");
		sbb.append("id = ? ");	   //2	
		try{
			PreparedStatement stmt = getConnection().prepareStatement(sbb.toString());
			stmtPara(stmt, 1, Types.INTEGER, lastId);
			stmtPara(stmt, 2, Types.INTEGER, lastId);
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	public List<Appello> getAll() {
//		List<Appello> retValue = new ArrayList<Appello>();
//
//		try {
//			Statement statement = getConnection().createStatement();
//			ResultSet rs = statement.executeQuery("select * from mf_appello");
//			while (rs.next()) {
//				retValue.add(assignBean(rs));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return retValue;
//	}
    
	public Integer lastRecordId() {
		Integer retValue = 0;
		try {
			Statement statement = getConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT MAX(id) FROM mf_appello");
			while (rs.next()) {
				retValue = rs.getInt("MAX(id)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retValue;
	}
	

	public Appello getById(int id) {
		return (Appello)super.getById(id);
	}
	
	Appello assignBean(ResultSet rs) throws SQLException {
		Appello retValue = new Appello();
		retValue.setId(rs.getInt("id"));
		retValue.setAdId(rs.getInt("ad_id"));
		retValue.setInizioIscr(rs.getDate("inizio_iscr"));
		retValue.setFineIscr(rs.getDate("fine_iscr"));
		retValue.setDataAppello(rs.getDate("data_appello"));
		retValue.setOra(rs.getString("ora"));
		retValue.setTipo(rs.getString("tipo"));
		retValue.setDocenteId(rs.getInt("docente_id"));
		retValue.setCdsId(rs.getInt("cds_id"));
		retValue.setFacoltaId(rs.getInt("facolta_id"));
		retValue.setTipoRecord(rs.getInt("tipo_record"));
		retValue.setAppTr1(rs.getInt("app_tr_1"));
		
		return retValue;
	}
	

	/*DEVO CREARE UNA CLASSE APPELLOLIST DOVE FACCIO GLI INNER JOIN TRA TUTTE LE TABELLE*/
	public List<AppelloList> getListaAppelli(Integer adId, Date dataAppello, String ora, String tipo, Integer docenteId, Integer cdsId, Integer facoltaId){
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("select a.id appello_id, a.ad_id, ad.descrizione ad_descrizione, ");
		sb.append("a.data_appello, a.ora, a.tipo, a.docente_id, ");
		sb.append("d.nome, d.cognome, a.cds_id, cds.descrizione cds_descrizione, a.facolta_id, f.descrizione f_descrizione, a.tipo_record ,a.app_tr_1 ");
		sb.append("from mf_appello a inner join mf_attivita_didattica ad on a.ad_id=ad.id ");
		sb.append("inner join mf_corso_di_studio cds on a.cds_id=cds.id ");
		sb.append("inner join mf_docente d on a.docente_id=d.id ");
		sb.append("inner join mf_facolta f on a.facolta_id=f.id ");
		sb.append("where ");
		sb.append("a.data_appello >= curDate() ");
		
		if(adId > 0){
		sb.append("and a.ad_id = " + adId + " ");
		}
//		if(inizioIscrizione != null)
//		sb.append("inizio_iscr = " + inizioIscrizione + " ");
//		
//		if(fineIscrizione != null)
//		sb.append("fine_iscr = " + fineIscrizione + " ");
		
		if(dataAppello != null ){
		sb.append("a.data_appello = " + dataAppello + " ");
		}
		if(ora != null && !ora.isEmpty()){
		sb.append("and lower(ora) like ('%" + ora.toLowerCase().replaceAll("'","''") + "%') ");
		//sb.append("and a.ora = " + ora + " ");
		}
		if(tipo != null && !tipo.isEmpty()){
		sb.append("and lower(tipo) like ('%" + tipo.toLowerCase().replaceAll("'","''") + "%') ");
		//sb.append("and a.tipo = " + tipo + " ");
		}
		if(docenteId > 0){
		sb.append("and a.docente_id = " + docenteId + " ");
		}
		if(cdsId > 0){
		sb.append("and a.cds_id = " + cdsId + " ");
		}
		if(facoltaId > 0){
		sb.append("and a.facolta_id = " + facoltaId + " ");
		}
		sb.append("ORDER BY a.app_tr_1 ASC, a.id ASC ");
		
		List<AppelloList> retValue = new ArrayList<AppelloList>();	
		
		try {
			
			PreparedStatement ps = getConnection().prepareStatement(sb.toString());
			
			ResultSet rs = ps.executeQuery();
			
			AppelloList appello = null;
			
			while (rs.next()) {
			appello = new AppelloList();
			
			appello.setAppelloId(rs.getInt("appello_id"));
			appello.setAdId(rs.getInt("ad_id"));
			appello.setAdDescrizione(rs.getString("ad_descrizione"));
//			appello.setInizioIscr(rs.getDate("inizio_iscr"));
//			appello.setFineIscr(rs.getDate("fine_iscr"));
			appello.setDataAppello(rs.getDate("data_appello"));
			appello.setOra(rs.getString("ora"));
			appello.setTipo(rs.getString("tipo"));
			appello.setDocenteId(rs.getInt("docente_id"));
			appello.setNome(rs.getString("nome"));
			appello.setCognome(rs.getString("cognome"));
			appello.setCdsId(rs.getInt("cds_id"));
			appello.setCdsDescrizione(rs.getString("cds_descrizione"));
			appello.setFacoltaId(rs.getInt("facolta_id"));
			appello.setFacoltaDescrizione(rs.getString("f_descrizione"));
			appello.setTipoRecord(rs.getInt("tipo_record"));
			appello.setAppTr1(rs.getInt("app_tr_1"));
			retValue.add(appello);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	return retValue;
		
	}

//	public TreeSet<Appello> getAllOrdered() {
//		return new TreeSet<Appello>(getAll());
//	}
	
}
