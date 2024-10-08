import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.nio.file.Paths;

/**
 * Třída Sklad představuje sklad, který spravuje kolekci zboží.
 */
public class Sklad {
    private Map<Integer, Zbozi> sklad; // Mapa pro uložení zboží s jejich ID jako klíči

    /**
     * Konstruktor inicializuje sklad s prázdným HashMap.
     */
    public Sklad() {
        sklad = new HashMap<>(); // Inicializace skladu
    }

    /**
     * Přidá nebo aktualizuje zboží ve skladu.
     * @param id ID zboží
     * @param nazev Název zboží
     * @param cena Cena zboží
     * @param pocetKs Počet kusů zboží
     */
    public void pridatZbozi(int id, String nazev, double cena, int pocetKs) {
        Zbozi zbozi = new Zbozi(id, nazev, cena, pocetKs);
        sklad.put(id, zbozi);
        System.out.println("Zboží přidáno/aktualizováno.");
    }

    /**
     * Aktualizuje počet kusů zboží ve skladu.
     * @param id ID zboží
     * @param pocetKs Nový počet kusů zboží
     */
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

    /**
     * Zobrazí detaily zboží podle jeho ID.
     * @param id ID zboží
     */
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


    //Vypočítá a vrátí celkovou cenu veškerého zboží ve skladu.


    public double vypocetCelkoveCeny() {
        double celkovaCena = 0;
        for (Zbozi zbozi : sklad.values()) {
            celkovaCena += zbozi.getCelkovaCena();
        }
        return celkovaCena;
    }

    //Importuje data ze souboru CSV/TXT do skladu.
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

    //Exportuje data ze skladu do souboru CSV/TXT.

    public void exportData(String filePath) {
        String exportPath = Paths.get("src", filePath).toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(exportPath))) {
            for (Zbozi zbozi : sklad.values()) {
                writer.write(zbozi.toCSV());
                writer.newLine();
            }
            System.out.println("Data byla úspěšně exportována do " + exportPath);
        } catch (IOException e) {
            System.out.println("Chyba při zápisu do souboru: " + e.getMessage());
        }
    }


    public static class Zbozi { //Třída Zbozi představuje zbozí ve skladu.
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
}

// Michal Klymov, semestrální práce - DSA.
// VŠPJ, 2024