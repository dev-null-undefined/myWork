DELIMITER $$
CREATE PROCEDURE PridejHrace(
    IN nazev_tym varchar(45),
    IN prijmeni varchar(45),
    IN jmeno varchar(45),
    IN vek int(11)
) 
BEGIN 
    DECLARE idTym int(11) default 0;
    set idTym=(select tym.idtym from tym where tym.nazev=nazev_tym);
    IF (idTym IS NULL ) THEN
        set idTym=(select max(tym.idtym)+1 from tym);
        insert into tym VALUES (idTym,nazev_tym);
    END IF;
    INSERT INTO hrac (Jmeno,Prijmeni,Vek,tym_idtym) VALUES (jmeno,prijmeni,vek,idTym);
END$$
CREATE PROCEDURE ZmenPost(
    IN nazev_tym varchar(45),
    IN prijmeni varchar(45),
    IN jmeno varchar(45),
    IN nazev_postu varchar(45)
) 
BEGIN 
    DECLARE idHrac int(11) default 0;
    DECLARE idPost int(11) default 0;
    set idHrac=(select hrac.idhrac from hrac inner join tym on tym_idtym=tym.idtym
                where tym.nazev=nazev_tym and hrac.Jmeno=jmeno and hrac.Prijmeni=prijmeni);
    IF (idHrac IS NOT NULL ) THEN
        set idPost=(select post.idpost from post where post.nazev=nazev_postu);
        IF (idPost IS NULL ) THEN
             set idPost=(select max(post.idpost)+1 from post);
            insert into post VALUES (idPost,nazev_postu);
        END IF;
        UPDATE hrac SET post_idpost=idPost where hrac.idhrac=idHrac;
    END IF;
END$$
CREATE PROCEDURE ZmenTym(
    IN nazev_tym_new varchar(45),
    IN nazev_tym_old varchar(45),
    IN prijmeni varchar(45),
    IN jmeno varchar(45)
) 
BEGIN 
    DECLARE idHrac int(11) default 0;
    DECLARE idTym int(11) default 0;
    set idHrac=(select hrac.idhrac from hrac inner join tym on tym_idtym=tym.idtym
                where tym.nazev=nazev_tym_old and hrac.Jmeno=jmeno and hrac.Prijmeni=prijmeni);
    IF (idHrac IS NOT NULL ) THEN
        set idTym=(select tym.idtym from tym where tym.nazev=nazev_tym_new);
        IF (idTym IS NULL ) THEN
            set idTym=(select max(tym.idtym)+1 from tym);
            insert into tym VALUES (idTym,nazev_tym_new);
        END IF;
        UPDATE hrac SET tym_idtym=idTym where hrac.idhrac=idHrac;
    END IF;
END$$

DELIMITER ;