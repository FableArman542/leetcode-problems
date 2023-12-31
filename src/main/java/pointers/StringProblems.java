package pointers;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class StringProblems {

    public static void main(String[] args) {
        StringProblems stringProblems = new StringProblems();
//        StringBuilder sb = new StringBuilder("label");
//        sb.deleteCharAt(0);
//        System.out.println(sb.indexOf("l"));
//        System.out.println(sb.toString());
//        System.out.println(stringProblems.parenthesisValidation("function(a ()))"));
//        System.out.println(stringProblems.commonChars(new String[] {"bella", "label", "roller"}));
//        System.out.println("String: " + stringProblems.convertToString(1023432400));
        System.out.println(stringProblems.removeRepeatedSpacesFromString("test        asdfasdasd asdasd    sdgsdg  sdf  sdff"));
    }

    /**
     * LC 13. Roman to Integer
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int res = 0;

        for (int i = 0; i < s.length(); ++i) {
            int val = map.get(s.charAt(i));
            if (i != s.length()-1 && val < map.get(s.charAt(i+1))) {
                val = val*-1;
            }
            res += val;
        }

        return res;
    }

    /**
     * LC 12. Integer to Roman
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        String[] keys = new String[] {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};
        int[] values = new int[] {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

        StringBuilder sb = new StringBuilder();
        for (int i = values.length-1; i >= 0; --i) {
            int a = num/values[i];
            if (a > 0) {
                sb.append(keys[i].repeat(a));
                num = num % values[i];
            }
        }
        return sb.toString();
    }

    /**
     * LC 2390. Removing Stars From a String
     * @param s
     * @return
     */
    public String removeStars(String s) {
        String[] chars = s.split("");
        Stack<String> stack = new Stack<>();

        for (String str : chars) {
            if (Objects.equals(str, "*")) {
                stack.pop();
                continue;
            }
            stack.add(str);
        }

        return String.join("", stack);
    }

    public String removeRepeatedSpacesFromString(String s) {

        return s.replaceAll("\\s+", " ");
    }

    /**
     * Convert an integer to a String
     * @param number
     * @return
     */
    public String convertToString(int number) {
        LinkedList<Character> characters = new LinkedList<>();
        while(number > 0) {
            int n = number % 10;
            char c = (char) (n+48);
            characters.addFirst(c);
            number = number / 10;
        }
        StringBuilder sb = new StringBuilder();
        characters.forEach(sb::append);
        return sb.toString();
    }

    /**
     * LC 1002. Find Common Characters
     * Given array `words` return all common characters that show up in all strings including duplicates
     * @param words
     * @return
     */
    public List<String> commonChars1(String[] words) {
        if (words.length == 0) return Collections.emptyList();
        List<String> res = new ArrayList<>();
        List<StringBuilder> wordsStream = Arrays.stream(words)
                .map(StringBuilder::new)
                .collect(Collectors.toList());
        String[] f = wordsStream.remove(0).toString().split("");
        for (String str : f) {
            if (wordsStream.stream().allMatch(w -> {
                int idx = w.indexOf(str);
                if (idx != -1) w.deleteCharAt(idx);
                return idx != -1;
            })) {
                res.add(str);
            }
        }
        return res;
    }

    public List<String> commonChars(String[] words) {
        if (words.length == 0) return Collections.emptyList();
        List<String> res = new ArrayList<>();
        int[] minFrequencies = new int[26];
        Arrays.fill(minFrequencies, Integer.MAX_VALUE);

        for (String current: words) {
            int[] minCur = new int[26];
            for (char c: current.toCharArray()) {
                minCur[c-'a'] ++;
            }
            for (int i = 0; i < minFrequencies.length; ++i) {
                minFrequencies[i] = Math.min(minCur[i], minFrequencies[i]);
            }
        }

        for (int i = 0; i < minFrequencies.length; ++i) {
            while (minFrequencies[i] > 0) {
                res.add(String.valueOf((char)(i+'a')));
                minFrequencies[i] --;
            }
        }

        return res;
    }

    /**
     * Validate parenthesis in a string
     * @param s
     * @return
     */
    public boolean parenthesisValidation(String s) {
        var stack = new LinkedList<Character>();
        s = s.replaceAll("[^(|)]", ""); // enhancement
        System.out.println(s);
        var arr = s.toCharArray();

        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == '(') {
                stack.addLast(arr[i]);
            } else if (arr[i] == ')') {
                if (stack.size() == 0) return false;
                stack.removeLast();
            }
        }

        return stack.size() == 0;
    }

    /**
     * Validate if a string is a palindrome
     * @param s
     */
    public boolean isPalindrome(String s) {
        if ("".equals(s)) return true;

        StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.reverse().toString();

        s = s.toLowerCase(Locale.ROOT)
                .replaceAll("[^a-zA-Z0-9]", "")
                .replaceAll("\\s+", "");
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l ++;
                r --;
            } else return false;
        }

        return true;
    }

    public boolean isPalindrome1(String s) {
        if ("".equals(s)) return true;

        s = s.toLowerCase(Locale.ROOT)
                .replaceAll("[^a-zA-Z0-9]", "")
                .replaceAll("\\s+", "");

        StringBuilder sb = new StringBuilder();
        sb.append(s);
        return Objects.equals(sb.reverse().toString(), s);
    }

    /**
     * Reverse words in a String
     * @param s
     */
    public String reverseWords(String s) {
        String[] words = s.replaceAll("\\s+", " ").trim().split(" ");
        int p1 = 0;
        int p2 = words.length - 1;
        while (p2 >= p1) {
            String savedWord = words[p1];
            words[p1] = words[p2];
            words[p2] = savedWord;

            p1 ++;
            p2 --;
        }

        return String.join(" ", words);
    }

    /**
     * LC 1456. Maximum Number of Vowels in a Substring of Given Length
     * @param s
     * @param k
     * @return
     */
    public int maxVowels(String s, int k) {
        int left = 0;
        int sum = 0;
        int max = Integer.MIN_VALUE;

        for (int right = 0; right < s.length(); ++right) {
            if (isVowel(s.charAt(right))) {
                sum ++;
            }

            if (right - left + 1 == k) {
                max = Math.max(max, sum);
                sum = (isVowel(s.charAt(left))) ? sum-1 : sum;
                left ++;
            }
        }
        return max;
    }

    /**
     * Reverse vowels in a String
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int p1 = 0;
        int p2 = arr.length - 1;
        while (p2 > p1) {
            if (!isVowel(arr[p1])) p1 ++;
            if (!isVowel(arr[p2])) p2 --;
            if (isVowel(arr[p1]) && isVowel(arr[p2])) {
                char saved = arr[p1];
                arr[p1] = arr[p2];
                arr[p2] = saved;
                p1 ++;
                p2 --;
            }
        }
        return new String(arr);
    }
    private boolean isVowel(char character) {
        return Set.of('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u').contains(character);
    }

    /**
     * Check if subsequence is present
     * for: s = "abc", t = "ahbgdc"
     * returns true
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        if ("".equals(s)) return true;
        int tw = 0;
        int sw = 0;
        while (sw < s.length() && tw < t.length()) {
            if (s.charAt(sw) == t.charAt(tw)) {
                tw ++;
                sw ++;
            } else {
                tw ++;
            }
        }
        return sw == s.length();
    }

    /**
     * LC 443. Compress a char array to ["a","2","b","2","c","3"]
     * @param chars
     * @return
     */
    public int compress(char[] chars) {

        StringBuilder sb = new StringBuilder("");
        int counter = 0;
        int l1 = 0;
        for (int i = 0; i < chars.length; ++i) {

            if (isFirst(chars, i) && isLast(chars, i)) {
                sb.append(chars[i]);
                continue;
            }

            if (isFirst(chars, i)) {
                l1 = i + 1;
                sb.append(chars[i]);
                counter ++;
            }

            if (!isFirst(chars, i) && !isLast(chars, i)) {
                counter ++;
            }

            if (isLast(chars, i)) {
                counter ++;
                sb.append(String.valueOf(counter));
                counter = 0;
            }
        }

        sb.getChars(0, sb.length(), chars, 0);

        return sb.length();

    }

    private boolean isFirst(char[] chars, int i) {
        return i == 0 || chars[i - 1] != chars[i];
    }

    private boolean isLast(char[] chars, int i) {
        return i + 1 == chars.length || chars[i + 1] != chars[i];
    }

}
