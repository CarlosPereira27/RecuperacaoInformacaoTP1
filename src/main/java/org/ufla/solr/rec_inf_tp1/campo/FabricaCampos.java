package org.ufla.solr.rec_inf_tp1.campo;

import org.ufla.solr.rec_inf_tp1.model.MetaAtributoDocumento;

/**
 * Uma fábrica de campos para fabricar os campos utilizados neste projeto
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class FabricaCampos {

	// Tipos de campos utilizado neste projeto (pint/text_en).
	private static final String FIELD_TYPE_PINT = "pint";
	private static final String FIELD_TYPE_TEXT_EN = "text_en";

	public FabricaCampos() {

	}

	/**
	 * Cria o campo CONT.
	 * 
	 * @return atributos do campo CONT.
	 */
	public AtributosCampo criarCampoCONT() {
		return new AtributosCampo(MetaAtributoDocumento.CONT.getNome(), FIELD_TYPE_TEXT_EN).stored().indexed()
				.required().termVectors().termPositions().termOffsets().termPayloads();
	}

	/**
	 * Cria o campo RN.
	 * 
	 * @return atributos do campo RN.
	 */
	public AtributosCampo criarCampoRN() {
		return new AtributosCampo(MetaAtributoDocumento.RN.getNome(), FIELD_TYPE_PINT).stored().indexed().docValues()
				.required().omitNorms().omitTermFreqAndPositions().omitPositions();
	}

}
