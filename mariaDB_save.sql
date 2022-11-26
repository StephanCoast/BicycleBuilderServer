USE `bbdb`;

INSERT INTO `benutzer` (`id`, `name`, `email`, `passwort_hash`, `vorname`, `nachname`, `rolle`) VALUES
(1, 'Alice', 'alice@example.com', '$2a$10$ugcTN6seHHGoWtOw5FOktevmyCk4bZitKljRZ6JtJ6mX9qMfJumNm', 'Alice', 'DSL', 'ADMIN'),
(2, 'Bob', 'bob@example.com', '$2a$10$ugcTN6seHHGoWtOw5FOktevmyCk4bZitKljRZ6JtJ6mX9qMfJumNm', 'Bob', 'Baumeister', 'BENUTZER');

INSERT INTO `typ` (`id`, `name`) VALUES
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

INSERT INTO `datentyp` (`id`, `name`) VALUES
(1, 'STRING'),
(2, 'INTEGER'),
(3, 'FLOAT');

INSERT INTO `vergleichstyp` (`id`, `name`) VALUES
(1, 'MIN'),
(2, 'MAX'),
(3, 'GLEICH');

INSERT INTO `typ_eigenschaft_schnittstelle` (`id`, `name`, `wert`, `datentyp_id`, `typ_id`, `vergleichstyp_id`) VALUES
(1, 'lenkerklemmungDurchmesserMm', 9, '25.9', '3', ),
(2, 'lenkerklemmungDurchmesserMm', 8),
(3, 'gabelschaftdurchmesserMm', 8),
(4, 'vorbautyp', 8),
(5, 'gabelschaftdurchmesserMm', 7),
(6, 'reifenbreiteMm', 7),
(7, 'bremsscheibeDurchmesserMm', 7),
(8, 'gabelschaftdurchmesserMm', 6),
(9, 'steuersatzdurchmesserMm', 6),
(10, 'reifenbreiteMm', 11),
(11, 'reifendurchmesserMm', 11),
(12, 'einbaubreiteMm', 10),
(13, 'reifendurchmesserMm', 10),
(14, 'reifenbreiteMm', 10),
(15, 'bremsentyp', 12),
(16, 'bremsscheibeDurchmesserMm', 12),
(17, 'bremsentyp', 1),
(18, 'schaltungstyp', 1),
(19, 'reifendurchmesserMm', 1),
(20, 'innenlagerTyp', 1),
(21, 'innenlagerDurchmesserMm', 1),
(22, 'steuersatzdurchmesserMm', 1),
(23, 'sattelstützeDurchmesserMm', 1),
(24, 'schaltungstyp', 5),
(25, 'innenlagerDurchmesserMm', 2),
(26, 'innenlagerTyp', 2),
(27, 'innenlagerWellentyp', 2),
(28, 'innenlagerWellentyp', 3),
(29, 'Pedalgewindetyp', 3),
(30, 'Pedalgewindetyp', 4);