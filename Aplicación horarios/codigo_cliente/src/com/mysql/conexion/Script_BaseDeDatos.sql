CREATE DATABASE PROYECTO_ARQUITECTURA;
USE PROYECTO_ARQUITECTURA;

CREATE TABLE Usuario (ID_usuario int PRIMARY KEY auto_increment,
					  nombre varchar(15) NOT NULL,
					  correo varchar(25) NOT NULL,
					  institucion varchar(15) );
alter table Usuario auto_increment=1000;
                      
CREATE TABLE Perfil (ID_perfil int PRIMARY KEY auto_increment,
					 ID_usuario int NOT NULL,
                     nombre_usuario varchar(15) NOT NULL,
                     contrasena varchar(15) NOT NULL,
					 CONSTRAINT perfil_id_usuario_fk FOREIGN KEY (ID_usuario) REFERENCES Usuario(ID_usuario) );
alter table Perfil auto_increment=2000;
                
CREATE TABLE Materia (ID_materia int PRIMARY KEY auto_increment,
					  ID_usuario int NOT NULL,
					  nombre_materia varchar(15) NOT NULL,
					  semestre varchar(25) NOT NULL,
					  CONSTRAINT materia_id_usuario_fk FOREIGN KEY (ID_usuario) REFERENCES Usuario(ID_usuario));
alter table Materia auto_increment=3000;
                      
CREATE TABLE Grupo (ID_grupo int PRIMARY KEY auto_increment,
					ID_materia int NOT NULL,
				    numero_grupo numeric(2) NOT NULL,
                    duracion_clase numeric(2) NOT NULL,
                    dias varchar(20) NOT NULL,
                    nombre_profesor varchar(15),
                    link varchar(50),
                    salon varchar(5),
                    CONSTRAINT grupo_id_materia_fk FOREIGN KEY (ID_materia) REFERENCES Materia(ID_materia) ON DELETE CASCADE);
alter table Grupo auto_increment=4000;
                    
CREATE TABLE Horario (ID_horario int PRIMARY KEY auto_increment,
					  ID_usuario int NOT NULL,
					  ID_materia int NOT NULL,
					  fila numeric(1) NOT NULL,
                      columna numeric(1) NOT NULL,
                      CONSTRAINT horario_id_usuario_fk FOREIGN KEY (ID_usuario) REFERENCES Usuario(ID_usuario),
                      CONSTRAINT horario_id_materia_fk FOREIGN KEY (ID_materia) REFERENCES Materia(ID_materia));
alter table Horario auto_increment=5000;
                    
-- drop database PROYECTO_ARQUITECTURA;
-- select * from Perfil;
-- select * from Materia;
-- select * from Grupo;
-- select * from Horario;
                      
