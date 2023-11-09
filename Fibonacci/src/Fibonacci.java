import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.zip.ZipEntry;

public class Fibonacci {

    public static BigInteger[][] multiply2x2Matrices(BigInteger[][] a, BigInteger[][] b){
        BigInteger c00 = a[0][0].multiply(b[0][0]).add(a[0][1].multiply(b[1][0]));
        BigInteger c01 = a[0][0].multiply(b[0][1]).add(a[0][1].multiply(b[1][1]));
        BigInteger c10 = a[1][0].multiply(b[0][0]).add(a[1][1].multiply(b[1][0]));
        BigInteger c11 = a[1][0].multiply(b[0][1]).add(a[1][1].multiply(b[1][1]));
        return new BigInteger[][] {{c00, c01},{c10, c11}};
    }

    public static BigInteger[][] matPower(BigInteger[][] mat, BigInteger exponent) {
        /**
         * Implement this efficiently. This should compute mat^exponent using only O(log(exponent)) matrix multiplications.
         */
        if(exponent==BigInteger.ZERO) return new BigInteger[][] {{BigInteger.ONE,BigInteger.ZERO},{BigInteger.ZERO,BigInteger.ONE}};
        BigInteger[][] b = matPower(mat , exponent.divide(BigInteger.TWO));
        b = multiply2x2Matrices(b,b);
        if(exponent.mod(BigInteger.TWO).equals(BigInteger.ONE)){
            b = multiply2x2Matrices(b,mat);
        }
        return b;
    }

    public static BigInteger getNthFibonacciNumber(BigInteger n) {
        BigInteger[][] b = new BigInteger[][] {{BigInteger.ZERO, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ONE}};
        return matPower(b, n)[1][0]; // Replace the question marks by correct values.
    }

    public static void main(String[] args) {
        BigInteger b1 = getNthFibonacciNumber(BigInteger.valueOf(10000));
        BigInteger b2 = getNthFibonacciNumber(BigInteger.valueOf(9999));
        BigDecimal b3 = new BigDecimal(b1.toString());
        BigDecimal b4 = new BigDecimal(b2.toString());
        System.out.println(b1);
        System.out.println(b3.divide(b4, 1000, RoundingMode.HALF_EVEN));
    }
}