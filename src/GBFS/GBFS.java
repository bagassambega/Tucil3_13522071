package GBFS;

import Dictionary.*;
import java.util.*;

public class GBFS {
    public static int checkedNode = 0;
    public static long memoryUsage;

    // Akses ke dictionary di Dictionary/Word.dictionary
    public static List<String> findLadder(String start, String end) {
        long firstMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        PriorityQueue<HeuristicNode> queue = new PriorityQueue<>();
        Set <String> visited = new HashSet<>();

        int heuristic = HeuristicNode.calculateHeuristic(start, end);
        queue.add(new HeuristicNode(start, null, heuristic));
        visited.add(start);

        while (!queue.isEmpty()) {
            HeuristicNode current = queue.poll();

            if (current.word.equalsIgnoreCase(end)) {
                List<String> path = new ArrayList<>();
                HeuristicNode node = current;
                while (node != null) {
                    path.add(node.word);
                    node = node.parent;
                }
                Collections.reverse(path);
                long lastMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                memoryUsage = lastMemory - firstMemory;
                return path;
            }

            int max = Integer.MAX_VALUE;
            String next = "";
            for (String neighbor : Word.getNeighbors(current.word)) {
                if (visited.contains(neighbor)) {
                    continue;
                }
                int Heur = HeuristicNode.calculateHeuristic(neighbor, end);
                if (Heur < max) {
                    max = Heur;
                    next = neighbor;
                }
            }
            if (!next.isEmpty()) {
                checkedNode++;
                visited.add(next);
                queue.add(new HeuristicNode(next, current, HeuristicNode.calculateHeuristic(next, end)));
            }
        }
        long lastMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        memoryUsage = lastMemory - firstMemory;
        return null;
    }

}
