CREATE TABLE ubs(
id serial PRIMARY KEY NOT NULL,
nomeubs character varying NOT NULL);

CREATE TABLE acs(
id serial PRIMARY KEY NOT NULL,
nomeacs character varying NOT NULL,
cdubs integer NOT NULL,
CONSTRAINT fk_ubs FOREIGN KEY(cdubs)
REFERENCES ubs(id)
ON DELETE NO ACTION
ON UPDATE NO ACTION);

CREATE TABLE vacinantes(
id serial PRIMARY KEY NOT NULL,
nome character varying,
dtnasc DATE NOT NULL,
idade integer,
endereco text NOT NULL,
cpf char(11) NOT NULL,
nomemae character varying NOT NULL,
cdacs integer NOT NULL,
repspreen character varying NOT NULL,
cargo character varying NOT NULL,
CONSTRAINT fk_acs FOREIGN KEY(cdacs)
REFERENCES acs(id)
ON DELETE NO ACTION
ON UPDATE NO ACTION);
