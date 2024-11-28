package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Classe User representa a lógica de autenticação de usuários.
 * Inclui métodos para conexão ao banco de dados e verificação de credenciais.
 */
public class User {

    /**
     * Nome do usuário autenticado.
     */
    public String nome = "";

    /**
     * Método para conectar ao banco de dados.
     *
     * @return Connection objeto que representa a conexão com o banco.
     */
    public Connection conectarBD() {
        Connection conn = null; // Inicializa a conexão como null
        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            // URL de conexão ao banco de dados
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn = DriverManager.getConnection(url); // Estabelece a conexão
        } catch (Exception e) {
            // Imprime o erro caso ocorra uma falha na conexão
            e.printStackTrace();
        }
        return conn; // Retorna a conexão
    }

    /**
     * Verifica se o login e a senha fornecidos são válidos.
     *
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @return true se as credenciais forem válidas, false caso contrário.
     */
    public boolean verificarUsuario(String login, String senha) {
        boolean result = false; // Ponto 1 - Inicializa o resultado como falso
        Connection conn = conectarBD(); // Ponto 2 - Conecta ao banco de dados
        if (conn == null) { // Ponto 3 - Verifica se a conexão falhou
            System.out.println("Conexão ao banco de dados falhou!");
            return result; // Ponto 4 - Retorna falso se a conexão falhar
        }
    
        // Ponto 5 - Cria a consulta SQL para buscar o usuário
        String sql = "SELECT nome FROM usuarios WHERE login = '" + login + "' AND senha = '" + senha + "';";
        try {
            // Ponto 6 - Cria um Statement para executar a consulta SQL
            Statement st = conn.createStatement();
            // Ponto 7 - Executa a consulta SQL e obtém o ResultSet
            ResultSet rs = st.executeQuery(sql);
    
            if (rs.next()) { // Ponto 8 - Verifica se há resultados na consulta
                result = true; // Ponto 9 - Define o resultado como verdadeiro
                nome = rs.getString("nome"); // Ponto 10 - Obtém o nome do usuário
            }
    
            // Fecha os recursos do banco
            rs.close(); // Ponto 11 - Fecha o ResultSet
            st.close(); // Ponto 12 - Fecha o Statement
            conn.close(); // Ponto 13 - Fecha a conexão com o banco
        } catch (Exception e) { // Ponto 14 - Trata exceções que podem ocorrer
            e.printStackTrace(); // Ponto 15 - Imprime a exceção em caso de erro
        }
        return result; // Ponto 16 - Retorna o resultado
    }

    /**
     * Método principal para testar a funcionalidade de login.
     *
     * @param args Argumentos passados via linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        User user = new User();

        // Exemplo de login e senha para teste
        String login = "usuarioTeste";
        String senha = "senhaTeste";

        // Verifica as credenciais fornecidas
        if (user.verificarUsuario(login, senha)) {
            System.out.println("Usuário autenticado com sucesso!");
            System.out.println("Nome do usuário: " + user.nome);
        } else {
            System.out.println("Falha na autenticação. Verifique as credenciais.");
        }
    }
}
