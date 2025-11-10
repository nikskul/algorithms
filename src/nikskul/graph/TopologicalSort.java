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

public class TopologicalSort {

    enum Color { // null = WHITE
        GREY, BLACK
    }

    static Color[] paint = new Color[501];
    static List<Integer>[] adj = new LinkedList[500 * 250];
    static int[] topological = new int[501];
    static int k = 1;

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

            boolean hasCycle = false;
            for (int i = 1; i <= n; i++) {
                if (paint[i] == Color.BLACK) continue;
                if ((hasCycle = dfsCycle(i))) break;
            }

            if (hasCycle) {
                pr.println("NO");
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = k-1; i > 0; i--) {
                    sb.append(topological[i]).append(" ");
                }
                pr.printf("YES%n%s", sb);
            }
        }
    }

    static boolean dfsCycle(int u) {
        try {
            paint[u] = Color.GREY;
            if (adj[u] == null) return false;
            for (int v : adj[u]) {
                if (paint[v] == null) {
                    if (dfsCycle(v)) return true;
                } else if (paint[v] == Color.GREY) {
                    return true;
                }
            }
            return false;
        } finally {
            topological[k++] = u;
            paint[u] = Color.BLACK;
        }
    }


}
