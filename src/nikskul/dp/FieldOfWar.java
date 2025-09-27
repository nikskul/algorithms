package nikskul.dp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
Отряду нужно пересечь прямоугольное поле размером 𝑚×𝑛 квадратов.
Путь лежит из левого верхнего угла в правый нижний. Передвигаться можно только вправо и вниз.
Поле неровное, но у отряда есть карта с высотами каждого квадрата.
Опасность перехода между квадратами с высотами ℎ1 и ℎ2 оценивается как |ℎ2−ℎ1|.
Общая опасность пути — это сумма всех опасностей переходов.
Задача — найти минимальную опасность пути из квадрата (1,1) в квадрат (𝑚,𝑛).

**Входные данные:**

Первая строка содержит два числа 𝑚 и 𝑛 (1≤𝑚,𝑛≤100) через пробел.
В следующих 𝑛 строках записано по 𝑚 чисел.
Каждое число 𝑗 из 𝑖-й строки соответствует высоте квадрата (𝑖,𝑗).
 Все высоты — целые числа от 1 до 100.

**Выходные данные:**

Выведите одно число — минимальную опасность пути из квадрата (1,1) в квадрат (𝑚,𝑛).

**Пример 1:**

Вход:
```
2 2
1 1
1 1
```
Выход:
```
0
```

**Пример 2:**

Вход:
```
4 2
1 2 3 5
3 8 4 7
```
Выход:
```
6
```
 */
public class FieldOfWar {
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
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            int[][] field = new int[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(is.readLine());
                for (int j = 0; j < m; j++) {
                    field[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[n][m];
            for (int i = 1; i < n; i++) {
                dp[i][0] = dp[i - 1][0] + Math.abs(field[i][0] - field[i - 1][0]);
            }
            for (int i = 1; i < m; i++) {
                dp[0][i] = dp[0][i - 1] + Math.abs(field[0][i] - field[0][i - 1]);
            }

            for (int row = 1; row < n; row++) {
                for (int col = 1; col < m; col++) {
                        dp[row][col] = Math.min(
                            dp[row-1][col] + Math.abs(field[row][col] - field[row-1][col]),
                            dp[row][col-1] + Math.abs(field[row][col] - field[row][col-1])
                        );
                }
            }

            pr.println(dp[n - 1][m - 1]);
        }
    }
}
