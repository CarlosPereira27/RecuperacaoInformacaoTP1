package org.ufla.solr.rec_inf_tp1.model;

import java.io.Serializable;

import org.apache.solr.common.SolrInputDocument;
import org.ufla.solr.rec_inf_tp1.utils.StringUtils;

/**
 * Documento da forma original em que é extraído da base de dados estática.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class DocumentoOriginal implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer paperNumber; // PN
	private Integer recordNumber; // RN
	private Long medlineAcessionNumber; // AN
	private String author; // AU
	private String title; // TI
	private String source; // SO
	private String majorSubjects; // MJ
	private String minorSubjects; // MN
	private String abstractExtract; // AB/EX
	private String[] references; // RF
	private String[] citations; // CT

	/**
	 * Cria uma representação de documento do Solr com os atributos deste
	 * documento.
	 * 
	 * @return representação de documento do Solr com os atributos deste
	 *         documento.
	 */
	public SolrInputDocument toSolrInputDocument() {
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField(MetaAtributoDocumento.PN.getNome(), paperNumber);
		doc.setField(MetaAtributoDocumento.RN.getNome(), recordNumber);
		doc.setField(MetaAtributoDocumento.AN.getNome(), medlineAcessionNumber);
		doc.setField(MetaAtributoDocumento.AU.getNome(), author);
		doc.setField(MetaAtributoDocumento.TI.getNome(), title);
		doc.setField(MetaAtributoDocumento.SO.getNome(), source);
		doc.setField(MetaAtributoDocumento.MJ.getNome(), majorSubjects);
		doc.setField(MetaAtributoDocumento.MN.getNome(), minorSubjects);
		doc.setField(MetaAtributoDocumento.AB_EX.getNome(), abstractExtract);
		for (String reference : references) {
			doc.addField(MetaAtributoDocumento.RF.getNome(), reference);
		}
		for (String citation : citations) {
			doc.addField(MetaAtributoDocumento.CT.getNome(), citation);
		}
		return doc;
	}

	/**
	 * Cria uma representação de documento da forma definida para o trabalho
	 * prático com os atributos deste documento.
	 * 
	 * @return representação de documento da forma definida para o trabalho
	 *         prático com os atributos deste documento
	 */
	public Documento toDocumento() {
		StringBuilder conteudo = new StringBuilder();
		conteudo.append(author).append(' ').append(title).append(' ').append(source).append(' ').append(majorSubjects)
				.append(' ').append(minorSubjects).append(' ').append(abstractExtract);
		return new Documento(recordNumber, conteudo.toString());
	}

	/**
	 * Define um atributo de forma genérica.
	 * 
	 * @param atributoGen
	 *            atributo genérico a ser definido
	 */
	public void setAtributo(AtributoGenerico atributoGen) {
		MetaAtributo atributo = atributoGen.getAtributo();
		String valor = atributoGen.getValor();
		if (atributo.equals(MetaAtributoDocumento.PN)) {
			setPaperNumber(Integer.parseInt(valor));
		} else if (atributo.equals(MetaAtributoDocumento.RN)) {
			setRecordNumber(Integer.parseInt(valor));
		} else if (atributo.equals(MetaAtributoDocumento.AN)) {
			setMedlineAcessionNumber(Long.parseLong(valor));
		} else if (atributo.equals(MetaAtributoDocumento.AU)) {
			setAuthor(valor);
		} else if (atributo.equals(MetaAtributoDocumento.TI)) {
			setTitle(valor);
		} else if (atributo.equals(MetaAtributoDocumento.SO)) {
			setSource(valor);
		} else if (atributo.equals(MetaAtributoDocumento.MJ)) {
			setMajorSubjects(valor);
		} else if (atributo.equals(MetaAtributoDocumento.MN)) {
			setMinorSubjects(valor);
		} else if (atributo.equals(MetaAtributoDocumento.AB) || atributo.equals(MetaAtributoDocumento.EX)) {
			setAbstractExtract(valor);
		} else if (atributo.equals(MetaAtributoDocumento.RF)) {
			setReferences(StringUtils.splitSemStringVazia(valor, '\n'));
		} else if (atributo.equals(MetaAtributoDocumento.CT)) {
			setCitations(StringUtils.splitSemStringVazia(valor, '\n'));
		}
	}

	public Integer getPaperNumber() {
		return paperNumber;
	}

	public void setPaperNumber(Integer paperNumber) {
		this.paperNumber = paperNumber;
	}

	public Integer getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(Integer recordNumber) {
		this.recordNumber = recordNumber;
	}

	public Long getMedlineAcessionNumber() {
		return medlineAcessionNumber;
	}

	public void setMedlineAcessionNumber(Long medlineAcessionNumber) {
		this.medlineAcessionNumber = medlineAcessionNumber;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMajorSubjects() {
		return majorSubjects;
	}

	public void setMajorSubjects(String majorSubjects) {
		this.majorSubjects = majorSubjects;
	}

	public String getMinorSubjects() {
		return minorSubjects;
	}

	public void setMinorSubjects(String minorSubjects) {
		this.minorSubjects = minorSubjects;
	}

	public String getAbstractExtract() {
		return abstractExtract;
	}

	public void setAbstractExtract(String abstractExtract) {
		this.abstractExtract = abstractExtract;
	}

	public String[] getReferences() {
		return references;
	}

	public void setReferences(String[] references) {
		this.references = references;
	}

	public String[] getCitations() {
		return citations;
	}

	public void setCitations(String[] citations) {
		this.citations = citations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((paperNumber == null) ? 0 : paperNumber.hashCode());
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
		DocumentoOriginal other = (DocumentoOriginal) obj;
		if (paperNumber == null) {
			if (other.paperNumber != null)
				return false;
		} else if (!paperNumber.equals(other.paperNumber))
			return false;
		if (recordNumber == null) {
			if (other.recordNumber != null)
				return false;
		} else if (!recordNumber.equals(other.recordNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DocumentoOriginal [paperNumber=" + paperNumber + ", recordNumber=" + recordNumber
				+ ", medlineAcessionNumber=" + medlineAcessionNumber + ", author=" + author + ", title=" + title
				+ ", source=" + source + ", majorSubjects=" + majorSubjects + ", minorSubjects=" + minorSubjects
				+ ", abstractExtract=" + abstractExtract + ", references=" + references + ", citations=" + citations
				+ "]";
	}

}