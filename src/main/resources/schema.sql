CREATE TABLE credenziali (
                             id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                             username VARCHAR(255) NOT NULL,
                             password VARCHAR(255) NOT NULL,
                             ruolo VARCHAR(50) NOT NULL
);

CREATE TABLE scrittore (
                           id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                           nome VARCHAR(255) NOT NULL,
                           cognome VARCHAR(255) NOT NULL,
                           data_nascita DATE,
                           voto_totale INT DEFAULT 0,
                           credenziali_id BIGINT UNIQUE,
                           FOREIGN KEY (credenziali_id) REFERENCES credenziali(id)
);

CREATE TABLE utente (
                        id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                        nome VARCHAR(255) NOT NULL,
                        cognome VARCHAR(255) NOT NULL,
                        data_nascita DATE,
                        credenziali_id BIGINT UNIQUE,
                        FOREIGN KEY (credenziali_id) REFERENCES credenziali(id)
);

CREATE TABLE libro (
                       id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                       titolo VARCHAR(255) NOT NULL,
                       testo TEXT NOT NULL,
                       voto INT NOT NULL,
                       scrittore_id BIGINT,
                       FOREIGN KEY (scrittore_id) REFERENCES scrittore(id)
);

CREATE TABLE votazione (
                           id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                           mittente_id BIGINT,
                           destinatario_id BIGINT,
                           libro_id BIGINT,
                           voto INT NOT NULL,
                           FOREIGN KEY (destinatario_id) REFERENCES scrittore(id),
                           FOREIGN KEY (libro_id) REFERENCES libro(id)
);