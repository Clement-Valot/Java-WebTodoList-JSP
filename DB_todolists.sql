DROP DATABASE if Exists todolists;
CREATE DATABASE todolists;

drop table if exists todolists.todos;
drop table if exists todolists.user;

CREATE TABLE todolists.user(
  id_user INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NULL,
  first_name VARCHAR(45) NULL,
  last_name VARCHAR(45) NULL,
  password VARCHAR(45) NULL,
  instructor BOOLEAN);

CREATE TABLE todolists.todos (
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  description VARCHAR(300) NULL,
  id_creator INT NOT NULL,
  id_user INT NOT NULL,
  CONSTRAINT fk_user FOREIGN KEY(id_user) REFERENCES todolists.user(id_user) ON DELETE CASCADE);
  
INSERT INTO todolists.user (`username`,`first_name`,`last_name`,`password`,`instructor`) VALUES ('nada.nahle@ext.devinci.fr','Nada','Nahle','NN',true);
INSERT INTO todolists.user (`username`,`first_name`,`last_name`,`password`,`instructor`) VALUES ('clement.valot@edu.devinci.fr','Clement','Valot','CV',true);
INSERT INTO todolists.user (`username`,`first_name`,`last_name`,`password`,`instructor`) VALUES ('clement.mutez@edu.devinci.fr','Clement','Mutez','CM',false);
INSERT INTO todolists.user (`username`,`first_name`,`last_name`,`password`,`instructor`) VALUES ('remi.guillon-bony@edu.devinci.fr','Rémi','Guillon-Bony','RGB',false);
INSERT INTO todolists.user (`username`,`first_name`,`last_name`,`password`,`instructor`) VALUES ('vincent.poupet@edu.devinci.fr','Vincent','Poupet','VP',false);
INSERT INTO todolists.user (`username`,`first_name`,`last_name`,`password`,`instructor`) VALUES ('a','b','c','d',false);
Select * from todolists.user;

INSERT INTO todolists.todos (`description`,`id_creator`,`id_user`) VALUES ('faire le DM de Java',1,2);
INSERT INTO todolists.todos (`description`,`id_creator`,`id_user`) VALUES ('Ameliorer le DM de Java',1,5);
INSERT INTO todolists.todos (`description`,`id_creator`,`id_user`) VALUES ('Profiter des vacances',2,3);
INSERT INTO todolists.todos (`description`,`id_creator`,`id_user`) VALUES ('Profiter des vacances',2,5);
INSERT INTO todolists.todos (`description`,`id_creator`,`id_user`) VALUES ('faire le DM de Java',1,3);
INSERT INTO todolists.todos (`description`,`id_creator`,`id_user`) VALUES ('faire le DM de Java',1,4);
INSERT INTO todolists.todos (`description`,`id_creator`,`id_user`) VALUES ('faire le DM de Java',1,5);
INSERT INTO todolists.todos (`description`,`id_creator`,`id_user`) VALUES ('Ameliorer le DM de Java',1,3);
select * from todolists.todos;

SET @@global.time_zone = '+00:00';
SET @@session.time_zone = '+00:00';
SELECT @@global.time_zone, @@session.time_zone;
