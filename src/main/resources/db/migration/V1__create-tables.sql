CREATE TABLE TbOficina (
    id INT AUTO_INCREMENT,
    nome VARCHAR(75) NOT NULL,
    cnpj CHAR(14) UNIQUE NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado CHAR(2) NOT NULL,
    ativa TINYINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE TbEspecialidade (
    id INT AUTO_INCREMENT,
    especialidade VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE TbFabricante (
    id INT AUTO_INCREMENT,
    fabricante VARCHAR(75) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE TbOficinaEspecialidade (
    id_oficina INT,
    id_especialidade INT,
    PRIMARY KEY (id_oficina, id_especialidade),
    FOREIGN KEY (id_oficina) REFERENCES TbOficina(id),
    FOREIGN KEY (id_especialidade) REFERENCES TbEspecialidade(id)
);

CREATE TABLE TbOficinaFabricante (
    id_oficina INT,
    id_fabricante INT,
    PRIMARY KEY (id_oficina, id_fabricante),
    FOREIGN KEY (id_oficina) REFERENCES TbOficina(id),
    FOREIGN KEY (id_fabricante) REFERENCES TbFabricante(id)
);