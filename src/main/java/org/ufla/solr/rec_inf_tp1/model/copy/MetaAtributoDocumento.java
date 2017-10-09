package org.ufla.solr.rec_inf_tp1.model.copy;

import java.io.Serializable;

/**
 * Enumerador do metadado (nome) dos atributos contidos nos documentos.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public enum MetaAtributoDocumento implements MetaAtributo, Serializable {

	PN("PN"), 
	RN("RN"), 
	AN("AN"), 
	AU("AU"), 
	TI("TI"), 
	SO("SO"), 
	MJ("MJ"), 
	MN("MN"), 
	AB("AB"), 
	EX("EX"), 
	AB_EX("AB/EX"), 
	RF("RF"), 
	CT("CT"), 
	CONT("CONT"),
	CONT_DEF_ATR("  ");

	/**
	 * Nome do atributo.
	 */
	private String nome;

	private MetaAtributoDocumento(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	private static final char CARACTER_SEPARADOR_DEFAULT = ' ';
	private static final char CARACTER_SEPARADOR_RF_CT = '\n';

	@Override
	public char getCaracterSeparador() {
		switch (this) {
		case RF:
		case CT:
			return CARACTER_SEPARADOR_RF_CT;
		default:
			return CARACTER_SEPARADOR_DEFAULT;
		}
	}

}
