import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        try (
            BufferedReader is = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream("input.txt")
                    // System.in
                )
            );
            BufferedWriter os = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream("output.txt")
                    // System.out
                )
            );
        ) {
            StringTokenizer st = new StringTokenizer(is.readLine());
            // some logic ...
            os.write("-1");
        }
    }
}
