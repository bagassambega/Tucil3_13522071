package UCS;

import java.util.*;
import Utils.*;

public class UCS {
    public static List<String> getNeighbors(String word) {
        List<String> neighbors = new ArrayList<String>();
        char[] wordChars = word.toCharArray();

        for (int i = 0; i < wordChars.length; i++) {
            char original = wordChars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == original) {
                    continue;
                }
                wordChars[i] = c;
                if (Word.isWordExist(new String(wordChars))) {
//                    System.out.println("Checking: " + new String(wordChars));
                    neighbors.add(new String(wordChars));
                }
            }
            wordChars[i] = original;
        }
        return neighbors;
    }

    public static List<String> findLadder(String start, String end) {
        PriorityQueue<Node> queue = new PriorityQueue<Node>(Comparator.comparingInt(node -> node.cost));
        Set <String> visited = new HashSet<String>();
        queue.add(new Node(start, null, 0));

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            String currentWord = currentNode.word;

            if (visited.contains(currentWord)) {
                continue;
            }

            if (currentWord.equalsIgnoreCase(end)) {
                List<String> path = new ArrayList<>();
                Node node = currentNode;
                while (node != null) {
                    path.add(node.word);
                    node = node.parent;
                }
                Collections.reverse(path);
                return path;
            }

            visited.add(currentWord);

            List <String> neighbors = getNeighbors(currentWord);
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(new Node(neighbor, currentNode, currentNode.cost + 1));
                }
            }
        }

        return null;
    }
}
