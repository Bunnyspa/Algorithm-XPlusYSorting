package xplusysorting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class XPlusYSorting {

    private Vertex root = null;
    private long iteration = 0;
    private long subiteration = 0;

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
        Vertex vVertex = new Vertex(xList.get(0), yList.get(0));
        root = vVertex;
        for (int yi = 1; yi < size; yi++) {
            int vCost = verticalCosts[yi - 1];
            Edge edge = new Edge(vCost);
            vVertex.linkDown(edge);
            Vertex vertex = new Vertex(xList.get(0), yList.get(yi));
            edge.link(vertex);
            vVertex = vertex;
        }

        // Horizontal link
        vVertex = root;
        for (int yi = 0; yi < size; yi++) {
            Vertex hVertex = vVertex;
            for (int xi = 1; xi < size; xi++) {
                int hCost = horizontalCosts[xi - 1];
                Edge edge = new Edge(hCost);
                hVertex.linkRight(edge);
                Vertex vertex = new Vertex(xList.get(xi), yList.get(yi));
                edge.link(vertex);
                hVertex = vertex;
            }
            vVertex = vVertex.getDown();
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
        while (!edges.isEmpty()) {
            iteration++;
            int size = edges.size();
            int min = min(edges);
            for (int i = 0; i < size; i++) {
                subiteration++;
                Edge edge = edges.poll();
                edge.cost -= min;
                if (edge.cost == 0) {
                    addVerticies(out, edges, edge.next);
                } else {
                    edges.add(edge);
                }
            }
        }
        return out;
    }

    private static void addVerticies(List<Pair> array, Queue<Edge> edges, Vertex v) {
        array.add(v.pair);
        if (v.right != null) {
            if (v.right.cost == 0) {
                addVerticies(array, edges, v.right.next);
            } else {
                edges.add(v.right);
            }
        }
        if (v.down != null) {
            if (v.down.cost == 0) {
                addVerticies(array, edges, v.down.next);
            } else {
                edges.add(v.down);
            }
        }
    }

    private static void addEdges(Collection<Edge> edges, Vertex v) {
        if (v.right != null) {
            edges.add(v.right);
        }
        if (v.down != null) {
            edges.add(v.down);
        }
    }

    public long getIteration() {
        return iteration;
    }

    public long getSubiteration() {
        return subiteration;
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
