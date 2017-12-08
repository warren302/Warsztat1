package game2;

import static game1.Main1.*;


import java.util.Arrays;

import java.util.Collections;
import java.util.Scanner;

public class Main2 {
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		int[] sixNumbers = getSix();
		Arrays.sort(sixNumbers);
		System.out.println(Arrays.toString(sixNumbers));
		int[] lottoSix = drawSix();
		System.out.println(Arrays.toString(lottoSix));
		System.out.println(yourDrawResult(lottoSix, sixNumbers));
		scan.close();
	}
	
	
	static String yourDrawResult(int[] lotto, int[] yourSix){
		String[] str = {"","","","trójkę", "czwórkę", "piątkę", "szóstkę! Gratulacje :-)"};
		Arrays.sort(lotto);
		Arrays.sort(yourSix);
		int counter = 0;
		for (int i = 0; i < lotto.length; i++ ){
			for (int j = 0; j < yourSix.length; j++){
				if (yourSix[j] > lotto[i]){
					break;
				} else if (yourSix[j] == lotto[i]){
					counter++;
					break;	
				} 		
			}
		}
		if (counter >= 3)
			return  "Trafiłeś " + str[counter];
		else
			return "";			
	}
	
	
	static boolean inRange(int position, int beginRange, int endRange){
		if ((position >= beginRange) && (position <= endRange)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	static boolean isDuplicated(int[] tab, int element){
		for (int i = 0; i < tab.length; i++) {
			if  (tab[i] == element) {
				return true;
			}
		}
		return false;
	}
	
	
	static int[] getSix(){
		int[] intArr = new int[6];
		String[] strArr = {"pierwszą","drugą","trzecią","czwartą","piątą","szóstą"};
		for (int i = 0; i < intArr.length; i++){
			while (true) {
				int intTemp = getInt("Podaj "+strArr[i]+" liczbę (1-49):");
				boolean exists = false;
				boolean outOfRange = false;
				int j = 0;
				while ((j <= i) && !exists && !outOfRange) {
					if (i > 0) {
						if (intTemp == intArr[j]) {
							exists = true;
						} else { 
							outOfRange = (!inRange(intTemp, 1, 49));
						}
					} else { 
						outOfRange = (!inRange(intTemp, 1, 49));
					}
					j++;
				}
				if (exists){
					System.out.println("Tę liczbę już wpisałeś: ");
				} else if (outOfRange){
					System.out.println("Liczba poza zakresem: ");
				} else {
					intArr[i] = intTemp;
					break;
				}
			}
		}
		return intArr;
	}


	static int[] drawSix(){
		Integer[] arr = new Integer[49];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i+1;
		}
		Collections.shuffle(Arrays.asList(arr));
		int[] tempArr = new int[6];
		for (int i = 0; i < tempArr.length; i++){
			tempArr[i] = arr[i];
		}
		return tempArr;
	}
}
