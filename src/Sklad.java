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
}