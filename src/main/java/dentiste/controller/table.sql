CREATE TABLE dent(
    id SERIAL PRIMARY KEY ,
    code VARCHAR UNIQUE NOT NULL,
    nom VARCHAR NOT NULL
);

CREATE TABLE prestation (
    id SERIAL PRIMARY KEY ,
    nom VARCHAR
);

CREATE TABLE prixprestation(
    id SERIAL PRIMARY KEY ,
    codedent VARCHAR REFERENCES dent(code) ,
    idprestation INT REFERENCES prestation ,
    prix DOUBLE PRECISION
);

CREATE TABLE patient (
    id SERIAL PRIMARY KEY ,
    nom VARCHAR ,
    dtn DATE
);

CREATE TABLE patientEtatDent (
    id SERIAL PRIMARY KEY ,
    date DATE NOT NULL ,
    idpatient INT REFERENCES patient,
    codedent VARCHAR REFERENCES dent(code),
    etat INT CHECK ( etat >= 0 AND etat <= 10 )
);

CREATE TABLE priorisation (
    id SERIAL PRIMARY KEY ,x
    nom VARCHAR NOT NULL
);

CREATE TABLE compositionPriorisation (
    id SERIAL PRIMARY KEY,
    idpriorisation INT REFERENCES  priorisation,
    codedent VARCHAR REFERENCES dent(code) ,
    niveau INT NOT NULL
);

CREATE TABLE echelleDent (
    id SERIAL PRIMARY KEY ,
    min INT CHECK ( min >= 0 AND min<=10 ) ,
    max INT CHECK ( max >= 0 AND max<=10 ) ,
    idprestation INT REFERENCES prestation
);

CREATE VIEW v_patientEtatDentOrderByPriorite AS
SELECT
    ped.date,
    ped.idpatient,
    ped.codedent,
    ped.etat,
    cp.idpriorisation,
    cp.niveau
FROM patientEtatDent ped
    JOIN compositionPriorisation cp ON cp.codedent = ped.codedent
ORDER BY cp.niveau DESC;


CREATE VIEW v_prestationniveau AS
SELECT ed.idprestation, p.nom, ed.min, ed.max
FROM echelleDent ed, prestation p
WHERE
    p.id= ed.idprestation
ORDER BY ed.min;

CREATE VIEW v_prixprestation AS
SELECT
    vpn.idprestation,
    vpn.nom,
    vpn.min,
    vpn.max,
    pp.prix,
    d.code AS codedent
FROM v_prestationniveau vpn
    JOIN prixprestation pp ON vpn.idprestation = pp.idprestation
    JOIN dent d ON pp.codedent = d.code
ORDER BY
    CASE
        WHEN vpn.min = 1 THEN 1
        WHEN vpn.min = 0 THEN 2
        WHEN vpn.min = 4 THEN 3
        WHEN vpn.min = 8 THEN 4
        ELSE 5
    END;



