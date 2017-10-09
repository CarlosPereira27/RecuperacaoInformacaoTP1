package org.ufla.solr.rec_inf_tp1.config;

import java.io.File;

/**
 * Representação das configurações das base de dados (documentos/consultas).
 * Apenas indica os arquivos das bases de dados.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public abstract class ConfigBaseDeDados {

	/**
	 * Define o caminho dos arquivos com a base de dados dentro da máquina em
	 * que está rodando o projeto.
	 */
	private static String caminhoAbsolutoCFC = "/home/carlos/workspaces/solr/RecuperacaoInformacaoTP1/cfc/";

	/**
	 * Define o array com os nomes dos arquivos da base de dados de documentos.
	 */
	private static final String[] ARQUIVOS_DOCUMENTOS = { "cf74", "cf75", "cf76", "cf77", "cf78", "cf79" };

	/**
	 * Recupera um determinado arquivo da base de dados de documentos.
	 * 
	 * @param indice
	 *            índice do arquivo a ser recuperado.
	 * @return arquivo da base de dados de documentos, ou null em caso de não
	 *         existir documento com tal índice.
	 */
	public static File getArquivoDocumento(int indice) {
		if (indice < 0 || indice >= ARQUIVOS_DOCUMENTOS.length) {
			return null;
		}
		return new File(caminhoAbsolutoCFC + ARQUIVOS_DOCUMENTOS[indice]);
	}

	public static void setCaminhoAbslutoCFC(String caminhoAbsolutoCFC) {
		ConfigBaseDeDados.caminhoAbsolutoCFC = caminhoAbsolutoCFC;
		arquivoConsulta = new File(caminhoAbsolutoCFC + "cfquery");
	}

	/**
	 * Arquivo da base de dados de consultas.
	 */
	public static File arquivoConsulta = new File(caminhoAbsolutoCFC + "cfquery");

}
