package org.ufla.solr.rec_inf_tp1.config;

import java.io.File;
import java.nio.file.Path;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.cloud.SolrZkClient;

/**
 * Representação das configurações da aplicação Solr. Utiliza o padrão de
 * projeto singleton.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class ConfigSolrClient {

	/**
	 * Única instância de ConfigSolrClient da aplicação (Singleton)
	 */
	private static ConfigSolrClient configSolrClient;

	/**
	 * Host da aplicação Solr
	 */
	private String host = "localhost";

	/**
	 * Porta utilizada pela aplicação Solr.
	 */
	private int porta = 8983;

	/**
	 * Coleção a ser utilizada da aplicação Solr.
	 */
	private String colecao = "CFC";

	/**
	 * Nome da configuração da coleção.
	 */
	private String configuracao = "_default";

	/**
	 * Quantidade de shards da coleção.
	 */
	private Integer qtdShards = 2;

	/**
	 * Quantidade de réplicas da coleção.
	 */
	private Integer qtdReplicas = 2;

	/**
	 * Diretório de uma determinada configuração.
	 */
	private String diretorioConfiguracao;

	/**
	 * Host da aplicação Solr ZooKeeper.
	 */
	private String hostZooKeeper;

	/**
	 * Porta utilizada pela aplicação Solr ZooKeeper.
	 */
	private Integer portaZooKeeper;

	/**
	 * Define se deverá utilizar as implementações de pré-processamento contidas
	 * neste projeto.
	 */
	private Boolean preProcessamento = true;

	/**
	 * Valor sim do pré-processamento
	 */
	private static final String PRE_PROCESSAMENTO_SIM = "SIM";

	/**
	 * Construtor privado para implementação do padrão Singleton
	 */
	private ConfigSolrClient() {

	}

	/**
	 * Define se deve ter o pré-processamento implementado neste projeto ou não.
	 * 
	 * @param preProcessamento
	 *            string com valor [sim, nao] informando se deve ter
	 *            pré-processamento ou não
	 */
	public void setPreProcessamento(String preProcessamento) {
		this.preProcessamento = PRE_PROCESSAMENTO_SIM.startsWith(preProcessamento);
	}

	/**
	 * Retorna a única instância de ConfigSolrClient.
	 * 
	 * @return única instância de ConfigSolrClient
	 */
	public static ConfigSolrClient getInstance() {
		if (configSolrClient == null) {
			configSolrClient = new ConfigSolrClient();
		}
		return configSolrClient;
	}

	public Path getPathDiretorioConfiguracao() {
		return new File(diretorioConfiguracao).toPath();
	}

	/**
	 * Recupera as informações da coleção.
	 * 
	 * @return string com as informações da coleção.
	 */
	public String getInfoColecao() {
		return String.format("%s (configuração:%s, qtdShards:%d, qtdReplicas=%d)", colecao, configuracao, qtdShards,
				qtdReplicas);
	}

	/**
	 * Recupera as informações do Solr.
	 * 
	 * @return string com as informações do Solr.
	 */
	public String getInfoSolr() {
		return String.format("http://%s:%d", host, porta);
	}

	/**
	 * Recupera as informações do Solr ZooKeeper.
	 * 
	 * @return string com as informações do Solr ZooKeeper.
	 */
	public String getInfoSolrZooKeeper() {
		return String.format("%s:%d", hostZooKeeper, portaZooKeeper);
	}

	/**
	 * Cria o cliente Solr para usar a aplicação Solr com a coleção.
	 * 
	 * @return cliente Solr para usar a aplicação Solr com a coleção
	 */
	public SolrClient getSolrClientCollection() {
		return new HttpSolrClient.Builder(getInfoSolr() + "/solr/" + colecao).build();
	}

	/**
	 * Cria o cliente Solr para usar a aplicação Solr.
	 * 
	 * @return cliente Solr para usar a aplicação Solr
	 */
	public SolrClient getSolrClient() {
		return new HttpSolrClient.Builder(getInfoSolr() + "/solr/").build();
	}

	/**
	 * Cria o cliente Cloud Solr para usar a aplicação Cloud Solr.
	 * 
	 * @return cliente Cloud Solr para usar a aplicação Cloud Solr
	 */
	public CloudSolrClient getCloudSolrClient() {
		return new CloudSolrClient.Builder().withSolrUrl(getInfoSolr() + "/solr/").withZkHost(getInfoSolrZooKeeper())
				.build();
	}

	private static final int ZK_CLIENT_TIMEOUT = 500;

	/**
	 * Cria o cliente Solr ZooKeeper para usar a aplicação Solr ZooKeeper.
	 * 
	 * @return cliente Solr ZooKeeper para usar a aplicação Solr ZooKeeper
	 */
	public SolrZkClient getZkClient() {
		return new SolrZkClient(getInfoSolrZooKeeper(), ZK_CLIENT_TIMEOUT);
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}

	public String getColecao() {
		return colecao;
	}

	public void setColecao(String colecao) {
		this.colecao = colecao;
	}

	public String getConfiguracao() {
		return configuracao;
	}

	public void setConfiguracao(String configuracao) {
		this.configuracao = configuracao;
	}

	public Integer getQtdShards() {
		return qtdShards;
	}

	public void setQtdShards(Integer qtdShards) {
		this.qtdShards = qtdShards;
	}

	public Integer getQtdReplicas() {
		return qtdReplicas;
	}

	public void setQtdReplicas(Integer qtdReplicas) {
		this.qtdReplicas = qtdReplicas;
	}

	public String getDiretorioConfiguracao() {
		return diretorioConfiguracao;
	}

	public void setDiretorioConfiguracao(String diretorioConfiguracao) {
		this.diretorioConfiguracao = diretorioConfiguracao;
	}

	public String getHostZooKeeper() {
		return hostZooKeeper;
	}

	public void setHostZooKeeper(String hostZooKeeper) {
		this.hostZooKeeper = hostZooKeeper;
	}

	public Integer getPortaZooKeeper() {
		return portaZooKeeper;
	}

	public void setPortaZooKeeper(Integer portaZooKeeper) {
		this.portaZooKeeper = portaZooKeeper;
	}

	public Boolean getPreProcessamento() {
		return preProcessamento;
	}

	public void setPreProcessamento(Boolean preProcessamento) {
		this.preProcessamento = preProcessamento;
	}

}
