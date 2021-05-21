package mw.solutions;

/**
 *
 * 312. 戳气球
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 *
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 *
 * 求所能获得硬币的最大数量。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [3,1,5,8]
 * 输出：167
 * 解释：
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * 示例 2：
 *
 * 输入：nums = [1,5]
 * 输出：10
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 500
 * 0 <= nums[i] <= 100
 *
 */
public class Leetcode312 extends SolutionBase {
    private int[][] cache;

    @Override
    public void solve() {
        int[] nums = scanInts();
        if (nums == null || nums.length == 0)
            return;
        int[] allNums = new int[nums.length + 2];
        System.arraycopy(nums, 0, allNums, 1, nums.length);
        allNums[0] = 1;
        allNums[allNums.length - 1] = 1;
        cache = new int[allNums.length][allNums.length];
        print(dp(allNums, 1, allNums.length - 2));
    }

    private int dp(int[] nums, int s, int e) {
        if (e < s) return 0;
        if (cache[s][e] > 0) return cache[s][e];

        for (int m = s; m <= e; m++) {
            int leftMax = dp(nums, s, m - 1);
            int rightMax = dp(nums, m + 1, e);
            int current = leftMax + rightMax + (nums[s - 1] * nums[m] * nums[e + 1]);
            cache[s][e] = Math.max(cache[s][e], current);
        }
        return cache[s][e];
    }
}