INSERT INTO dent (code, nom) VALUES
    ('01', 'Incisive Centrale Superieure Gauche'),
    ('02', 'Incisive Laterale Superieure Gauche'),
    ('03', 'Canine Superieure Gauche'),
    ('04', 'Premiere Premolaire Superieure Gauche'),
    ('05', 'Deuxieme Premolaire Superieure Gauche'),
    ('06', 'Premiere Molaire Superieure Gauche'),
    ('07', 'Deuxieme Molaire Superieure Gauche'),
    ('08', 'Troisieme Molaire Superieure Gauche'),
    ('09', 'Quatrieme Molaire Superieure Gauche'),
    ('10', 'Incisive Centrale Superieure Droite'),
    ('11', 'Incisive Laterale Superieure Droite'),
    ('12', 'Canine Superieure Droite'),
    ('13', 'Premiere Premolaire Superieure Droite'),
    ('14', 'Deuxieme Premolaire Superieure Droite'),
    ('15', 'Premiere Molaire Superieure Droite'),
    ('16', 'Deuxieme Molaire Superieure Droite'),

    ('21', 'Incisive Centrale Inferieure Gauche'),
    ('22', 'Incisive Laterale Inferieure Gauche'),
    ('23', 'Canine Inferieure Gauche'),
    ('24', 'Premiere Premolaire Inferieure Gauche'),
    ('25', 'Deuxieme Premolaire Inferieure Gauche'),
    ('26', 'Premiere Molaire Inferieure Gauche'),
    ('27', 'Deuxieme Molaire Inferieure Gauche'),
    ('28', 'Troisieme Molaire Inferieure Gauche'),
    ('29', 'Quatrieme Molaire Inferieure Gauche '),
    ('30', 'Incisive Centrale Inferieure Droite'),
    ('31', 'Incisive Laterale Inferieure Droite'),
    ('32', 'Canine Inferieure Droite'),
    ('33', 'Premiere Premolaire Inferieure Droite'),
    ('34', 'Deuxieme Premolaire Inferieure Droite'),
    ('35', 'Premiere Molaire Inferieure Droite'),
    ('36', 'Deuxieme Molaire Inferieure Droite');


INSERT INTO prestation (nom) VALUES
    ('Ramplacement'),
    ('Grand reparation'),
    ('Reparation'),
    ('Netoyagge');



INSERT INTO prixprestation(codedent, idprestation, prix) VALUES
    ('01',1 , 100000),
    ('01',2 , 5000),
    ('01',3 , 2000),
    ('01',4 , 1000),

    ('02',1 , 100000),
    ('02',2 , 5000),
    ('02',3 , 2000),
    ('02',4 , 1000),

    ('03',1 , 100000),
    ('03',2 , 5000),
    ('03',3 , 2000),
    ('03',4 , 1000),

    ('04',1 , 100000),
    ('04',2 , 5000),
    ('04',3 , 2000),
    ('04',4 , 1000),

    ('05',1 , 100000),
    ('05',2 , 5000),
    ('05',3 , 2000),
    ('05',4 , 1000),

    ('06',1 , 100000),
    ('06',2 , 5000),
    ('06',3 , 2000),
    ('06',4 , 1000),

    ('07',1 , 100000),
    ('07',2 , 5000),
    ('07',3 , 2000),
    ('07',4 , 1000),

    ('08',1 , 100000),
    ('08',2 , 5000),
    ('08',3 , 2000),
    ('08',4 , 1000),

    ('09',1 , 100000),
    ('09',2 , 5000),
    ('09',3 , 2000),
    ('09',4 , 1000),

    ('10',1 , 100000),
    ('10',2 , 5000),
    ('10',3 , 2000),
    ('10',4 , 1000),

    ('11',1 , 100000),
    ('11',2 , 5000),
    ('11',3 , 2000),
    ('11',4 , 1000),

    ('12',1 , 100000),
    ('12',2 , 5000),
    ('12',3 , 2000),
    ('12',4 , 1000),

    ('13',1 , 100000),
    ('13',2 , 5000),
    ('13',3 , 2000),
    ('13',4 , 1000),

    ('14',1 , 100000),
    ('14',2 , 5000),
    ('14',3 , 2000),
    ('14',4 , 1000),

    ('15',1 , 100000),
    ('15',2 , 5000),
    ('15',3 , 2000),
    ('15',4 , 1000),


('16',1 , 100000),
    ('16',2 , 5000),
    ('16',3 , 2000),
    ('16',4 , 1000),

    ('21',1 , 100000),
    ('21',2 , 5000),
    ('21',3 , 2000),
    ('21',4 , 1000),

    ('22',1 , 100000),
    ('22',2 , 5000),
    ('22',3 , 2000),
    ('22',4 , 1000),


('23',1 , 100000),
    ('23',2 , 5000),
    ('23',3 , 2000),
    ('23',4 , 1000),

    ('24',1 , 100000),
    ('24',2 , 5000),
    ('24',3 , 2000),
    ('24',4 , 1000),

    ('25',1 , 100000),
    ('25',2 , 5000),
    ('25',3 , 2000),
    ('25',4 , 1000),


