package it.mf.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.TreeSet;

import it.mf.model.Ateneo;

public class AteneoDao extends Dao{

	private static final String TABLE_NAME = "mf_ateneo";
	
	public AteneoDao() {
		super();
	}
	
	@Override
	String getTableName() {
		return TABLE_NAME;
	}
	
	public void check(Ateneo ateneo) {
		try {
			PreparedStatement ps = getConnection().prepareStatement("select descrizione from mf_ateneo where id = ?");
			ps.setInt(1, ateneo.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				update(ateneo);	
			} else {
				add(ateneo);
			}
		} catch (SQLException ex) {
			System.out.println("Error in check() -->" + ex.getMessage());
		}
	}
	
	public void add(Ateneo ateneo) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO mf_ateneo "); 
		sb.append("(descrizione ,citta ,via  ,prov ,telefono ,email) VALUES "); 
		sb.append("(?    		,?     ,?    ,?    ,?   	 ,?) ");
		//          1     		 2      3     4     5    	  6             

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sb.toString());
			stmtPara(stmt, 1, Types.VARCHAR, ateneo.getDescrizione());
			stmtPara(stmt, 2, Types.VARCHAR, ateneo.getCitta());
			stmtPara(stmt, 3, Types.VARCHAR, ateneo.getVia());
			stmtPara(stmt, 4, Types.VARCHAR, ateneo.getProv());
			stmtPara(stmt, 5, Types.VARCHAR, ateneo.getTelefono());
			stmtPara(stmt, 6, Types.VARCHAR, ateneo.getEmail());


			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Ateneo ateneo) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE mf_ateneo SET "); 
		sb.append("descrizione=?, "); 			//1
		sb.append("citta=?, "); 				//2
		sb.append("via=?, "); 					//3
		sb.append("prov=?, "); 					//4
		sb.append("telefono=?, "); 				//5
		sb.append("email=? "); 				//6
		sb.append("WHERE ");
		sb.append("id = ? ");					//7
		
		try {
			
			PreparedStatement stmt = getConnection().prepareStatement(sb.toString());
			
			stmtPara(stmt, 1, Types.VARCHAR, ateneo.getDescrizione());
			stmtPara(stmt, 2, Types.VARCHAR, ateneo.getCitta());
			stmtPara(stmt, 3, Types.VARCHAR, ateneo.getVia());
			stmtPara(stmt, 4, Types.VARCHAR, ateneo.getProv());
			stmtPara(stmt, 5, Types.VARCHAR, ateneo.getTelefono());
			stmtPara(stmt, 6, Types.VARCHAR, ateneo.getEmail());
			stmtPara(stmt, 7, Types.INTEGER, ateneo.getId());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Ateneo> getAll() {
		List<Ateneo> retValue = new ArrayList<Ateneo>();

		try {
			Statement statement = getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from mf_ateneo");
			while (rs.next()) {
			retValue.add(assignBean(rs));
		}
		} catch (SQLException e) {
			e.printStackTrace();
	}

	return retValue;
	}
	
	/**
	 * 
	 * @return Hashtable<Integer, Ateneo> tutte gli atenei 
	 */
	public Hashtable<Integer, Ateneo> getAllHt() {
		List<Ateneo> atenei = getAll();
		Hashtable<Integer, Ateneo> retValue = new Hashtable<Integer, Ateneo>(atenei.size()); 
		for (Ateneo one : atenei) 
			retValue.put(one.getId(), one);

		return retValue;
	}
	
	public Ateneo getById(int id) {
		return (Ateneo)super.getById(id);
	}
	
	Ateneo assignBean(ResultSet rs) throws SQLException {
		Ateneo retValue = new Ateneo();
		retValue.setId(rs.getInt("id"));
		retValue.setDescrizione(rs.getString("descrizione"));
		retValue.setCitta(rs.getString("citta"));
		retValue.setVia(rs.getString("via"));
		retValue.setProv(rs.getString("prov"));
		retValue.setTelefono(rs.getString("telefono"));
		retValue.setEmail(rs.getString("email"));
		return retValue;
	}

	public TreeSet<Ateneo> getAllOrdered() {
		return new TreeSet<Ateneo>(getAll());
	}

	
}
