public class QuickSort {

    private static int partitionCount = 0;

    public static void main(String[] args) {

        //Quick Sort
        int[] serviceTime = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};

        System.out.println("Original array:");
        printArray(serviceTime);
        System.out.println();

        quickSort(serviceTime, 0, serviceTime.length - 1);

        System.out.println("\nFinal sorted array:");
        printArray(serviceTime);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        int pivotIndex = i + 1;

        partitionCount++;
        if (partitionCount <= 2) {
            System.out.println("Partition stage " + partitionCount + ":");
            System.out.println("  Pivot: " + pivot);
            System.out.println("  Left partition:  " + subarrayToString(arr, low, pivotIndex - 1));
            System.out.println("  Right partition: " + subarrayToString(arr, pivotIndex + 1, high));
            System.out.println();
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