package org.ufla.solr.rec_inf_tp1.metrica.copy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Responsável por implementar os cálculos necessários para conseguir os valores
 * da métrica baseada na precisão e revocação para os resultados de uma
 * determinada consulta.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class MetricaPrecisaoRevocacao {

	/**
	 * Documentos relevantes da consulta
	 */
	private Set<Integer> documentosRelevantes;
	/**
	 * Documentos recuperados pela consulta
	 */
	private List<Integer> documentosRecuperados;

	/**
	 * Construtor que recebe os documentos relevantes de uma consulta e os
	 * documentos recuperados pela consulta.
	 * 
	 * @param documentosRelevantes
	 *            documentos relevantes da consulta
	 * @param documentosRecuperados
	 *            documentos recuperados pela consulta
	 */
	public MetricaPrecisaoRevocacao(Set<Integer> documentosRelevantes, List<Integer> documentosRecuperados) {
		this.documentosRelevantes = documentosRelevantes;
		this.documentosRecuperados = documentosRecuperados;
	}

	/**
	 * Calcula os pontos de precisão e revocação para os documentos recuperados
	 * e rankeados.
	 * 
	 * @return lista de pontos de precisão e revocação para os documentos
	 *         recuperados e rankeadoss
	 */
	public List<PontoPrecisaoRevocacao> calcularPontos() {
		List<PontoPrecisaoRevocacao> pontos = new ArrayList<>();
		final int MAX_DOCUMENTOS_REC = documentosRecuperados.size();
		final int MAX_DOCUMENTOS_REL = documentosRelevantes.size();
		final double REVOCACAO_POR_ACERTO = 100.0d / MAX_DOCUMENTOS_REL;
		double revocacaoAtual = 0;
		int contAcertos = 0;
		for (int i = 0; i < MAX_DOCUMENTOS_REC; i++) {
			if (documentosRelevantes.contains(documentosRecuperados.get(i))) {
				revocacaoAtual += REVOCACAO_POR_ACERTO;
				contAcertos++;
				pontos.add(new PontoPrecisaoRevocacao(contAcertos / (double) (i + 1) * 100.0d, revocacaoAtual));
			}
		}
		if (contAcertos == MAX_DOCUMENTOS_REL) {
			pontos.get(MAX_DOCUMENTOS_REL - 1).revocacao = 100.0d;
		}
		return pontos;
	}

	/**
	 * Calcula a tabela de precisão por revocação.
	 * 
	 * @return tabela de precisão por revocação
	 */
	public TabelaPrecisaoRevocacao calcularTabela() {
		List<PontoPrecisaoRevocacao> pontos = calcularPontos();
		TabelaPrecisaoRevocacao tabelaPrecisaoRevocacao = new TabelaPrecisaoRevocacao();
		final int MAX_PONTOS = pontos.size();
		int indiceP = 0;
		for (int i = 0; i < TabelaPrecisaoRevocacao.QTD_NIVEIS_REVOCACAO; i++) {
			while (indiceP < MAX_PONTOS
					&& TabelaPrecisaoRevocacao.NIVEIS_REVOCACAO[i] > pontos.get(indiceP).revocacao) {
				indiceP++;
			}
			if (indiceP == MAX_PONTOS) {
				while (i < TabelaPrecisaoRevocacao.QTD_NIVEIS_REVOCACAO) {
					tabelaPrecisaoRevocacao.setPrecisao(0, i);
					i++;
				}
			} else {
				tabelaPrecisaoRevocacao.setPrecisao(pontos.get(indiceP).precisao, i);
			}
		}
		return tabelaPrecisaoRevocacao;
	}

	/**
	 * Calcula a tabela de precisão e revocação média para uma lista de tabelas
	 * de precisão e revocação.
	 * 
	 * @param tabelasPrecisaoRevocacao
	 *            lista de tabelas de precisão e revocação.
	 * @return tabela de precisão e revocação média
	 */
	public static TabelaPrecisaoRevocacao calcularTabelaMedia(List<TabelaPrecisaoRevocacao> tabelasPrecisaoRevocacao) {
		TabelaPrecisaoRevocacao tabelaPrecisaoRevocacaoMedia = new TabelaPrecisaoRevocacao();
		final int N = tabelasPrecisaoRevocacao.size();
		double precisao[] = new double[TabelaPrecisaoRevocacao.QTD_NIVEIS_REVOCACAO];
		for (TabelaPrecisaoRevocacao tabela : tabelasPrecisaoRevocacao) {
			double precisaoAtual[] = tabela.getPrecisao();
			for (int i = 0; i < TabelaPrecisaoRevocacao.QTD_NIVEIS_REVOCACAO; i++) {
				precisao[i] += precisaoAtual[i];
			}
		}
		for (int i = 0; i < TabelaPrecisaoRevocacao.QTD_NIVEIS_REVOCACAO; i++) {
			precisao[i] /= N;
		}
		tabelaPrecisaoRevocacaoMedia.setPrecisao(precisao);
		return tabelaPrecisaoRevocacaoMedia;
	}

	public Set<Integer> getDocumentosRelevantes() {
		return documentosRelevantes;
	}

	public void setDocumentosRelevantes(Set<Integer> documentosRelevantes) {
		this.documentosRelevantes = documentosRelevantes;
	}

	public List<Integer> getDocumentosRecuperados() {
		return documentosRecuperados;
	}

	public void setDocumentosRecuperados(List<Integer> documentosRecuperados) {
		this.documentosRecuperados = documentosRecuperados;
	}

}
