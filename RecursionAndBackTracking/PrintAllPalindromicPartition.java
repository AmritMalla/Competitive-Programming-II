import java.util.Scanner;

public class Main {

    public static void solution(String str, String asf) {
        if (str.length() == 0) {
            System.out.println(asf);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            if (isPalindrome(str, 0, i)) {
                solution(str.substring(i + 1), asf + "(" + str.substring(0, i + 1) + ") ");
            }
        }

    }

    private static boolean isPalindrome(String str, int beg, int end) {
        while (beg < end) {
            if (str.charAt(beg) != str.charAt(end)) return false;
            beg++;
            end--;
        }
        return true;

    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();
        solution(str, "");
    }

}