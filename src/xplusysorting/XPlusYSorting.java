package xplusysorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XPlusYSorting {

    /*
    +----+----+----+
    |
    |
    +----+----+----+
    |
    |
    +----+----+----+
    |
    |
    +----+----+----+
     */
    private Vertex root;

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

    private int[] getCosts(List<Integer> list) {
        int[] costs = new int[list.size() - 1];
        for (int i = 0; i < list.size() - 1; i++) {
            costs[i] = list.get(i + 1) - list.get(i);
        }
        return costs;
    }

//    public List<Pair> sort() {
//        if (root == null) {
//            return new ArrayList<>();
//        }
//    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Vertex vCur = root;
        while (vCur != null) {
            sb.append(vCur.toString());
            Vertex hCur = vCur.getRight();
            while (hCur != null) {
                sb.append(hCur.toString());
                hCur = hCur.getRight();
            }
            sb.append(System.lineSeparator());
            vCur = vCur.getDown();
        }
        return sb.toString().trim();
    }

}
