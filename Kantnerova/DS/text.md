# Jazyk SQL 

- neprocedualni            (Dotazovaci)
Nevytvari se methody     tvori se jedno radkove prikazy
                         kterymi vkladame menime nebo
                         ziskavame data.

>prikazy se deli na 4 zakladni skupiny:
>## 1) DDL (Data Definition Language)
>- tyto prikazy pracuji s objekty (Database,tabulka,procedura,index,etc. ....)
>ktere jsou ulozeny na strane serveru
>- Napr.: 
>
>                  CREATE (Vytvoreni objektu)
>                  ALTER (Zmena struktury objektu(Pridani sloupce))
>                  DROP (Smazani objektu vcetne vsech dat)
>## 2) DML (Data Manipulation Language) 
>- tyto prikazy pracuji s daty
>- A jsou to:
>
>                  INSERT INTO .... (Vlozeni dat)
>                  UPDATE .... SET ... (Zmena Dat)
>                  DELETE FROM ... (Smazani Dat)
>                  SELECT .... FROM ... (Vypis dat)
>
>## 3) DCL (Data Control Language)
>- tyto prikazy umoznuji pracovat s opravnenim
> a pravy pristupu k datum
>- a jsou to: 
>
>                  GRAND ... prideleni
>                  REVOKE ... odebrani
>
>## 4) TCL (Tranasaction Control Language)
>- umoznuji praci s transakcemi a zajistuji 'ACID'
>- Jsou to: 
>
>                  BEGIN TRANSACTION ... zacatek transakce
>                  COMMIT .... potvrzeni a konec transakce
>                  ROLBACK .... strorno ukonceni transakce

# Integritni omezeni 
- Entitní integritní omezení – *povinné integritní omezení, které zajišťuje úplnost primárního klíče tabulky*
- Referenční integritní omezení - *zabývají se vztahy dvou tabulek, kde jejich relace je určena vazbou primárního a cizího klíče*
- Doménová integritní omezení – *zajišťují dodržování datových typů/domén definovaných u sloupců databázové tabulky*
  
# Normalizace a normalni formy
1) NF
   - všechny sloupce tabulky nelze dále dělit na části nesoucí nějakou informaci -> prvky musí být atomické
   - jeden sloupec neobsahuje složené hodnoty.
2) NF
   - tabulka obsahuje pouze sloupce, které jsou závislé na celém klíči
   - zajistime pomoci jednoducheho primarniho klice
3) NF
   - tabulka je ve třetí normální formě, pokud neexistují žádné závislosti mezi neklíčovými sloupci

**[Neco navic](http://www.databaze.chytrak.cz/)**

### Self reference 
- Odkaz tabulky sama na sebe
1) **1:N** hiearchisky vztah (Podrizeny nadrizeny)
    
            Zamestnanec
    
            PK:id
    
            prijmeni
    
            jmeno
    
            FK:vedouci_id
