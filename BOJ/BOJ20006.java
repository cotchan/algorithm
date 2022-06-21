
import java.io.*;
import java.util.*;


public class Main {

    static class Pair {
        String nickname;
        int level;

        public Pair(String nickname, int level) {
            this.nickname = nickname;
            this.level = level;
        }
    }

    static class Room {
        boolean isFull;
        int member;
        int level;
        List<Pair> peoples;

        public Room(int level, int maxv) {
            this.isFull = maxv == 1 ? true : false;
            this.member = 1;
            this.level = level;
            peoples = new ArrayList<>();
        }

        public boolean isValidLevel(int level) {
            int result = Math.abs(this.level - level);
            return result <= 10;
        }
    }

    static int P, M;
    static List<Room> rooms = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] pmInfo = br.readLine().split(" ");
        P = Integer.parseInt(pmInfo[0]);
        M = Integer.parseInt(pmInfo[1]);

        for (int i = 0; i < P; ++i) {
            //레벨 l
            //닉네임 n
            String[] playerInfo = br.readLine().split(" ");
            int l = Integer.parseInt(playerInfo[0]);
            String nickname = playerInfo[1];

            boolean hasRoom = false;

            for (Room room : rooms) {
                if (!room.isValidLevel(l)) continue;
                if (room.isFull) continue;

                room.member++;
                if (room.member == M) {
                    room.isFull = true;
                }

                room.peoples.add(new Pair(nickname, l));
                hasRoom = true;
                break;
            }

            if (!hasRoom) {
                Room newRoom = new Room(l, M);
                newRoom.peoples.add(new Pair(nickname, l));
                rooms.add(newRoom);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (Room room : rooms) {
            Collections.sort(room.peoples, (a, b) -> {
                return a.nickname.compareTo(b.nickname);
            });

            if (room.isFull) {
                sb.append("Started!\n");
            } else {
                sb.append("Waiting!\n");
            }

            for (Pair person : room.peoples) {
                sb.append(person.level).append(" ").append(person.nickname).append("\n");
            }
        }

        System.out.println(sb);
    }
}
