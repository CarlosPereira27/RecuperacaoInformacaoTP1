package org.ufla.solr.rec_inf_tp1.model;

/**
 * Representa o metadado (nome) de um atributo.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public interface MetaAtributo {

	/**
	 * Recupera o nome do atributo.
	 * 
	 * @return nome do atributo
	 */
	public String getNome();

	/**
	 * Retorna o caracter separador do atributo, caso este atributo estiver
	 * definido em mais de uma linha.
	 * 
	 * @return caracter separador do atributo
	 */
	public char getCaracterSeparador();

	/**
	 * Recupera com qual atributo (documento/consulta/argumento) a linha está
	 * relacionada.
	 * 
	 * @param linha
	 *            linha
	 * 
	 * @param clazz
	 *            classe enumeradora que implementa a interface
	 *            {@link MetaAtributo} e representa o atributo a ser recuperado.
	 *            Existem três classes enumeradoras para atributo:
	 *            {@link MetaAtributoDocumento} que representa o atributo de um
	 *            documento, {@link MetaAtributoConsulta} que representa o
	 *            atributo de uma consulta, e {@link MetaArgumento} que
	 *            representa um argumento da aplicação.
	 * @return atributo (documento/consulta/argumento) relacionado a linha
	 */
	public static <MetaAtr extends MetaAtributo> MetaAtributo getAtributo(String linha, Class<MetaAtr> clazz) {
		if (linha == null || linha.trim().isEmpty()) {
			return null;
		}
		if (clazz.equals(MetaArgumento.class)) {
			MetaAtributo atr = validarMetaArgumento(linha);
			if (atr != null) {
				return atr;
			}
		}
		for (MetaAtr atr : clazz.getEnumConstants()) {
			if (linha.startsWith(atr.getNome())) {
				return atr;
			}
		}
		return null;
	}

	/**
	 * Valida os casos de argumentos que possuem prefixos que também sao
	 * argumentos. Exemplo: '-conf' possui '-c';
	 * 
	 * @param linha
	 *            linha a ser analisada
	 * @return argumento que possui argumento prefixo, ou null em caso de não
	 *         ser esses argumentos
	 */
	static MetaAtributo validarMetaArgumento(String linha) {
		if (linha.startsWith(MetaArgumento.HELP.getNome())) {
			return MetaArgumento.HELP;
		}
		if (linha.startsWith(MetaArgumento.CONF.getNome())) {
			return MetaArgumento.CONF;
		}
		if (linha.startsWith(MetaArgumento.HOST.getNome())) {
			return MetaArgumento.HOST;
		}
		return null;
	}
}
