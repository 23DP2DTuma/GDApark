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
        paraditMasinas();
        int id = ievaditSkaitli("Ievadi mašīnas ID, ko vēlies rediģēt: ");
        for (Masina m : masinas) {
            if (m.id == id) {
                System.out.println("Esošā informācija: " + m);
                m.marka = ievaditTekstu("Jaunā marka (vai Enter, lai saglabātu esošo '" + m.marka + "'): ", m.marka);
                m.modelis = ievaditTekstu1("Jaunais modelis (vai Enter, lai saglabātu esošo '" + m.modelis + "'): ", m.modelis);
                m.krasa = ievaditTekstu("Jaunā krāsa (vai Enter, lai saglabātu esošo '" + m.krasa + "'): ", m.krasa);
                
                int jaunaisGads = ievaditSkaitli("Jaunais gads (vai 0, lai saglabātu esošo " + m.gads + "): ");
                if (jaunaisGads != 0) {
                    while (jaunaisGads < 1850 || jaunaisGads > 2025) {
                        System.out.println("Gadam jābūt diapazonā no 1850 līdz 2025. Mēģini vēlreiz.");
                        jaunaisGads = ievaditSkaitli("Jaunais gads (vai 0, lai saglabātu esošo " + m.gads + "): ");
                        if (jaunaisGads == 0) break;
                    }
                    if (jaunaisGads != 0) {
                        m.gads = jaunaisGads;
                    }
                }
                
                double jaunaCena = ievaditDouble("Jaunā cena (vai 0, lai saglabātu esošo " + m.cena + "): ");
                if (jaunaCena != 0) {
                    while (jaunaCena < 0) {
                        System.out.println("Cenai jābūt pozitīvai. Mēģini vēlreiz.");
                        jaunaCena = ievaditDouble("Jaunā cena (vai 0, lai saglabātu esošo " + m.cena + "): ");
                        if (jaunaCena == 0) break;
                    }
                    if (jaunaCena != 0) {
                        m.cena = jaunaCena;
                    }
                }
                
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
        paraditMasinas();
        int id = ievaditSkaitli("Ievadi mašīnas ID, ko vēlies dzēst: ");
        
        // Vispirms atrast mašīnu, lai parādītu informāciju
        Masina dzestama = null;
        for (Masina m : masinas) {
            if (m.id == id) {
                dzestama = m;
                break;
            }
        }
        
        if (dzestama == null) {
            System.out.println("Mašīna ar šo ID netika atrasta.");
            return;
        }
        
        System.out.println("Dzēšamā mašīna: " + dzestama);
        if (!apstiprinatDarbibu("Vai tiešām vēlaties dzēst šo mašīnu? (j/n): ")) return;
        
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
        return ievaditTekstu(uzaicinajums, null);
    }
    
    static String ievaditTekstu(String uzaicinajums, String defaultVertiba) {
        while (true) {
            System.out.print(uzaicinajums);
            String ievade = scanner.nextLine().trim();
            if (ievade.isEmpty() && defaultVertiba != null) {
                return defaultVertiba;
            }
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
    
    static boolean apstiprinatDarbibu(String uzaicinajums) {
        System.out.print(uzaicinajums);
        String atbilde = scanner.nextLine().trim().toLowerCase();
        return atbilde.equals("j");
    }

    static String ievaditTekstu1(String uzaicinajums) {
        return ievaditTekstu1(uzaicinajums, null);
    }
    
    static String ievaditTekstu1(String uzaicinajums, String defaultVertiba) {
        while (true) {
            System.out.print(uzaicinajums);
            String ievade = scanner.nextLine().trim();
            if (ievade.isEmpty() && defaultVertiba != null) {
                return defaultVertiba;
            }
            if (ievade.isEmpty()) {
                System.out.println("Ievade nevar būt tukša! Mēģini vēlreiz.");
                continue;
            }
            // Checking if input contains ONLY letters, numbers and spaces
            if (!ievade.matches("[a-zA-Z0-9āčēģīķļņūšžĀČĒĢĪĶĻŅŪŠŽ\\s]+")) {
                System.out.println("Ievadei jābūt tikai burtiem un cipariem. Mēģini vēlreiz.");
                continue;
            }
            return ievade;
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

        double cena;
        while (true) {
            cena = ievaditDouble("Cena: ");
            if (cena > 0) break;
            System.out.println("Cenai jābūt pozitīvai. Mēģini vēlreiz.");
        }

        masinas.add(new Masina(marka, modelis, krasa, gads, cena));
        System.out.println("Mašīna pievienota!");
    }

    static void paraditMasinas() {
        if (masinas.isEmpty()) {
            System.out.println("Nav pievienota neviena mašīna!");
            return;
        }
        
        // Sakārtojam mašīnas pēc ID augošā secībā, pirms parādīšanas
        masinas.sort(Comparator.comparingInt(m -> m.id));
        
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
        System.out.println("1. Pēc ID (augošā secībā)");
        System.out.println("2. Pēc ID (dilstošā secībā)");
        System.out.println("3. Pēc cenas (augošā secībā)");
        System.out.println("4. Pēc cenas (dilstošā secībā)");
        System.out.println("5. Pēc gada (augošā secībā)");
        System.out.println("6. Pēc gada (dilstošā secībā)");
        System.out.println("7. Pēc markas (alfabētiski)");
        System.out.println("8. Pēc modeļa (alfabētiski)");
        
        int choice = ievaditSkaitli("Izvēlies kārtošanas veidu: ");

        switch (choice) {
            case 1 -> masinas.sort(Comparator.comparingInt(m -> m.id));
            case 2 -> masinas.sort(Comparator.comparingInt((Masina m) -> m.id).reversed());
            case 3 -> masinas.sort(Comparator.comparingDouble(m -> m.cena));
            case 4 -> masinas.sort(Comparator.comparingDouble((Masina m) -> m.cena).reversed());
            case 5 -> masinas.sort(Comparator.comparingInt(m -> m.gads));
            case 6 -> masinas.sort(Comparator.comparingInt((Masina m) -> m.gads).reversed());
            case 7 -> masinas.sort(Comparator.comparing(m -> m.marka));
            case 8 -> masinas.sort(Comparator.comparing(m -> m.modelis));
            default -> {
                System.out.println("Nepareiza izvēle!");
                return;
            }
        }
        
        System.out.println("Mašīnas sakārtotas!");
        
        // Parādam sakārtotās mašīnas (bet neizmantojam paraditMasinas() metodi, jo tā automātiski kārto pēc ID)
        System.out.println("\n%-5s %-10s %-10s %-10s %-6s %-8s".formatted("ID", "Marka", "Modelis", "Krāsa", "Gads", "Cena"));
        System.out.println("----------------------------------------------------------------");
        for (Masina m : masinas) {
            System.out.println(m);
        }
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
        System.out.println("5. Pēc ID diapazona");
        
        int choice = ievaditSkaitli("Izvēlies filtru: ");

        System.out.println("\n%-5s %-10s %-10s %-10s %-6s %-8s".formatted("ID", "Marka", "Modelis", "Krāsa", "Gads", "Cena"));
        System.out.println("----------------------------------------------------------------");

        switch (choice) {
            case 1 -> {
                double minCena = ievaditDouble("Ievadi minimālo cenu: ");
                double maxCena;
                while (true) {
                    maxCena = ievaditDouble("Ievadi maksimālo cenu: ");
                    if (maxCena >= minCena) break;
                    System.out.println("Maksimālai cenai jābūt lielākai vai vienādai ar minimālo cenu. Mēģini vēlreiz.");
                }
                
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
                int maxGads;
                while (true) {
                    maxGads = ievaditSkaitli("Ievadi maksimālo gadu: ");
                    if (maxGads >= minGads) break;
                    System.out.println("Maksimālajam gadam jābūt lielākam vai vienādam ar minimālo gadu. Mēģini vēlreiz.");
                }
                
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
            case 5 -> {
                int minId = ievaditSkaitli("Ievadi minimālo ID: ");
                int maxId;
                while (true) {
                    maxId = ievaditSkaitli("Ievadi maksimālo ID: ");
                    if (maxId >= minId) break;
                    System.out.println("Maksimālajam ID jābūt lielākam vai vienādam ar minimālo ID. Mēģini vēlreiz.");
                }
                
                boolean atrastaMasina = false;
                for (Masina m : masinas) {
                    if (m.id >= minId && m.id <= maxId) {
                        System.out.println(m);
                        atrastaMasina = true;
                    }
                }
                if (!atrastaMasina) {
                    System.out.println("Nav atrasta neviena mašīna šajā ID diapazonā!");
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
                    if (data.length != 6) { // Tagad pārbaudam 6 laukus, ieskaitot ID
                        System.out.println("Kļūdains ieraksts failā: " + line);
                        continue;
                    }
                    
                    int id = Integer.parseInt(data[0]);
                    int gads = Integer.parseInt(data[4]);
                    double cena = Double.parseDouble(data[5]);
                    
                    if (gads >= 1850 && gads <= 2025 && cena > 0) {
                        masinas.add(new Masina(id, data[1], data[2], data[3], gads, cena));
                    } else {
                        System.out.println("Nekorekts gads vai cena ierakstā: " + line);
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
                bw.write(m.id + "," + m.marka + "," + m.modelis + "," + m.krasa + "," + m.gads + "," + m.cena + "\n");
            }
            System.out.println("Dati saglabāti!");
        } catch (IOException e) {
            System.out.println("Kļūda saglabājot failu.");
        }
    }
}




