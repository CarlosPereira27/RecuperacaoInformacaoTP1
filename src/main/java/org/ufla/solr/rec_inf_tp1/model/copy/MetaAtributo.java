package org.ufla.solr.rec_inf_tp1.model.copy;

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
	 * Recupera com qual atributo (documento/consulta) a linha da base de dados
	 * está relacionada.
	 * 
	 * @param linha
	 *            linha da base de dados
	 * 
	 * @param clazz
	 *            classe enumeradora que implementa a interface Atributo e
	 *            representa o atributo a ser recuperado. Existem duas classes
	 *            enumeradoras para atributo: {@link MetaAtributoDocumento} que
	 *            representa o atributo de um documento e
	 *            {@link MetaAtributoConsulta} que representa o atributo de uma
	 *            consulta.
	 * @return atributo (documento/consulta) relacionado a linha
	 */
	public static <MetaAtr extends MetaAtributo> MetaAtributo getAtributo(String linha, Class<MetaAtr> clazz) {
		if (linha == null || linha.trim().isEmpty()) {
			return null;
		}
		for (MetaAtr atr : clazz.getEnumConstants()) {
			if (linha.startsWith(atr.getNome())) {
				return atr;
			}
		}
		return null;
	}
}
