CREATE TABLE ubs(
id serial PRIMARY KEY NOT NULL,
nomeubs character varying NOT NULL);

CREATE TABLE acs(
id serial PRIMARY KEY NOT NULL,
nomeacs character varying NOT NULL,
senhaacesso character varying default '123456',
cdubs integer NOT NULL,
CONSTRAINT fk_ubs FOREIGN KEY(cdubs)
REFERENCES ubs(id)
ON DELETE NO ACTION
ON UPDATE NO ACTION);

CREATE TABLE laboratorios(
id serial PRIMARY KEY NOT NULL,
nomelab character varying not null,
nomevacina character varying not null);

INSERT INTO LABORATORIOS (id, nomelab, nomevacina) values (0, ' ', ' ');
INSERT INTO LABORATORIOS (nomelab, nomevacina) values ('Fiocruz', 'Astrazeneca');
INSERT INTO LABORATORIOS (nomelab, nomevacina) values ('Butantan', 'Coronavac');
INSERT INTO LABORATORIOS (nomelab, nomevacina) values ('Pfizer', 'Pfizer');


CREATE TABLE vacinantes(
id serial PRIMARY KEY NOT NULL,
nome character varying,
dtnasc date,
endereco text,
cpf char(11) unique,
cns char(15) unique, 
nomemae character varying,
cdacs integer NOT NULL,
cdlab integer NOT NULL,
status integer default 0,
primeiradose date,
segundadose date,

CONSTRAINT fk_acs FOREIGN KEY(cdacs)
REFERENCES acs(id)
ON DELETE NO ACTION
ON UPDATE NO ACTION,

CONSTRAINT fk_lab FOREIGN KEY(cdlab)
REFERENCES laboratorios(id)
ON DELETE NO ACTION
ON UPDATE NO ACTION);

INSERT INTO ubs(nomeubs) VALUES ('ESF RETA');
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'REGINA RODDRIGUES DA SILVA', 1);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'ELIZABETH ANDRADE DE ARAUJO LIMA', 1);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'ARYDNA FERREIRA DOS SANTOS', 1);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'OFELIA SOARES DOS SANTOS', 1);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'MERCIA VIEIRA DO NASCIMENTO', 1);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'MARIA DE FATIMA SANTOS COSTA', 1);

INSERT INTO ubs(nomeubs) VALUES ('ESF GETULIO VARGAS');
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'ADRIANA COSTA DA SILVA', 2);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'ANTONIA PEREIRA FERNANDES', 2);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'DANIEL PINHEIRO MELO', 2);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'EDICLECIA OLIVEIRA RODRIGUES', 2);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'LEILA SUELY MENDES DA SILVA', 2);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'LEILIAN MARIA BARBOSA', 2);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'LUCINEIDE DOS SANTOS PESSOA', 2);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'MARIA DAS DORES CHAVES DOS SANTOS', 2);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'RHANNA JHENIFFER ASSIS LIMA', 2);

INSERT INTO ubs(nomeubs) VALUES ('ESF VILA NOVA');
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'CLAUDIA DE JESUS PEREIRA', 3);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'GEORGEA ANDREZZA RIBEIRO SANTOS', 3);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'GILDETE FERNANDES DE BELSON SANTOS', 3);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'JOELIANE SILVA OLIVEIRA', 3);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'JOELZA SILVA OLIVEIRA', 3);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'LUCAS DUARTE DOS SANTOS', 3);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'LUCIENE SOUZA CORDEIRO FEITOSA', 3);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'MARESSA SOUZA GUIMARAES', 3);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'SANDRA SOUZA FRANÇA', 3);


INSERT INTO ubs(nomeubs) VALUES ('ESF VILA ESPERANÇA');
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'EMANUELE SOUZA CRUZ', 4);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'ELIANE BATISTA DOS SANTOS', 4);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'LAINE PEREIRA DOS SANTOS', 4);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'FABIO ALMEIDA DOS SANTOS', 4);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'MAGDA GEISA SILVA SANTOS', 4);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'CRISTIANE SANTANA COSTA', 4);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'FABIANA SILVA DE SOUZA JAHEL', 4);

INSERT INTO ubs(nomeubs) VALUES ('ESF LATICINIOS');
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'ANA CAROLLYNA RODRIGUES ', 5);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'ELICLEIDE ALMEIDA VIANA', 5);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'GISLANE DOS SANTOS NASCIMENTO', 5);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'MARIA APARECIDA DOS SANTOS', 5);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'NORMA ANGELICA DA SILVA', 5);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'RAONNE ALVES MENDES', 5);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'TAIS SOUZA', 5);

INSERT INTO ubs(nomeubs) VALUES ('ESF UDR');
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'ANA MARIA BARBOSARAMOS', 6);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'MARIA APARECIDA OLIVEIRA SALES', 6);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'ROSA MARQUES FERREIRA', 6);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'CARINA COSTA DA CONCEIÇÃO', 6);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'MARLUCE ALVES DA SILVA', 6);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'BEATRIZ SILVA SANTOS', 6);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'KELLY DIAS MOREIRA', 6);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'JANDRESSA DO PRADO SOUZA', 6);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'GABRIELLA GONÇALVES EVANGELISTA', 6);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'AGATHA CRISTINNE ROGRIGUES LIMA', 6);

