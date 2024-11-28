# Projeto de Login

## O que é este projeto?
Este projeto é um exemplo básico de como fazer login usando Java e MySQL. Ele se conecta a um banco de dados para verificar se o nome de usuário e a senha estão corretos.

---

## Problemas encontrados no código
Enquanto revisava o código, encontrei algumas coisas que poderiam ser melhoradas:

1. **Segurança:**
   - A forma como o código verifica o login e a senha pode permitir um ataque chamado "SQL Injection". Isso acontece porque os dados são colocados direto na consulta, sem nenhuma proteção.
   - **O que fazer para melhorar:** Usar algo chamado `PreparedStatement`, que ajuda a evitar esses ataques.

2. **Fechar conexões com o banco:**
   - Se algo der errado, o programa pode não fechar a conexão com o banco de dados.
   - **O que fazer para melhorar:** Usar um jeito especial de lidar com recursos chamado `try-with-resources`, que fecha tudo sozinho.

3. **Credenciais no código:**
   - A senha do banco de dados está escrita direto no código. Isso não é bom porque alguém que vê o código também pode ver a senha.
   - **O que fazer para melhorar:** Guardar as informações de conexão (usuário, senha, etc.) em outro lugar, como um arquivo separado ou variáveis do sistema.

4. **Erro sem explicação:**
   - O programa só mostra mensagens genéricas quando algo dá errado. Poderia ser mais claro para quem está usando.
   - **O que fazer para melhorar:** Mostrar mensagens mais detalhadas ou guardar os erros em um registro.

---

## Como usar este projeto
1. Instale o MySQL e crie um banco de dados chamado `test`.
2. Adicione uma tabela chamada `usuarios` e insira um usuário para teste:
   ```sql
   CREATE TABLE usuarios (
       id INT AUTO_INCREMENT PRIMARY KEY,
       login VARCHAR(50) NOT NULL,
       senha VARCHAR(50) NOT NULL,
       nome VARCHAR(100) NOT NULL
   );

   INSERT INTO usuarios (login, senha, nome) VALUES ('usuarioTeste', 'senhaTeste', 'Teste User');


## Compile o programa no terminal:

javac -cp "lib/*" src/login/User.java

## Execute o programa:

java -cp "src:lib/*" login.User

