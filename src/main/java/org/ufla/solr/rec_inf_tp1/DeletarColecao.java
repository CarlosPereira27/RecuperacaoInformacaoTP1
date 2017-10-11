package org.ufla.solr.rec_inf_tp1;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.common.util.NamedList;
import org.ufla.solr.rec_inf_tp1.config.ConfigSolrClient;

/**
 * Responsável por deletar uma determinada coleção da aplicação Solr.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class DeletarColecao {

	/**
	 * Deleta a coleção, definida na classe de configuração
	 * {@link ConfigSolrClient}, na aplicação Solr.
	 * 
	 * @throws IOException
	 */
	public void deletar() throws SolrServerException, IOException {
		System.out.printf("\nDeletando a coleção '%s' na aplicação Solr Cloud que está executando no host '%s'\n\n",
				ConfigSolrClient.colecao, ConfigSolrClient.getInfoSolr());

		SolrClient solrClient = ConfigSolrClient.getSolrClient();

		System.out.println("\nResposta:\n");
		NamedList<Object> resposta;
		CollectionAdminRequest.Delete deleteRequest = CollectionAdminRequest.deleteCollection(ConfigSolrClient.colecao);
		resposta = solrClient.request(deleteRequest);
		System.out.println(resposta);

		solrClient.close();
	}

}
