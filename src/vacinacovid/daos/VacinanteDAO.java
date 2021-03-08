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
import vacinacovid.modelo.VacinanteBean;

/**
 *
 * @author maxwell
 */
public class VacinanteDAO extends GenericDAO{
    
    public boolean insert(VacinanteBean v){
        String sql = "INSERT INTO vacinantes(" +
"            nome, dtnasc, idade, endereco, cpf, cns, nomemae, cdacs, status, " +
"            primeiradose, segundadose)" +
"    VALUES  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        return executeCommand(sql, 
                v.getNome(),
                v.getDtNascimento(),
                v.getIdade(),
                v.getEndereco(),
                v.getCpf(),
                v.getCns(),
                v.getNomeMae(),
                v.getAgente().getId(),
                v.getStatus(),
                v.getPrimeiraDose(),
                v.getSegundaDose());
    }
    
    public boolean update(VacinanteBean v) {
        String sql = "UPDATE vacinantes " +
"   SET nome=?, dtnasc=?, idade=?, endereco=?, cpf=?, cns=?, nomemae=?," +
"       cdacs=?, status = ?, primeiradose = ?, segundadose = ? " +
" WHERE id = ?";
        return executeCommand(sql,  
                v.getNome(),
                v.getDtNascimento(),
                v.getIdade(),
                v.getEndereco(),
                v.getCpf(),
                v.getCns(),
                v.getNomeMae(),
                v.getAgente().getId(),
                v.getStatus(),
                v.getPrimeiraDose(),
                v.getSegundaDose(),
                v.getId());
    }

    public boolean delete(VacinanteBean v) {
        String sql = "DELETE FROM vacinantes WHERE id = ?";
        return executeCommand(sql, v.getId());
    }

    public List<VacinanteBean> select() {
        ResultSet rs;
        List<VacinanteBean> vacinantes = new ArrayList<>();
        String sql = "SELECT * FROM vw_vacinantes ORDER BY idade desc,"
                + "nomeubs asc, nome asc";
        rs = executeQuery(sql);
        try {
            while (rs.next()) {
                VacinanteBean v = new VacinanteBean();
                v.setId(rs.getInt("id"));
                v.setNome(rs.getString("nome"));
                System.out.println(rs.getDate("dtnasc"));
                v.setDtNascimento(rs.getDate("dtnasc"));
                v.setIdade(rs.getInt("idade"));
                v.setEndereco(rs.getString("endereco"));
                v.setCpf(rs.getString("cpf"));
                v.setCns(rs.getString("cns"));
                v.setNomeMae(rs.getString("nomemae"));
                AcsBean acs = new AcsBean();
                acs.setId(rs.getInt("cdacs"));
                acs.setNome(rs.getString("nomeacs"));
                UbsBean ubs = new UbsBean();
                ubs.setId(rs.getInt("cdubs"));
                ubs.setNome(rs.getString("nomeubs"));
                acs.setUbs(ubs);
                v.setAgente(acs);
                v.setStatus(rs.getInt("status"));
                v.setPrimeiraDose(rs.getDate("primeiradose"));
                v.setSegundaDose(rs.getDate("segundadose"));
                vacinantes.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VacinanteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("falha ao realizar SELECT");
        } finally {
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(VacinanteDAO.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("falha ao fechar ResultSet");
            }

        }
        return vacinantes;
    }

    public VacinanteBean getRequerente(Integer id) {
        String sql = "SELECT * FROM requerentes WHERE id = ?";
        VacinanteBean u = null;

        ResultSet rs;
        rs = executeQuery(sql, id);

        try {
            while (rs.next()) {
                u = new VacinanteBean();
                u.setId(rs.getInt("id"));
               // u.setSetor(rs.getString("setor"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VacinanteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Falha ao recuperar registro");
        } finally {
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(VacinanteDAO.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("falha ao fechar ResultSet");
            }
        }
        return u;
    }
}
