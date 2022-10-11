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

/**
 *
 * @author maxwell
 */
public class FrmAcesso extends GenericJDialog implements ActionListener {

    private JLabel lbSenha;
    private JPasswordField tfSenha;
    private JButton btAcessar;

    public FrmAcesso(JFrame parent, boolean modal, String titulo, int largura, int altura, int decorationStyle) {
        super(parent, modal, titulo, largura, altura, decorationStyle);
        ConstruirTela();
    }

    private void ConstruirTela() {
        inicializarComponentesDaTela();
        definirLayout();
        registrarEventos();
    }

    private void inicializarComponentesDaTela() {

        lbSenha = new JLabel("Informe a senha:");
        lbSenha.setBounds(45, 20, 200, 20);

        tfSenha = new JPasswordField(20);
        tfSenha.setBounds(45, 40, 200, 20);

        

        btAcessar = new JButton("Acessar");
    }

    private void definirLayout() {
        pnFundo = new JPanel(null);
        pnFundo.add(lbSenha);
        pnFundo.add(tfSenha);
        pnBotoes = new JPanel(new FlowLayout());
        pnBotoes.setBounds(0, 75, 300, 35);
        pnBotoes.add(btAcessar);
        pnBotoes.setBackground(Color.white);
        pnFundo.add(pnBotoes);
        pnFundo.setBackground(Color.white);
        getContentPane().add(pnFundo);
    }

    private void registrarEventos() {
        btAcessar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAcessar) {
            validarSenha();
        }
    }

    private void validarSenha() {
        ImageIcon icone = new ImageIcon(this.getClass().getResource("password.png"));
        
        if (tfSenha.getText().equals(Controle.agenteSelecionada.getSenha())) {
            dispose();
            Controle.abrirTelaPrincipal(Controle.esfSelecionado);
            Controle.selecionaAcs.dispose();
                
        
        }else{
            JOptionPane.showMessageDialog(this, "Senha inválida", "Atenção", JOptionPane.ERROR_MESSAGE, icone);
            tfSenha.requestFocus();
        }
    }

}
