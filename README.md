# pauta-api
Projeto teste para backend

Com base no desafio foi feito uma API RestFull com Spring Boot
O banco de dados usado foi o MySQL: Usuario e senha estão no application.properties (usuario e senha sao root)
Foi utilizado o Flyway para criação das tabelas no banco, não foi optado pela criação pela JPA.
A APi tem baseicamente dois controllers:
  O controller de pautas com os seguinters endpints
    Criar nova pauta (gera a data de validade com base nos minutos definidos no applicatio.properties)
    Listar pautas
    Atualizar data validade para votação
  O controller de votação com os seguintes endpoints
    Votar Sim
    Votar Nao
    Estatisticas da votação
    Validar CPF
    
    Tanto o endpoint de votar sim e votar nao tem como parãmetro o CPF que tem algumas validações
    O endpoint de estatísticas mostra o estatus da votação da pauta selecionada com a quantidade de votação
    Validar CPF é a chamada da api externa para validação do CPF passado..
    
    Estrutura
     API RestFull com Spring Boot
     Banco de dados MySQL
     JPA
     Flyway
     IDE STS
     
     A PI possui uma breve documento com Swagger que mosta os endpoints e também pode ser usado para testes.
     Foi colocado 2 testes de inegração de exemplos.
     Foi realizado a chamada de API externa no heroku com RestTemplate
