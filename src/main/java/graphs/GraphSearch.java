package graphs;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    /**
     * LC 1926. Nearest Exit from Entrance in Maze
     * @param maze
     * @param entrance
     * @return
     */
    public int nearestExit(char[][] maze, int[] entrance) {

        LinkedList<Node> queue = new LinkedList<>();
        Node e = new Node(entrance[0], entrance[1]);
        queue.addFirst(e);
        Node pointer = e;
        int level = 0;
        while(!queue.isEmpty()) {
            Node n = queue.removeFirst();
            if (maze[n.y][n.x] == '+') {
                if(n.equals(pointer)) {
                    pointer = queue.peekLast();
                }
                continue;
            }
            maze[n.y][n.x] = '+';

            if (n != e && (n.y == 0 || n.y == maze.length-1 || n.x == 0 || n.x == maze[0].length-1)) {
                return level;
            }

            if ((n.y - 1 >= 0 && n.y - 1 < maze.length) && maze[n.y - 1][n.x] == '.') {
                queue.addLast(new Node(n.y - 1, n.x));
            }
            if ((n.y + 1 >= 0 && n.y + 1 < maze.length) && maze[n.y + 1][n.x] == '.') {
                queue.addLast(new Node(n.y + 1, n.x));
            }
            if ((n.x - 1 >= 0 && n.x - 1 < maze[0].length) && maze[n.y][n.x - 1] == '.') {
                queue.addLast(new Node(n.y, n.x - 1));
            }
            if ((n.x + 1 >= 0 && n.x + 1 < maze[0].length) && maze[n.y][n.x + 1] == '.') {
                queue.addLast(new Node(n.y, n.x + 1));
            }

            if(n.equals(pointer)) {
                pointer = queue.peekLast();
                level ++;
            }
        }

        return -1;
    }

    public int nearestExit1(char[][] maze, int[] entrance) {

        LinkedList<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        var e = new Node(entrance[0], entrance[1]);
        queue.addFirst(e);
        Node pointer = e;
        int level = 0;
        while(!queue.isEmpty()) {
            var n = queue.removeFirst();
            if (visited.contains(n)) {
                if(n.equals(pointer)) {
                    pointer = queue.peekLast();
                }
                continue;
            }
            visited.add(n);

            if (n != e && (n.y == 0 ||n.y == maze.length-1 || n.x == 0 || n.x == maze[0].length-1)) {
                return level;
            }

            // expand the node's neighbours
            Node up = new Node(n.y - 1, n.x);
            Node down = new Node(n.y + 1, n.x);
            Node left = new Node(n.y, n.x - 1);
            Node right = new Node(n.y, n.x + 1);

            if ((up.y >= 0 && up.y < maze.length) && maze[up.y][up.x] == '.' && !visited.contains(up)) {
                queue.addLast(up);
            }
            if ((down.y >= 0 && down.y < maze.length) && maze[down.y][down.x] == '.' && !visited.contains(down)) {
                queue.addLast(down);
            }
            if ((left.x >= 0 && left.x < maze[0].length) && maze[left.y][left.x] == '.' && !visited.contains(left)) {
                queue.addLast(left);
            }
            if ((right.x >= 0 && right.x < maze[0].length) && maze[right.y][right.x] == '.' && !visited.contains(right)) {
                queue.addLast(right);
            }

            if(n.equals(pointer)) {
                pointer = queue.peekLast();
                level ++;
            }
        }

        return -1;
    }

    class Node {
        int x, y;
        Node (int y, int x) {
            this.x = x;
            this.y = y;
        }

        public int hashCode() {
            return x ^ y;
        }

        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                Node other = (Node) obj;
                return other.x == this.x && other.y == this.y;
            }
            return false;
        }
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

    /**
     * LC 200. Number of islands
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int nIslands = 0;
        for (int y = 0; y < grid.length; ++y) {
            for (int x = 0; x < grid[y].length; ++x) {
                if (grid[y][x] == '1') {
                    dfsIsland(grid, x, y);
                    nIslands ++;
                }
            }
        }
        return nIslands;
    }

    private void dfsIsland(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid[0].length || y < 0 || y >= grid.length
                || grid[y][x] != '1') return;
        if (grid[y][x] == '1') grid[y][x] = '0';
        dfsIsland(grid, x-1, y);
        dfsIsland(grid, x+1, y);
        dfsIsland(grid, x, y-1);
        dfsIsland(grid, x, y+1);
    }

    /**
     * LC 419. Battleships in a board
     * @param board
     * @return
     */
    public int countBattleshipsEasier(char[][] board) {
        int ships = 0;
        for (int y = 0; y < board.length; ++y) {
            for (int x = 0; x < board[0].length; ++x) {
                if (board[y][x] == 'X') {
                    dfsShips(board, x, y);
                    ++ ships;
                }
            }
        }
        return ships;
    }

    private void dfsShips(char[][] board, int x, int y) {
        // If out of bounds or water return
        if (x < 0 || x >= board[0].length || y < 0 || y >= board.length || board[y][x] != 'X') return;
        if (board[y][x] == 'X') board[y][x] = '.';
        dfsShips(board, x-1, y);
        dfsShips(board, x+1, y);
        dfsShips(board, x, y-1);
        dfsShips(board, x, y+1);
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

    /**
     * LC 1466. Reorder Routes to Make All Paths Lead to the City Zero
     * @param n
     * @param connections
     * @return
     */
    public int minReorder(int n, int[][] connections) {
        // create edges
        Map<Integer, List<int[]>> adj = new HashMap<>();
        // Fill adjacency matrix
        for (int[] connection : connections) {
            List<int[]> a = adj.getOrDefault(connection[0], new ArrayList<int[]>());
            a.add(new int[] {connection[1], 1});
            adj.put(connection[0], a);

            List<int[]> b = adj.getOrDefault(connection[1], new ArrayList<int[]>());
            b.add(new int[] {connection[0], 0});
            adj.put(connection[1], b);
        }

        return dfs(0, -1, adj, 0);
    }

    private int dfs(int n, int parent, Map<Integer, List<int[]>> adj, int changes) {
        for (int[] neighbour : adj.getOrDefault(n, new ArrayList<int[]>())) {
            if (neighbour[0] != parent) {
                changes += neighbour[1];
                changes = dfs(neighbour[0], n, adj, changes);
            }
        }
        return changes;
    }

//    public int minReorder(int n, int[][] connections) {
//        // create edges
//        Set<Tuple> edges = Arrays.stream(connections)
//                .map(c -> new Tuple(c[0], c[1]))
//                .collect(Collectors.toSet());
//        List<List<Integer>> neighbours = IntStream.range(0, n+1)
//                .mapToObj(i -> edges.stream()
//                        .filter(e -> e.i == i || e.j == i)
//                        .map(e -> e.i == i ? e.j : e.i)
//                        .collect(Collectors.toList()))
//                .collect(Collectors.toList());
//        Set<Integer> visited = new HashSet<>();
//        int changes = 0;
//
//        dfs(0, edges, neighbours, visited, changes);
//        return changes;
//    }
//
//    private void dfs(int n, Set<Tuple> edges, List<List<Integer>> neighbours, Set<Integer> visited, int changes) {
//        // get neighbours
//        visited.add(n);
//        for (int neighbour: neighbours.get(n)) {
//            if (visited.contains(neighbour)) continue;
//            if (!edges.contains(new Tuple(neighbour, n))) {
//                ++ changes;
//            }
//            dfs(neighbour, edges, neighbours, visited, changes);
//        }
//    }

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

}
