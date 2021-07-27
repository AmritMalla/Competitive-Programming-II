import java.util.Scanner;

public class Main {

    static String max;

    public static void findMaximum(StringBuilder sb, int k) {
        if(sb.toString().compareTo(max) > 0){
            max = sb.toString();
        }
        if(k == 0){
            return;
        }

        for (int i = 0; i < sb.length(); i++) {
            for (int j = i; j < sb.length(); j++) {
                if(sb.charAt(i) < sb.charAt(j)){
                    swap(sb, i, j);
                    findMaximum(sb, k - 1);
                    swap(sb, i, j);
                }
            }
        }

    }

    public static void swap(StringBuilder sb, int i, int j){
        char temp = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, temp);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        int k = scn.nextInt();
        max = str;
        StringBuilder sb = new StringBuilder();
        findMaximum(new StringBuilder(str), k);
        System.out.println(max);
    }

}