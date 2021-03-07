/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vacinacovid.testes;

import java.sql.Date;
import vacinacovid.daos.VacinanteDAO;
import vacinacovid.modelo.AcsBean;
import vacinacovid.modelo.UbsBean;
import vacinacovid.modelo.VacinanteBean;

/**
 *
 * @author maxwell
 */
public class TestaInsereVacinante {
    public static void main(String args[]){
        VacinanteBean v = new VacinanteBean();
        v.setNome("Maxwell de Oliveira Chaves");
        //v.setDtNascimento(new Date(1979-1900, 5, 25));
        v.setIdade(41);
        v.setEndereco("Rua Rio Grande do Norte 635 - Vila Nova");
        v.setCpf("04960780622");
        v.setNomeMae("Nelzita de Oliveira Chaves");
        AcsBean acs = new AcsBean();
        acs.setId(20);
        acs.setNome("Joelza Silva Oliveira");
        UbsBean ubs = new UbsBean();
        ubs.setId(3);
        ubs.setNome("ESF Vila Nova");
        acs.setUbs(ubs);
        v.setAgente(acs);
        v.setNomeRespPreenchimento("Joselza");
        v.setCargoResponsavel("ACS");
        
        
        VacinanteDAO dao = new VacinanteDAO();
        dao.insert(v);
    }
}
