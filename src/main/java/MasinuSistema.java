import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class MasinuSistema {
    static List<Masina> masinas = new ArrayList<>();
    static final String CSV_FILE = "masinas.csv";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ieladetDatus();
        
        while (true) {
            System.out.println("\n1. Pievienot mašīnu");
            System.out.println("2. Parādīt visas mašīnas");
            System.out.println("3. Kārtot mašīnas");
            System.out.println("4. Filtrēt mašīnas");
            System.out.println("5. Saglabāt un iziet");
            
            int choice = ievaditSkaitli("Izvēlies darbību: ");
            scanner.nextLine(); // Attīram buferi pēc skaitliskas ievades
            switch (choice) {
                case 1 -> pievienotMasinu();
                case 2 -> paraditMasinas();
                case 3 -> kartotMasinas();
                case 4 -> filtretMasinas();
                case 5 -> { saglabatDatus(); return; }
                default -> System.out.println("Nepareiza izvēle!");
            }
        }
    }

    static int ievaditSkaitli(String uzaicinajums) {
        while (true) {
            try {
                System.out.print(uzaicinajums);
                int skaitlis = scanner.nextInt();
                scanner.nextLine(); // Attīram buferi pēc ievades
                return skaitlis;
            } catch (InputMismatchException e) {
                System.out.println("Nepareiza ievade! Lūdzu, ievadiet skaitli.");
                scanner.nextLine();
            }
        }
    }

    static double ievaditDouble(String uzaicinajums) {
        while (true) {
            try {
                System.out.print(uzaicinajums);
                double skaitlis = scanner.nextDouble();
                scanner.nextLine(); // Attīram buferi pēc ievades
                return skaitlis;
            } catch (InputMismatchException e) {
                System.out.println("Nepareiza ievade! Lūdzu, ievadiet skaitli.");
                scanner.nextLine();
            }
        }
    }

    static String ievaditTekstu(String uzaicinajums) {
        while (true) {
            System.out.print(uzaicinajums);
            String ievade = scanner.nextLine().trim();
            if (!ievade.matches(".*\\d.*")) {
                return ievade;
            }
            System.out.println("Ievadei jābūt tikai no burtiem, mēģini vēlreiz.");
        }
    }

    static void pievienotMasinu() {
        String marka = ievaditTekstu("Marka: ");
        String modelis = ievaditTekstu("Modelis: ");
        String krasa = ievaditTekstu("Krāsa: ");

        int gads;
        while (true) {
            gads = ievaditSkaitli("Gads: ");
            if (gads >= 1850 && gads <= 2025) break;
            System.out.println("Gadam jābūt diapazonā no 1850 līdz 2025. Mēģini vēlreiz.");
        }

        double cena = ievaditDouble("Cena: ");

        masinas.add(new Masina(marka, modelis, krasa, gads, cena));
        System.out.println("Mašīna pievienota!");
    }

    static void paraditMasinas() {
        if (masinas.isEmpty()) {
            System.out.println("Nav pievienota neviena mašīna!");
            return;
        }
        
        System.out.println("\n%-10s %-10s %-10s %-6s %-8s".formatted("Marka", "Modelis", "Krāsa", "Gads", "Cena"));
        System.out.println("----------------------------------------------------------------");
        for (Masina m : masinas) {
            System.out.println(m);
        }
    }

    static void ieladetDatus() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int gads = Integer.parseInt(data[3]);
                if (gads >= 1850 && gads <= 2025) {
                    masinas.add(new Masina(data[0], data[1], data[2], gads, Double.parseDouble(data[4])));
                }
            }
        } catch (IOException e) {
            System.out.println("Nevarēja ielādēt failu, sākam ar tukšu sarakstu.");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Kļūda failā! Nepareizs datu formāts.");
        }
    }

    static void saglabatDatus() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (Masina m : masinas) {
                bw.write(m.marka + "," + m.modelis + "," + m.krasa + "," + m.gads + "," + m.cena + "\n");
            }
            System.out.println("Dati saglabāti!");
        } catch (IOException e) {
            System.out.println("Kļūda saglabājot failu.");
        }
    }
}




