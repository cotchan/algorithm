import java.io.*;
import java.util.*;

public class Main {

    static class Trie {
        int depth;
        TreeMap<String, Trie> next;

        public Trie(int depth) {
            next = new TreeMap<>();
            this.depth = depth;
        }

        Trie insert(String key) {
            Trie trie = next.get(key);
            if (trie == null) {
                //없는 자식이면 새로 생성
                Trie nxt = new Trie(depth + 1);
                next.put(key, nxt);
                return nxt;
            } else {
                //이미 있는 자식이면 거기다가 다시 추가
                trie.insert(key);
                return trie;
            }
        }

        boolean find(String key) {
            if (next.containsKey(key)) return true;
            else return false;
        }

        Trie get(String key) {
            return next.get(key);
        }

        void print() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < depth; ++i) {
                sb.append("--");
            }
            String prefix = sb.toString();
            for (String key : next.keySet()) {
                System.out.println(prefix + key);
                Trie nxt = next.get(key);
                nxt.print();
            }
        }
    }

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        Trie trie = new Trie(0);

        for (int i = 0; i < N; ++i) {
            String[] caveInfo = br.readLine().split(" ");
            Trie nxt = null;
            for (int j = 1; j < caveInfo.length; ++j) {
                String caveName = caveInfo[j];
                if (j == 1) {
                    if (trie.find(caveName)) {
                        nxt = trie.get(caveName);
                    } else {
                        nxt = trie.insert(caveName);
                    }
                } else {
                    if (nxt == null) continue;
                    if (nxt.find(caveName)) {
                        nxt = nxt.get(caveName);
                    } else {
                        nxt = nxt.insert(caveName);
                    }
                }
            }
        }

        trie.print();
    }
}
