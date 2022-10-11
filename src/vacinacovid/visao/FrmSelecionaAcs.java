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
import javax.swing.JPanel;
import javax.swing.JRootPane;
import vacinacovid.controle.Controle;
import vacinacovid.modelo.AcsBean;

/**
 *
 * @author maxwell
 */
public class FrmSelecionaAcs extends GenericJDialog implements ActionListener {

    private JLabel lbAcs;
    private JComboBox cbAcs;
    private JButton btProsseguir;
    private String agente;

    public FrmSelecionaAcs(JFrame parent, boolean modal, String titulo, int largura, int altura, int decorationStyle) {
        super(parent, modal, titulo, largura, altura, decorationStyle);
        ConstruirTela();
    }

    private void ConstruirTela() {
        inicializarComponentesDaTela();
        definirLayout();
        registrarEventos();
    }

    private void inicializarComponentesDaTela() {
        lbAcs = new JLabel("Agente:");
        lbAcs.setBounds(20, 20, 100, 20);

        cbAcs = new JComboBox();
        cbAcs.setBounds(20, 40, 300, 20);
        carregarEsf();

        btProsseguir = new JButton("Prosseguir");
    }

    private void carregarEsf() {

        if (Controle.esfSelecionado.equals("RETA")) {
            for (int i = 0; i < Controle.agentes.size(); i++) {
                if (Controle.agentes.get(i).getUbs().getNome().equals("ESF RETA")) {
                    cbAcs.addItem(Controle.agentes.get(i).getNome());
                    Controle.selecionados.add(Controle.agentes.get(i));
                }
            }

        }

        if (Controle.esfSelecionado.equals("GETULIO VARGAS")) {
            for (int i = 0; i < Controle.agentes.size(); i++) {
                if (Controle.agentes.get(i).getUbs().getNome().equals("ESF GETULIO VARGAS")) {
                    cbAcs.addItem(Controle.agentes.get(i).getNome());
                    Controle.selecionados.add(Controle.agentes.get(i));
                }
            }

        }

        if (Controle.esfSelecionado.equals("VILA NOVA")) {
            for (int i = 0; i < Controle.agentes.size(); i++) {
                if (Controle.agentes.get(i).getUbs().getNome().equals("ESF VILA NOVA")) {
                    cbAcs.addItem(Controle.agentes.get(i).getNome());
                    Controle.selecionados.add(Controle.agentes.get(i));
                }
            }

        }

        if (Controle.esfSelecionado.equals("VILA ESPERANÇA")) {
            for (int i = 0; i < Controle.agentes.size(); i++) {
                if (Controle.agentes.get(i).getUbs().getNome().equals("ESF VILA ESPERANÇA")) {
                    cbAcs.addItem(Controle.agentes.get(i).getNome());
                    Controle.selecionados.add(Controle.agentes.get(i));
                }
            }

        }

        if (Controle.esfSelecionado.equals("LATICINIOS")) {
            for (int i = 0; i < Controle.agentes.size(); i++) {
                if (Controle.agentes.get(i).getUbs().getNome().equals("ESF LATICINIOS")) {
                    cbAcs.addItem(Controle.agentes.get(i).getNome());
                    Controle.selecionados.add(Controle.agentes.get(i));
                }
            }

        }

        if (Controle.esfSelecionado.equals("UDR")) {
            for (int i = 0; i < Controle.agentes.size(); i++) {
                if (Controle.agentes.get(i).getUbs().getNome().equals("ESF UDR")) {
                    cbAcs.addItem(Controle.agentes.get(i).getNome());
                    Controle.selecionados.add(Controle.agentes.get(i));
                }
            }

        }

        if (Controle.esfSelecionado.equals("CRUZEIRO")) {
            for (int i = 0; i < Controle.agentes.size(); i++) {
                if (Controle.agentes.get(i).getUbs().getNome().equals("ESF CRUZEIRO")) {
                    cbAcs.addItem(Controle.agentes.get(i).getNome());
                    Controle.selecionados.add(Controle.agentes.get(i));
                }
            }

        }

        if (Controle.esfSelecionado.equals("SETE DE SETEMBRO")) {
            for (int i = 0; i < Controle.agentes.size(); i++) {
                if (Controle.agentes.get(i).getUbs().getNome().equals("ESF SETE DE SETEMBRO")) {
                    cbAcs.addItem(Controle.agentes.get(i).getNome());
                    Controle.selecionados.add(Controle.agentes.get(i));
                }
            }

        }

        if (Controle.esfSelecionado.equals("VALDIVINA FERRAZ 1")) {
            for (int i = 0; i < Controle.agentes.size(); i++) {
                if (Controle.agentes.get(i).getUbs().getNome().equals("ESF VALDIVINA FERRAZ 1")) {
                    cbAcs.addItem(Controle.agentes.get(i).getNome());
                    Controle.selecionados.add(Controle.agentes.get(i));
                }
            }

        }

        if (Controle.esfSelecionado.equals("VALDIVINA FERRAZ 2")) {
            for (int i = 0; i < Controle.agentes.size(); i++) {
                if (Controle.agentes.get(i).getUbs().getNome().equals("ESF VALDIVINA FERRAZ 2")) {
                    cbAcs.addItem(Controle.agentes.get(i).getNome());
                    Controle.selecionados.add(Controle.agentes.get(i));
                }
            }

        }

        if (Controle.esfSelecionado.equals("VILA GABRIEL PASSOS")) {
            for (int i = 0; i < Controle.agentes.size(); i++) {
                if (Controle.agentes.get(i).getUbs().getNome().equals("ESF VILA GABRIEL PASSOS")) {
                    cbAcs.addItem(Controle.agentes.get(i).getNome());
                    Controle.selecionados.add(Controle.agentes.get(i));
                }
            }

        }

        if (Controle.esfSelecionado.equals("VILA PEREIRA")) {
            for (int i = 0; i < Controle.agentes.size(); i++) {
                if (Controle.agentes.get(i).getUbs().getNome().equals("ESF VILA PEREIRA")) {
                    cbAcs.addItem(Controle.agentes.get(i).getNome());
                    Controle.selecionados.add(Controle.agentes.get(i));
                }
            }

        }

        agente = cbAcs.getSelectedItem().toString();
    }

    private void definirLayout() {
        pnFundo = new JPanel(null);
        pnFundo.add(lbAcs);
        pnFundo.add(cbAcs);
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
        cbAcs.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btProsseguir) {
            prepararAcesso();
        }

        if (e.getSource() == cbAcs) {
            agente = cbAcs.getSelectedItem().toString();
        }
    }

    private void prepararAcesso() {

        for (AcsBean acs : Controle.agentes) {
            if (agente.equals(acs.getNome())) {
                Controle.agenteSelecionada = acs;
                
            }
        }

        if (Controle.agenteSelecionada.getSenha().equals("123456")) {
            cadastarNovaSenha();            
        } else {
            acessar();
        }
    }

    public void acessar() {
        FrmAcesso frm = new FrmAcesso(null, true, "Acesso ao Sistema", 300, 150, 3);
        frm.setVisible(true);
    }

    private void cadastarNovaSenha() {
        FrmCadastraNovaSenha frm = new FrmCadastraNovaSenha(null, true, "Cadastre "
                + "uma nova senha", 300, 200, JRootPane.ERROR_DIALOG);
        frm.setVisible(true);
    }
}
