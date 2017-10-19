package org.ufla.solr.rec_inf_tp1.func;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ufla.solr.rec_inf_tp1.metrica.MetricaPrecisaoRevocacao;
import org.ufla.solr.rec_inf_tp1.metrica.TabelaPrecisaoRevocacao;

/**
 * Representa o resultado de todas as consultas e possui a responsabilidade de
 * gerar o relatório.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class Relatorio {

	/**
	 * Saída de fluxo de dados onde o relatório será escrito.
	 */
	private BufferedWriter bw;
	/**
	 * Lista de resultados que formam o relatório.
	 */
	private List<ResultadoConsulta> resultados;

	/**
	 * Inicializa o relatório.
	 * 
	 * @param bw
	 *            saída de fluxo de dados onde o relatório será escrito.
	 */
	public Relatorio(BufferedWriter bw) {
		this.bw = bw;
		resultados = new ArrayList<>();
	}

	/**
	 * Adiciona um resultado de uma consulta na lista de resultados.
	 * 
	 * @param resultadoConsulta
	 *            resultado de uma consulta
	 * @return true, se o resultado foi adicionado com sucesso, caso contrário
	 *         false
	 */
	public boolean addResultado(ResultadoConsulta resultadoConsulta) {
		return resultados.add(resultadoConsulta);
	}

	/**
	 * Cria uma lista com as tabelas dos resultados.
	 * 
	 * @return lista com as tabelas dos resultados
	 */
	private List<TabelaPrecisaoRevocacao> getTabelas() {
		List<TabelaPrecisaoRevocacao> tabelas = new ArrayList<>();
		for (ResultadoConsulta resultado : resultados) {
			tabelas.add(resultado.getTabela());
		}
		return tabelas;
	}

	/**
	 * Recupera o somatório da quantidade de documentos relevantes para cada
	 * consulta.
	 * 
	 * @return somatório da quantidade de documentos relevantes para cada
	 *         consulta.
	 */
	public int getTotalDocRelevantes() {
		int totalDocRelevantes = 0;
		for (ResultadoConsulta resultadoConsulta : resultados) {
			totalDocRelevantes += resultadoConsulta.getQtdDocRelevantes();
		}
		return totalDocRelevantes;
	}

	/**
	 * Recuperao o somatório da quantidade de documentos recuperados por cada
	 * consulta.
	 * 
	 * @return somatório da quantidade de documentos recuperados por cada
	 *         consulta.
	 */
	public int getTotalDocRecuperados() {
		int totalDocRecuperados = 0;
		for (ResultadoConsulta resultadoConsulta : resultados) {
			totalDocRecuperados += resultadoConsulta.getQtdDocRecuperados();
		}
		return totalDocRecuperados;
	}

	/**
	 * Recupera o somatório da quantidade de documentos recuperados que são
	 * relevantes para cada consulta.
	 * 
	 * @return somatório da quantidade de documentos recuperados que são
	 *         relevantes para cada consulta.
	 */
	public int getTotalDocRecuperadosRelevantes() {
		int totalDocRecRel = 0;
		for (ResultadoConsulta resultadoConsulta : resultados) {
			totalDocRecRel += resultadoConsulta.getQtdDocRecuperadosRelevantes();
		}
		return totalDocRecRel;
	}

	/**
	 * Gera o relatório na saída de fluxo de dados definida.
	 * 
	 * @throws IOException
	 */
	public void gerarRelatorio() throws IOException {
		TabelaPrecisaoRevocacao tabelaMedia = MetricaPrecisaoRevocacao.calcularTabelaMedia(getTabelas());

		bw.write("Tabela Média,\n");
		bw.write(String.format("Área,%.2f\n", tabelaMedia.getArea()));
		bw.write("Revocação,Precisão\n");
		for (int i = 0; i < TabelaPrecisaoRevocacao.QTD_NIVEIS_REVOCACAO; i++) {
			bw.write(String.format("%.2f,%.2f\n", TabelaPrecisaoRevocacao.NIVEIS_REVOCACAO[i],
					tabelaMedia.getPrecisao()[i]));
		}
		bw.write(String.format("Total rel,%d\n", getTotalDocRelevantes()));
		bw.write(String.format("Total rec,%d\n", getTotalDocRecuperados()));
		bw.write(String.format("Total rec rel,%d\n", getTotalDocRecuperadosRelevantes()));
		for (ResultadoConsulta resultado : resultados) {
			resultado.gerarRelatorioConsulta(bw);
		}
	}

	public List<ResultadoConsulta> getResultados() {
		return resultados;
	}

}
