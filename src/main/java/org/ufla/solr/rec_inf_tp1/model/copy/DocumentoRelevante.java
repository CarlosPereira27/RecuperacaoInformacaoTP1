package org.ufla.solr.rec_inf_tp1.model.copy;

import java.io.Serializable;

/**
 * Representa um documento relevante para uma consulta.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class DocumentoRelevante implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer recordNumber;
	private String relevanceScore;

	public DocumentoRelevante() {

	}

	public DocumentoRelevante(Integer recordNumber, String relevanceScore) {
		this.recordNumber = recordNumber;
		this.relevanceScore = relevanceScore;
	}

	public Integer getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(Integer recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getRelevanceScore() {
		return relevanceScore;
	}

	public void setRelevanceScore(String relevanceScore) {
		this.relevanceScore = relevanceScore;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((recordNumber == null) ? 0 : recordNumber.hashCode());
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
		DocumentoRelevante other = (DocumentoRelevante) obj;
		if (recordNumber == null) {
			if (other.recordNumber != null)
				return false;
		} else if (!recordNumber.equals(other.recordNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("{\n" + "\t\"recordNumber\":%d,\n" + "\t\"relevanceScore\":%s\n}", recordNumber,
				relevanceScore);
	}

}
