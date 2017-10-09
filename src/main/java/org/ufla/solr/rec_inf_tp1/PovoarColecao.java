package org.ufla.solr.rec_inf_tp1;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.schema.SchemaRequest;
import org.ufla.solr.rec_inf_tp1.campo.FabricaCampos;
import org.ufla.solr.rec_inf_tp1.config.ConfigSolrClient;
import org.ufla.solr.rec_inf_tp1.extrator.ExtratorDocumentos;
import org.ufla.solr.rec_inf_tp1.model.Documento;
import org.ufla.solr.rec_inf_tp1.model.MetaAtributoDocumento;

/**
 * Responsável por criar os campos no schema do Solr e adicionar os documentos
 * na coleção definida no Solr.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class PovoarColecao {

	/**
	 * Cria os campos CONT e RN no schema do Solr.
	 * 
	 * @param solr
	 *            cliente Solr
	 */
	private static void criaCampos(SolrClient solr) {
		FabricaCampos fabricaCampos = new FabricaCampos();
		try {
			solr.request(new SchemaRequest.AddField(fabricaCampos.criarCampoCONT()));
			solr.request(new SchemaRequest.AddField(fabricaCampos.criarCampoRN()));
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deleta os campos CONT e RN no schema do Solr.
	 * 
	 * @param solr
	 *            cliente Solr
	 */
	private static void deletaCampos(SolrClient solr) {
		try {
			solr.request(new SchemaRequest.DeleteField(MetaAtributoDocumento.CONT.getNome()));
			solr.request(new SchemaRequest.DeleteField(MetaAtributoDocumento.RN.getNome()));
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deleta a base de dados do Solr.
	 * 
	 * @param solr
	 *            cliente Solr
	 */
	private static void deletaBaseDeDados(SolrClient solr) {
		try {
			System.out.println("Respostar deletar -> " + solr.deleteByQuery("*:*"));
			deletaCampos(solr);
			System.out.println("Commit -> " + solr.commit());
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) throws Exception {
		// Preparando o cliente Solr
		SolrClient solr = ConfigSolrClient.getSolrClient();

		deletaBaseDeDados(solr);

		criaCampos(solr);

		ExtratorDocumentos extratorDocumentos = new ExtratorDocumentos();
		Documento documento;

		while ((documento = extratorDocumentos.proximoDocumento()) != null) {
			solr.add(documento.toSolrInputDocument());
		}

		// Salva as mudanças
		System.out.println("Commit -> " + solr.commit());
		System.out.println("Documents added");

		try {
			solr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
