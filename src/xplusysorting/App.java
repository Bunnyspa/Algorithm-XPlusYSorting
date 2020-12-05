package xplusysorting;

import java.util.List;
import java.util.Random;

public class App {

    public static void main(String[] args) throws Exception {
        testTime();
    }

    private static void test() throws Exception {
        int[] x = {2, 3, 3, 5, 6, 7, 7, 7};
        int[] y = {0, 0, 1, 2, 2, 3, 5, 7};
        XPlusYSorting algorithm = new XPlusYSorting(x, y);
        System.out.println(algorithm);
        List<Pair> out = algorithm.sort();
        System.out.println(out);
        System.out.println(verify(out));
    }

    private static void testTime() throws Exception {
        int size = 1024;
        System.out.println("Size: " + size);
        long start = System.nanoTime();
        int[] x = random(size, size * size);
        int[] y = random(size, size * size);
        XPlusYSorting algorithm = new XPlusYSorting(x, y);
        List<Pair> out = algorithm.sort();
        System.out.println(verify(out));
        long elapsed = System.nanoTime() - start;
        System.out.println(size + ": " + (double) elapsed / 1000000000);
    }

    private static int[] step(int size, int step, int offset) {
        int[] out = new int[size];
        for (int i = 0; i < size; i++) {
            out[i] = i * step + offset;
        }
        return out;
    }

    private static int[] random(int size, int bound) {
        int[] out = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            out[i] = random.nextInt(bound);
        }
        return out;
    }

    private static boolean verify(List<Pair> data) {
        for (int i = 0; i < data.size() - 1; i++) {
            Pair a = data.get(i);
            Pair b = data.get(i + 1);
            if (a.first + a.second > b.first + b.second) {
                return false;
            }
        }
        return true;
    }
}
