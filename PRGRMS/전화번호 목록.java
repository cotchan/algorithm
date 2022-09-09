class Solution {

    static final int NUMBER_SIZE = 10;

    static class Trie {
        boolean finished;
        Trie[] next;

        public Trie() {
            finished = false;
            next = new Trie[NUMBER_SIZE];
        }

        public void insert(int idx, String str) {
            if (str.length() == idx) {
                finished = true;
                return;
            }

            int nowNumber = str.charAt(idx) - '0';
            if (this.next[nowNumber] == null) {
                this.next[nowNumber] = new Trie();
            }

            this.next[nowNumber].insert(idx + 1, str);
        }

        public boolean isPrefix(int idx, String str) {
            if (idx == str.length()) {
                for (int i = 0; i < NUMBER_SIZE; ++i) {
                    if (this.next[i] != null) return true;
                }

                return !finished;
            }

            int nowNumber = str.charAt(idx) - '0';

            if (this.next[nowNumber] == null) return false;

            return this.next[nowNumber].isPrefix(idx + 1, str);
        }
    }

    public boolean solution(String[] phone_book) {

        Trie trie = new Trie();

        for (String phone : phone_book) {
            trie.insert(0, phone);
        }

        boolean answer = true;
        for (String phone : phone_book) {
            if (trie.isPrefix(0, phone)) {
                answer = false;
            }
        }

        return answer;
    }
}
