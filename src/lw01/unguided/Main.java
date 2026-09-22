package lw01.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("washes.txt"));
        
            int totalRecords = scanner.nextInt();
            
            WashService[] services = new WashService[totalRecords];
            int[] unitsArray = new int [totalRecords];
            
            for (int i = 0; i < totalRecords; i++) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();
                int units = scanner.nextInt(); 

                if (type.equals("MOTORCYCLE")) {
                    services[i] = new MotorcycleWash(id, days);
                } else if (type.equals("CAR")) {
                    services[i] = new CarWash(id, days);
                }
                unitsArray[i] = units;

                System.out.println(services[i].summary(unitsArray[i]));
            }
        
        
        scanner.close();
    }
}
