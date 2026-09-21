
public class SelectionSort {

    public static void main(String[] args) {
        //section sort

        int[] serviceTime = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};

        System.out.println("Original array:");
        printArray(serviceTime);
        System.out.println();

        selectionSort(serviceTime);

        System.out.println("\nFinal sorted array:");
        printArray(serviceTime);
    }

    private static void selectionSort(int[] serviceTime) {
        int comparisons = 0;
        int swaps = 0;
        int n = serviceTime.length;

        for (int i = 0; i < n - 1; i++) {
            int min = i;

            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (serviceTime[j] < serviceTime[min]) {
                    min = j;
                }
            }

            int temp = serviceTime[i];
            serviceTime[i] = serviceTime[min];
            serviceTime[min] = temp;
            swaps++;

            if (i < 3) {
                System.out.println("After pass " + (i + 1) + ": ");
                printArray(serviceTime);
                System.out.println("  Comparisons so far: " + comparisons
                        + " | Swaps so far: " + swaps);
            }
        }

        System.out.println("\nTotal comparisons: " + comparisons);
        System.out.println("Total swaps: " + swaps);
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