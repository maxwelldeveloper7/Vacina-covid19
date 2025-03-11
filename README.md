<img src="http://img.shields.io/static/v1?label=License&message=MIT&color=green&style=for-the-badge"/>

<img src="http://img.shields.io/static/v1?label=STATUS&message=%20FINALIZADO&color=critical&style=for-the-badge"/>

# Vacina COVID-19

## Contexto
O projeto **Vacina COVID-19** é um sistema desenvolvido para gerenciar a vacinação contra a COVID-19. Os agentes de saúde cadastram os usuários do SUS pelos quais são responsáveis, e no momento da vacinação no **drive-thru**, eles serão identificados e imunizados. O sistema registrará as doses ministradas.

Este sistema foi desenvolvido para computadores **desktop** e **notebooks**, e pode ser executado em qualquer sistema operacional que tenha uma [Máquina Virtual Java (JVM)](https://www.java.com/pt-BR/) instalada. Os dados são persistidos em um banco de dados **PostgreSQL** disponibilizado em nuvem (SaaS).

## Funcionalidades
- Cadastro de usuários do SUS
- Identificação e registro de vacinação
- Registro de doses ministradas
- Persistência de dados em nuvem

## Pré-requisitos
- [JDK 8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html) ou superior instalado
- Um servidor em nuvem com [SGDB PostgreSQL 10](https://www.postgresql.org/docs/10/)

## Configuração do Banco de Dados
Antes de executar a aplicação, certifique-se de criar o banco de dados e as tabelas necessárias no PostgreSQL executando o script SQL contido no arquivo `base.sql`.

## Configuração da Aplicação
1. Clone este repositório para o seu ambiente local:
   ```bash
   git clone https://github.com/seuusuario/vacina-covid.git
   ```
2. Acesse o banco de dados pelo [pgAdmin](https://www.pgadmin.org/) e execute o script `base.sql`.
3. Configure a conexão no código, substituindo os valores necessários:
   ```java
   private static final String URL = "jdbc:postgresql://seu-servidor:5432/vacina";
   private static final String USER = "seu_usuario";
   private static final String PASSWORD = "sua_senha";
   ```
4. Execute a classe `Main.java` para iniciar a aplicação.

## 🛠️ Estrutura do Projeto
### Pacote `controle`
- `Controle.java`: Classe responsável pelo controle das operações principais.

### Pacote `daos`
- `AcsDAO.java`
- `ConexaoPostgres.java`
- `DataBasePersistense.java`
- `GenericDAO.java`
- `VacinanteDAO.java`

### Pacote `modelo`
- `AcsBean.java`
- `LaboratorioBean.java`
- `UbsBean.java`
- `VacinanteBean.java`

### Pacote `visao`
- Arquivos de interface gráfica e imagens da aplicação.

### Outros Arquivos
- `Main.java`: Classe principal do projeto.
- `Utilidades.java`: Métodos utilitários usados no sistema.

## 📌 Técnicas Utilizadas
- **Arquitetura MVC** (Model-View-Controller) para separação de responsabilidades.
- **Persistência de dados** utilizando PostgreSQL.
- **JDBC** para conexão com o banco de dados.
- **Interface gráfica** baseada em Java Swing.

## 🤝 Contribuindo
Este repositório foi criado para fins de estudo. Fique à vontade para contribuir!

Se deseja contribuir com este projeto, abra issues e pull requests no repositório do GitHub.

Se possível:
- ⭐️ Dê Star para o projeto
- 🐛 Encontrar e relatar issues

---

Disponibilizado 🚀 por [Seu Nome](https://www.linkedin.com/in/seu-perfil/).
