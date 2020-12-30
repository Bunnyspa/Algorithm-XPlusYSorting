package xplusysorting;

import java.util.List;
import java.util.Random;

public class App {

    public static void main(String[] args) throws Exception {
        test();
    }

    private static void example() throws Exception {
        int[] x = {1, 2, 4};
        int[] y = {1, 3, 4};
        XPlusYSorting algorithm = new XPlusYSorting(x, y);
        List<Pair> out = algorithm.sort();
        System.out.println(out);
    }

    private static void test() throws Exception {
        int[] sizes = new int[]{100, 200, 400};
        System.out.println("---------- Random ----------");
        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            System.out.println("Size: " + size);
            int[] x = random(size, size);
            int[] y = random(size, size);
            XPlusYSorting algorithm = new XPlusYSorting(x, y);
            List<Pair> out = algorithm.sort();
            System.out.println("Verification: " + verify(out, size));
            System.out.println("----------");
        }
        System.out.println("---------- Step ----------");
        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            System.out.println("Size: " + size);
            int[] x = step(size, 7, 0);
            int[] y = step(size, 11, 0);
            XPlusYSorting algorithm = new XPlusYSorting(x, y);
            List<Pair> out = algorithm.sort();
            System.out.println("Verification: " + verify(out, size));
            System.out.println("----------");
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

    private static boolean verify(List<Pair> data, int size) {
        if (data.size() != size * size) {
            return false;
        }
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
