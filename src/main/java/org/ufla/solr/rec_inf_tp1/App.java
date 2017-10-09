package org.ufla.solr.rec_inf_tp1;

import org.ufla.solr.rec_inf_tp1.config.ConfigBaseDeDados;
import org.ufla.solr.rec_inf_tp1.config.ConfigSolrClient;

/**
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class App {

	private static final String HELP_MESSAGE = "Para executar digite:\n" + "java -jar recupInfSolr.jar\n"
			+ "Parâmetros do programa:\n"
			+ "-cmd <arg> (obrigatório, argumentos suportados: [povoarColecao, consultasERelatorio])\n"
			+ "-c <colecao> (usado para definir a coleção utilizada)\n"
			+ "-host <host> (usado para definir o host da aplicação Solr, o padrão é localhost)\n"
			+ "-p <porta> (usado para definir a porta do host da aplicação Solr,o padrão é 8983)\n"
			+ "-out <arquivo> (usado para definir o arquivo para salvar o relatório {consultasERelatorio})\n"
			+ "-bd <baseCFC> (caminho para a base de dados CFC)\n" + "-h ou -help (mostra a mensagem de ajuda)";

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
	 * @param argumento
	 *            argumento a ser definido o valor.
	 * @param valor
	 *            valor a ser definido
	 */
	private static void definirArgumento(Argumento argumento, String valor) {
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
			ConfigSolrClient.porta = Integer.parseInt(valor);
			break;
		case OUT:
			arquivoSaida = valor;
			break;
		case BD:
			ConfigBaseDeDados.setCaminhoAbslutoCFC(valor);
			break;
		default:
			break;
		}
	}

	public static void main(String[] args) throws Exception {
		if (args.length == 1) {
			Argumento argumento = Argumento.getArgumento(args[0]);
			if (!(Argumento.H.equals(argumento) || Argumento.HELP.equals(argumento))) {
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
			Argumento argumento = Argumento.getArgumento(args[i]);
			if (argumento == null || Argumento.H.equals(argumento) || Argumento.HELP.equals(argumento)) {
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
		}

	}

}
