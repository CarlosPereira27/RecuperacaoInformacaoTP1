package org.ufla.solr.rec_inf_tp1;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.common.util.NamedList;
import org.ufla.solr.rec_inf_tp1.config.ConfigSolrClient;

/**
 * Responsável por criar uma determinada coleção da aplicação Solr.
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
		System.out.printf("\nCriando a coleção '%s' na aplicação Solr Cloud que está executando no host '%s'\n\n",
				ConfigSolrClient.getInfoColecao(), ConfigSolrClient.getInfoSolr());

		SolrClient solrClient = ConfigSolrClient.getSolrClient();

		System.out.println("\nResposta:\n");
		NamedList<Object> resposta;
		CollectionAdminRequest.Create createRequest = CollectionAdminRequest.createCollection(ConfigSolrClient.colecao,
				ConfigSolrClient.configuracao, ConfigSolrClient.qtdShards, ConfigSolrClient.qtdReplicas);
		createRequest.setMaxShardsPerNode(ConfigSolrClient.qtdShards);
		resposta = solrClient.request(createRequest);
		System.out.println(resposta);

		solrClient.close();
	}

}
