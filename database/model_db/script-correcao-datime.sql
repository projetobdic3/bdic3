UPDATE transacao  
SET tra_data_hora = STR_TO_DATE(concat(tra_data, ' ', tra_hora), '%d/%m/%Y %H:%i:%s');
