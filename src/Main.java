import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static final String EXPORT_DIRECTORY = "exported_data";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sklad sklad = new Sklad();
        int volba;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Přidat zboží");
            System.out.println("2. Aktualizovat počet zboží");
            System.out.println("3. Vypsat zboží podle ID");
            System.out.println("4. Zrušit zboží podle ID");
            System.out.println("5. Vypsat všechno zboží");
            System.out.println("6. Vypočítat celkovou cenu zboží");
            System.out.println("7. Importovat data ze souboru");
            System.out.println("8. Exportovat data do souboru");
            System.out.println("9. Zobrazit nápovědu");
            System.out.println("10. Ukončit program");
            System.out.print("Zadejte volbu: ");
            volba = scanner.nextInt();

            switch (volba) {
                case 1:
                    System.out.print("Zadejte ID zboží: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Vyčištění bufferu
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
                    int idAktualizace = scanner.nextInt();
                    System.out.print("Zadejte nový počet kusů: ");
                    int novyPocetKs = scanner.nextInt();
                    sklad.aktualizovatPocetZbozi(idAktualizace, novyPocetKs);
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

    // Metoda pro zobrazení využití paměti.
    public static void zobrazitVyzitiPameti() {
        // Získání instance runtime spojené s aktuální Java aplikací.
        Runtime runtime = Runtime.getRuntime();

        // Vynucení garbage collectoru pro přesnější měření.
        System.gc();

        // Výpočet použité paměti.
        long pametPouzita = runtime.totalMemory() - runtime.freeMemory();
        // Získání celkové paměti aktuálně dostupné JVM.
        long pametCelkem = runtime.totalMemory();
        // Získání maximální paměti, kterou se JVM pokusí použít.
        long pametMax = runtime.maxMemory();

        // Výpis detailů o využití paměti.
        System.out.println("\n=== Využití paměti ===");
        System.out.println("Použitá paměť: " + pametPouzita / (1024 * 1024) + " MB");
        System.out.println("Dostupná paměť: " + pametCelkem / (1024 * 1024) + " MB");
        System.out.println("Maximální paměť: " + pametMax / (1024 * 1024) + " MB");
    }
}