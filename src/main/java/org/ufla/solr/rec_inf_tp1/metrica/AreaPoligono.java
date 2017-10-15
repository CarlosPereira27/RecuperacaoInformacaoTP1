package org.ufla.solr.rec_inf_tp1.metrica;

/**
 * Responsável por calcular a área de um determinado polígono irregular.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class AreaPoligono {

	/**
	 * Calcula a área de um polígono irregular.
	 * 
	 * @param pontos
	 *            array de pontos do polígono.
	 * @return área do polígono.
	 */
	public static double areaPoligono(Ponto[] pontos) {
		double area = 0;
		for (int i = 1; i < pontos.length; i++) {
			area += pontos[i - 1].x * pontos[i].y;
			area -= pontos[i - 1].y * pontos[i].x;
		}
		area += pontos[pontos.length - 1].x * pontos[0].y;
		area -= pontos[pontos.length - 1].y * pontos[0].x;
		return Math.abs(area / 2.0);
	}

}
