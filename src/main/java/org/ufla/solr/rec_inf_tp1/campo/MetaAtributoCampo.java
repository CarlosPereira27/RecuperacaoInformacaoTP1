package org.ufla.solr.rec_inf_tp1.campo;

/**
 * Enumerador do metadado (nome) dos atributos contidos na definição de um campo
 * em um determinado schema do Solr.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public enum MetaAtributoCampo {

	NAME("name"), 
	FIELD_TYPE("type"), 
	DEFAULT("default"), 
	STORED("stored"), 
	INDEXED("indexed"), 
	DOC_VALUES("docValues"), 
	MULTI_VALUED("multiValued"), 
	REQUIRED("required"), 
	OMIT_NORMS("omitNorms"), 
	OMIT_TERM_FREQ_AND_POSITIONS("omitTermFreqAndPositions"), 
	OMIT_POSITIONS("omitPositions"), 
	TERM_VECTORS("termVectors"), 
	TERM_POSITIONS("termPositions"), 
	TERM_OFFSETS("termOffsets"), 
	TERM_PAYLOADS("termPayloads"), 
	SORT_MISSING_FIRST("sortMissingFirst"), 
	SORT_MISSING_LAST("sortMissingLast");

	/**
	 * Nome do atributo.
	 */
	private String nome;

	private MetaAtributoCampo(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}