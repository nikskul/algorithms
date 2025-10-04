package nikskul.dp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class ChessRookWithPathTrace {
    public static void main1(String[] args) throws IOException {

        try (
            var pr = new PrintWriter(
                new FileOutputStream("input.txt")
                // System.out
            )
        ) {
            int n = 1000;
            pr.printf("%d %d", n, n);
            StringJoiner b = new StringJoiner(" ");
            for (int i = 0; i < n; i++) {
                b.add(System.lineSeparator());
                for (int j = 0; j < n; j++) {
                    b.add("1000000000");
                }
            }
            pr.print((long) 1e9 + (long) 1e9 + (long) 1e9);
            pr.print(b);
        }
    }

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

            int[][] a = new int[n + 1][m + 1];
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(is.readLine());
                for (int j = 1; j <= m; j++) {
                    a[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            long[][] dp = new long[n + 1][m + 1];
            long[] lf = new long[n + 1];
            long[] up = new long[m + 1];

            dp[1][1] = up[1] = lf[1] = a[1][1];

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (i + j == 2) continue;
                    if (i == 1) {
                        dp[i][j] = a[i][j] + lf[i];
                        up[j] = dp[i][j];
                    } else if (j == 1) {
                        dp[i][j] = a[i][j] + up[j];
                        lf[i] = dp[i][j];
                    } else {
                        long min = Math.min(lf[i], up[j]);
                        dp[i][j] = a[i][j] + min;
                    }
                    lf[i] = Math.min(dp[i][j], lf[i]);
                    up[j] = Math.min(dp[i][j], up[j]);
                }
            }

            List<Map.Entry<Integer, Integer>> path = new ArrayList<>(n + m);
            int row = n, col = m;
            path.add(Map.entry(row, col));
            while (row > 1 || col > 1) {
                for (int x = 1; x <= Math.max(row, col); ++x) {
                    if (row > x && dp[row][col] == a[row][col] + dp[row - x][col]) {
                        row = row - x;
                        break;
                    }
                    if (col > x && dp[row][col] == a[row][col] + dp[row][col - x]) {
                        col = col - x;
                        break;
                    }
                }
                path.add(Map.entry(row, col));
            }

            StringBuilder b = new StringBuilder();
            for (int j = path.size() - 1; j >= 0; j--) {
                var i = path.get(j);
                b.append(i.getKey()).append(" ").append(i.getValue())
                    .append(System.lineSeparator());
            }
            pr.println(dp[n][m] + " " + path.size());
            pr.print(b);
        }
    }


}
