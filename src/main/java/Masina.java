

class Masina {
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
        return String.format("%-5d %-10s %-10s %-10s %-6d %-8.2f", id, marka, modelis, krasa, gads, cena);
    }
}