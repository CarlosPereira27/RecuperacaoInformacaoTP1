package org.ufla.solr.rec_inf_tp1.model.copy;

import java.io.Serializable;

/**
 * Enumerador do metadado (nome) dos atributos contidos nas consultas.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public enum MetaAtributoConsulta implements MetaAtributo, Serializable {

	QN("QN"), 
	QU("QU"), 
	NR("NR"), 
	RD("RD"), 
	CONT_DEF_ATR("  ");

	/**
	 * Nome do atributo.
	 */
	private String nome;

	private MetaAtributoConsulta(String nome) {
		this.nome = nome;
	}

	@Override
	public String getNome() {
		return nome;
	}

	private static final char CARACTER_SEPARADOR_DEFAULT = ' ';

	@Override
	public char getCaracterSeparador() {
		return CARACTER_SEPARADOR_DEFAULT;
	}

}
