SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- ----------------------------------------------------- 
-- Table'BDIC3'.'PAIS'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'PAIS';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'PAIS' (
    'pais_id' INT NOT NULL AUTO_INCREMENT,
    'pais_descricao' VARCHAR(250) NOT NULL,
    PRIMARY KEY ('pais_id')
)  ENGINE=InnoDB;


-- ----------------------------------------------------- 
-- Table'BDIC3'.'ESTADO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'ESTADO';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'ESTADO' (
    'est_id' INT NOT NULL AUTO_INCREMENT,
    'est_descricao' VARCHAR(250) NOT NULL,
    'est_sigla' VARCHAR(3) NOT NULL,
    'pais_id' INT NOT NULL,
    PRIMARY KEY ('est_id'),
    INDEX 'idx_ESTADO_PAIS' ('pais_id' ASC),
    CONSTRAINT 'fk_ESTADO_PAIS' FOREIGN KEY ('pais_id')
        REFERENCES 'BDIC3'.'PAIS' ('pais_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table'BDIC3'.'CIDADE'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'CIDADE';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'CIDADE' (
    'cid_id' INT NOT NULL AUTO_INCREMENT,
    'cid_descricao' VARCHAR(250) NOT NULL,
    'est_id' INT NOT NULL,
    PRIMARY KEY ('cid_id'),
    INDEX 'idx_CIDADE_ESTADO' ('est_id' ASC),
    CONSTRAINT 'fk_CIDADE_ESTADO' FOREIGN KEY ('est_id')
        REFERENCES 'BDIC3'.'ESTADO' ('est_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB;


-- ----------------------------------------------------- 
-- Table'BDIC3'.'LOCALIDADETIPO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'LOCALIDADETIPO';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'LOCALIDADETIPO' (
    'lot_id' INT NOT NULL AUTO_INCREMENT,
    'lot_descricao' VARCHAR(255) NOT NULL,
    PRIMARY KEY ('lot_id')
)  ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table'BDIC3'.'LOCALIDADE'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'LOCALIDADE';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'LOCALIDADE' (
    'loc_id' INT NOT NULL AUTO_INCREMENT,
    'loc_posicao' POINT NOT NULL,
    'loc_endereco' VARCHAR(250) NOT NULL,
    'loc_numero' VARCHAR(5) NOT NULL,
    'cid_id' INT NOT NULL,
    'loc_complemento' VARCHAR(255) NOT NULL,
    'loc_cep' VARCHAR(45) NOT NULL,
    'lot_id' INT NOT NULL,
    PRIMARY KEY ('loc_id'),
    CONSTRAINT 'fk_LOCALIDADE_LOCALIDADETIPO' FOREIGN KEY ('lot_id')
        REFERENCES 'BDIC3'.'LOCALIDADETIPO' ('lot_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT 'fk_LOCALIDADE_CIDADE' FOREIGN KEY ('cid_id')
        REFERENCES 'BDIC3'.'CIDADE' ('cid_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table'BDIC3'.'CLIENTE'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'CLIENTE';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'CLIENTE' (
    'cli_id' INT NOT NULL AUTO_INCREMENT,
    'cli_nome' VARCHAR(250) NOT NULL,
    'cli_primeironome' VARCHAR(45) NOT NULL,
    'cli_ultimonome' VARCHAR(45) NOT NULL,
    'cli_telefone' VARCHAR(45) NOT NULL,
    'cli_celular' VARCHAR(45) NOT NULL,
    'cli_cpf' VARCHAR(45) NOT NULL,
    'cli_sexo' VARCHAR(1) NOT NULL,
    'cli_email' VARCHAR(45) NOT NULL,
    'cli_senha' VARCHAR(45) NOT NULL,
    'cli_datanascimento' DATETIME NOT NULL,
    'loc_id' INT NOT NULL,
    'cli_rg' VARCHAR(45) NULL,
    'cli_renda' DECIMAL(10 , 2 ) NULL,
    'cli_biometria' VARCHAR(45) NULL,
    'cli_token' VARCHAR(45) NULL,
    PRIMARY KEY ('cli_id'),
    CONSTRAINT 'fk_CLIENTE_LOCALIDADE1' FOREIGN KEY ('loc_id')
        REFERENCES 'BDIC3'.'LOCALIDADE' ('loc_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table'BDIC3'.'ESTABELECIMENTO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'ESTABELECIMENTO';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'ESTABELECIMENTO' (
    'etb_id' INT NOT NULL AUTO_INCREMENT,
    'etb_tipo' VARCHAR(45) NOT NULL,
    'etb_nome' VARCHAR(45) NOT NULL,
    'etb_cnpj' VARCHAR(14) NOT NULL,
    'etb_email' VARCHAR(255) NOT NULL,
    'etb_senha' VARCHAR(45) NOT NULL,
    'loc_id' INT NOT NULL,
    PRIMARY KEY ('etb_id' , 'loc_id'),
    INDEX 'idx_ETB_LOCALIDADE' ('loc_id' ASC),
    CONSTRAINT 'fk_ESTABELECIMENTO_LOCALIDADE' FOREIGN KEY ('loc_id')
        REFERENCES 'BDIC3'.'LOCALIDADE' ('loc_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table'BDIC3'.'OPERADORA_CARTAO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'OPERADORA_CARTAO';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'OPERADORA_CARTAO' (
    'odc_id' INT NOT NULL AUTO_INCREMENT,
    'odc_descricao' VARCHAR(255) NULL,
    PRIMARY KEY ('odc_id')
)  ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table'BDIC3'.'FRAUDE'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'FRAUDE';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'FRAUDE' (
    'fra_id' INT NOT NULL AUTO_INCREMENT,
    'fra_name' VARCHAR(250) NOT NULL,
    'fra_forma_deteccao' VARCHAR(45) NOT NULL,
    'fra_data_deteccao' VARCHAR(45) NOT NULL,
    'tra_id' INT NOT NULL,
    'frt_id' INT NOT NULL,
    PRIMARY KEY ('fra_id'),
    INDEX 'idx_FRA_TRANSACAO' ('tra_id' ASC),
    INDEX 'idx_FRAUDE_FRAUDETIPO' ('frt_id' ASC),
    CONSTRAINT 'fk_FRA_TRANSACAO' FOREIGN KEY ('tra_id')
        REFERENCES 'BDIC3'.'TRANSACAO' ('tra_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT 'fk_FRAUDE_FRAUDETIPO' FOREIGN KEY ('frt_id')
        REFERENCES 'BDIC3'.'FRAUDETIPO' ('frt_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB;



-- -----------------------------------------------------
-- Table'BDIC3'.'MIDIA'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'MIDIA';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'MIDIA' (
    'mid_id' INT NOT NULL AUTO_INCREMENT,
    'mid_name' VARCHAR(250) NOT NULL,
    'mid_description' VARCHAR(250) NOT NULL,
    'mid_number' VARCHAR(45) NOT NULL,
    'mid_diafatura' INT NOT NULL,
    'mid_validademes' INT NOT NULL,
    'mid_validadeano' INT NOT NULL,
    'mid_valormaximo' DECIMAL(10 , 2 ) NOT NULL,
    'mid_codigoseguranca' VARCHAR(4) NULL,
    'odc_id' INT NOT NULL,
    'cli_id' INT NOT NULL,
    PRIMARY KEY ('mid_id' , 'cli_id'),
    INDEX 'idx_MID_CLIENTE' ('cli_id' ASC),
    CONSTRAINT 'fk_MIDIA_CLIENTE' FOREIGN KEY ('cli_id')
        REFERENCES 'BDIC3'.'CLIENTE' ('cli_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    INDEX 'idx_MID_OPERADORA_CARTAO' ('odc_id' ASC),
    CONSTRAINT 'fk_MIDIA_OPERADORA_CARTAO' FOREIGN KEY ('odc_id')
        REFERENCES 'BDIC3'.'OPERADORA_CARTAO' ('odc_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table'BDIC3'.'SISTEMA_OPERACIONAL'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'SISTEMA_OPERACIONAL';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'SISTEMA_OPERACIONAL' (
    'sis_id' INT NOT NULL AUTO_INCREMENT,
    'sis_nome' VARCHAR(45) NULL,
    'sis_versao' VARCHAR(45) NULL,
    'sis_movel' VARCHAR(45) NULL,
    PRIMARY KEY ('sis_id')
)  ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table'BDIC3'.'NAVEGADOR'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'NAVEGADOR';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'NAVEGADOR' (
    'nav_id' INT NOT NULL,
    'nav_nome' VARCHAR(45) NULL,
    'nav_versao' VARCHAR(45) NULL,
    PRIMARY KEY ('nav_id')
)  ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table'BDIC3'.'SESSAO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'SESSAO';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'SESSAO' (
    'ses_id' INT NOT NULL AUTO_INCREMENT,
    'ses_data' VARCHAR(45) NULL,
    'ses_hora' VARCHAR(45) NULL,
    'cli_id' INT NOT NULL,
    'sis_id' INT NOT NULL,
    'nav_id' INT NOT NULL,
    PRIMARY KEY ('ses_id' , 'cli_id' , 'sis_id' , 'nav_id'),
    INDEX 'idx_SESSAO_CLIENTE1' ('cli_id' ASC),
    INDEX 'idx_SESSAO_SISTEMA_OPERACIONAL1' ('sis_id' ASC),
    INDEX 'idx_SESSAO_NAVEGADOR1' ('nav_id' ASC),
    CONSTRAINT 'fk_SESSAO_CLIENTE1' FOREIGN KEY ('cli_id')
        REFERENCES 'BDIC3'.'CLIENTE' ('cli_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT 'fk_SESSAO_SISTEMA_OPERACIONAL1' FOREIGN KEY ('sis_id')
        REFERENCES 'BDIC3'.'SISTEMA_OPERACIONAL' ('sis_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT 'fk_SESSAO_NAVEGADOR1' FOREIGN KEY ('nav_id')
        REFERENCES 'BDIC3'.'NAVEGADOR' ('nav_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB;


-- ----------------------------------------------------- 
-- Table'BDIC3'.'TRANSACAOTIPO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'TRANSACAOTIPO';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'TRANSACAOTIPO' (
    'trt_id' INT NOT NULL AUTO_INCREMENT,
    'trt_description' VARCHAR(250) NOT NULL,
    PRIMARY KEY ('trt_id')
)  ENGINE=InnoDB;



-- -----------------------------------------------------
-- Table'BDIC3'.'TRANSACAO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'TRANSACAO';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'TRANSACAO' (
    'tra_id' INT NOT NULL AUTO_INCREMENT,
    'trt_id' INT NOT NULL,
    'tra_total' FLOAT NOT NULL,
    'tra_data' VARCHAR(50) NOT NULL,
    'tra_hora' VARCHAR(50) NOT NULL,
    'tra_descricao_pagamento' VARCHAR(200) NULL,
    'tra_validade' DATETIME NULL,
    'tra_url_pagamento' VARCHAR(150) NULL,
    'loc_id' INT NOT NULL COMMENT 'tambem conhecido como tra_geolocalizacao',
    'tra_status' VARCHAR(10) NOT NULL,
    'tra_quantidadeparcela' INT NULL,
    'etb_id' INT NOT NULL,
    'cli_id' INT NOT NULL,
    'mid_id' INT NOT NULL,
    'ses_id' INT NOT NULL,
    'mep_id' INT NOT NULL,
    'tra_confirmed' BOOLEAN,
    PRIMARY KEY ('tra_id' , 'etb_id' , 'cli_id' , 'mid_id' , 'loc_id' , 'ses_id'),
    INDEX 'idx_TRANSACAO_ESTABELECIMENTO' ('etb_id' ASC),
    INDEX 'idx_TRANSACAO_CLIENTE' ('cli_id' ASC),
    INDEX 'idx_TRANSACAO_MIDIA' ('mid_id' ASC),
    INDEX 'idx_TRANSACAO_SESSAO1' ('ses_id' ASC),
    CONSTRAINT 'fk_TRANSACAO_ESTABELECIMENTO' FOREIGN KEY ('etb_id')
        REFERENCES 'BDIC3'.'ESTABELECIMENTO' ('etb_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT 'fk_TRANSACAO_CLIENTE' FOREIGN KEY ('cli_id')
        REFERENCES 'BDIC3'.'CLIENTE' ('cli_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT 'fk_TRANSACAO_MIDIA' FOREIGN KEY ('mid_id')
        REFERENCES 'BDIC3'.'MIDIA' ('mid_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT 'fk_TRANSACAO_GEOLOCALIZACAO' FOREIGN KEY ('loc_id')
        REFERENCES 'BDIC3'.'LOCALIDADE' ('loc_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT 'fk_TRANSACAO_SESSAO1' FOREIGN KEY ('ses_id')
        REFERENCES 'BDIC3'.'SESSAO' ('ses_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT 'fk_TRANSACAO_TRANSACAOTIPO' FOREIGN KEY ('trt_id')
        REFERENCES 'BDIC3'.'TRANSACAOTIPO' ('trt_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB COMMENT='MID_mid_id';

-- ----------------------------------------------------- 
-- Table'BDIC3'.'TRANSACAO_PRODUTO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'TRANSACAO_PRODUTO';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'TRANSACAO_PRODUTO' (
    'trp_id' INT NOT NULL AUTO_INCREMENT,
    'prd_id' INT NOT NULL,
    'tra_id' INT NOT NULL,
    PRIMARY KEY ('trp_id')
)  ENGINE=InnoDB;



-- ----------------------------------------------------- 
-- Table'BDIC3'.'PRODUTO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'PRODUTO';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'PRODUTO' (
    'prd_id' INT NOT NULL AUTO_INCREMENT,
    'prd_description' VARCHAR(250) NOT NULL,
    'prd_price' DECIMAL(10 , 2 ) NOT NULL,
    PRIMARY KEY ('prd_id')
)  ENGINE=InnoDB;




-- ----------------------------------------------------- 
-- Table'BDIC3'.'FRAUDETIPO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'FRAUDETIPO';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'FRAUDETIPO' (
    'frt_id' INT NOT NULL AUTO_INCREMENT,
    'frt_description' VARCHAR(250) NOT NULL,
    PRIMARY KEY ('frt_id')
)  ENGINE=InnoDB;


-- ----------------------------------------------------- 
-- Table'BDIC3'.'MEIO_PAGAMENTO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'MEIO_PAGAMENTO';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'MEIO_PAGAMENTO' (
    'mep_id' INT NOT NULL AUTO_INCREMENT,
    'mep_descricao' VARCHAR(45) NULL,
    PRIMARY KEY ('mep_id')
)  ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table'BDIC3'.'MEIO_PAGAMENTO_ESTABELECIMENTO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'MEIO_PAGAMENTO_ ESTABELECIMENTO';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'MEIO_PAGAMENTO_ ESTABELECIMENTO' (
    'etb_id' INT NOT NULL AUTO_INCREMENT,
    'mep_id' INT NOT NULL,
    'mpe_token' VARCHAR(60) NOT NULL,
    PRIMARY KEY ('etb_id' , 'mep_id'),
    INDEX 'idx_MEIO_PAGAMENTO_ETB_MEIO_PAGAMENTO1' ('mep_id' ASC),
    CONSTRAINT 'fk_MEIO_PAGAMENTO_ETB_ESTABELECIMENTO1' FOREIGN KEY ('etb_id')
        REFERENCES 'BDIC3'.'ESTABELECIMENTO' ('etb_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT 'fk_MEIO MEIO_PAGAMENTO_ETB_MEIO_PAGAMENTO1' FOREIGN KEY ('mep_id')
        REFERENCES 'BDIC3'.'MEIO_PAGAMENTO' ('mep_id')
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table'BDIC3'.'ETAPA_TRANSACAO'
-- -----------------------------------------------------
DROP TABLE IF EXISTS'BDIC3'.'ETAPA_TRANSACAO';

CREATE TABLE IF NOT EXISTS 'BDIC3'.'ETAPA_TRANSACAO' (
    'tra_id' INT NOT NULL,
    'etb_id' INT NOT NULL,
    'mep_id' INT NOT NULL,
    'cli_id' INT NOT NULL,
    'ett_datahora' DATETIME NOT NULL,
    'ett_statuso' VARCHAR(10) NULL,
    'ett_detalhe_historico' VARCHAR(255) NULL COMMENT 'pode armazenar o código do comprovante',
    PRIMARY KEY ('tra_id' , 'etb_id' , 'mep_id' , 'cli_id')
)  ENGINE=InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
