package pointers;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrayProblems {

    public static void main(String[] args) {
        ArrayProblems arrayProblems = new ArrayProblems();
        int[] arr = {123, -3, -2, 12, 99, 9};
//        System.out.println(arrayProblems.findBiggestSingleDigit(arr));
//        List<int[]> coordinates = generateCircleCoordinates(0, 0, 3);
//        for (int[] coordinate : coordinates) {
//            System.out.println("(" + coordinate[0] + ", " + coordinate[1] + ")");
//        }

//        System.out.println(generateCircleCoordinates(0, 0, 3, 4));
//        reverseArray(arr);

//        System.out.println(Arrays.toString(arr));
//        int[] a = {-1, 0, 21, 7, 4};
//        System.out.println(arrayProblems.getValueWithSameIndex(a));

        System.out.println(arrayProblems.fibonnaci(6));
        PriorityQueue<Integer>a = new PriorityQueue<>();
    }

    /**
     * LC 2215. Find the Difference of Two Arrays
     * @param nums1
     * @param nums2
     * @return
     */
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        var n1 = Arrays.stream(nums1)
                .boxed()
                .collect(Collectors.toSet());
        var n2 = Arrays.stream(nums2)
                .boxed()
                .collect(Collectors.toSet());

        return List.of(
                n1.stream().filter(n -> !n2.contains(n)).collect(Collectors.toList()),
                n2.stream().filter(n -> !n1.contains(n)).collect(Collectors.toList())
        );
    }

    public int fibonnaci(int n) {
        int[] stored = new int[n + 1];
        return fibonnaciSequence(n, stored);
    }

    public int fibonnaciSequence(int n, int[] stored) {
        if (n < 2) return n;
        if (stored[n] != 0) return stored[n];
        return fibonnaciSequence(n- 1, stored) + fibonnaciSequence(n - 2, stored);
    }

    public int getBiggestValue(List<Integer> list) {
        return list.stream().max(Integer::compareTo).orElse(-1);
    }

    public int getValueWithSameIndex(int[] arr) {
        return IntStream.range(0, arr.length)
                .filter(i -> arr[i] == i)
                .map(i -> arr[i])
                .boxed()
                .findFirst().orElse(-1);
    }

    /**
     * LC 7. Reverse Integer
     * @param x
     * @return
     */
    public int reverse(int x) {
        if (x == 0 || x/10 == x) return 0;
        StringBuilder sb = new StringBuilder();
        long l = x;
        Boolean isNegative = l < 0;
        if (isNegative) l = l * (-1);
        while(l > 0) {
            sb.append("" + l % 10);
            l = l/10;
        }
        if (Long.parseLong(sb.toString()) > Integer.MAX_VALUE) {
            return 0;
        }
        return isNegative ? Integer.parseInt("-" + sb.toString()) : Integer.parseInt(sb.toString());
    }

    /**
     * LC 344. Reverse String
     * @param arr
     */
    public static void reverseString(String[] arr) {
        int lP = 0, rP = arr.length - 1;

        while (lP < rP) {
            var temp = arr[lP];
            arr[lP] = arr[rP];
            arr[rP] = temp;
            ++ lP;
            -- rP;
        }
    }

    public static List<List<Double>> generateCircleCoordinates(int xx, int yy, int radius, double nSegments) {
        List<List<Double>> coordinates = new ArrayList<>();

        for (int i = 0; i < nSegments; ++i) {
            var angle = Math.toRadians(((double)i/nSegments) * 360);
            double x = Math.ceil(Math.cos(angle) * radius);
            double y = Math.ceil(Math.sin(angle) * radius);
            coordinates.add(List.of(x + xx, y + yy));
        }

        return coordinates;
    }

    public void wiggleSort(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            if (i % 2 != 0 && (nums[i] < nums[i-1])) {
                // odd
                var temp = nums[i];
                nums[i] = nums[i-1];
                nums[i-1] = temp;

            } else if (i > 0 && i % 2 == 0 && (nums[i] > nums[i-1])) {
                var temp = nums[i];
                nums[i] = nums[i-1];
                nums[i-1] = temp;
            }
        }
    }

    /**
     * Find the biggest single-digit number that occurs in an array
     * @param arr
     * @return
     */
    public Integer findBiggestSingleDigit(int[] arr) {
        return Arrays.stream(arr)
            .filter(number -> number < 10 && number > -10)
            .max().orElse(-1);
    }
    /**
     * LC 238. Compute the product of everything in an array except self
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] resultArray = new int[nums.length];
        int prefix = 1;
        int postfix = 1;

        // compute prefix
        resultArray[0] = 1;
        for (int i = 0; i < nums.length; ++i) {
            if (i > 0) {
                prefix = nums[i-1] * prefix;
                resultArray[i] = prefix;
            }
        }

        // compute prefix
        for (int i = nums.length-1; i >= 0; --i) {
            if (i < nums.length-1) {
                postfix = nums[i+1] * postfix;
                resultArray[i] = resultArray[i] * postfix;
            }
        }

        return resultArray;
    }

    /**
     * LC 605. Check if it's possible to place `n` flowers
     * @param flowerbed
     * @param n - number of flowers
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int actualNumber = 0;
        for (int i = 0; i < flowerbed.length; ++i) {
            if (flowerbed[i] == 0 && ( (i-1 < 0) || flowerbed[i-1]==0 )
                    && ( (i+1 >= flowerbed.length) || flowerbed[i+1]==0 )) {
                actualNumber ++;
                flowerbed[i] = 1;
            }
        }
        return n <= actualNumber;
    }

    /**
     * LC 1431. Get the kids which if they get `extraCandies` they have more than the maximum
     * @param candies
     * @param extraCandies
     * @return
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Arrays.stream(candies).max().orElse(0);
        return Arrays.stream(candies)
                .mapToObj(candy -> (candy + extraCandies) >= max)
                .collect(Collectors.toList());
    }

    /**
     * LC 11. Compute the max area of water
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while(left <= right) {
            maxArea = Math.max(maxArea, calculateArea(left, right, height[left], height[right]));

            if (height[left] < height[right]) {
                left ++;
                System.out.println("HERE");
            } else {
                right --;
                System.out.println("H!");
            }

        }
        return maxArea;
    }

    private int calculateArea(int l, int r, int lV, int rV) {
        int lowestVal = Math.min(lV, rV);
        int dist = r - l;
        BiFunction<Integer, Integer, Boolean> a = (val, side) -> val > side;


        return lowestVal * dist;
    }

    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int operations = 0;
        int left = 0;
        int right = nums.length - 1;

        while(left < right) {
            int sum = nums[left] + nums[right];
            if (sum == k) {
                operations ++;
                left ++;
                right --;
            } else if (sum < k) {
                left ++;
            } else {
                right --;
            }
        }

        return operations;
    }

    /**
     * LC 1. Two sum
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            Integer val = map.get(nums[i]);
            if (val != null) {
                return new int[] { val, i };
            }
            map.put(target - nums[i], i);
        }
        return new int[2];
    }

}
