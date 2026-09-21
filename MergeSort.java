public class MergeSort {

    public static void main(String[] args) {

        int[] serviceTime = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};
        //merge sort

        System.out.println("Original array:");
        printArray(serviceTime);
        System.out.println();

        mergeSort(serviceTime, 0, serviceTime.length - 1);

        System.out.println("\nFinal sorted array:");
        printArray(serviceTime);
    }


    private static void mergeSort(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        System.out.println("Dividing: " + subarrayToString(arr, left, right)
                + " -> " + subarrayToString(arr, left, mid)
                + " and " + subarrayToString(arr, mid + 1, right));

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; i++) leftArr[i] = arr[left + i];
        for (int j = 0; j < n2; j++) rightArr[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        while (i < n1) arr[k++] = leftArr[i++];
        while (j < n2) arr[k++] = rightArr[j++];

        System.out.println("Merging into: " + subarrayToString(arr, left, right));
    }

    private static String subarrayToString(int[] arr, int left, int right) {
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