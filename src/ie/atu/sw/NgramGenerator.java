package ie.atu.sw;

import java.io.File;
import java.io.PrintWriter;

public class NgramGenerator {
	
	public void save(Object[][] table, String file) throws Exception{
		PrintWriter pw = new PrintWriter(new File(file));
		
		for (int row = 0; row < table.length; row++) {
			pw.write(table[row][0] + "," + table[row][1]);
		}
		
		pw.close();
		
	}
	
	public void getNGrams(String w, int n) throws Exception{
		Parser p = new Parser(n);
		String[] ngrams = new String[w.length() - n + 1];
				
		System.out.println("getting N grams...");
		
		for (int i = 0; i <= w.length()-n; i++) {
			ngrams[i] = w.substring(i, i + n);
		}
		
		for (String i : ngrams) {
			p.addNGram(i);
		}
		
		

	}
	
	

}