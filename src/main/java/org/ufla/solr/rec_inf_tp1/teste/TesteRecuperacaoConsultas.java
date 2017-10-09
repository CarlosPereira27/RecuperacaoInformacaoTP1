package org.ufla.solr.rec_inf_tp1.teste;

import org.ufla.solr.rec_inf_tp1.extrator.ExtratorConsultas;
import org.ufla.solr.rec_inf_tp1.model.Consulta;

public class TesteRecuperacaoConsultas {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ExtratorConsultas extratorConsultas = new ExtratorConsultas();
		Consulta consulta;
		int cont = 0;
		while ((consulta = extratorConsultas.proximaConsulta()) != null) {
			cont++;
		}
		System.out.println("QTD consultas -> " + cont);
	}

}
