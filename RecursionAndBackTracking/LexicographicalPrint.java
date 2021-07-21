import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		
		
        // first call from root
		for(int i = 1;i<=9;i++){
		    printLexNum(n, i);
		}
	}
	
	public static void printLexNum(int n, int digit){
	    if(digit > n) return;
	    System.out.println(digit);
	    for(int i = 0;i<10;i++){
	        printLexNum(n, digit * 10 + i);
	    }
	}
	
}