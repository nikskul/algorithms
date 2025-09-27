package nikskul.mod;

// Операции по модулю
public class ModArithmetic {

    // Вычисление модуля a mod m, при a < 0
    public static long mod(long a, long m) {
        // Смещаем в положительную часть
        return ((a % m) + m) % m;
    }

    // Бинарное возведение в степень по модулю m
    public static long mFastPow(long a, long n, long m) {
        if (n == 0) return 1;
        if (n % 2 == 1) { // Нечетное, тогда a * a^(n-1)
            return a * mFastPow(a, n - 1, m) % m;
        }
        // Если четное, то вычисляем a^(n/2),
        // тогда a^n = a^(n/2) * a^(n/2)
        long tmp = mFastPow(a, n / 2, m);
        return (tmp * tmp) % m;
    }

    // Умножение по модулю
    public static long mMul(long a, long b, long m) {
        // (ab) mod m = (a mod m * b mod m) mod m
        return mod(a, m) * mod(b, m) % m;
    }

    // Деление по модулю P, где P - простое число
    // Подробнее Малая теорема Ферма
    public static long mDivP(long a, long b, long p) {
        return mMul(a, mFastPow(b, p - 2L, p), p);
    }

    // Деление по модулю M, где M - любое число
    public static long mDivM(long a, long b, long m) {
        // расширенный алгоритм Евклида
        long d = extgcd(a, b, 1, 0, 0, 1).d;
        long x = extgcd(a, b, 1, 0, 0, 1).d;
        if (a % d != 0) return -1L; // Решения нет
        return mMul((a / d), x, m);
    }

    // Алгоритм Евклида (Наибольший общий делитель (НОД).
    public static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, mod(a, b));
    }

    // Расширенный алгоритм Евклида
    public static ExtendedGcd extgcd(
        long a, long b,
        long x1, long y1,
        long x2, long y2
    ) {
        if (b == 0) return new ExtendedGcd(x1, y1, a);
        long q = a / b;
        long x = x1 - q * x2;
        long y = y1 - q * y2;
        return extgcd(b, mod(a, b), x2, y2, x, y);
    }

    public static class ExtendedGcd {
        // d = НОД(a,b) = ax + by;
        // Исходный случай НОД(a*x_1 + b*y_1, a*x_2 + b*x_2)
        // Тогда x_1=1, y_1=0, x_2=0, y_2=1
        // и НОД(a*1+b*0, a*0+b*1) = НОД(a, b)
        public final long x;
        public final long y;
        public final long d;

        public ExtendedGcd(long x, long y, long d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

}
