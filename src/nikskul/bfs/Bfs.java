package nikskul.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

// Breath first algorithm
// Поиск в ширину (Нахождение кратчайшего пути)
public class Bfs {
    public static void main(String[] args) throws IOException {
        try (
            Scanner is = new Scanner(new BufferedReader(
                new FileReader("input.txt")
            ));
            BufferedWriter os = new BufferedWriter(
                new FileWriter("output.txt")
            );
        ) {
            int n = is.nextInt();
            boolean[][] arr = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = is.nextInt() == 1;
                }
            }
            int source = is.nextInt() - 1;
            int target = is.nextInt() - 1;

            int minPath = bfs(arr, source, target);

            os.write(Integer.toString(minPath));
        }
    }

    static int bfs(boolean[][] graph, int source, int target) {
        boolean[] burned = new boolean[graph.length];
        Queue<Map.Entry<Integer, Integer>> queue = new ArrayDeque<>(graph.length);

        queue.add(Map.entry(source, 0));
        burned[source] = true;
        while (!queue.isEmpty()) {
            var entry = queue.poll();
            int u = entry.getKey();
            int k = entry.getValue();
            if (u == target) {
                return k;
            }
            for (int v = 0; v < graph[u].length; v++) {
                if (graph[u][v] && !burned[v]) {
                    queue.add(Map.entry(v, k + 1));
                    burned[v] = true;
                }
            }
        }
        return -1;
    }
}
