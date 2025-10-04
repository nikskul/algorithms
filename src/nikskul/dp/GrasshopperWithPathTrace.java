package nikskul.dp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class GrasshopperWithPathTrace {

    public static void main(String[] args) throws IOException {
        solution();
    }

    // 5 2
    // 10 -100 10 10 10
    private static void solution() throws IOException {
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
            int k = Integer.parseInt(st.nextToken());

            int[] a = new int[n + 1];
            st = new StringTokenizer(is.readLine());
            for (int i = 1; i <= n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            long[] dp = new long[n + 1];
            int[] parent = new int[n + 1];
            Deque<Integer> deque = new ArrayDeque<>();

            deque.add(0);
            for (int i = 1; i <= n; i++) {
                while(!deque.isEmpty() && deque.peekFirst() < i - k) {
                    deque.pollFirst();
                }

                dp[i] = dp[deque.peekFirst()] + a[i];
                parent[i] = deque.peekFirst();

                while (!deque.isEmpty() && dp[deque.peekLast()] >= dp[i]) {
                    deque.pollLast();
                }
                deque.addLast(i);
            }

            List<Integer> path = new ArrayList<>(n);
            for (int x = n; x > 0; x = parent[x]) {
                path.add(x);
            }
            Collections.reverse(path);

            StringBuilder b = new StringBuilder();
            for (int cell : path) {
                b.append(cell).append(" ");
            }
            pr.println(dp[n] + " " + path.size());
            pr.println(b.toString().trim());
        }
    }

}
