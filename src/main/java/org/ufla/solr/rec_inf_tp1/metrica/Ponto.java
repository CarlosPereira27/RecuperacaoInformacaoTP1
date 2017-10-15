package org.ufla.solr.rec_inf_tp1.metrica;

/**
 * Representa um ponto em duas dimensões.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class Ponto {

	public double x;
	public double y;

	public Ponto(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Ponto [x=" + x + ", y=" + y + "]";
	}

}
