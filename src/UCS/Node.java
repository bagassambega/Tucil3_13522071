package UCS;

public class Node {
    public String word;
    Node parent;
    int cost;

    Node(String word, Node parent, int cost) {
        this.word = word;
        this.parent = parent;
        this.cost = cost;
    }
}
