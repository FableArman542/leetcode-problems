package pointers;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Islands {

    public static void main(String[] args) {
        Islands islands = new Islands();
        char[][] grid = {
                {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0', '1', '1'},
                {'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '0'},
                {'1', '0', '1', '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1'},
                {'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '0'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'}
        };

        System.out.println(islands.numIslands(grid));
    }

    public int numIslands(char[][] grid) {
        int nIslands = 0;
        Set<Node> visitedNodes = new HashSet<>();
        for (int y = 0; y < grid.length; ++y) {
            for (int x = 0; x < grid[y].length; ++x) {
                if (grid[y][x] == '1' && !visitedNodes.contains(new Node(x, y))) {
                    bfsIsland(grid, visitedNodes, x, y);
                    nIslands ++;
                }
            }
        }
        return nIslands;
    }

    private void bfsIsland(char[][] grid, Set<Node> visitedNodes, int x, int y) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(new Node(x, y));

        while(queue.size() > 0) {
            // expand
            Node current = queue.removeFirst();
            if (visitedNodes.contains(current)) continue;
            int xx = current.x, yy = current.y;
            visitedNodes.add(current);

            // add to queue
            if ((xx-1 >= 0 && grid[yy][xx-1] == '1') && !visitedNodes.contains(new Node(xx-1, yy))) {
                queue.addLast(new Node(xx-1, yy));
            }
            if ((xx+1 < grid[yy].length && grid[yy][xx+1] == '1') && !visitedNodes.contains(new Node(xx+1, yy))) {
                queue.addLast(new Node(xx+1, yy));
            }
            if ((yy+1 < grid.length && grid[yy+1][xx] == '1') && !visitedNodes.contains(new Node(xx, yy+1))) {
                queue.addLast(new Node(xx, yy+1));
            }
            if ((yy-1 >= 0 && grid[yy-1][xx] == '1') && !visitedNodes.contains(new Node(xx, yy-1))) {
                queue.addLast(new Node(xx, yy-1));
            }
        }
    }

    class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "{ " + x + ", " + y + " }";
        }

        @Override
        public int hashCode() {
            return x ^ y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                Node n = (Node) obj;
                return n.x == x && n.y == y;
            }
            return false;
        }

    }
}
