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


class Masina {
    String marka, modelis, krasa;
    int gads;
    double cena;

    public Masina(String marka, String modelis, String krasa, int gads, double cena) {
        this.marka = marka;
        this.modelis = modelis;
        this.krasa = krasa;
        this.gads = gads;
        this.cena = cena;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-10s %-10s %-6d %-8.2f", marka, modelis, krasa, gads, cena);
    }
}
