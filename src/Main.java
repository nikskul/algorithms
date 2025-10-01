import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

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

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    int curr = a[i][j];
                    if (i - 1 <= 0 && j - 1 <= 0)
                        dp[i][j] = curr;
                    else if (j - 1 <= 0)
                        dp[i][j] = curr + dp[i - 1][j];
                    else if (i - 1 <= 0)
                        dp[i][j] = curr + dp[i][j - 1];
                    else
                        dp[i][j] = Math.min(
                            curr + dp[i - 1][j - 1],
                            Math.min(
                                curr + dp[i - 1][j],
                                curr + dp[i][j - 1]
                            )
                        );
                }
            }

            List<Map.Entry<Integer, Integer>> coordinates = new ArrayList<>(n * m);
            int x = n, y = m;
            while (x > 0 && y > 0) {
                coordinates.add(0, Map.entry(x, y));
                if (dp[x][y] == dp[x - 1][y] + a[x][y]) {
                    x--;
                } else if (dp[x][y] == dp[x][y - 1] + a[x][y]) {
                    y--;
                } else {
                    x--;
                    y--;
                }
            }

            pr.println(dp[n][m] + " " + coordinates.size());
            for (var pair : coordinates) {
                pr.println(pair.getKey() + " " + pair.getValue());
            }
        }
    }

    static final long MOD = (long) (1e9 + 33);

    static long mod(long a) {
        return ((a % MOD) + MOD) % MOD;
    }
}
