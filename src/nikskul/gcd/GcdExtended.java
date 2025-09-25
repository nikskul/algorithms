package nikskul.gcd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GcdExtended {

    public static void main(String[] args) throws IOException {
        try (
            BufferedReader is = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
            PrintWriter wr = new PrintWriter(os);
        ) {
            StringTokenizer st = new StringTokenizer(is.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Point p = extgcd(a, b, 1, 0, 0, 1);
            wr.printf("[%d, %d], r=%d%n", p.x, p.y, p.r);
            wr.printf("(a, b) = %d = ax + by = %d*%d + %d*%d = %d%n",
                p.r, a, p.x, b, p.y, (a*p.x + b*p.y));
        }
    }

    record Point(int x, int y, int r) {}

    private static Point extgcd(
        int r1, int r2,
        int x0, int y0,
        int x1, int y1
    ) {
        if (r2 == 0) return new Point(x0, y0, r1);
        if (r1 < r2) return extgcd(r2, r1, x0, y0, x1, y1);
        int q = r1 / r2;
        int x = x0 - q * x1;
        int y = y0 - q * y1;
        return extgcd(r2, mod(r1, r2), x1, y1, x, y);
    }

    private static int mod(int a, int b) {
        return ((a % b) + b) % b;
    }
}
