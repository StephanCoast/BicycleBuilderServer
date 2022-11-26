USE `bbdb`;

INSERT INTO `benutzer` (`id`, `name`, `email`, `passwort_hash`, `vorname`, `nachname`, `rolle`) VALUES
(1, 'Alice', 'alice@example.com', '$2a$10$ugcTN6seHHGoWtOw5FOktevmyCk4bZitKljRZ6JtJ6mX9qMfJumNm', 'Alice', 'DSL', 'ADMIN'),
(2, 'Bob', 'bob@example.com', '$2a$10$ugcTN6seHHGoWtOw5FOktevmyCk4bZitKljRZ6JtJ6mX9qMfJumNm', 'Bob', 'Baumeister', 'BENUTZER');

INSERT INTO `artikeltyp` (`id`, `name`) VALUES
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
