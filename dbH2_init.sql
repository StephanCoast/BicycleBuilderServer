INSERT INTO `USER_ROLE` (`ID`, `NAME`) VALUES
(1, 'ADMIN'),
(2, 'CONSULTANT');

INSERT INTO `USER` (`ID`, `NAME`, `EMAIL`, `PASSWORD_HASH`, `FORENAME`, `LASTNAME`, `USER_ROLE_ID`) VALUES
(1, 'Alice', 'alice@example.com', '$2a$10$ugcTN6seHHGoWtOw5FOktevmyCk4bZitKljRZ6JtJ6mX9qMfJumNm', 'Alice', 'DSL', 1),
(2, 'Bob', 'bob@example.com', '$2a$10$ugcTN6seHHGoWtOw5FOktevmyCk4bZitKljRZ6JtJ6mX9qMfJumNm', 'Bob', 'Baumeister', 2);

INSERT INTO `ARTICLE_TYPE` (`ID`, `NAME`) VALUES
(1, 'Rahmen'),
(2, 'Innenlager'),
(3, 'Kurbel'),
(4, 'Pedale'),
(5, 'Schaltung'),
(6, 'Steuersatz'),
(7, 'Gabel'),
(8, 'Vorbau'),
(9, 'Lenker'),
(10, 'Laufrad'),
(11, 'Reifen'),
(12, 'Bremse'),
(13, 'Zubehör');

INSERT INTO `CONFIGURATION_STATUS` (`ID`, `NAME`) VALUES
(1, 'ENTWURF'),
(2, 'ABGESCHLOSSEN'),
(3, 'EINKAUF'),
(4, 'STORNO');
