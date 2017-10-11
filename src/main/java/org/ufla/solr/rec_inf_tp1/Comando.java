package org.ufla.solr.rec_inf_tp1;

/**
 * Enumera os possíveis comandos da aplicação.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public enum Comando {

	POVOAR_COLECAO("povoarColecao"), 
	CONSULTAS_E_RELATORIO("consultasERelatorio"), 
	CRIAR_COLECAO("criarColecao"),
	ADICIONAR_CONFIGURACAO("addConfig"),
	DELETAR_COLECAO("deletarColecao");

	/**
	 * Nome do comando.
	 */
	private String nome;

	private Comando(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	/**
	 * Recupera um comando pelo seu nome.
	 * 
	 * @param cmd
	 *            comando a ser recuperado
	 * @return comando recuperado, ou null caso não exista
	 */
	public static Comando getComando(String cmd) {
		if (cmd == null) {
			return null;
		}
		for (Comando comando : values()) {
			if (comando.getNome().equals(cmd)) {
				return comando;
			}
		}
		return null;
	}

}
