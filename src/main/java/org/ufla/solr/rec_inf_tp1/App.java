package org.ufla.solr.rec_inf_tp1;

import org.ufla.solr.rec_inf_tp1.config.ConfigBaseDeDados;
import org.ufla.solr.rec_inf_tp1.config.ConfigSolrClient;
import org.ufla.solr.rec_inf_tp1.extrator.ExtratorConfiguracoes;
import org.ufla.solr.rec_inf_tp1.model.AtributoGenerico;
import org.ufla.solr.rec_inf_tp1.model.MetaArgumento;

/**
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class App {

	private static final String HELP_MESSAGE = "Os comandos disponíveis são:\n"
			+ "java -jar recupInfSolr.jar -cmd criarColecao -host <host> -p <porta> -c <colecao> -conf <configuracao> -nshard <qtd_shards> -nReplicas <qtd_replicas>\n"
			+ "java -jar recupInfSolr.jar -cmd povoarColecao -host <host> -p <porta> -c <colecao> -bd <baseCFC>\n"
			+ "java -jar recupInfSolr.jar -cmd consultasERelatorio -host <host> -p <porta> -c <colecao> -bd <baseCFC> -out <arquivo>\n"

			+ "\nParâmetros do programa:\n"
			+ "-cmd <arg> -> comando a ser realizado (obrigatório), argumentos suportados: [criarColecao, povoarColecao, consultasERelatorio])\n"
			+ "-host <host> -> define o host da aplicação Solr, o padrão é localhost\n"
			+ "-p <porta> -> define a porta do host da aplicação Solr,o padrão é 8983\n"
			+ "-c <colecao> -> define a coleção utilizada\n"
			+ "-bd <baseCFC> -> define ocaminho para a base de dados CFC\n"
			+ "-out <arquivo> -> define o arquivo para salvar o relatório {consultasERelatorio}\n"
			+ "-conf <configuracao> -> define o nome da configuração utilizada na coleção {criarColecao}, o padrão é _default\n"
			+ "-nshard <qtd_shards> -> define a quantidade de shards da coleção {criarColecao}, o padrão é 2\n"
			+ "-nReplicas <qtd_replicas> -> define a quantidade de shards da coleção {criarColecao}, o padrão é 2"
			+ "-h ou -help (mostra a mensagem de ajuda)\n"

			+ "\nPara facilitar o uso dos parâmetros no programa, defina os parâmetros no arquivo de configuração.\n"
			+ "O arquivo de configuração deve chamar 'config.prop', e deve seguir o padrão do arquivo de configuração exemplo.\n";

	public static void displayHelpMessage() {
		System.out.println(HELP_MESSAGE);
	}

	/**
	 * Comando a ser executado.
	 */
	private static Comando cmd;
	/**
	 * Arquivo de saída para relatórios.
	 */
	private static String arquivoSaida;

	/**
	 * Define o valor de um argumento da aplicação.
	 * 
	 * @param configuracao
	 *            argumento e valor a ser definido o valor.
	 */
	private static void definirArgumento(AtributoGenerico configuracao) {
		definirArgumento((MetaArgumento) configuracao.getAtributo(), configuracao.getValor());
	}

	/**
	 * Define o valor de um argumento da aplicação.
	 * 
	 * @param argumento
	 *            argumento a ser definido o valor.
	 * @param valor
	 *            valor a ser definido
	 */
	private static void definirArgumento(MetaArgumento argumento, String valor) {
		switch (argumento) {
		case CMD:
			cmd = Comando.getComando(valor);
			if (cmd == null) {
				System.out.println(String.format("Comando (%s) não reconhecido!\n\n", valor));
				System.exit(0);
			}
			break;
		case C:
			ConfigSolrClient.colecao = valor;
			break;
		case HOST:
			ConfigSolrClient.URL = valor;
			break;
		case P:
			try {
				ConfigSolrClient.porta = Integer.parseInt(valor);
			} catch (NumberFormatException e) {
				erroDefinicaoDeNumero(valor, MetaArgumento.P.getNome());
			}
			break;
		case OUT:
			arquivoSaida = valor;
			break;
		case BD:
			ConfigBaseDeDados.setCaminhoAbslutoCFC(valor);
			break;
		case CONF:
			ConfigSolrClient.configuracao = valor;
			break;
		case N_SHARD:
			try {
				ConfigSolrClient.qtdShards = Integer.parseInt(valor);
			} catch (NumberFormatException e) {
				erroDefinicaoDeNumero(valor, MetaArgumento.N_SHARD.getNome());
			}
			break;
		case N_REPLICAS:
			try {
				ConfigSolrClient.qtdReplicas = Integer.parseInt(valor);
			} catch (NumberFormatException e) {
				erroDefinicaoDeNumero(valor, MetaArgumento.N_REPLICAS.getNome());
			}
			break;
		default:
			break;
		}
	}

	private static void erroDefinicaoDeNumero(String valor, String argumento) {
		System.out.println(String.format("Erro na definicação do argumento %s! O valor não é um número inteiro (%s).\n",
				argumento, valor));
		System.exit(0);
	}

	/**
	 * Extraí as configurações do arquivo de configuração.
	 */
	private static void extrairConfiguracoes() {
		ExtratorConfiguracoes extratorConfiguracoes = new ExtratorConfiguracoes();
		AtributoGenerico configuracao;
		while ((configuracao = extratorConfiguracoes.proximaConfiguracao()) != null) {
			// System.out.println(configuracao.getAtributo().getNome() + " =
			// "+configuracao.getValor());
			definirArgumento(configuracao);
		}
		System.out.println("Fim da extração de configurações do arquivo de configurações.\n\n");
	}

	public static void main(String[] args) throws Exception {
		extrairConfiguracoes();
		if (args.length == 1) {
			MetaArgumento argumento = MetaArgumento.getArgumento(args[0]);
			if (!(MetaArgumento.H.equals(argumento) || MetaArgumento.HELP.equals(argumento))) {
				System.out.println("Argumento não reconhecido!\n\n");
			}
			displayHelpMessage();
			System.exit(0);
		}
		if (args.length % 2 == 1) {
			System.out.println("Quantidade de argumentos errada!\n\n");
			displayHelpMessage();
			System.exit(0);
		}
		for (int i = 0; i < args.length; i += 2) {
			MetaArgumento argumento = MetaArgumento.getArgumento(args[i]);
			if (argumento == null || MetaArgumento.H.equals(argumento) || MetaArgumento.HELP.equals(argumento)) {
				System.out.println(String.format("Argumento (%s) não reconhecido!\n\n", args[i]));
				System.exit(0);
			}
			definirArgumento(argumento, args[i + 1]);
		}
		if (cmd == null) {
			System.out.println("Comando não definido!\n\n");
			System.exit(0);
		}

		switch (cmd) {
		case POVOAR_COLECAO:
			PovoarColecao.main(args);
			break;
		case CONSULTAS_E_RELATORIO:
			if (arquivoSaida == null) {
				ConsultaEAnaliseColecao.setWriter(System.out);
			} else {
				ConsultaEAnaliseColecao.setWriter(arquivoSaida);
			}
			ConsultaEAnaliseColecao.main(args);
			break;
		case CRIAR_COLECAO:
			CriarColecao.main(args);
			break;
		}

	}

}
