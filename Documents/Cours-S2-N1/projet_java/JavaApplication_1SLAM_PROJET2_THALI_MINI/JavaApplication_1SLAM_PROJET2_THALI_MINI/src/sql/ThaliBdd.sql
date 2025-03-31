DROP DATABASE Thali;
-- /**
--  * BDD Thali
--  * avril 2023
--  */
CREATE DATABASE Thali DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE Thali;

CREATE TABLE Etape (
  Id INTEGER PRIMARY KEY AUTO_INCREMENT, 
  CodeExcursion CHAR(3),
  NumEtape INTEGER,
  Description VARCHAR(50),
  DureePrevue INTEGER
);

INSERT INTO Etape VALUES (1, 'E01', 1, 'traversée aller', 30);
INSERT INTO Etape VALUES (2, 'E01', 2, 'promenade dans l''île', 60);
INSERT INTO Etape VALUES (3, 'E01', 3, 'visite de la chapelle', 20);
INSERT INTO Etape VALUES (4, 'E01', 4, 'visite du phare', 30 );
INSERT INTO Etape VALUES (5, 'E01', 5, 'promenade sur une petite crique', 15);
INSERT INTO Etape VALUES (6, 'E01', 6, 'visite du jardin exotique', 45);
INSERT INTO Etape VALUES (7, 'E01', 7, 'traversée retour',  30);
INSERT INTO Etape VALUES (8, 'E02', 1, 'Trajet aller en car',  45);
INSERT INTO Etape VALUES (9, 'E02', 2, 'Randonnée : aller au cap par les bois', 140);
INSERT INTO Etape VALUES (10, 'E02', 3, 'Pique-nique', 60);
INSERT INTO Etape VALUES (11, 'E02', 4, 'Randonnée : retour du cap par la lande', 120);
INSERT INTO Etape VALUES (12, 'E02', 5, 'Trajet retour en car',  45);

CREATE TABLE Excursion (
  Code CHAR(3),
  Libelle VARCHAR(50),
  NbPlaces INTEGER,
  Tarif DECIMAL(5,2),  
  PRIMARY KEY (Code)
);

INSERT INTO Excursion VALUES ('E01', 'Excursion dans l''île', 8, 0);
INSERT INTO Excursion VALUES ('E02', 'Randonnée pédestre au "Cap des vents"', 35, 25.75);


ALTER TABLE Etape 
    ADD CONSTRAINT FK_ETAPE_EXCURSION
    FOREIGN KEY (CodeExcursion) REFERENCES Excursion (Code) ON DELETE CASCADE;
ALTER TABLE Etape 
    ADD CONSTRAINT UNIQUE (CodeExcursion, NumEtape);