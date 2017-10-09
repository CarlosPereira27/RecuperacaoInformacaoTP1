package org.ufla.solr.rec_inf_tp1.teste.copy;

import org.ufla.solr.rec_inf_tp1.extrator.ExtratorDocumentos;
import org.ufla.solr.rec_inf_tp1.model.Documento;

public class TesteRecuperacaoDocumentos {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ExtratorDocumentos extrairDocumentos = new ExtratorDocumentos();
		Documento documento;

		int cont = 0;
		while ((documento = extrairDocumentos.proximoDocumento()) != null) {
			cont++;
		}

		System.out.println("QTD documentos -> " + cont);
	}

}
