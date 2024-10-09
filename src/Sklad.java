

import skladt.Zbozi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.TreeMap;

public class Sklad {
    private TreeMap<Integer, Zbozi> sklad = new TreeMap<>();
    private com.example.sklad.DatabaseConnection dbConnection;

    public Sklad() {
        try {
            dbConnection = new com.example.sklad.DatabaseConnection();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            dbConnection = null; // Ensure dbConnection is set to null if connection fails
        }
    }

    public void pridatZbozi(int id, String nazev, double cena, int pocetKs) {
        Zbozi zbozi = new Zbozi(id, nazev, cena, pocetKs);
        sklad.put(id, zbozi);
        if (dbConnection != null) {
            try {
                dbConnection.saveItem(id, nazev, cena, pocetKs);
                System.out.println("Item with ID " + id + " has been added/updated.");
            } catch (SQLException e) {
                System.out.println("Error saving item to the database: " + e.getMessage());
            }
        } else {
            System.out.println("Database connection is not available.");
        }
    }

    public void aktualizovatPocetZbozi(int id, int novyPocetKs) {
        Zbozi zbozi = sklad.get(id);
        if (zbozi != null) {
            zbozi.setPocetKs(novyPocetKs);
            if (dbConnection != null) {
                try {
                    dbConnection.saveItem(id, zbozi.getNazev(), zbozi.getCena(), novyPocetKs);
                } catch (SQLException e) {
                    System.out.println("Error updating item in the database: " + e.getMessage());
                }
            } else {
                System.out.println("Database connection is not available.");
            }
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }

    public void vypisZbozi(int id) {
        Zbozi zbozi = sklad.get(id);
        if (zbozi != null) {
            System.out.println(zbozi);
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }

    public void zrusitZbozi(int id) {
        if (sklad.remove(id) != null) {
            System.out.println("Item with ID " + id + " has been removed.");
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }

    public void vypisVsechny() {
        if (dbConnection != null) {
            try {
                TreeMap<Integer, Zbozi> serazenySklad = dbConnection.getAllItems();
                System.out.println(String.format("%-10s %-20s %-10s %-10s", "ID", "Name", "Price", "Quantity"));
                System.out.println("--------------------------------------------------------------");
                for (Zbozi zbozi : serazenySklad.values()) {
                    System.out.println(zbozi);
                }
            } catch (SQLException e) {
                System.out.println("Error retrieving items from the database: " + e.getMessage());
            }
        } else {
            System.out.println("Database connection is not available.");
        }
    }

    public double vypocetCelkoveCeny() {
        double celkovaCena = 0;
        for (Zbozi zbozi : sklad.values()) {
            celkovaCena += zbozi.getCelkovaCena();
        }
        return celkovaCena;
    }

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
            System.out.println("Data successfully imported from " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void exportData(String fileName) {
        String exportDirectory = Main.EXPORT_DIRECTORY;
        String exportPath = Paths.get(exportDirectory, fileName).toString();
        Path directoryPath = Paths.get(exportDirectory);

        try {
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(exportPath))) {
                for (Zbozi zbozi : sklad.values()) {
                    writer.write(zbozi.toCSV());
                    writer.newLine();
                }
                System.out.println("Data successfully exported to " + exportPath);
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (dbConnection != null) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing database connection: " + e.getMessage());
        }
    }
}