package mw.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 给你一个整数数组nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

 示例 1：
 输入：nums = [1,2,3]
 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 示例 2：
 输入：nums = [0]
 输出：[[],[0]]

 提示：
 1 <= nums.length <= 10
 -10 <= nums[i] <= 10
 nums 中的所有元素 互不相同

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/subsets
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leetcode78 extends SolutionBase {
    @Override
    public void solve() {
        int[] nums = scanInts();
        List res = subSets_(nums, 0, nums.length - 1);
        res.add(new ArrayList<>());
        print(res);
    }

    private List<List<Integer>> subSets_(int[] nums, int start, int end) {
        if (end - start == -1) {
            return new ArrayList();
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> set = new ArrayList<>();
        if (end - start == 0) {
            set.add(nums[start]);
            res.add(set);
            return res;
        }

        set.add(nums[start]);
        set.add(nums[end]);
        res.add(set);
        res.add(Arrays.asList(nums[end]));
        res.add(Arrays.asList(nums[start]));

        List<List<Integer>> subRes = subSets_(nums, start + 1, end - 1);
        res.addAll(subRes);
        for (List<Integer> set_ : subRes) {
            List<Integer> newSet = new ArrayList<>();
            newSet.addAll(set_);
            newSet.add(nums[end]);
            res.add(newSet);
            newSet = new ArrayList<>();
            newSet.addAll(set_);
            newSet.add(nums[start]);
            res.add(newSet);
            newSet = new ArrayList<>();
            newSet.addAll(set_);
            newSet.add(nums[start]);
            newSet.add(nums[end]);
            res.add(newSet);
        }
        return res;
    }
}