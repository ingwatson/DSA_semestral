# Systém pro správu skladu

Tento projekt je jednoduchý systém pro správu skladu implementovaný v jazyce Java. Umožňuje uživatelům spravovat kolekci zboží, včetně přidávání, aktualizace, odstraňování a zobrazování zboží, stejně jako výpočet celkové ceny veškerého zboží ve skladu.

## Struktura projektu

- `src/Main.java`: Obsahuje hlavní třídu a třídu `Zbozi`.
- `src/Sklad.java`: Obsahuje třídu `Sklad`.

## Třídy

### Sklad

Třída `Sklad` představuje sklad, který spravuje kolekci zboží (`Zbozi`). Poskytuje metody pro přidávání, aktualizaci, odstraňování a zobrazování zboží, stejně jako pro výpočet celkové ceny veškerého zboží ve skladu.

#### Metody

- `pridatZbozi(int id, String nazev, double cena, int pocetKs)`: Přidá nebo aktualizuje zboží ve skladu.
- `aktualizovatPocetZbozi(int id, int pocetKs)`: Aktualizuje počet kusů zboží ve skladu.
- `vypisZbozi(int id)`: Zobrazí detaily zboží podle jeho ID.
- `zrusitZbozi(int id)`: Odstraní zboží ze skladu podle jeho ID.
- `vypisVsechny()`: Zobrazí všechno zboží ve skladu, seřazené podle jejich ID.
- `vypocetCelkoveCeny()`: Vypočítá a vrátí celkovou cenu veškerého zboží ve skladu.
- `importData(String filePath)`: Importuje data ze souboru CSV/TXT do skladu.
- `exportData(String filePath)`: Exportuje data ze skladu do souboru CSV/TXT.

### Zbozi

Třída `Zbozi` představuje zboží ve skladu. Obsahuje detaily o zboží, jako je jeho ID, název, cena a počet kusů.

#### Metody

- `getId()`: Vrátí ID zboží.
- `getNazev()`: Vrátí název zboží.
- `getCena()`: Vrátí cenu zboží.
- `getPocetKs()`: Vrátí počet kusů zboží.
- `setPocetKs(int pocetKs)`: Nastaví počet kusů zboží.
- `getCelkovaCena()`: Vrátí celkovou cenu zboží (cena * počet kusů).
- `toString()`: Vrátí textovou reprezentaci zboží.
- `toCSV()`: Vrátí CSV reprezentaci zboží.

## Datové struktury

V projektu jsou použity následující datové struktury:

1. **`HashMap`**:
   - Používá se ve třídě `Sklad` pro uložení zboží (`Zbozi`) s jejich ID jako klíči.
   - `HashMap` umožňuje rychlý přístup k zboží podle jejich ID.


2. **`TreeMap`**:
   - Používá se ve třídě Sklad pro seřazení zboží podle jejich ID při výpisu všech zboží.
   - `TreeMap` automaticky udržuje zboží seřazené podle klíčů (ID).


2. **`Array`**:
   - Používá se při importu dat ze souboru CSV/TXT do skladu.
   - `Array` je používán pro dočasné uložení rozdělených hodnot z řádku souboru.

## Sledování využití paměti

Aplikace po každé uživatelské operaci zobrazuje informace o využití paměti JVM. Tato funkce zobrazuje tři klíčové hodnoty:

- **Použitá paměť**: Paměť aktuálně využívaná programem.
- **Dostupná paměť**: Množství paměti dostupné pro program v daném okamžiku.
- **Maximální paměť**: Maximální množství paměti, které může JVM použít.

# Použití

1. Zkompilujte Java soubory:
    ```sh
    javac main.java
    ```

2. Spusťte hlavní třídu:
    ```sh
    java main.java
    ```

3. Postupujte podle pokynů na obrazovce pro správu skladu.


### Michal Klymov, semestrální práce - DSA.
#### VŠPJ, 2024 
