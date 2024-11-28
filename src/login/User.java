package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

    public Connection conectarBD() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return conn;
    }

    public String nome = "";

    public boolean verificarUsuario(String login, String senha) {
        boolean result = false; // Ponto 1
        Connection conn = conectarBD(); // Ponto 2
        if (conn == null) { // Ponto 3
            System.out.println("Conexão ao banco de dados falhou!");
            return result; // Ponto 4
        }
    
        String sql = "SELECT nome FROM usuarios WHERE login = '" + login + "' AND senha = '" + senha + "';"; // Ponto 5
        try {
            Statement st = conn.createStatement(); // Ponto 6
            ResultSet rs = st.executeQuery(sql); // Ponto 7
    
            if (rs.next()) { // Ponto 8
                result = true; // Ponto 9
                nome = rs.getString("nome"); // Ponto 10
            }
    
            rs.close(); // Ponto 11
            st.close(); // Ponto 12
            conn.close(); // Ponto 13
        } catch (Exception e) { // Ponto 14
            e.printStackTrace(); // Ponto 15
        }
        return result; // Ponto 16
    }
    

    public static void main(String[] args) {
        User user = new User();

        String login = "usuarioTeste";
        String senha = "senhaTeste";

        if (user.verificarUsuario(login, senha)) {
            System.out.println("Usuário autenticado com sucesso!");
            System.out.println("Nome do usuário: " + user.nome);
        } else {
            System.out.println("Falha na autenticação. Verifique as credenciais.");
        }
    }
}
