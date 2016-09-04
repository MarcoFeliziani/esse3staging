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


//import it.mf.model.Ateneo;
import it.mf.model.Facolta;

public class FacoltaDao extends Dao {
	
	private static final String TABLE_NAME = "mf_facolta";

	public FacoltaDao() {
		super();
	}
	
	@Override
	String getTableName() {
		return TABLE_NAME;
	}

	public void check(Facolta facolta) {
		try {
			PreparedStatement ps = getConnection().prepareStatement("select descrizione from mf_facolta where id = ? ");
			ps.setInt(1, facolta.getId());
//			ps.setInt(2, facolta.getAteneoId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				update(facolta);	
			} else {
				add(facolta);
			}
		} catch (SQLException ex) {
			System.out.println("Error in check() -->" + ex.getMessage());
		}
	}

	public void add(Facolta facolta) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO mf_facolta "); 
		sb.append("(descrizione ,telefono  ,email ) VALUES "); 
		sb.append("(?    	    ,?    	   ,?     ) ");
		//          1     	     2          3                    

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sb.toString());
//			stmtPara(stmt, 1, Types.INTEGER, facolta.getAteneoId());
			stmtPara(stmt, 1, Types.VARCHAR, facolta.getDescrizione());
			stmtPara(stmt, 2, Types.VARCHAR, facolta.getTelefono());
			stmtPara(stmt, 3, Types.VARCHAR, facolta.getEmail());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Facolta facolta) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE mf_facolta SET "); 
		sb.append("descrizione=?, "); 			//1
		sb.append("telefono=?, "); 		//2
		sb.append("email=? "); 			//3
		sb.append("WHERE ");
		sb.append("id = ? ");			//4
//		sb.append("and ateneo_id=? "); //5
		
		try {
			
			PreparedStatement stmt = getConnection().prepareStatement(sb.toString());
			
			stmtPara(stmt, 1, Types.VARCHAR, facolta.getDescrizione());
			stmtPara(stmt, 2, Types.VARCHAR, facolta.getTelefono());
			stmtPara(stmt, 3, Types.VARCHAR, facolta.getEmail());
			stmtPara(stmt, 4, Types.INTEGER, facolta.getId());
//			stmtPara(stmt, 5, Types.INTEGER, facolta.getAteneoId());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Facolta> getAll() {
		List<Facolta> retValue = new ArrayList<Facolta>();
		
		//AteneoDao adao = new AteneoDao();
		//Hashtable<Integer, Ateneo> htAteneo = adao.getAllHt();
		
		StringBuffer sb = new StringBuffer();
		sb.append("select "); 
		sb.append("* "); 
		sb.append("from mf_facolta "); 
//		sb.append("inner join mf_ateneo a on f.ateneo_id= a.id "); 
		sb.append("order by descrizione, telefono, email ");

		try {
			Statement statement = getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sb.toString());
			while (rs.next()) {
				Facolta facolta = assignBean(rs);
				//facolta.setAteneo(htAteneo.get(facolta.getAteneoId()));
				retValue.add(facolta);
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
	public Hashtable<Integer, Facolta> getAllFa() {
		List<Facolta> faculties = getAll();
		Hashtable<Integer, Facolta> retValue = new Hashtable<Integer, Facolta>(faculties.size()); 
		for (Facolta one : faculties) 
			retValue.put(one.getId(), one);

		return retValue;
	}


	public Facolta getById(int id) {
		return (Facolta)super.getById(id);
	}
	
//	public Facolta getByIdTwo(int id, int ateneoId) {
//		return (Facolta)super.getByIdTwo(id,ateneoId);
//	}
	
	Facolta assignBean(ResultSet rs) throws SQLException {
		Facolta retValue = new Facolta();
		retValue.setId(rs.getInt("id"));
//		retValue.setAteneoId(rs.getInt("ateneo_id"));
		retValue.setDescrizione(rs.getString("descrizione"));
		retValue.setTelefono(rs.getString("telefono"));
		retValue.setEmail(rs.getString("email"));
		return retValue;
	}

	public TreeSet<Facolta> getAllOrdered() {
		return new TreeSet<Facolta>(getAll());
	}

}
