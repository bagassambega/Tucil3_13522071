package A_Star;

import GBFS.*;

public class StarNode implements Comparable<StarNode>{
    public String word;
    int gn; // Cost to reach this node
    int hn; // Heuristic value
    int fn; // Total evaluation value, fn = gn + hn
    StarNode parent;

    public StarNode(String word, int gn, int hn, StarNode parent) {
        this.word = word;
        this.gn = gn;
        this.hn = hn;
        this.fn = gn + hn;
        this.parent = parent;
    }

    public static int heuristic(String first, String end) {
        return HeuristicNode.calculateHeuristic(first, end);
    }


    @Override
    public int compareTo(StarNode other) {
        return Integer.compare(this.fn, other.fn);
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
