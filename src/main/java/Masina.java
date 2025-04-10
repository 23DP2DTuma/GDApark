class Masina {
    // ANSI krāsu kodi
    static final String RESET = "\u001B[0m";
    static final String BOLD = "\u001B[1m";
    static final String RED = "\u001B[31m";
    static final String GREEN = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";
    static final String BLUE = "\u001B[34m";
    static final String PURPLE = "\u001B[35m";
    static final String CYAN = "\u001B[36m";
    
    static int idCounter = 1;
    int id;
    String marka, modelis, krasa;
    int gads;
    double cena;

    public Masina(String marka, String modelis, String krasa, int gads, double cena) {
        this.id = idCounter++;
        this.marka = marka;
        this.modelis = modelis;
        this.krasa = krasa;
        this.gads = gads;
        this.cena = cena;
    }

    public Masina(int id, String marka, String modelis, String krasa, int gads, double cena) {
        this.id = id;
        this.marka = marka;
        this.modelis = modelis;
        this.krasa = krasa;
        this.gads = gads;
        this.cena = cena;
        if (id >= idCounter) {
            idCounter = id + 1;
        }
    }

    @Override
    public String toString() {
        // Formatējam tekstu tā, lai tas ietilpst kolonnās
        String formattedId = formatString(BOLD + String.valueOf(id) + RESET, 5);
        String formattedMarka = formatString(BLUE + marka + RESET, 12);
        String formattedModelis = formatString(modelis, 12);
        String formattedKrasa = formatString(PURPLE + krasa + RESET, 12);
        String formattedGads = formatString(YELLOW + String.valueOf(gads) + RESET, 6);
        String formattedCena = formatString(GREEN + String.format("%.2f", cena) + RESET, 10);

        // Добавляем CYAN в начале строки и RESET в конце
        return CYAN + "│" + RESET + " " + formattedId + " " + CYAN + "│" + RESET + " " + formattedMarka + " " + 
               CYAN + "│" + RESET + " " + formattedModelis + " " + CYAN + "│" + RESET + " " + formattedKrasa + " " + 
               CYAN + "│" + RESET + " " + formattedGads + " " + CYAN + "│" + RESET + " " + formattedCena + " " + CYAN + "│" + RESET;
    }

    // Statiska metode tabulas galvenes izvadei
    public static String printTableHeader() {
        return CYAN + "┌───────┬──────────────┬──────────────┬──────────────┬────────┬────────────┐" + RESET + "\n" +
               CYAN + "│" + RESET + " " + BOLD + "ID" + RESET + "    " + CYAN + "│" + RESET + " " + BOLD + "Marka" + RESET + "        " + 
               CYAN + "│" + RESET + " " + BOLD + "Modelis" + RESET + "      " + CYAN + "│" + RESET + " " + BOLD + "Krāsa" + RESET + "        " + 
               CYAN + "│" + RESET + " " + BOLD + "Gads" + RESET + "   " + CYAN + "│" + RESET + " " + BOLD + "Cena" + RESET + "       " + CYAN + "│" + RESET + "\n" +
               CYAN + "├───────┼──────────────┼──────────────┼──────────────┼────────┼────────────┤" + RESET;
    }

    // Statiska metode tabulas kājenes izvadei
    public static String printTableFooter() {
        return CYAN + "└───────┴──────────────┴──────────────┴──────────────┴────────┴────────────┘" + RESET;
    }

    // Palīgmetode teksta formatēšanai, lai tas ietilpst noteiktā platumā
    private String formatString(String input, int maxLength) {
        if (input == null) input = "";
        
        // Aprēķina cik rakstzīmes var būt redzamas, ņemot vērā ANSI kodus
        String withoutAnsi = input.replaceAll("\u001B\\[[;\\d]*m", "");
        int visibleLength = withoutAnsi.length();
        
        if (visibleLength <= maxLength) {
            // Ja teksts ietilpst, pievienojam atstarpes beigās
            return input + " ".repeat(maxLength - visibleLength);
        } else {
            // Ja teksts neietilpst, saīsinām un pievienojam "..."
            // Šeit nepieciešams saīsināt oriģinālo tekstu, nevis to ar ANSI kodiem
            
            // Noņemam ANSI kodus, lai pareizi saīsinātu
            String cleanText = withoutAnsi;
            
            // Saīsinām tekstu
            String shortened = cleanText.substring(0, maxLength - 3) + "...";
            
            // Piemeklējam ANSI kodus no sākuma un beigām
            String startCodes = "";
            String endCodes = "";
            
            // Ja ievades tekstā ir ANSI kodi, izveidojam pareizos kodus
            if (input.contains("\u001B[")) {
                // Atrodam pirmos ANSI kodus
                int firstEscape = input.indexOf("\u001B[");
                int firstEnd = input.indexOf("m", firstEscape);
                if (firstEscape >= 0 && firstEnd >= 0) {
                    startCodes = input.substring(firstEscape, firstEnd + 1);
                }
                
                // Ja ir RESET kods beigās, saglabājam to
                if (input.endsWith(RESET)) {
                    endCodes = RESET;
                }
            }
            
            // Atgriežam saīsināto tekstu ar saglabātiem ANSI kodiem
            return startCodes + shortened + endCodes;
        }
    }
}