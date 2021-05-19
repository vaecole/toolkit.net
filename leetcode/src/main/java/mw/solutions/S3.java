package mw.solutions;

/**
 * 二进制1的个数
 *
 * 输入样例 1
 * 10
 * 0
 * -32768
 * 输出样例 1
 * 2
 * 0
 * 17
 */
public class S3 extends SolutionBase {
    @Override
    public void solve() {
        int[] nums = scanInts();
        for (int num : nums) {
            int mask = 1;
            int bitWidth = 32;
            int count = 0;
            do {
                count += (num & mask);
                num >>= 1;
            }
            while (--bitWidth > 0);
            print(count);
        }
    }
}