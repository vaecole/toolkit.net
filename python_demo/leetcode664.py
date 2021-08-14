"""
664. 奇怪的打印机
有台奇怪的打印机有以下两个特殊要求：

打印机每次只能打印由 同一个字符 组成的序列。
每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。


示例 1：
输入：s = "aaabbb"
输出：2
解释：首先打印 "aaa" 然后打印 "bbb"。

示例 2：
输入：s = "aba"
输出：2
解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。


提示：
1 <= s.length <= 100
s 由小写英文字母组成
"""

class Solution(object):
    def strangePrinter(self, s):
        """
        :type s: str
        :rtype: int
        """
        if not s:
            return 0
        cache = dict()
        res = self.helper(s, cache)
        return res

    def helper(self, s, cache):
        if len(s) == 0:
            return 0
        if len(s) == 1:
            return 1
        if len(s) == 2:
            return 1 if s[0] == s[1] else 2

        if s in cache:
            return cache[s]

        end = len(s) - 1
        if s[end] == s[0]:
            res = self.helper(s[0:end], cache)
            cache[s] = res
            return res

        res = 0
        for i in range(1, end + 1):
            c_min = self.helper(s[0:i], cache) + self.helper(s[i:end + 1], cache)
            if res < 1 or c_min < res:
                res = c_min
        cache[s] = res
        return res
