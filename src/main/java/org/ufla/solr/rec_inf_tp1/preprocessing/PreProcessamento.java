package org.ufla.solr.rec_inf_tp1.preprocessing;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Classe principal da aplicação.
 * 
 * @author carlos
 * @author douglas
 * @author italo
 *
 */

public class PreProcessamento {
		
	public static String processText(String text) {
		try {
			StopWords stpWords = new StopWords();		
			ArrayList<String> listStpWords = stpWords.listStpWords();
			ArrayList<String> listText = new ArrayList<String>();
			StringReader sText;
			Stemmer stem = new Stemmer();
			
			text = text.toLowerCase();
			text = stpWords.removeStpWords(text, listStpWords);
			sText = new StringReader(text);
			listText = stem.runStem(sText);
			text = listText.toString().replace("[","").replace("]","").replace(",","");
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return text;
	}	
}
