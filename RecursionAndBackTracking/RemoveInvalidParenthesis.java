import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void removeInvalidParenthesis(String str, int minRemoval, HashSet<String> ans) {

        if (minRemoval == 0) {
            if (isValid(str)) {
                if(ans.contains(str)) return;
                System.out.println(str);
                ans.add(str);
            }
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            String left = str.substring(0, i);
            String right = str.substring(i + 1);
            removeInvalidParenthesis(left + right, minRemoval - 1, ans);
        }

    }

    public static int getMin(String str) {
        Stack<Character> st = new Stack<>();
        for (char ch : str.toCharArray()) {
            if (st.empty() || ch == '(') {
                st.push(ch);
            } else {
                if (st.peek() == '(') st.pop();
                else st.push(ch);
            }
        }
        return st.size();
    }

    public static boolean isValid(String str) {
        return getMin(str) == 0;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        removeInvalidParenthesis(str, getMin(str), new HashSet<>());
    }

}