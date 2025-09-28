package nikskul.dp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GrasshopperV2 {
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

            st = new StringTokenizer(is.readLine());
            long[] dp = new long[n+1];
            for (int i = 1; i <= n; i++) {
                dp[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 2; i <= n; i++) {
                if (i > 4) {
                    dp[i] = Math.max(
                        Math.max(dp[i-1], dp[i-3]),
                        dp[i-5]
                    ) + dp[i];
                } else if (i > 2) {
                    dp[i] = Math.max(dp[i-1], dp[i-3]) + dp[i];
                } else {
                    dp[i] = dp[i-1] + dp[i];
                }
            }

            pr.println(dp[n]);
        }
    }
}
