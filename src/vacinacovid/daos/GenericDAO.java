/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vacinacovid.daos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vacinacovid.controle.Controle;

/**
 *
 * @author Maxwell
 */
public abstract class GenericDAO {

    private static final long serialVersionUID = 1L;
    private static PreparedStatement ps = null;
    private static CallableStatement cs = null;

    public ResultSet executeQuery(String query, Object... params) {
        ps = null;
        ResultSet rs = null;
        try {
            ps = ConexaoPostgres.conectar().prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            rs = ps.executeQuery();
            ConexaoPostgres.commit();
        } catch (SQLException ex) {
            ConexaoPostgres.rollback();
            System.out.println("Falha ao executar query.");
            ConexaoPostgres.desconectar();
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet executeQuery(String query) {
        ps = null;
        ResultSet rs = null;
        try {
            ps = ConexaoPostgres.conectar().prepareStatement(query);
            rs = ps.executeQuery();
            ConexaoPostgres.commit();
        } catch (SQLException ex) {
            ConexaoPostgres.rollback();
            System.out.println("Falha ao executar query.");
            ConexaoPostgres.desconectar();
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public boolean executeCommand(String query, Object... params) {
        ps = null;
        try {
            ps = ConexaoPostgres.conectar().prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ps.executeUpdate();
            ConexaoPostgres.commit();
            return true;
        } catch (SQLException ex) {
            ConexaoPostgres.rollback();
            System.out.println("Falha ao executar DML (Linguagem de Manipulação de Dados)."+ ex.getErrorCode());
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getSQLState());
            Controle.tratarErroSQL(ex.getMessage());            
            return false;
            
        } finally {
            fecharPrepareStatement();
        }

    }

    public boolean executeProcedure(String query, Object... params) {
        cs = null;
        try {
            cs = ConexaoPostgres.conectar().prepareCall(query);
            for (int i = 0; i < params.length; i++) {
                cs.setObject(i + 1, params[i]);
            }
            cs.execute();
            ConexaoPostgres.commit();
            return true;
        } catch (SQLException ex) {
            ConexaoPostgres.rollback();
            System.out.println("Falha ao executar Procedure.");
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            fecharCallableStatement();
        }

    }

    public int executeProcedureInt(String query, Object... params) {
        cs = null;
        ResultSet rs = null;
        int result = 0;
        try {
            cs = ConexaoPostgres.conectar().prepareCall(query);
            for (int i = 0; i < params.length; i++) {
                cs.setObject(i + 1, params[i]);
            }

            rs =  cs.executeQuery();
            ConexaoPostgres.commit();

            while(rs.next()){
                result = rs.getInt(1);
            }

            return result;
        } catch (SQLException ex) {
            ConexaoPostgres.rollback();
            System.out.println("Falha ao executar Procedure.");
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        } finally {
            fecharCallableStatement();
        }

    }

    public void fecharPrepareStatement() {
        try {
            if (ps != null) {
                ps.close();
                ConexaoPostgres.desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao fechar preparedStatement.");
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fecharCallableStatement() {
        try {
            if (ps != null) {
                ps.close();
                ConexaoPostgres.desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao fechar preparedStatement.");
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
