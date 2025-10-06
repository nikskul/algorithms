package nikskul.graph.transition;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class AdjacencyMatrixToEdgeList {
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

            Set<Map.Entry<Integer, Integer>> edgeSet = new TreeSet<>(
                Map.Entry.<Integer, Integer>comparingByKey()
                    .thenComparing(Map.Entry::getValue)
            );
            for (int i = 1; i <= n; i++) {
                var str = is.readLine();
                for (int j = 1; j <= i; j++) {
                    if ('1' == str.charAt(j - 1)) {
                        edgeSet.add(Map.entry(j, i));
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for (var edge : edgeSet) {
                sb.append(edge.getKey()).append(" ").append(edge.getValue())
                    .append(System.lineSeparator());
            }
            pr.println(edgeSet.size());
            pr.print(sb.toString().trim());
        }
    }
}
