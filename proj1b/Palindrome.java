public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        if (word == null) {
            return null;
        }
        Deque<Character> res = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        int len = deque.size();
        if (len <= 1) {
            return true;
        }

        return helper(deque, len);
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
        Deque<Character> deque = wordToDeque(word);
        int len = deque.size();
        if (len <= 1) {
            return true;
        }

        while (deque.size() > 1) {
            if (!cc.equalChars(deque.removeFirst(), deque.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
