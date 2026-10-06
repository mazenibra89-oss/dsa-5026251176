package lw03.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<String> terdaftar = new HashSet<>();
        Scanner scanRegistrasi = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        while (scanRegistrasi.hasNextLine()) {
            String id = scanRegistrasi.nextLine();
            terdaftar.add(id);
        }
        scanRegistrasi.close();

        Set<String> hadir = new HashSet<>();
        List<String> riwayatCheckIn = new ArrayList<>();
        int percobaanDitolak = 0;

        Scanner scanCheckIn = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        while (scanCheckIn.hasNextLine()) {
            String id = scanCheckIn.nextLine();

            if (!terdaftar.contains(id)) {
                riwayatCheckIn.add(id + ": Rejected (not registered)");
                percobaanDitolak++;
            } else if (hadir.contains(id)) {
                riwayatCheckIn.add(id + ": Rejected (already checked in)");
                percobaanDitolak++;
            } else {
                hadir.add(id);
                riwayatCheckIn.add(id + ": Checked in");
            }
        }
        scanCheckIn.close();

        System.out.println("===== Event Check-In Results =====");
        for (String baris : riwayatCheckIn) {
            System.out.println(baris);
        }

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + terdaftar.size());
        System.out.println("Successful check-ins: " + hadir.size());
        System.out.println("Absent students: " + (terdaftar.size() - hadir.size()));
        System.out.println("Rejected attempts: " + percobaanDitolak);

    }
}