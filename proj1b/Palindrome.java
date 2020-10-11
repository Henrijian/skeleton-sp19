public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        if (word == null) {
            return null;
        }
        Deque<Character> returnDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            returnDeque.addLast(word.charAt(i));
        }
        return returnDeque;
    }
    private boolean isPalindrome(Deque<Character> wordDeque, CharacterComparator cc) {
        if (wordDeque == null) {
            return false;
        }
        if (wordDeque.size() <= 1) {
            return true;
        }
        Character first = wordDeque.removeFirst();
        Character last = wordDeque.removeLast();
        if (cc == null) {
            if (!first.equals(last)) {
                return false;
            }
        } else {
            if (!cc.equalChars(first, last)) {
                return false;
            }
        }
        return isPalindrome(wordDeque, cc);
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindrome(wordDeque, null);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindrome(wordDeque, cc);
    }
}
