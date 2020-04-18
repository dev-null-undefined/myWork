CREATE TABLE `Kantnerova`.`Zamestnanec` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `Jmeno` VARCHAR (45) NOT NULL,
    `Prijmeni` VARCHAR (45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Kantnerova`.`Stroj` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `Nazev` VARCHAR (45) NOT NULL,
    `Cenna` INT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Kantnerova`.`Pouziti` (
    `id` INT NOT NULL,
    `Zamestnanec_id` INT NOT NULL,
    `Stroj_id` INT NOT NULL,
    `Datum` DATETIME NOT NULL,
    `Pocet_hodin` INT NULL DEFAULT 1,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`Zamestnanec_id`) REFERENCES `Kantnerova`.`Zamestnanec` (`id`),
    FOREIGN KEY (`Stroj_id`) REFERENCES `Kantnerova`.`Stroj` (`id`)
);

-- Vypište, kdy a kolik hodin pracovali zaměstnanci na jednotlivých strojích, setřiďte podle data a jména zaměstnance.
SELECT
    *
FROM
    Pouziti
    INNER JOIN Zamestnanec ON Pouziti.Zamestnanec_id = Zamestnanec.id
    INNER JOIN Stroj ON Pouziti.Stroj_id = Stroj.id
ORDER BY
    Datum,
    Jmeno;