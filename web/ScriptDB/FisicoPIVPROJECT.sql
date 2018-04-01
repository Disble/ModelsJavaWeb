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

-- Roles
-- 1 = admin
-- 0 = Usuario
INSERT INTO USUARIO VALUES('admin','admin', 1);
INSERT INTO USUARIO VALUES('user', 'user', 0);

SELECT * FROM USUARIO;

SELECT rolU FROM USUARIO WHERE nombreU='admin' AND passU='admin';

SELECT * FROM USUARIO WHERE idU=1;

INSERT INTO USUARIO(nombreU, passU, rolU) VALUES();
