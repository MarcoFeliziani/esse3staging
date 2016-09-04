package it.mf.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
//import java.util.Hashtable;
import java.util.List;
import java.util.TreeSet;

//import it.mf.model.Ateneo;
import it.mf.model.CDS;
//import it.mf.model.Facolta;



public class CDSDao extends Dao {
	
	private static final String TABLE_NAME = "mf_corso_di_studio";
	
	public CDSDao() {
		super();
	}
	
	@Override
	String getTableName() {
		return TABLE_NAME;
	}
	
	public void check(CDS cds) {
		try {
			PreparedStatement ps = getConnection().prepareStatement("select descrizione from mf_corso_di_studio where id=? ");
			ps.setInt(1, cds.getId());
//			ps.setInt(2, cds.getFacoltaId());
//			ps.setInt(3, cds.getAteneoId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				update(cds);	
			} else {
				add(cds);
			}
		} catch (SQLException ex) {
			System.out.println("Error in check() -->" + ex.getMessage());
		}
	}
	
	public void add(CDS cds) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO mf_corso_di_studio "); 
		sb.append("(descrizione ,durata ,crediti) VALUES "); 
		sb.append("(?    	    ,?      ,?) ");
		//          1     		 2       3                 

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sb.toString());
			stmtPara(stmt, 1, Types.VARCHAR, cds.getDescrizione());
			stmtPara(stmt, 2, Types.VARCHAR, cds.getDurata());
			stmtPara(stmt, 3, Types.INTEGER, cds.getCrediti());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(CDS cds) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE mf_corso_di_studio SET "); 
		sb.append("descrizione=?, "); 			//1
		sb.append("durata=?, "); 			//2
		sb.append("crediti=? "); 			//3
		sb.append("WHERE ");
		sb.append("id = ? ");			//4
//		sb.append("and ateneo_id=? "); //3
//		sb.append("and facolta_id=? "); //4
		
		try {
			
			PreparedStatement stmt = getConnection().prepareStatement(sb.toString());
			
			stmtPara(stmt, 1, Types.VARCHAR, cds.getDescrizione());
			stmtPara(stmt, 2, Types.VARCHAR, cds.getDurata());
			stmtPara(stmt, 3, Types.INTEGER, cds.getCrediti());
			stmtPara(stmt, 4, Types.INTEGER, cds.getId());
//			stmtPara(stmt, 3, Types.INTEGER, cds.getAteneoId());
//			stmtPara(stmt, 4, Types.INTEGER, cds.getFacoltaId());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<CDS> getAll() {
		List<CDS> retValue = new ArrayList<CDS>();
		
//		AteneoDao adao = new AteneoDao();
//		FacoltaDao fdao = new FacoltaDao();
//		Hashtable<Integer, Ateneo> htAteneo = adao.getAllHt();
//		Hashtable<Integer, Facolta> htFacolta = fdao.getAllFa();
		
		StringBuffer sb = new StringBuffer();
		sb.append("select "); 
		sb.append("cds.* "); 
		sb.append("from mf_corso_di_studio cds "); 
		sb.append("order by cds.descrizione, cds.durata, cds.crediti");
//		sb.append("inner join mf_facolta f on f.id = cds.facolta_id  "); 
//		sb.append("inner join mf_ateneo a on a.id = cds.ateneo_id  ");
//		sb.append("order by cds.descrizione, cds.ateneo_id, cds.facolta_id ");

		try {
			Statement statement = getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sb.toString());
			while (rs.next()) {
				CDS cds = assignBean(rs);
//				cds.setAteneo(htAteneo.get(cds.getAteneoId()));
//				cds.setFacolta(htFacolta.get(cds.getFacoltaId()));
				retValue.add(cds);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retValue;
	}
	
	/**
	 * 
	 * @return Hashtable<Integer, Facolta> tutte le facoltà
	 */
//	public Hashtable<Integer, CDS> getAllCds() {
//		List<CDS> course = getAll();
//		Hashtable<Integer, CDS> retValue = new Hashtable<Integer, CDS>(course.size()); 
//		for (CDS one : course) 
//			retValue.put(one.getId(), one);
//
//		return retValue;
//	}
	
	public CDS getById(int id) {
		return (CDS)super.getById(id);
	}
	
//	public CDS getByIdCDS(int id, int ateneoId, int facoltaId) {
//		return (CDS)super.getByIdCDS(id,ateneoId, facoltaId);
//	}
	
	CDS assignBean(ResultSet rs) throws SQLException {
		CDS retValue = new CDS();
		retValue.setId(rs.getInt("id"));
//		retValue.setFacoltaId(rs.getInt("facolta_id"));
//		retValue.setAteneoId(rs.getInt("ateneo_id"));
		retValue.setDescrizione(rs.getString("descrizione"));
		retValue.setDurata(rs.getString("durata"));
		retValue.setCrediti(rs.getInt("crediti"));
		return retValue;
	}

	public TreeSet<CDS> getAllOrdered() {
		return new TreeSet<CDS>(getAll());
	}

}
