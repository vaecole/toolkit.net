package solutions;

import java.util.Scanner;

public class SolutionBase {
    public void resolve() {
        print("Resolved!");
    }

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
        int len = scanner.nextInt();
        String[] strs = new String[len];
        int index = 0;
        while (len > 0) {
            strs[index++] = scanner.nextLine();
            len--;
        }
        return strs;
    }

    public void print(int value) {
        System.out.println(value);
    }

    public void print(String str) {
        System.out.println(str);
    }
}
