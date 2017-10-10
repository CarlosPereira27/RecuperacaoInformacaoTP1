package org.ufla.solr.rec_inf_tp1.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

/**
 * Representação das configurações da aplicação Solr.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public abstract class ConfigSolrClient {

	/**
	 * URL da aplicação Solr
	 */
	public static String URL = "localhost";

	/**
	 * Porta utilizada pela aplicação Solr.
	 */
	public static int porta = 8983;

	/**
	 * Coleção a ser utilizada da aplicação Solr.
	 */
	public static String colecao = "CFC2";

	/**
	 * Nome da configuração da coleção.
	 */
	public static String configuracao = "_default";

	/**
	 * Quantidade de shards da coleção.
	 */
	public static Integer qtdShards = 2;

	/**
	 * Quantidade de réplicas da coleção.
	 */
	public static Integer qtdReplicas = 2;

	/**
	 * Recupera as informações da coleção.
	 * 
	 * @return string com as informações da coleção.
	 */
	public static String getInfoColecao() {
		return String.format("Coleção:%s, configuração:%s, qtdShards:%d, qtdReplicas=%d", colecao, configuracao,
				qtdShards, qtdReplicas);
	}

	/**
	 * Recupera as informações do Solr.
	 * 
	 * @return string com as informações do Solr.
	 */
	public static String getInfoSolr() {
		return String.format("http://%s:%d", URL, porta);
	}

	/**
	 * Cria o cliente Solr para usar a aplicação Solr com a coleção.
	 * 
	 * @return cliente Solr para usar a aplicação Solr com a coleção
	 */
	public static SolrClient getSolrClientCollection() {
		return new HttpSolrClient.Builder("http://" + URL + ":" + porta + "/solr/" + colecao).build();
	}

	/**
	 * Cria o cliente Solr para usar a aplicação Solr.
	 * 
	 * @return cliente Solr para usar a aplicação Solr
	 */
	public static SolrClient getSolrClient() {
		return new HttpSolrClient.Builder("http://" + URL + ":" + porta + "/solr/").build();
	}

}
