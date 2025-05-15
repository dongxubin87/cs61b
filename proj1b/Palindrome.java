public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        if (word == null) {
            return null;
        }
        Deque res = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    public boolean isPalindrome(String word) {
        int len = word.length();
        if (len <= 1) {
            return true;
        }
        Deque<Character> deque = wordToDeque(word);
        return helper(deque, deque.size());
    }


    private boolean helper(Deque<Character> deque, int size) {
        if (size <= 1) {
            return true;
        }

        if (deque.removeFirst() != deque.removeLast()) {
            return false;
        }
        return helper(deque, deque.size());
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int len = word.length();
        if (len <= 1) {
            return true;
        }
        for (int i = 0; i < len / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(len - 1 - i))) {
                return false;
            }
        }
        return true;
    }

}
