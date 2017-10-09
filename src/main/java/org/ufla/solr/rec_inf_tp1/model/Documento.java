package org.ufla.solr.rec_inf_tp1.model;

import java.io.Serializable;

import org.apache.solr.common.SolrInputDocument;

/**
 * Documento formatado da forma definida para o trabalho prático de Recuperação
 * da Informação.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class Documento implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Identificador do documento.
	 */
	private Integer recordNumber; // RN
	/**
	 * Conteúdo do documento.
	 */
	private String conteudo; // CONT

	/**
	 * Cria uma representação de documento do Solr com os atributos deste
	 * documento.
	 * 
	 * @return representação de documento do Solr com os atributos deste
	 *         documento.
	 */
	public SolrInputDocument toSolrInputDocument() {
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField(MetaAtributoDocumento.RN.getNome(), recordNumber);
		doc.setField(MetaAtributoDocumento.CONT.getNome(), conteudo);
		return doc;
	}

	public Documento() {

	}

	public Documento(Integer recordNumber, String conteudo) {
		this.recordNumber = recordNumber;
		this.conteudo = conteudo;
	}

	public Integer getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(Integer recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
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
		Documento other = (Documento) obj;
		if (recordNumber == null) {
			if (other.recordNumber != null)
				return false;
		} else if (!recordNumber.equals(other.recordNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Documento [recordNumber=" + recordNumber + ", conteudo=" + conteudo + "]";
	}

}
