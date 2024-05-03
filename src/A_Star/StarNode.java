package A_Star;

import java.util.PriorityQueue;

public class StarNode implements Comparable<StarNode>{
    public String word;
    int gCost; // Cost to reach this node
    int hCost; // Heuristic value

    public StarNode(String word, int gCost, int hCost) {
        this.word = word;
        this.gCost = gCost;
        this.hCost = hCost;
    }

    public static int heuristic(String first, String end) {
        int distance = 0;
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != end.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    public static int getGCost(PriorityQueue<StarNode> openSet, String word) {
        for (StarNode node : openSet) {
            if (node.word.equals(word)) {
                return node.gCost;
            }
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public int compareTo(StarNode other) {
        return Integer.compare(this.gCost + this.hCost, other.gCost + other.hCost);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        StarNode other = (StarNode) obj;
        return this.word.equals(other.word);
    }
}
