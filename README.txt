Instruções para Rodar esta DEMO - Backend
-----------------------------------------

1 - Instalar MongoDB
   1.1 - Acessar a URL https://www.mongodb.com/download-center/community
   1.2 - Selecionar Versão 4.2.1 (current), OS e Package
   1.3 - Realizar o Download e instalar o pacote

2 - Subir MongoDB
   2.1 - criar a pasta de dados do mongodb (Ex. c:\data\db)
   2.2 - Executar arquivo C:\Program Files\MongoDB\Server\4.2\bin\mongod.exe
   https://spring.io/guides/gs/accessing-data-mongodb/

3 - Baixar a Aplicação do GIT
  https://github.com/rfbellani/carrinho

4 - Abrir aplicação na IDE Eclipse

5 - Atualizar as Dependências do MAVEN

6 - Executar a aplicação pela IDE Eclipse

   
ESTRATÉGIA ADOTADA
   - Backend utilizando Springboot , com Appserver Jetty persistindo em MongoDB
   - Backend disponilizando APIs para usuarios, Itens e Carrinho de Compras
   - Utilizado Maven para controle de Dependências
   - Validação de Unicidade de Registros:
     - Usuário - Através do campo "e-mail"
     - Item - Através do campo "nome"
   - Utilizado Jacoco para avaliação da Cobertura de Testes
   - A classe Configuracao.java inicia o SpringBoot e gera uma massa inicial de testes
   - Massa de Carrinho de Compras gerada para Cliente com e-mail firma@teste.com
     
   - Nesta primeira versão alguns itens não foram abordados:
     - Tratamento de Exception aplicado somente no valor do Item
     - health_check do backend
     - Controle de autenticação de usuários (apenas cadastramento único por e-mail informado)
     - Aplicação não foi Dockerizada e não foi publicada em Cloud
     - Cobertura de testes não atingiu o percentual de 60% (Utilizado Jacoco para aferição)
     - Não foi gerado Empacotamento do Projeto
     
     
Instruções para Rodar esta DEMO - Frontend
-------------------------------------------
1 - Instalar a ultima versão do NodeJS
2 - Instalar a o Angular CLI com o comando:
    npm  install -g @angular/cli
    
3 - Baixar o Projeto do GIT
    https://github.com/rfbellani/carrinho-front
    
4-  ir para a pasta do projeto e subir a aplicação
    ng serve --open
        
ESTRATÉGIA ADOTADA
  - Utilização do Angular CLI para criação do Projeto
  - Utilizado bootstrap para estilo das páginas 
  - Criada uma página inicial de Menu com as Opções de Usuario, Item e Carrinho
  - A aplicação do Front interage com o Backend através do acesso à APIs
  - Utilizado Visual Studio Code para desenvolvimento do frontend
  - Alguns logs foram mantidos no código 
  
  - Nesta primeira versão alguns itens não foram abordados:
    - Não foram incluidas todas as validações nos campos das páginas
    - Não estão sendo exibidos nas páginas os erros retornados do Backend
    - Não foi gerado empacotamento do projeto
    

     

