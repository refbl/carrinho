Instuções para Rodar esta DEMO

1 - Instalar MongoDB
   1.1 - Acessar a URL https://www.mongodb.com/download-center/community
   1.2 - Selecionar Versão 4.2.1 (current), OS e Package
   1.3 - Realizar o Download e instalar o pacote

2 - Subir MongoDB
   2.1 - criar a pasta de dados do mongodb (Ex. c:\data\db)
   2.2 - Executar arquivo C:\Program Files\MongoDB\Server\4.2\bin\mongod.exe
   https://spring.io/guides/gs/accessing-data-mongodb/
   
ESTRATÉGIA ADOTADA
   - Backend utilizando Springboot , com Appserver Jetty persistindo em MongoDB
   - Backend expondo APIs para usuarios, Itens e Carrinho de Compras
   - Utilizado Maven para controle de Dependências
   - Validação de Unicidade de Registros:
     - Usuário - Através do campo "e-mail"
     - Item - Através do campo "nome"
   - Por conta do prazo nesta primeira versão alguns itens não foram abordados:
     - health_check do backend
     - Controle de usuários mais aprimorado (verificação através do campo e-mail)

