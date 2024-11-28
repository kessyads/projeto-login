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
        boolean result = false;
        Connection conn = conectarBD();
        if (conn == null) {
            System.out.println("Conexão ao banco de dados falhou!");
            return result;
        }

        String sql = "SELECT nome FROM usuarios WHERE login = '" + login + "' AND senha = '" + senha + "';";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                result = true;
                nome = rs.getString("nome");
            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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
