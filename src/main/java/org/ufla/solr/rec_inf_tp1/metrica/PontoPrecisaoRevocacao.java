package org.ufla.solr.rec_inf_tp1.metrica;

/**
 * Representa um ponto em duas dimensões, utilizado para definir pontos de
 * precisão por revocação.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class PontoPrecisaoRevocacao {

	public double precisao;
	public double revocacao;

	public PontoPrecisaoRevocacao(double precisao, double revocacao) {
		this.precisao = precisao;
		this.revocacao = revocacao;
	}

	/**
	 * Converte este ponto de precisão e revocação em um ponto genérico em duas
	 * dimensões.
	 * 
	 * @return ponto genérico em duas dimensões igual a este ponto
	 */
	public Ponto toPonto() {
		return new Ponto(revocacao, precisao);
	}

	@Override
	public String toString() {
		return "PontoPrecisaoRevocacao [precisao=" + precisao + ", revocacao=" + revocacao + "]";
	}

}
