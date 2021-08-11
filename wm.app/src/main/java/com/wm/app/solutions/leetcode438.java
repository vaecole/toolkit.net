package com.wm.app.solutions;

import java.util.*;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * <p>
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 * <p>
 * 说明：
 * <p>
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 * <p>
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * <p>
 * 输出:
 * [0, 6]
 * <p>
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 * 示例 2:
 * <p>
 * 输入:
 * s: "abab" p: "ab"
 * <p>
 * 输出:
 * [0, 1, 2]
 * <p>
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 * <p>
 * ababababab, aab -> [0,2,4,6]
 */
public class leetcode438 extends SolutionBase {
    @Override
    public void solve() {
        LinkedList<Integer> res = new LinkedList<>();
        String[] inputs = scanStrings();
        if (inputs.length <= 1) {
            print("[]");
            return;
        }
        String s = inputs[0];
        String p = inputs[1];
        if (s.length() < p.length()) {
            print("[]");
            return;
        }

        Map<Character, Integer> lookUp = new HashMap<>(p.length());
        buildLookUp(p, lookUp);
        Queue<Character> que = new LinkedList<>();
        int i = 0;
        while (i < s.length()) {
            while (i < s.length() && que.size() < p.length()) {
                char c = s.charAt(i);
                if (!lookUp.containsKey(c)) {
                    que.clear();
                    buildLookUp(p, lookUp);
                } else {
                    int count = lookUp.get(c) - 1;
                    if (count < 0) {
                        char out = que.poll();
                        while (out != c) {
                            lookUp.put(out, lookUp.get(out) + 1);
                            out = que.poll();
                        }
                    } else {
                        lookUp.put(c, count);
                    }
                    que.add(c);
                }
                i++;
            }
            if (que.size() == p.length())
                res.add(i - p.length());
            Character out = que.poll();
            if (out != null)
                lookUp.put(out, lookUp.get(out) + 1);
        }

        printArray(res.toArray());
    }

    private void buildLookUp(String p, Map<Character, Integer> lookUp) {
        lookUp.clear();
        for (char c : p.toCharArray()) {
            if (lookUp.containsKey(c))
                lookUp.put(c, lookUp.get(c) + 1);
            else
                lookUp.put(c, 1);
        }
    }
}
