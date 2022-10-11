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
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class FrmSelecionaUbs extends GenericJDialog implements ActionListener {

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
        registrarEventos();
    }

    private void inicializarComponentesDaTela() {
        lbEsf = new JLabel("ESF:");
        lbEsf.setBounds(20, 20, 100, 20);

        cbEsf = new JComboBox();
        cbEsf.setBounds(20, 40, 300, 20);
        carregarEsf();

        btProsseguir = new JButton("Prosseguir");
    }

    private void carregarEsf() {
        cbEsf.addItem("Todos");
        cbEsf.addItem("RETA");
        cbEsf.addItem("GETULIO VARGAS");
        cbEsf.addItem("VILA NOVA");
        cbEsf.addItem("VILA ESPERANÇA");
        cbEsf.addItem("LATICINIOS");
        cbEsf.addItem("UDR");
        cbEsf.addItem("CRUZEIRO");
        cbEsf.addItem("SETE DE SETEMBRO");
        cbEsf.addItem("VALDIVINA FERRAZ 1");
        cbEsf.addItem("VALDIVINA FERRAZ 2");
        cbEsf.addItem("VILA GABRIEL PASSOS");
        cbEsf.addItem("VILA PEREIRA");
    }

    private void definirLayout() {
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

    private void registrarEventos() {
        btProsseguir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btProsseguir) {
            validarModoDeAcesso();
        }
    }

    private void validarModoDeAcesso() {
        if (cbEsf.getSelectedItem().toString().equals("Todos")) {
            JPasswordField psw = new JPasswordField(10);
            psw.setEchoChar('*');
            JLabel rotulo = new JLabel("Entre com a senha:");
            JPanel entUsuario = new JPanel();
            entUsuario.add(rotulo);
            entUsuario.add(psw);
            psw.requestFocus();
            JOptionPane.showMessageDialog(null, entUsuario, "Acesso Restrito", JOptionPane.PLAIN_MESSAGE);
            String senha = psw.getText();
            System.out.println(senha);

            if (senha.equals("saude2021")) {
                Controle.abrirTelaPrincipalGestor();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Senha Inválida");
            }

        } else {
            Controle.esfSelecionado = cbEsf.getSelectedItem().toString();
            Controle.abrirTelaSelecionaAcs();            
            dispose();
        }
    }
}
