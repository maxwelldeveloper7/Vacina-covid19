

# 💉 Vacina COVID-19 - Sistema de Gerenciamento de Vacinação
<div align='center'><img src='./src/vacinacovid/visao/vacina.png'/></div>

Este projeto foi desenvolvido para facilitar o gerenciamento da vacinação contra a **COVID-19**. O sistema permite que **agentes de saúde** cadastrem usuários do **SUS** sob sua responsabilidade e realizem a identificação e imunização dos cidadãos no momento da vacinação via **drive-thru**.  

A solução foi projetada para **desktops e notebooks**, sendo compatível com qualquer sistema operacional que possua uma **Máquina Virtual Java (JVM)** instalada. Os dados são armazenados em um **banco de dados PostgreSQL** hospedado na nuvem.  

## 🚀 Funcionalidades  

✔ Registro e gerenciamento de vacinantes  
✔ Identificação de cidadãos no drive-thru  
✔ Controle da administração de doses  
✔ Registro detalhado de imunizações  

## 📋 Pré-requisitos  

Antes de iniciar, certifique-se de atender aos seguintes requisitos:  

- [Java JDK 8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html) ou superior instalado  
- [PostgreSQL 10](https://www.postgresql.org/download/) ou superior provisionado em nuvem  
- Ferramenta [pgAdmin](https://www.pgadmin.org/) para gerenciar o banco de dados  

## 🛠️ Configuração do Banco de Dados  

1. Crie um banco de dados chamado **vacina** no PostgreSQL.  
2. Acesse o **pgAdmin** e execute o script `base.sql` para criar as tabelas e registros iniciais.  

## 🏗️ Estrutura do Projeto  

A estrutura do projeto foi organizada para garantir **modularidade e escalabilidade**, seguindo boas práticas de desenvolvimento.  

```
arquivos/         # Pasta de recursos do sistema (equivalente a assets)
 ├── src/         # Código-fonte do sistema
 │   ├── controle/      # Camada de controle da aplicação
 │   │   └── Controle.java
 │   ├── dao/           # Objetos de acesso aos dados
 │   │   ├── AcsDAO.java  
 │   │   ├── ConexaoPostgres.java  
 │   │   ├── DataBasePersistence.java  
 │   │   ├── GenericDAO.java  
 │   │   └── VacinanteDAO.java
 │   ├── modelo/        # Classes de modelo (representação dos dados)
 │   │   ├── AcsBean.java  
 │   │   ├── LaboratorioBean.java  
 │   │   ├── UbsBean.java  
 │   │   └── VacinanteBean.java
 │   ├── visao/         # Camada de apresentação (interface gráfica)
 │   │   ├── cancelar.png  # Recursos gráficos (ícones, logos, etc.)
 │   │   ├── coronavirus.png  
 │   │   ├── inserir.png  
 │   │   ├── report.png  
 │   │   ├── vacina.png  
 │   │   ├── Nanuque-logo.jpeg  
 │   │   ├── salvar.png
 │   │   ├── password.png  
 │   │   ├── FrmAcesso.java  # Telas do sistema
 │   │   ├── Principal.java  
 │   │   ├── PrincipalGestor.java
 │   │   ├── FrmCadVacinante.java  
 │   │   ├── FrmSelecionaAcs.java  
 │   │   ├── FrmAlteraVacinante.java  
 │   │   ├── FrmCadVacinanteIrrestrito.java  
 │   │   ├── FrmSelecionaUbs.java  
 │   │   ├── GenericJDialog.java
 │   ├── util/          # Classes utilitárias do sistema
 │   │   └── Utilidades.java
 │   ├── Main.java      # Classe principal para execução do sistema
 ├── README.md      # Documentação do projeto
 ├── .gitignore     # Arquivos a serem ignorados pelo Git
 ├── LICENSE        # Licença de uso
 ├── configuracoes.properties        # Configurações
 ├── manifest.mf        # Configurações de build
```

## 🔗 Dependências  

| Dependência | Descrição |
|-------------|-----------------------------------------------------------|
| [PostgreSQL JDBC Driver](https://jdbc.postgresql.org/) | Driver para conexão com o banco PostgreSQL |
| [JDK 8+](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html) | Ambiente de desenvolvimento Java |
| [pgAdmin](https://www.pgadmin.org/) | Gerenciador gráfico para PostgreSQL |


## 🎯 Executando a Aplicação  

1. **Clone o repositório:**  
   ```bash
   git clone https://github.com/seuusuario/vacina-covid19.git
   ```
2. **Configure o banco de dados:**  
   - Acesse o **pgAdmin**  
   - Crie o banco de dados `vacina`  
   - Execute o script `base.sql` para criar as tabelas  

3. **Compile e execute o projeto:**  
   ```bash
   javac Main.java
   java Main
   ```

## 🤝 Contribuindo  

Este repositório foi criado para fins educacionais e pode ser aprimorado com sugestões da comunidade. Se você deseja contribuir, siga os passos abaixo:  

1. **Faça um fork do projeto**  
2. **Crie uma branch para sua funcionalidade:**  
   ```bash
   git checkout -b minha-nova-feature
   ```
3. **Faça as alterações e commit:**  
   ```bash
   git commit -m "Adicionando nova funcionalidade"
   ```
4. **Envie para o repositório:**  
   ```bash
   git push origin minha-nova-feature
   ```
5. **Abra um Pull Request! 🚀**  

---

Desenvolvido por [Maxwell de Oliveira Chaves](https://www.linkedin.com/in/maxwell-oliveira-chaves/)  

Se este projeto foi útil para você, **não se esqueça de deixar uma ⭐!** 🚀  
