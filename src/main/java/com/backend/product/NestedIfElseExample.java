public class NestedIfElseExample {
    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 20;

        if (num1 > 0) {
            if (num2 > 0) {
                System.out.println("Both numbers are positive.");
            } else {
                System.out.println("First number is positive, but second number is non-positive.");
            }
        } else {
            System.out.println("First number is non-positive.");
        }
    }
}
