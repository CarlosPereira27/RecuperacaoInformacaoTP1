package org.ufla.solr.rec_inf_tp1.config;

import java.io.File;
import java.nio.file.Path;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.cloud.SolrZkClient;

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
	 * Host da aplicação Solr
	 */
	public static String host = "localhost";

	/**
	 * Porta utilizada pela aplicação Solr.
	 */
	public static int porta = 8983;

	/**
	 * Coleção a ser utilizada da aplicação Solr.
	 */
	public static String colecao = "CFC";

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
	 * Diretório de uma determinada configuração.
	 */
	public static String diretorioConfiguracao;

	/**
	 * Host da aplicação Solr ZooKeeper.
	 */
	public static String hostZooKeeper;

	/**
	 * Porta utilizada pela aplicação Solr ZooKeeper.
	 */
	public static Integer portaZooKeeper;

	public static Path getPathDiretorioConfiguracao() {
		return new File(diretorioConfiguracao).toPath();
	}

	/**
	 * Recupera as informações da coleção.
	 * 
	 * @return string com as informações da coleção.
	 */
	public static String getInfoColecao() {
		return String.format("%s (configuração:%s, qtdShards:%d, qtdReplicas=%d)", colecao, configuracao,
				qtdShards, qtdReplicas);
	}

	/**
	 * Recupera as informações do Solr.
	 * 
	 * @return string com as informações do Solr.
	 */
	public static String getInfoSolr() {
		return String.format("http://%s:%d", host, porta);
	}

	/**
	 * Recupera as informações do Solr ZooKeeper.
	 * 
	 * @return string com as informações do Solr ZooKeeper.
	 */
	public static String getInfoSolrZooKeeper() {
		return String.format("%s:%d", hostZooKeeper, portaZooKeeper);
	}

	/**
	 * Cria o cliente Solr para usar a aplicação Solr com a coleção.
	 * 
	 * @return cliente Solr para usar a aplicação Solr com a coleção
	 */
	public static SolrClient getSolrClientCollection() {
		return new HttpSolrClient.Builder(getInfoSolr() + "/solr/" + colecao).build();
	}

	/**
	 * Cria o cliente Solr para usar a aplicação Solr.
	 * 
	 * @return cliente Solr para usar a aplicação Solr
	 */
	public static SolrClient getSolrClient() {
		return new HttpSolrClient.Builder(getInfoSolr() + "/solr/").build();
	}

	/**
	 * Cria o cliente Cloud Solr para usar a aplicação Cloud Solr.
	 * 
	 * @return cliente Cloud Solr para usar a aplicação Cloud Solr
	 */
	public static CloudSolrClient getCloudSolrClient() {
		return new CloudSolrClient.Builder().withSolrUrl(getInfoSolr() + "/solr/").withZkHost(getInfoSolrZooKeeper())
				.build();
	}

	private static final int ZK_CLIENT_TIMEOUT = 500;

	/**
	 * Cria o cliente Solr ZooKeeper para usar a aplicação Solr ZooKeeper.
	 * 
	 * @return cliente Solr ZooKeeper para usar a aplicação Solr ZooKeeper
	 */
	public static SolrZkClient getZkClient() {
		return new SolrZkClient(getInfoSolrZooKeeper(), ZK_CLIENT_TIMEOUT);
	}

}
