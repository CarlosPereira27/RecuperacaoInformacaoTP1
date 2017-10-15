package org.ufla.solr.rec_inf_tp1.func;

import java.io.IOException;

import org.ufla.solr.rec_inf_tp1.utils.StringUtils;

/**
 * Responsável por executar a aplicação Python gerarGraficos.py, que gera os
 * gráficos para os resultados obtidos nesta aplicação.
 * 
 * @author carlos
 *
 */
public class GerarGraficos {

	/**
	 * Python que irá executar o programa de geração de gráficos.
	 */
	private static String python = "python";
	/**
	 * Localização e nome do programa em python de geração de gráficos.
	 */
	private static String programa = "gerarGraficos.py";

	/**
	 * Localização e nome do arquivo com os resultados onde os gráficos serão
	 * gerados.
	 */
	private String arquivoResultado;

	/**
	 * Construtor definindo a localização e nome do arquivo com os resultados
	 * 
	 * @param arquivoResultado
	 *            localização e nome do arquivo com os resultados
	 */
	public GerarGraficos(String arquivoResultado) {
		this.arquivoResultado = arquivoResultado;
	}

	/**
	 * Chama o programa python para gerar os gráficos em relação aos resultados
	 * do arquivo passado pelo construtor.
	 */
	public void gerar() {
		System.out.printf("\nGerando gráficos com a aplicação '%s' em python para os resultados do arquivo '%s'.\n\n",
				programa, arquivoResultado);
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(String.format("%s %s -r %s", python, programa, arquivoResultado));
			process.waitFor();
			if (process.exitValue() == 0) {
				System.out.println("Gráficos gerados com sucesso!");
			} else {
				System.out.println("Erro na geração de gráficos!");
				System.out.println(StringUtils.getMensagem(process.getErrorStream()));
			}
		} catch (IOException | InterruptedException e1) {
			System.out.println("Erro na geração de gráficos!");
			e1.printStackTrace();
		}
	}

}
