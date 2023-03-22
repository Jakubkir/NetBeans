package sortowaniebabelkowe;

public class SortowanieBabelkowe {

    public static void main(String[] args) {
        int[] array = new int[]{3, 54, 8, 9, 21, 45, 685, 598, 542, 5, 24, 58, 55, 36, 35, 28, 95, 74, 65};
        int[] sortedArray = BubbleSort(array);
        for (int e
                : sortedArray) {
            System.out.println(e);
        }
    }

    private static int[] BubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                }
            }
        }
        return array;
    }

}
