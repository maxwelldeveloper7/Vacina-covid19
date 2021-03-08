/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vacinacovid.testes;

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
        v.setDtNascimentoStr("25/05/1979");
        v.setIdade(41);
        v.setEndereco("Rua Rio Grande do Norte 635 - Vila Nova");
        v.setCpf("04960780621");
        v.setCns("012345648912345");
        v.setNomeMae("Nelzita de Oliveira Chaves");
        AcsBean acs = new AcsBean();
        acs.setId(20);
        acs.setNome("Joelza Silva Oliveira");
        UbsBean ubs = new UbsBean();
        ubs.setId(3);
        ubs.setNome("ESF Vila Nova");
        acs.setUbs(ubs);
        v.setAgente(acs);
        v.setStatus("Vacinado");
        v.setPrimeiraDose("03/02/2021");
        v.setSegundaDose("03/05/2021");
        
        
        VacinanteDAO dao = new VacinanteDAO();
        dao.insert(v);
    }
}
