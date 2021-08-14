"""
56. 合并区间
以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。



示例 1：

输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
示例 2：

输入：intervals = [[1,4],[4,5]]
输出：[[1,5]]
解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。


提示：

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
"""


class Solution(object):
    def merge(self, intervals):
        """
        :type intervals: List[List[int]]
        :rtype: List[List[int]]
        """
        sorted_arrays = self.quick_sort(intervals, 0, len(intervals))
        merged = []
        i = 0
        while i < len(sorted_arrays):
            left = sorted_arrays[i][0]
            right = sorted_arrays[i][1]
            while i < len(sorted_arrays) - 1 and right >= sorted_arrays[i + 1][0]:
                if sorted_arrays[i + 1][1] > right:
                    right = sorted_arrays[i + 1][1]
                i += 1
            merged.append([left, right])
            i += 1

        return merged

    def quick_sort(self, arrays, s, e):
        if e - s <= 1:
            return arrays
        if e - s == 2:
            if arrays[e - 1][0] < arrays[s][0]:
                t = arrays[s]
                arrays[s] = arrays[e - 1]
                arrays[e - 1] = t
            return arrays

        sentinel = s
        i = s
        j = e - 1
        while i < j:
            while i < j and arrays[j][0] >= arrays[sentinel][0]:
                j -= 1
            t = arrays[j]
            arrays[j] = arrays[sentinel]
            arrays[sentinel] = t
            sentinel = j

            while i < j and arrays[i][0] <= arrays[sentinel][0]:
                i += 1
            t = arrays[i]
            arrays[i] = arrays[sentinel]
            arrays[sentinel] = t
            sentinel = i

        self.quick_sort(arrays, s, i)
        self.quick_sort(arrays, i + 1, e)
        return arrays
