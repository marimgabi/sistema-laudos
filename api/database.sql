-- TABELA role
CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    role VARCHAR(100) NOT NULL,
    descricao TEXT
);

-- TABELA user
CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    data_inclusao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_alteracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status CHAR(1) CHECK (status IN ('A', 'I')) NOT NULL
);

-- TABELA user_role
CREATE TABLE user_role (
    user_id INT,
    role_id INT,
    data_inclusao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_alteracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status CHAR(1) CHECK (status IN ('A', 'I')) NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_userrole_user FOREIGN KEY (user_id) REFERENCES "user"(id),
    CONSTRAINT fk_userrole_role FOREIGN KEY (role_id) REFERENCES role(id)
);

-- TABELA estado
CREATE TABLE estado (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    sigla CHAR(2),
    data_inclusao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_alteracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status CHAR(1) CHECK (status IN ('A', 'I')) NOT NULL
);

-- TABELA conselho
CREATE TABLE conselho (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    numero VARCHAR(50) NOT NULL,
    id_estado INT,
    data_inclusao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_alteracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status CHAR(1) CHECK (status IN ('A', 'I')) NOT NULL,
    CONSTRAINT fk_conselho_estado FOREIGN KEY (id_estado) REFERENCES estado(id)
);

-- TABELA medico
CREATE TABLE medico (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    tipo VARCHAR(50),
    id_user INT,
    id_conselho INT,
    data_inclusao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_alteracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status CHAR(1) CHECK (status IN ('A', 'I')) NOT NULL,
    CONSTRAINT fk_medico_user FOREIGN KEY (id_user) REFERENCES "user"(id),
    CONSTRAINT fk_medico_conselho FOREIGN KEY (id_conselho) REFERENCES conselho(id)
);

-- TABELA template
CREATE TABLE template (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255),
    conteudo TEXT,
    tipo VARCHAR(50),
    data_inclusao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_alteracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status CHAR(1) CHECK (status IN ('A', 'I')) NOT NULL
);

-- TABELA medico_template
CREATE TABLE medico_template (
    id_medico INT,
    id_template INT,
    data_inclusao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_alteracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status CHAR(1) CHECK (status IN ('A', 'I')) NOT NULL,
    PRIMARY KEY (id_medico, id_template),
    CONSTRAINT fk_medtemp_medico FOREIGN KEY (id_medico) REFERENCES medico(id),
    CONSTRAINT fk_medtemp_template FOREIGN KEY (id_template) REFERENCES template(id)
);

-- TABELA laudo
CREATE TABLE laudo (
    id SERIAL PRIMARY KEY,
    data_laudo DATE,
    nome_paciente VARCHAR(255),
    sexo CHAR(1) CHECK (sexo IN ('M', 'F', 'O')),
    data_nascimento DATE,
    id_medico_solicitante INT,
    id_medico_executante INT,
    conteudo TEXT,
    data_inclusao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_alteracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status CHAR(1) CHECK (status IN ('A', 'I')) NOT NULL,
    CONSTRAINT fk_laudo_med_solic FOREIGN KEY (id_medico_solicitante) REFERENCES medico(id),
    CONSTRAINT fk_laudo_med_exec FOREIGN KEY (id_medico_executante) REFERENCES medico(id)
);

-- Insert com os estados

INSERT INTO estado (nome, sigla, data_inclusao, data_alteracao, status) VALUES
('Acre', 'AC', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Alagoas', 'AL', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Amapá', 'AP', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Amazonas', 'AM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Bahia', 'BA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Ceará', 'CE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Distrito Federal', 'DF', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Espírito Santo', 'ES', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Goiás', 'GO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Maranhão', 'MA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Mato Grosso', 'MT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Mato Grosso do Sul', 'MS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Minas Gerais', 'MG', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Pará', 'PA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Paraíba', 'PB', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Paraná', 'PR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Pernambuco', 'PE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Piauí', 'PI', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Rio de Janeiro', 'RJ', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Rio Grande do Norte', 'RN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Rio Grande do Sul', 'RS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Rondônia', 'RO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Roraima', 'RR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Santa Catarina', 'SC', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('São Paulo', 'SP', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Sergipe', 'SE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A'),
('Tocantins', 'TO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'A');
