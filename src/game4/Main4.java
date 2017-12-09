package game4;

import java.util.Random;
import java.util.Scanner;

public class Main4 {
	static Scanner scan = new Scanner(System.in);
	static Random rand = new Random();
	public static int[] diceCode = new int[3];
			
	public static void main(String[] args) {
		frontalPanel();
		throwingDice();
		finalPanel();	
	}

	private static void throwingDice() {
		inputDiceCode();
		System.out.println("Rzuciłes :" +  calculatingThrow()); 
	}

	private static int calculatingThrow() {
		int sum = 0;
		for (int i = 0; i < diceCode[0]; i++) {
			sum += rand.nextInt(diceCode[1])+1;	
		}
		sum += diceCode[2];
		return sum;
	}

	private static void inputDiceCode() {
		if (!isDiceCode(inputCommand())) {
			System.out.println("Niewłaściwy kod");
			inputDiceCode();
		} 
	}

	
	private static boolean isDiceCode(String command) {
		String[] dices = {"D100" , "D3", "D4", "D6", "D8" , "D10" , "D12", "D20"};
		int i;
		int position = -1;
		for (i = 0; i < dices.length; i++) { 
			position = command.indexOf(dices[i]);
			if ( position >= 0) { 
				break;
			}
		}
		if (i == dices.length) {
			return false;
		} else {
			String[] strArr = new String[3];
			if (position == 0) {
				strArr[0] = "1";
				strArr[1] = dices[i].substring(1);
				strArr[2] = command.substring(dices[i].length());
			} else {
				strArr[0] = command.substring(0, position);
				strArr[1] = dices[i].substring(1);
				strArr[2] = command.substring(dices[i].length()+1);
			}
			if (strArr[2].length() == 1) {
				return false;
			}
			if (((strArr[2].length() > 1) && (strArr[2].indexOf('+') != 0) && strArr[2].indexOf('-') != 0)) {
				return false;
			}
			try {
				diceCode[0] = Integer.parseInt(strArr[0]);
				diceCode[1] = Integer.parseInt(strArr[1]);
				diceCode[2] = Integer.parseInt(strArr[2]);			
			} catch (Exception e) {
				
				return false;
			}
			return true;	
		}
	}

	
	private static String inputCommand() {
		System.out.println("Podaj kod rzutu kostką np. \"2D6+3\": ");
		return scan.nextLine();
	}

	
	private static void finalPanel() {
		scan.close();
	}

	
	private static void frontalPanel() {
		System.out.println("To jest symulator rzutu kostkami do gry.");;
	}
}
