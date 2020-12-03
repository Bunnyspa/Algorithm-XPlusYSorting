/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xplusysorting;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Bunnyspa
 */
public class App {

    public static void main(String[] args) throws Exception {
        int[] x = {2, 3, 3, 5, 6, 7, 7, 7};
        int[] y = {0, 0, 1, 2, 2, 3, 5, 7};
        XPlusYSorting algorithm = new XPlusYSorting(x, y);
        System.out.println(algorithm);
        // List<Pair> out = algorithm.sort();
//        System.out.println(out);
//        System.out.println(verify(out)); 
    }

    private static Integer[] random(int size, int bound) {
        Integer[] out = new Integer[size];
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
