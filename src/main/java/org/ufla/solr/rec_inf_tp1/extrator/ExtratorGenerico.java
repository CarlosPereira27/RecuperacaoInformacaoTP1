package org.ufla.solr.rec_inf_tp1.extrator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.ufla.solr.rec_inf_tp1.model.AtributoGenerico;
import org.ufla.solr.rec_inf_tp1.model.MetaAtributo;
import org.ufla.solr.rec_inf_tp1.model.MetaAtributoConsulta;
import org.ufla.solr.rec_inf_tp1.model.MetaAtributoDocumento;

/**
 * Classe responsável por implementar métodos genéricos para extrair dados de um
 * arquivo.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public abstract class ExtratorGenerico {

	/**
	 * Arquivo de consultas que está sendo processado.
	 */
	private File arquivo;
	/**
	 * Leitor do arquivo atual.
	 */
	private BufferedReader br;
	/**
	 * Última linha que foi lida do arquivo atual.
	 */
	protected String linhaAtual;
	/**
	 * Número da linha atual
	 */
	protected int numLinhaAtual = 0;

	/**
	 * Caractere especial que está contido em alguns arquivos da base de dados
	 * para marcar a sua última linha.
	 */
	private static final char FINAL = '';

	public ExtratorGenerico() {
	}

	/**
	 * Lê uma linha do arquivo atual e salva na variável de instância
	 * linhaAtual. Esse método trata a exceção de leitura apenas imprimindo sua
	 * pilha de execução e a relançando como exceção de runtime (não
	 * verificada). Este processo é realizado, pois os arquivos são controlados
	 * e acredita que não será lançada exceção.
	 */
	protected void leLinha() {
		try {
			linhaAtual = br.readLine();
			numLinhaAtual++;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Verifica se a leitura do arquivo chegou ao fim dele.
	 * 
	 * @return true, se chegou ao fim do arquivo, caso contrário, false
	 */
	protected boolean isFinalDeArquivo() {
		// fim de arquivo normal com linha em branco
		if (linhaAtual == null) {
			return true;
		}
		// fim de arquivo com linha com caracteres especiais
		for (char c : linhaAtual.toCharArray()) {
			if (c != FINAL) {
				return false;
			}
		}
		leLinha();
		return linhaAtual == null;
	}

	/**
	 * Define um arquivo para extrair dados deste arquivo. Cria um leitor para o
	 * arquivo e também já realiza a leitura da primeira linha e mantém em cache
	 * na variável linhaAtual.
	 * 
	 * @param arquivo
	 *            arquivo que será usado para extrair dados
	 * @return true, se a definição do arquivo foi realizada com sucesso, caso
	 *         contrário false
	 */
	protected boolean setArquivo(File arquivo) {
		if (arquivo == null) {
			return false;
		}
		this.arquivo = arquivo;
		try {
			br = new BufferedReader(new FileReader(this.arquivo));
			leLinha();
		} catch (Exception e) {
			System.out.println("Arquivo não existe!");
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return true;
	}

	/**
	 * Pula as linhas inúteis da base de dados, linhas em branco e linhas que
	 * demarcam o fim de um arquivo.
	 * 
	 * @return true, se apenas pulou as linhas inúteis, false caso encontrou o
	 *         fim da base de dados.
	 */
	protected abstract boolean pularLinhasInuteis();

	/**
	 * Recupera o meta atributo que indica que o último meta atributo encontrado
	 * ainda está sendo definido para um determinado documento/consulta.
	 * 
	 * @return meta atributo de continuação de definição do último meta atributo
	 */
	protected abstract MetaAtributo getContAtributo();

	/**
	 * Recupera a classe enumeradora dos atributos desta instância da base de
	 * dados. Há uma classe para documentos {@link MetaAtributoDocumento} e
	 * outra para consultas {@link MetaAtributoConsulta}.
	 * 
	 * @return classe enumeradora dos atributos desta instância da base de dados
	 */
	protected abstract <MetaAtr extends MetaAtributo> Class<MetaAtr> getMetaAtributoClass();

	/**
	 * Realiza a leitura do próximo atributo do documento/consulta atual.
	 * 
	 * @return próximo atributo do documento/consulta atual, ou null em caso de
	 *         não ter mais documentos/consultas.
	 */
	protected <MetaAtr extends MetaAtributo> AtributoGenerico proximoAtributo() {
		if (!pularLinhasInuteis()) {
			return null;
		}

		final MetaAtributo CONT_ATRIBUTO = getContAtributo();
		final Class<MetaAtr> metaAtributoClass = getMetaAtributoClass();
		MetaAtributo atributo = MetaAtributo.getAtributo(linhaAtual, metaAtributoClass);
		int n = atributo.getNome().length();
		StringBuilder valor = new StringBuilder(linhaAtual.substring(n).trim());
		leLinha();
		while (CONT_ATRIBUTO.equals(MetaAtributo.getAtributo(linhaAtual, metaAtributoClass))) {
			valor.append(atributo.getCaracterSeparador()).append(linhaAtual.trim());
			leLinha();
		}
		return new AtributoGenerico(atributo, valor.toString());
	}

	/**
	 * Fecha o leitor que está sendo utilizado para ler o arquivo.
	 */
	protected void fechaLeitor() {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

}
