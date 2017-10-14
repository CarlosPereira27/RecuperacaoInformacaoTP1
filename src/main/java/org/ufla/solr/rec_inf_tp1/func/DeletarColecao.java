package org.ufla.solr.rec_inf_tp1.func;

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
		ConfigSolrClient configSolrClient = ConfigSolrClient.getInstance();
		System.out.printf("\nDeletando a coleção '%s' na aplicação Solr Cloud que está executando no host '%s'\n\n",
				configSolrClient.getColecao(), configSolrClient.getInfoSolr());

		SolrClient solrClient = configSolrClient.getSolrClient();

		System.out.println("\nResposta:\n");
		NamedList<Object> resposta;
		CollectionAdminRequest.Delete deleteRequest = CollectionAdminRequest.deleteCollection(configSolrClient.getColecao());
		resposta = solrClient.request(deleteRequest);
		System.out.println(resposta);

		solrClient.close();
	}

}
