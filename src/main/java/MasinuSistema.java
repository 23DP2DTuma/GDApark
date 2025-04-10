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
    // ANSI krāsu kodi
    static final String RESET = "\u001B[0m";
    static final String BOLD = "\u001B[1m";
    static final String RED = "\u001B[31m";
    static final String GREEN = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";
    static final String BLUE = "\u001B[34m";
    static final String PURPLE = "\u001B[35m";
    static final String CYAN = "\u001B[36m";
    static final String WHITE_BG = "\u001B[47m";
    static final String BLACK_TEXT = "\u001B[30m";
    
    static List<Masina> masinas = new ArrayList<>();
    static final String CSV_FILE = "masinas.csv";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ieladetDatus();
        izvaditVirsrakstu();
        while (true) {
            izvaditIzveletni();
            int choice = ievaditSkaitli("Izvēlies darbību: ");
            switch (choice) {
                case 1 -> pievienotMasinu();
                case 2 -> paraditMasinas();
                case 3 -> kartotMasinas();
                case 4 -> filtretMasinas();
                case 5 -> redigetMasinu();
                case 6 -> dzestMasinu();
                case 7 -> { 
                    saglabatDatus(); 
                    System.out.println(GREEN + "Paldies par darbu ar Mašīnu sistēmu! Uz redzēšanos!" + RESET);
                    return; 
                }
                default -> System.out.println(RED + "Nepareiza izvēle!" + RESET);
            }
        }
    }
    
    static void izvaditVirsrakstu() {
        System.out.println(WHITE_BG + BLACK_TEXT + BOLD + "                                              " + RESET);
        System.out.println(WHITE_BG + BLACK_TEXT + BOLD + "          AUTOMAŠĪNU VADĪBAS SISTĒMA          " + RESET);
        System.out.println(WHITE_BG + BLACK_TEXT + BOLD + "                                              " + RESET);
    }
    
    static void izvaditIzveletni() {
        System.out.println("\n" + BOLD + "PIEEJAMĀS DARBĪBAS:" + RESET);
        System.out.println(CYAN + "1. " + RESET + "Pievienot mašīnu");
        System.out.println(CYAN + "2. " + RESET + "Parādīt visas mašīnas");
        System.out.println(CYAN + "3. " + RESET + "Kārtot mašīnas");
        System.out.println(CYAN + "4. " + RESET + "Filtrēt mašīnas");
        System.out.println(CYAN + "5. " + RESET + "Rediģēt mašīnu");
        System.out.println(CYAN + "6. " + RESET + "Dzēst mašīnu");
        System.out.println(CYAN + "7. " + RESET + "Saglabāt un iziet");
    }
    
    static void redigetMasinu() {
        if (masinas.isEmpty()) {
            System.out.println(YELLOW + "Nav pievienota neviena mašīna!" + RESET);
            return;
        }
        paraditMasinas();
        int id = ievaditSkaitli("Ievadi mašīnas ID, ko vēlies rediģēt: ");
        for (Masina m : masinas) {
            if (m.id == id) {
                System.out.println(BLUE + "Esošā informācija: " + RESET + m);
                m.marka = ievaditTekstu("Jaunā marka (vai Enter, lai saglabātu esošo '" + GREEN + m.marka + RESET + "'): ", m.marka);
                m.modelis = ievaditTekstu1("Jaunais modelis (vai Enter, lai saglabātu esošo '" + GREEN + m.modelis + RESET + "'): ", m.modelis);
                m.krasa = ievaditTekstu("Jaunā krāsa (vai Enter, lai saglabātu esošo '" + GREEN + m.krasa + RESET + "'): ", m.krasa);
                
                int jaunaisGads = ievaditSkaitli("Jaunais gads (vai 0, lai saglabātu esošo " + GREEN + m.gads + RESET + "): ");
                if (jaunaisGads != 0) {
                    while (jaunaisGads < 1850 || jaunaisGads > 2025) {
                        System.out.println(RED + "Gadam jābūt diapazonā no 1850 līdz 2025. Mēģini vēlreiz." + RESET);
                        jaunaisGads = ievaditSkaitli("Jaunais gads (vai 0, lai saglabātu esošo " + GREEN + m.gads + RESET + "): ");
                        if (jaunaisGads == 0) break;
                    }
                    if (jaunaisGads != 0) {
                        m.gads = jaunaisGads;
                    }
                }
                
                double jaunaCena = ievaditDouble("Jaunā cena (vai 0, lai saglabātu esošo " + GREEN + m.cena + RESET + "): ");
                if (jaunaCena != 0) {
                    while (jaunaCena < 0) {
                        System.out.println(RED + "Cenai jābūt pozitīvai. Mēģini vēlreiz." + RESET);
                        jaunaCena = ievaditDouble("Jaunā cena (vai 0, lai saglabātu esošo " + GREEN + m.cena + RESET + "): ");
                        if (jaunaCena == 0) break;
                    }
                    if (jaunaCena != 0) {
                        m.cena = jaunaCena;
                    }
                }
                
                System.out.println(GREEN + BOLD + "Mašīna atjaunināta!" + RESET);
                return;
            }
        }
        System.out.println(RED + "Mašīna ar šo ID netika atrasta." + RESET);
    }
    
    static void dzestMasinu() {
        if (masinas.isEmpty()) {
            System.out.println(YELLOW + "Nav pievienota neviena mašīna!" + RESET);
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
            System.out.println(RED + "Mašīna ar šo ID netika atrasta." + RESET);
            return;
        }
        
        System.out.println(YELLOW + "Dzēšamā mašīna: " + RESET + dzestama);
        if (!apstiprinatDarbibu(RED + "Vai tiešām vēlaties dzēst šo mašīnu? (j/n): " + RESET)) return;
        
        if (masinas.removeIf(m -> m.id == id)) {
            System.out.println(GREEN + BOLD + "Mašīna dzēsta!" + RESET);
        } else {
            System.out.println(RED + "Mašīna ar šo ID netika atrasta." + RESET);
        }
    }

    static int ievaditSkaitli(String uzaicinajums) {
        while (true) {
            try {
                System.out.print(PURPLE + uzaicinajums + RESET);
                int skaitlis = scanner.nextInt();
                scanner.nextLine(); // Attīram buferi pēc ievades
                return skaitlis;
            } catch (InputMismatchException e) {
                System.out.println(RED + "Nepareiza ievade! Lūdzu, ievadiet skaitli." + RESET);
                scanner.nextLine();
            }
        }
    }

    static double ievaditDouble(String uzaicinajums) {
        while (true) {
            try {
                System.out.print(PURPLE + uzaicinajums + RESET);
                double skaitlis = scanner.nextDouble();
                scanner.nextLine(); // Attīram buferi pēc ievades
                return skaitlis;
            } catch (InputMismatchException e) {
                System.out.println(RED + "Nepareiza ievade! Lūdzu, ievadiet skaitli." + RESET);
                scanner.nextLine();
            }
        }
    }

    static String ievaditTekstu(String uzaicinajums) {
        return ievaditTekstu(uzaicinajums, null);
    }
    
    static String ievaditTekstu(String uzaicinajums, String defaultVertiba) {
        while (true) {
            System.out.print(PURPLE + uzaicinajums + RESET);
            String ievade = scanner.nextLine().trim();
            if (ievade.isEmpty() && defaultVertiba != null) {
                return defaultVertiba;
            }
            if (ievade.isEmpty()) {
                System.out.println(RED + "Ievade nevar būt tukša! Mēģini vēlreiz." + RESET);
                continue;
            }
            if (!ievade.matches("[a-zA-ZāčēģīķļņūšžĀČĒĢĪĶĻŅŪŠŽ]+")) {
                System.out.println(RED + "Ievadei jābūt tikai no burtiem, bez cipariem un simboliem. Mēģini vēlreiz." + RESET);
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
            System.out.print(PURPLE + uzaicinajums + RESET);
            String ievade = scanner.nextLine().trim();
            if (ievade.isEmpty() && defaultVertiba != null) {
                return defaultVertiba;
            }
            if (ievade.isEmpty()) {
                System.out.println(RED + "Ievade nevar būt tukša! Mēģini vēlreiz." + RESET);
                continue;
            }
            // Checking if input contains ONLY letters, numbers and spaces
            if (!ievade.matches("[a-zA-Z0-9āčēģīķļņūšžĀČĒĢĪĶĻŅŪŠŽ\\s]+")) {
                System.out.println(RED + "Ievadei jābūt tikai burtiem un cipariem. Mēģini vēlreiz." + RESET);
                continue;
            }
            return ievade;
        }
    }

    static void pievienotMasinu() {
        System.out.println(BOLD + "\nJAUNAS MAŠĪNAS PIEVIENOŠANA" + RESET);
        String marka = ievaditTekstu("Marka: ");
        String modelis = ievaditTekstu1("Modelis: ");
        String krasa = ievaditTekstu("Krāsa: ");

        int gads;
        while (true) {
            gads = ievaditSkaitli("Gads: ");
            if (gads >= 1850 && gads <= 2025) break;
            System.out.println(RED + "Gadam jābūt diapazonā no 1850 līdz 2025. Mēģini vēlreiz." + RESET);
        }

        double cena;
        while (true) {
            cena = ievaditDouble("Cena: ");
            if (cena > 0) break;
            System.out.println(RED + "Cenai jābūt pozitīvai. Mēģini vēlreiz." + RESET);
        }

        masinas.add(new Masina(marka, modelis, krasa, gads, cena));
        System.out.println(GREEN + BOLD + "Mašīna pievienota!" + RESET);
    }

    static void paraditMasinas() {
        if (masinas.isEmpty()) {
            System.out.println(YELLOW + "Nav pievienota neviena mašīna!" + RESET);
            return;
        }
        
        // Sakārtojam mašīnas pēc ID augošā secībā, pirms parādīšanas
        masinas.sort(Comparator.comparingInt(m -> m.id));
        
        System.out.println(BOLD + "\nMASINAS SARAKSTS:" + RESET);
        System.out.println(CYAN + Masina.printTableHeader() + RESET);
        for (Masina m : masinas) {
            System.out.println(m);
        }
        System.out.println(CYAN + Masina.printTableFooter() + RESET);
    }

    static void kartotMasinas() {
        if (masinas.isEmpty()) {
            System.out.println(YELLOW + "Nav pievienota neviena mašīna!" + RESET);
            return;
        }
        
        System.out.println(BOLD + "\nKĀRTOŠANAS OPCIJAS:" + RESET);
        System.out.println(CYAN + "1. " + RESET + "Pēc ID (augošā secībā)");
        System.out.println(CYAN + "2. " + RESET + "Pēc ID (dilstošā secībā)");
        System.out.println(CYAN + "3. " + RESET + "Pēc cenas (augošā secībā)");
        System.out.println(CYAN + "4. " + RESET + "Pēc cenas (dilstošā secībā)");
        System.out.println(CYAN + "5. " + RESET + "Pēc gada (augošā secībā)");
        System.out.println(CYAN + "6. " + RESET + "Pēc gada (dilstošā secībā)");
        System.out.println(CYAN + "7. " + RESET + "Pēc markas (alfabētiski)");
        System.out.println(CYAN + "8. " + RESET + "Pēc modeļa (alfabētiski)");
        
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
                System.out.println(RED + "Nepareiza izvēle!" + RESET);
                return;
            }
        }
        
        System.out.println(GREEN + BOLD + "Mašīnas sakārtotas!" + RESET);
        
        System.out.println(CYAN + Masina.printTableHeader() + RESET);
        for (Masina m : masinas) {
            System.out.println(m);
        }
        System.out.println(CYAN + Masina.printTableFooter() + RESET);
    }

    static void filtretMasinas() {
        if (masinas.isEmpty()) {
            System.out.println(YELLOW + "Nav pievienota neviena mašīna!" + RESET);
            return;
        }
        
        System.out.println(BOLD + "\nFILTRĒŠANAS OPCIJAS:" + RESET);
        System.out.println(CYAN + "1. " + RESET + "Pēc cenas diapazona");
        System.out.println(CYAN + "2. " + RESET + "Pēc markas");
        System.out.println(CYAN + "3. " + RESET + "Pēc krāsas");
        System.out.println(CYAN + "4. " + RESET + "Pēc gada diapazona");
        System.out.println(CYAN + "5. " + RESET + "Pēc ID diapazona");
        
        int choice = ievaditSkaitli("Izvēlies filtru: ");

        switch (choice) {
            case 1 -> {
                double minCena = ievaditDouble("Ievadi minimālo cenu: ");
                double maxCena;
                while (true) {
                    maxCena = ievaditDouble("Ievadi maksimālo cenu: ");
                    if (maxCena >= minCena) break;
                    System.out.println(RED + "Maksimālai cenai jābūt lielākai vai vienādai ar minimālo cenu. Mēģini vēlreiz." + RESET);
                }
                
                boolean atrastaMasina = false;
                System.out.println(CYAN + Masina.printTableHeader() + RESET);
                for (Masina m : masinas) {
                    if (m.cena >= minCena && m.cena <= maxCena) {
                        System.out.println(m);
                        atrastaMasina = true;
                    }
                }
                System.out.println(CYAN + Masina.printTableFooter() + RESET);
                
                if (!atrastaMasina) {
                    System.out.println(YELLOW + "Nav atrasta neviena mašīna šajā cenu diapazonā!" + RESET);
                }
            }
            case 2 -> {
                String marka = ievaditTekstu("Ievadi marku: ");
                
                boolean atrastaMasina = false;
                System.out.println(CYAN + Masina.printTableHeader() + RESET);
                for (Masina m : masinas) {
                    if (m.marka.equalsIgnoreCase(marka)) {
                        System.out.println(m);
                        atrastaMasina = true;
                    }
                }
                System.out.println(CYAN + Masina.printTableFooter() + RESET);
                
                if (!atrastaMasina) {
                    System.out.println(YELLOW + "Nav atrasta neviena mašīna ar šo marku!" + RESET);
                }
            }
            case 3 -> {
                String krasa = ievaditTekstu("Ievadi krāsu: ");
                
                boolean atrastaMasina = false;
                System.out.println(CYAN + Masina.printTableHeader() + RESET);
                for (Masina m : masinas) {
                    if (m.krasa.equalsIgnoreCase(krasa)) {
                        System.out.println(m);
                        atrastaMasina = true;
                    }
                }
                System.out.println(CYAN + Masina.printTableFooter() + RESET);
                
                if (!atrastaMasina) {
                    System.out.println(YELLOW + "Nav atrasta neviena mašīna ar šo krāsu!" + RESET);
                }
            }
            case 4 -> {
                int minGads = ievaditSkaitli("Ievadi minimālo gadu: ");
                int maxGads;
                while (true) {
                    maxGads = ievaditSkaitli("Ievadi maksimālo gadu: ");
                    if (maxGads >= minGads) break;
                    System.out.println(RED + "Maksimālajam gadam jābūt lielākam vai vienādam ar minimālo gadu. Mēģini vēlreiz." + RESET);
                }
                
                boolean atrastaMasina = false;
                System.out.println(CYAN + Masina.printTableHeader() + RESET);
                for (Masina m : masinas) {
                    if (m.gads >= minGads && m.gads <= maxGads) {
                        System.out.println(m);
                        atrastaMasina = true;
                    }
                }
                System.out.println(CYAN + Masina.printTableFooter() + RESET);
                
                if (!atrastaMasina) {
                    System.out.println(YELLOW + "Nav atrasta neviena mašīna šajā gadu diapazonā!" + RESET);
                }
            }
            case 5 -> {
                int minId = ievaditSkaitli("Ievadi minimālo ID: ");
                int maxId;
                while (true) {
                    maxId = ievaditSkaitli("Ievadi maksimālo ID: ");
                    if (maxId >= minId) break;
                    System.out.println(RED + "Maksimālajam ID jābūt lielākam vai vienādam ar minimālo ID. Mēģini vēlreiz." + RESET);
                }
                
                boolean atrastaMasina = false;
                System.out.println(CYAN + Masina.printTableHeader() + RESET);
                for (Masina m : masinas) {
                    if (m.id >= minId && m.id <= maxId) {
                        System.out.println(m);
                        atrastaMasina = true;
                    }
                }
                System.out.println(CYAN + Masina.printTableFooter() + RESET);
                
                if (!atrastaMasina) {
                    System.out.println(YELLOW + "Nav atrasta neviena mašīna šajā ID diapazonā!" + RESET);
                }
            }
            default -> System.out.println(RED + "Nepareiza izvēle!" + RESET);
        }
    }

    static void ieladetDatus() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] data = line.split(",");
                    if (data.length != 6) { // Tagad pārbaudam 6 laukus, ieskaitot ID
                        System.out.println(RED + "Kļūdains ieraksts failā: " + line + RESET);
                        continue;
                    }
                    
                    int id = Integer.parseInt(data[0]);
                    int gads = Integer.parseInt(data[4]);
                    double cena = Double.parseDouble(data[5]);
                    
                    if (gads >= 1850 && gads <= 2025 && cena > 0) {
                        masinas.add(new Masina(id, data[1], data[2], data[3], gads, cena));
                    } else {
                        System.out.println(RED + "Nekorekts gads vai cena ierakstā: " + line + RESET);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println(RED + "Kļūda ieraksta apstrādē: " + line + RESET);
                }
            }
            System.out.println(GREEN + "Dati veiksmīgi ielādēti!" + RESET);
        } catch (IOException e) {
            System.out.println(YELLOW + "Nevarēja ielādēt failu, sākam ar tukšu sarakstu." + RESET);
        }
    }

    static void saglabatDatus() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (Masina m : masinas) {
                bw.write(m.id + "," + m.marka + "," + m.modelis + "," + m.krasa + "," + m.gads + "," + m.cena + "\n");
            }
            System.out.println(GREEN + BOLD + "Dati veiksmīgi saglabāti!" + RESET);
        } catch (IOException e) {
            System.out.println(RED + "Kļūda saglabājot failu." + RESET);
        }
    }
}




