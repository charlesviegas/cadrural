insert into TB_PRODUTOR(ID, TIPO_PESSOA, CPFCNPJ, NOMERAZAO, INSCESTADUAL, LOGRADOURO, NUMERO, COMPLEMENTO, MUNICIPIO, ESTADO, CEP, TELEFONE, EMAIL) values (NEXTVAL('SEQ_PRODUTOR'), 'JURIDICA', '12345678901234', 'AKE BERNARD VAN DER VINNE', '123456789', 'Rua Maria Rita','SN' , 'AP 207', 'Maracaju', 'MS', '79033200', '6744872581', 'barros@zica.com.br');
insert into TB_PRODUTOR(ID, TIPO_PESSOA, CPFCNPJ, NOMERAZAO, INSCESTADUAL, LOGRADOURO, NUMERO, COMPLEMENTO, MUNICIPIO, ESTADO, CEP, TELEFONE, EMAIL) values (NEXTVAL('SEQ_PRODUTOR'), 'JURIDICA', '12345678901234', 'AKE BERNARD VAN DER VINNE', '123456789', 'Rua Maria Rita','SN' , 'AP 207', 'Maracaju', 'MS', '79033200', '6744872581', 'barros@zica.com.br');

insert into TB_FAZENDA(ID, AREA, ESTADO, MATRICULA, MUNICIPIO, NOME, ID_PRODUTOR) values (NEXTVAL('SEQ_FAZENDA'), 12.34, 'MS', '1234455', 'Campo Grande', 'Santo Antônio', CURRVAL('SEQ_PRODUTOR'));
insert into TB_FAZENDA(ID, AREA, ESTADO, MATRICULA, MUNICIPIO, NOME, ID_PRODUTOR) values (NEXTVAL('SEQ_FAZENDA'), 29.34, 'MS', '1234455', 'Campo Grande', 'Cantagalo1', CURRVAL('SEQ_PRODUTOR'));