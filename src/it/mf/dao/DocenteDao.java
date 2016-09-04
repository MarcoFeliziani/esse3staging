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
import it.mf.model.Docente;

public class DocenteDao extends Dao {
	
	private static final String TABLE_NAME = "mf_docente";

	public DocenteDao() {
		super();
	}
	
	@Override
	String getTableName() {
		return TABLE_NAME;
	}

	public void check(Docente docente) {
		try {
			PreparedStatement ps = getConnection().prepareStatement("select nome from mf_docente where id = ? ");
			ps.setInt(1, docente.getId());
//			ps.setInt(2, docente.getAteneoId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				update(docente);	
			} else {
				add(docente);
			}
		} catch (SQLException ex) {
			System.out.println("Error in check() -->" + ex.getMessage());
		}
	}

	public void add(Docente docente) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO mf_docente "); 
		sb.append("(nome ,cognome ,telefono  ,mail ,psw ,utente ,ruolo  ,sesso) VALUES "); 
		sb.append("(?    ,?       ,?     	 ,?    ,?   ,?      ,?      ,? ) ");
		//          1     2        3          4     5    6       7       8       

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sb.toString());
			//stmtPara(stmt, 1, Types.INTEGER, docente.getAteneoId());
			stmtPara(stmt, 1, Types.VARCHAR, docente.getNome());
			stmtPara(stmt, 2, Types.VARCHAR, docente.getCognome());
			stmtPara(stmt, 3, Types.VARCHAR, docente.getTelefono());
			stmtPara(stmt, 4, Types.VARCHAR, docente.getMail());
			stmtPara(stmt, 5, Types.VARCHAR, docente.getPsw());
			stmtPara(stmt, 6, Types.VARCHAR, docente.getUtente());
			stmtPara(stmt, 7, Types.VARCHAR, docente.getRuolo());
			stmtPara(stmt, 8, Types.VARCHAR, docente.getSesso());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Docente docente) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE mf_docente SET "); 
		sb.append("nome=?, "); 			//1
		sb.append("cognome=?, "); 		//2
		sb.append("telefono=?, "); 		//3
		sb.append("mail=?, "); 			//4
		sb.append("psw=?, "); 			//5
		sb.append("utente=?, "); 		//6
		sb.append("ruolo=?, "); 		//7
		sb.append("sesso=? "); 			//8
		sb.append("WHERE ");
		sb.append("id = ? ");			//9
//		sb.append("and ateneo_id=? "); //10
		
		try {
			
			PreparedStatement stmt = getConnection().prepareStatement(sb.toString());
			
			stmtPara(stmt, 1, Types.VARCHAR, docente.getNome());
			stmtPara(stmt, 2, Types.VARCHAR, docente.getCognome());
			stmtPara(stmt, 3, Types.VARCHAR, docente.getTelefono());
			stmtPara(stmt, 4, Types.VARCHAR, docente.getMail());
			stmtPara(stmt, 5, Types.VARCHAR, docente.getPsw());
			stmtPara(stmt, 6, Types.VARCHAR, docente.getUtente());
			stmtPara(stmt, 7, Types.VARCHAR, docente.getRuolo());
			stmtPara(stmt, 8, Types.VARCHAR, docente.getSesso());
			stmtPara(stmt, 9, Types.INTEGER, docente.getId());
//			stmtPara(stmt, 10, Types.INTEGER, docente.getAteneoId());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Docente> getAll() {
		List<Docente> retValue = new ArrayList<Docente>();
		
//		AteneoDao adao = new AteneoDao();
//		Hashtable<Integer, Ateneo> htAteneo = adao.getAllHt();
		
		StringBuffer sb = new StringBuffer();
		sb.append("select "); 
		sb.append("* "); 
		sb.append("from mf_docente "); 
//		sb.append("inner join mf_ateneo a on d.ateneo_id= a.id "); 
		sb.append("order by nome, cognome, ruolo ");

		try {
			Statement statement = getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sb.toString());
			while (rs.next()) {
				Docente docente = assignBean(rs);
//				docente.setAteneo(htAteneo.get(docente.getAteneoId()));
				retValue.add(docente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retValue;
	}

	/**
	 * 
	 * @return Hashtable<Integer, Docente> tutte i docenti 
	 */
	public Hashtable<Integer, Docente> getAllHt() {
		List<Docente> people = getAll();
		Hashtable<Integer, Docente> retValue = new Hashtable<Integer, Docente>(people.size()); 
		for (Docente docente : people) 
			retValue.put(docente.getId(), docente);

		return retValue;
	}

	
	/**
	 * 
	 * @return il primo ammistratore
	 */
	public Docente getOneAmministratore() {
		Docente retValue = null;
		
		try {
			Statement statement = getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from mf_docente where ruolo = 'A' order by id");
			if (rs.next()) 
				retValue = assignBean(rs);				
			
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retValue;
	}
	


	public Docente getById(int id) {
		return (Docente)super.getById(id);
	}
	
//	public Docente getByIdTwo(int id, int ateneoId) {
//		return (Docente)super.getByIdTwo(id,ateneoId);
//	}
	
	Docente assignBean(ResultSet rs) throws SQLException {
		Docente retValue = new Docente();
		retValue.setId(rs.getInt("id"));
//		retValue.setAteneoId(rs.getInt("ateneo_id"));
		retValue.setNome(rs.getString("nome"));
		retValue.setCognome(rs.getString("cognome"));
		retValue.setTelefono(rs.getString("telefono"));
		retValue.setMail(rs.getString("mail"));
		retValue.setPsw(rs.getString("psw"));
		retValue.setUtente(rs.getString("utente"));
		retValue.setRuolo(rs.getString("ruolo"));
		retValue.setSesso(rs.getString("sesso"));
		return retValue;
	}

	public TreeSet<Docente> getAllOrdered() {
		return new TreeSet<Docente>(getAll());
	}

}
