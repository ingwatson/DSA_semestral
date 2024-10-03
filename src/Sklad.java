// Sklad.java
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Třída Sklad představuje sklad, který spravuje kolekci zboží.
 */
public class Sklad {
    private Map<Integer, Zbozi> sklad; // Mapa pro uložení zboží s jejich ID jako klíči

    /**
     * Konstruktor inicializuje sklad s prázdným HashMap.
     */
    public Sklad() {
        sklad = new HashMap<>();
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

    /**
     * Odstraní zboží ze skladu podle jeho ID.
     * @param id ID zboží
     */
    public void zrusitZbozi(int id) {
        if (sklad.remove(id) != null) {
            System.out.println("Zboží s ID " + id + " bylo zrušeno.");
        } else {
            System.out.println("Zboží s ID " + id + " nebylo nalezeno.");
        }
    }

    /**
     * Zobrazí všechno zboží ve skladu, seřazené podle jejich ID.
     */
    public void vypisVsechny() {
        TreeMap<Integer, Zbozi> serazenySklad = new TreeMap<>(sklad);

        System.out.println(String.format("%-10s %-20s %-10s %-10s", "ID", "Název", "Cena", "Počet kusů"));
        System.out.println("--------------------------------------------------------------");
        for (Zbozi zbozi : serazenySklad.values()) {
            System.out.println(zbozi);
        }
    }

    /**
     * Vypočítá a vrátí celkovou cenu veškerého zboží ve skladu.
     * @return Celková cena zboží
     */
    public double vypocetCelkoveCeny() {
        double celkovaCena = 0;
        for (Zbozi zbozi : sklad.values()) {
            celkovaCena += zbozi.getCelkovaCena();
        }
        return celkovaCena;
    }
}