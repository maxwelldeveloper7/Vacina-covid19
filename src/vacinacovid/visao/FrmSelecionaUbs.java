/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vacinacovid.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import vacinacovid.modelo.UbsBean;

/**
 *
 * @author maxwell
 */
public class FrmSelecionaUbs extends GenericJDialog{

    private JLabel lbEsf;
    private JComboBox cbEsf;
    private JButton btProsseguir;
    
    public FrmSelecionaUbs(JFrame parent, boolean modal, String titulo, int largura, int altura, int decorationStyle) {
        super(parent, modal, titulo, largura, altura, decorationStyle);
        ConstruirTela();
    }
    
    

    private void ConstruirTela() {
        inicializarComponentesDaTela();
        definirLayout();
    }
    
    private void inicializarComponentesDaTela() {
        lbEsf = new JLabel("ESF:");
        lbEsf.setBounds(20, 20, 100, 20);
        
        cbEsf = new JComboBox();
        cbEsf.setBounds(20, 40, 300, 20);
        
        btProsseguir = new JButton ("Prosseguir");
        
    }
    
    private void carregarEsf() {
        List<UbsBean> unidades = new ArrayList();
        UbsBean reta = new UbsBean();
    }
    
    private void definirLayout(){
        pnFundo = new JPanel(null);
        pnFundo.add(lbEsf);
        pnFundo.add(cbEsf);
        pnBotoes = new JPanel(new FlowLayout());
        pnBotoes.setBounds(0, 80, 350, 35);
        pnBotoes.add(btProsseguir);
        pnBotoes.setBackground(Color.white);
        pnFundo.add(pnBotoes);
        pnFundo.setBackground(Color.white);
        getContentPane().add(pnFundo);
    }
       

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    

    
}