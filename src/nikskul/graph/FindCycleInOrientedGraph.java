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

public class FindCycleInOrientedGraph {

    enum Color { // null = WHITE
        GREY, BLACK
    }

    static Color[] paint = new Color[501];
    static List<Integer>[] adj = new LinkedList[500 * 250];
    static int[] parent = new int[501];
    static int cycleStart = -1, cycleEnd;

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

                if (adj[u] == null) adj[u] = new LinkedList<>();

                adj[u].add(v);
            }

            for (int i = 1; i <= n; i++) {
                if (dfs(i)) break;
            }

            if (cycleStart == -1) {
                pr.println("NO");
            } else {
                var cycle = new LinkedList<Integer>();
                cycle.add(cycleStart);
                for (int v = cycleEnd; v != cycleStart; v = parent[v])
                    cycle.add(1, v);
                StringBuilder sb = new StringBuilder();
                cycle.forEach(v -> sb.append(v).append(" "));
                pr.printf("YES%n%d%n%s", cycle.size(), sb);
            }
        }
    }

    static boolean dfs(int u) {
        try {
            paint[u] = Color.GREY;
            if (adj[u] == null) return false;
            for (int v : adj[u]) {
                if (paint[v] == null) {
                    parent[v] = u;
                    if (dfs(v)) return true;
                } else if (paint[v] == Color.GREY) {
                    cycleEnd = u;
                    cycleStart = v;
                    return true;
                }
            }
            return false;
        } finally {
            paint[u] = Color.BLACK;
        }
    }

}
