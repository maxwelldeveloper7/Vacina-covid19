/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacinacovid.visao;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import vacinacovid.controle.Controle;
import vacinacovid.daos.AcsDAO;

/**
 *
 * @author maxwell
 */
public class FrmCadastraNovaSenha extends GenericJDialog implements ActionListener {

    private JLabel lbSenha, lbRepitaSenha;
    private JPasswordField tfSenha, tfRepitaSenha;
    private JButton btSalvar, btCancelar;

    public FrmCadastraNovaSenha(JFrame parent, boolean modal, String titulo, int largura, int altura, int decorationStyle) {
        super(parent, modal, titulo, largura, altura, decorationStyle);
        ConstruirTela();
    }

    private void ConstruirTela() {
        inicializarComponentesDaTela();
        definirLayout();
        registrarEventos();
    }

    private void inicializarComponentesDaTela() {

        lbSenha = new JLabel("Nova senha:");
        lbSenha.setBounds(45, 20, 200, 20);

        tfSenha = new JPasswordField(20);
        tfSenha.setBounds(45, 40, 200, 20);

        lbRepitaSenha = new JLabel("Repita senha:");
        lbRepitaSenha.setBounds(45, 70, 200, 20);

        tfRepitaSenha = new JPasswordField(20);
        tfRepitaSenha.setBounds(45, 90, 200, 20);

        btSalvar = new JButton("Salvar");
        btCancelar = new JButton("Cancelar");
    }

    private void definirLayout() {
        pnFundo = new JPanel(null);
        pnFundo.add(lbSenha);
        pnFundo.add(tfSenha);
        pnFundo.add(lbRepitaSenha);
        pnFundo.add(tfRepitaSenha);
        pnBotoes = new JPanel(new FlowLayout());
        pnBotoes.setBounds(0, 125, 300, 35);
        pnBotoes.add(btSalvar);
        pnBotoes.add(btCancelar);
        pnBotoes.setBackground(Color.white);
        pnFundo.add(pnBotoes);
        pnFundo.setBackground(Color.white);
        getContentPane().add(pnFundo);
    }

    private void registrarEventos() {
        btSalvar.addActionListener(this);
        btCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btSalvar) {
            if (validarSenha()) {
                salvarSenha();
                Controle.selecionaAcs.acessar();
                dispose();
            }
        }
        if (e.getSource() == btCancelar) {
            dispose();
        }
    }

    private boolean validarSenha() {
        ImageIcon icone = new ImageIcon(this.getClass().getResource("password.png"));
        
        if (tfSenha.getPassword().length == 0 && tfRepitaSenha.getPassword().length == 0) {
            
            JOptionPane.showMessageDialog(this, "Preencha os campos", "Atenção", JOptionPane.WARNING_MESSAGE, icone);
            tfSenha.requestFocus();
            return false;        
                
        
        }else if(!tfSenha.getText().equals(tfRepitaSenha.getText())) {
            JOptionPane.showMessageDialog(this, "Senhas não conferem", "Atenção", JOptionPane.WARNING_MESSAGE, icone);
            tfSenha.requestFocus();            
            return false;
            
        }else{
            return true;
        }
    

        /*ImageIcon icone = new ImageIcon(this.getClass().getResource("password.png"));
         JOptionPane.showMessageDialog(this, "Senhas não conferem", "Atenção", JOptionPane.WARNING_MESSAGE, icone);
         return false;
         */
    }

    private void salvarSenha() {
        Controle.agenteSelecionada.setSenha(tfRepitaSenha.getText());
        
        AcsDAO dao = new AcsDAO();
        
        dao.update(Controle.agenteSelecionada);
        
        
    }

}
