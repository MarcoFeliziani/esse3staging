package it.mf.model;

import java.util.Date;



public class Appello implements Comparable<Appello> {
	
	private Integer id; 
	private Integer adId;
	private Date inizioIscr;
	private Date fineIscr;
	private Date dataAppello;
	private String ora;
	private String tipo;
	private Integer docenteId;
	private Integer cdsId;
	private Integer facoltaId;
	private Integer tipoRecord;
	private Integer appTr1;
	private AD ad;
	private Docente docente;
	private CDS cds;
	private Facolta facolta;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public Date getInizioIscr() {
		return inizioIscr;
	}
	public void setInizioIscr(Date inizioIscr) {
		this.inizioIscr = inizioIscr;
	}
	public Date getFineIscr() {
		return fineIscr;
	}
	public void setFineIscr(Date fineIscr) {
		this.fineIscr = fineIscr;
	}
	public Date getDataAppello() {
		return dataAppello;
	}
	public void setDataAppello(Date dataAppello) {
		this.dataAppello = dataAppello;
	}
	public String getOra() {
		return ora;
	}
	public void setOra(String ora) {
		this.ora = ora;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getDocenteId() {
		return docenteId;
	}
	public void setDocenteId(Integer docenteId) {
		this.docenteId = docenteId;
	}
	public Integer getCdsId() {
		return cdsId;
	}
	public void setCdsId(Integer cdsId) {
		this.cdsId = cdsId;
	}
	public Integer getFacoltaId() {
		return facoltaId;
	}
	public void setFacoltaId(Integer facoltaId) {
		this.facoltaId = facoltaId;
	}
	public Integer getTipoRecord() {
		return tipoRecord;
	}
	public void setTipoRecord(Integer tipoRecord) {
		this.tipoRecord = tipoRecord;
	}
	public Integer getAppTr1() {
		return appTr1;
	}
	public void setAppTr1(Integer appTr1) {
		this.appTr1 = appTr1;
	}
	public AD getAd() {
		return ad;
	}
	public void setAd(AD ad) {
			this.ad = ad;
	}
	public Docente getDocente() {
		return docente;
	}
	public void setAd(Docente docente) {
			this.docente = docente;
	}
	public CDS getCds() {
		return cds;
	}
	public void setCDS(CDS cds) {
			this.cds = cds;
	}
	public Facolta getFacolta() {
		return facolta;
	}
	public void setFacolta(Facolta facolta) {
			this.facolta = facolta;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adId == null) ? 0 : adId.hashCode());
		result = prime * result + ((inizioIscr == null) ? 0 : inizioIscr.hashCode());
		result = prime * result + ((fineIscr == null) ? 0 : fineIscr.hashCode());
		result = prime * result + ((dataAppello == null) ? 0 : dataAppello.hashCode());
		result = prime * result + ((ora == null) ? 0 : ora.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((docenteId == null) ? 0 : docenteId.hashCode());
		result = prime * result + ((cdsId == null) ? 0 : cdsId.hashCode());
		result = prime * result + ((facoltaId == null) ? 0 : facoltaId.hashCode());
		result = prime * result + ((appTr1 == null) ? 0 : appTr1.hashCode());
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
		Appello other = (Appello) obj;
		if (adId == null) {
			if (other.adId != null)
				return false;
		} else if (!adId.equals(other.adId))
			return false;
		if (inizioIscr == null) {
			if (other.inizioIscr != null)
				return false;
		} else if (!inizioIscr.equals(other.inizioIscr))
			return false;
		if (fineIscr == null) {
			if (other.fineIscr != null)
				return false;
		} else if (!fineIscr.equals(other.fineIscr))
			return false;
		if (dataAppello == null) {
			if (other.dataAppello != null)
				return false;
		} else if (!dataAppello.equals(other.dataAppello))
			return false;
		if (ora == null) {
			if (other.ora != null)
				return false;
		} else if (!ora.equals(other.ora))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (docenteId == null) {
			if (other.docenteId != null)
				return false;
		} else if (!docenteId.equals(other.docenteId))
			return false;
		if (cdsId == null) {
			if (other.cdsId != null)
				return false;
		} else if (!cdsId.equals(other.cdsId))
			return false;
		if (facoltaId == null) {
			if (other.facoltaId != null)
				return false;
		} else if (!facoltaId.equals(other.facoltaId))
			return false;
		if (tipoRecord == null) {
			if (other.tipoRecord != null)
				return false;
		} else if (!tipoRecord.equals(other.tipoRecord))
			return false;
		if (appTr1 == null) {
			if (other.appTr1 != null)
				return false;
		} else if (!appTr1.equals(other.appTr1))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Appello [adId=" + adId + ", id=" + id + ", inizioIscr=" + inizioIscr + ", fineIscr="
				+ fineIscr + ", dataAppello=" + dataAppello + ", ora=" + ora
				+ ", tipo=" + tipo + ", docenteId=" + docenteId
				+ ", cdsId=" + cdsId + ", facoltaId=" + facoltaId + ", tipoRecord=" + tipoRecord + ", appTr1=" + appTr1 + "]";
	}
	@Override
	public int compareTo(Appello o) {
		
		int r = tipo.compareToIgnoreCase(o.tipo);
		if (r != 0)
			return r;
		return id-o.id;
	}

}
