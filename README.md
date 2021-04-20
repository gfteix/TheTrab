# TheTrab
Trabalho realizado no primeiro bimestre de 2019 no curso técnico em informática.

<p align="center">
  <img src="https://github.com/gf-teixeira/TheTrab/blob/main/screenshots/Capturar1.PNG">
  <img src="https://github.com/gf-teixeira/TheTrab/blob/main/screenshots/Capturar2.PNG">
  <img src="https://github.com/gf-teixeira/TheTrab/blob/main/screenshots/Capturar3.PNG">
  <img src="https://github.com/gf-teixeira/TheTrab/blob/main/screenshots/Capturar4.PNG">

</p>

## Como rodar?

1 - Crie um banco chamado LPB no pgadmin

2 - Clique em query tool 

  <img src="https://github.com/gf-teixeira/TheTrab/blob/main/screenshots/pgadmin2.PNG">

3 - Execute as inserções (estão no arquivo SQL.txt na raiz do projeto) no pgadmin

  <img src="https://github.com/gf-teixeira/TheTrab/blob/main/screenshots/pgadmin1.PNG">

4 - abra o projeto no Netbeans, no arquivo src/java/model/Banco.java altere a seguinte linha com o seu usuário e senha do postgres:
 
 `conexao=DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/LPB","seu-usuario","sua-senha");`

5 - execute o projeto no Netbeans

## Tecnologias

- HTML
- CSS
- Java
- Servlet
- PostgreSQL
