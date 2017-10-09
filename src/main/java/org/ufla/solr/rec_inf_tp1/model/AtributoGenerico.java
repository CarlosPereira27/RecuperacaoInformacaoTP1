package org.ufla.solr.rec_inf_tp1.model;

import java.io.Serializable;

/**
 * Define um atributo genérico, possuindo campos para definir qual é este
 * atributo (metadado/nome) e qual o seu valor.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class AtributoGenerico implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Representa o metadado (nome) do atributo da instância.
	 */
	private MetaAtributo atributo;
	/**
	 * Representa o valor do atributo da instância.
	 */
	private String valor;

	public AtributoGenerico() {

	}

	public AtributoGenerico(MetaAtributo atributo, String valor) {
		this.atributo = atributo;
		this.valor = valor;
	}

	public MetaAtributo getAtributo() {
		return atributo;
	}

	public void setNome(MetaAtributo atributo) {
		this.atributo = atributo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atributo == null) ? 0 : atributo.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtributoGenerico other = (AtributoGenerico) obj;
		if (atributo == null) {
			if (other.atributo != null)
				return false;
		} else if (!atributo.equals(other.atributo))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Atributo [atributo=" + atributo + ", valor=" + valor + "]";
	}

}
