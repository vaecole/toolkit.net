package com.wm.app.solutions;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public abstract class SolutionBase {
    public abstract void solve();

    public int[] scanInts() {
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

    public String[] scanStrings() {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt())
            throw new RuntimeException("Please input a number first to specify the length of String[]");
        int len = Integer.parseInt(scanner.nextLine());
        String[] strs = new String[len];
        int index = 0;
        while (len > 0) {
            strs[index++] = scanner.nextLine();
            len--;
        }
        return strs;
    }

    public void print(Object obj) {
        System.out.println(obj);
    }

    public <T> void printArray(T[] obj) {
        System.out.print("[");
        System.out.print(Arrays.stream(obj).map(Object::toString).collect(Collectors.joining(",")));
        System.out.println("]");
    }
}
