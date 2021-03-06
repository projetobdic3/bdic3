	DROP SCHEMA IF EXISTS bdic3;
	CREATE SCHEMA IF NOT EXISTS bdic3;
	USE bdic3;

	-- -----------------------------------------------------
	-- Table'bdic3'.'localidade'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS localidade;

	CREATE TABLE IF NOT EXISTS localidade (
		loc_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		loc_latitude FLOAT NULL,
		loc_longitude FLOAT NULL,
		loc_endereco VARCHAR(250),
		loc_cidade VARCHAR(250),
		loc_estado VARCHAR(3),
		loc_pais VARCHAR(3),
		loc_cep VARCHAR(25)
	)  ENGINE=InnoDB;


	-- -----------------------------------------------------
	-- Table'bdic3'.'cliente'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS cliente;

	CREATE TABLE IF NOT EXISTS cliente (
		cli_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		cli_nome VARCHAR(250) NULL,
		cli_primeironome VARCHAR(45) NULL,
		cli_ultimonome VARCHAR(45) NULL,
		cli_telefone VARCHAR(45) NULL,
		cli_celular VARCHAR(45) NULL,
		cli_cpf VARCHAR(45) NULL,
		cli_email VARCHAR(45) NULL,
		loc_id INT(11) NULL,
		cli_rg VARCHAR(45) NULL,
		cli_biometria VARCHAR(45) NULL,
		cli_token VARCHAR(45) NULL,
		cli_upper_limit FLOAT(10,2) NULL,
		cli_senha VARCHAR(45) NULL,
		cli_datanascimento VARCHAR(15) NULL,
		cli_renda FLOAT(10,2) NULL DEFAULT NULL,
		cli_sexo VARCHAR(1) NULL,
		CONSTRAINT fk_cliente_localidade1 FOREIGN KEY (loc_id)
			REFERENCES localidade (loc_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION
	)  ENGINE=InnoDB;


	-- -----------------------------------------------------
	-- Table'bdic3'.'estabelecimento'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS estabelecimento;

	CREATE TABLE IF NOT EXISTS estabelecimento (
		etb_id INT(11) NOT NULL AUTO_INCREMENT,
		etb_tipo VARCHAR(200) NULL,
		etb_nome VARCHAR(200) NULL,
		loc_id INT(11) NULL,
		etb_email VARCHAR(255) NULL,
		etb_senha VARCHAR(45) NULL,
		etb_cnpj VARCHAR(14) NULL,
		PRIMARY KEY (etb_id , loc_id),
		INDEX idx_ETB_localidade (loc_id ASC),
		CONSTRAINT fk_estabelecimento_localidade FOREIGN KEY (loc_id)
			REFERENCES localidade (loc_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION
	)  ENGINE=InnoDB;


	-- -----------------------------------------------------
	-- Table'bdic3'.'midia'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS midia;

	CREATE TABLE IF NOT EXISTS midia (
		mid_id INT NOT NULL AUTO_INCREMENT,
		mid_name VARCHAR(250) NULL,
		mid_description VARCHAR(250) NULL,
		mid_number VARCHAR(45) NULL,
		mid_bandeira VARCHAR(80) NULL,
		cli_id INT(11) NULL,
		mid_validadeano INT(11) NULL,
		mid_diafatura INT(11) NULL,
		mid_validademes INT(11) NULL,
		mid_valormaximo FLOAT(10,2) NULL,
		mid_codigoseguranca VARCHAR(4) NULL DEFAULT NULL,
                mid_valorlim FLOAT(10,2) NULL,
		PRIMARY KEY (mid_id , cli_id),
		INDEX idx_MID_cliente (cli_id ASC),
		CONSTRAINT fk_midia_cliente FOREIGN KEY (cli_id)
			REFERENCES cliente (cli_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION
	)  ENGINE=InnoDB;


	-- -----------------------------------------------------
	-- Table'bdic3'.'sistema_operacional'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS sistema_operacional;

	CREATE TABLE IF NOT EXISTS sistema_operacional (
		sis_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		sis_nome VARCHAR(45) NULL,
		sis_versao VARCHAR(45) NULL,
		sis_movel VARCHAR(45) NULL
	)  ENGINE=InnoDB;


	-- -----------------------------------------------------
	-- Table'bdic3'.'navegador'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS navegador;

	CREATE TABLE IF NOT EXISTS navegador (
		nav_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		nav_nome VARCHAR(45) NULL,
		nav_versao VARCHAR(45) NULL
	)  ENGINE=InnoDB;


	-- -----------------------------------------------------
	-- Table'bdic3'.'sessao'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS sessao;

	CREATE TABLE IF NOT EXISTS sessao (
		ses_id INT NOT NULL AUTO_INCREMENT,
		ses_data VARCHAR(45) NULL,
		ses_hora VARCHAR(45) NULL,
		cli_id INT NULL,
		sis_id INT NULL,
		nav_id INT NULL,
		PRIMARY KEY (ses_id , cli_id , sis_id , nav_id),
		INDEX idx_sessao_cliente1 (cli_id ASC),
		INDEX idx_sessao_sistema_operacional1 (sis_id ASC),
		INDEX idx_sessao_navegador1 (nav_id ASC),
		CONSTRAINT fk_sessao_cliente1 FOREIGN KEY (cli_id)
			REFERENCES cliente (cli_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION,
		CONSTRAINT fk_sessao_sistema_operacional1 FOREIGN KEY (sis_id)
			REFERENCES sistema_operacional (sis_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION,
		CONSTRAINT fk_sessao_navegador1 FOREIGN KEY (nav_id)
			REFERENCES navegador (nav_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION
	)  ENGINE=InnoDB;



	-- -----------------------------------------------------
	-- Table `bdic3`.`etapa_meio_pagamento`
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS etapa_meio_pagamento;

	CREATE TABLE IF NOT EXISTS meio_pagamento (
	  mep_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	  mep_descricao VARCHAR(45) NULL DEFAULT NULL

	)  ENGINE = InnoDB;


	-- -----------------------------------------------------
	-- Table'bdic3'.'transacao'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS transacao;

	CREATE TABLE IF NOT EXISTS transacao (
		tra_id INT NOT NULL AUTO_INCREMENT,
		tra_tipo VARCHAR(50) NULL,
		tra_total FLOAT NULL,
		tra_data_hora VARCHAR(50) NULL,
		etb_id INT(11) NULL,
		cli_id INT(11) NULL,
		mid_id INT(11) NULL,
		loc_id INT(11) NULL COMMENT 'tambem conhecido como tra_geolocalizacao',
		ses_id INT(11) NULL,
		tra_descricao_pagamento VARCHAR(200) NULL DEFAULT NULL,
		tra_validade VARCHAR(45) NULL DEFAULT NULL,
		tra_url_pagamento VARCHAR(150) NULL DEFAULT NULL,
		tra_status VARCHAR(10) NULL,
		tra_quantidadeparcela INT(11) NULL DEFAULT NULL,
		mep_id INT(11) NULL,
		tra_confirmed VARCHAR(1) NULL DEFAULT NULL,
		PRIMARY KEY (tra_id , etb_id , cli_id , mid_id , loc_id , ses_id),
		INDEX idx_transacao_estabelecimento (etb_id ASC),
		INDEX idx_transacao_cliente (cli_id ASC),
		INDEX idx_transacao_midia (mid_id ASC),
		INDEX idx_transacao_sessao1 (ses_id ASC),
		INDEX fk_transacao_GEOLOCALIZACAO (loc_id ASC),
		INDEX fk_transacao_meio_pagamento1_idx (mep_id ASC),
		CONSTRAINT fk_transacao_estabelecimento FOREIGN KEY (etb_id)
			REFERENCES estabelecimento (etb_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION,
		CONSTRAINT fk_transacao_cliente FOREIGN KEY (cli_id)
			REFERENCES cliente (cli_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION,
		CONSTRAINT fk_transacao_midia FOREIGN KEY (mid_id)
			REFERENCES midia (mid_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION,
		CONSTRAINT fk_transacao_GEOLOCALIZACAO FOREIGN KEY (loc_id)
			REFERENCES localidade (loc_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION,
		CONSTRAINT fk_transacao_sessao1 FOREIGN KEY (ses_id)
			REFERENCES sessao (ses_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION,
		CONSTRAINT fk_meio_pagamento1 FOREIGN KEY (mep_id)
			REFERENCES meio_pagamento (mep_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION
	)  ENGINE=InnoDB COMMENT='MID_mid_id';


	-- -----------------------------------------------------
	-- Table'bdic3'.'fraude'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS fraude;

	CREATE TABLE IF NOT EXISTS fraude (
		fra_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		fra_nome VARCHAR(250) NULL,
		fra_tipo VARCHAR(100) NULL,    
		fra_forma_deteccao VARCHAR(45) NULL,
		fra_data_deteccao VARCHAR(45) NULL,
		tra_id INT NULL,
		INDEX idx_FRA_transacao (tra_id ASC),
		CONSTRAINT fk_FRA_transacao FOREIGN KEY (tra_id)
			REFERENCES transacao (tra_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION
	)  ENGINE=InnoDB;


	-- ----------------------------------------------------- 
	-- Table'bdic3'.'produto'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS produto;

	CREATE TABLE IF NOT EXISTS produto (
	   prd_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	   prd_nome VARCHAR(45),
       prd_preco_normal FLOAT (10,2)
    )  ENGINE = InnoDB;


	-- ----------------------------------------------------- 
	-- Table'bdic3'.'pedido'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS pedido;

    CREATE TABLE IF NOT EXISTS pedido (
  		ped_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	  	ped_dt VARCHAR(50) NULL,
	  	cli_id INT NULL,
        tra_id INT NULL,
	  	INDEX FK_PEDIDO_CLIENTE (cli_id ASC),
	  	CONSTRAINT FK_PEDIDO_CLIENTE
	    	FOREIGN KEY (cli_id)
	    	REFERENCES cliente (cli_id)
	    	ON DELETE NO ACTION
	    	ON UPDATE NO ACTION
        CONSTRAINT FK_PEDIDO_TRANSACAO
	    	FOREIGN KEY (tra_id)
	    	REFERENCES transacao (tra_id)
	    	ON DELETE NO ACTION
	    	ON UPDATE NO ACTION
	) 	ENGINE = InnoDB;

	-- -----------------------------------------------------
	-- Table `bdic3db`.`PEDIDO_HAS_PRODUTO`
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS pedido_has_produto;

	CREATE TABLE IF NOT EXISTS pedido_has_produto (
		ped_has_prod_id INT NOT NULL AUTO_INCREMENT,
	  	ped_id INT,
	  	prd_id INT,
	 	ped_has_prod_qtd INT(11),
	  	ped_has_prod_preco_unit_reg DOUBLE,
	  	PRIMARY KEY (ped_has_prod_id),
	  	INDEX FK_PEDIDO_HAS_PRODUTO_PRODUTO (prd_id ASC),
	  	INDEX FK_PEDIDO_HAS_PRODUTO_PEDIDO (ped_id ASC),
	  	CONSTRAINT FK_PEDIDO_HAS_PRODUTO_PEDIDO
	    	FOREIGN KEY (ped_id)
	    	REFERENCES pedido (ped_id)
	    	ON DELETE NO ACTION
	    	ON UPDATE NO ACTION,
	  	CONSTRAINT FK_PEDIDO_HAS_PRODUTO_PRODUTO
		    FOREIGN KEY (prd_id)
		    REFERENCES produto (prd_id)
		    ON DELETE NO ACTION
		    ON UPDATE NO ACTION
	)  	ENGINE = InnoDB;


	-- -----------------------------------------------------
	-- Table `bdic3db`.`pagamento`
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS pagamento;

	CREATE TABLE IF NOT EXISTS pagamento (
	  pag_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	  pag_forma_pagamento VARCHAR(45) NULL,
	  pag_parcelas INT(11) NULL,
	  pag_dt VARCHAR(50) NULL,
	  pag_valor_pagamento FLOAT(10,2) NULL,
	  ped_id INT NULL,
      loc_id INT NULL,
	  mid_id INT NULL,
	  INDEX FK_PAGAMENTO_PEDIDO (ped_id ASC),
	  INDEX FK_PAGAMENTO_LOCALIDADE (loc_id ASC),
	  INDEX FK_PAGAMENTO_MIDIA (mid_id ASC),
	  CONSTRAINT FK_PAGAMENTO_PEDIDO
	    FOREIGN KEY (ped_id)
	    REFERENCES pedido (ped_id)
	    ON DELETE NO ACTION
	    ON UPDATE NO ACTION,
	  CONSTRAINT FK_PAGAMENTO_LOCALIDADE
	    FOREIGN KEY (loc_id)
	    REFERENCES localidade (loc_id)
	    ON DELETE NO ACTION
	    ON UPDATE NO ACTION,
	  CONSTRAINT FK_PAGAMENTO_MIDIA
	    FOREIGN KEY (mid_id)
	    REFERENCES midia (mid_id)
	    ON DELETE NO ACTION
	    ON UPDATE NO ACTION
	)  ENGINE = InnoDB;

	-- ----------------------------------------------------- 
	-- Table'bdic3'.'transacao_produto'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS transacao_produto;

	CREATE TABLE IF NOT EXISTS transacao_produto (
		trp_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		prd_id INT NULL,
		tra_id INT NULL,
		INDEX fk_transacao_produto_transacao1_idx (tra_id ASC),
		INDEX fk_transacao_produto_produto1_idx (prd_id ASC),
		CONSTRAINT fk_transacao_produto_transacao1
		  FOREIGN KEY (tra_id)
		  REFERENCES transacao (tra_id)
		  ON DELETE NO ACTION
		  ON UPDATE NO ACTION,
		CONSTRAINT fk_transacao_produto_produto1
		  FOREIGN KEY (prd_id)
		  REFERENCES produto (prd_id)
		  ON DELETE NO ACTION
		  ON UPDATE NO ACTION
	)  ENGINE=InnoDB;


	-- -----------------------------------------------------
	-- Table'bdic3'.'meio_pagamento_estabelecimento'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS meio_pagamento_estabelecimento;

	CREATE TABLE IF NOT EXISTS meio_pagamento_estabelecimento (
		etb_id INT NOT NULL AUTO_INCREMENT,
		mep_id INT NULL,
		mpe_token VARCHAR(60) NULL,
		PRIMARY KEY (etb_id , mep_id),
		INDEX idx_meio_pagamento_ETB_meio_pagamento1 (mep_id ASC),
		CONSTRAINT fk_meio_pagamento_ETB_estabelecimento1 FOREIGN KEY (etb_id)
			REFERENCES estabelecimento (etb_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION,
		CONSTRAINT fk_meio_pagamento_ETB_meio_pagamento1 FOREIGN KEY (mep_id)
			REFERENCES meio_pagamento (mep_id)
			ON DELETE NO ACTION ON UPDATE NO ACTION
	)  ENGINE=InnoDB;


	-- -----------------------------------------------------
	-- Table'bdic3'.'etapa_transacao'
	-- -----------------------------------------------------
	DROP TABLE IF EXISTS etapa_transacao;

	CREATE TABLE IF NOT EXISTS etapa_transacao (
	  tra_id INT NULL,
	  etb_id INT NULL,
	  mep_id INT NULL,
	  cli_id INT NULL,
	  ett_datahora VARCHAR(15) NULL,
	  ett_statuso VARCHAR(10) NULL,
	  ett_detalhe_historico VARCHAR(255) NULL COMMENT 'pode armazenar o código do comprovante',
	  PRIMARY KEY (tra_id , etb_id , mep_id , cli_id),
	  INDEX fk_etapatransacao_estabelecimento1_idx (etb_id ASC),
	  INDEX fk_etapatransacao_meio_pagamento1_idx (mep_id ASC),
	  INDEX fk_etapatransacao_cliente1_idx (cli_id ASC),
	  CONSTRAINT fk_etapatransacao_transacao1 FOREIGN KEY (tra_id)
		REFERENCES transacao (tra_id)
		ON DELETE NO ACTION ON UPDATE NO ACTION,
	  CONSTRAINT fk_etapatransacao_estabelecimento1 FOREIGN KEY (etb_id)
		REFERENCES estabelecimento (etb_id)
		ON DELETE NO ACTION ON UPDATE NO ACTION,
	  CONSTRAINT fk_etapatransacao_meio_pagamento1 FOREIGN KEY (mep_id)
		REFERENCES meio_pagamento (mep_id)
		ON DELETE NO ACTION ON UPDATE NO ACTION,
	  CONSTRAINT fk_etapatransacao_cliente1 FOREIGN KEY (cli_id)
		REFERENCES cliente (cli_id)
		ON DELETE NO ACTION ON UPDATE NO ACTION
	) ENGINE = InnoDB
