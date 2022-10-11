package vacinacovid.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author Maxwell
 */
public class ConexaoPostgres{

    private static Connection con = null;
    private static final String URL = "jdbc:postgresql://localhost:5432/vacina";    
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String USER = "postgres";
    private static final String PASSWORD = "";
    private static ConexaoPostgres instanciaSingleton = null;

    private ConexaoPostgres() {
    }

    public static ConexaoPostgres getInstancia() {
        if (instanciaSingleton == null) {
            instanciaSingleton = new ConexaoPostgres();
        }
        return instanciaSingleton;
    }

    public static Connection conectar() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            con.setAutoCommit(false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Driver não encontrado.\n" + ex.getMessage());
            System.exit(0);
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao estabelecer conexão com o Banco de Dados.\n"
                    + "Motivo: "
                    + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
            System.exit(0);

        }
        return con;
    }

    public static void desconectar() {
        try {
            if (con != null) {
                con.close();
            } else {
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConexaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao desconectar Banco de Dados.\n" + ex.getMessage());
            System.exit(0);
        }
    }

    public static void commit() {
        if (con != null) {
            try {
                con.commit();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao efetivar transação.\n" + ex.getMessage());
                Logger.getLogger(ConexaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void rollback() {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao desfazer transação.\n" + ex.getMessage());
                Logger.getLogger(ConexaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
