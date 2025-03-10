# Vacina COVID-19

## Contexto
O projeto **Vacina COVID-19** é um sistema desenvolvido para gerenciar a vacinação contra a COVID-19. Os agentes de saúde cadastram os usuários do SUS pelos quais são responsáveis, e no momento da vacinação no **drive-thru**, eles serão identificados e imunizados. O sistema registrará as doses ministradas.

Este sistema foi desenvolvido para **computadores desktop e notebooks** e pode ser executado em qualquer sistema operacional que tenha uma **máquina virtual Java** instalada. Os dados são persistidos em um **SGDB PostgreSQL** disponibilizado em nuvem (**SaaS**).

---

## Requisitos
- **JDK 8** ou superior.
- Um **servidor em nuvem** com **SGDB PostgreSQL 10**.

---

## Instalação
### Configuração do Banco de Dados
1. **Provisionar um serviço RDBS** (PostgreSQL 10) na nuvem.
2. **Criar o banco de dados** chamado `vacina`.
3. **Acessar o banco** utilizando o **pgAdmin** ou qualquer outro cliente PostgreSQL.
4. **Executar o script SQL** contido no arquivo `base.sql` para criar as tabelas e registros iniciais.

### Configuração do Sistema
1. **Clonar o repositório** em cada computador que será utilizado:
   ```bash
   git clone https://github.com/seuusuario/vacina-covid.git
   ```
2. **Navegar até a pasta do projeto**:
   ```bash
   cd vacina-covid
   ```
3. **Executar o arquivo principal**:
   ```bash
   java -cp . Main
   ```

---

## Uso
- **Cadastro de usuários do SUS** pelos agentes de saúde.
- **Registro das vacinações** no drive-thru.
- **Consulta e acompanhamento** das doses ministradas.

---

## Tecnologias Utilizadas
- **Java 8+**
- **PostgreSQL 10**
- **pgAdmin** (para administração do banco de dados)

---

## Contribuição
Caso queira contribuir com melhorias no projeto, siga as etapas:
1. Faça um **fork** do repositório.
2. Crie uma **branch** para suas alterações:
   ```bash
   git checkout -b minha-melhoria
   ```
3. Realize as alterações e faça **commit**:
   ```bash
   git commit -m "Descrição da melhoria"
   ```
4. Faça um **push** para o seu fork:
   ```bash
   git push origin minha-melhoria
   ```
5. Abra um **Pull Request** no repositório principal.

---

## Licença
Este projeto é licenciado sob a [MIT License](LICENSE).
