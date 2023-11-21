public class SwitchCaseExample {
    public static void main(String[] args) {
        char grade = 'B';

        switch (grade) {
            case 'A':
                System.out.println("Excellent!");
                break;
            case 'B':
                System.out.println("Good job!");
                break;
            case 'C':
                System.out.println("Satisfactory.");
                break;
            case 'D':
                System.out.println("Needs improvement.");
                break;
            default:
                System.out.println("Invalid grade.");
        }
    }
}
