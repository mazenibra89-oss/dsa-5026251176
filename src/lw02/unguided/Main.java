package lw02.unguided;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("orders.txt"));

        LinkedList<String[]> ordersList = new LinkedList<>();
        LinkedList<String[]> foodList = new LinkedList<>();
        LinkedList<String[]> drinkList = new LinkedList<>();
        LinkedList<String[]> successOrders = new LinkedList<>();
        Queue<String[]> orderQueue = new LinkedList<>();
        Stack<String[]> failedOrders = new Stack<>();

        while (sc.hasNextLine()) {
            String baris = sc.nextLine();
            String[] parts = baris.split(" ");
            ordersList.add(parts);
        }

        sc.close();

        foodList.add(new String[]{"Bakso", "2"});
        foodList.add(new String[]{"Sate", "1"});
        foodList.add(new String[]{"Soto", "2"});

        drinkList.add(new String[]{"EsTeh", "4"});
        drinkList.add(new String[]{"EsJeruk", "2"});

        orderQueue.addAll(ordersList);

        while (!orderQueue.isEmpty()) {
            String[] order = orderQueue.poll();
            String name = order[0];
            String food = order[1];
            String drink = order[2];
            String table = order[3];

            int indexFood = -1;
            if (!food.equals("-")) {
                for (int i = 0; i < foodList.size(); i++) {
                    if (foodList.get(i)[0].equals(food)) {
                        indexFood = i;
                    }
                }
            }

            int indexDrink = -1;
            if (!drink.equals("-")) {
                for (int i = 0; i < drinkList.size(); i++) {
                    if (drinkList.get(i)[0].equals(drink)) {
                        indexDrink = i;
                    }
                }
            }

            boolean foodTersedia = true;
            if (!food.equals("-")) {
                int foodStock = Integer.parseInt(foodList.get(indexFood)[1]);
                if (foodStock <= 0) {
                    foodTersedia = false;
                }
            }

            boolean drinkTersedia = true;
            if (!drink.equals("-")) {
                int drinkStock = Integer.parseInt(drinkList.get(indexDrink)[1]);
                if (drinkStock <= 0) {
                    drinkTersedia = false;
                }
            }

            if (foodTersedia && drinkTersedia) {
                if (!food.equals("-")) {
                    int foodStock = Integer.parseInt(foodList.get(indexFood)[1]);
                    foodList.get(indexFood)[1] = String.valueOf(foodStock - 1);
                }
                if (!drink.equals("-")) {
                    int drinkStock = Integer.parseInt(drinkList.get(indexDrink)[1]);
                    drinkList.get(indexDrink)[1] = String.valueOf(drinkStock - 1);
                }
                successOrders.add(order);
            } else {
                failedOrders.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] order : successOrders) {
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }

        System.out.println("=== Remaining Food Stock ===");
        for (String[] food : foodList) {
            System.out.println(food[0] + ": " + food[1]);
        }
        System.out.println("=== Remaining Drink Stock ===");
        for (String[] drink : drinkList) {
            System.out.println(drink[0] + ": " + drink[1]);
        }

        System.out.println("=== Failed Orders ===");
        while (!failedOrders.isEmpty()) {
            String[] order = failedOrders.pop();
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
    }
}
