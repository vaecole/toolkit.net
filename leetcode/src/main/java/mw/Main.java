package mw;

import mw.solutions.Leetcode121;
import mw.solutions.Leetcode78;
import mw.solutions.SolutionBase;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SolutionBase solution = new Leetcode121();
        solution.solve();
    }

    public static void print(Object str){
        System.out.println(str);
    }

    public static int[] scanInts() {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int[] nums = new int[len];
        int index = 0;
        while (len > 0) {
            nums[index++] = scanner.nextInt();
            len--;
        }
        return nums;
    }

    public static String[] scanStrings() {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        String[] strs = new String[len];
        int index = 0;
        while (len > 0) {
            strs[index++] = scanner.nextLine();
            len--;
        }
        return strs;
    }
}
