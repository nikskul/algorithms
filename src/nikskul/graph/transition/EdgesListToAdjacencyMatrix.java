package nikskul.graph.transition;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class EdgesListToAdjacencyMatrix {
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
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int[][] m = new int[v + 1][v + 1];
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(is.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                m[v1][v2] = m[v2][v1] = 1;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++)
                    sb.append(m[i][j]);
                sb.append(System.lineSeparator());
            }
            pr.print(sb.toString().trim());
        }
    }
}
