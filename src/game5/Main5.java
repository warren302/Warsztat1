package game5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Scanner;


public class Main5 {
	static String[] source = {"http://www.onet.pl/", "http://www.interia.pl/", "http://www.wp.pl/", "http://www.gazeta.pl/"};
	
	public static void main(String[] args) {
		frontalPanel();
		buildSetOfWords();
		write10WordsToFile(countWordsAndFind10(readFileToList()));
		closingPanel();
	}
	

	private static void frontalPanel() {
		System.out.println("Program zczytuje nagłówki artykułów z popularnych serwisów internetowych");
		System.out.println("i wybiera 10 najpopularniejszych slów");
	}

	private static void closingPanel() {
		System.out.println("Program zakończył zbieranie danych");
	}
	
	private static String clearLine(String line) {
		String codes = "[,-.:;\'\"\\!\\?]";
		return line.replaceAll(codes, " ");
	}
	
	private static ArrayList<String> readFileToList(){
		ArrayList<String> wordsList = new ArrayList<>(); 
		try {
			Path path = Paths.get("popular_words.txt");
			File file = new File(path.toString());
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()){
				String word = scanner.next();
				if (wordsList.isEmpty()) {
					wordsList.add(word);
				} else {
					if ((wordsList.indexOf(word)) == -1) {
						wordsList.add(word);
					}
				}
			}
			scanner.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wordsList;
	}
	
	
	private static String[] findMostPopularWords(String[] wordsArray, int[] wordsArrayCounter) {
		String[] mostPopular = new String[10];
		int[] highestInd = new int[10];
		for (int i = 0; i < highestInd.length; i++) {
			int pos = 0;
			int max = 0;
			for (int j = 0; j < wordsArrayCounter.length; j++) {
				if (wordsArrayCounter[j] > max) {
					max = wordsArrayCounter[j];
					pos = j;
				}
				highestInd[i] = pos;
				wordsArrayCounter[pos] = 0;
			}
		}
		for (int i = 0; i < highestInd.length; i++) {
			mostPopular[i] = wordsArray[highestInd[i]];
		}
		return mostPopular;
	}
	
	
	private static int findWordInArray(String[] strArr, String word) {
		for (int i = 0; i < strArr.length; i++) {
			if (strArr[i].equals(word)) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	private static String[] countWordsAndFind10 (ArrayList<String> wordsList) {
		String[] wordsArray = wordsList.toArray(new String[0]);
		int[] wordsArrayCounter = new int[wordsArray.length];
		try {
			Path path = Paths.get("popular_words.txt");
			File file = new File(path.toString());
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()){
				String word = scanner.next();
				int position = findWordInArray(wordsArray, word); 
				if ( position >= 0 ) {
					wordsArrayCounter[position]++;
				} 
			}
			scanner.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return findMostPopularWords(wordsArray, wordsArrayCounter);
	}
	
	
	private static void write10WordsToFile(String[] mostPopularWords) {

		try {
			FileWriter fileW = new FileWriter("most_popular_words.txt", false);
			for (String word : mostPopularWords) {
				fileW.append(word).append("\n");
			}
			fileW.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	

	private static void buildSetOfWords() {
		for (int i = 0; i < source.length; i++) {
			if (i == 0) {
				writeToFileFromSource(source[i], false);
			} else {
				writeToFileFromSource(source[i], true);
			}
		}
	}
	
	
	private static void writeToFileFromSource(String url, boolean add) {
		Connection connect = Jsoup.connect(url);
		try {
			FileWriter fileW = new FileWriter("popular_words.txt", add);
			Document document = connect.get();
		    Elements links = document.select("span.title");
		    for (Element elem : links) {
		        String[] words = clearLine(elem.text()).split(" ");
		        for (String word : words) {
		        	if (word.length() > 3) {
		        		fileW.append(word.trim().toLowerCase()).append("\n");
		        	}
		        }
		    }
		    fileW.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	
}
