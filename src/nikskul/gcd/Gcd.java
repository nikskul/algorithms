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

public class Gcd {

    public static void main(String[] args) throws IOException {
        try (
            BufferedReader is = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
            PrintWriter wr = new PrintWriter(os);
        ) {
            StringTokenizer st = new StringTokenizer(is.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int gcd = gcd(a, b);
            wr.println(gcd);
        }
    }

    private static int gcd(int a, int b) {
        if (a < b) return gcd(b, a);
        if (b == 0) return a;
        if (b == 1) return 1;
        return gcd(b, mod(a, b));
    }

    private static int mod(int a, int b) {
        return ((a % b) + b) % b;
    }
}
