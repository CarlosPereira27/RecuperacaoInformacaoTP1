package org.ufla.solr.rec_inf_tp1.extrator;

import org.ufla.solr.rec_inf_tp1.config.ConfigBaseDeDados;
import org.ufla.solr.rec_inf_tp1.model.AtributoGenerico;
import org.ufla.solr.rec_inf_tp1.model.Documento;
import org.ufla.solr.rec_inf_tp1.model.DocumentoOriginal;
import org.ufla.solr.rec_inf_tp1.model.MetaAtributo;
import org.ufla.solr.rec_inf_tp1.model.MetaAtributoDocumento;

/**
 * Responsável por extrair os documentos da base de dados estática que será
 * utilizada.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class ExtratorDocumentos extends ExtratorGenerico {

	/**
	 * Indice do arquivo atual que está sendo processado.
	 */
	private int indiceArquivoAtual;

	/**
	 * Inicializa as configurações do extrator de documentos da base de dados.
	 */
	public ExtratorDocumentos() {
		System.out.println("Iniciando extração dos documentos!");
		indiceArquivoAtual = 0;
		setArquivo(ConfigBaseDeDados.getInstance().getArquivoDocumento(indiceArquivoAtual));
	}

	/**
	 * Inicia a leitura do próximo arquivo da base de dados.
	 * 
	 * @return true se ainda há um próximo arquivo na base de dados, false caso
	 *         contrário.
	 */
	private boolean proximoArquivo() {
		indiceArquivoAtual++;
		fechaLeitor();
		return setArquivo(ConfigBaseDeDados.getInstance().getArquivoDocumento(indiceArquivoAtual));
	}

	/**
	 * Realiza a leitura do próximo documento da base de dados e o retorna da
	 * forma utilizada no trabalho prático.
	 * 
	 * @return próximo documento da base de dados, ou null caso a base de dados
	 *         tenha acabado.
	 */
	public Documento proximoDocumento() {
		DocumentoOriginal documento = proximoDocumentoOriginal();
		return (documento == null) ? null : documento.toDocumento();
	}

	/**
	 * Realiza a leitura do próximo documento da base de dados e o retorna da
	 * forma original definida na base de dados.
	 * 
	 * @return próximo documento da base de dados, ou null caso a base de dados
	 *         tenha acabado.
	 */
	public DocumentoOriginal proximoDocumentoOriginal() {
		if (!pularLinhasInuteis()) {
			fechaLeitor();
			return null;
		}
		DocumentoOriginal documento = new DocumentoOriginal();
		while (!linhaAtual.trim().isEmpty()) {
			AtributoGenerico atributo = proximoAtributo();
			if (atributo == null) {
				fechaLeitor();
				return null;
			}
			documento.setAtributo(atributo);
		}

		return documento;
	}

	@Override
	public boolean pularLinhasInuteis() {
		while (linhaAtual != null && linhaAtual.trim().isEmpty()) {
			leLinha();
		}
		if (isFinalDeArquivo()) {
			if (!proximoArquivo()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public MetaAtributo getContAtributo() {
		return MetaAtributoDocumento.CONT_DEF_ATR;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<MetaAtributoDocumento> getMetaAtributoClass() {
		return MetaAtributoDocumento.class;
	}

}
