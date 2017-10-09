package org.ufla.solr.rec_inf_tp1.campo;

import java.util.HashMap;

/**
 * Representa os atributos de um campo em um schema do Solr.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class AtributosCampo extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public AtributosCampo() {
		inicializar();
	}

	public AtributosCampo(String nome) {
		this();
		nome(nome);
	}

	public AtributosCampo(String nome, String tipo) {
		this(nome);
		tipo(tipo);
	}

	/**
	 * Verifica se esta instância de atributos de campo está válida. A instância
	 * está válida se o nome e o tipo do campo já foi definido.
	 * 
	 * @return true se a instância está válida, caso contrário false
	 */
	public boolean estaValido() {
		return get(MetaAtributoCampo.NAME.getNome()) != null && get(MetaAtributoCampo.FIELD_TYPE.getNome()) != null;
	}

	/**
	 * Inicializa os atributos booleanos do campo como false.
	 */
	private void inicializar() {
		put(MetaAtributoCampo.STORED.getNome(), false);
		put(MetaAtributoCampo.INDEXED.getNome(), false);
		put(MetaAtributoCampo.DOC_VALUES.getNome(), false);
		put(MetaAtributoCampo.MULTI_VALUED.getNome(), false);
		put(MetaAtributoCampo.REQUIRED.getNome(), false);
		put(MetaAtributoCampo.OMIT_NORMS.getNome(), false);
		put(MetaAtributoCampo.OMIT_TERM_FREQ_AND_POSITIONS.getNome(), false);
		put(MetaAtributoCampo.OMIT_POSITIONS.getNome(), false);
		put(MetaAtributoCampo.TERM_VECTORS.getNome(), false);
		put(MetaAtributoCampo.TERM_POSITIONS.getNome(), false);
		put(MetaAtributoCampo.TERM_OFFSETS.getNome(), false);
		put(MetaAtributoCampo.TERM_PAYLOADS.getNome(), false);
		put(MetaAtributoCampo.SORT_MISSING_FIRST.getNome(), false);
		put(MetaAtributoCampo.SORT_MISSING_LAST.getNome(), false);
	}

	/**
	 * Define o nome do campo.
	 * 
	 * @param nome
	 *            nome do campo
	 * @return própria estrutura com os atributos do campo.
	 */
	public AtributosCampo nome(String nome) {
		put(MetaAtributoCampo.NAME.getNome(), nome);
		return this;
	}

	/**
	 * Define o tipo do campo.
	 * 
	 * @param tipo
	 *            tipo do campo
	 * @return própria estrutura com os atributos do campo.
	 */
	public AtributosCampo tipo(String tipo) {
		put(MetaAtributoCampo.FIELD_TYPE.getNome(), tipo);
		return this;
	}

	/**
	 * Define o valor default do campo.
	 * 
	 * @param valor
	 *            valor default do campo
	 * @return própria estrutura com os atributos do campo.
	 */
	public AtributosCampo valorDefault(String valor) {
		put(MetaAtributoCampo.DEFAULT.getNome(), valor);
		return this;
	}

	/**
	 * Define o campo como stored.
	 * 
	 * @return própria estrutura com os atributos do campo.
	 */
	public AtributosCampo stored() {
		put(MetaAtributoCampo.STORED.getNome(), true);
		return this;
	}

	/**
	 * Define o campo como indexed.
	 * 
	 * @return própria estrutura com os atributos do campo.
	 */
	public AtributosCampo indexed() {
		put(MetaAtributoCampo.INDEXED.getNome(), true);
		return this;
	}

	/**
	 * Define o campo como docValues.
	 * 
	 * @return própria estrutura com os atributos do campo.
	 */
	public AtributosCampo docValues() {
		put(MetaAtributoCampo.DOC_VALUES.getNome(), true);
		return this;
	}

	/**
	 * Define o campo como multiValued.
	 * 
	 * @return própria estrutura com os atributos do campo.
	 */
	public AtributosCampo multiValued() {
		put(MetaAtributoCampo.MULTI_VALUED.getNome(), true);
		return this;
	}

	/**
	 * Define o campo como required.
	 * 
	 * @return própria estrutura com os atributos do campo.
	 */
	public AtributosCampo required() {
		put(MetaAtributoCampo.REQUIRED.getNome(), true);
		return this;
	}

	/**
	 * Define o campo como omitNorms.
	 * 
	 * @return própria estrutura com os atributos do campo.
	 */
	public AtributosCampo omitNorms() {
		put(MetaAtributoCampo.OMIT_NORMS.getNome(), true);
		return this;
	}

	/**
	 * Define o campo como omitTermFreqAndPositions.
	 * 
	 * @return própria estrutura com os atributos do campo.
	 */
	public AtributosCampo omitTermFreqAndPositions() {
		put(MetaAtributoCampo.OMIT_TERM_FREQ_AND_POSITIONS.getNome(), true);
		return this;
	}

	/**
	 * Define o campo como omitPositions.
	 * 
	 * @return própria estrutura com os atributos do campo.
	 */
	public AtributosCampo omitPositions() {
		put(MetaAtributoCampo.OMIT_POSITIONS.getNome(), true);
		return this;
	}

	/**
	 * Define o campo como termVectors.
	 * 
	 * @return própria estrutura com os atributos do campo.
	 */
	public AtributosCampo termVectors() {
		put(MetaAtributoCampo.TERM_VECTORS.getNome(), true);
		return this;
	}

	/**
	 * Define o campo como termPositions.
	 * 
	 * @return própria estrutura com os atributos do campo.
	 */
	public AtributosCampo termPositions() {
		put(MetaAtributoCampo.TERM_POSITIONS.getNome(), true);
		return this;
	}

	/**
	 * Define o campo como termOffsets.
	 * 
	 * @return própria estrutura com os atributos do campo.
	 */
	public AtributosCampo termOffsets() {
		put(MetaAtributoCampo.TERM_OFFSETS.getNome(), true);
		return this;
	}

	/**
	 * Define o campo como termPayloads.
	 * 
	 * @return própria estrutura com os atributos do campo.
	 */
	public AtributosCampo termPayloads() {
		put(MetaAtributoCampo.TERM_PAYLOADS.getNome(), true);
		return this;
	}

	/**
	 * Define o campo como sortMissingFirst.
	 * 
	 * @return própria estrutura com os atributos do campo.
	 */
	public AtributosCampo sortMissingFirst() {
		put(MetaAtributoCampo.SORT_MISSING_FIRST.getNome(), true);
		return this;
	}

	/**
	 * Define o campo como sortMissingLast.
	 * 
	 * @return própria estrutura com os atributos do campo.
	 */
	public AtributosCampo sortMissingLast() {
		put(MetaAtributoCampo.SORT_MISSING_LAST.getNome(), true);
		return this;
	}

}
