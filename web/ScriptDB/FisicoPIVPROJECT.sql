CREATE DATABASE PIVPROJECT;
GO

USE PIVPROJECT;
GO

CREATE TABLE USUARIO(
	idU INT PRIMARY KEY IDENTITY (1,1),
	nombreU VARCHAR(20),
	passU VARCHAR(30),
	rolU INT
);

CREATE TABLE FIGURASGEO(
	idFG INT PRIMARY KEY IDENTITY (1,1),
	idU INT REFERENCES USUARIO(idU),
);

CREATE TABLE CUADRADO(
	idCuak INT PRIMARY KEY IDENTITY (1,1),
	idFG INT REFERENCES FIGURASGEO(idFG),
        coorX INT,
	coorY INT,
	lado FLOAT,
	area FLOAT,
	perimetro FLOAT,
	mover INT,
	coorXM INT,
	coorYM INT
);

CREATE TABLE RECTANGULO(
	idRect INT PRIMARY KEY IDENTITY(1,1),
	idFG INT REFERENCES FIGURASGEO(idFG),
        coorX INT,
	coorY INT,
	lado1 FLOAT,
	lado2 FLOAT,
	area FLOAT,
	perimetro FLOAT,
	mover INT,
	coorXM INT,
	coorYM INT
);

CREATE TABLE CIRCUNFERENCIA(
	idCir INT PRIMARY KEY IDENTITY(1,1),
	idFG INT REFERENCES FIGURASGEO(idFG),
	centroX INT,
	centroY INT,
	radio FLOAT,
	area FLOAT,
	perimetro FLOAT,
	mover INT,
	coorXM INT,
	coorYM INT
);

CREATE TABLE ELIPSE(
	idElip INT PRIMARY KEY IDENTITY(1,1),
	idFG INT REFERENCES FIGURASGEO(idFG),
	centroX INT,
	centroY INT,
	horizontal FLOAT,
	vertical FLOAT,
	area FLOAT,
	perimetro FLOAT,
	mover INT,
	coorXM INT,
	coorYM INT
);

CREATE TABLE TRIANGULOREC(
	idTri INT PRIMARY KEY IDENTITY(1,1),
	idFG INT REFERENCES FIGURASGEO(idFG),
        coorX INT,
	coorY INT,
	base FLOAT,
	altura FLOAT,
	area FLOAT,
	perimetro FLOAT,
	mover INT,
	coorXM INT,
	coorYM INT
);

CREATE TABLE LINEA(
	idLin INT PRIMARY KEY IDENTITY (1,1),
	idFG INT REFERENCES FIGURASGEO(idFG),
	coorX1 INT,
	coorY1 INT,
	coorX2 INT,
	coorY2 INT,
	longitud FLOAT,
	mover INT,
	coorXM INT,
	coorYM INT
);

-- imaginario
-- 0 = No es imaginario
-- 1 = Es imaginario
CREATE TABLE ECUACIONES2GRADO(
	idEcu INT PRIMARY KEY IDENTITY(1,1),
	idU INT REFERENCES USUARIO(idU),
	a INT,
	b INT,
	c INT,
	raizP FLOAT,
	raizN FLOAT,
	imaginario INT,
	discriminante FLOAT
);

CREATE TABLE CMAGICO(
	idCM INT PRIMARY KEY IDENTITY(1,1),
	idU INT REFERENCES USUARIO(idU),
	tam INT,
	valores TEXT
);


-- Roles
-- 1 = admin
-- 0 = Usuario
INSERT INTO USUARIO VALUES('admin','admin', 1)
INSERT INTO USUARIO VALUES('user', 'user', 0)

SELECT * FROM USUARIO;
SELECT * FROM ECUACIONES2GRADO;
SELECT * FROM CUADRADO;
SELECT * FROM RECTANGULO;
SELECT * FROM CIRCUNFERENCIA
SELECT * FROM ELIPSE;
SELECT * FROM TRIANGULOREC;
SELECT * FROM LINEA;

SELECT rolU FROM USUARIO WHERE nombreU='admin' AND passU='admin'

SELECT * FROM USUARIO WHERE idU=1

INSERT INTO USUARIO(nombreU, passU, rolU) VALUES()
INSERT INTO ECUACIONES2GRADO(idU, imaginario, discriminante) VALUES(2,1,-123);
INSERT INTO CUADRADO(idFG, lado, area, perimetro, mover) VALUES()
INSERT INTO RECTANGULO(idFG, coorX, coorY, lado1, lado2, area, perimetro, mover, coorXM, coorYM) VALUES()
INSERT INTO TRIANGULOREC(idFG, coorX, coorY, base, altura, area, perimetro, mover, coorXM, coorYM) VALUES()
INSERT INTO LINEA(idFG, coorX1, coorY1, coorX2, coorY2, longitud, mover, coorXM, coorYM) VALUES()
