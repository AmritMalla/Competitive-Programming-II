import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int[] arr = new int[scn.nextInt()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
        solve(arr, 0, new ArrayList<>(), new ArrayList<>(), 0, 0);
        System.out.println(ans);
    }

    static int minDiff = Integer.MAX_VALUE;

    static String ans = "";

    public static void solve(int[] arr, int index, ArrayList<Integer> set1, ArrayList<Integer> set2, int sumSet1,
                             int sumSet2) {

//        base case
        if (index == arr.length) {
            if (minDiff > Math.abs(sumSet1 - sumSet2)) {
                minDiff = Math.abs(sumSet1 - sumSet2);
                ans = set1 + " " + set2;
            }
            return;
        }

        if (set1.size() < (arr.length + 1) / 2) {
            set1.add(arr[index]);
            solve(arr, index + 1, set1, set2, sumSet1 + arr[index], sumSet2);
            set1.remove(set1.size() - 1);
        }

        if (set2.size() < (arr.length + 1) / 2) {
            set2.add(arr[index]);
            solve(arr, index + 1, set1, set2, sumSet1, sumSet2 + arr[index]);
            set2.remove(set2.size() - 1);
        }
    }

}