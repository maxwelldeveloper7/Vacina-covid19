/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vacinacovid.testes;

import javax.swing.JRootPane;
import vacinacovid.controle.Controle;
import vacinacovid.visao.FrmCadVacinanteIrrestrito;

/**
 *
 * @author maxwell
 */
public class TestaTelaCadastro {
    public static void main(String args[]){
        FrmCadVacinanteIrrestrito form = new FrmCadVacinanteIrrestrito(null, true, "Cadastro", 590, 370, JRootPane.PLAIN_DIALOG);
        form.setVisible(true);
    }
}
