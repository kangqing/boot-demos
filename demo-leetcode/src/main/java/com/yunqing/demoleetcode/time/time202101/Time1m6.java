package com.yunqing.demoleetcode.time.time202101;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1.6 并查集 节点路径权值规划
 * @author kangqing
 * @since 2021/1/6 20:44
 */
public class Time1m6 {
    public static void main(String[] args) {

    }
}

class Solution1m6 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int size = equations.size();
        // 并查集，最坏情况下，即equations中每一个字符都不相同
        UnionFind unionFind = new UnionFind(2 * size);
        // 第一部：预处理，将变量的值与 id 进行映射，让并查集低层使用数组实现，方便编码
        Map<String, Integer> map = new HashMap<>(2 * size);
        int id = 0;
        for (int i = 0; i < size; i++) {
            List<String> equation = equations.get(i);
            String str1 = equation.get(0);
            String str2 = equation.get(1);
            // 不存在则存入哈希表
            if (!map.containsKey(str1)) {
                map.put(str1, id++);
            }
            if (!map.containsKey(str2)) {
                map.put(str2, id++);
            }
            // 并查集合并
            unionFind.union(map.get(str1), map.get(str2), values[i]);
        }
        // 第二部：做查询
        final int queriesSize = queries.size();
        // 返回结果
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            String s1 = queries.get(i).get(0);
            String s2 = queries.get(i).get(1);
            // 在哈希表中获取对应的id
            Integer id1 = map.get(s1);
            Integer id2 = map.get(s2);
            // 如果有一个不存在哈希表中，则说明结果是 -1.0
            if (id1 == null || id2 == null) {
                res[i] = -1.0;
            } else {
                // 结果是并查集中两个变量对应权值的比值
                res[i] = unionFind.isConnected(id1, id2);
            }
        }
        return res;
    }

    /**
     * 并查集类
     */
    private static class UnionFind {

        // 低层是一个数组，表示每个节点所对应的父节点
        private final int[] parent;
        // 权值，节点指向父节点的权值
        private final double[] weight;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        /**
         * 合并并查集
         * @param x 节点
         * @param y 节点
         * @param value 权值
         */
        public void union(int x, int y, double value) {
            // 查询两个根节点的id
            int rootX = find(x);
            int rootY = find(y);
            // 根节点相等，说明在一个并查集内，不需要合并
            if (rootX == rootY) return;
            // x的根节点，指向y的根节点
            parent[rootX] = rootY;
            // 计算权值
            weight[rootX] = weight[y] * value / weight[x];
        }

        /**
         * 路径压缩
         * @param x 节点
         * @return 返回根节点id
         */
        public int find(int x) {
            if (x != parent[x]) {
                // 保存原来节点
                int origin = parent[x];
                parent[x] = find(parent[x]);
                // 更新权值
                weight[x] *= weight[origin];
            }
            return parent[x];
        }

        public double isConnected(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return weight[x] / weight[y];
            } else {
                return -1.0d;
            }
        }

    }

}


