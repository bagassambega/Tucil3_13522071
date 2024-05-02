package GBFS;

import Dictionary.*;
import java.util.*;

public class GBFS {
    // Akses ke dictionary di Dictionary/Word.dictionary
    public static List<String> findLadder(String start, String end) {
        PriorityQueue<HeuristicNode> queue = new PriorityQueue<HeuristicNode>();
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
                return path;
            }

            List <String> heuristicVisit = new ArrayList<>();
            List <Integer> heuristicValue = new ArrayList<>();
            for (String neighbor : getNeighbors(current.word)) {
                int neighborHeuristic = HeuristicNode.calculateHeuristic(neighbor, end);
                heuristicValue.add(neighborHeuristic);
                heuristicVisit.add(neighbor);
            }
            // Sortir berdasarkan nilai heuristik paling rendah
            for (int i = 0; i < heuristicValue.size(); i++) {
                for (int j = i + 1; j < heuristicValue.size(); j++) {
                    if (heuristicValue.get(i) > heuristicValue.get(j)) {
                        int temp = heuristicValue.get(i);
                        heuristicValue.set(i, heuristicValue.get(j));
                        heuristicValue.set(j, temp);

                        String tempString = heuristicVisit.get(i);
                        heuristicVisit.set(i, heuristicVisit.get(j));
                        heuristicVisit.set(j, tempString);
                    }
                }
            }

            for (String neighbor : heuristicVisit) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(new HeuristicNode(neighbor, current, HeuristicNode.calculateHeuristic(neighbor, end)));
                }
            }
        }
        return null;
    }

    public static List<String> getNeighbors(String word) {
        List<String> neighbors = new ArrayList<>();
        char[] wordArray = word.toCharArray();

        for (int i = 0; i < wordArray.length; i++) {
            char originalChar = wordArray[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != originalChar) {
                    wordArray[i] = c;
                    String newWord = new String(wordArray);
                    if (Word.dictionary.contains(newWord)) {
                        neighbors.add(newWord);
                    }
                }
            }
            wordArray[i] = originalChar;
        }

        return neighbors;
    }
}
