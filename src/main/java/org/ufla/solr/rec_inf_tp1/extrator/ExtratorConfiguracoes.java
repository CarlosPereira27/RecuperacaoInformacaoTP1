package org.ufla.solr.rec_inf_tp1.extrator;

import java.io.File;

import org.ufla.solr.rec_inf_tp1.model.AtributoGenerico;
import org.ufla.solr.rec_inf_tp1.model.MetaArgumento;
import org.ufla.solr.rec_inf_tp1.model.MetaAtributo;

/**
 * Responsável por extrair as configurações do arquivo de configuração.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class ExtratorConfiguracoes extends ExtratorGenerico {

	/**
	 * Arquivo de configuração.
	 */
	private static final File ARQUIVO_CONFIGURACAO = new File("config.prop");

	/**
	 * Inicializa as configurações do extrator de consultas da base de dados.
	 */
	public ExtratorConfiguracoes() {
		System.out.println("Iniciando extração da configuração!");
		setArquivo(ARQUIVO_CONFIGURACAO);
	}

	/**
	 * Recupera o índice do caracter de atribuição na string do valor de um
	 * atributo se este estiver definido corretamente.
	 * 
	 * @param valor
	 *            string que contém a atribuição e o valor da configuração
	 * @return índice do caracter de atribuição se este estiver definido
	 *         corretamente, -1 caso contrário
	 */
	private int indiceAtribuicao(String valor) {
		int indice = valor.indexOf('=');
		if (indice == -1) {
			return -1;
		}
		for (int i = 0; i < indice; i++) {
			if (!Character.isWhitespace(valor.charAt(i))) {
				return -1;
			}
		}
		return indice;
	}

	/**
	 * Recupera a linha de definição de uma configuração.
	 * 
	 * @param configuracao
	 *            configuração definida na linha
	 * @return linha de definição de uma configuração
	 */
	private String getLinha(AtributoGenerico configuracao) {
		return configuracao.getAtributo().getNome() + configuracao.getValor();
	}

	/**
	 * Realiza a leitura da próxima configuração do arquivo de configurações.
	 * 
	 * @return próxima configuração do arquivo de configurações, ou null caso a
	 *         base de dados tenha acabado.
	 */
	public AtributoGenerico proximaConfiguracao() {
		AtributoGenerico configuracao;
		int indiceAtr;
		do {
			configuracao = proximoAtributo();
			if (configuracao == null) {
				fechaLeitor();
				return null;
			}
			indiceAtr = indiceAtribuicao(configuracao.getValor());
			if (indiceAtr == -1) {
				System.out.println(configuracao.getValor());
				System.out.println(String.format(
						"Problema encontrado na linha %d do arquivo de configuração, linha abaixo:\n\"%s\"\n",
						numLinhaAtual - 1, getLinha(configuracao)));
			}
			configuracao.setValor(configuracao.getValor().substring(indiceAtr + 1).trim());

		} while (indiceAtr == -1);

		return configuracao;

	}

	@Override
	protected void leLinha() {
		super.leLinha();
		if (linhaAtual != null) {
			linhaAtual = linhaAtual.trim();
		}
	}

	@Override
	public boolean pularLinhasInuteis() {
		while (linhaAtual != null && (linhaAtual.trim().isEmpty() || linhaAtual.startsWith("#"))) {
			leLinha();
		}
		if (isFinalDeArquivo()) {
			return false;
		}
		return true;
	}

	@Override
	public MetaAtributo getContAtributo() {
		return MetaArgumento.CONT_DEF_ATR;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<MetaArgumento> getMetaAtributoClass() {
		return MetaArgumento.class;
	}

}
