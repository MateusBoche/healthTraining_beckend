DROP TABLE IF EXISTS user_model;
DROP TABLE IF EXISTS question_model;

CREATE TABLE user_model
(
    id       SERIAL     not null,
    email    varchar(50) not null,
    nomeCompleto varchar(50) not null,
    senha varchar(50) not null,
    PRIMARY KEY (id)
);

CREATE TABLE question_model
(
    id       SERIAL     not null,
    question    varchar(50) not null,
    answer boolean not null,
    category varchar(50) not null,
    phase  int not null,
    PRIMARY KEY (id)
);
