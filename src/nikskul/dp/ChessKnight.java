package nikskul.dp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ChessKnight {
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

            long[][] dp = new long[n][m];
            dp[0][0] = lazy(dp, n - 1, m - 1, n, m);

            pr.println(dp[n - 1][m - 1]);
        }
    }

    static long lazy(long[][] dp, int row, int col, int MAXROW, int MAXCOL) {
        if (row < 0 || col < 0 || row >= MAXROW || col >= MAXCOL)
            return 0;
        if (row == 0 && col == 0)
            return 1;
        if (dp[row][col] != 0) {
            return dp[row][col];
        }
        dp[row][col] = mod(
            mod(lazy(dp, row - 2, col + 1, MAXROW, MAXCOL)
                + lazy(dp, row + 1, col - 2, MAXROW, MAXCOL)
            ) + mod(lazy(dp, row - 2, col - 1, MAXROW, MAXCOL)
                + lazy(dp, row - 1, col - 2, MAXROW, MAXCOL)
            )
        );
        return dp[row][col];
    }

    static final long MOD = (long) (1e9 + 123);

    static long mod(long a) {
        return ((a % MOD) + MOD) % MOD;

    }
}
