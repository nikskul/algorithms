package nikskul.dp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ChessRook {

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

            long sum;

            long[][] dp = new long[n + 1][m + 1];
            dp[1][1] = 1;

            sum = 0;
            for (int row = 2; row <= n; row++) {
                sum = mod(sum + dp[row - 1][1]);
                dp[row][1] = sum;
            }

            sum = 0;
            for (int col = 2; col <= m; col++) {
                sum = mod(sum + dp[1][col - 1]);
                dp[1][col] = sum;
            }

            long[][] rowSum = new long[n + 1][m + 1];
            long[][] colSum = new long[n + 1][m + 1];

            for (int row = 2; row <= n; row++) {
                for (int col = 2; col <= m; col++) {
                    rowSum[row][col] = mod(rowSum[row][col - 1] + dp[row][col - 1]);
                    colSum[row][col] = mod(colSum[row - 1][col] + dp[row - 1][col]);
                    dp[row][col] = mod(rowSum[row][col] + colSum[row][col]);
                }
            }

            pr.println(dp[n][m]);
        }
    }

    static final long MOD = (long) (1e9 + 33);

    static long mod(long a) {
        return ((a % MOD) + MOD) % MOD;
    }

}
