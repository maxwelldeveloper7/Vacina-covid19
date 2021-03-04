/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vacinacovid.daos;

import almoxarifado.modelo.RequerenteBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maxwell
 */
public class RequerenteDAO extends GenericDAO{
    
    public boolean insert(RequerenteBean r){
        String sql = "INSERT INTO requerentes (setor) VALUES (?)";
        return executeCommand(sql, r.getSetor());
    }
    
    public boolean update(RequerenteBean r) {
        String sql = "UPDATE requerentes SET setor = ? WHERE id = ?";
        return executeCommand(sql, r.getSetor(), r.getId());
    }

    public boolean delete(RequerenteBean r) {
        String sql = "DELETE FROM requerentes WHERE id = ?";
        return executeCommand(sql, r.getId());
    }

    public List<RequerenteBean> select() {
        ResultSet rs;
        List<RequerenteBean> requerentes = new ArrayList<>();
        String sql = "SELECT * FROM requerentes ORDER BY setor ASC";
        rs = executeQuery(sql);
        try {
            while (rs.next()) {
                RequerenteBean r = new RequerenteBean();
                r.setId(rs.getInt("id"));
                r.setSetor(rs.getString("setor"));
                requerentes.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequerenteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("falha ao realizar SELECT");
        } finally {
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(RequerenteDAO.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("falha ao fechar ResultSet");
            }

        }
        return requerentes;
    }

    public RequerenteBean getRequerente(Integer id) {
        String sql = "SELECT * FROM requerentes WHERE id = ?";
        RequerenteBean u = null;

        ResultSet rs;
        rs = executeQuery(sql, id);

        try {
            while (rs.next()) {
                u = new RequerenteBean();
                u.setId(rs.getInt("id"));
                u.setSetor(rs.getString("setor"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequerenteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Falha ao recuperar registro");
        } finally {
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(RequerenteDAO.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("falha ao fechar ResultSet");
            }
        }
        return u;
    }
}
