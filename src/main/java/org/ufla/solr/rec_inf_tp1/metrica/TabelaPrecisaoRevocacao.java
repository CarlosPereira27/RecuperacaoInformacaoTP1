package org.ufla.solr.rec_inf_tp1.metrica;

/**
 * Representa a tabela com os 11 valores de precisão para os 11 níveis de
 * revocação utilizados.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class TabelaPrecisaoRevocacao {

	/**
	 * Quantidade de níveis de revocação.
	 */
	public static final int QTD_NIVEIS_REVOCACAO = 11;

	/**
	 * Array com os 11 níveis de revocação pré-determinados.
	 */
	public static final double[] NIVEIS_REVOCACAO = { 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };

	/**
	 * Array de precisões para os 11 níveis de revocação.
	 */
	private double[] precisao;

	/**
	 * Inicializa o array de precisões.
	 */
	public TabelaPrecisaoRevocacao() {
		precisao = new double[NIVEIS_REVOCACAO.length];
	}

	/**
	 * Define a precisão para um determinado nível de revocação (11 níveis).s
	 * 
	 * @param precisao
	 *            precisão a ser definida
	 * @param nivelRevocacao
	 *            nível de revocação referente a precisão
	 */
	public void setPrecisao(double precisao, int nivelRevocacao) {
		this.precisao[nivelRevocacao] = precisao;
	}

	/**
	 * Retorna a área abaixo do gráfico.
	 * 
	 * @return área abaixo do gráfico.
	 */
	public double getArea() {
		Ponto[] pontos = new Ponto[13];
		pontos[0] = new Ponto(0, 0);
		for (int i = 0; i < QTD_NIVEIS_REVOCACAO; i++) {
			pontos[i + 1] = new Ponto(NIVEIS_REVOCACAO[i], precisao[i]);
		}
		pontos[12] = new Ponto(100, 0);
		return AreaPoligono.areaPoligono(pontos);
	}

	public double[] getPrecisao() {
		return precisao;
	}

	public void setPrecisao(double[] precisao) {
		this.precisao = precisao;
	}

}
