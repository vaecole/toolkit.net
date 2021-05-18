package solutions;

import java.util.Scanner;
import java.util.Stack;

/**
 * 最小回文数
 */
public class S2 extends SolutionBase {
    @Override
    public void solve() {
        String[] strs = scanStrings();
        for (String baseStr : strs) {
            String upper = baseStr.substring(0, baseStr.length() / 2);
            int endIndex = baseStr.length() / 2 + baseStr.length() % 2;
            String lower = baseStr.substring(endIndex);
            String expectedLower = reverseString(upper);
            if (expectedLower.compareTo(lower) > 0) {
                System.out.println(baseStr.substring(0, endIndex) + expectedLower);
            } else {
                String newUpper = supperIntPlusOne(baseStr.substring(0, endIndex));
                System.out.println(newUpper + reverseString(newUpper.substring(0, newUpper.length() - baseStr.length() % 2)));
            }
        }
    }

    public static String reverseString(String str) {
        if (str == null || str == "")
            return str;
        StringBuffer sb = new StringBuffer(str.length());
        Stack stack = new Stack();
        for (char s : str.toCharArray()) {
            stack.push(s);
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static String supperIntPlusOne(String int1) {
        StringBuffer sb = new StringBuffer(int1.length() + 1);
        sb.append(int1);
        int carry = 1;
        for (int i = int1.length() - 1; i >= 0; i--) {
            int base = int1.charAt(i) - '0';
            base += carry;
            sb.replace(i, i + 1, String.valueOf(base % 10));
            if (base >= 10) {
                carry = base / 10;
            } else {
                carry = 0;
            }
        }
        if (carry > 0) {
            sb.insert(0, carry);
        }
        return sb.toString();
    }
}