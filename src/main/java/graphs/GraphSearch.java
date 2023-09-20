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

        int[][] c = {{1, 0, 0, 1},{0, 1, 1, 0},{0, 1, 1, 1},{1, 0, 1, 1}};
        System.out.println(graphSearch.findCircleNum(c));
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


}
