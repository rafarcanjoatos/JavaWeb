package DatabaseConector;

// Classes necessárias para uso de Banco de dados //
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Início da classe de conexão//
public class DatabaseConector {

    // Propriedades
    public static String status = "Não conectou...";
    public java.sql.Connection c;

    // Método Construtor da Classe//
    public DatabaseConector() {

    }

    // Método de Conexão//
    public static java.sql.Connection getDatabaseConector() {

        // atributo do tipo Connection
        Connection connection = null;

        try {

            // Carregando o JDBC Driver padrão
            String driverName = "com.mysql.jdbc.Driver";

            Class.forName(driverName);

            // Configurando a nossa conexão com um banco de dados//
            String serverName = "localhost"; // caminho do servidor do BD
            String mydatabase = "bd_contacomigo"; // nome do seu banco de dados
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = "root"; // nome de um usuário de seu BD
            String password = ""; // sua senha de acesso
            connection = DriverManager.getConnection(url, username, password);

            // Testa sua conexão//
            if (connection != null) {

                status = ("STATUS--->Conectado com sucesso!");

            } else {

                status = ("STATUS--->Não foi possivel realizar conexão");

            }

            return connection;

            // Driver não encontrado
        } catch (ClassNotFoundException e) {

            System.out.println("O driver expecificado nao foi encontrado.");

            return null;

        } catch (SQLException e) {

            // Não conseguindo se conectar ao banco
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");

            return null;

        }

    }

    // Método que retorna o status da sua conexão//
    public static String statusConection() {

        return status;

    }

    // Método que fecha sua conexão//
    public static boolean FecharConexao() {

        try {

            DatabaseConector.getDatabaseConector().close();

            return true;

        } catch (SQLException e) {

            return false;

        }

    }

    // Método que reinicia sua conexão//
    public static java.sql.Connection ReiniciarConexao() {

        FecharConexao();

        return DatabaseConector.getDatabaseConector();

    }

}