('26',1 , 100000),
    ('26',2 , 5000),
    ('26',3 , 2000),
    ('26',4 , 1000),

    ('27',1 , 100000),
    ('27',2 , 5000),
    ('27',3 , 2000),
    ('27',4 , 1000),


('28',1 , 100000),
    ('28',2 , 5000),
    ('28',3 , 2000),
    ('28',4 , 1000),


('29',1 , 100000),
    ('29',2 , 5000),
    ('29',3 , 2000),
    ('29',4 , 1000),


('30',1 , 100000),
    ('30',2 , 5000),
    ('30',3 , 2000),
    ('30',4 , 1000),

    ('31',1 , 100000),
    ('31',2 , 5000),
    ('31',3 , 2000),
    ('31',4 , 1000),

    ('32',1 , 100000),
    ('32',2 , 5000),
    ('32',3 , 2000),
    ('32',4 , 1000),


('33',1 , 100000),
    ('33',2 , 5000),
    ('33',3 , 2000),
    ('33',4 , 1000),

    ('34',1 , 100000),
    ('34',2 , 5000),
    ('34',3 , 2000),
    ('34',4 , 1000),

    ('35',1 , 100000),
    ('35',2 , 5000),
    ('35',3 , 2000),
    ('35',4 , 1000),


('36',1 , 100000),
    ('36',2 , 5000),
    ('36',3 , 2000),
    ('36',4 , 1000);



INSERT INTO patient (nom, dtn) VALUES
    ('Bema', '1999-01-23'),
    ('Koto', '2005-07-15'),
    ('Rasoa', '2009-09-13');

INSERT INTO priorisation (nom) VALUES
    ('Beaute'),
    ('Sante');

INSERT INTO echelleDent (min, max, idprestation) VALUES
    (0, 0, 1),
    (1, 4, 2),
    (5, 7, 3),
    (8, 10, 4);

INSERT INTO compositionPriorisation(idpriorisation, codedent, niveau)
VALUES
    (1, '01', 100),
    (1, '02', 95),
    (1, '03', 75),
    (1, '04', 50),
    (1, '05', 50),
    (1, '06', 40),
    (1, '07', 40),
    (1, '08', 30),
    (1, '09', 30),
    (1, '10', 100),
    (1, '11', 95),
    (1, '12', 75),
    (1, '13', 50),
    (1, '14', 50),
    (1, '15', 40),
    (1, '16', 40),

    (1, '21', 100),
    (1, '22', 95),
    (1, '23', 75),
    (1, '24', 50),
    (1, '25', 50),
    (1, '26', 40),
    (1, '27', 40),
    (1, '28', 30),
    (1, '29', 30),
    (1, '30', 100),
    (1, '31', 95),
    (1, '32', 75),
    (1, '33', 50),
    (1, '34', 50),
    (1, '35', 40),
    (1, '36', 40),


    (2, '01', 10),
    (2, '02', 10),
    (2, '03', 20),
    (2, '04', 30),
    (2, '05', 30),
    (2, '06', 40),
    (2, '07', 40),
    (2, '08', 50),
    (2, '09', 50),
    (2, '10', 10),
    (2, '11', 10),
    (2, '12', 20),
    (2, '13', 30),
    (2, '14', 30),
    (2, '15', 40),
    (2, '16', 40),

    (2, '21', 10),
    (2, '22', 10),
    (2, '23', 20),
    (2, '24', 30),
    (2, '25', 30),
    (2, '26', 40),
    (2, '27', 40),
    (2, '28', 50),
    (2, '29', 50),
    (2, '30', 10),
    (2, '31', 10),
    (2, '32', 20),
    (2, '33', 30),
    (2, '34', 30),
    (2, '35', 40),
    (2, '36', 40);



