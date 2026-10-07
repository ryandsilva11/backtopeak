CREATE TABLE guildas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

INSERT INTO
    herois (
        nome,
        classe,
        vida,
        pontos_de_ataque,
        guilda_id
    )
VALUES (
        'Lancelot',
        'Guerreiro',
        110,
        16,
        NULL
    );

INSERT INTO guildas (nome) VALUES ('Irmandade das Sombras');

SELECT herois.nome as heroi, herois.classe, guildas.nome as guilda
FROM herois
    INNER JOIN guildas ON herois.guilda_id = guildas.id;

SELECT herois.nome AS heroi, guildas.nome AS guilda
FROM herois
    LEFT JOIN guildas ON herois.guilda_id = guildas.id;