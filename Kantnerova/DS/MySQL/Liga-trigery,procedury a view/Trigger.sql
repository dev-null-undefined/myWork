DELIMITER $$
CREATE TRIGGER `hrac_before_delete`
BEFORE
DELETE 
ON `hrac` 
FOR EACH ROW
BEGIN
INSERT INTO
    zmeny_hraci (typ,datum,nazev_tymu,nazev_postu,jmeno,prijmeni)
VALUES
    ('odchod',
    NOW(),
    (select tym.nazev from tym where tym.idtym=OLD.tym_idtym),
    (select post.nazev from post where post.idpost=OLD.post_idpost),
    OLD.Jmeno,
    OLD.Prijmeni);
END$$ 
CREATE TRIGGER `hrac_after_insert`
AFTER
INSERT  
ON `hrac` 
FOR EACH ROW
BEGIN
INSERT INTO
    zmeny_hraci (typ,datum,nazev_tymu,nazev_postu,jmeno,prijmeni)
VALUES
    ('prichod',
    NOW(),
    (select tym.nazev from tym where tym.idtym=NEW.tym_idtym),
    (select post.nazev from post where post.idpost=NEW.post_idpost),
    NEW.Jmeno,
    NEW.Prijmeni);
END$$ 
CREATE TRIGGER `hrac_after_update`
AFTER
UPDATE  
ON `hrac` 
FOR EACH ROW
BEGIN
    IF(OLD.tym_idtym<>NEW.tym_idtym) THEN
        INSERT INTO
            zmeny_hraci (typ,datum,nazev_tymu,nazev_postu,jmeno,prijmeni)
        VALUES
            ('přestup',
            NOW(),
            (select tym.nazev from tym where tym.idtym=NEW.tym_idtym),
            (select post.nazev from post where post.idpost=NEW.post_idpost),
            NEW.Jmeno,
            NEW.Prijmeni);
    END IF;
    IF(OLD.post_idpost<>NEW.post_idpost) THEN
        INSERT INTO
            zmeny_hraci (typ,datum,nazev_tymu,nazev_postu,jmeno,prijmeni)
        VALUES
            ('zmena_postu',
            NOW(),
            (select tym.nazev from tym where tym.idtym=NEW.tym_idtym),
            (select post.nazev from post where post.idpost=NEW.post_idpost),
            NEW.Jmeno,
            NEW.Prijmeni);
    END IF;
END$$ 
DELIMITER ;