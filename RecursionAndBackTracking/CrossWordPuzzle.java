import java.util.Scanner;

public class Main {

    public static void solution(char[][] arr, String[] words, int vidx) {
        if (vidx == words.length) {
            print(arr);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == '-' || arr[i][j] == words[vidx].charAt(0)) {

                    if (canPlaceWordHorizontally(arr, words[vidx], i, j)) {
                        boolean[] placedIndices = placeWordHorizontaly(arr, words[vidx], i, j);
                        solution(arr, words, vidx + 1);
                        removeWordHorizontally(arr, placedIndices, i, j);
                    }

                    if (canPlaceWordVertically(arr, words[vidx], i, j)) {
                        boolean[] placedIndices = placeWordVertically(arr, words[vidx], i, j);
                        solution(arr, words, vidx + 1);
                        removeWordVertically(arr, placedIndices, i, j);
                    }

                }
            }
        }

    }

    private static void removeWordVertically(char[][] arr, boolean[] placedIndices, int i, int j) {
        for (int ii = 0; ii < placedIndices.length; ii++) {
            if (placedIndices[ii]) {
                arr[i + ii][j] = '-';
            }
        }
    }

    private static void removeWordHorizontally(char[][] arr, boolean[] placedIndices, int i, int j) {
        for (int jj = 0; jj < placedIndices.length; jj++) {
            if (placedIndices[jj]) {
                arr[i][j + jj] = '-';
            }
        }
    }

    private static boolean[] placeWordVertically(char[][] arr, String word, int i, int j) {
        boolean[] indices = new boolean[word.length()];
        for (int ii = 0; ii < word.length(); ii++) {
            if (arr[i + ii][j] == '-') {
                indices[ii] = true;
                arr[i + ii][j] = word.charAt(ii);
            }
        }
        return indices;
    }

    private static boolean[] placeWordHorizontaly(char[][] arr, String word, int i, int j) {
        boolean[] indices = new boolean[word.length()];
        for (int jj = 0; jj < word.length(); jj++) {
            if (arr[i][j + jj] == '-') {
                indices[jj] = true;
                arr[i][j + jj] = word.charAt(jj);
            }
        }
        return indices;
    }

    private static boolean canPlaceWordVertically(char[][] arr, String word, int i, int j) {
        if (i - 1 >= 0 && arr[i - 1][j] != '+') return false;
        else if (i + word.length() < arr.length && arr[i + word.length()][j] != '+') return false;
        if(i + word.length() > arr.length) return false;
        for (int ii = 0; ii < word.length(); ii++) {
            if (arr[i + ii][j] == '-' || arr[i + ii][j] == word.charAt(ii)) {
                continue;
            } else
                return false;
        }
        return true;
    }

    private static boolean canPlaceWordHorizontally(char[][] arr, String word, int i, int j) {
        if (j - 1 >= 0 && arr[i][j - 1] != '+') return false;
        else if (j + word.length() < arr[i].length && arr[i][j + word.length()] != '+') return false;

        if(j + word.length() > arr[i].length) return false;

        for (int jj = 0; jj < word.length(); jj++) {
            if (arr[i][j + jj] == '-' || arr[i][j + jj] == word.charAt(jj)) {
                continue;
            } else return false;
        }
        return true;

    }


    public static void print(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        char[][] arr = new char[10][10];
        for (int i = 0; i < arr.length; i++) {
            String str = scn.next();
            arr[i] = str.toCharArray();
        }
        int n = scn.nextInt();
        String[] words = new String[n];
        for (int i = 0; i < words.length; i++) {
            words[i] = scn.next();
        }
        solution(arr, words, 0);
    }
}