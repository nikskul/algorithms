import static nikskul.mod.ModArithmetic.mDivP;
import static nikskul.mod.ModArithmetic.mMul;
import static nikskul.mod.ModArithmetic.mod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        try (
            BufferedReader is = new BufferedReader(new InputStreamReader(
                new FileInputStream("input.txt")
                // System.in
            ));
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("output.txt")
                // System.out
            ));
            PrintWriter pw = new PrintWriter(os);
        ) {
            StringTokenizer st = new StringTokenizer(is.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            long MOD = (long) (1e9 + 7);

            long[] fact = new long[n+1];
            fact[0] = 1;
            for (int i = 1; i <= n; i++) {
                fact[i] = mMul(fact[i-1], i, MOD);
            }

            long res = 0;
            for (int k = l; k >= 1; k--) {
                if (n < (long) m * k) {
                    continue;
                }
                long curDiv = mMul(fact[m * k], fact[n - m * k], MOD);
                long tempRes = mDivP(fact[n], curDiv, MOD);
                res = (res + tempRes) % MOD;
            }
            pw.println(mod(res, MOD));
        }
    }
}
