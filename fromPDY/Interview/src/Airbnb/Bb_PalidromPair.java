package Airbnb;
import java.util.*;
/*
1. check if it is valid function:
Use two pointer, initially, the start point index is 0
the end pointer index is the length of the input, compare
if the start character is equal to the end character,
if true, start plus 1, end minus 1, until start equals
to end, otherwise it is not a palindrome
2. Use HashMap to record the inverse of the input string
Traverse the input string array
1. if map contains the string, it means they can combine
to a palindrome.
2. if map contains a empty string, and input string is
palindrome, it means they can combine to a palindrome
3. if map contains the prefix of the string and the suffix
of the string is a palindrome, it means they can combine
to a palindrome. input: edcaba  key:edc  => edcabacde
4. if map contains the suffix of the string and the prefix
of the string is a palindrome, it means they can combine
to a palindrome. input: abaedc  key:edc  => cdeabaedc
time: O(n * L) L: average words length space: O(n)
 */
public class Bb_PalidromPair {
	public static boolean isValid (String str) {
		int start = 0;
		int end = str.length() - 1;
		while (start < end) {
			if (str.charAt(start) != str.charAt(end)) return false;
			start ++;
			end--;
		}
		return true;
	}

	public static List<List<Integer>> palindrome (String[] words) {
		List<List<Integer>> res = new ArrayList<>();
		HashMap<String, Integer> map = new HashMap<>();
		if (words == null || words.length == 0) return res;
		for (int i = 0; i < words.length; i++) {
			StringBuilder sb = new StringBuilder(words[i]);
			map.put(sb.reverse().toString(), i);
		}

		for (int i = 0; i < words.length; i++) {
			String str = words[i];
			if (map.containsKey("") && isValid(str) && i != map.get("")) {
				res.add(Arrays.asList(map.get(""), i));
				res.add(Arrays.asList(i, map.get("")));
			}
			if (map.containsKey(str) && map.get(str) != i) {
				res.add(Arrays.asList(i, map.get(str)));
			}
			for (int j = 1; j < str.length(); j++) {
				String prefix = str.substring(0, j);
				String surfix = str.substring(j, str.length());
				if (map.containsKey(prefix) && isValid(surfix)) {
					res.add(Arrays.asList(i, map.get(prefix)));
				}
				if (map.containsKey(surfix) && isValid(prefix)) {
					res.add(Arrays.asList(map.get(surfix), i));
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		String[] words = {"", "aba", "edfcdc", "fde", "cdcfde", "aba"};
		List<List<Integer>> list = palindrome(words);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
