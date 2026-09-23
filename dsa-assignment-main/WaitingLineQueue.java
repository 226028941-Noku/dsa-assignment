
public class WaitingLineQueue {

    static class Student {
        private int studentNo;
        private String name;
        private String serviceType;
        private int estimatedServiceTime;

        public Student(int studentNo, String name, String serviceType, int estimatedServiceTime) {
            this.studentNo = studentNo;
            this.name = name;
            this.serviceType = serviceType;
            this.estimatedServiceTime = estimatedServiceTime;
        }

        public int getEstimatedServiceTime() {
            return estimatedServiceTime;
        }

        public void display() {
            System.out.println(
                "Student No: " + studentNo +
                " | Name: " + name +
                " | Service: " + serviceType +
                " | Estimated Time: " + estimatedServiceTime + " min"
            );
        }
    }

    static class StudentQueue {
        private Student[] students;
        private int front;
        private int rear;
        private int count;
        private int capacity;

        public StudentQueue(int capacity) {
            this.capacity = capacity;
            students = new Student[capacity];
            front = 0;
            rear = -1;
            count = 0;
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public void enqueue(Student student) {
            if (count == capacity) {
                System.out.println("Queue is full.");
                return;
            }

            rear++;
            students[rear] = student;
            count++;

            System.out.println(student.name + " has joined the queue.");
        }

        public Student dequeue() {
            if (isEmpty()) {
                System.out.println("Queue is empty.");
                return null;
            }

            Student student = students[front];
            front++;
            count--;

            return student;
        }

        public Student peek() {
            if (isEmpty()) {
                System.out.println("Queue is empty.");
                return null;
            }

            return students[front];
        }

        public void displayQueue() {
            if (isEmpty()) {
                System.out.println("Queue is empty.");
                return;
            }

            System.out.println();
            System.out.println("========== WAITING LINE ==========");

            for (int i = front; i <= rear; i++) {
                System.out.print((i - front + 1) + ". ");
                students[i].display();
            }

            System.out.println("==================================");
        }
    }

    public static void main(String[] args) {

        StudentQueue queue = new StudentQueue(10);

        Student maria = new Student(221045678, "Maria", "Registration", 12);
        Student tomas = new Student(222034512, "Tomas", "Student Card", 5);
        Student ndapewa = new Student(223041876, "Ndapewa", "Fees", 8);
        Student simon = new Student(221067341, "Simon", "Documents", 4);
        Student anna = new Student(224056789, "Anna", "Academic Enquiry", 10);
        Student peter = new Student(225078912, "Peter", "Document Collection", 6);

        System.out.println("========== STUDENT ARRIVALS ==========");

        queue.enqueue(maria);
        queue.enqueue(tomas);
        queue.enqueue(ndapewa);
        queue.enqueue(simon);
        queue.enqueue(anna);
        queue.enqueue(peter);

        System.out.println();
        System.out.println("Queue after six student arrivals:");
        queue.displayQueue();

        System.out.println();
        System.out.println("Student currently at the front:");

        Student frontStudent = queue.peek();

        if (frontStudent != null) {
            frontStudent.display();
        }

        System.out.println();
        System.out.println("========== SERVING STUDENTS ==========");

        Student servedStudent1 = queue.dequeue();
        System.out.println("Serving student:");
        if (servedStudent1 != null) {
            servedStudent1.display();
        }

        Student servedStudent2 = queue.dequeue();
        System.out.println("Serving student:");
        if (servedStudent2 != null) {
            servedStudent2.display();
        }

        Student servedStudent3 = queue.dequeue();
        System.out.println("Serving student:");
        if (servedStudent3 != null) {
            servedStudent3.display();
        }

        System.out.println();
        System.out.println("Queue after three students have been served:");
        queue.displayQueue();

        System.out.println();
        System.out.println("Is the queue empty? " + queue.isEmpty());
    }
}