package it.mf.modelView;

import java.util.Date;

public class AppelloList {
	
	Integer appelloId;
	Integer adId;
	String adDescrizione;
	Date inizioIscrizione;
	Date fineIscrizione;
	Date dataAppello;
	String ora;
	String tipo;
	Integer docenteId;
	String nome;
	String cognome;
	String nomeCompleto;
	Integer cdsId;
	String cdsDescrizione;
	Integer facoltaId;
	String facoltaDescrizione;
	Integer tipoRecord;
	Integer appTr1;
	
	
	public Integer getAppelloId() {
		return appelloId;
	}
	public void setAppelloId(Integer appelloId) {
		this.appelloId = appelloId;
	}
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public String getAdDescrizione() {
		return adDescrizione;
	}
	public void setAdDescrizione(String adDescrizione) {
		this.adDescrizione = adDescrizione;
	}
	public Date getInizioIscrizione() {
		return inizioIscrizione;
	}
	public void setInizioIscrizione(Date inizioIscrizione) {
		this.inizioIscrizione = inizioIscrizione;
	}
	public Date getFineIscrizione() {
		return fineIscrizione;
	}
	public void setFineIscrizione(Date fineIscrizione) {
		this.fineIscrizione = fineIscrizione;
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNomeCompleto() {
		return nome + " " + cognome;
	}
	public Integer getCdsId() {
		return cdsId;
	}
	public void setCdsId(Integer cdsId) {
		this.cdsId = cdsId;
	}
	public String getCdsDescrizione() {
		return cdsDescrizione;
	}
	public void setCdsDescrizione(String cdsDescrizione) {
		this.cdsDescrizione = cdsDescrizione;
	}
	public Integer getFacoltaId() {
		return facoltaId;
	}
	public void setFacoltaId(Integer facoltaId) {
		this.facoltaId = facoltaId;
	}
	public String getFacoltaDescrizione() {
		return facoltaDescrizione;
	}
	public void setFacoltaDescrizione(String facoltaDescrizione) {
		this.facoltaDescrizione = facoltaDescrizione;
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
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppelloList other = (AppelloList) obj;
		if (adDescrizione == null) {
			if (other.adDescrizione != null)
				return false;
		} else if (!adDescrizione.equals(other.adDescrizione))
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
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (cdsDescrizione == null) {
			if (other.cdsDescrizione != null)
				return false;
		} else if (!cdsDescrizione.equals(other.cdsDescrizione))
			return false;
		if (facoltaDescrizione == null) {
			if (other.facoltaDescrizione != null)
				return false;
		} else if (!facoltaDescrizione.equals(other.facoltaDescrizione))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AppelloList [appelloId=" + appelloId + ", adId=" + adId + ", adDescrizione=" + adDescrizione + ", dataAppello=" + dataAppello + 
				", ora=" + ora + ", tipo=" + tipo + ", docenteId=" + docenteId + ", cdsId=" + cdsId + ", facoltaId=" + facoltaId + "]";
	}
}

