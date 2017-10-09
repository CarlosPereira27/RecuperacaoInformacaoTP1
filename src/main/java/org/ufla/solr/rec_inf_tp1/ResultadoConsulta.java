package org.ufla.solr.rec_inf_tp1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import org.ufla.solr.rec_inf_tp1.metrica.MetricaPrecisaoRevocacao;
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

	public ResultadoConsulta() {

	}

	/**
	 * Gera o relatório para o resultado desta consulta.
	 * 
	 * @param bw
	 *            saída de fluxo de dados onde o relatório será escrito
	 * @throws IOException
	 */
	public void gerarRelatorioConsulta(BufferedWriter bw) throws IOException {
		bw.write(String.format("\n\n\nConsulta Num,%d\n", consulta.getQueryNumber()));

		bw.write("\nPontos\nRevocação,Precisão\n");
		for (PontoPrecisaoRevocacao ponto : pontos) {
			bw.write(String.format("%.2f,%.2f\n", ponto.revocacao, ponto.precisao));
		}

		bw.write("\nTabela\nRevocação,Precisão\n");
		for (int j = 0; j < TabelaPrecisaoRevocacao.QTD_NIVEIS_REVOCACAO; j++) {
			bw.write(
					String.format("%.2f,%.2f\n", TabelaPrecisaoRevocacao.NIVEIS_REVOCACAO[j], tabela.getPrecisao()[j]));
		}
	}

	public ResultadoConsulta(List<PontoPrecisaoRevocacao> pontos, TabelaPrecisaoRevocacao tabela, Consulta consulta) {
		this.pontos = pontos;
		this.tabela = tabela;
		this.consulta = consulta;
	}

	public ResultadoConsulta(MetricaPrecisaoRevocacao metricaPrecisaoRevocacao, Consulta consulta) {
		this.pontos = metricaPrecisaoRevocacao.calcularPontos();
		this.tabela = metricaPrecisaoRevocacao.calcularTabela();
		this.consulta = consulta;
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

}
