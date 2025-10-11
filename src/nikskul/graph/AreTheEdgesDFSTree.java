package nikskul.graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Как проверить предыдущую задачу?
 *
 * Вам нужно проверить, может ли данный набор ребер
 * образовывать дерево обхода в глубину.
 *
 * Входные данные:
 *
 * 1. В первой строке записаны два целых числа
 * n (1 ≤ n ≤ 500) и m (0 ≤ m ≤ 2n⋅(n−1)).
 * 2. В следующих m строках даны ребра графа,
 * каждое из которых задается двумя числами u и v (1 ≤ u, v ≤ n; u ≠ v).
 * Гарантируется, что ребра не повторяются и граф не ориентирован.
 * 3. Далее идет число q (0 ≤ q ≤ m).
 * 4. В следующей строке записаны q чисел — номера ребер из выбранного множества.
 *
 * Выходные данные:
 *
 * Выведите <<YES>>, если выбранное множество ребер
 * может образовывать дерево обхода в глубину, иначе — <<NO>>.
 *
 * Пример 1:
 *
 * Sample Input:
 * 10 14
 * 5 6
 * 7 6
 * 3 1
 * 2 7
 * 10 2
 * 10 8
 * 7 9
 * 10 1
 * 4 8
 * 1 4
 * 6 1
 * 2 8
 * 7 8
 * 9 6
 * 10
 * 14 10 11 8 2 4 6 3 9 12
 *
 * Sample Output:
 * NO
 *
 * Пример 2:
 *
 * Sample Input:
 * 6 10
 * 1 3
 * 3 5
 * 1 6
 * 1 5
 * 1 4
 * 5 6
 * 6 4
 * 2 6
 * 2 1
 * 3 2
 * 5
 * 5 3 1 8 4
 *
 * Sample Output:
 * NO
 *
 * Пример 3:
 *
 * Sample Input:
 * 6 10
 * 5 3
 * 4 6
 * 1 2
 * 6 5
 * 3 2
 * 6 1
 * 1 4
 * 1 3
 * 2 6
 * 1 5
 * 5
 * 9 1 7 4 8
 *
 * Sample Output:
 * YES
 */
public class AreTheEdgesDFSTree {

    static List<Integer>[] G = new LinkedList[501];
    static List<Integer>[] T = new LinkedList[501];
    static int[][] edges = new int[175_000][2];
    static boolean[] used = new boolean[501];
    static int[] component = new int[501];

    public static void main(String[] args) throws IOException {
        try (
            var is = new BufferedReader(new InputStreamReader(
                new FileInputStream("input.txt")
                //System.in
            ));
            var pr = new PrintWriter(
                new FileOutputStream("output.txt")
                //System.out
            )
        ) {
            var st = new StringTokenizer(is.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= m; i++) {
                st = new StringTokenizer(is.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                if (G[u] == null) G[u] = new LinkedList<>();
                if (G[v] == null) G[v] = new LinkedList<>();
                G[u].add(v);
                G[v].add(u);
                edges[i][0] = u;
                edges[i][1] = v;
            }

            st = new StringTokenizer(is.readLine());
            int q = Integer.parseInt(st.nextToken());

            // Строим древо из предложенных ответов
            if (q != 0) {
                st = new StringTokenizer(is.readLine());
                for (int i = 1; i <= q; i++) {
                    int idx = Integer.parseInt(st.nextToken());
                    int u = edges[idx][0];
                    int v = edges[idx][1];

                    if (T[u] == null) T[u] = new LinkedList<>();
                    if (T[v] == null) T[v] = new LinkedList<>();
                    T[u].add(v);
                    T[v].add(u);
                }
            }

            int k = 0; // Определяем компоненты связности вершин
            for (int i = 1; i <= n; i++) {
                if (used[i]) continue;
                dfsComponent(G, i, ++k);
            }

            boolean[] validComponent = new boolean[k+1];
            validComponent[0] = true;

            for (int u = 1; u <= n; u++){
                if (validComponent[component[u]]) continue;

                Arrays.fill(used, false);
                if (!dfs(T, u, G)) continue;

                boolean valid = true;
                for (int i = 1; i <= n; i++) {
                    if (component[i] == component[u] && !used[i]) {
                        valid = false;
                        break;
                    }
                }

                if (valid) validComponent[component[u]] = true;
            }

            for (var valid : validComponent) {
                if (!valid){
                    pr.println("NO");
                    return;
                }
            }
            pr.println("YES");
        }
    }

    static boolean dfs(
        List<Integer>[] tree, int u, List<Integer>[] graph
    ) {
        used[u] = true;
        if (tree[u] == null)
            return graph[u] == null || graph[u].isEmpty();
        for (int v : tree[u]) {
            if (used[v]) continue;
            if (!dfs(tree, v, graph)) return false;
        }
        for (int v : graph[u]) {
            if (!used[v]) return false;
        }
        return true;
    }

    static void dfsComponent(List<Integer>[] graph, int u, int k) {
        used[u] = true;
        component[u] = k;
        if (graph[u] == null) return;
        for (int v : graph[u]) {
            if (used[v]) continue;
            dfsComponent(graph, v, k);
        }
    }

}
