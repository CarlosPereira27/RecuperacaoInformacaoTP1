package org.ufla.solr.rec_inf_tp1;

import java.io.IOException;

import org.apache.solr.common.cloud.SolrZkClient;
import org.ufla.solr.rec_inf_tp1.config.ConfigSolrClient;

/**
 * Responsável por adicionar uma configuração no Solr usando o Solr ZooKeeper.
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
		System.out.printf(
				"\nIniciando upload da configuração '%s' localizada em '%s' usando o Solr Cloud ZooKeeper que está executando no host '%s:%d'.\n\n",
				ConfigSolrClient.configuracao, ConfigSolrClient.diretorioConfiguracao, ConfigSolrClient.hostZooKeeper,
				ConfigSolrClient.portaZooKeeper);
		SolrZkClient zkClient = ConfigSolrClient.getZkClient();

		zkClient.upConfig(ConfigSolrClient.getPathDiretorioConfiguracao(), ConfigSolrClient.configuracao);
		System.out.println("\n\nUpload da configuração realizada!");
		zkClient.close();
	}

}
