package nikskul.graph.transition;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Матрица смежности -> список смежности
 *
 * Дан граф, заданный своей матрицей смежности.
 * Выведите его представление в виде списка смежности.
 *
 * Входные данные
 *
 * В первой строке записано целое число n (1≤n≤500) — количество вершин в графе.
 *
 * В следующих n строках записано n чисел, каждое равно 0 или 1.
 * Если на пересечении i-й строки и j-го столбца стоит 1,
 * то в графе есть ребро между i-й и j-й вершиной.
 * Гарантируется, что матрица симметрична относительно главной диагонали,
 * а на главной диагонали стоят нули.
 *
 * Выходные данные
 *
 * Выведите n строк. В i-й строке выведите количество
 * соседей вершины i и их номера в возрастающем порядке.
 *
 * Sample Input 2:
 * 10
 * 0110110100
 * 1010000100
 * 1101100111
 * 0010001000
 * 1010000100
 * 1000000000
 * 0001000011
 * 1110100010
 * 0010001101
 * 0010001010
 *
 * Sample Output 2:
 * 5 2 3 5 6 8
 * 3 1 3 8
 * 7 1 2 4 5 8 9 10
 * 2 3 7
 * 3 1 3 8
 * 1 1
 * 3 4 9 10
 * 5 1 2 3 5 9
 * 4 3 7 8 10
 * 3 3 7 9 
 */
public class AdjacencyMatrixToAdjacencyList {
    public static void main(String[] args) throws IOException {
        solution();
    }

    static void solution() throws IOException {
        try (
            var is = new BufferedReader(new InputStreamReader(
                new FileInputStream("input.txt")
                // System.in
            ));
            var pr = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream("output.txt")
                // System.out
            ))
        ) {
            var st = new StringTokenizer(is.readLine());
            int n = Integer.parseInt(st.nextToken());

            LinkedList<Integer>[] adjList = new LinkedList[n + 1];
            for (int i = 1; i <= n; i++) {
                var curr = adjList[i] = new LinkedList<>();
                var str = is.readLine();
                for (int j = 1; j <= str.length(); j++) {
                    if ('1' == str.charAt(j-1)) {
                        curr.add(j);
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for (var vertex : adjList) {
                if (vertex == null) continue;
                sb.append(vertex.size()).append(" ");
                for (var neighbour : vertex) {
                    sb.append(neighbour).append(" ");
                }
                sb.append(System.lineSeparator());
            }
            pr.println(sb);
        }
    }
}
