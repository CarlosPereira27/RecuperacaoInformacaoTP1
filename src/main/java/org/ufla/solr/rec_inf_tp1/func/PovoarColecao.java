package org.ufla.solr.rec_inf_tp1.func;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.schema.SchemaRequest;
import org.ufla.solr.rec_inf_tp1.campo.FabricaCampos;
import org.ufla.solr.rec_inf_tp1.config.ConfigBaseDeDados;
import org.ufla.solr.rec_inf_tp1.config.ConfigSolrClient;
import org.ufla.solr.rec_inf_tp1.extrator.ExtratorDocumentos;
import org.ufla.solr.rec_inf_tp1.model.Documento;
import org.ufla.solr.rec_inf_tp1.model.MetaAtributoDocumento;
import org.ufla.solr.rec_inf_tp1.preprocessing.PreProcessamento;

/**
 * Responsável por criar os campos no schema do Solr e adicionar os documentos
 * na coleção definida no Solr.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class PovoarColecao {

	/**
	 * Configuração do Cliente Solr.
	 */
	private ConfigSolrClient configSolrClient;

	/**
	 * Inicializa a configuração do Cliente Solr
	 */
	public PovoarColecao() {
		configSolrClient = ConfigSolrClient.getInstance();
	}

	/**
	 * Cria os campos CONT e RN no schema do Solr.
	 * 
	 * @param solr
	 *            cliente Solr
	 */
	private void criaCampos(SolrClient solr) {
		FabricaCampos fabricaCampos = new FabricaCampos();
		try {
			System.out.printf("\nAdicionando os campos '%s' e '%s' na coleção '%s'.\n\n",
					MetaAtributoDocumento.RN.getNome(), MetaAtributoDocumento.CONT.getNome(),
					configSolrClient.getColecao());
			solr.deleteByQuery("*:*");
			solr.request(new SchemaRequest.AddField(fabricaCampos.criarCampoCONT()));
			solr.request(new SchemaRequest.AddField(fabricaCampos.criarCampoRN()));
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deleta os campos CONT e RN no schema do Solr.
	 * 
	 * @param solr
	 *            cliente Solr
	 */
	private void deletaCampos(SolrClient solr) {
		try {
			solr.request(new SchemaRequest.DeleteField(MetaAtributoDocumento.CONT.getNome()));
			solr.request(new SchemaRequest.DeleteField(MetaAtributoDocumento.RN.getNome()));
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deleta a base de dados do Solr.
	 * 
	 * @param solr
	 *            cliente Solr
	 */
	private void deletaBaseDeDados(SolrClient solr) {
		try {
			System.out.printf(
					"\nDeletando todos documentos contidos na coleção '%s'.\nDeletando os campos '%s' e '%s' da coleção '%s' se existir. Pois, estes campos serão configurados para serem usados\n",
					configSolrClient.getColecao(), MetaAtributoDocumento.RN.getNome(),
					MetaAtributoDocumento.CONT.getNome(), configSolrClient.getColecao());
			solr.deleteByQuery("*:*");
			deletaCampos(solr);
			System.out.println("Commit -> " + solr.commit());
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Povoa a coleção, definida na classe de configuração
	 * {@link ConfigSolrClient}, com a base de dados CFC, definida na classe de
	 * configuração {@link ConfigBaseDeDados}, na aplicação Solr.
	 * 
	 * @throws IOException
	 */
	public void povoar() throws Exception {
		System.out.printf(
				"\nIniciando povoamento da coleção '%s' da aplicação Solr Cloud, que está executando no host '%s'. O povoamento é feito com a base de dados cfc localizada em '%s/'.\n\n",
				configSolrClient.getColecao(), configSolrClient.getInfoSolr(),
				ConfigBaseDeDados.getInstance().getDiretorioCFC());
		// Preparando o cliente Solr
		SolrClient solr = configSolrClient.getSolrClientCollection();

		deletaBaseDeDados(solr);

		criaCampos(solr);

		ExtratorDocumentos extratorDocumentos = new ExtratorDocumentos();
		Documento documento;

		while ((documento = extratorDocumentos.proximoDocumento()) != null) {
			if (configSolrClient.getPreProcessamento()) {
				documento.setConteudo(PreProcessamento.processText(documento.getConteudo()));
			}
			solr.add(documento.toSolrInputDocument());
		}

		// Salva as mudanças
		System.out.println("\nCommit -> " + solr.commit());
		System.out.println("Documentos adicionados com sucesso!");

		solr.close();

	}

}
