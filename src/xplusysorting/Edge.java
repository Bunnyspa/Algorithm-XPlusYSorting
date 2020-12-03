package xplusysorting;

class Edge {

    int cost;
    Vertex next;

    Edge(int cost) {
        this.cost = cost;
    }

    void link(Vertex v) {
        next = v;
    }

}
