/**
 * CampusServiceCentre
 *
 * PART D — INTEGRATED SERVICE-CENTRE SYSTEM
 *
 * Ties together the components already built in Parts A-C:
 *   Option 1-3  -> WaitingLineQueue.StudentQueue   (Task A1: Queue)
 *   Option 4-7  -> LinkedList / Node                (Task A2: Singly Linked List)
 *   Option 8    -> Array traversal statistics        (Task A4)
 *   Option 9    -> SelectionSort / InsertionSort / MergeSort / QuickSort (Part B)
 *   Option 10   -> Sorting experiment                (Part C)
 *
 * The Postfix Stack task (A3) is intentionally NOT part of this menu,
 * as the brief states it is a separate, stand-alone exercise.
 *
 * No built-in Stack/Queue/LinkedList/sort classes are used anywhere;
 * every structure is the group's own implementation from Parts A-B.
 */
public class CampusServiceCentre {

    // ---------------------------------------------------------------
    // A small, self-built resizable array of ints. This is what backs
    // Task A4 (daily statistics) and the "sort service times" option,
    // grown manually by doubling -- no ArrayList / built-in collection
    // is used, so it still satisfies the "Array" requirement.
    // ---------------------------------------------------------------
    static class ServiceTimeArray {
        private int[] data;
        private int count;

        ServiceTimeArray(int initialCapacity) {
            data = new int[initialCapacity];
            count = 0;
        }

        void add(int value) {
            if (count == data.length) {
                grow();
            }
            data[count] = value;
            count++;
        }

        private void grow() {
            int[] bigger = new int[data.length * 2];
            for (int i = 0; i < data.length; i++) {
                bigger[i] = data[i];
            }
            data = bigger;
        }

        int size() {
            return count;
        }

        int[] toArray() {
            int[] result = new int[count];
            for (int i = 0; i < count; i++) {
                result[i] = data[i];
            }
            return result;
        }
    }

    // ---------------- Shared program state ----------------
    private static WaitingLineQueue.StudentQueue waitingQueue = new WaitingLineQueue.StudentQueue(100);
    private static LinkedList serviceRecords = new LinkedList();
    private static ServiceTimeArray servedTimes = new ServiceTimeArray(10);

    public static void main(String[] args) {
        int choice;

        do {
            printMenu();
            choice = readInt("Select option: ");
            System.out.println();

            switch (choice) {
                case 1:  addStudentToQueue();        break;
                case 2:  serveNextStudent();          break;
                case 3:  waitingQueue.displayQueue();  break;
                case 4:  addServiceRecord();          break;
                case 5:  serviceRecords.displayStudents(); break;
                case 6:  searchServiceRecord();        break;
                case 7:  removeServiceRecord();        break;
                case 8:  displayDailyStatistics();     break;
                case 9:  sortServiceTimes();           break;
                case 10: runSortingExperiment();       break;
                case 11: System.out.println("Exiting. Goodbye!"); break;
                default: System.out.println("Invalid option, please choose 1-11.");
            }

            System.out.println();

        } while (choice != 11);
    }

    private static void printMenu() {
        System.out.println("========================================");
        System.out.println(" CAMPUS SERVICE CENTRE");
        System.out.println("========================================");
        System.out.println("1. Add student to waiting queue");
        System.out.println("2. Serve next student (remove from queue)");
        System.out.println("3. Display waiting students");
        System.out.println("4. Add student service record (Linked List - insertStudent())");
        System.out.println("5. Display student service records");
        System.out.println("6. Search for student record");
        System.out.println("7. Remove student record");
        System.out.println("8. Display daily statistics");
        System.out.println("9. Sort service times");
        System.out.println("10. Run sorting experiment");
        System.out.println("11. Exit");
    }

    // =================================================================
    // Options 1-3 : Queue  (Task A1, WaitingLineQueue.java)
    // =================================================================

