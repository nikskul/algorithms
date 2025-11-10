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

public class ValidateTopologicalByEdges {

    static List<Integer>[] adj = new LinkedList[(int) 2e5];
    static int[] topological = new int[(int) 2e5];

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

            st = new StringTokenizer(is.readLine());
            for (int i = 1; i <= n; i++) {
                int v = Integer.parseInt(st.nextToken());
                topological[v] = i;
            }

            for (int u = 1; u <= n; u++) {
                var edges = adj[u];
                if (edges == null) continue;
                for (int v : edges) {
                    if (topological[v] <= topological[u]) {
                        pr.println("NO");
                        return;
                    }
                }
            }
            pr.println("YES");
        }
    }
}
