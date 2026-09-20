import java.util.Scanner;

public class PostfixEvaluator {

    public static double evaluate(String expression) {
        String[] elements = expression.trim().split("\\s+");
        ArrayStack stack = new ArrayStack(elements.length);

        for (String element : elements) {
            if (isNumber(element)) {
                stack.push(Double.parseDouble(element));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                double result = apply(a, b, element);
                stack.push(result);
                System.out.println("Top after " + element + ": " + stack.peek());
            }
            System.out.print("Stack after " + element + ": ");
            stack.display();
        }

        return stack.pop();
    }

    private static boolean isNumber(String element) {
        try {
            Double.parseDouble(element);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static double apply(double a, double b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "x":
            case "*":
                return a * b;
            case "÷":
            case "/":
                return a / b;
            default:
                System.out.println("Unknown operator: " + operator);
                return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Postfix Expression Evaluator ===");
        System.out.println("Supported operators: +  -  x  *  ÷  /");
        System.out.print("Enter a postfix expression (e.g. 5 3 + 2 *): ");

        String postfix = scanner.nextLine();

        System.out.println("\nPostfix expression: " + postfix);
        double result = evaluate(postfix);
        System.out.println("Final result: " + result);

        scanner.close();
    }
}
