import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    boolean[] col = new boolean[n];
    boolean[] fd = new boolean[n * 2 - 1];
    boolean[] rd = new boolean[n * 2 - 1];
    nQueensBAB(col, fd, rd, "", 0);
    
  }
  
  public static void nQueensBAB(boolean[] col, boolean[]fd, boolean[] rd , String asf, int r){
    if(r == col.length){
        System.out.println(asf + ".");
        return;
    }  
    
    for(int i = 0;i<col.length;i++){
        int fdi = r + i;
        int rdi = r - i + col.length - 1;
        if(!col[i] && !fd[fdi] && !rd[rdi] ){
            col[i] = fd[fdi] = rd[rdi] = true;
            nQueensBAB(col, fd, rd, asf + r + "-" + i + ", " , r + 1);
            col[i] = fd[fdi] = rd[rdi] = false;
        }
    }
  }

}