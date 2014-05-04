DROP SCHEMA IF EXISTS BDIC3;
CREATE SCHEMA IF NOT EXISTS BDIC3;
USE BDIC3;

-- ----------------------------------------------------- 
-- Table'BDIC3'.'PAIS'
-- -----------------------------------------------------
DROP TABLE IF EXISTS PAIS;

CREATE TABLE IF NOT EXISTS PAIS (
    pais_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    pais_descricao VARCHAR(250) NOT NULL
)  ENGINE=InnoDB;


-- ----------------------------------------------------- 
-- Table'BDIC3'.'ESTADO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS ESTADO;

CREATE TABLE IF NOT EXISTS ESTADO (
    est_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    est_descricao VARCHAR(250) NOT NULL,
    est_sigla VARCHAR(3) NOT NULL,
    pais_id INT NOT NULL,
    INDEX idx_ESTADO_PAIS (pais_id ASC),
    CONSTRAINT fk_ESTADO_PAIS FOREIGN KEY (pais_id)
        REFERENCES PAIS (pais_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table'BDIC3'.'CIDADE'
-- -----------------------------------------------------
DROP TABLE IF EXISTS CIDADE;

CREATE TABLE IF NOT EXISTS CIDADE (
    cid_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cid_descricao VARCHAR(250) NOT NULL,
    est_id INT NOT NULL,
    INDEX idx_CIDADE_ESTADO (est_id ASC),
    CONSTRAINT fk_CIDADE_ESTADO FOREIGN KEY (est_id)
        REFERENCES ESTADO (est_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB;


-- ----------------------------------------------------- 
-- Table'BDIC3'.'LOCALIDADETIPO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS LOCALIDADETIPO;

CREATE TABLE IF NOT EXISTS LOCALIDADETIPO (
    lot_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    lot_descricao VARCHAR(255) NOT NULL
)  ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table'BDIC3'.'LOCALIDADE'
-- -----------------------------------------------------
DROP TABLE IF EXISTS LOCALIDADE;

CREATE TABLE IF NOT EXISTS LOCALIDADE (
    loc_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    loc_posicao POINT NOT NULL,
    loc_endereco VARCHAR(250) NOT NULL,
    loc_numero VARCHAR(5) NOT NULL,
    cid_id INT NOT NULL,
    loc_complemento VARCHAR(255) NOT NULL,
    loc_cep VARCHAR(45) NOT NULL,
    lot_id INT NOT NULL,
    CONSTRAINT fk_LOCALIDADE_LOCALIDADETIPO FOREIGN KEY (lot_id)
        REFERENCES LOCALIDADETIPO (lot_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_LOCALIDADE_CIDADE FOREIGN KEY (cid_id)
        REFERENCES CIDADE (cid_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table'BDIC3'.'CLIENTE'
-- -----------------------------------------------------
DROP TABLE IF EXISTS CLIENTE;

CREATE TABLE IF NOT EXISTS CLIENTE (
    cli_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cli_nome VARCHAR(250) NOT NULL,
    cli_primeironome VARCHAR(45) NOT NULL,
    cli_ultimonome VARCHAR(45) NOT NULL,
    cli_telefone VARCHAR(45) NOT NULL,
    cli_celular VARCHAR(45) NOT NULL,
    cli_cpf VARCHAR(45) NOT NULL,
    cli_sexo VARCHAR(1) NOT NULL,
    cli_email VARCHAR(45) NOT NULL,
    cli_senha VARCHAR(45) NOT NULL,
    cli_datanascimento DATETIME NOT NULL,
    loc_id INT NOT NULL,
    cli_rg VARCHAR(45) NULL,
    cli_renda DECIMAL(10 , 2 ) NULL,
    cli_biometria VARCHAR(45) NULL,
    cli_token VARCHAR(45) NULL,
    CONSTRAINT fk_CLIENTE_LOCALIDADE1 FOREIGN KEY (loc_id)
        REFERENCES LOCALIDADE (loc_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table'BDIC3'.'ESTABELECIMENTO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS ESTABELECIMENTO;

CREATE TABLE IF NOT EXISTS ESTABELECIMENTO (
    etb_id INT NOT NULL AUTO_INCREMENT,
    etb_tipo VARCHAR(45) NOT NULL,
    etb_nome VARCHAR(45) NOT NULL,
    etb_cnpj VARCHAR(14) NOT NULL,
    etb_email VARCHAR(255) NOT NULL,
    etb_senha VARCHAR(45) NOT NULL,
    loc_id INT NOT NULL,
    PRIMARY KEY (etb_id , loc_id),
    INDEX idx_ETB_LOCALIDADE (loc_id ASC),
    CONSTRAINT fk_ESTABELECIMENTO_LOCALIDADE FOREIGN KEY (loc_id)
        REFERENCES LOCALIDADE (loc_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table'BDIC3'.'OPERADORA_CARTAO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS OPERADORA_CARTAO;

CREATE TABLE IF NOT EXISTS OPERADORA_CARTAO (
    odc_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    odc_descricao VARCHAR(255) NULL
)  ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table'BDIC3'.'MIDIA'
-- -----------------------------------------------------
DROP TABLE IF EXISTS MIDIA;

CREATE TABLE IF NOT EXISTS MIDIA (
    mid_id INT NOT NULL AUTO_INCREMENT,
    mid_name VARCHAR(250) NOT NULL,
    mid_description VARCHAR(250) NOT NULL,
    mid_number VARCHAR(45) NOT NULL,
    mid_diafatura INT NOT NULL,
    mid_validademes INT NOT NULL,
    mid_validadeano INT NOT NULL,
    mid_valormaximo DECIMAL(10 , 2 ) NOT NULL,
    mid_codigoseguranca VARCHAR(4) NULL,
    odc_id INT NOT NULL,
    cli_id INT NOT NULL,
    PRIMARY KEY (mid_id , cli_id),
    INDEX idx_MID_CLIENTE (cli_id ASC),
    CONSTRAINT fk_MIDIA_CLIENTE FOREIGN KEY (cli_id)
        REFERENCES CLIENTE (cli_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    INDEX idx_MID_OPERADORA_CARTAO (odc_id ASC),
    CONSTRAINT fk_MIDIA_OPERADORA_CARTAO FOREIGN KEY (odc_id)
        REFERENCES OPERADORA_CARTAO (odc_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table'BDIC3'.'SISTEMA_OPERACIONAL'
-- -----------------------------------------------------
DROP TABLE IF EXISTS SISTEMA_OPERACIONAL;

CREATE TABLE IF NOT EXISTS SISTEMA_OPERACIONAL (
    sis_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    sis_nome VARCHAR(45) NULL,
    sis_versao VARCHAR(45) NULL,
    sis_movel VARCHAR(45) NULL
)  ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table'BDIC3'.'NAVEGADOR'
-- -----------------------------------------------------
DROP TABLE IF EXISTS NAVEGADOR;

CREATE TABLE IF NOT EXISTS NAVEGADOR (
    nav_id INT NOT NULL PRIMARY KEY,
    nav_nome VARCHAR(45) NULL,
    nav_versao VARCHAR(45) NULL
)  ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table'BDIC3'.'SESSAO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS SESSAO;

CREATE TABLE IF NOT EXISTS SESSAO (
    ses_id INT NOT NULL AUTO_INCREMENT,
    ses_data VARCHAR(45) NULL,
    ses_hora VARCHAR(45) NULL,
    cli_id INT NOT NULL,
    sis_id INT NOT NULL,
    nav_id INT NOT NULL,
    PRIMARY KEY (ses_id , cli_id , sis_id , nav_id),
    INDEX idx_SESSAO_CLIENTE1 (cli_id ASC),
    INDEX idx_SESSAO_SISTEMA_OPERACIONAL1 (sis_id ASC),
    INDEX idx_SESSAO_NAVEGADOR1 (nav_id ASC),
    CONSTRAINT fk_SESSAO_CLIENTE1 FOREIGN KEY (cli_id)
        REFERENCES CLIENTE (cli_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_SESSAO_SISTEMA_OPERACIONAL1 FOREIGN KEY (sis_id)
        REFERENCES SISTEMA_OPERACIONAL (sis_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_SESSAO_NAVEGADOR1 FOREIGN KEY (nav_id)
        REFERENCES NAVEGADOR (nav_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB;


-- ----------------------------------------------------- 
-- Table'BDIC3'.'TRANSACAOTIPO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS TRANSACAOTIPO;

CREATE TABLE IF NOT EXISTS TRANSACAOTIPO (
    trt_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    trt_description VARCHAR(250) NOT NULL
)  ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table'BDIC3'.'TRANSACAO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS TRANSACAO;

CREATE TABLE IF NOT EXISTS TRANSACAO (
    tra_id INT NOT NULL AUTO_INCREMENT,
    trt_id INT NOT NULL,
    tra_total FLOAT NOT NULL,
    tra_data VARCHAR(50) NOT NULL,
    tra_hora VARCHAR(50) NOT NULL,
    tra_descricao_pagamento VARCHAR(200) NULL,
    tra_validade DATETIME NULL,
    tra_url_pagamento VARCHAR(150) NULL,
    loc_id INT NOT NULL COMMENT 'tambem conhecido como tra_geolocalizacao',
    tra_status VARCHAR(10) NOT NULL,
    tra_quantidadeparcela INT NULL,
    etb_id INT NOT NULL,
    cli_id INT NOT NULL,
    mid_id INT NOT NULL,
    ses_id INT NOT NULL,
    mep_id INT NOT NULL,
    tra_confirmed BOOLEAN,
    PRIMARY KEY (tra_id , etb_id , cli_id , mid_id , loc_id , ses_id),
    INDEX idx_TRANSACAO_ESTABELECIMENTO (etb_id ASC),
    INDEX idx_TRANSACAO_CLIENTE (cli_id ASC),
    INDEX idx_TRANSACAO_MIDIA (mid_id ASC),
    INDEX idx_TRANSACAO_SESSAO1 (ses_id ASC),
    CONSTRAINT fk_TRANSACAO_ESTABELECIMENTO FOREIGN KEY (etb_id)
        REFERENCES ESTABELECIMENTO (etb_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_TRANSACAO_CLIENTE FOREIGN KEY (cli_id)
        REFERENCES CLIENTE (cli_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_TRANSACAO_MIDIA FOREIGN KEY (mid_id)
        REFERENCES MIDIA (mid_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_TRANSACAO_GEOLOCALIZACAO FOREIGN KEY (loc_id)
        REFERENCES LOCALIDADE (loc_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_TRANSACAO_SESSAO1 FOREIGN KEY (ses_id)
        REFERENCES SESSAO (ses_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_TRANSACAO_TRANSACAOTIPO FOREIGN KEY (trt_id)
        REFERENCES TRANSACAOTIPO (trt_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB COMMENT='MID_mid_id';


-- ----------------------------------------------------- 
-- Table'BDIC3'.'FRAUDETIPO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS FRAUDETIPO;

CREATE TABLE IF NOT EXISTS FRAUDETIPO (
    frt_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    frt_description VARCHAR(250) NOT NULL
)  ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table'BDIC3'.'FRAUDE'
-- -----------------------------------------------------
DROP TABLE IF EXISTS FRAUDE;

CREATE TABLE IF NOT EXISTS FRAUDE (
    fra_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fra_name VARCHAR(250) NOT NULL,
    fra_forma_deteccao VARCHAR(45) NOT NULL,
    fra_data_deteccao VARCHAR(45) NOT NULL,
    tra_id INT NOT NULL,
    frt_id INT NOT NULL,
    INDEX idx_FRA_TRANSACAO (tra_id ASC),
    INDEX idx_FRAUDE_FRAUDETIPO (frt_id ASC),
    CONSTRAINT fk_FRA_TRANSACAO FOREIGN KEY (tra_id)
        REFERENCES TRANSACAO (tra_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_FRAUDE_FRAUDETIPO FOREIGN KEY (frt_id)
        REFERENCES FRAUDETIPO (frt_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB;


-- ----------------------------------------------------- 
-- Table'BDIC3'.'TRANSACAO_PRODUTO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS TRANSACAO_PRODUTO;

CREATE TABLE IF NOT EXISTS TRANSACAO_PRODUTO (
    trp_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    prd_id INT NOT NULL,
    tra_id INT NOT NULL
)  ENGINE=InnoDB;


-- ----------------------------------------------------- 
-- Table'BDIC3'.'PRODUTO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS PRODUTO;

CREATE TABLE IF NOT EXISTS PRODUTO (
    prd_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    prd_description VARCHAR(250) NOT NULL,
    prd_price DECIMAL(10 , 2 ) NOT NULL
)  ENGINE=InnoDB;


-- ----------------------------------------------------- 
-- Table'BDIC3'.'MEIO_PAGAMENTO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS MEIO_PAGAMENTO;

CREATE TABLE IF NOT EXISTS MEIO_PAGAMENTO (
    mep_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    mep_descricao VARCHAR(45) NULL
)  ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table'BDIC3'.'MEIO_PAGAMENTO_ESTABELECIMENTO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS MEIO_PAGAMENTO_ESTABELECIMENTO;

CREATE TABLE IF NOT EXISTS MEIO_PAGAMENTO_ESTABELECIMENTO (
    etb_id INT NOT NULL AUTO_INCREMENT,
    mep_id INT NOT NULL,
    mpe_token VARCHAR(60) NOT NULL,
    PRIMARY KEY (etb_id , mep_id),
    INDEX idx_MEIO_PAGAMENTO_ETB_MEIO_PAGAMENTO1 (mep_id ASC),
    CONSTRAINT fk_MEIO_PAGAMENTO_ETB_ESTABELECIMENTO1 FOREIGN KEY (etb_id)
        REFERENCES ESTABELECIMENTO (etb_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_MEIO_PAGAMENTO_ETB_MEIO_PAGAMENTO1 FOREIGN KEY (mep_id)
        REFERENCES MEIO_PAGAMENTO (mep_id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table'BDIC3'.'ETAPA_TRANSACAO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS ETAPA_TRANSACAO;

CREATE TABLE IF NOT EXISTS ETAPA_TRANSACAO (
    tra_id INT NOT NULL,
    etb_id INT NOT NULL,
    mep_id INT NOT NULL,
    cli_id INT NOT NULL,
    ett_datahora DATETIME NOT NULL,
    ett_statuso VARCHAR(10) NULL,
    ett_detalhe_historico VARCHAR(255) NULL COMMENT 'pode armazenar o código do comprovante',
    PRIMARY KEY (tra_id , etb_id , mep_id , cli_id)
)  ENGINE=InnoDB;

