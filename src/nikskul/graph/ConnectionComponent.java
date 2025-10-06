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
 * Дан неориентированный граф, заданный списком ребер.
 * Требуется определить его компоненты связности.
 *
 * Входные данные
 *
 * Первая строка содержит два целых числа
 * N, M (1≤N≤20000, 1≤M≤200000) — количество вершин и ребер.
 *
 * Следующие M строк описывают ребра графа.
 * Каждое ребро задается двумя числами
 * u, v (1≤u, v≤N, u≠v) — номерами концов ребра.
 *
 * Выходные данные
 *
 * Первая строка должна содержать число L — количество компонент связности.
 * На следующей строке выведите N чисел
 * из диапазона от 1 до L — номера компонент связности для каждой вершины.
 * Компоненты следует пронумеровать от 1 до L в порядке возрастания
 * минимального номера вершины в каждой компоненте.
 *
 * Sample Input 1:
 *
 * 4 2
 * 1 2
 * 3 4
 *
 * Sample Output 1:
 *
 * 2
 * 1 1 2 2
 *
 * Sample Input 2:
 *
 * 2 1
 * 2 1
 *
 * Sample Output 2:
 *
 * 1
 * 1 1
 */
public class ConnectionComponent {

    static int[] used = new int[20_001];
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

            for (int i = 1, k = 1; i <= n; i++) {
                if (used[i] == 0) {
                    dfs(i, k++);
                }
            }

            StringBuilder sb = new StringBuilder();
            int connectionComponents = 0;
            for (int i = 1; i <= n; i++) {
                var component = used[i];
                sb.append(component).append(" ");
                if (component > connectionComponents) {
                    connectionComponents = component;
                }
            }
            pr.println(connectionComponents);
            pr.println(sb);
        }
    }

    static void dfs(int v, int k) {
        if (used[v] != 0) return;
        used[v] = k;
        if (adjList[v] == null) return;
        for (var u : adjList[v]) {
            if (used[u] != 0) continue;
            dfs(u, k);
        }
    }
}
