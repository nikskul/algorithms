package nikskul.graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class PQChessKnight {


    static boolean[][] used = new boolean[100 * 101][100 * 101];

    static class Cell {
        public final int x;
        public final int y;
        public final int k;

        Cell(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }

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
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());


            boolean hasPath = false;

            Cell source = new Cell(x1, y1, 0);
            Queue<Cell> queue = new ArrayDeque<>();
            queue.add(source);
            used[source.x][source.y] = true;
            int moveCount = source.k;
            while (!queue.isEmpty()) {
                Cell curr = queue.poll();
                if (curr.x == x2 && curr.y == y2) {
                    hasPath = true;
                    moveCount = curr.k;
                    break;
                }

                if (curr.x + p <= M && curr.y + q <= N && !used[curr.x + p][curr.y + q]) {
                    queue.add(new Cell(curr.x + p, curr.y + q, curr.k + 1));
                    used[curr.x + p][curr.y + q] = true;
                }
                if (curr.x - p > 0 && curr.y + q <= N && !used[curr.x - p][curr.y + q]) {
                    queue.add(new Cell(curr.x - p, curr.y + q, curr.k + 1));
                    used[curr.x - p][curr.y + q] = true;
                }
                if (curr.x + p <= M && curr.y - q > 0 && !used[curr.x + p][curr.y - q]) {
                    queue.add(new Cell(curr.x + p, curr.y - q, curr.k + 1));
                    used[curr.x + p][curr.y - q] = true;
                }
                if (curr.x - p > 0 && curr.y - q > 0 && !used[curr.x - p][curr.y - q]) {
                    queue.add(new Cell(curr.x - p, curr.y - q, curr.k + 1));
                    used[curr.x - p][curr.y - q] = true;
                }

                if (curr.y + p <= N && curr.x + q <= M && !used[curr.x + q][curr.y + p]) {
                    queue.add(new Cell(curr.x + q, curr.y + p, curr.k + 1));
                    used[curr.x + q][curr.y + p] = true;
                }
                if (curr.y - p > 0 && curr.x + q <= M && !used[curr.x + q][curr.y - p]) {
                    queue.add(new Cell(curr.x + q, curr.y - p, curr.k + 1));
                    used[curr.x + q][curr.y - p] = true;
                }
                if (curr.y + p <= N && curr.x - q > 0 && !used[curr.x - q][curr.y + p]) {
                    queue.add(new Cell(curr.x - q, curr.y + p, curr.k + 1));
                    used[curr.x - q][curr.y + p] = true;
                }
                if (curr.y - p > 0 && curr.x - q > 0 && !used[curr.x - q][curr.y - p]) {
                    queue.add(new Cell(curr.x - q, curr.y - p, curr.k + 1));
                    used[curr.x - q][curr.y - p] = true;
                }
            }

            if (!hasPath) {
                pr.println(-1);
            } else {
                pr.println(moveCount);
            }
        }
    }


}
