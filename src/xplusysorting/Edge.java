/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xplusysorting;

/**
 *
 * @author Bunnyspa
 */
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
