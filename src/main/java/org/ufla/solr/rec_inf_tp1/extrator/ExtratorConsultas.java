package org.ufla.solr.rec_inf_tp1.extrator;

import org.ufla.solr.rec_inf_tp1.config.ConfigBaseDeDados;
import org.ufla.solr.rec_inf_tp1.model.AtributoGenerico;
import org.ufla.solr.rec_inf_tp1.model.Consulta;
import org.ufla.solr.rec_inf_tp1.model.MetaAtributo;
import org.ufla.solr.rec_inf_tp1.model.MetaAtributoConsulta;

/**
 * Responsável por extrair as consultas da base de dados estática que será
 * utilizada.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class ExtratorConsultas extends ExtratorGenerico {

	/**
	 * Inicializa as configurações do extrator de consultas da base de dados.
	 */
	public ExtratorConsultas() {
		System.out.println("Iniciando extração das consultas!");
		setArquivo(ConfigBaseDeDados.getInstance().getArquivoConsulta());
	}

	/**
	 * Realiza a leitura da próxima consulta da base de dados.
	 * 
	 * @return próxima consulta da base de dados, ou null caso a base de dados
	 *         tenha acabado.
	 */
	public Consulta proximaConsulta() {
		if (!pularLinhasInuteis()) {
			fechaLeitor();
			return null;
		}
		Consulta consulta = new Consulta();
		while (!linhaAtual.trim().isEmpty()) {
			AtributoGenerico atributo = proximoAtributo();
			if (atributo == null) {
				fechaLeitor();
				return null;
			}
			consulta.setAtributo(atributo);
		}

		return consulta;
	}

	@Override
	public boolean pularLinhasInuteis() {
		while (linhaAtual != null && linhaAtual.trim().isEmpty()) {
			leLinha();
		}
		return !isFinalDeArquivo();
	}

	@Override
	public MetaAtributo getContAtributo() {
		return MetaAtributoConsulta.CONT_DEF_ATR;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<MetaAtributoConsulta> getMetaAtributoClass() {
		return MetaAtributoConsulta.class;
	}

}
