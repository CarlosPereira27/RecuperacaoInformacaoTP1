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
	 * Cria o cliente Solr para usar a aplicação.
	 * 
	 * @return cliente Solr para usar a aplicação
	 */
	public static SolrClient getSolrClient() {
		return new HttpSolrClient.Builder("http://" + URL + ":" + porta + "/solr/" + colecao).build();
	}

}
