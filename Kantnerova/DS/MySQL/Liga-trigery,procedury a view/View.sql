CREATE VIEW VsechnyInformace AS
SELECT
    Liga.hrac.Jmeno,
    Liga.hrac.Prijmeni,
    Liga.post.nazev AS post,
    Liga.tym.nazev AS tym
FROM
    hrac
    INNER JOIN tym ON tym.idtym = hrac.tym_idtym
    INNER JOIN post ON post.idpost = hrac.post_idpost
ORDER BY
    Liga.tym.nazev,
    Liga.hrac.Jmeno,
    Liga.hrac.Prijmeni,
    Liga.post.nazev;

CREATE VIEW PoctyHracuNaPostu AS
SELECT
    count(*) AS pocet,
    Liga.tym.nazev AS tym,
    Liga.post.nazev AS post
FROM
    hrac
    INNER JOIN tym ON tym.idtym = hrac.tym_idtym
    INNER JOIN post ON post.idpost = hrac.post_idpost
GROUP BY
    Liga.tym.idtym,
    Liga.post.idpost;

CREATE VIEW Prestupy AS
SELECT
    *
FROM
    zmeny_hraci
WHERE
    typ = 'přestup';