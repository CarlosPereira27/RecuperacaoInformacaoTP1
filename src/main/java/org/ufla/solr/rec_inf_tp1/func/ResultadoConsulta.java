package org.ufla.solr.rec_inf_tp1.func;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import org.ufla.solr.rec_inf_tp1.metrica.AreaPoligono;
import org.ufla.solr.rec_inf_tp1.metrica.MetricaPrecisaoRevocacao;
import org.ufla.solr.rec_inf_tp1.metrica.Ponto;
import org.ufla.solr.rec_inf_tp1.metrica.PontoPrecisaoRevocacao;
import org.ufla.solr.rec_inf_tp1.metrica.TabelaPrecisaoRevocacao;
import org.ufla.solr.rec_inf_tp1.model.Consulta;

/**
 * Representa o resultado de uma consulta em relação a métrica de
 * precisão/revocação.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class ResultadoConsulta {

	private List<PontoPrecisaoRevocacao> pontos;
	private TabelaPrecisaoRevocacao tabela;
	private Consulta consulta;
	private int qtdDocRecuperados;

	public ResultadoConsulta() {

	}

	public ResultadoConsulta(List<PontoPrecisaoRevocacao> pontos, TabelaPrecisaoRevocacao tabela, Consulta consulta,
			int qtdDocRecuperados) {
		this.pontos = pontos;
		this.tabela = tabela;
		this.consulta = consulta;
		this.qtdDocRecuperados = qtdDocRecuperados;
	}

	public ResultadoConsulta(MetricaPrecisaoRevocacao metricaPrecisaoRevocacao, Consulta consulta,
			int qtdDocRecuperados) {
		this(metricaPrecisaoRevocacao.calcularPontos(), metricaPrecisaoRevocacao.calcularTabela(), consulta,
				qtdDocRecuperados);
	}

	/**
	 * Gera o relatório para o resultado desta consulta.
	 * 
	 * @param bw
	 *            saída de fluxo de dados onde o relatório será escrito
	 * @throws IOException
	 */
	public void gerarRelatorioConsulta(BufferedWriter bw) throws IOException {
		bw.write(",\n,\n,\n");
		bw.write(String.format("Consulta Num,%d\n", consulta.getQueryNumber()));
		bw.write(String.format("Qtd Rel,%d\n", getQtdDocRelevantes()));
		bw.write(String.format("Qtd Rec,%d\n", getQtdDocRecuperados()));
		bw.write(String.format("Qtd Rec Rel,%d\n", getQtdDocRecuperadosRelevantes()));
		bw.write("Pontos,\n");
		bw.write(String.format("Área,%.2f\n", getAreaPontos()));
		bw.write("Revocação,Precisão\n");
		for (PontoPrecisaoRevocacao ponto : pontos) {
			bw.write(String.format("%.2f,%.2f\n", ponto.revocacao, ponto.precisao));
		}
		bw.write("Tabela,\n");
		bw.write(String.format("Área,%.2f\n", tabela.getArea()));
		bw.write("Revocação,Precisão\n");
		for (int j = 0; j < TabelaPrecisaoRevocacao.QTD_NIVEIS_REVOCACAO; j++) {
			bw.write(
					String.format("%.2f,%.2f\n", TabelaPrecisaoRevocacao.NIVEIS_REVOCACAO[j], tabela.getPrecisao()[j]));
		}
	}

	/**
	 * Cálcula a área do polígono em relação aos pontos de precisão e revocação.
	 * 
	 * @return área do polígono em relação aos pontos de precisão e revocação
	 */
	public double getAreaPontos() {
		int qtdPontos = pontos.size() + 1;
		if (pontos.get(0).revocacao > 0) {
			qtdPontos++;
		}
		if (pontos.get(pontos.size() - 1).revocacao < 100) {
			qtdPontos++;
		}
		Ponto[] poligono = new Ponto[qtdPontos];
		poligono[0] = new Ponto(0, 0);
		int desloc = 1;
		if (pontos.get(0).revocacao > 0) {
			poligono[1] = new Ponto(0, pontos.get(0).precisao);
			desloc++;
		}
		for (int i = 0; i < pontos.size(); i++) {
			poligono[desloc + i] = pontos.get(i).toPonto();
		}
		if (pontos.get(pontos.size() - 1).revocacao < 100) {
			poligono[qtdPontos - 1] = new Ponto(100, 0);
		}
		return AreaPoligono.areaPoligono(poligono);
	}

	/**
	 * Recupera quantidade de documentos relevantes para essa consulta.
	 * 
	 * @return quantidade de documentos relevantes para essa consulta
	 */
	public int getQtdDocRelevantes() {
		return consulta.getQtyRelevantDocuments();
	}

	/**
	 * Recupera quantidade de documentos recuperados que são relevantes para
	 * essa consulta.
	 * 
	 * @return quantidade de documentos recuperados que são relevantes para essa
	 *         consulta
	 */
	public int getQtdDocRecuperadosRelevantes() {
		return pontos.size();
	}

	public List<PontoPrecisaoRevocacao> getPontos() {
		return pontos;
	}

	public void setPontos(List<PontoPrecisaoRevocacao> pontos) {
		this.pontos = pontos;
	}

	public TabelaPrecisaoRevocacao getTabela() {
		return tabela;
	}

	public void setTabela(TabelaPrecisaoRevocacao tabela) {
		this.tabela = tabela;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public int getQtdDocRecuperados() {
		return qtdDocRecuperados;
	}

	public void setQtdDocRecuperados(int qtdDocRecuperados) {
		this.qtdDocRecuperados = qtdDocRecuperados;
	}

}