INSERT INTO ubs(nomeubs) VALUES ('ESF CRUZEIRO');
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'CHARLES SOUZA LOPES DE OLIVEIRA', 7);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'CLEIA OLIVEIRA DE SOUZA', 7);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'DAYANNI COSTA DOS SANTOS', 7);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'FERNANDA XAVIER DA SILVA', 7);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'JOSEANE ANTUNES DE SOUZA', 7);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'KAROLINY TEIXEIRA MIRANDA', 7);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'NIRES MARQUES ALMEIDA', 7);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'THAIS MASCARENHAS FERREIRA', 7);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'VANDRE FRANÇA DE SOUZA', 7);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'VIVIANE CONSUELO DE CASTRO', 7);

INSERT INTO ubs(nomeubs) VALUES ('ESF SETE DE SETEMBRO');
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'ANDRE FERREIRA VIANA REIS', 8);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'ANNA CAROLINA GONÇALVES NUNES', 8);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'CINTIA VAZ DOS SANTOS', 8);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'DARCILENE DOS SANTOS SOARES', 8);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'FABIANA BARBOSA DIAS', 8);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'KENIA IVINA DE JESUS GOMES', 8);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'TATIULE FERNANDES DA CRUZ', 8);

INSERT INTO ubs(nomeubs) VALUES ('ESF VALDIVINA FERRAZ 1');
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'WANDERLY DOS SANTOS SILVA', 9);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'JULIANA DOS SANTOS GONÇALVES', 9);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'ELIENE MEDEIROS DOS SANTOS', 9);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'MARIELENA MEDEIROS DOS SANTOS', 9);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'LUAN DE JESUS SAUDE', 9);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'LORAYNE SANTOS FERREIRA', 9);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'LUANA RIBEIRO DOS SANTOS', 9);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'LUDMILLA SOUZA SALES', 9);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'NIVEA LIMA OLIVEIRA', 9);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'VANESSA CRISTINE DOS SANTOS ALVES', 9);

INSERT INTO ubs(nomeubs) VALUES ('ESF VALDIVINA FERRAZ 2');
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'CLAUDIA PEREIRA DE SOUZA', 10);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'DAIANE DE SOUZA ALVES', 10);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'DENISE SAMPAIO DE JESUS', 10);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'EMILY CACIQUE GUEDES', 10);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'HIAGO ALMEIDA DOS SANTOS', 10);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'KAMILA DA SILVA TOMAS', 10);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'RENATA CAMPOS DE ALMEIDA', 10);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'VALERIA MOTA SOARES', 10);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'VANDERSON NICOLAU GOMES', 10);

INSERT INTO ubs(nomeubs) VALUES ('ESF VILA GABRIEL PASSOS');
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'LUCIANA COSTA SOUZA', 11);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'LUCIENE LOPES TEIXEIRA', 11);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'ALESSANDRA CANTÃO XAVIER', 11);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'NADIA LIMA DE SOUZA', 11);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'OSIANE SILVA DE SOUZA', 11);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'VALDILENE MACHADO DE OLIVEIRA', 11);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'ERLANDIA PEREIRA DOS SANTOS', 11);


INSERT INTO ubs(nomeubs) VALUES ('ESF VILA PEREIRA');
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'ALINE VIANA DE SOUZA', 12);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'AURENICE BARBOSA NASCIMENTO', 12);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'DAYANE TRACY RUAS', 12);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'EDNA REGIS DE MATOS SANTIAGO', 12);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'GEANE RAMOS DA SILVA', 12);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'GILBERSON PATEZ DOS SANTOS', 12);
INSERT INTO acs(nomeacs, cdubs) VALUES ( 'JUCIARA DOS SANTOS PENHA', 12);

CREATE VIEW vw_vacinantes AS(
select 
    v.id,
    v.nome,
    v.dtnasc,
    date_part('year',age(v.dtnasc)) as idade,
    v.endereco,
    v.cpf,
    v.cns,
    v.nomemae,
    v.cdacs,
    a.nomeacs,
    a.senhaacesso,
    a.cdubs,
    u.nomeubs,
    v.cdlab,
    l.nomelab,
    l.nomevacina,
    v.status,
    v.primeiradose,
    v.segundadose
   FROM vacinantes v
     INNER JOIN acs a ON v.cdacs = a.id
     INNER JOIN laboratorios l on v.cdlab = l.id
     INNER JOIN ubs u ON a.cdubs = u.id
);


CREATE VIEW vw_acs as(
select

a.id,
a.nomeacs,
a.senhaacesso,
a.cdubs,
u.nomeubs

from acs a inner join ubs u on a.cdubs = u.id);