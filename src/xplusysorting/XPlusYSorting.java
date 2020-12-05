package xplusysorting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class XPlusYSorting {

    private Vertex root = null;

    public XPlusYSorting(int[] x, int[] y) throws Exception {
        if (x.length != y.length) {
            throw new Exception("Length does not match");
        }

        int size = x.length;

        if (size == 0) {
            return;
        }

        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();
        for (int i : x) {
            xList.add(i);
        }
        for (int i : y) {
            yList.add(i);
        }
        Collections.sort(xList);
        Collections.sort(yList);

        int[] horizontalCosts = getCosts(xList);
        int[] verticalCosts = getCosts(yList);

        // Vertical link
        Vertex vCur = new Vertex(xList.get(0), yList.get(0));
        root = vCur;
        for (int yi = 1; yi < size; yi++) {
            int vCost = verticalCosts[yi - 1];
            Edge edge = new Edge(vCost);
            vCur.linkDown(edge);
            Vertex vertex = new Vertex(xList.get(0), yList.get(yi));
            edge.link(vertex);
            vCur = vertex;
        }

        // Horizontal link
        vCur = root;
        for (int yi = 0; yi < size; yi++) {
            Vertex hCur = vCur;
            for (int xi = 1; xi < size; xi++) {
                int hCost = horizontalCosts[xi - 1];
                Edge edge = new Edge(hCost);
                hCur.linkRight(edge);
                Vertex vertex = new Vertex(xList.get(xi), yList.get(yi));
                edge.link(vertex);
                hCur = vertex;
            }
            vCur = vCur.getDown();
        }
    }

    private static int[] getCosts(List<Integer> list) {
        int[] costs = new int[list.size() - 1];
        for (int i = 0; i < list.size() - 1; i++) {
            costs[i] = list.get(i + 1) - list.get(i);
        }
        return costs;
    }

    public List<Pair> sort() {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Pair> out = new ArrayList<>();
        Queue<Edge> edges = new LinkedList<>();
        out.add(root.pair);
        addEdges(edges, root);
        int iteration = 1;
        while (!edges.isEmpty()) {
            System.out.println("Iteration " + iteration);
            int size = edges.size();
            System.out.println("Next Edges: " + size);
            int min = min(edges);
            System.out.println("Minimum: " + min);
            int count = 0;
            for (int i = 0; i < size; i++) {
                Edge edge = edges.poll();
                edge.cost -= min;
                if (edge.cost == 0) {
                    Vertex v = edge.next;
                    out.add(v.pair);
                    addEdges(edges, v);
                    count++;
                } else {
                    edges.add(edge);
                }
            }
            System.out.println("+" + count + " Edges -> " + out.size() + " Total");
            iteration++;
            System.out.println("--------------------------------------------------");
        }
        return out;
    }

    private static void addEdges(Collection<Edge> edges, Vertex v) {
        if (v.right != null) {
            edges.add(v.right);
        }
        if (v.down != null) {
            edges.add(v.down);
        }
    }

    private static int min(Collection<Edge> edges) {
        int min = Integer.MAX_VALUE;
        for (Edge edge : edges) {
            if (edge.cost < min) {
                min = edge.cost;
            }
        }
        return min;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Vertex vv = root;
        while (vv != null) {
            Vertex hv = vv;
            while (hv != null) {
                sb.append(hv.toString());
                Edge he = hv.right;
                if (he != null) {
                    sb.append(" ").append(he.cost).append(" ");
                }
                hv = hv.getRight();
            }
            sb.append(System.lineSeparator());
            Edge ve = vv.down;
            if (ve != null) {
                sb.append(ve.cost);
                sb.append(System.lineSeparator());
            }
            vv = vv.getDown();
        }
        return sb.toString().trim();
    }

}
