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

_____________________________________

## Legenda dos Pontos Numerados

Aqui estão os principais pontos do método `verificarUsuario` identificados no grafo de fluxo:

1. **Ponto 1**: Inicializa a variável `result` como `false`.
2. **Ponto 2**: Conecta ao banco de dados chamando o método `conectarBD`.
3. **Ponto 3**: Verifica se a conexão (`conn`) é `null`.
4. **Ponto 4**: Retorna `false` se a conexão falhar.
5. **Ponto 5**: Cria a consulta SQL para buscar o usuário com login e senha.
6. **Ponto 6**: Cria um `Statement` para executar a consulta SQL.
7. **Ponto 7**: Executa a consulta SQL e obtém o `ResultSet`.
8. **Ponto 8**: Verifica se o `ResultSet` contém dados.
9. **Ponto 9**: Atualiza `result` para `true` se os dados forem encontrados.
10. **Ponto 10**: Atualiza o nome do usuário com o valor do banco.
11. **Ponto 11**: Fecha o `ResultSet`.
12. **Ponto 12**: Fecha o `Statement`.
13. **Ponto 13**: Fecha a conexão com o banco de dados.
14. **Ponto 14**: Trata exceções que podem ocorrer durante o processo.
15. **Ponto 15**: Imprime a exceção usando `e.printStackTrace()`.
16. **Ponto 16**: Retorna o valor de `result`.

## Grafo de Fluxo

O grafo abaixo representa os fluxos lógicos do método `verificarUsuario`:

## Grafo de Fluxo - 
    projeto-login/documentacao/fluoxograma.png

## Grafo de Fluxo e Pontos Numerados

O grafo de fluxo foi construído com base no método `verificarUsuario`. Ele utiliza os pontos numerados descritos acima para representar as ações, decisões e saídas do método. Isso facilita a análise da lógica e ajuda a identificar os fluxos básicos do código.

## Caminhos Básicos

Os caminhos básicos do método `verificarUsuario` são:

1. **Caminho 1**: Start → Ponto 1 → Ponto 2 → Ponto 3 (Sim) → Ponto 4 → End.
2. **Caminho 2**: Start → Ponto 1 → Ponto 2 → Ponto 3 (Não) → Ponto 5 → Ponto 6 → Ponto 7 → Ponto 14 → Ponto 15 → End.
3. **Caminho 3**: Start → Ponto 1 → Ponto 2 → Ponto 3 (Não) → Ponto 5 → Ponto 6 → Ponto 7 → Ponto 8 (Sim) → Ponto 9 → Ponto 10 → Ponto 11 → Ponto 12 → Ponto 13 → Ponto 16 → End.


## Complexidade Ciclomática

A complexidade ciclomática do método `verificarUsuario` é calculada da seguinte forma:

- **Nodos (N):** 16
- **Arestas (E):** 18
- **Componentes Conectados (P):** 1

**Fórmula:** M = E - N + 2P  
**Resultado:** M = 18 - 16 + 2(1) = **4**

A complexidade ciclomática é **4**, o que indica que existem 4 caminhos básicos no método.

---

## Caminhos Básicos

Os caminhos básicos do método `verificarUsuario` são:

1. **Caminho 1**: Start → Ponto 1 → Ponto 2 → Ponto 3 (Sim) → Ponto 4 → End
2. **Caminho 2**: Start → Ponto 1 → Ponto 2 → Ponto 3 (Não) → Ponto 5 → Ponto 6 → Ponto 7 → Ponto 14 → Ponto 15 → End
3. **Caminho 3**: Start → Ponto 1 → Ponto 2 → Ponto 3 (Não) → Ponto 5 → Ponto 6 → Ponto 7 → Ponto 8 (Não) → Ponto 11 → Ponto 12 → Ponto 13 → Ponto 16 → End
4. **Caminho 4**: Start → Ponto 1 → Ponto 2 → Ponto 3 (Não) → Ponto 5 → Ponto 6 → Ponto 7 → Ponto 8 (Sim) → Ponto 9 → Ponto 10 → Ponto 11 → Ponto 12 → Ponto 13 → Ponto 16 → End
