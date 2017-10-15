package org.ufla.solr.rec_inf_tp1.preprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class StopWords {

	private BufferedReader in;

	public ArrayList<String> listStpWords() throws IOException {
		ArrayList<String> StpWords = new ArrayList<String>();
		FileReader file = new FileReader("stoplist.txt");
		in = new BufferedReader(file);
		String line;

		while ((line = in.readLine()) != null) {
			StringTokenizer token = new StringTokenizer(line, " ");
			StpWords.add(token.nextToken());
		}
		in.close();
		return StpWords;
	}

	// Arrumar problema de remover stopwords.
	public String removeStpWords(String stpWords, ArrayList<String> listStpWords) {

		String noStopWords = "", aux;
		List<String> listWithStpWords = Arrays.asList(stpWords.split(" "));
		ArrayList<String> aListWithStpWords = new ArrayList<String>(listWithStpWords);
		ArrayList<String> listWithoutStpWords = new ArrayList<String>(aListWithStpWords);
		Iterator<String> iter = aListWithStpWords.iterator();

		while (iter.hasNext()) {
			aux = iter.next();
			if (listStpWords.contains(aux))
				listWithoutStpWords.remove(aux);

		}

		for (String str : listWithoutStpWords)
			if (!listWithoutStpWords.isEmpty())
				noStopWords += str + " ";
			else
				noStopWords += str;

		return noStopWords;
	}
}