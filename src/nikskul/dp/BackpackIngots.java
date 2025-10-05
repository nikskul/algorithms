package nikskul.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
У вас есть N слитков золота, каждый имеет свой вес.
Также у вас есть рюкзак вместимости S.
Необходимо вычислить максимальный вес, который вы можете с собой унести.
Вы не можете дробить слитки.

Входные данные
В первой строке записано два целых числа S и N (1≤S≤10^4, 1≤N≤300).

Во второй строке записаны nn целых чисел — веса слитков.
Каждый слиток имеет неотрицательный вес, не превосходящий 10^5.

Выходные данные
Выведите единственное число — максимальный суммарный вес,
который вы можете унести.
*/
public class BackpackIngots {
    public static void main(String[] args) throws IOException {
        solution();
    }

    private static void solution() throws IOException {
        try (
            var is = new BufferedReader(new InputStreamReader(
                //new FileInputStream("input.txt")
                System.in
            ));
            var pr = new PrintWriter(
                //new FileOutputStream("output.txt")
                System.out
            )
        ) {
            var st = new StringTokenizer(is.readLine());
            final int S = Integer.parseInt(st.nextToken());
            final int N = Integer.parseInt(st.nextToken());

            int[] s = new int[N + 1];
            st = new StringTokenizer(is.readLine());
            for (int i = 1; i <= N; i++) {
                s[i] = Integer.parseInt(st.nextToken());
            }

            boolean[][] dp = new boolean[N + 1][S + 1];
            dp[0][0] = true;

            for (int i = 1; i <= N; i++) {
                for (int j = 0; j <= S; j++) {
                    if (s[i] <= j) {
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - s[i]];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            for (int j = N; j >= 1; j--) {
                for (int i = S; i >= 1; i--) {
                    if (dp[j][i]) {
                        pr.println(i);
                        return;
                    }
                }
            }
            pr.println(0);
        }
    }
}
