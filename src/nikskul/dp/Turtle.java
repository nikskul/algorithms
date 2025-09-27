package nikskul.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
Черепашка

В верхнем-левом углу таблицы сидит черепаха. Она умеет ходить только вниз, вправо и вниз-вправо.
В каждой клетке записано число, и черепашка прибавляет его к своей сумме, когда оказывается на клетке.
Черепаха хочет попасть в нижнюю-правую клетку, получив в итоге минимально возможную сумму.
Помогите ей - посчитайте, какую сумму она в итоге наберет?

Входные данные

В первой строке входных данных записано два числа
n, m (1≤n,m≤10001≤n,m≤1000) - количество строк и столбцов в таблице.

В следующих nn строках записано по mm чисел - данная таблица.
Каждое число в таблице по модулю не превышает 10001000. 

Выходные данные

Выведите единственное число - минимальную сумму, которую может набрать черепашка.

Sample Input 1:

4 4
1 3 5 2
-1 4 2 9
5 -6 1 -4
-6 6 -2 2

Sample Output 1:

-7

Sample Input 2:

2 4
1 2 3 4
5 6 7 8

Sample Output 2:

14
 */
public class Turtle {
    public static void main(String[] args) throws IOException {
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
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] table = new int[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(is.readLine());
                for (int j = 0; j < m; j++) {
                    table[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 1; i < n; i++) {
                table[i][0] = table[i - 1][0] + table[i][0];
            }
            for (int i = 1; i < m; i++) {
                table[0][i] = table[0][i - 1] + table[0][i];
            }

            for (int row = 1; row < n; row++) {
                for (int col = 1; col < m; col++) {
                    table[row][col] = Math.min(
                        Math.min(table[row - 1][col - 1], table[row - 1][col]),
                        table[row][col - 1]
                    ) + table[row][col];
                }
            }

            pr.println(table[n - 1][m - 1]);
        }
    }
}
