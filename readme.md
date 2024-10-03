Zde je `readme.md` pro váš projekt:

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

## Použití

1. Zkompilujte Java soubory:
    ```sh
    javac src/*.java
    ```

2. Spusťte hlavní třídu:
    ```sh
    java -cp src Main
    ```

3. Postupujte podle pokynů na obrazovce pro správu skladu.

## Licence

Tento projekt je licencován pod licencí MIT. Podrobnosti naleznete v souboru `LICENSE`.