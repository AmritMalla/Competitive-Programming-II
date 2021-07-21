import java.io.*;
import java.util.*;

public class Main {
	static int max = 0;
	
	public static int dfs(int[][]arr, int r, int c){
	    if(r<0 || c < 0 || r >= arr.length || c >= arr[r].length || arr[r][c] == 0 ) return 0;
	    int cg = arr[r][c];
	    arr[r][c] = 0;
	    int max = dfs(arr, r - 1, c);
	    max = Math.max(dfs(arr, r, c-1), max);
	    max = Math.max(dfs(arr, r, c+1), max);
	    max = Math.max(dfs(arr, r+1, c), max);
	    
	    arr[r][c] = cg;
	    return max + cg; 
	}
	
	
	
	public static void getMaxGold(int[][] arr){
	    
	    for(int i = 0;i<arr.length;i++){
	        for(int j = 0;j<arr[i].length;j++){
	            if(arr[i][j] != 0 ){
	                int gold = dfs(arr, i, j);
	                max = Math.max(gold, max);
	            }
	        }
	    }	
		
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int m = scn.nextInt();
		int[][] arr = new int[m][n];
		for(int i = 0; i < arr.length; i++){
			for(int j = 0 ; j  < arr[0].length; j++){
				arr[i][j] = scn.nextInt();
			}
		}
		getMaxGold(arr);
		System.out.println(max);
	}
		
}