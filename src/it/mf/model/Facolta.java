package it.mf.model;

public class Facolta implements Comparable<Facolta> {

	private Integer id;
	//private Integer ateneoId;
	private String descrizione;
	private String telefono;
	private String email;
	//private Ateneo ateneo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
//	public Integer getAteneoId() {
//		return ateneoId;
//	}
//	public void setAteneoId(Integer ateneoId) {
//		this.ateneoId = ateneoId;
//	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Facolta other = (Facolta) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Facolta [descrizione=" + descrizione + ", telefono=" + telefono + ", email=" + email + "]";
	}
	@Override
	public int compareTo(Facolta o) {
		
		int r = descrizione.compareToIgnoreCase(o.descrizione);
		if (r != 0)
			return r;
		return id-o.id;
	}
}
