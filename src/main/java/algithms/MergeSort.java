package algithms;

import java.util.Arrays;

public class MergeSort {

    public static void main(String args[]) {

        int[] a = {2, 4, 1, 3, -2, -129};
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(a);

        System.out.println(Arrays.toString(a));

    }

    public void mergeSort(int[] arr) {

        if (arr.length <= 1) return;

        int middle = arr.length / 2;
        int[] left = new int[middle];
        int[] right = new int[arr.length - middle];

        // fill left and right array
        for(int i = 0; i < middle; ++i) {
            left[i] = arr[i];
        }
        for(int i = middle; i < arr.length; ++i) {
            right[i - middle] = arr[i];
        }

        mergeSort(left);
        mergeSort(right);

        sort(arr, left, right);

    }

    private void sort(int[] arr, int[] left, int[] right) {
        int l = 0, r = 0;
        for (int p = 0; p < arr.length; ++p) {
            if (l < left.length && (r >= right.length || left[l] < right[r])) {
                arr[p] = left[l];
                l ++;
            } else if (r < right.length) {
                arr[p] = right[r];
                r ++;
            }

        }
    }


}
