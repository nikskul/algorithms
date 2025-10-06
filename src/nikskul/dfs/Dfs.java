package nikskul.dfs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Dfs {

    static boolean[] used = new boolean[101];
    static Set<Integer>[] adjList = new Set[101];

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

                if (adjList[u] == null) adjList[u] = new HashSet<>(n);
                adjList[u].add(v);
                if (adjList[v] == null) adjList[v] = new HashSet<>(n);
                adjList[v].add(u);
            }

            st = new StringTokenizer(is.readLine());
            int source = Integer.parseInt(st.nextToken());
            StringBuilder sb = new StringBuilder();
            int count = dfs(source, sb);

            pr.println(count);
            pr.println(sb.toString().trim());
        }
    }

    static int dfs(int v, StringBuilder sb) {
        used[v] = true;
        sb.append(" ").append(v);
        int count = 1;
        if (adjList[v] == null) return count;
        for (var u : adjList[v]) {
            if (used[u]) continue;
            count += dfs(u, sb) + 1;
            sb.append(" ").append(v);
        }
        return count;
    }
}
