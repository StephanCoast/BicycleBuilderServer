INSERT INTO `USER_ROLE` (`ID`, `NAME`) VALUES
(1, 'ADMIN'),
(2, 'CONSULTANT');

INSERT INTO `USER` (`ID`, `NAME`, `EMAIL`, `PASSWORD_HASH`, `FORENAME`, `LASTNAME`, `USER_ROLE_ID`) VALUES
(1, 'Admin123', 'admin123@bbuilder.com', '$2a$10$ugcTN6seHHGoWtOw5FOktevmyCk4bZitKljRZ6JtJ6mX9qMfJumNm', 'Anna', 'Admin', 1),
(2, 'Biker123', 'biker123@bbuilder.com', '$2a$10$ugcTN6seHHGoWtOw5FOktevmyCk4bZitKljRZ6JtJ6mX9qMfJumNm', 'Leo', 'Lenker', 2),
(3, 'Consultant', 'consultant@bbuilder.com', '$2a$10$hgjHj59HIO7dbtOXFjSv.u/lgX3SVsLlAIahoduRgIjCOxcJv4bLC', 'Caro', 'Consultant', 2);

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
