package org.ufla.solr.rec_inf_tp1;

/**
 * Enumera os possíveis argumentos da aplicação.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public enum Argumento {

	H("-h"), HELP("-help"), CMD("-cmd"), C("-c"), HOST("-host"), P("-p"), OUT("-out"), BD("-bd");

	/**
	 * Nome do argumento.
	 */
	private String nome;

	private Argumento(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	/**
	 * Recupera um argumento pelo seu nome.
	 * 
	 * @param arg
	 *            argumento a ser recuperado
	 * @return argumento recuperado, ou null caso não exista
	 */
	public static Argumento getArgumento(String arg) {
		if (arg == null) {
			return null;
		}
		for (Argumento argumento : values()) {
			if (argumento.getNome().equals(arg)) {
				return argumento;
			}
		}
		return null;
	}

}
