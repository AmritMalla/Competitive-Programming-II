import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String s1 = scn.nextLine();
        String s2 = scn.nextLine();
        String s3 = scn.nextLine();

        HashMap<Character, Integer> charIntMap = new HashMap<>();
        String unique = "";
        for (int i = 0; i < s1.length(); i++) {
            if (!charIntMap.containsKey(s1.charAt(i))) {
                charIntMap.put(s1.charAt(i), -1);
                unique += s1.charAt(i);
            }
        }

        for (int i = 0; i < s2.length(); i++) {
            if (!charIntMap.containsKey(s2.charAt(i))) {
                charIntMap.put(s2.charAt(i), -1);
                unique += s2.charAt(i);
            }
        }

        for (int i = 0; i < s3.length(); i++) {
            if (!charIntMap.containsKey(s3.charAt(i))) {
                charIntMap.put(s3.charAt(i), -1);
                unique += s3.charAt(i);
            }
        }

        boolean[] usedNumbers = new boolean[10];
        solution(unique, 0, charIntMap, usedNumbers, s1, s2, s3);
    }


    public static void solution(String unique, int idx,
                                HashMap<Character, Integer> charIntMap, boolean[] usedNumbers,
                                String s1, String s2, String s3) {
        if (idx == unique.length()) {
            int num1 = getSum(charIntMap, s1);
            int num2 = getSum(charIntMap, s2);
            int num3 = getSum(charIntMap, s3);

            if (num1 + num2 == num3) {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (charIntMap.containsKey(c)) {
                        System.out.print(c + "-" + charIntMap.get(c) + " ");
                    }
                }
                System.out.println();
            }
            return;
        }

        char ch = unique.charAt(idx);
        for (int num = 0; num <= 9; num++) {
            if (!usedNumbers[num]) {
                charIntMap.put(ch, num);
                usedNumbers[num] = true;
                solution(unique, idx + 1, charIntMap, usedNumbers, s1, s2, s3);
                usedNumbers[num] = false;
                charIntMap.put(ch, -1);
            }
        }
    }

    private static int getSum(HashMap<Character, Integer> charIntMap, String s1) {
        String s = "";
        for (int i = 0; i < s1.length(); i++) {
            s += charIntMap.get(s1.charAt(i));
        }
        return Integer.parseInt(s);
    }

}
