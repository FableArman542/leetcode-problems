package pointers;

import java.util.Locale;
import java.util.Objects;
import java.util.Set;

public class StringProblems {

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
