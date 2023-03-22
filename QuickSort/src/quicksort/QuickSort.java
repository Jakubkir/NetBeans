package quicksort;

public class QuickSort {

    public static void main(String[] args) {
        int[] array = new int[]{166, 665, 7, 1, 123, 256, 9, 12, 5846};
        for (int x
                : quickSort(array, 0, array.length - 1)) {
            System.out.println(x);
        }
    }

    private static int[] quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex);
            quickSort(array, pivotIndex + 1, high);

        }
        return array;
        
    }

    private static int partition(int[] array, int low, int high) {

        int pivot = array[low];
        int q = high + 1;
        int p = low - 1;
        while (true) {
            do {
                p++;
            } while (array[p] < pivot);
            do {
                q--;
            } while (array[q] > pivot);
            if (p >= q) {
                return q;
            }
            int temp = array[p];
            array[p] = array[q];
            array[q] = temp;

        }

    }
}
