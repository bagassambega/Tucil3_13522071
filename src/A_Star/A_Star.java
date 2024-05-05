package A_Star;

import Dictionary.Word;

import java.util.*;

public class A_Star {
    public static int checkedNode = 0;
    public static long memoryUsage;

    public static List<String> findLadder(String start, String end) {
        long firstMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        if (!Word.dictionary.contains(end)) {
            return null;
        }

        PriorityQueue<StarNode> queue = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();
        queue.add(new StarNode(start, 0, StarNode.heuristic(start, end)));

        while (!queue.isEmpty()) {
            // Sortir queue berdasarkna hn = fn + gn
            sortQueue(queue);

            StarNode current = queue.poll();
            assert current != null;
            String currentWord = current.word;

            if (visited.contains(currentWord)) {
                continue;
            }
            checkedNode++;

            if (currentWord.equalsIgnoreCase(end)) {
                String temp = current.word;
                List<String> path = new ArrayList<>();
                while (parent.containsKey(temp)) {
                    path.add(temp);
                    temp = parent.get(temp);
                }
                path.add(start);
                Collections.reverse(path);
                long lastMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                memoryUsage = lastMemory - firstMemory;
                return path;
            }

            visited.add(currentWord);

            for (String next : Word.getNeighbors(currentWord)) {
                if (!visited.contains(next)) {
                    queue.add(new StarNode(next, current.fn + 1, StarNode.heuristic(next, end)));
                    parent.put(next, currentWord);
                }
            }
        }
        long lastMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        memoryUsage = lastMemory - firstMemory;
        return null;
    }

    public static void sortQueue(PriorityQueue<StarNode> queue) {
        List<StarNode> list = new ArrayList<>(queue);
        list.sort(Comparator.comparingInt(node -> node.hn));
        new PriorityQueue<>(list);
    }
}
