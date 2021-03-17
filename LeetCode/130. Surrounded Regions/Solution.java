import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Solution {
    class IndexPair {
        int i, j;
        IndexPair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return String.format("[i=%d, j=%d]", i, j);
        }
    }

    class Graph {
        char[][] board;
        private HashMap<String, Boolean> isVisited;
        Graph(char[][] board) {
            this.board = board;
            this.reset();
        }

        void reset() {
            isVisited = new HashMap<>();
        }

        List<IndexPair> neighborsFor(IndexPair p) {
            List<IndexPair> list = new ArrayList<>();
            if (p.i - 1 >= 0) {
                list.add(new IndexPair(p.i - 1, p.j));
            }
            if (p.j - 1 >= 0) {
                list.add(new IndexPair(p.i, p.j - 1));
            }

            if (p.i + 1 < board.length) {
                list.add(new IndexPair(p.i + 1, p.j));
            }
            if (p.j + 1 < board[0].length) {
                list.add(new IndexPair(p.i, p.j + 1));
            }
            return list;
        }

        void secure(IndexPair p) {
            isVisited.put(p.toString(), true);
            for (IndexPair n : neighborsFor(p)) {
                if (board[n.i][n.j] == 'O' && !isVisited.containsKey(n.toString())) {
                    secure(n);
                }
            }
        }

        void makeFlips() {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == 'O') {
                        if (!isVisited.containsKey(new IndexPair(i, j).toString())) {
                            board[i][j] = 'X';
                        }
                    }
                }
            }
        }

        @Override
        public String toString() {
            String s = "";
            for (char[] row : board) {
                s += Arrays.toString(row) + "\n";
            }
            return s;
        }
    }
    
    public void solve(char[][] board) {
        Graph g = new Graph(board);
        List<IndexPair> borderPairs = findBorderOs(board);
        for (IndexPair borderPair : borderPairs) {
            g.secure(borderPair);
        }
        g.makeFlips();
    }

    List<IndexPair> findBorderOs(char[][] board) {
        List<IndexPair> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') {
                list.add(new IndexPair(i, 0));
            }
            if (board[i][board[0].length - 1] == 'O') {
                list.add(new IndexPair(i, board[0].length - 1));
            }
        }

        if (board.length > 0) {
            for (int i = 0; i < board[0].length; i++) {
                if (board[0][i] == 'O') {
                    list.add(new IndexPair(0, i));
                }
                if (board[board.length - 1][i] == 'O') {
                    list.add(new IndexPair(board.length - 1, i));
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        char[][] board = {};
        Solution sol = new Solution();
        sol.solve(board);
    }
}