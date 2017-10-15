package org.ufla.solr.rec_inf_tp1.func;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.common.util.NamedList;
import org.ufla.solr.rec_inf_tp1.config.ConfigSolrClient;

/**
 * Responsável por criar uma determinada coleção da aplicação Solr Cloud.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class CriarColecao {

	/**
	 * Cria a coleção, definida na classe de configuração
	 * {@link ConfigSolrClient}, na aplicação Solr.
	 * 
	 * @throws IOException
	 */
	public void criar() throws SolrServerException, IOException {
		ConfigSolrClient configSolrClient = ConfigSolrClient.getInstance();
		System.out.printf("\nCriando a coleção '%s' na aplicação Solr Cloud que está executando no host '%s'\n\n",
				configSolrClient.getInfoColecao(), configSolrClient.getInfoSolr());

		SolrClient solrClient = configSolrClient.getSolrClient();

		System.out.println("\nResposta:\n");
		NamedList<Object> resposta;
		CollectionAdminRequest.Create createRequest = CollectionAdminRequest.createCollection(configSolrClient.getColecao(),
				configSolrClient.getConfiguracao(), configSolrClient.getQtdShards(), configSolrClient.getQtdReplicas());
		createRequest.setMaxShardsPerNode(configSolrClient.getQtdShards());
		resposta = solrClient.request(createRequest);
		System.out.println(resposta);

		solrClient.close();
	}

}
