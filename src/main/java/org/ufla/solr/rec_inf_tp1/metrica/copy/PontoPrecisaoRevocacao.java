package org.ufla.solr.rec_inf_tp1.metrica.copy;

/**
 * Representa um ponto em duas dimensões, utilizado para definir pontos de precisão
 * por revocação.
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

	@Override
	public String toString() {
		return "PontoPrecisaoRevocacao [precisao=" + precisao + ", revocacao=" + revocacao + "]";
	}

}
