/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacinacovid.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vacinacovid.modelo.AcsBean;
import vacinacovid.modelo.UbsBean;

/**
 *
 * @author maxwell
 */
public class AcsDAO extends GenericDAO {

    
    public boolean update(AcsBean a) {
        String sql = "UPDATE acs "
                + "   SET senhaacesso = ? WHERE id = ?";
        return executeCommand(sql,
                a.getSenha(),
                a.getId());
    }
    
    public List<AcsBean> select() {
        ResultSet rs;
        List<AcsBean> agentes = new ArrayList<>();
        String sql = "SELECT * FROM vw_acs WHERE id != 0 ORDER BY nomeacs asc";
        rs = executeQuery(sql);
        try {
            while (rs.next()) {
                AcsBean acs = new AcsBean();
                acs.setId(rs.getInt("id"));
                acs.setNome(rs.getString("nomeacs"));
                acs.setSenha(rs.getString("senhaacesso"));
                UbsBean u = new UbsBean(rs.getInt("cdubs"), rs.getString("nomeubs"));
                acs.setUbs(u);
                agentes.add(acs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AcsDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("falha ao realizar SELECT");
        } finally {
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(AcsDAO.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("falha ao fechar ResultSet");
            }

        }
        return agentes;
    }
    
    public List<AcsBean> selectComInconsistencia() {
        ResultSet rs;
        List<AcsBean> agentes = new ArrayList<>();
        String sql = "SELECT * FROM vw_acs WHERE ORDER BY nomeacs asc";
        rs = executeQuery(sql);
        try {
            while (rs.next()) {
                AcsBean acs = new AcsBean();
                acs.setId(rs.getInt("id"));
                acs.setNome(rs.getString("nomeacs"));
                acs.setSenha(rs.getString("senhaacesso"));
                UbsBean u = new UbsBean(rs.getInt("cdubs"), rs.getString("nomeubs"));
                acs.setUbs(u);
                agentes.add(acs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AcsDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("falha ao realizar SELECT");
        } finally {
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(AcsDAO.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("falha ao fechar ResultSet");
            }

        }
        return agentes;
    }
    
    
    public List<AcsBean> select(String esfNome) {
        ResultSet rs;
        List<AcsBean> agentes = new ArrayList<>();
        String sql = "select * from vw_acs where nomeubs = ? and id != 0 ORDER BY nomeacs asc";
        rs = executeQuery(sql, esfNome);
        try {
            while (rs.next()) {
                AcsBean acs = new AcsBean();
                acs.setId(rs.getInt("id"));
                acs.setNome(rs.getString("nomeacs"));
                acs.setSenha(rs.getString("senhaacesso"));
                UbsBean u = new UbsBean(rs.getInt("cdubs"), rs.getString("nomeubs"));
                acs.setUbs(u);
                agentes.add(acs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AcsDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("falha ao realizar SELECT");
        } finally {
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(AcsDAO.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("falha ao fechar ResultSet");
            }

        }
        return agentes;
    }
    
    public List<AcsBean> selectComInconsistencia(String esfNome) {
        ResultSet rs;
        List<AcsBean> agentes = new ArrayList<>();
        String sql = "select * from vw_acs where nomeubs = ? ORDER BY nomeacs asc";
        rs = executeQuery(sql, esfNome);
        try {
            while (rs.next()) {
                AcsBean acs = new AcsBean();
                acs.setId(rs.getInt("id"));
                acs.setNome(rs.getString("nomeacs"));
                acs.setSenha(rs.getString("senhaacesso"));
                UbsBean u = new UbsBean(rs.getInt("cdubs"), rs.getString("nomeubs"));
                acs.setUbs(u);
                agentes.add(acs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AcsDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("falha ao realizar SELECT");
        } finally {
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(AcsDAO.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("falha ao fechar ResultSet");
            }

        }
        return agentes;
    }
    
}
