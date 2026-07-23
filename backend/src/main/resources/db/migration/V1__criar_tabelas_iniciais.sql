CREATE TABLE tb_instituicao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo VARCHAR(20) NOT NULL
);

CREATE TABLE tb_evento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    data_inicial DATE NOT NULL,
    hora_inicial TIME NOT NULL,
    data_final DATE NOT NULL,
    hora_final TIME NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT FALSE,
    instituicao_id INT NOT NULL,

    CONSTRAINT fk_evento_instituicao
        FOREIGN KEY (instituicao_id)
        REFERENCES tb_instituicao (id)
);