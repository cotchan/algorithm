import java.io.*;

public class Main {

    public static final int ALPHABET_SIZE = 26;

    static class Trie {
        Trie[] nxt;
        boolean isFinished;

        public Trie() {
            this.nxt = new Trie[ALPHABET_SIZE];
            this.isFinished = false;
        }

        public void insert(String str, int idx) {
            if (idx == str.length()) {
                isFinished = true;
                return;
            }

            int index = str.charAt(idx) - 'a';

            if (this.nxt[index] == null) {
                this.nxt[index] = new Trie();
            }

            this.nxt[index].insert(str, idx + 1);
        }

        public boolean search(String str, int idx) {
            if (idx == str.length()) {
                return isFinished;
            }

            int index = str.charAt(idx) - 'a';

            if (this.nxt[index] != null) {
                return this.nxt[index].search(str, idx + 1);
            } else {
                return false;
            }
        }
    }

    public static int N, M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        Trie tree = new Trie();

        for (int i = 0; i < N; ++i) {
            String str = br.readLine();
            tree.insert(str, 0);
        }

        int cnt = 0;

        for (int i = 0; i < M; ++i) {
            String queryString = br.readLine();

            if (tree.search(queryString, 0)) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
