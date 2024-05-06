package A_Star;

import Dictionary.Word;

import java.util.*;

public class A_Star {
    public static int checkedNode = 0;
    public static long memoryUsage;

    public static List<String> findLadder(String start, String end) {
        long firstMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        PriorityQueue<StarNode> queue = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();
        queue.add(new StarNode(start, 0, StarNode.heuristic(start, end), null));

        while (!queue.isEmpty()) {
            // Sortir queue berdasarkna fn = gn + hn
            sortQueue(queue);

            StarNode current = queue.poll();
            assert current != null;
            String currentWord = current.word;

            if (visited.contains(currentWord)) {
                continue;
            }
            checkedNode++;

            if (currentWord.equalsIgnoreCase(end)) {
                StarNode temp = current;
                List<String> path = new ArrayList<>();
                while (temp != null) {
                    path.add(temp.word);
                    temp = temp.parent;
                }
                Collections.reverse(path);
                long lastMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                memoryUsage = lastMemory - firstMemory;
                return path;
            }

            visited.add(currentWord);

            for (String next : Word.getNeighbors(currentWord)) {
                if (!visited.contains(next)) {
                    queue.add(new StarNode(next, current.gn + 1, StarNode.heuristic(next, end), current));
                }
            }
        }
        long lastMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        memoryUsage = lastMemory - firstMemory;
        return null;
    }

    public static void sortQueue(PriorityQueue<StarNode> queue) {
        List<StarNode> list = new ArrayList<>(queue);
        list.sort(Comparator.comparingInt(node -> node.fn));
        new PriorityQueue<>(list);
    }
}
