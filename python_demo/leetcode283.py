"""
283. 移动零
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
说明:

必须在原数组上操作，不能拷贝额外的数组。
尽量减少操作次数。
"""


class Solution(object):
    def moveZeroes(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        first_0_flag = False
        first_0 = 0
        for i in range(len(nums)):
            if nums[i] == 0:
                if not first_0_flag:
                    first_0_flag = True
                    first_0 = i
            else:
                if first_0_flag:
                    nums[first_0] = nums[i]
                    nums[i] = 0
                    first_0_flag = False
                    if first_0 < len(nums) - 1 and nums[first_0 + 1] == 0:
                        first_0 += 1
                        first_0_flag = True
        return nums