    private static void addStudentToQueue() {
        System.out.println("--- Add Student To Waiting Queue ---");
        int studentNo = readInt("Student number: ");
        System.out.print("Name: ");
        String name = readLine();
        System.out.print("Service type: ");
        String serviceType = readLine();
        int estTime = readInt("Estimated service time (min): ");

        WaitingLineQueue.Student student =
                new WaitingLineQueue.Student(studentNo, name, serviceType, estTime);
        waitingQueue.enqueue(student);
    }

    private static void serveNextStudent() {
        System.out.println("--- Serve Next Student ---");
        WaitingLineQueue.Student served = waitingQueue.dequeue();
        if (served != null) {
            System.out.println("Now serving:");
            served.display();
            // Record the service time so it feeds Option 8 (statistics)
            // and Option 9 (sorting) -- this is the Queue and Array
            // components working together.
            servedTimes.add(served.getEstimatedServiceTime());
        }
    }

    // =================================================================
    // Options 4-7 : Singly Linked List  (Task A2, LinkedList.java)
    // =================================================================

    private static void addServiceRecord() {
        System.out.println("--- Add Student Service Record ---");
        LinkedList.Node newNode = new LinkedList.Node();
        System.out.print("Student number: ");
        newNode.studentNumber = readLine();
        System.out.print("Name: ");
        newNode.studentName = readLine();
        System.out.print("Service type: ");
        newNode.serviceType = readLine();
        newNode.estimatedTime = readInt("Estimated time (min): ");

        System.out.println("Insert where? 1 = beginning, 2 = end, 3 = choose a position");
        int mode = readInt("Choice: ");

        try {
            if (mode == 1) {
                serviceRecords.insertStudent(newNode, 1);
                System.out.println("Inserted at the beginning.");
            } else if (mode == 3) {
                int pos = readInt("Position (e.g. 3 for 3rd): ");
                serviceRecords.insertStudent(newNode, pos);
                System.out.println("Inserted at position " + pos + ".");
            } else {
                serviceRecords.insertAtEnd(newNode);
                System.out.println("Inserted at the end.");
            }
        } catch (NullPointerException e) {
            System.out.println("Could not insert at that position (list is not long enough yet).");
        }
    }

    private static void searchServiceRecord() {
        System.out.println("--- Search Student Record ---");
        System.out.print("Enter student number to search: ");
        String sn = readLine();
        serviceRecords.searchStudent(sn);
    }

    private static void removeServiceRecord() {
        System.out.println("--- Remove Student Record ---");
        System.out.print("Enter student number to remove: ");
        String sn = readLine();
        serviceRecords.deleteStudent(sn);
        System.out.println("If that student number existed, the record has been removed.");
    }

    // =================================================================
    // Option 8 : Array processing  (Task A4 statistics)
    // =================================================================

    private static void displayDailyStatistics() {
        int[] times = servedTimes.toArray();

        System.out.println("========================================");
        System.out.println(" DAILY STATISTICS - SERVICE CENTRE");
        System.out.println("========================================");

        if (times.length == 0) {
            System.out.println("No students have been served yet (use option 2 first).");
            return;
        }

        int totalStudentsServed = times.length;
        int totalServiceTime = 0;
        int highest = times[0];
        int lowest = times[0];
        int longerThan10 = 0;

        for (int i = 0; i < times.length; i++) {
            totalServiceTime += times[i];
            if (times[i] > highest) highest = times[i];
            if (times[i] < lowest) lowest = times[i];
            if (times[i] > 10) longerThan10++;
        }

        double average = (double) totalServiceTime / totalStudentsServed;

        System.out.println("Total students served: " + totalStudentsServed);
        System.out.println("Total service time: " + totalServiceTime + " minutes");
        System.out.println("Average service time: " + average + " minutes");
        System.out.println("Highest service time: " + highest + " minutes");
        System.out.println("Lowest service time: " + lowest + " minutes");
        System.out.println("Number of services longer than 10 minutes: " + longerThan10);
    }

