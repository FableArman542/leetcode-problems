package algithms;

public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] arr = new int[] {1, 3, 4,5, 8, 10, 15, 16};
        int index = binarySearch.binarySearch(arr, 136);
        System.out.println(index);
        if (index != -1) System.out.println(arr[index]);
    }

    /**
     * LC 704. Binary Search
     * Returns the index of the `k` value element in the array
     * If not present returns  -1
     * @param arr
     * @param k
     * @return
     */
    public int binarySearch(int[] arr, int k, int begin, int end) {
        int middle = begin + ((end-begin) / 2);
        if (end < begin) return -1;
        if (arr[middle] < k && begin != end) {
            return binarySearch(arr, k, middle + 1, end);
        } else if (arr[middle] > k && begin != end) {
            return binarySearch(arr, k, begin, middle - 1);
        } else if (arr[middle] == k) {
            return middle;
        }
        return -1;
    }

    public int binarySearch(int[] arr, int k) {
        return binarySearch(arr, k, 0, arr.length - 1);
    }

}
