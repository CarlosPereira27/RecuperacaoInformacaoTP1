package org.ufla.solr.rec_inf_tp1.func;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.ufla.solr.rec_inf_tp1.config.ConfigBaseDeDados;
import org.ufla.solr.rec_inf_tp1.config.ConfigSolrClient;
import org.ufla.solr.rec_inf_tp1.extrator.ExtratorConsultas;
import org.ufla.solr.rec_inf_tp1.metrica.MetricaPrecisaoRevocacao;
import org.ufla.solr.rec_inf_tp1.model.Consulta;
import org.ufla.solr.rec_inf_tp1.model.MetaAtributoDocumento;

/**
 * Responsável por consultar os documentos na coleção definida no Solr e
 * analisar o resultado.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class ConsultaEAnaliseColecao {

	private static final Integer ROWS_START = 0;
	private static final Integer ROWS_VALOR = 1239;
	private static final String FIELD_LIST_ITEM = "RN";
	private static final String SORT_ITEM = "score";
	private static final String DEFAULT_SEARCH_FIELD_PARAM = "df";
	private static final String DEFAULT_SEARCH_FIELD_VALOR = "CONT";

	/**
	 * Fluxo de saída de dados, onde será escrito o relatório das consultas.
	 */
	private BufferedWriter bw;

	/**
	 * Arquivo de saída para relatórios.
	 */
	private String arquivoSaida;

	/**
	 * Define o fluxo de saída de dados, onde será escrito o relatório das
	 * consultas.
	 * 
	 * @param arquivoSaida
	 *            nome do arquivo em que deverá escrever o relatório
	 */
	public void setWriter(String arquivoSaida) {
		try {
			this.arquivoSaida = arquivoSaida;
			setWriter(new FileOutputStream(arquivoSaida));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new RuntimeException(String.format("Arquivo %s não existe!\n", arquivoSaida));
		}
	}

	/**
	 * Define o fluxo de saída de dados, onde será escrito o relatório das
	 * consultas.
	 * 
	 * @param out
	 *            fluxo de saída de dados
	 */
	public void setWriter(OutputStream out) {
		this.bw = new BufferedWriter(new OutputStreamWriter(out));
	}

	/**
	 * Realiza uma consulta na aplicação Solr.
	 * 
	 * @param solr
	 *            cliente Solr
	 * @param consulta
	 *            consulta a ser realizada.
	 * @return lista com identificadores dos documentos recuperados pela
	 *         consulta.
	 */
	private List<Integer> consultar(SolrClient solr, Consulta consulta) {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setStart(ROWS_START).setRows(ROWS_VALOR).setSort(SortClause.desc(SORT_ITEM))
				.setFields(FIELD_LIST_ITEM)
				.setQuery(String.format("%s:%s", MetaAtributoDocumento.CONT, consulta.getQueryText()))
				.add(DEFAULT_SEARCH_FIELD_PARAM, DEFAULT_SEARCH_FIELD_VALOR);
		List<Integer> documentosRecuperados = new ArrayList<>();
		try {
			QueryResponse queryResponse = solr.query(solrQuery);
			SolrDocumentList resultado = queryResponse.getResults();
			for (SolrDocument doc : resultado) {
				documentosRecuperados.add((Integer) doc.getFieldValue(MetaAtributoDocumento.RN.getNome()));
			}
		} catch (SolrServerException | IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return documentosRecuperados;
	}

	/**
	 * Realiza as consultas e a análise dos resultados.
	 * 
	 * @throws IOException
	 */
	public void executar() throws IOException {
		ConfigSolrClient configSolrClient = ConfigSolrClient.getInstance();
		System.out.printf(
				"\nIniciando consultas da base de dados cfc localizada em '%s/cfcquery' na coleção '%s' da aplicação Solr Cloud, que está executando no host '%s'.\n\n",
				ConfigBaseDeDados.getInstance().getDiretorioCFC(), configSolrClient.getColecao(),
				configSolrClient.getInfoSolr());
		// Preparando o cliente Solr
		SolrClient solr = configSolrClient.getSolrClientCollection();

		ExtratorConsultas extratorConsultas = new ExtratorConsultas();
		Consulta consulta;

		Relatorio relatorio = new Relatorio(bw);

		while ((consulta = extratorConsultas.proximaConsulta()) != null) {
			List<Integer> documentosRecuperados = consultar(solr, consulta);
			MetricaPrecisaoRevocacao metrica = new MetricaPrecisaoRevocacao(consulta.getDocumentosRelevantes(),
					documentosRecuperados);
			ResultadoConsulta resultadoConsulta = new ResultadoConsulta(metrica, consulta);
			relatorio.addResultado(resultadoConsulta);
		}

		relatorio.gerarRelatorio();

		bw.close();

		System.out.printf("\nConsultas realizadas com sucesso!\nRelatório gerado com sucesso no arquivo '%s'\n\n",
				arquivoSaida);

		// Gerar gráficos
		if (arquivoSaida != null) {
			GerarGraficos gerarGraficos = new GerarGraficos(arquivoSaida);
			gerarGraficos.gerar();
		}

		solr.close();

	}

}
