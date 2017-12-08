package game1;

import java.util.Random;
import java.util.Scanner;

public class Main1 {

	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
	
		int target = getRandomInt(100);
		while (true) {
			int number = getInt("Zgadnij liczbę (1-100):");
			if (number < target) {
				System.out.println("Za mało!");
			} else if (number > target){
				System.out.println("Za dużo!");
			} else {
				System.out.println("Zgadłeś");
				break;
			}
		}
		scan.close();
	}

	
	static int getRandomInt(int n) {
		
		Random rand = new Random(); 
		return rand.nextInt(n-1)+1;
	}
	
	
    public static int getInt(String title) {
    	
    	int param;
    	System.out.print(title);
    	while (!scan.hasNextInt()) {
    		System.out.println("To nie jest liczba");
    		scan.next();
    		}
    	param = scan.nextInt();
    	return param;
    }
}
