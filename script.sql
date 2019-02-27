create database studentdb;

CREATE TABLE `studentdb`.`student` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `email` VARCHAR(100) NULL,
  PRIMARY KEY (`id`));

INSERT INTO student VALUES (null,"Hong Duc","Ta","hong-duc.ta@devinci.fr");
select * from student;