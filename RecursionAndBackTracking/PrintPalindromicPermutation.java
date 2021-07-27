import java.io.*;
import java.util.*;

public class Main {

    //currentSpot -> currentSpot
    // totalSpot -> totalSpot
    // frequencyMap -> frequencyMap
    // oddCharacter -> oddCharacter
    // answerSoFar -> answerSoFar
    public static void generatepw(int currentSpot, int totalSpot, HashMap<Character, Integer> frequencyMap, 
                                  Character oddCharacter, String answerSoFar) {

        if (currentSpot > totalSpot) {
            StringBuilder sb = new StringBuilder(answerSoFar);
            if (oddCharacter != null) {
                System.out.println(answerSoFar + oddCharacter + sb.reverse());
            } else {
                System.out.println(answerSoFar + sb.reverse());
            }
            return;
        }

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            int val = entry.getValue();
            if (val > 0) {
                entry.setValue(val - 1);
                generatepw(currentSpot + 1, totalSpot, frequencyMap, oddCharacter, answerSoFar + entry.getKey());
                entry.setValue(val);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }

        Character oddCharacter = null;
        int oddCount = 0;
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                oddCount++;
                oddCharacter = entry.getKey();
            }
            entry.setValue(entry.getValue() / 2);
        }

        if (oddCount > 1) {
            System.out.println(-1);
            return;
        }
        int len = str.length() / 2;

        generatepw(1, len, frequencyMap, oddCharacter, "");

    }

}