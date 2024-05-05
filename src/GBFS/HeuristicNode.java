package GBFS;

public class HeuristicNode implements Comparable<HeuristicNode> {
    public String word;
    int heuristic;
    HeuristicNode parent;

    public HeuristicNode(String word, HeuristicNode parent, int heuristic) {
        this.word = word;
        this.parent = parent;
        this.heuristic = heuristic;
    }

    // Nilai heuristik didasarkan ke perbedaan character antara dua kata. Makin kecil heuristik makin dekat ke tujuan. Menggunakan
    // algoritma Hamming Distance
    public static int calculateHeuristic(String first, String end) {
        int distance = 0;
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != end.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    public int compareTo(HeuristicNode other) {
        return Integer.compare(this.heuristic, other.heuristic);
    }
}
