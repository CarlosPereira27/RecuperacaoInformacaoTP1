package org.ufla.solr.rec_inf_tp1.model.copy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.ufla.solr.rec_inf_tp1.utils.StringUtils;

/**
 * Consulta da forma original em que é extraída da base de dados estática.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class Consulta implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer queryNumber; // QN
	private String queryText; // QU
	private Integer qtyRelevantDocuments; // NR
	private Set<DocumentoRelevante> relevantDocs; // RD

	/**
	 * Define um atributo de forma genérica.
	 * 
	 * @param atributoGen
	 *            atributo genérico a ser definido
	 */
	public void setAtributo(AtributoGenerico atributoGen) {
		MetaAtributo atributo = atributoGen.getAtributo();
		String valor = atributoGen.getValor();
		if (atributo.equals(MetaAtributoConsulta.QN)) {
			setQueryNumber(Integer.parseInt(valor));
		} else if (atributo.equals(MetaAtributoConsulta.QU)) {
			setQueryText(valor);
		} else if (atributo.equals(MetaAtributoConsulta.NR)) {
			setQtyRelevantDocuments(Integer.parseInt(valor));
		} else if (atributo.equals(MetaAtributoConsulta.RD)) {
			setRelevantDocs(valor);
		}
	}

	/**
	 * Recupera um conjunto com os números de identificação dos documentos
	 * relevantes para a consulta.
	 * 
	 * @return conjunto com os números de identificação dos documentos
	 *         relevantes para a consulta
	 */
	public Set<Integer> getDocumentosRelevantes() {
		Set<Integer> documentosRelevantes = new HashSet<>();
		for (DocumentoRelevante doc : relevantDocs) {
			documentosRelevantes.add(doc.getRecordNumber());
		}
		return documentosRelevantes;
	}

	/**
	 * Define o conjunto de documentos relevantes a partir da string que contém
	 * essa lista de documentos relevantes e seus scores.
	 * 
	 * @param valor
	 *            string que contém a lista de documentos relevantes e seus
	 *            scores
	 */
	public void setRelevantDocs(String valor) {
		relevantDocs = new HashSet<>();
		String[] tokens = StringUtils.splitSemStringVazia(valor, ' ');
		if (tokens.length % 2 == 1) {
			throw new RuntimeException(String.format(
					"Erro! Atributo 'relevantDocs' não possui uma quantidade de tokens par (lista de número do documento/score) para a consulta %d:\n%s",
					queryNumber, valor));
		}
		if (tokens.length / 2 != qtyRelevantDocuments) {
			throw new RuntimeException(String.format(
					"Erro! Quantidade de documentos relevantes definida %d, quantidade que realmente está declarada %d.",
					qtyRelevantDocuments, tokens.length / 2));
		}
		for (int i = 0; i < tokens.length; i += 2) {
			relevantDocs.add(new DocumentoRelevante(Integer.parseInt(tokens[i]), tokens[i + 1]));
		}
	}

	public Integer getQueryNumber() {
		return queryNumber;
	}

	public void setQueryNumber(Integer queryNumber) {
		this.queryNumber = queryNumber;
	}

	public String getQueryText() {
		return queryText;
	}

	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}

	public Integer getQtyRelevantDocuments() {
		return qtyRelevantDocuments;
	}

	public void setQtyRelevantDocuments(Integer qtyRelevantDocuments) {
		this.qtyRelevantDocuments = qtyRelevantDocuments;
	}

	public Set<DocumentoRelevante> getRelevantDocs() {
		return relevantDocs;
	}

	public void setRelevantDocs(Set<DocumentoRelevante> relevantDocs) {
		this.relevantDocs = relevantDocs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((queryNumber == null) ? 0 : queryNumber.hashCode());
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
		Consulta other = (Consulta) obj;
		if (queryNumber == null) {
			if (other.queryNumber != null)
				return false;
		} else if (!queryNumber.equals(other.queryNumber))
			return false;
		return true;
	}

	public String simpleToString() {
		return String.format(
				"{\n" + "\t\"queryNumber\":%d,\n" + "\t\"queryText\":%s\n" + "\t\"qtyRelevantDocuments\":%s\n}",
				queryNumber, queryText, qtyRelevantDocuments);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("{\n" + "\t\"queryNumber\":%d,\n" + "\t\"queryText\":%s\n"
				+ "\t\"qtyRelevantDocuments\":%s,\n" + "\t\"relevantDocs\":\n\t[\n", queryNumber, queryText,
				qtyRelevantDocuments));
		for (DocumentoRelevante documentoRelevante : relevantDocs) {
			String str = documentoRelevante.toString();
			final int N = str.length();
			int i = 0;
			while (i < N) {
				int fim = str.indexOf('\n', i) + 1;
				if (fim == 0) {
					fim = N;
				}
				sb.append("\t\t").append(str.substring(i, fim));
				i = fim;
			}
			sb.append(",\n");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("\n\t]\n}");
		return sb.toString();
	}

}
