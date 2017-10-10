package org.ufla.solr.rec_inf_tp1;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.common.util.NamedList;
import org.ufla.solr.rec_inf_tp1.config.ConfigSolrClient;

public class CriarColecao {

	public static void main(String[] args) {
		System.out.println("Criando a coleção:");
		System.out.println(ConfigSolrClient.getInfoColecao());
		System.out.println("Na aplicação Solr que está escutando em: " + ConfigSolrClient.getInfoSolr());

		SolrClient solrClient = ConfigSolrClient.getSolrClient();

		System.out.println("Resposta!");
		NamedList<Object> resposta;
		try {
			CollectionAdminRequest.Create createRequest = CollectionAdminRequest.createCollection(
					ConfigSolrClient.colecao, ConfigSolrClient.configuracao, ConfigSolrClient.qtdShards,
					ConfigSolrClient.qtdReplicas);
			createRequest.setMaxShardsPerNode(ConfigSolrClient.qtdShards);
			resposta = solrClient.request(createRequest);
			System.out.println(resposta);
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}

		try {
			solrClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
