contexto:
Este projeto Vacina COVID-19 é um sistema desenvolvido para gerenciar a vacinação contra a COVID-19. Os agente de Saúde cadastram os usuários do SUS dos quais é responsável, e no momento da vacinação no drivethru, eles serão identificados e imunizados. O sistema registrará doses as doses ministradas.

Este sistema foi desenvolvido para computadores desktop e notebooks, e pode ser executado em qualquer sistema operacional que tenha uma máquina virtual Java instalada, e os dados são perssistidos em um SGDB postgresql disponibilizado em nuvem (SaaS).

requisitos: JDK8 ou superior, Uma servidor em nuvem com SGDB postgresql 10.

Instalação: após provisionar um serviço RDBS e criado o banco de dados 'vacina' deve ser acessado pelo pgadmin e executar o script sql do arquivo base.sql para criar as tabelas e registros iniciais. Em cada computador que será utilizado fazer um git clone do projeto e executar o arquivo Main.java