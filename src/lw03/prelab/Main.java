package lw03.prelab;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        problem1();
        problem2();
        problem3();
    }

    static void problem1() {
        Scanner scanLagu = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        List<String> daftarLagu = new ArrayList<>();

        while (scanLagu.hasNextLine()) {
            String[] baris = scanLagu.nextLine().split(" ", 2);
            if (baris[0].equals("ADD")) {
                daftarLagu.add(baris[1]);
            } else if (baris[0].equals("INSERT")) {
                String[] detailInsert = baris[1].split(" ", 2);
                daftarLagu.add(Integer.parseInt(detailInsert[0]), detailInsert[1]);
            } else if (baris[0].equals("REMOVE")) {
                daftarLagu.remove(baris[1]);
            }
        }

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + daftarLagu.size());
        for (int idx = 0; idx < daftarLagu.size(); idx++) {
            System.out.println((idx + 1) + ": " + daftarLagu.get(idx));
        }

        scanLagu.close();
    }

    static void problem2() {
        Scanner scanPeserta = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        Set<String> daftarPeserta = new LinkedHashSet<>();
        int jumlahDuplikat = 0;

        while (scanPeserta.hasNextLine()) {
            if (!daftarPeserta.add(scanPeserta.nextLine())) {
                jumlahDuplikat++;
            }
        }

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + daftarPeserta.size());
        int nomorUrut = 1;
        for (String namaPeserta : daftarPeserta) {
            System.out.println(nomorUrut + ". " + namaPeserta);
            nomorUrut++;
        }
        System.out.println("Duplicate registrations: " + jumlahDuplikat);

        scanPeserta.close();
    }

    static void problem3() {
        Scanner scanBarang = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        Map<String, Integer> stokBarang = new LinkedHashMap<>();
        int transaksiGagal = 0;

        while (scanBarang.hasNext()) {
            String aksi = scanBarang.next();
            String namaBarang = scanBarang.next();
            int jumlah = scanBarang.nextInt();

            if (aksi.equals("ADD")) {
                stokBarang.put(namaBarang, stokBarang.getOrDefault(namaBarang, 0) + jumlah);
            } else if (stokBarang.containsKey(namaBarang) && stokBarang.get(namaBarang) >= jumlah) {
                stokBarang.put(namaBarang, stokBarang.get(namaBarang) - jumlah);
            } else {
                transaksiGagal++;
            }
        }

        System.out.println("===== Problem 3 =====");
        for (String item : stokBarang.keySet()) {
            System.out.println(item + ": " + stokBarang.get(item));
        }
        System.out.println("Failed sales: " + transaksiGagal);

        scanBarang.close();
    }
}