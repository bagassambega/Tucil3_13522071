package A_Star;

import GBFS.*;

public class StarNode implements Comparable<StarNode>{
    public String word;
    int fn; // Cost to reach this node
    int gn; // Heuristic value
    int hn; // Total cost, fn + gn

    public StarNode(String word, int fn, int gn) {
        this.word = word;
        this.fn = fn;
        this.gn = gn;
        this.hn = fn + gn;
    }

    public static int heuristic(String first, String end) {
        return HeuristicNode.calculateHeuristic(first, end);
    }


    @Override
    public int compareTo(StarNode other) {
        return Integer.compare(this.hn, other.hn);
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
