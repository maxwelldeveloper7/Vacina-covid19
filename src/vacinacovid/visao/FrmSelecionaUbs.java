/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vacinacovid.visao;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author maxwell
 */
public class FrmSelecionaUbs extends GenericJDialog{

    private JLabel lbUbs;
    private JComboBox cbUbs;
    
    public FrmSelecionaUbs(JFrame parent, boolean modal, String titulo, int largura, int altura) {
        super(parent, modal, titulo, largura, altura);
    }

    public void construirTela(){
        pnFundo = new JPanel(new BorderLayout());
        
        getContentPane().add(pnFundo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
