package pointers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayProblems {

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

}
