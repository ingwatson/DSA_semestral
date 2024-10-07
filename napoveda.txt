# Návod na použití programu pro správu skladu zboží

Tento program umožňuje správu skladu zboží pomocí různých funkcí. Níže jsou uvedeny jednotlivé operace, které můžete provést v programu, a jejich popis.

## 1. Přidat nebo aktualizovat zboží
Tato volba umožňuje přidat nové zboží do skladu nebo aktualizovat již existující zboží.

### Postup:
1. Po výběru této operace budete vyzváni k zadání **ID zboží** (unikátní číselný identifikátor).
2. Poté zadejte **název zboží**.
3. Zadejte **cenu zboží** (v měně, kterou používáte).
4. Nakonec zadejte **počet kusů** (množství zboží na skladě).

Pokud zboží s daným ID již existuje, program automaticky aktualizuje jeho údaje. Pokud neexistuje, bude přidáno jako nové zboží.

## 2. Aktualizovat počet kusů zboží podle ID
Tato volba umožňuje změnit množství zboží na skladě.

### Postup:
1. Zadejte **ID zboží**, které chcete upravit.
2. Poté zadejte nový **počet kusů**.
    - Pokud zadáte hodnotu menší nebo rovnou nule, zboží bude automaticky odstraněno ze skladu.

## 3. Vypsat zboží podle ID
Tato volba umožňuje zobrazit detaily konkrétního zboží podle jeho **ID**.

### Postup:
1. Zadejte **ID zboží**, které chcete zobrazit.

Pokud zboží s tímto ID existuje, zobrazí se jeho detaily (název, cena, počet kusů). Pokud neexistuje, budete o tom informováni.

## 4. Zrušit zboží podle ID
Tato volba slouží k odstranění zboží ze skladu.

### Postup:
1. Zadejte **ID zboží**, které chcete odstranit.

Pokud zboží s tímto ID existuje, bude odstraněno. Pokud ne, program vás informuje, že takové zboží nebylo nalezeno.

## 5. Vypsat všechna zboží seřazená podle ID
Tato volba zobrazí veškeré zboží na skladě, seřazené podle **ID**. U každého zboží se zobrazí jeho název, cena a počet kusů.

### Postup:
Stačí zvolit tuto operaci a program automaticky zobrazí všechna zboží.

## 6. Spočítat celkovou cenu zboží
Tato volba spočítá a zobrazí celkovou hodnotu všech zboží na skladě.

### Postup:
Stačí zvolit tuto operaci a program automaticky spočítá celkovou cenu zboží na skladě.

## 7. Importovat data z CSV/TXT souboru
Tato volba umožňuje hromadný import zboží ze souboru typu CSV nebo TXT.

### Postup:
1. Zadejte **cestu k souboru** (včetně jeho názvu), odkud chcete importovat.
2. Program načte zboží z daného souboru a přidá je do skladu.

## 8. Exportovat data do CSV/TXT souboru
Tato volba slouží k exportu dat o zboží do souboru ve formátu CSV nebo TXT.

### Postup:
1. Zadejte **název souboru**, do kterého chcete exportovat.
2. Zvolte formát souboru (**csv** nebo **txt**).
3. Data o zboží budou uložena do souboru ve zvolené složce.

## 9. Zobrazit nápovědu
Tato volba zobrazí tento návod na použití programu.

## 10. Konec
Tato volba ukončí program.

### Postup:
Stačí vybrat tuto volbu a program se automaticky ukončí.
