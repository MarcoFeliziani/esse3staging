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
//import it.mf.model.CDS;
import it.mf.model.AD;
//import it.mf.model.Facolta;

public class ADDao extends Dao {
	
	private static final String TABLE_NAME = "mf_attivita_didattica";
	
	public ADDao() {
		super();
	}
	
	@Override
	String getTableName() {
		return TABLE_NAME;
	}
	
	public void check(AD ad) {
		try {
			PreparedStatement ps = getConnection().prepareStatement("select descrizione from mf_attivita_didattica where id=? ");
			ps.setInt(1, ad.getId());
//			ps.setInt(2, ad.getCdsId());
//			ps.setInt(3, ad.getFacoltaId());
//			ps.setInt(4, ad.getAteneoId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				update(ad);	
			} else {
				add(ad);
			}
		} catch (SQLException ex) {
			System.out.println("Error in check() -->" + ex.getMessage());
		}
	}
	
	public void add(AD ad) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO mf_attivita_didattica "); 
		sb.append("(descrizione ,crediti) VALUES "); 
		sb.append("(?      		,?      ) ");
		//          1     		 2           

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sb.toString());
//			stmtPara(stmt, 1, Types.INTEGER, ad.getCdsId());
//			stmtPara(stmt, 2, Types.INTEGER, ad.getFacoltaId());
//			stmtPara(stmt, 3, Types.INTEGER, ad.getAteneoId());
			stmtPara(stmt, 1, Types.VARCHAR, ad.getDescrizione());
			stmtPara(stmt, 2, Types.INTEGER, ad.getCrediti());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(AD ad) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE mf_attivita_didattica SET "); 
		sb.append("descrizione=?, "); 		//1
		sb.append("crediti=? ");			//2
		sb.append("WHERE ");
		sb.append("id = ? ");				//3
//		sb.append("and cds_id=? "); 		//4
//		sb.append("and facolta_id=? "); 	//5
//		sb.append("and ateneo_id=? ");		//6
		
		try {
			
			PreparedStatement stmt = getConnection().prepareStatement(sb.toString());
			
			stmtPara(stmt, 1, Types.VARCHAR, ad.getDescrizione());
			stmtPara(stmt, 2, Types.INTEGER, ad.getCrediti());
			stmtPara(stmt, 3, Types.INTEGER, ad.getId());
//			stmtPara(stmt, 4, Types.INTEGER, ad.getCdsId());
//			stmtPara(stmt, 5, Types.INTEGER, ad.getFacoltaId());
//			stmtPara(stmt, 6, Types.INTEGER, ad.getAteneoId());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<AD> getAll() {
		List<AD> retValue = new ArrayList<AD>();
		
//		AteneoDao adao = new AteneoDao();
//		FacoltaDao fdao = new FacoltaDao();
//		CDSDao cdsdao = new CDSDao();
//		Hashtable<Integer, Ateneo> htAteneo = adao.getAllHt();
//		Hashtable<Integer, Facolta> htFacolta = fdao.getAllFa();
//		Hashtable<Integer, CDS> htCds = cdsdao.getAllCds();
		
		StringBuffer sb = new StringBuffer();
		sb.append("select "); 
		sb.append("* "); 
		sb.append("from mf_attivita_didattica "); 
//		sb.append("inner join mf_facolta f on f.id = ad.facolta_id  "); 
//		sb.append("inner join mf_ateneo a on a.id = ad.ateneo_id  ");
//		sb.append("inner join mf_corso_di_studio cds on cds.id = ad.cds_id  ");
		sb.append("order by descrizione, crediti ");

		try {
			Statement statement = getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sb.toString());
			while (rs.next()) {
				AD ad = assignBean(rs);
//				ad.setAteneo(htAteneo.get(ad.getAteneoId()));
//				ad.setFacolta(htFacolta.get(ad.getFacoltaId()));
//				ad.setCds(htCds.get(ad.getCdsId()));
				retValue.add(ad);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retValue;
	}
	
	public AD getById(int id) {
		return (AD)super.getById(id);
	}
	
	/*public AD getByIdAD(int id, int ateneoId, int facoltaId, int cdsId) {
		return (AD)super.getByIdAD(id, ateneoId, facoltaId, cdsId);
	}*/
	
	AD assignBean(ResultSet rs) throws SQLException {
		AD retValue = new AD();
		retValue.setId(rs.getInt("id"));
//		retValue.setCdsId(rs.getInt("cds_id"));
//		retValue.setFacoltaId(rs.getInt("facolta_id"));
//		retValue.setAteneoId(rs.getInt("ateneo_id"));
		retValue.setDescrizione(rs.getString("descrizione"));
		retValue.setCrediti(rs.getInt("crediti"));
		return retValue;
	}

	public TreeSet<AD> getAllOrdered() {
		return new TreeSet<AD>(getAll());
	}

}
