package com.wm.app.solutions;

/**

 */
public class Leetcode461 extends SolutionBase {
    @Override
    public void solve() {
        int[] nums = scanInts();
        print(hammingDistance(nums[1], nums[2]));
    }

    public int hammingDistance(int x, int y) {
        int res = 0;
        while (x != 0 || y != 0) {
            res += x % 2 == y % 2 ? 0 : 1;
            x = x >> 1;
            y = y >> 1;
        }
        return res;
    }
}