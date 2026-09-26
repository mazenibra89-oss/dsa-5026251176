package lw02.prelab;

import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
   public static void main(String[] args) {

        Scanner sc = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        LinkedList<String[]> transactionsList = new LinkedList<>();
        LinkedList<String[]> customerList = new LinkedList<>();

        while (sc.hasNext()) {
            String name = sc.next();
            String type = sc.next();
            String amount = sc.next();
            
            transactionsList.add(new String[]{name, type, amount});

            boolean customerExists = false;
            for (String[] customer : customerList) {
                if (customer[0].equals(name)) {
                    customerExists = true;
                    break;
                }
            }
            if (!customerExists) {
                customerList.add(new String[]{name, "0"});
            }
        }

        sc.close();

        Queue<String[]> transactionQueue = new LinkedList<>();
        while (!transactionsList.isEmpty()) {
            transactionQueue.add(transactionsList.removeFirst());
        }

        Stack<String[]> failedTransactions = new Stack<>();

        while (!transactionQueue.isEmpty()) {
            String[] transaction = transactionQueue.poll();
            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            String[] targetCustomer = null;
            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    targetCustomer = cust;
                    break;
                }
            }

            if (targetCustomer != null) {
                int currentBalance = Integer.parseInt(targetCustomer[1]);

                if (type.equalsIgnoreCase("DEPOSIT")) {
                    currentBalance += amount;
                    targetCustomer[1] = String.valueOf(currentBalance);
                } else if (type.equalsIgnoreCase("WITHDRAW")) {
                    if (amount > currentBalance) {
                        failedTransactions.push(transaction);
                    } else {
                        currentBalance -= amount;
                        targetCustomer[1] = String.valueOf(currentBalance);
                    }
                }
            }


        }

        System.out.println("=== Final Balances ===");
        for (String[] cust : customerList) {
            System.out.println(cust[0] + ": " + cust[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] failed = failedTransactions.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }


   } 
}
