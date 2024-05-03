package A_Star;

import Dictionary.Word;

import java.util.*;

public class A_Star {
    public static List<String> findLadder(String start, String end) {
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
                return path;
            }

            closedSet.add(current.word);

            for (String neighbor : getNeighbors(current.word)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int newGCost = current.gCost + 1;

                if (!openSet.contains(new StarNode(neighbor, 0, 0)) ||
                        newGCost < StarNode.getGCost(openSet, neighbor)) {
                    parent.put(neighbor, current.word);

                    openSet.add(new StarNode(neighbor, newGCost, newGCost + StarNode.heuristic(neighbor, end)));
                }
            }
        }
        return null;
    }

    public static List<String> getNeighbors(String word) {
        List <String> neighbors = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            char[] wordChars = word.toCharArray();
            char original = wordChars[i];

            for (char c = 'a'; c <= 'z'; c++) {
                if (c == original) {
                    continue;
                }
                wordChars[i] = c;
                if (Word.isWordExist(new String(wordChars))) {
                    neighbors.add(new String(wordChars));
                }
            }
            wordChars[i] = original;
        }
        return neighbors;
    }
}
