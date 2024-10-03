import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Zbozi {
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
}

public class Main {
    public static void main(String[] args) {
        Sklad sklad = new Sklad();
        Scanner scanner = new Scanner(System.in);
        int volba;

        do {
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
                    System.out.print("Zadejte ID zboží: ");
                    int idAkt = scanner.nextInt();
                    System.out.print("Zadejte nový počet kusů: ");
                    int novyPocetKs = scanner.nextInt();
                    sklad.aktualizovatPocetZbozi(idAkt, novyPocetKs);
                    break;
                case 3:
                    System.out.print("Zadejte ID zboží: ");
                    int idVypis = scanner.nextInt();
                    sklad.vypisZbozi(idVypis);
                    break;
                case 4:
                    System.out.print("Zadejte ID zboží: ");
                    int idZrus = scanner.nextInt();
                    sklad.zrusitZbozi(idZrus);
                    break;
                case 5:
                    sklad.vypisVsechny();
                    break;
                case 6:
                    System.out.println("Celková cena všech zboží ve skladu: " + sklad.vypocetCelkoveCeny());
                    break;
                case 7:
                    System.out.println("Ukončuji program.");
                    break;
                default:
                    System.out.println("Neplatná volba, zkuste to znovu.");
            }
        } while (volba != 7);

        scanner.close();
    }
}