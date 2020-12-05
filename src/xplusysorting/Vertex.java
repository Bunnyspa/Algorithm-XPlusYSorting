package xplusysorting;

class Vertex {

    Edge right, down;
    final Pair pair;

    Vertex(int x, int y) {
        pair = new Pair(x, y);
    }

    void linkRight(Edge e) {
        right = e;
    }

    void linkDown(Edge e) {
        down = e;
    }

    Vertex getRight() {
        if (right == null) {
            return null;
        }
        return right.next;
    }

    Vertex getDown() {
        if (down == null) {
            return null;
        }
        return down.next;
    }

    @Override
    public String toString() {
        return pair.toString();
    }

}
