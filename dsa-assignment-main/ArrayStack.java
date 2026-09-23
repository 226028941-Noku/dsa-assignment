public class ArrayStack {
    private final double[] stack;
    private int top;
    private final int size;

    public ArrayStack(int size) {
        this.size = size;
        this.stack = new double[size];
        this.top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public void push(double data) {
        if (isFull()) {
            System.out.println("Stack is full!");
        } else {
            top++;
            stack[top] = data;
        }
    }

    public double pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return 0;
        } else {
            double poppedElement = stack[top];
            top--;
            return poppedElement;
        }
    }

    public double peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return 0;
        } else {
            return stack[top];
        }
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
        } else {
            for (int i = top; i >= 0; i--) {
                System.out.print(stack[i] + " ");
            }
            System.out.println();
        }
    }
}
