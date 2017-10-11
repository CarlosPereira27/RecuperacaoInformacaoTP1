package org.ufla.solr.rec_inf_tp1;

import java.util.Locale;

import org.ufla.solr.rec_inf_tp1.config.ConfigBaseDeDados;
import org.ufla.solr.rec_inf_tp1.config.ConfigSolrClient;
import org.ufla.solr.rec_inf_tp1.extrator.ExtratorConfiguracoes;
import org.ufla.solr.rec_inf_tp1.model.AtributoGenerico;
import org.ufla.solr.rec_inf_tp1.model.MetaArgumento;

/**
 * Classe principal da aplicação.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class App {

	private static final String HELP_MESSAGE = "\nOs comandos disponíveis são:\n"
			+ "java -jar recInfTP1.jar -cmd addConfig -zkhost <host_zoo_keeper> -zkp <porta_zoo_keepper> -conf <nome_configuracao> -dirconf <diretorio_configuracao>\n"
			+ "java -jar recInfTP1.jar -cmd criarColecao -host <host> -p <porta> -c <colecao> -conf <nome_configuracao> -nshard <qtd_shards> -nReplicas <qtd_replicas>\n"
			+ "java -jar recInfTP1.jar -cmd deletarColecao -host <host> -p <porta> -c <colecao>\n"
			+ "java -jar recInfTP1.jar -cmd povoarColecao -host <host> -p <porta> -c <colecao> -bd <baseCFC>\n"
			+ "java -jar recInfTP1.jar -cmd consultasERelatorio -host <host> -p <porta> -c <colecao> -bd <baseCFC> -out <arquivo>\n"

			+ "\nParâmetros do programa:\n"
			+ "-cmd <arg> -> comando a ser realizado (obrigatório), argumentos suportados: [addConf, criarColecao, deletarColecao, povoarColecao, consultasERelatorio]\n"
			+ "-host <host> -> define o host da aplicação Solr, o padrão é localhost\n"
			+ "-p <porta> -> define a porta do host da aplicação Solr,o padrão é 8983\n"
			+ "-c <colecao> -> define a coleção utilizada\n"
			+ "-bd <baseCFC> -> define ocaminho para a base de dados CFC\n"
			+ "-out <arquivo> -> define o arquivo para salvar o relatório {consultasERelatorio}\n"
			+ "-conf <nome_configuracao> -> define o nome da configuração utilizada na coleção {criarColecao}, o padrão é _default\n"
			+ "-nshard <qtd_shards> -> define a quantidade de shards da coleção {criarColecao}, o padrão é 2\n"
			+ "-nReplicas <qtd_replicas> -> define a quantidade de shards da coleção {criarColecao}, o padrão é 2\n"
			+ "-dirconf <diretorio_configuracao> -> define o diretório de uma determinada configuração {addConf}\n"
			+ "-zkhost <host_zoo_keeper> ->  define o host da aplicação Solr ZooKeeper {addConf}, o padrão é localhost\n"
			+ "-zkp <porta_zoo_keepper> -> define a porta do host da aplicação Solr ZooKeeper {addConf},o padrão é 9983\n"
			+ "-h ou -help -> mostra a mensagem de ajuda\n"

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
	public static String arquivoSaida;

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
			ConfigSolrClient.host = valor;
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
		case DIR_CONF:
			ConfigSolrClient.diretorioConfiguracao = valor;
			break;
		case ZK_HOST:
			ConfigSolrClient.hostZooKeeper = valor;
			break;
		case ZK_P:
			try {
				ConfigSolrClient.portaZooKeeper = Integer.parseInt(valor);
			} catch (NumberFormatException e) {
				erroDefinicaoDeNumero(valor, MetaArgumento.ZK_P.getNome());
			}
			break;
		default:
			break;
		}
	}

	private static void erroDefinicaoDeNumero(String valor, String argumento) {
		System.out.println(String.format("Erro na definição do argumento %s! O valor não é um número inteiro (%s).\n",
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
			// System.out.println(configuracao.getAtributo().getNome() + " = " +
			// configuracao.getValor());
			definirArgumento(configuracao);
		}
		System.out.println("Fim da extração de configurações do arquivo de configurações.");
	}

	public static void main(String[] args) throws Exception {
		// Definição de locale em inglês para imprimir corretamente ponto
		// flutuando com o ponto sendo o divisor da parte inteira da
		// fracionária.
		Locale.setDefault(Locale.ENGLISH);
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
			PovoarColecao povoarColecao = new PovoarColecao();
			povoarColecao.povoar();
			break;
		case CONSULTAS_E_RELATORIO:
			ConsultaEAnaliseColecao consultaEAnaliseColecao = new ConsultaEAnaliseColecao();
			if (arquivoSaida == null) {
				consultaEAnaliseColecao.setWriter(System.out);
			} else {
				consultaEAnaliseColecao.setWriter(arquivoSaida);
			}
			consultaEAnaliseColecao.executar();
			break;
		case CRIAR_COLECAO:
			CriarColecao criarColecao = new CriarColecao();
			criarColecao.criar();
			break;
		case DELETAR_COLECAO:
			DeletarColecao deletarColecao = new DeletarColecao();
			deletarColecao.deletar();
			break;
		case ADICIONAR_CONFIGURACAO:
			AdicionarConfiguracao adiconarConfiguracao = new AdicionarConfiguracao();
			adiconarConfiguracao.adicionar();
			break;
		default:
			break;
		}

	}

}
