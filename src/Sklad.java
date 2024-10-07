import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Sklad {
    private Map<Integer, Zbozi> sklad;

    public Sklad() {
        sklad = new HashMap<>();
    }

    public void pridatZbozi(int id, String nazev, double cena, int pocetKs) {
        Zbozi zbozi = new Zbozi(id, nazev, cena, pocetKs);
        sklad.put(id, zbozi);
        System.out.println("Zboží přidáno/aktualizováno.");
    }

    public void aktualizovatPocetZbozi(int id, int pocetKs) {
        Zbozi zbozi = sklad.get(id);
        if (zbozi != null) {
            zbozi.setPocetKs(pocetKs);
            if (pocetKs <= 0) {
                sklad.remove(id);
                System.out.println("Zboží s ID " + id + " bylo odstraněno ze skladu (počet kusů = 0).");
            } else {
                System.out.println("Počet kusů zboží s ID " + id + " byl aktualizován.");
            }
        } else {
            System.out.println("Zboží s ID " + id + " nebylo nalezeno.");
        }
    }

    public void vypisZbozi(int id) {
        Zbozi zbozi = sklad.get(id);
        if (zbozi != null) {
            System.out.println(zbozi);
        } else {
            System.out.println("Zboží s ID " + id + " nebylo nalezeno.");
        }
    }

    public void zrusitZbozi(int id) {
        if (sklad.remove(id) != null) {
            System.out.println("Zboží s ID " + id + " bylo zrušeno.");
        } else {
            System.out.println("Zboží s ID " + id + " nebylo nalezeno.");
        }
    }

    public void vypisVsechny() {
        TreeMap<Integer, Zbozi> serazenySklad = new TreeMap<>(sklad);
        System.out.println(String.format("%-10s %-20s %-10s %-10s", "ID", "Název", "Cena", "Počet kusů"));
        System.out.println("--------------------------------------------------------------");
        for (Zbozi zbozi : serazenySklad.values()) {
            System.out.println(zbozi);
        }
    }

    public double vypocetCelkoveCeny() {
        double celkovaCena = 0;
        for (Zbozi zbozi : sklad.values()) {
            celkovaCena += zbozi.getCelkovaCena();
        }
        return celkovaCena;
    }

    // Import dat z CSV nebo TXT souboru
    public void importData(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String nazev = data[1];
                double cena = Double.parseDouble(data[2]);
                int pocetKs = Integer.parseInt(data[3]);
                pridatZbozi(id, nazev, cena, pocetKs);
            }
            System.out.println("Data byla úspěšně importována z " + filePath);
        } catch (IOException e) {
            System.out.println("Chyba při čtení souboru: " + e.getMessage());
        }
    }

    // Export dat do CSV nebo TXT souboru
    public void exportData(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Zbozi zbozi : sklad.values()) {
                writer.write(zbozi.toCSV());
                writer.newLine();
            }
            System.out.println("Data byla úspěšně exportována do " + filePath);
        } catch (IOException e) {
            System.out.println("Chyba při zápisu do souboru: " + e.getMessage());
        }
    }

    // Třída Zbozi představuje zboží ve skladu
    public static class Zbozi {
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

        // Export jednoho zboží do CSV formátu
        public String toCSV() {
            return id + "," + nazev + "," + cena + "," + pocetKs;
        }
    }
}
