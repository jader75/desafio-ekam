CREATE TABLE beneficiarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    data_nascimento DATE,
    data_inclusao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    data_exclusao TIMESTAMP
);

CREATE TABLE documentos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_documento VARCHAR(50) NOT NULL,
    descricao VARCHAR(255),
    data_inclusao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    beneficiario_id BIGINT,
    FOREIGN KEY (beneficiario_id) REFERENCES beneficiarios(id)
);