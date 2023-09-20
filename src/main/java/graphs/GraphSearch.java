package graphs;

import java.util.*;
import java.util.stream.Collectors;

public class GraphSearch {

    public static void main(String[] args) {
        List<List<Integer>> rooms = List.of(List.of(1,1,0),
                List.of(1,1,0),List.of(0,0,1));
        GraphSearch graphSearch = new GraphSearch();
//        System.out.println(graphSearch.canVisitAllRooms(rooms));
//        int[][] c = {{1, 0, 0, 1},{0, 1, 1, 0},{0, 1, 1, 1},{1, 0, 1, 1}};
//        System.out.println(graphSearch.findCircleNum(c));

//        char[][] board = {{'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', '.'}};
        char[][] board = {{'X', '.', 'X'}, {'X', '.', 'X'}};
        System.out.println(graphSearch.countBattleships(board));
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms.size() < 2) return true;

        Set<Integer> visitedNodes = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);

        while (queue.size() > 0) {
            Integer i = queue.removeFirst();
            visitedNodes.add(i);

            // cant be visited
            // cant be already in queue
            // cant be the `i`
            List<Integer> keys = rooms.get(i).stream()
                    .filter(key -> !visitedNodes.contains(key)
                            && !Objects.equals(key, i)
                            && !queue.contains(key))
                    .collect(Collectors.toList());
            queue.addAll(queue.size(), keys);
        }

        return visitedNodes.size() == rooms.size();
    }

    public int findCircleNum(int[][] isConnected) {
        int provinces = 0;
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < isConnected.length; ++i) {
            if (!visited.contains(i)) {
                bfs(isConnected, i, visited);
                ++ provinces;
            }
        }

        return provinces;
    }

    private void bfs(int[][] c, int index, Set<Integer> visited) {
        int[] cc = c[index];
        for (int i = 0; i < cc.length; ++i) {
            if (cc[i] == 1 && !visited.contains(i)) {
                visited.add(i);
                bfs(c, i, visited);
            }
        }
    }

    class Tuple {
        int i;
        int j;
        public Tuple(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public int hashCode() { return i ^ j; }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Tuple) {
                Tuple t = (Tuple) obj;
                return t.i == i && t.j == j;
            }
            return false;
        }

        @Override
        public String toString() {
            return "[" + i + ", " + j +"]";
        }
    }

    public int countBattleships(char[][] board) {
        int ships = 0;
        Set<Tuple> visitedNodes = new HashSet<>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == 'X' && !visitedNodes.contains(new Tuple(i, j))) {
                    if (j+1 < board[i].length && board[i][j+1] == 'X') {
                        // expand right
                        expandNode(board, i, j+1, 0, 1, visitedNodes);
                    } else if (i+1 < board.length && board[i+1][j] == 'X') {
                        // or expand below
                        expandNode(board, i+1, j, 1, 0, visitedNodes);
                    }
                    ++ ships;
                    visitedNodes.add(new Tuple(i, j));
                }
            }
        }

        return ships;
    }

    private void expandNode(char[][] board, int i, int j, int ip, int jp, Set<Tuple> visitedNodes) {
        if (i < board.length && j < board[0].length && board[i][j] == 'X') {
            visitedNodes.add(new Tuple(i, j));
            expandNode(board, i+ip, j+jp, ip, jp, visitedNodes);
        }
    }


}
