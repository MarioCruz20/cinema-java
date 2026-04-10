CREATE DATABASE Cinema_Star;

USE Cinema_Star;

CREATE TABLE Contenido (
    contenidoID INT PRIMARY KEY IDENTITY(1,1),
    titulo VARCHAR(100),
    genero VARCHAR(50),
    duracion INT,
    tipo VARCHAR(20),
    popularidad FLOAT
);

CREATE TABLE Pelicula (
    contenidoID INT PRIMARY KEY,
    director VARCHAR(100),

    FOREIGN KEY (contenidoID) REFERENCES Contenido(contenidoID)
);

CREATE TABLE Documental (
    contenidoID INT PRIMARY KEY,
    director VARCHAR(100),
    narrador VARCHAR(100),

    FOREIGN KEY (contenidoID) REFERENCES Contenido(contenidoID)
);

--Inserts Contenido--
INSERT INTO Contenido (titulo, genero, duracion, tipo, popularidad)
VALUES ('Inception', 'Sci-Fi', 148, 'Pelicula', 9.5);

INSERT INTO Contenido (titulo, genero, duracion, tipo, popularidad)
VALUES ('Planet Earth', 'Naturaleza', 60, 'Documental', 9.8);

INSERT INTO Contenido (titulo, genero, duracion, tipo, popularidad)
VALUES ('Shrek', 'Animada', 200, 'Pelicula', 9.0);

INSERT INTO Contenido (titulo, genero, duracion, tipo, popularidad)
VALUES ('Titanic', 'Drama', 228, 'Pelicula', 7.5);

INSERT INTO Contenido (titulo, genero, duracion, tipo, popularidad)
VALUES ('John Wick', 'Acción', 158, 'Pelicula', 9.5);

INSERT INTO Contenido (titulo, genero, duracion, tipo, popularidad)
VALUES ('El Conjuro', 'Terror', 118, 'Pelicula', 6.9);

INSERT INTO Contenido (titulo, genero, duracion, tipo, popularidad)
VALUES ('Interstellar', 'Sci-Fi', 169, 'Pelicula', 8.1);

INSERT INTO Contenido (titulo, genero, duracion, tipo, popularidad)
VALUES ('Joker', 'Drama', 159, 'Pelicula', 7.8);

INSERT INTO Contenido (titulo, genero, duracion, tipo, popularidad)
VALUES ('Toy Story', 'Animada', 191, 'Pelicula', 7.4);

--Inserts Pelicula--

INSERT INTO Pelicula (contenidoID, director)
VALUES (1, 'Christopher Nolan');

--Inserts Documental--
INSERT INTO Documental (contenidoID, director, narrador)
VALUES (2, 'Alastair Fothergill', 'David Attenborough');

INSERT INTO Documental (contenidoID, director, narrador)
VALUES (3, 'Michael Moore', 'John Doe');

SELECT * FROM  Contenido;
SELECT * FROM Pelicula;
SELECT * FROM Documental;
