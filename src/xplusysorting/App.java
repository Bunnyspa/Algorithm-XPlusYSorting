package xplusysorting;

import java.util.List;
import java.util.Random;

public class App {

    public static void main(String[] args) throws Exception {
        test();
    }

    private static void test() throws Exception {
        for (int size : new int[]{100, 200}) {
            System.out.println("Size: " + size);
            long start = System.nanoTime();
            int[] x = step(size, 7,0);
            int[] y = step(size, 11,0);
            XPlusYSorting algorithm = new XPlusYSorting(x, y);
            List<Pair> out = algorithm.sort();
            System.out.println(verify(out));
            long elapsed = System.nanoTime() - start;
            System.out.println((double) elapsed / 1000000000);
            System.out.println("--------------------------------------------------");
        }
    }

    private static int[] repeat(int size, int number) {
        int[] out = new int[size];
        for (int i = 0; i < size; i++) {
            out[i] = number;
        }
        return out;
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
