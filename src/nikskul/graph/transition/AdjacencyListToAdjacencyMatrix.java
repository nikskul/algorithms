package nikskul.graph.transition;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AdjacencyListToAdjacencyMatrix {
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

            int[][] m = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(is.readLine());
                int d = Integer.parseInt(st.nextToken());
                for (int j = 1; j <= d; j++) {
                    int v = Integer.parseInt(st.nextToken());
                    m[i][v] = m[v][i] = 1;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++)
                    sb.append(m[i][j]);
                sb.append(System.lineSeparator());
            }
            pr.print(sb.toString().trim());
        }
    }
}
