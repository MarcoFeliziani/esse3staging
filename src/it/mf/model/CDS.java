package it.mf.model;

public class CDS implements Comparable<CDS> {

	private Integer id;
//	private Integer ateneoId;
//	private Integer facoltaId;
	private String descrizione;
	private String durata;
	private Integer crediti;
	
//	private Facolta facolta;
//	private Ateneo ateneo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
//	public Integer getAteneoId() {
//		return ateneoId;
//	}
//	public void setAteneoId(Integer ateneoId) {
//		this.ateneoId = ateneoId;
//	}
//	public Integer getFacoltaId() {
//		return facoltaId;
//	}
//	public void setFacoltaId(Integer facoltaId) {
//		this.facoltaId = facoltaId;
//	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getDurata() {
		return durata;
	}
	public void setDurata(String durata) {
		this.durata = durata;
	}
	public Integer getCrediti() {
		return crediti;
	}
	public void setCrediti(Integer crediti) {
		this.crediti = crediti;
	}
//	public Facolta getFacolta() {
//		return facolta;
//	}
//	public void setFacolta(Facolta facolta) {
//		this.facolta = facolta;
//	}
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
		CDS other = (CDS) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (durata == null) {
			if (other.durata != null)
				return false;
		} else if (!durata.equals(other.durata))
			return false;
		if (crediti == null) {
			if (other.crediti != null)
				return false;
		} else if (!crediti.equals(other.crediti))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CDS [descrizione=" + descrizione + ", durata=" + durata + ", crediti=" + crediti + "]";
	}
	@Override
	public int compareTo(CDS o) {
		
		int r = descrizione.compareToIgnoreCase(o.descrizione);
		if (r != 0)
			return r;
		return id-o.id;
	}
}

