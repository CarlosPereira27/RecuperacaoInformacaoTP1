package org.ufla.solr.rec_inf_tp1.func;

import java.io.IOException;

import org.apache.solr.common.cloud.SolrZkClient;
import org.ufla.solr.rec_inf_tp1.config.ConfigSolrClient;

/**
 * Responsável por adicionar uma configuração no Solr Cloud utilizando o Solr Cloud ZooKeeper.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class AdicionarConfiguracao {

	/**
	 * Adiciona da configuração, definida na classe de configuração
	 * {@link ConfigSolrClient}, na aplicação Solr.
	 * 
	 * @throws IOException
	 */
	public void adicionar() throws IOException {
		ConfigSolrClient configSolrClient = ConfigSolrClient.getInstance();
		System.out.printf(
				"\nIniciando upload da configuração '%s' localizada em '%s' usando o Solr Cloud ZooKeeper que está executando no host '%s:%d'.\n\n",
				configSolrClient.getConfiguracao(), configSolrClient.getDiretorioConfiguracao(),
				configSolrClient.getHostZooKeeper(), configSolrClient.getPortaZooKeeper());
		SolrZkClient zkClient = configSolrClient.getZkClient();

		zkClient.upConfig(configSolrClient.getPathDiretorioConfiguracao(), configSolrClient.getConfiguracao());
		System.out.println("\n\nUpload da configuração realizada!");
		zkClient.close();
	}

}
