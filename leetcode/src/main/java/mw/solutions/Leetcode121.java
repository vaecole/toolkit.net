package mw.solutions;

/**
 121. 买卖股票的最佳时机
 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。

 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。

 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。



 示例 1：

 输入：[7,1,5,3,6,4]
 输出：5
 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 示例 2：

 输入：prices = [7,6,4,3,1]
 输出：0
 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。


 提示：

 1 <= prices.length <= 105
 0 <= prices[i] <= 104
 */
public class Leetcode121 extends SolutionBase {
    // 解法1：DP解法
    @Override
    public void solve() {
        int[] prices = scanInts();
        if(prices==null||prices.length<=1)
            print(0);

        int[] nums = new int[prices.length-1];
        for(int i=0;i<prices.length-1;i++){
            nums[i] = prices[i+1]-prices[i];
        }
        int res = maxSubSeq(nums, 0, nums.length-1);
        if(res<0)
            print(0);
        print(res);
    }

    private int maxSubSeq(int[] nums, int start, int end){
        if(start>end)   return 0;
        if(start==end)  return nums[start];

        int mid_index=(start+end)/2;
        int mid_max=nums[mid_index];
        int acc=nums[mid_index];
        for(int i=mid_index-1;i>=start;i--){
            acc+=nums[i];
            if(acc>mid_max)
                mid_max=acc;
        }
        acc=mid_max;
        for(int i=mid_index+1;i<=end;i++){
            acc+=nums[i];
            if(acc>mid_max)
                mid_max=acc;
        }

        int left_max = maxSubSeq(nums, start, mid_index-1);
        int right_max = maxSubSeq(nums, mid_index+1, end);
        return max(mid_max, left_max, right_max);
    }

    private int max(int a, int b, int c){
        if(a>=b&&a>=c)
            return a;
        if(a<=b&&c<=b)
            return b;
        return c;
    }

    /*
    解法2：假设第一天就买入，
    检查第二天的价格：如果比第一天低，则应第二天买入；如果比第一天高，则应此时卖出计算收益，并作为当前最高收益；
    检查下一天的价格：如果比上一次买入价低，则应这一天买入；如果比上一次买入价高，则计算收益后与最高收益比较，若高则设为最高收益；
    ...
    以此类推，最后一天结束后，即可得到最高收益。
    */
    private int solve2(int[] prices) {
        if(prices.length <= 1)
            return 0;
        int buy = prices[0], profit = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i]<buy){
                buy=prices[i];
            }
            if(prices[i]-buy>profit){
                profit=prices[i]-buy;
            }
        }
        return profit;
    }
}