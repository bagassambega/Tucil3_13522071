package UCS;

import java.util.*;
import Dictionary.*;

public class UCS {
    public static int checkedNode = 0;
    public static long memoryUsage;

    public static List<String> findLadder(String start, String end) {
        long firstMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        Set <String> visited = new HashSet<>();
        queue.add(new Node(start, null, 0));

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            String currentWord = currentNode.word;

            if (visited.contains(currentWord)) {
                continue;
            }
            checkedNode++;

            if (currentWord.equalsIgnoreCase(end)) {
                List<String> path = new ArrayList<>();
                Node node = currentNode;
                while (node != null) {
                    path.add(node.word);
                    node = node.parent;
                }
                Collections.reverse(path);
                long lastMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                memoryUsage = lastMemory - firstMemory;
                return path;
            }

            visited.add(currentWord);

            List <String> neighbors = Word.getNeighbors(currentWord);
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(new Node(neighbor, currentNode, currentNode.cost + 1));
                }
            }
        }
        long lastMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        memoryUsage = lastMemory - firstMemory;

        return null;
    }
}
