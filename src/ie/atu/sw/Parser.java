package ie.atu.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Parser {
	
	private Object[][] table;
	private NgramGenerator ng = new NgramGenerator();
	private int ngSize;
	
	public Parser(int n) {
		this.ngSize = n;
		table = new Object[26^n][2]; // No resize but lots of empty space
	}
	
	public void addNGram (String ngram) {
		int index = ngram.hashCode() % table.length;
		
		long counter = 1;
		if (table[index][0] == null) {
			counter += (Long)table[index][1];
		}
		table[index][0] = ngram;
		table[index][1] = counter;

	}
	
	public void parseFile(String fileName) throws Exception{
				
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))){
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				String[] words = line.split("\\s+");
				
				for (String word : words) {
					word = word.trim().replaceAll("[^a-zA-Z]", "").toLowerCase();
					if (word.length() >= ngSize) {
					ng.getNGrams(word, ngSize);
					}else continue;
					//ng.getNGrams(buildString(words), ngSize);
				}
			}
			br.close();
		}
	}
	
	public void parseDirectory(String directory) throws Exception{
		//directory = "./TextFiles"
		File f = new File(directory);
		String[] files = f.list();
		for (String file : files) {
			
		}
	}
	
	//public String buildString(String[] arr) {
		//StringBuilder sb = new StringBuilder();
		
		//for (int i = 0; i < arr.length; i++) {
		//	sb.append(arr[i]);
		//}
		
		//return sb.toString();
//	}
	
	public Object[][] getTable() {
		return table;
	}
	
	

}