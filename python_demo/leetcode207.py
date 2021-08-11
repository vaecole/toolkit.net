"""
207. 课程表
你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。

例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。



示例 1：

输入：numCourses = 2, prerequisites = [[1,0]]
输出：true
解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
示例 2：

输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
输出：false
解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。


提示：

1 <= numCourses <= 105
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
prerequisites[i] 中的所有课程对 互不相同
"""


class Solution(object):
    def canFinish(self, numCourses, prerequisites):
        """
        使用类似于BFS用到的队列来找出环
        :type numCourses: int
        :type prerequisites: List[List[int]]
        :rtype: bool
        """
        if len(prerequisites) == 0:
            return True
        deps = dict()
        for dep in prerequisites:
            if len(dep) == 0:
                continue
            if dep[0] in deps:
                deps[dep[0]].append(dep[1])
            else:
                deps[dep[0]] = [dep[1]]
        # 每个课程都有依赖，说明无论如何都无法完成
        if len(deps) == numCourses:
            return False

        que = []
        for key in deps.keys():
            for d in deps[key]:
                que.append([key, d])
        if len(que) == 0:
            return True

        known = list(que)
        line = que.pop()
        while line:
            c = line[-1]
            if c in deps:
                for d in deps[c]:
                    if d in line:
                        return False
                    if d not in known:
                        que.insert(0, line + [d])
                        known.append(d)
            else:
                known += line
            if len(que) == 0:
                break
            line = que.pop()

        return True

    def tree_visit(self, deps, r, seen):
        """
        此递归函数可以实现找环，但是当链表太长的时候会出现栈溢出
        :param deps:
        :param r:
        :param seen:
        :return:
        """
        res = True
        for c in deps[r]:
            if c in seen:
                res = False
                break
            c_seen = list(seen)
            c_seen.append(c)
            if c in deps:
                # print(c, '->', deps[c])
                res = res and self.tree_visit(deps, c, c_seen)
                if not res:
                    break
        return res
