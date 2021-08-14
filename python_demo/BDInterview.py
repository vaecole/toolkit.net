"""
Byte(dance)一面|日期：
2021年6月11日

3. 给定一个未排序的整型数组，找到第一个缺少的正整数。时间复杂度要求O(n)
示例 1:
输入: [1,2,0]
输出: 3
示例 2:
输入: [3,4,-1,1]
输出: 2
示例 3:
输入: [7,8,9,11,12]
输出: 1
"""


class Solution(object):
    def solve(self, nums):
        ary = [0] * len(nums)
        for x in nums:
            if len(nums) >= x > 0:
                ary[x - 1] = 1
        for i in range(0, len(nums)):
            if ary[i] == 0:
                return i + 1

        return len(nums) + 1
