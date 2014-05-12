	DROP SCHEMA IF EXISTS BDIC3;
	CREATE SCHEMA IF NOT EXISTS BDIC3;
	USE BDIC3;

	-- -----------------------------------------------------
	-- Table'BDIC3'.'LOCALIDADE'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS LOCALIDADE;

	CREATE TABLE IF NOT EXISTS LOCALIDADE (
		loc_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		loc_latitude FLOAT NOT NULL,
		loc_longitude FLOAT NOT NULL,
		loc_endereco VARCHAR(250) NOT NULL,
		loc_cidade VARCHAR(250) NOT NULL,
		loc_estado VARCHAR(3) NOT NULL,
		loc_pais VARCHAR(3) NOT NULL,
		loc_cep VARCHAR(25) NOT NULL
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
		cli_email VARCHAR(45) NOT NULL,
		loc_id INT(11) NOT NULL,
		cli_rg VARCHAR(45) NOT NULL,
		cli_biometria VARCHAR(45) NOT NULL,
		cli_token VARCHAR(45) NOT NULL,
		cli_senha VARCHAR(45) NULL,
		cli_datanascimento DATETIME NULL,
		cli_renda DECIMAL(10,2) NULL DEFAULT NULL,
		cli_sexo VARCHAR(1) NULL,
		CONSTRAINT fk_CLIENTE_LOCALIDADE1 FOREIGN KEY (loc_id)
			REFERENCES LOCALIDADE (loc_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION
	)  ENGINE=InnoDB;


	-- -----------------------------------------------------
	-- Table'BDIC3'.'ESTABELECIMENTO'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS ESTABELECIMENTO;

	CREATE TABLE IF NOT EXISTS ESTABELECIMENTO (
		etb_id INT(11) NOT NULL AUTO_INCREMENT,
		etb_tipo VARCHAR(200) NOT NULL,
		etb_nome VARCHAR(200) NOT NULL,
		loc_id INT(11) NOT NULL,
		etb_email VARCHAR(255) NULL,
		etb_senha VARCHAR(45) NULL,
		etb_cnpj VARCHAR(14) NULL,
		PRIMARY KEY (etb_id , loc_id),
		INDEX idx_ETB_LOCALIDADE (loc_id ASC),
		CONSTRAINT fk_ESTABELECIMENTO_LOCALIDADE FOREIGN KEY (loc_id)
			REFERENCES LOCALIDADE (loc_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION
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
		mid_bandeira VARCHAR(80) NOT NULL,
		cli_id INT(11) NULL,
		mid_validadeano INT(11) NULL,
		mid_diafatura INT(11) NULL,
		mid_validademes INT(11) NULL,
		mid_valormaximo FLOAT(10,2) NULL,
		mid_codigoseguranca VARCHAR(4) NULL DEFAULT NULL,
		PRIMARY KEY (mid_id , cli_id),
		INDEX idx_MID_CLIENTE (cli_id ASC),
		CONSTRAINT fk_MIDIA_CLIENTE FOREIGN KEY (cli_id)
			REFERENCES CLIENTE (cli_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION
	)  ENGINE=InnoDB;


	-- -----------------------------------------------------
	-- Table'BDIC3'.'SISTEMA_OPERACIONAL'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS SISTEMA_OPERACIONAL;

	CREATE TABLE IF NOT EXISTS SISTEMA_OPERACIONAL (
		sis_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		sis_nome VARCHAR(45) NOT NULL,
		sis_versao VARCHAR(45) NULL,
		sis_movel VARCHAR(45) NULL
	)  ENGINE=InnoDB;


	-- -----------------------------------------------------
	-- Table'BDIC3'.'NAVEGADOR'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS NAVEGADOR;

	CREATE TABLE IF NOT EXISTS NAVEGADOR (
		nav_id INT NOT NULL PRIMARY KEY,
		nav_nome VARCHAR(45) NOT NULL,
		nav_versao VARCHAR(45) NOT NULL
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
	-- Table `BDIC3`.`MEIO_PAGAMENTO`
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS MEIO_PAGAMENTO;

	CREATE TABLE IF NOT EXISTS MEIO_PAGAMENTO (
	  mep_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	  mep_descricao VARCHAR(45) NULL DEFAULT NULL

	)  ENGINE = InnoDB;


	-- -----------------------------------------------------
	-- Table'BDIC3'.'TRANSACAO'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS TRANSACAO;

	CREATE TABLE IF NOT EXISTS TRANSACAO (
		tra_id INT NOT NULL AUTO_INCREMENT,
		tra_tipo INT(11) NOT NULL,
		tra_total FLOAT NOT NULL,
		tra_data VARCHAR(50) NOT NULL,
		tra_hora VARCHAR(50) NOT NULL,
		etb_id INT(11) NOT NULL,
		cli_id INT(11) NOT NULL,
		mid_id INT(11) NOT NULL,
		loc_id INT(11) NOT NULL COMMENT 'tambem conhecido como tra_geolocalizacao',
		ses_id INT(11) NOT NULL,
		tra_descricao_pagamento VARCHAR(200) NULL DEFAULT NULL,
		tra_validade VARCHAR(45) NULL DEFAULT NULL,
		tra_url_pagamento VARCHAR(150) NULL DEFAULT NULL,
		tra_status VARCHAR(10) NULL,
		tra_quantidadeparcela INT(11) NULL DEFAULT NULL,
		mep_id INT(11) NULL,
		tra_confirmed VARCHAR(1) NULL DEFAULT NULL,
		PRIMARY KEY (tra_id , etb_id , cli_id , mid_id , loc_id , ses_id),
		INDEX idx_TRANSACAO_ESTABELECIMENTO (etb_id ASC),
		INDEX idx_TRANSACAO_CLIENTE (cli_id ASC),
		INDEX idx_TRANSACAO_MIDIA (mid_id ASC),
		INDEX idx_TRANSACAO_SESSAO1 (ses_id ASC),
		INDEX fk_TRANSACAO_GEOLOCALIZACAO (loc_id ASC),
		INDEX fk_TRANSACAO_MEIO_PAGAMENTO1_idx (mep_id ASC),
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
		CONSTRAINT fk_MEIO_PAGAMENTO1 FOREIGN KEY (mep_id)
			REFERENCES MEIO_PAGAMENTO (mep_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION
	)  ENGINE=InnoDB COMMENT='MID_mid_id';


	-- -----------------------------------------------------
	-- Table'BDIC3'.'FRAUDE'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS FRAUDE;

	CREATE TABLE IF NOT EXISTS FRAUDE (
		fra_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		fra_nome VARCHAR(250) NOT NULL,
		fra_tipo VARCHAR(100) NOT NULL,    
		fra_forma_deteccao VARCHAR(45) NOT NULL,
		fra_data_deteccao VARCHAR(45) NOT NULL,
		tra_id INT NOT NULL,
		INDEX idx_FRA_TRANSACAO (tra_id ASC),
		CONSTRAINT fk_FRA_TRANSACAO FOREIGN KEY (tra_id)
			REFERENCES TRANSACAO (tra_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION
	)  ENGINE=InnoDB;


	-- -----------------------------------------------------
	-- Table `BDIC3`.`TRANSACAO_INCOMUM`
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS TRANSACAO_INCOMUM;

	CREATE TABLE IF NOT EXISTS TRANSACAO_INCOMUM (
	  tra_id INT NOT NULL PRIMARY KEY,
	  inc_tipo VARCHAR(45) NOT NULL,
	  inc_forma_deteccao VARCHAR(45) NOT NULL,
	  inc_data_deteccao VARCHAR(45) NOT NULL,
	  inc_confirmacao_cliente CHAR(1) NOT NULL,
	  inc_descricao VARCHAR(250) NULL,
	  INDEX fk_TRANSACAO_INCOMUM_TRANSACAO1_idx (tra_id ASC),
	  CONSTRAINT fk_TRANSACAO_INCOMUM_TRANSACAO1
		FOREIGN KEY (tra_id) REFERENCES TRANSACAO (tra_id)
		ON DELETE NO ACTION ON UPDATE NO ACTION
	) ENGINE = InnoDB;


	-- ----------------------------------------------------- 
	-- Table'BDIC3'.'PRODUTO'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS PRODUTO;

	CREATE TABLE IF NOT EXISTS PRODUTO (
		prd_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		prd_description VARCHAR(250) NOT NULL,
		prd_price FLOAT(10 , 2 ) NOT NULL
	)  ENGINE=InnoDB;


	-- ----------------------------------------------------- 
	-- Table'BDIC3'.'TRANSACAO_PRODUTO'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS TRANSACAO_PRODUTO;

	CREATE TABLE IF NOT EXISTS TRANSACAO_PRODUTO (
		trp_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		prd_id INT NOT NULL,
		tra_id INT NOT NULL,
		INDEX fk_TRANSACAO_PRODUTO_TRANSACAO1_idx (tra_id ASC),
		INDEX fk_TRANSACAO_PRODUTO_PRODUTO1_idx (prd_id ASC),
		CONSTRAINT fk_TRANSACAO_PRODUTO_TRANSACAO1
		  FOREIGN KEY (tra_id)
		  REFERENCES TRANSACAO (tra_id)
		  ON DELETE NO ACTION
		  ON UPDATE NO ACTION,
		CONSTRAINT fk_TRANSACAO_PRODUTO_PRODUTO1
		  FOREIGN KEY (prd_id)
		  REFERENCES PRODUTO (prd_id)
		  ON DELETE NO ACTION
		  ON UPDATE NO ACTION
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
	  ett_datahora VARCHAR(45) NOT NULL,
	  ett_statuso VARCHAR(10) NULL,
	  ett_detalhe_historico VARCHAR(255) NULL COMMENT 'pode armazenar o código do comprovante',
	  PRIMARY KEY (tra_id , etb_id , mep_id , cli_id),
	  INDEX fk_ETAPA_TRANSACAO_ESTABELECIMENTO1_idx (etb_id ASC),
	  INDEX fk_ETAPA_TRANSACAO_MEIO_PAGAMENTO1_idx (mep_id ASC),
	  INDEX fk_ETAPA_TRANSACAO_CLIENTE1_idx (cli_id ASC),
	  CONSTRAINT fk_ETAPA_TRANSACAO_TRANSACAO1 FOREIGN KEY (tra_id)
		REFERENCES TRANSACAO (tra_id)
		ON DELETE NO ACTION ON UPDATE NO ACTION,
	  CONSTRAINT fk_ETAPA_TRANSACAO_ESTABELECIMENTO1 FOREIGN KEY (etb_id)
		REFERENCES ESTABELECIMENTO (etb_id)
		ON DELETE NO ACTION ON UPDATE NO ACTION,
	  CONSTRAINT fk_ETAPA_TRANSACAO_MEIO_PAGAMENTO1 FOREIGN KEY (mep_id)
		REFERENCES MEIO_PAGAMENTO (mep_id)
		ON DELETE NO ACTION ON UPDATE NO ACTION,
	  CONSTRAINT fk_ETAPA_TRANSACAO_CLIENTE1 FOREIGN KEY (cli_id)
		REFERENCES CLIENTE (cli_id)
		ON DELETE NO ACTION ON UPDATE NO ACTION
	) ENGINE = InnoDB


