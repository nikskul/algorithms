package nikskul.dp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
Кузнечик

Кузнечик сидит перед клетчатой полоской длиной 𝑛.
В каждой клетке записано целое число, которое определяет количество конфет,
которые кузнечик получит, оказавшись в этой клетке.
Он может прыгать на следующую клетку или через одну.
Задача — определить, какое максимальное количество конфет может съесть кузнечик,
если в конце он должен оказаться в последней клетке.
Важно помнить, что количество конфет может быть отрицательным.

Входные данные

В первой строке указано одно целое число 𝑛 (1 ≤ 𝑛 ≤ 10^5) — длина полоски.

Во второй строке через пробел записаны 𝑛 целых чисел
𝑎𝑖 (−10^9 ≤ 𝑎𝑖 ≤ 10^9) — количество конфет в каждой клетке.

Выходные данные

Выведите одно целое число — максимальное количество конфет, которое может собрать кузнечик.

Пример 1:

Вход:
5
4 7 6 6 6

Выход:
29

Пример 2:

Вход:
10
-4 -4 -5 -2 1 3 1 2 -4 -3

Выход:
-2
 */
public class Grasshopper {
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
                dp[i] = Math.max(dp[i-2], dp[i-1]) + dp[i];
            }

            pr.println(dp[n]);
        }
    }
}
