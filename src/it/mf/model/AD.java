package it.mf.model;

public class AD implements Comparable<AD> {
	
	private Integer id;
	//private Integer cdsId;
	//private Integer ateneoId;
	//private Integer facoltaId;
	private String descrizione;
	private Integer crediti;
	//private CDS cds;
	//private Facolta facolta;
	//private Ateneo ateneo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	/*public Integer getCdsId() {
		return cdsId;
	}
	public void setCdsId(Integer cdsId) {
		this.cdsId = cdsId;
	}
	public Integer getAteneoId() {
		return ateneoId;
	}
	public void setAteneoId(Integer ateneoId) {
		this.ateneoId = ateneoId;
	}
	public Integer getFacoltaId() {
		return facoltaId;
	}
	public void setFacoltaId(Integer facoltaId) {
		this.facoltaId = facoltaId;
	}*/
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Integer getCrediti() {
		return crediti;
	}
	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}
	/*public Integer getDocenteId() {
		return docenteId;
	}
	public void setDocenteId(int docenteId) {
		this.docenteId = docenteId;
	}
	public Facolta getFacolta() {
		return facolta;
	}
	public void setFacolta(Facolta facolta) {
		this.facolta = facolta;
	}
	public Ateneo getAteneo() {
		return ateneo;
	}
	public void setAteneo(Ateneo ateneo) {
		this.ateneo = ateneo;
	}
	public CDS getCds() {
		return cds;
	}
	public void setCds(CDS cds) {
		this.cds = cds;
	}*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((crediti == null) ? 0 : crediti.hashCode());
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
		AD other = (AD) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
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
		return "CDS [descrizione=" + descrizione + ", crediti=" + crediti + ", id=" + id +  "]";
	}
	
	@Override
	public int compareTo(AD o) {
		
		int r = descrizione.compareToIgnoreCase(o.descrizione);
		if (r != 0)
			return r;
		return id-o.id;
	}

}
