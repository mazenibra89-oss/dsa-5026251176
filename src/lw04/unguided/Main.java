package lw04.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("parcels.txt"));
 
            int n = sc.nextInt();
            int[] parcels = new int[n];
            for (int i = 0; i < n; i++) {
                parcels[i] = sc.nextInt();
            }
 
            int m = sc.nextInt();
            int[] targets = new int[m];
            for (int i = 0; i < m; i++) {
                targets[i] = sc.nextInt();
            }
            sc.close();
 
            int[] afterTwo = new int[n];
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - 1 - i; j++) {
                    if (parcels[j] < parcels[j + 1]) {
                        int temp = parcels[j];
                        parcels[j] = parcels[j + 1];
                        parcels[j + 1] = temp;
                    }
                }
                if (i == 1) {
                    for (int k = 0; k < n; k++) {
                        afterTwo[k] = parcels[k];
                    }
                }
            }
 
            System.out.println("==== After 2 Passes ====");
            for (int i = 0; i < n; i++) {
                if (i > 0) {
                    System.out.print(" ");
                }
                System.out.print(afterTwo[i]);
            }
            System.out.println();
 
            System.out.println("==== Fully Sorted ====");
            for (int i = 0; i < n; i++) {
                if (i > 0) {
                    System.out.print(" ");
                }
                System.out.print(parcels[i]);
            }
            System.out.println();
 
            System.out.println("==== Search Results ====");
            for (int t = 0; t < m; t++) {
                int low = 0;
                int high = n - 1;
                int steps = 0;
                int found = -1;
 
                while (low <= high) {
                    int mid = (low + high) / 2;
                    steps++;
                    if (parcels[mid] == targets[t]) {
                        found = mid;
                        break;
                    } else if (parcels[mid] > targets[t]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
 
                if (found != -1) {
                    System.out.println(targets[t] + ": FOUND at index " + found + " (steps: " + steps + ")");
                } else {
                    System.out.println(targets[t] + ": NOT FOUND (steps: " + steps + ")");
                }
            }

    }

}