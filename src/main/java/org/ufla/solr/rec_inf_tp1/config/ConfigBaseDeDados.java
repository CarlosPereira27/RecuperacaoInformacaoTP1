package org.ufla.solr.rec_inf_tp1.config;

import java.io.File;

/**
 * Representação das configurações das base de dados (documentos/consultas).
 * Indica os arquivos das bases de dados. Utiliza o padrão de projeto singleton.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class ConfigBaseDeDados {

	/**
	 * Define o array com os nomes dos arquivos da base de dados de documentos.
	 */
	private static final String[] ARQUIVOS_DOCUMENTOS = { "cf74", "cf75", "cf76", "cf77", "cf78", "cf79" };

	/**
	 * Define o nome do arquivo da base de dados do arquivo de consulta
	 */
	private static final String ARQUIVO_CONSULTA = "cfquery";

	/**
	 * Única instância de ConfigBaseDeDados da aplicação (Singleton)
	 */
	private static ConfigBaseDeDados configBaseDeDados;

	/**
	 * Define o diretório dos arquivos com a base de dados CFC.
	 */
	private String diretorioCFC;

	/**
	 * Construtor privado para implementação do padrão Singleton
	 */
	private ConfigBaseDeDados() {

	}

	/**
	 * Retorna a única instância de ConfigBaseDeDados.
	 * 
	 * @return única instância de ConfigBaseDeDados
	 */
	public static ConfigBaseDeDados getInstance() {
		if (configBaseDeDados == null) {
			configBaseDeDados = new ConfigBaseDeDados();
		}
		return configBaseDeDados;
	}

	/**
	 * Recupera um determinado arquivo da base de dados CFC de documentos.
	 * 
	 * @param indice
	 *            índice do arquivo a ser recuperado.
	 * @return arquivo da base de dados CFC de documentos, ou null em caso de
	 *         não existir documento com tal índice.
	 */
	public File getArquivoDocumento(int indice) {
		if (indice < 0 || indice >= ARQUIVOS_DOCUMENTOS.length) {
			return null;
		}
		return new File(diretorioCFC + File.separator + ARQUIVOS_DOCUMENTOS[indice]);
	}

	/**
	 * Recupera o arquivo da base de dados CFC com as consultas.
	 * 
	 * @return arquivo da base de dados CFC com as consultas
	 */
	public File getArquivoConsulta() {
		return new File(diretorioCFC + File.separator + ARQUIVO_CONSULTA);
	}

	public String getDiretorioCFC() {
		return this.diretorioCFC;
	}

	public void setDiretorioCFC(String diretorioCFC) {
		this.diretorioCFC = diretorioCFC;
	}

}
