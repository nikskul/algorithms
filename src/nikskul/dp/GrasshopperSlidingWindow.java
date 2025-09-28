package nikskul.dp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Кузнечик
 *
 * Кузнечик стоит перед клетчатой дорожкой длиной 𝑛.
 * Каждая клетка либо свободна, либо занята.
 * Кузнечик может прыгать на 1, 2, ..., 𝑘 клеток вперед.
 * Задача: найти, сколькими способами он может добраться до последней клетки,
 * не проходя через занятые.
 *
 * **Входные данные**
 *
 * Первая строка содержит два числа 𝑛 и 𝑘 (1 ≤ 𝑘 ≤ 𝑛 ≤ 5 ⋅ 10^5).
 *
 * Вторая строка — строка длины 𝑛, состоящая из нулей и единиц.
 * Ноль — свободная клетка, единица — занятая.
 *
 * **Выходные данные**
 *
 * Выведите одно число — количество способов добраться до последней клетки.
 * Выведите его по модулю 10^9 + 7.
 *
 * **Примеры**
 *
 * **Пример 1:**
 *
 * Вход:
 * 8 3
 * 01100010
 *
 * Выход:
 * 3
 *
 * **Пример 2:**
 *
 * Вход:
 * 8 3
 * 00000001
 *
 * Выход:
 * 0
 *
 * **Пример 3:**
 *
 * Вход:
 * 8 4
 * 00111000
 *
 * Выход:
 * 4
 */
public class GrasshopperSlidingWindow {

    static final long MOD = (long) (1e9 + 7);

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
            int k = Integer.parseInt(st.nextToken());

            var matcher = Pattern.compile("\\d").matcher(is.readLine());

            long[] s = new long[n + k + 1];
            long[] dp = new long[n + k + 1];
            s[k-1] = 0;
            dp[k] = 1;
            for (int i = k; i < n+k; i++) {
                matcher.find();
                s[i] = s[i-1] + dp[i];
                if (matcher.group().equals("0")) {
                    dp[i + 1] = s[i] - s[i - k];
                }
            }

            pr.println(dp[n+k]);
        }
    }
}
