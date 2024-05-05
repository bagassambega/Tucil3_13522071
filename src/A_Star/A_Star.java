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

        PriorityQueue<StarNode> openSet = new PriorityQueue<>();
        Set<String> closedSet = new HashSet<>();
        Map<String, String> parent = new HashMap<>();

        openSet.add(new StarNode(start, 0, StarNode.heuristic(start, end)));

        while (!openSet.isEmpty()) {
            StarNode current = openSet.poll();

            if (current.word.equalsIgnoreCase(end)) {
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

            closedSet.add(current.word);

            for (String neighbor : Word.getNeighbors(current.word)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }
                checkedNode++;

                int newGCost = current.gCost + 1;

                if (!openSet.contains(new StarNode(neighbor, 0, 0)) ||
                        newGCost < StarNode.getGCost(openSet, neighbor)) {
                    parent.put(neighbor, current.word);

                    openSet.add(new StarNode(neighbor, newGCost, newGCost + StarNode.heuristic(neighbor, end)));
                }
            }
        }
        long lastMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        memoryUsage = lastMemory - firstMemory;
        return null;
    }
}
