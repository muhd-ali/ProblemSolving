import java.io.*;
import java.util.*;

class Person {
    public final String name;
    public final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return name + ": " + age;
    }
}

/*
 * Part 1: Sort a list of Person class objects by Age.  For Person's with
 * the same age, sort alphabetically by name
 */
class Sorter {
    static void sort(Person[] data) {
        Arrays.sort(data, new Comparator<Person>() {
            public int compare(Person s1, Person s2) {
                if (s1.age != s2.age) {
                    return s1.age - s2.age;
                } else {
                    return s1.name.compareTo(s2.name);
                }
            }
        });
    }
}

/*
 * Part 2: Given a comma separated list of sock colors, return the total number of
 * matching sock pairs that can be made.
 *
 * For example:
 * `red,blue,red,green,green` should return 2
 * `yellow,red,green` should return 0
 */
class SockMatcher {
    public static int getSockPairCount(String sockList) {
        String[] socks = sockList.split(",");
        Map<String, Integer> map = new HashMap<>();
        for(String sock : socks) {
            int count = map.getOrDefault(sock, 0);
            count++;
            map.put(sock, count);
        }

        int count = 0;
        for(String key : map.keySet()) {
            count += map.get(key) / 2;
        }
        return count;
    }
}

/*
Part 3: Implement the board game Othello/Reversi on the following board.
Alternate black and white turns, and don't allow illegal moves.

Extra credit: Have one human play against a computer that always makes a
legal move.

Extra extra credit: Have the computer make at least somewhat strategic moves
rather than just some legal move.
*/

class Othello {
    enum COLOR { NONE, WHITE, BLACK };

    public COLOR[][] spaces = new COLOR[8][8];

    public Othello() {
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                spaces[x][y] = COLOR.NONE;
            }
        }

        spaces[3][4] = COLOR.WHITE;
        spaces[4][3] = COLOR.WHITE;
        spaces[3][3] = COLOR.BLACK;
        spaces[4][4] = COLOR.BLACK;
    }

    private String renderSpace(int x, int y) {
        switch(spaces[x][y]) {
            case BLACK: return "B";
            case WHITE: return "W";
        }
        return " ";
    }

    public void render() {
        System.out.print("  a b c d e f g h\n");
        for(int y = 0; y < 8; y++) {
            System.out.printf("%d %s %s %s %s %s %s %s %s\n",
                    y+1,
                    renderSpace(0,y),
                    renderSpace(1,y),
                    renderSpace(2,y),
                    renderSpace(3,y),
                    renderSpace(4,y),
                    renderSpace(5,y),
                    renderSpace(6,y),
                    renderSpace(7,y)
            );
        }
    }

    static private class Coordinate {
        public int x;
        public int y;

        public Coordinate(int _x, int _y) {
            x = _x;
            y = _y;
        }
    }

    private Coordinate getMoveInput() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String text = in.readLine();
            return new Coordinate(
                    Math.max(0, Math.min(7, text.charAt(0) - 'a')),
                    Math.max(0, Math.min(7, Character.digit(text.charAt(1), 10)-1))
            );
        }
        catch(Exception e) {
            return getMoveInput();
        }
    }

    static private class ValidMove {
        Coordinate move;
        List<Coordinate> turned;
        
        private ValidMove(Coordinate move, List<Coordinate> turned) {
            this.move = move;
            this.turned = turned;
        }

        static List<ValidMove> generateValidMoveList(Coordinate coord, COLOR[][] spaces, COLOR mover) {
            COLOR curr = spaces[coord.x][coord.y];
            if (curr == COLOR.NONE || curr != mover) {
                return null;
            }
            
            COLOR other = curr == COLOR.BLACK ? COLOR.WHITE : COLOR.BLACK;

            List<ValidMove> moves = new ArrayList<>();
            for(int inc_x = -1; inc_x < 2; inc_x++) {
                for(int inc_y = -1; inc_y < 2; inc_y++) {
                    if (inc_x == 0 && inc_y == 0) {
                        continue;
                    }
                    ValidMove move = moveForCoordinate(coord, inc_x, inc_y, other, spaces);
                    if (move != null) {
                        moves.add(move);
                    }
                }
            }
            return moves;
        }

        static Coordinate nextPosInBounds(Coordinate coord, int inc_x, int inc_y, COLOR[][] spaces) {
            int next_x = coord.x + inc_x, next_y = coord.y + inc_y;
            if (next_x >= 0 && next_x < spaces.length) {
                if (next_y >= 0 && next_y < spaces[0].length) {
                    return new Coordinate(next_x, next_y);
                }
            }
            return null;
        }

        static ValidMove moveForCoordinate(Coordinate coord, int inc_x, int inc_y, COLOR other, COLOR[][] spaces) {
            List<Coordinate> turned = new ArrayList<>();
            Coordinate curr = coord, endPos = null;
            while(true) {
                curr = nextPosInBounds(curr, inc_x, inc_y, spaces);
                if (curr == null) {
                    break;
                }
                if (spaces[curr.x][curr.y] == other) {
                    turned.add(curr);
                } else if (spaces[curr.x][curr.y] == COLOR.NONE) {
                    if (turned.size() > 0) {
                        endPos = curr;
                    }
                    break;
                }
            }
            if (endPos != null) {
                return new ValidMove(endPos, turned);
            }
            return null;
        }
    }

    public void play() {
        COLOR mover = COLOR.BLACK;
        while(true) {
            COLOR other = mover == COLOR.BLACK ? COLOR.WHITE : COLOR.BLACK;
            render();
            Coordinate c = getMoveInput();
            boolean madeValidMove = false;
            List<ValidMove> moves = new ArrayList<>();
            for(int x = 0; x < spaces.length; x++) {
                for(int y = 0; y < spaces.length; y++) {
                    if (spaces[x][y] == mover) {
                        List<ValidMove> inner = ValidMove.generateValidMoveList(new Coordinate(x, y), spaces, mover);
                        if (inner != null) {
                            moves.addAll(inner);
                        }
                    }
                }
            }
            for(ValidMove move : moves) {
                if (c.x == move.move.x && c.y == move.move.y) {
                    spaces[c.x][c.y] = mover;
                    for (Coordinate t : move.turned) {
                        spaces[t.x][t.y] = mover;
                    }
                    madeValidMove = true;
                    break;
                }
            }

            
            if (madeValidMove) {
                mover = other;
            } else {
                System.out.println("Try again");
            }
        }
    }
}

public class Test {

    public static void main(String[] args) {
        System.out.print("Part 1:\n");
        Person[] data = {
            new Person("Matt", 50),
            new Person("Lulu", 5),
            new Person("Laura", 49),
            new Person("Abby", 50),
            new Person("Chris", 1),
            new Person("Jen", 35),
            new Person("Flavia", 12),
            new Person("Alicia", 21),
            new Person("Greg", 78),
            new Person("Boris", 9)
        };
        Sorter.sort(data);
        for(Person s : data) {
            System.out.print("\t");
            System.out.println(s);
        }
        System.out.println("\n");

        System.out.print("Part 2:\n");
        int sockPairsCount = SockMatcher.getSockPairCount(
            "red,red,red,red"
        );
        System.out.println("\tSock Pairs: " + sockPairsCount);
        System.out.println("\n");

        System.out.print("Part 3:\n");
        Othello b = new Othello();
        b.play();
    }
}