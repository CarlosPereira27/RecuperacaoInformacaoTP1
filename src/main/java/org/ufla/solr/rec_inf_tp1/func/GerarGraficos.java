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
	private static String python = "python3";

	/**
	 * Localização e nome do programa em python de geração de gráficos.
	 */
	private static String programa = "gerarGraficos.py";

	/**
	 * Modo de execução da aplicação de geração de gráficos, modos disponíveis
	 * [simples, completo]. O modo simples apenas gera o gráfico da tabela
	 * média, o modo completo gera os gráficos de cada consulta.
	 */
	private String modo = "simples";

	/**
	 * Define o título do gráfico a ser gerado.
	 */
	private String tituloGrafico = "";

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
	 * Construtor definindo a localização e nome do arquivo com os resultados, e
	 * o título do gráfico a ser gerado.
	 * 
	 * @param arquivoResultado
	 *            localização e nome do arquivo com os resultados
	 * @param tituloGrafico
	 *            título do gráfico a ser gerado.
	 */
	public GerarGraficos(String arquivoResultado, String tituloGrafico) {
		this.arquivoResultado = arquivoResultado;
		this.tituloGrafico = tituloGrafico;
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
			process = Runtime.getRuntime()
					.exec(new String[] { python, programa, "-r", arquivoResultado, "-m", modo, "-t", tituloGrafico });
			process.waitFor();
			if (process.exitValue() == 0) {
				System.out.println("Gráficos gerados com sucesso!");
			} else {
				System.out.println("Erro na geração de gráficos! Exit value != 0.");
				System.out.println(StringUtils.getMensagem(process.getInputStream()));
				System.out.println(StringUtils.getMensagem(process.getErrorStream()));
			}
		} catch (IOException | InterruptedException e1) {
			System.out.println("Erro na geração de gráficos!");
			e1.printStackTrace();
		}
	}

	public static String getPython() {
		return python;
	}

	public static void setPython(String python) {
		GerarGraficos.python = python;
	}

	public static String getPrograma() {
		return programa;
	}

	public static void setPrograma(String programa) {
		GerarGraficos.programa = programa;
	}

	public String getModo() {
		return modo;
	}

	public void setModo(String modo) {
		this.modo = modo;
	}

	public String getTituloGrafico() {
		return tituloGrafico;
	}

	public void setTituloGrafico(String tituloGrafico) {
		this.tituloGrafico = tituloGrafico;
	}

	public String getArquivoResultado() {
		return arquivoResultado;
	}

	public void setArquivoResultado(String arquivoResultado) {
		this.arquivoResultado = arquivoResultado;
	}

}
