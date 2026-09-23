public class QuickSort {

    private static int partitionCount = 0;

    public static void main(String[] args) {

        //Quick Sort
        int[] serviceTime = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};

        System.out.println("Original array:");
        printArray(serviceTime);
        System.out.println();

        partitionCount = 0;
        quickSort(serviceTime, true);

        System.out.println("\nFinal sorted array:");
        printArray(serviceTime);
    }

    public static int quickSort(int[] arr) {
        return quickSort(arr, false);
    }

    private static int quickSort(int[] arr, boolean showPartitions) {
        int[] comparisons = {0};
        if (arr.length > 0) {
            quickSort(arr, 0, arr.length - 1, comparisons, showPartitions);
        }
        return comparisons[0];
    }

    private static void quickSort(int[] arr, int low, int high, int[] comparisons, boolean showPartitions) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high, comparisons, showPartitions);
            quickSort(arr, low, pivotIndex - 1, comparisons, showPartitions);
            quickSort(arr, pivotIndex + 1, high, comparisons, showPartitions);
        }
    }

    private static int partition(int[] arr, int low, int high, int[] comparisons, boolean showPartitions) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            comparisons[0]++;
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        int pivotIndex = i + 1;

        if (showPartitions) {
            partitionCount++;
            if (partitionCount <= 2) {
                System.out.println("Partition stage " + partitionCount + ":");
                System.out.println("  Pivot: " + pivot);
                System.out.println("  Left partition:  " + subarrayToString(arr, low, pivotIndex - 1));
                System.out.println("  Right partition: " + subarrayToString(arr, pivotIndex + 1, high));
                System.out.println();
            }
        }

        return pivotIndex;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static String subarrayToString(int[] arr, int left, int right) {
        if (left > right) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = left; i <= right; i++) {
            sb.append(arr[i]);
            if (i < right) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private static void printArray(int[] arr) {
        System.out.println(subarrayToString(arr, 0, arr.length - 1));
    }
}
