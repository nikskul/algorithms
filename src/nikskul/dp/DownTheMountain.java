package nikskul.dp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DownTheMountain {
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

            long[][] dp = new long[n][];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(is.readLine());
                dp[i] = new long[i + 1];
                for (int j = 0; j <= i; j++) {
                    dp[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = n - 2; i >= 0; i--) {
                for (int j = 0; j <= i; j++) {
                    dp[i][j] += Math.max(dp[i + 1][j], dp[i + 1][j + 1]);
                }
            }

            pr.println(dp[0][0]);
        }
    }

}
