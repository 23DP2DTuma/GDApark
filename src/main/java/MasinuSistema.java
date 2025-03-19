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
            System.out.println("5. Rediģēt mašīnu");
            System.out.println("6. Dzēst mašīnu");
            System.out.println("7. Saglabāt un iziet");

            int choice = ievaditSkaitli("Izvēlies darbību: ");
            switch (choice) {
                case 1 -> pievienotMasinu();
                case 2 -> paraditMasinas();
                case 3 -> kartotMasinas();
                case 4 -> filtretMasinas();
                case 5 -> redigetMasinu();
                case 6 -> dzestMasinu();
                case 7 -> { saglabatDatus(); return; }
                default -> System.out.println("Nepareiza izvēle!");
            }
        }
    }
    static void redigetMasinu() {
        if (masinas.isEmpty()) {
            System.out.println("Nav pievienota neviena mašīna!");
            return;
        }
        int id = ievaditSkaitli("Ievadi mašīnas ID, ko vēlies rediģēt: ");
        for (Masina m : masinas) {
            if (m.id == id) {
                System.out.println("Esošā informācija: " + m);
                m.marka = ievaditTekstu("Jaunā marka: ");
                m.modelis = ievaditTekstu("Jaunais modelis: ");
                m.krasa = ievaditTekstu("Jaunā krāsa: ");
                m.gads = ievaditSkaitli("Jaunais gads: ");
                m.cena = ievaditDouble("Jaunā cena: ");
                System.out.println("Mašīna atjaunināta!");
                return;
            }
        }
        System.out.println("Mašīna ar šo ID netika atrasta.");
    }
    static void dzestMasinu() {
        if (masinas.isEmpty()) {
            System.out.println("Nav pievienota neviena mašīna!");
            return;
        }
        int id = ievaditSkaitli("Ievadi mašīnas ID, ko vēlies dzēst: ");
        if (masinas.removeIf(m -> m.id == id)) {
            System.out.println("Mašīna dzēsta!");
        } else {
            System.out.println("Mašīna ar šo ID netika atrasta.");
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
            if (ievade.isEmpty()) {
                System.out.println("Ievade nevar būt tukša! Mēģini vēlreiz.");
                continue;
            }
            if (!ievade.matches("[a-zA-ZāčēģīķļņūšžĀČĒĢĪĶĻŅŪŠŽ]+")) {
                System.out.println("Ievadei jābūt tikai no burtiem, bez cipariem un simboliem. Mēģini vēlreiz.");
                continue;
            }
            return ievade;
        }
    }

    static String ievaditTekstu1(String uzaicinajums) {
        while (true) {
            System.out.print(uzaicinajums);
            String ievade = scanner.nextLine().trim();
            if (ievade.isEmpty()) {
                System.out.println("Ievade nevar būt tukša! Mēģini vēlreiz.");
                continue;
            }
            // Modified to allow only letters and numbers
            if (ievade.matches("[a-zA-Z0-9āčēģīķļņūšžĀČĒĢĪĶĻŅŪŠŽ\\s]+")) {
                return ievade;
            }
            System.out.println("Ievadei jābūt tikai burtiem un cipariem. Mēģini vēlreiz.");
        }
    }

    static void pievienotMasinu() {
        String marka = ievaditTekstu("Marka: ");
        String modelis = ievaditTekstu1("Modelis: ");
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
    
    System.out.println("\n%-5s %-10s %-10s %-10s %-6s %-8s".formatted("ID", "Marka", "Modelis", "Krāsa", "Gads", "Cena"));
    System.out.println("----------------------------------------------------------------");
    for (Masina m : masinas) {
        System.out.println(m);
    }
}

    static void kartotMasinas() {
        if (masinas.isEmpty()) {
            System.out.println("Nav pievienota neviena mašīna!");
            return;
        }
        
        System.out.println("\nIzvēlies kārtošanas veidu:");
        System.out.println("1. Pēc cenas (augošā secībā)");
        System.out.println("2. Pēc cenas (dilstošā secībā)");
        System.out.println("3. Pēc gada (augošā secībā)");
        System.out.println("4. Pēc gada (dilstošā secībā)");
        System.out.println("5. Pēc markas (alfabētiski)");
        System.out.println("6. Pēc modeļa (alfabētiski)");
        
        int choice = ievaditSkaitli("Izvēlies kārtošanas veidu: ");

        switch (choice) {
            case 1 -> masinas.sort(Comparator.comparingDouble(m -> m.cena));
            case 2 -> masinas.sort(Comparator.comparingDouble((Masina m) -> m.cena).reversed());
            case 3 -> masinas.sort(Comparator.comparingInt(m -> m.gads));
            case 4 -> masinas.sort(Comparator.comparingInt((Masina m) -> m.gads).reversed());
            case 5 -> masinas.sort(Comparator.comparing(m -> m.marka));
            case 6 -> masinas.sort(Comparator.comparing(m -> m.modelis));
            default -> System.out.println("Nepareiza izvēle!");
        }
        
        System.out.println("Mašīnas sakārtotas!");
        paraditMasinas();
    }

    static void filtretMasinas() {
        if (masinas.isEmpty()) {
            System.out.println("Nav pievienota neviena mašīna!");
            return;
        }
        
        System.out.println("\nIzvēlies filtru:");
        System.out.println("1. Pēc cenas diapazona");
        System.out.println("2. Pēc markas");
        System.out.println("3. Pēc krāsas");
        System.out.println("4. Pēc gada diapazona");
        
        int choice = ievaditSkaitli("Izvēlies filtru: ");

        System.out.println("\n%-10s %-10s %-10s %-6s %-8s".formatted("Marka", "Modelis", "Krāsa", "Gads", "Cena"));
        System.out.println("----------------------------------------------------------------");

        switch (choice) {
            case 1 -> {
                double minCena = ievaditDouble("Ievadi minimālo cenu: ");
                double maxCena = ievaditDouble("Ievadi maksimālo cenu: ");
                
                boolean atrastaMasina = false;
                for (Masina m : masinas) {
                    if (m.cena >= minCena && m.cena <= maxCena) {
                        System.out.println(m);
                        atrastaMasina = true;
                    }
                }
                if (!atrastaMasina) {
                    System.out.println("Nav atrasta neviena mašīna šajā cenu diapazonā!");
                }
            }
            case 2 -> {
                String marka = ievaditTekstu("Ievadi marku: ");
                
                boolean atrastaMasina = false;
                for (Masina m : masinas) {
                    if (m.marka.equalsIgnoreCase(marka)) {
                        System.out.println(m);
                        atrastaMasina = true;
                    }
                }
                if (!atrastaMasina) {
                    System.out.println("Nav atrasta neviena mašīna ar šo marku!");
                }
            }
            case 3 -> {
                String krasa = ievaditTekstu("Ievadi krāsu: ");
                
                boolean atrastaMasina = false;
                for (Masina m : masinas) {
                    if (m.krasa.equalsIgnoreCase(krasa)) {
                        System.out.println(m);
                        atrastaMasina = true;
                    }
                }
                if (!atrastaMasina) {
                    System.out.println("Nav atrasta neviena mašīna ar šo krāsu!");
                }
            }
            case 4 -> {
                int minGads = ievaditSkaitli("Ievadi minimālo gadu: ");
                int maxGads = ievaditSkaitli("Ievadi maksimālo gadu: ");
                
                boolean atrastaMasina = false;
                for (Masina m : masinas) {
                    if (m.gads >= minGads && m.gads <= maxGads) {
                        System.out.println(m);
                        atrastaMasina = true;
                    }
                }
                if (!atrastaMasina) {
                    System.out.println("Nav atrasta neviena mašīna šajā gadu diapazonā!");
                }
            }
            default -> System.out.println("Nepareiza izvēle!");
        }
    }

    static void ieladetDatus() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] data = line.split(",");
                    if (data.length != 5) {
                        System.out.println("Kļūdains ieraksts failā: " + line);
                        continue;
                    }
                    
                    int gads = Integer.parseInt(data[3]);
                    double cena = Double.parseDouble(data[4]);
                    
                    if (gads >= 1850 && gads <= 2025) {
                        masinas.add(new Masina(data[0], data[1], data[2], gads, cena));
                    } else {
                        System.out.println("Nekorekts gads ierakstā: " + line);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Kļūda ieraksta apstrādē: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Nevarēja ielādēt failu, sākam ar tukšu sarakstu.");
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




