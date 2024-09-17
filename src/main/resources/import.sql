INSERT INTO credenziali (username, password, ruolo) VALUES ('dnlsdn', '12', 'SCRITTORE');
INSERT INTO credenziali (username, password, ruolo) VALUES ('mario18', '12', 'SCRITTORE');
INSERT INTO credenziali (username, password, ruolo) VALUES ('annalisa98', '12', 'UTENTE');

-- UTENTE
INSERT INTO utente (nome, cognome, data_nascita, credenziali_id) VALUES ('Annalisa', 'Cardini', '2001-10-22', 3);

-- SCRITTORE
INSERT INTO scrittore (nome, cognome, data_nascita, voto_totale, credenziali_id) VALUES ('Daniel', 'Sadun', '2001-12-06', 0, 1);
INSERT INTO scrittore (nome, cognome, data_nascita, voto_totale, credenziali_id) VALUES ('Mario', 'Rossi', '2001-03-09', 7, 2);

-- Inserimento di dati nella tabella Libro
INSERT INTO libro (titolo, testo, voto, scrittore_id) VALUES ('Libro 1', 'Testo libro 1', 0, 1);
INSERT INTO libro (titolo, testo, voto, scrittore_id) VALUES ('Libro 2', 'Testo libro 2', 0, 1);
INSERT INTO libro (titolo, testo, voto, scrittore_id) VALUES ('Libro 1', 'Testo libro 1', 7, 2);

-- Inserimento di dati nella tabella Votazione
INSERT INTO votazione (mittente_id, destinatario_id, libro_id, voto) VALUES (3, 2, 3, 7);
