package org.ufla.solr.rec_inf_tp1.teste;

import java.util.Arrays;

import org.ufla.solr.rec_inf_tp1.utils.StringUtils;


public class TesteStringUtils {

	public static void main(String[] args) {
		String str = "Hello  oi    bao 		ok   bom demais   ";
		String[] esperado = { "Hello", "oi", "bao", "		ok", "bom", "demais" };
		String[] resultado = StringUtils.splitSemStringVazia(str, ' ');
		if (Arrays.deepEquals(esperado, resultado)) {
			System.out.println("OK");
		} else {
			System.out.println("ERRO");
			System.out.println("ESPERADO:");
			System.out.println(Arrays.deepToString(esperado));
			System.out.println("RESULTADO:");
			System.out.println(Arrays.deepToString(resultado));
		}

	}

}
