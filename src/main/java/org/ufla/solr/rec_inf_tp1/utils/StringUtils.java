package org.ufla.solr.rec_inf_tp1.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsável por alguns métodos de utilidade geral relacionados a String e
 * fluxo de dados.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */
public class StringUtils {

	/**
	 * Encontra o próximo índice que não contém o caracter informado em uma
	 * determinada string.
	 * 
	 * @param str
	 *            string a buscar um caracter diferente
	 * @param c
	 *            caracter que deseja pular
	 * @param i
	 *            índice inicial, que contém o caracter c
	 * @return índice após o índice i que não contém o caracter c na string,
	 *         caso não existir retornará o tamanho da string
	 */
	public static int pularCaracteres(String str, char c, int indice) {
		final int N = str.length();
		indice++;
		while (indice < N && str.charAt(indice) == c) {
			indice++;
		}
		return indice;
	}

	/**
	 * Split por um caracter separador, onde o array de strings não contém
	 * strings vazias.
	 * 
	 * @param str
	 *            string a ser quebrada (split)
	 * @param c
	 *            caracter separador para realizar o split
	 * @return array de strings resultantes do split (tokens)
	 */
	public static String[] splitSemStringVazia(String str, char c) {
		List<String> tokens = new ArrayList<>();
		int indice = pularCaracteres(str, c, -1);
		final int N = str.length();
		while (indice < N) {
			int fim = str.indexOf(c, indice);
			if (fim == -1) {
				fim = N;
			}
			tokens.add(str.substring(indice, fim));
			indice = pularCaracteres(str, c, fim);
		}
		return tokens.toArray(new String[0]);
	}

	/**
	 * Recupera uma mensagem inteira de um fluxo de entrada de dados.
	 * 
	 * @param input
	 *            fluxo de entrada de dados
	 * @return mensagem contida no fluxo de entrada de dados
	 */
	public static String getMensagem(InputStream input) {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
		StringBuilder mensagem = new StringBuilder();
		String linha;
		try {
			while ((linha = bufferedReader.readLine()) != null) {
				mensagem.append(linha).append('\n');
			}
			bufferedReader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
		return mensagem.toString();
	}

}
