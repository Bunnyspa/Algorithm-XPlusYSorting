/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xplusysorting;

import java.util.Collection;

/**
 *
 * @author Bunnyspa
 */
class Vertex {

    private Edge right, down;
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

    void addEdge(Collection<Edge> edges) {
        if (right != null) {
            edges.add(right);
        }
        if (down != null) {
            edges.add(down);
        }
    }

    @Override
    public String toString() {
        return pair.toString();
    }

}