    // =================================================================
    // Option 9 : Sort service times  (Part B algorithms)
    // =================================================================

    private static void sortServiceTimes() {
        System.out.println("--- Sort Service Times ---");
        int[] times = servedTimes.toArray();

        if (times.length == 0) {
            System.out.println("No service times recorded yet. Serve some students first (option 2).");
            return;
        }

        System.out.println("Choose sorting algorithm:");
        System.out.println("1. Selection Sort");
        System.out.println("2. Insertion Sort");
        System.out.println("3. Merge Sort");
        System.out.println("4. Quick Sort");
        int choice = readInt("Choice: ");

        System.out.print("Before sorting: ");
        printArray(times);

        int comparisons;
        switch (choice) {
            case 1: comparisons = SelectionSort.selectionSort(times); break;
            case 2: comparisons = InsertionSort.insertionSort(times); break;
            case 3: comparisons = MergeSort.mergeSort(times);         break;
            case 4: comparisons = QuickSort.quickSort(times);         break;
            default:
                System.out.println("Invalid choice, defaulting to Quick Sort.");
                comparisons = QuickSort.quickSort(times);
        }

        System.out.print("After sorting:  ");
        printArray(times);
        System.out.println("Data-value comparisons used: " + comparisons);
    }

    // =================================================================
    // Option 10 : Sorting experiment  (Part C)
    // =================================================================

    private static void runSortingExperiment() {
        System.out.println("--- Sorting Algorithm Experiment ---");
        int[] sizes = {20, 50, 100, 500};

        System.out.printf("%-16s%-8s%-16s%-16s%n", "Algorithm", "Size", "Comparisons", "Time (ns)");

        for (int size : sizes) {
            int[] original = generateRandomArray(size);
            runOneAlgorithm("Selection Sort", original, 1, size);
            runOneAlgorithm("Insertion Sort", original, 2, size);
            runOneAlgorithm("Merge Sort", original, 3, size);
            runOneAlgorithm("Quick Sort", original, 4, size);
        }

        // Almost-sorted test: sort a 100-element array, then swap five
        // neighbouring pairs, and give every algorithm the same array.
        int[] almostSorted = generateRandomArray(100);
        SelectionSort.selectionSort(almostSorted); // fully sort it first
        for (int p = 0; p < 5; p++) {
            int idx = p * 2;
            int temp = almostSorted[idx];
            almostSorted[idx] = almostSorted[idx + 1];
            almostSorted[idx + 1] = temp;
        }

        System.out.println();
        System.out.println("--- Almost-sorted array test (size 100) ---");
        runOneAlgorithm("Selection Sort", almostSorted, 1, 100);
        runOneAlgorithm("Insertion Sort", almostSorted, 2, 100);
        runOneAlgorithm("Merge Sort", almostSorted, 3, 100);
        runOneAlgorithm("Quick Sort", almostSorted, 4, 100);
    }

    private static void runOneAlgorithm(String label, int[] original, int algoCode, int size) {
        int[] copy = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }

        long start = System.nanoTime();
        int comparisons;
        switch (algoCode) {
            case 1: comparisons = SelectionSort.selectionSort(copy); break;
            case 2: comparisons = InsertionSort.insertionSort(copy); break;
            case 3: comparisons = MergeSort.mergeSort(copy);         break;
            default: comparisons = QuickSort.quickSort(copy);
        }
        long end = System.nanoTime();

        System.out.printf("%-16s%-8d%-16d%-16d%n", label, size, comparisons, (end - start));
    }

    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }
        return arr;
    }

    // ---------------- Small shared helpers ----------------

    private static void printArray(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb);
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = readLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static String readLine() {
        String line = "";
        try {
            int c = System.in.read();
            while (c != -1 && c != '\n') {
                if (c != '\r') {
                    line = line + (char) c;
                }
                c = System.in.read();
            }
        } catch (Exception e) {
            return line;
        }
        return line;
    }
}