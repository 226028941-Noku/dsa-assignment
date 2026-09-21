public class InsertionSort {

    public static void main(String[] args) {
        //insertion sort

        int[] serviceTime = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};

        System.out.println("Original array:");
        printArray(serviceTime);
        System.out.println();

        insertionSort(serviceTime);

        System.out.println("\nFinal sorted array:");
        printArray(serviceTime);
    }

    private static void insertionSort(int[] serviceTime) {
        int comparisons = 0;
        int shifts = 0;
        int n = serviceTime.length;

        for (int i = 1; i < n; i++) {
            int key = serviceTime[i];
            int j = i - 1;


            while (j >= 0) {
                comparisons++;
                if (serviceTime[j] > key) {
                    serviceTime[j + 1] = serviceTime[j];
                    shifts++;
                    j--;
                } else {
                    break;
                }
            }
            serviceTime[j + 1] = key;

            if (i <= 3) {
                System.out.println("After pass " + i + ": ");
                printArray(serviceTime);
                System.out.println("  Comparisons so far: " + comparisons
                        + " | Shifts so far: " + shifts);
            }
        }

        System.out.println("\nTotal comparisons: " + comparisons);
        System.out.println("Total shifts: " + shifts);
    }

    private static void printArray(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb);
    }
}