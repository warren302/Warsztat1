package game3;

import java.util.Scanner;

public class Main3 {
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		frontalPanel();
		searching(0, 1000);
		finalPanel();
	}

	private static boolean frontalPanel(){
		System.out.println("Pomyśl liczbę od 0 do 1000 a ja ją odgadnę w max 10 próbach.");
		return true;
	}
	
	private static boolean finalPanel(){
		scan.close();
		return true;
	}
	
	private static boolean searching(int min, int max){
		int guess = guessing(min, max);
		int guessResult = isGuessed(guess);
		if (guessResult == 0) {
			System.out.println("Wygrałem!");
		} else if (guessResult == 1){
			searching(min, guess);
		} else {
			searching(guess, max);
		}
		return true;
	}
	
	
	private static int guessing(int min, int max){
		int guess = guess(min, max);
		System.out.println("Zgaduję: " + guess);
		return guess;
	}
	
	
	private static String getReply(){
		System.out.print("Podaj odpowiedź (\"zgadłeś\", \"za mało\", \"za dużo\"): ");
		return scan.nextLine().toLowerCase().trim();
	}

	
	private static int isGuessed(int guess){
		String reply = getReply();
		if (reply.equals("zgadłeś") || reply.equals("zgadles")){
			return  0;
		} else if (reply.equals("za mało") || reply.equals("za malo")){
			return -1;
		} else if (reply.equals("za dużo") || reply.equals("za duzo")){
			return 1;
		} else {
			System.out.println("nie oszukuj!");
			return isGuessed(guess);
		}
	}
	
	
	private static int guess(int min, int max) {
		return (int) ((max + min)/2);
	}

}

