import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static final String EXPORT_DIRECTORY = "testy/exporty"; // Adresář pro export souborů

    public static void main(String[] args) {
        Sklad sklad = new Sklad(); // Vytvoření instance skladu
        Scanner scanner = new Scanner(System.in); // Scanner pro vstup uživatele
        int volba; // Proměnná pro volbu uživatele (prvky menu).

        do {
            System.out.println("\nSkladový systém");
            System.out.println("\n1. Přidat nebo aktualizovat zboží");
            System.out.println("2. Aktualizovat počet kusů zboží podle ID");
            System.out.println("3. Vypsat zboží podle ID");
            System.out.println("4. Zrušit zboží podle ID");
            System.out.println("5. Vypsat všechna zboží seřazená podle ID");
            System.out.println("6. Spočítat celkovou cenu zboží");
            System.out.println("7. Importovat data z CSV/TXT souboru");
            System.out.println("8. Exportovat data do CSV/TXT souboru");
            System.out.println("9. Zobrazit nápovědu");
            System.out.println("10. Konec");
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
                    zobrazitVyzitiPameti();
                    break;
                case 2:
                    System.out.print("Zadejte ID zboží: ");
                    int idAkt = scanner.nextInt();
                    System.out.print("Zadejte nový počet kusů: ");
                    int novyPocetKs = scanner.nextInt();
                    sklad.aktualizovatPocetZbozi(idAkt, novyPocetKs);
                    zobrazitVyzitiPameti();
                    break;
                case 3:
                    System.out.print("Zadejte ID zboží: ");
                    int idVypis = scanner.nextInt();
                    sklad.vypisZbozi(idVypis);
                    zobrazitVyzitiPameti();
                    break;
                case 4:
                    System.out.print("Zadejte ID zboží: ");
                    int idZrus = scanner.nextInt();
                    sklad.zrusitZbozi(idZrus);
                    zobrazitVyzitiPameti();
                    break;
                case 5:
                    sklad.vypisVsechny();
                    zobrazitVyzitiPameti();
                    break;
                case 6:
                    System.out.println("Celková cena všech zboží ve skladu: " + sklad.vypocetCelkoveCeny());
                    zobrazitVyzitiPameti();
                    break;
                case 7:
                    scanner.nextLine(); // Vyčištění bufferu
                    System.out.print("Zadejte cestu k souboru pro import: ");
                    String importPath = scanner.nextLine();
                    sklad.importData(importPath);
                    zobrazitVyzitiPameti();
                    break;
                case 8:
                    System.out.print("Zadejte název souboru: ");
                    scanner.nextLine(); // Vyčištění bufferu
                    String fileName = scanner.nextLine(); // Uživatel zadá název souboru
                    System.out.print("Zvolte formát souboru (csv/txt): "); // Uživatel zadá formát souboru
                    String format = scanner.nextLine().toLowerCase(); // Převod na malá písmena

                    if (format.equals("csv")) {
                        sklad.exportData(fileName + ".csv");
                        zobrazitVyzitiPameti();
                    } else if (format.equals("txt")) {
                        sklad.exportData(fileName + ".txt");
                        zobrazitVyzitiPameti();
                    } else {
                        System.out.println("Neplatný formát, export zrušen.");
                    }

                    break;
                case 9:
                    try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("napoveda.txt")) {
                        if (inputStream != null) {
                            String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                            System.out.println(content);
                        } else {
                            System.out.println("Soubor nápovědy nenalezen.");
                        }
                    } catch (IOException e) {
                        System.out.println("Chyba při čtení souboru: " + e.getMessage());
                    }
                    zobrazitVyzitiPameti();
                    break;
                case 10:
                    System.out.println("Ukončuji program.\nMichal Klymov, semestrální práce - DSA.\n" +
                            "VŠPJ, 2024");
                    break;
                default:
                    System.out.println("Neplatná volba, zkuste to znovu.");
            }
        } while (volba != 10);

        scanner.close(); // Uzavření scanneru
    }

    /**
     * Metoda pro zobrazení využití paměti.
     */
    public static void zobrazitVyzitiPameti() {
        Runtime runtime = Runtime.getRuntime();

        // Vynucení garbage collectoru pro přesnější měření
        System.gc();

        long pametPouzita = runtime.totalMemory() - runtime.freeMemory();
        long pametCelkem = runtime.totalMemory();
        long pametMax = runtime.maxMemory();

        System.out.println("\n=== Využití paměti ===");
        System.out.println("Použitá paměť: " + pametPouzita / (1024 * 1024) + " MB");
        System.out.println("Dostupná paměť: " + pametCelkem / (1024 * 1024) + " MB");
        System.out.println("Maximální paměť: " + pametMax / (1024 * 1024) + " MB");
    }
}