package mw;

import mw.solutions.leetcode438;
import mw.solutions.Leetcode312;
import mw.solutions.Leetcode78;
import mw.solutions.SolutionBase;
import mw.sourcecode.demo.NIODemo;
import mw.sourcecode.demo.ThreadLocalDemo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SolutionBase solution = new NIODemo();
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
