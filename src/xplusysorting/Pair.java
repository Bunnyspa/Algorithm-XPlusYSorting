package xplusysorting;

public class Pair {

    public final int first;
    public final int second;

    public Pair(int a, int b) {
        this.first = a;
        this.second = b;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
