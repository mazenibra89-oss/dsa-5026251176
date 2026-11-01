package lw04.prelab;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("branches.txt"));
 
            int n = sc.nextInt();
            int[] branches = new int[n];
            for (int i = 0; i < n; i++) {
                branches[i] = sc.nextInt();
            }
 
            int m = sc.nextInt();
            int[] targets = new int[m];
            for (int i = 0; i < m; i++) {
                targets[i] = sc.nextInt();
            }
            sc.close();
 
            int passes = 3;
            for (int i = 1; i <= passes && i < n; i++) {
                int key = branches[i];
                int j = i - 1;
                while (j >= 0 && branches[j] < key) {
                    branches[j + 1] = branches[j];
                    j--;
                }
                branches[j + 1] = key;
            }
 
            for (int i = 0; i < n; i++) {
                if (i > 0) {
                    System.out.print(" ");
                }
                System.out.print(branches[i]);
            }
            System.out.println();
 
            for (int t = 0; t < m; t++) {
                int found = -1;
                for (int i = 0; i < n; i++) {
                    if (branches[i] == targets[t]) {
                        found = i;
                        break;
                    }
                }
                if (found != -1) {
                    System.out.println(targets[t] + ": FOUND at index " + found);
                } else {
                    System.out.println(targets[t] + ": NOT FOUND");
                }
            }
    }
}
