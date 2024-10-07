import java.util.Scanner;

public class Main {
    // Definování pevné cesty k adresáři pro export, kde budou soubory uloženy.
    private static final String EXPORT_DIRECTORY = "C:/exportedFiles/";

    public static void main(String[] args) {
        Sklad sklad = new Sklad(); // Vytvoření instance skladu
        Scanner scanner = new Scanner(System.in); // Scanner pro vstup uživatele
        int volba; // Proměnná pro volbu uživatele (prvky menu).

        do {
            System.out.println("\n1. Přidat nebo aktualizovat zboží");
            System.out.println("2. Aktualizovat počet kusů zboží podle ID");
            System.out.println("3. Vypsat zboží podle ID");
            System.out.println("4. Zrušit zboží podle ID");
            System.out.println("5. Vypsat všechna zboží seřazená podle ID");
            System.out.println("6. Spočítat celkovou cenu zboží");
            System.out.println("7. Importovat data z CSV/TXT souboru");
            System.out.println("8. Exportovat data do CSV/TXT souboru");
            System.out.println("9. Konec");
            System.out.print("Vyberte operaci: ");
            volba = scanner.nextInt();

            switch (volba) {
                case 1:
                    System.out.print("Zadejte ID zboží: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
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
                    scanner.nextLine(); // Vyčištění bufferu
                    System.out.print("Zadejte cestu k souboru pro import: ");
                    String importPath = scanner.nextLine();
                    sklad.importData(importPath);
                    break;
                case 8:
                    System.out.print("Zadejte název souboru: ");
                    scanner.nextLine(); // Vyčištění bufferu
                    String fileName = scanner.nextLine(); // Uživatel zadá název souboru
                    System.out.print("Zvolte formát souboru (csv/txt): "); // Uživatel zadá formát souboru
                    String format = scanner.nextLine().toLowerCase(); // Převod na malá písmena

                    if (format.equals("csv")) {
                        sklad.exportData(EXPORT_DIRECTORY + fileName + ".csv");
                    } else if (format.equals("txt")) {
                        sklad.exportData(EXPORT_DIRECTORY + fileName + ".txt");
                    } else {
                        System.out.println("Neplatný formát, export zrušen.");
                    }
                    break;
                case 9:
                    System.out.println("Ukončuji program.");
                    break;
                default:
                    System.out.println("Neplatná volba, zkuste to znovu.");
            }
        } while (volba != 9);

        scanner.close(); // Uzavření scanneru
    }
}
