package skladt;

public class Zbozi {
    private int id;
    private String nazev;
    private double cena;
    private int pocetKs;

    public Zbozi(int id, String nazev, double cena, int pocetKs) {
        this.id = id;
        this.nazev = nazev;
        this.cena = cena;
        this.pocetKs = pocetKs;
    }

    public int getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }

    public double getCena() {
        return cena;
    }

    public int getPocetKs() {
        return pocetKs;
    }

    public void setPocetKs(int pocetKs) {
        this.pocetKs = pocetKs;
    }

    public double getCelkovaCena() {
        return cena * pocetKs;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-20s %-10.2f %-10d", id, nazev, cena, pocetKs);
    }

    public String toCSV() {
        return id + "," + nazev + "," + cena + "," + pocetKs;
    }
}