package org.ufla.solr.rec_inf_tp1.model;

/**
 * Representa o metadado (nome) de um argumento.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public enum MetaArgumento implements MetaAtributo {

	H("-h"), 
	HELP("-help"), 
	CMD("-cmd"), 
	C("-c"), 
	HOST("-host"), 
	P("-p"), 
	OUT("-out"), 
	BD("-bd"), 
	CONF("-conf"), 
	N_SHARD("-nshard"), 
	N_REPLICAS("-nreplicas"),
	DIR_CONF("-dirconf"),
	ZK_HOST("-zkhost"),
	ZK_P("-zkp"),
	PRE_PROC("-preproc"),
	CONT_DEF_ATR("CONSTANTE_PARA_FUNCIONAR_EXTRATOR");

	/**
	 * Nome do atributo.
	 */
	private String nome;

	private MetaArgumento(String nome) {
		this.nome = nome;
	}

	@Override
	public String getNome() {
		return nome;
	}

	private static final char CARACTER_SEPARADOR_DEFAULT = '=';

	@Override
	public char getCaracterSeparador() {
		return CARACTER_SEPARADOR_DEFAULT;
	}

	/**
	 * Recupera um argumento pelo seu nome.
	 * 
	 * @param arg
	 *            argumento a ser recuperado
	 * @return argumento recuperado, ou null caso não exista
	 */
	public static MetaArgumento getArgumento(String arg) {
		if (arg == null) {
			return null;
		}
		for (MetaArgumento argumento : values()) {
			if (argumento.getNome().equals(arg)) {
				return argumento;
			}
		}
		return null;
	}

}
