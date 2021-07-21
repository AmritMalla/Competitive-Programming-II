import java.io.*;
import java.util.*;

public class Main {

    public static int solution(String[] words, int[] farr, int[] score, int idx) {

        if(idx == words.length) return 0;

        int scoreNo = solution(words, farr, score, idx + 1);

        String word = words[idx];
        int scoreWord = 0;
        boolean flag = true;

        for(char c: word.toCharArray()){

            if(farr[c - 'a'] == 0){
                flag = false;
            }

            farr[c - 'a']--;
            scoreWord += score[c - 'a'];
        }
        
        //only include scoreWord if flag is true, otherwise 0
        int scoreYes = 0;
        if(flag){
            scoreYes = scoreWord + solution(words, farr, score, idx + 1);
        }

        for(char c: word.toCharArray()){
            farr[c - 'a']++;
        }
        return Math.max(scoreYes, scoreNo);
    }

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        int nofWords = scn.nextInt();
        String[] words = new String[nofWords];
        for(int i = 0 ; i < words.length; i++) {
            words[i] = scn.next();
        }
        int nofLetters = sc n.nextInt();
        char[] letters = new char[nofLetters];
        for (int i = 0; i < letters.length; i++) {
            letters[i] = scn.next().charAt(0);
        }
        int[] score = new int[26];
        for (int i = 0; i < score.length; i++) {
            score[i] = scn.nextInt();
        }
        if (words == null || words.length == 0 || letters == null || letters.length == 0 || score == null
                || score.length == 0) {
            System.out.println(0);
            return;
        }
        int[] farr = new int[score.length];
        for (char ch : letters) {
            farr[ch - 'a']++;
        }
        System.out.println(solution(words, farr, score, 0));

    }
}