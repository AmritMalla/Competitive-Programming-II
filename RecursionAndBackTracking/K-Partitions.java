import java.io.*;
import java.util.*;

public class Main {

    static int counter = 1;
    public static void solution(int i, int n, int k, int ssf, ArrayList<ArrayList<Integer>> ans) {
        //base case
        if(i > n){
            if(k == ssf){
                System.out.print(counter + ". ");
                counter++;
                for(List<Integer> a: ans){
                    System.out.print(a + " ");
                }
                System.out.println();
            }
            return;
        }

        for (int j = 0; j < ans.size(); j++) {
            if (j < ssf) {
                ans.get(j).add(i);
                solution(i + 1, n, k, ssf, ans);
                ans.get(j).remove(ans.get(j).size() - 1);
            } else {
                ans.get(j).add(i);
                solution(i + 1, n, k, ssf + 1, ans);
                ans.get(j).remove(ans.get(j).size() - 1);
                break;
            }
        }

    }


    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int k = scn.nextInt();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(new ArrayList<>());
        }
        solution(1, n, k, 0, ans);

    }

}