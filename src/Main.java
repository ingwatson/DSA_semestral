// Main.java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Třída Zbozi představuje zboží ve skladu.
 */
class Zbozi {
    private int id; // ID zboží
    private String nazev; // Název zboží
    private double cena; // Cena zboží
    private int pocetKs; // Počet kusů zboží

    /**
     * Konstruktor inicializuje nové zboží se zadanými detaily.
     * @param id ID zboží
     * @param nazev Název zboží
     * @param cena Cena zboží
     * @param pocetKs Počet kusů zboží
     */
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

    /**
     * Vrátí celkovou cenu zboží (cena * počet kusů).
     * @return Celková cena zboží
     */
    public double getCelkovaCena() {
        return cena * pocetKs;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-20s %-10.2f %-10d", id, nazev, cena, pocetKs);
    }
}

/**
 * Hlavní třída programu.
 */
public class Main {
    public static void main(String[] args) {
        Sklad sklad = new Sklad(); // Vytvoření instance skladu
        Scanner scanner = new Scanner(System.in); // Scanner pro vstup uživatele
        int volba; // Proměnná pro volbu uživatele

        do {
            // Zobrazení menu
            System.out.println("\n1. Přidat nebo aktualizovat zboží");
            System.out.println("2. Aktualizovat počet kusů zboží podle ID");
            System.out.println("3. Vypsat zboží podle ID");
            System.out.println("4. Zrušit zboží podle ID");
            System.out.println("5. Vypsat všechna zboží seřazená podle ID");
            System.out.println("6. Spočítat celkovou cenu zboží");
            System.out.println("7. Konec");
            System.out.print("Vyberte operaci: ");
            volba = scanner.nextInt();

            switch (volba) {
                case 1:
                    // Přidání nebo aktualizace zboží
                    System.out.print("Zadejte ID zboží: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // Vyčistit buffer
                    System.out.print("Zadejte název zboží: ");
                    String nazev = scanner.nextLine();
                    System.out.print("Zadejte cenu zboží: ");
                    double cena = scanner.nextDouble();
                    System.out.print("Zadejte počet kusů: ");
                    int pocetKs = scanner.nextInt();
                    sklad.pridatZbozi(id, nazev, cena, pocetKs);
                    break;
                case 2:
                    // Aktualizace počtu kusů zboží
                    System.out.print("Zadejte ID zboží: ");
                    int idAkt = scanner.nextInt();
                    System.out.print("Zadejte nový počet kusů: ");
                    int novyPocetKs = scanner.nextInt();
                    sklad.aktualizovatPocetZbozi(idAkt, novyPocetKs);
                    break;
                case 3:
                    // Výpis zboží podle ID
                    System.out.print("Zadejte ID zboží: ");
                    int idVypis = scanner.nextInt();
                    sklad.vypisZbozi(idVypis);
                    break;
                case 4:
                    // Zrušení zboží podle ID
                    System.out.print("Zadejte ID zboží: ");
                    int idZrus = scanner.nextInt();
                    sklad.zrusitZbozi(idZrus);
                    break;
                case 5:
                    // Výpis všech zboží seřazených podle ID
                    sklad.vypisVsechny();
                    break;
                case 6:
                    // Výpočet celkové ceny zboží
                    System.out.println("Celková cena všech zboží ve skladu: " + sklad.vypocetCelkoveCeny());
                    break;
                case 7:
                    // Ukončení programu
                    System.out.println("Ukončuji program.");
                    break;
                default:
                    // Neplatná volba
                    System.out.println("Neplatná volba, zkuste to znovu.");
            }
        } while (volba != 7);

        scanner.close(); // Uzavření scanneru
    }
}