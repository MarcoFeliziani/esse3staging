package it.mf.model;


public class Docente implements Comparable<Docente> {

	private Integer id;
	private Integer ateneoId;
	private String nome;
	private String cognome;
	private String telefono;
	private String mail;
	private String psw;
	private String utente;
	private String ruolo;
	private String sesso;
	//private Ateneo ateneo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNomeCompleto() {
		return nome + " " + cognome;
	}

	
	public String getCognome() {
		return cognome;
	}
	public Integer getAteneoId() {
		return ateneoId;
	}
	public void setAteneoId(Integer ateneoId) {
		this.ateneoId = ateneoId;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public String getUtente() {
		return utente;
	}
	public void setUtente(String utente) {
		this.utente = utente;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
//	public Ateneo getAteneo() {
//		return ateneo;
//	}
//	public void setAteneo(Ateneo ateneo) {
//		this.ateneo = ateneo;
//	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sesso == null) ? 0 : sesso.hashCode());
		result = prime * result + ((utente == null) ? 0 : utente.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Docente other = (Docente) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sesso == null) {
			if (other.sesso != null)
				return false;
		} else if (!sesso.equals(other.sesso))
			return false;
		if (utente == null) {
			if (other.utente != null)
				return false;
		} else if (!utente.equals(other.utente))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Docente [nome=" + nome + ", cognome=" + cognome + ", telefono=" + telefono + "]";
	}
	@Override
	public int compareTo(Docente o) {
		
		int r = nome.compareToIgnoreCase(o.nome);
		if (r != 0)
			return r;
		return id-o.id;
	}
	
}
