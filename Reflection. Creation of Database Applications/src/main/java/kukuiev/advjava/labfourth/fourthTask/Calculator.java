package kukuiev.advjava.labfourth.fourthTask;

public class Calculator {
    @MyAnnotation
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    @MyAnnotation
    public int divide(int a, int b) {
        if (b != 0) {
            return a / b;
        } else {
            throw new ArithmeticException("Ділення на нуль недопустиме.");
        }
    }
}
