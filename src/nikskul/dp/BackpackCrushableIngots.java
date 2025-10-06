package nikskul.dp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BackpackCrushableIngots {

    public static void main(String[] args) throws IOException {
        solution();
    }

    private static void solution() throws IOException {
        try (
            var is = new BufferedReader(new InputStreamReader(
                new FileInputStream("input.txt")
                //System.in
            ));
            var pr = new PrintWriter(
                new FileOutputStream("output.txt")
                //System.out
            )
        ) {
            var st = new StringTokenizer(is.readLine());
            int S = Integer.parseInt(st.nextToken());
            final int N = Integer.parseInt(st.nextToken());

            int[] s = new int[N + 1];
            st = new StringTokenizer(is.readLine());
            for (int i = 1; i <= N; i++) {
                s[i] = Integer.parseInt(st.nextToken());
            }

            long sum = 0;
            for (int i = 1; i <= N && S > 0; i++) {
                if (s[i] > S) {
                    sum += S;
                    S = 0;
                } else {
                    S -= s[i];
                    sum += s[i];
                }
            }

            pr.println(sum);
        }
    }
}
