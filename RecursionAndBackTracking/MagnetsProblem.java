import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static boolean solution(char[][] arr, int[] top, int[] left, int[] right, int[] bottom, char[][] ans,
                                   int row, int col) {

        if (col == arr[0].length) {
            col = 0;
            row = row + 1;
        }

        if (row == arr.length) {
            if (isAnswerValid(arr, top, left, right, bottom, ans)) {
                print(ans);
                return true;
            }
            return false;
        }

        if (arr[row][col] == 'L') {
            if (isItSafe(ans, top, left, right, bottom, row, col, '+') && isItSafe(ans, top, left, right, bottom, row, col + 1, '-')) {
                ans[row][col] = '+';
                ans[row][col + 1] = '-';
                if (solution(arr, top, left, right, bottom, ans, row, col + 2)) {
                    return true;
                }
                ans[row][col] = 'X';
                ans[row][col + 1] = 'X';

            }
            if (isItSafe(ans, top, left, right, bottom, row, col, '-') && isItSafe(ans, top, left, right, bottom, row, col + 1, '+')) {
                ans[row][col] = '-';
                ans[row][col + 1] = '+';
                if (solution(arr, top, left, right, bottom, ans, row, col + 2)) {
                    return true;
                }
                ans[row][col] = 'X';
                ans[row][col + 1] = 'X';
            }

        } else if (arr[row][col] == 'T') {
            if (isItSafe(ans, top, left, right, bottom, row, col, '+') && isItSafe(ans, top, left, right, bottom, row + 1, col, '-')) {
                ans[row][col] = '+';
                ans[row + 1][col] = '-';
                if (solution(arr, top, left, right, bottom, ans, row, col + 1)) {
                    return true;
                }
                ans[row][col] = 'X';
                ans[row + 1][col] = 'X';

            }
            if (isItSafe(ans, top, left, right, bottom, row, col, '-') && isItSafe(ans, top, left, right, bottom, row + 1, col, '+')) {
                ans[row][col] = '-';
                ans[row + 1][col] = '+';
                if (solution(arr, top, left, right, bottom, ans, row, col + 1)) {
                    return true;
                }
                ans[row][col] = 'X';
                ans[row + 1][col] = 'X';

            }
        }

        return solution(arr, top, left, right, bottom, ans, row, col + 1);
    }

    static int[] offset = {0, -1, 0, 1};


    private static boolean isItSafe(char[][] ans, int[] top, int[] left, int[] right, int[] bottom, int row, int col, char sign) {

        return isItValidOnSide(ans, row, col, sign)
                && isValidOnRow(ans, row, left[row], right[row], sign)
                && isValidOnCol(ans, col, top[col], bottom[col], sign);

    }

    private static boolean isValidOnCol(char[][] ans, int col, int plusLimit, int minusLimit, char sign) {
        if (plusLimit == -1 && minusLimit == -1) return true;

        int[] count = getCountOnCol(ans, col);

        if(sign == '+'){
            count[0]++;
        }else{
            count[1]++;
        }

        if (plusLimit != -1) {
            if (count[0] > plusLimit) return false;
        }

        if (minusLimit != -1) {
            if (count[1] > minusLimit) return false;
        }
        return true;
    }

    private static boolean isValidOnRow(char[][] ans, int row, int plusLimit, int minusLimit, char sign) {
        if (plusLimit == -1 && minusLimit == -1) return true;
        int[] count = getCountOnRow(ans, row);

        if(sign == '+'){
            count[0]++;
        }else{
            count[1]++;
        }

        if (plusLimit != -1) {
            if (count[0] > plusLimit) return false;
        }

        if (minusLimit != -1) {
            if (count[1] > minusLimit) return false;
        }
        return true;

    }

    private static int[] getCountOnRow(char[][] ans, int row) {
        int[] count = new int[2];
        for (int j = 0; j < ans[0].length; j++) {
            if (ans[row][j] == '+') count[0]++;
            if (ans[row][j] == '-') count[1]++;
        }
        return count;
    }

    private static int[] getCountOnCol(char[][] ans, int col) {
        int[] count = new int[2];
        for (int i = 0; i < ans.length; i++) {
            if (ans[i][col] == '+') count[0]++;
            if (ans[i][col] == '-') count[1]++;
        }
        return count;
    }

    private static boolean isItValidOnSide(char[][] ans, int row, int col, char sign) {
        int n = ans.length, m = ans[0].length;
        for (int k = 0; k < 3; k++) {
            int sideRow = row + offset[k];
            int sideCol = col + offset[k + 1];

            if (isValidIndex(sideRow, sideCol, n, m)) {
                if (ans[sideRow][sideCol] == sign) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValidIndex(int sideRow, int sideCol, int n, int m) {
        return sideRow >= 0 && sideRow < n && sideCol >= 0 && sideCol < m;
    }

    private static boolean isAnswerValid(char[][] arr, int[] top, int[] left, int[] right, int[] bottom, char[][] ans) {

        int n = arr.length, m = arr[0].length;
        for (int i = 0; i < n; i++) {
            int[] count = getCountOnRow(ans, i);
            if (left[i] != -1 && count[0] != left[i]) return false;

            if (right[i] != -1 && count[1] != right[i]) return false;
        }

        for (int j = 0; j < m; j++) {
            int[] count = getCountOnCol(ans, j);
            if (top[j] != -1 && count[0] != top[j]) return false;
            if (bottom[j] != -1 && count[1] != bottom[j]) return false;
        }
        return true;
    }

    public static void print(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        char[][] arr = new char[n][m];
        for (int i = 0; i < arr.length; i++) {
            String str = scn.next();
            arr[i] = str.toCharArray();
        }
        int[] top = new int[m];
        for (int i = 0; i < m; i++) {
            top[i] = scn.nextInt();
        }
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = scn.nextInt();
        }
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            right[i] = scn.nextInt();
        }
        int[] bottom = new int[m];
        for (int i = 0; i < m; i++) {
            bottom[i] = scn.nextInt();
        }

        char[][] ans = new char[n][m];
        for (char[] r : ans) {
            Arrays.fill(r, 'X');
        }
        if(!solution(arr, top, left, right, bottom, ans, 0, 0)){
            System.out.println("No Solution");
        }
    }


}