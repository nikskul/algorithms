package nikskul.graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Обратные ребра
 *
 * Дано: связный неориентированный граф, заданный списком ребер.
 *
 * Задача: определить количество обратных ребер в графе.
 *
 * Входные данные:
 *
 * В первой строке: два целых числа n (1≤n≤500) и m (0 ≤ m ≤ (n⋅(n−1))/2).
 *
 * В следующих m строках: ребра графа,
 * каждое ребро задается номерами концов u, v (1≤u, v≤n; u≠v).
 *
 * Гарантируется, что каждое ребро описано единожды.
 *
 * Выходные данные:
 *
 * Выведите единственное целое число — количество обратных ребер в графе.
 * Если ответов несколько, выведите любой.
 *
 * Sample Input 1:
 *
 * 5 7
 * 1 2
 * 1 4
 * 1 3
 * 5 2
 * 4 2
 * 2 3
 * 3 4
 * Sample Output 1:
 *
 * 3
 * Sample Input 2:
 *
 * 6 5
 * 1 6
 * 4 1
 * 6 3
 * 5 1
 * 2 4
 * Sample Output 2:
 *
 * 0
 */
public class CountingReverseEdges {

    static boolean[] used = new boolean[20_001];
    static List<Integer>[] adjList = new LinkedList[200_001];

    public static void main(String[] args) throws IOException {
        try (
            var is = new BufferedReader(new InputStreamReader(
                new FileInputStream("input.txt")
                // System.in
            ));
            var pr = new PrintWriter(
                new FileOutputStream("output.txt")
                // System.out
            )
        ) {
            var st = new StringTokenizer(is.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= m; i++) {
                st = new StringTokenizer(is.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                if (adjList[u] == null) adjList[u] = new LinkedList<>();
                adjList[u].add(v);
                if (adjList[v] == null) adjList[v] = new LinkedList<>();
                adjList[v].add(u);
            }

            int wood = 0;
            for (int i = 1; i <= n; i++) {
                if (!used[i]) {
                    wood += dfs(i);
                }
            }

            pr.println(m - wood);
        }
    }

    static int dfs(int s) {
        used[s] = true;
        int count = 0;
        if (adjList[s] == null) return 0;
        for (var v : adjList[s]) {
            if (used[v]) continue;
            count++;
            count += dfs(v);
        }
        return count;
    }
}
