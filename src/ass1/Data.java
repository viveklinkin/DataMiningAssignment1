/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author vivek
 */
public class Data {

    public static DecimalFormat df;
    int number;
    int label;
    float[] dimension;

    float closestE = 999999, closestC = -999999, closestJ = -9999999;
    int vE = -1, vC = -1, vJ = -1;

    public Data(String s, int number) {
        List<String> d1 = Arrays.asList(s.split(","));
        df = new DecimalFormat("#.#####");
        dimension = new float[d1.size() - 1];
        Iterator iter = d1.iterator();
        label = Integer.parseInt((String) iter.next());
        int counter = 0;
        while (iter.hasNext()) {
            dimension[counter] = Float.parseFloat((String) iter.next());
            counter++;
        }
        this.number = number;
    }

    public static float eDistance(Data a, Data b) {
        double distance = 0;
        for (int i = 0; i < a.length(); i++) {
            distance += (a.get(i) - b.get(i)) * (a.get(i) - b.get(i));
        }
        return (float) Math.sqrt(distance);
    }

    public static float cDistance(Data a, Data b) {
        float nume = 0, deno1 = 0, deno2 = 0;
        for (int i = 0; i < a.length(); i++) {
            nume += a.get(i) * b.get(i);
            deno1 += a.get(i) * a.get(i);
            deno2 += b.get(i) * b.get(i);
        }
        return (float) ( nume / (Math.sqrt(deno1) * Math.sqrt(deno2)));
    }

    public static float jDistance(Data a, Data b) {
        float nume = 0, deno1 = 0, deno2 = 0;
        for (int i = 0; i < a.length(); i++) {
            nume += a.get(i) * b.get(i);
            deno1 += a.get(i) * a.get(i);
            deno2 += b.get(i) * b.get(i);
        }
        return (float)nume / (deno1 + deno2 - nume);
    }

    public int length() {
        return dimension.length;
    }

    public float get(int x) {
        return dimension[x];
    }

    public void reduce() {
        int sq = (int) Math.sqrt(this.length());
        //int[][] reduced = new int[sq][sq];
        float reduced[] = new float[this.length() / 16];
        List<Float> dims = new ArrayList<>();
        for (float x : dimension) {
            dims.add(x);
        }
        List<Float> red_dims = new ArrayList<>();
        int count = 0;
        while (dims.size() != 0) {
            float sum = dims.get(4 * count) + dims.get(4*count + 1) + dims.get(4*count + 2) + dims.get(4*count +3)
                    + dims.get((4*count) + sq) + dims.get(4*count + sq + 1) + dims.get(4*count+sq + 2) + dims.get(4*count+sq + 3)
                    + dims.get((2 * sq) + 4 * count) + dims.get((2 * sq) + 4 * count + 1) + dims.get((2 * sq) + 4 * count + 2) + dims.get(2 * sq + 4 * count + 3)
                    + dims.get((3 * sq) + 4 * count) + dims.get((4* count) + 3* sq + 1) + dims.get((sq * 3) + 4* count + 2) + dims.get(sq * 3 + count* 4 + 3);
            sum /= 16;
            red_dims.add(sum);
            count++;
            if (count == sq / 4) {
                int looper = 4 * sq;
                while (looper-- > 0) {
                    dims.remove(0);
                }
                count = 0;
            }
        }
        dimension = new float[red_dims.size()];
        for(int i = 0; i<red_dims.size(); i++){
            dimension[i] = red_dims.get(i);
        }
    }
    /*
    public void printThisShit(float[] a){
        System.out.println("Start");
        int lim = (int) Math.sqrt(a.length);
        int counter = 0;
        for(float x : a){
            if(counter++ % lim == 0)System.out.print("\n");
            System.out.print(" " + x);
        }
        System.out.println("end");
    }*/
}
