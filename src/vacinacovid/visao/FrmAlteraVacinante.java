/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacinacovid.visao;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.text.MaskFormatter;
import vacinacovid.Utilidades;
import vacinacovid.controle.Controle;
import vacinacovid.daos.VacinanteDAO;
import vacinacovid.modelo.VacinanteBean;

/**
 *
 * @author maxwell
 */
public class FrmAlteraVacinante extends JDialog {

    private JLabel lbNome, lbNascimento, lbIdade, lbNomeMae, lbCpf, lbCns,
            lbEndereco, lbLaboratorio, lbDose1, lbDose2, lbStatus, lbIlustracao;

    private JTextField tfNome, tfIdade, tfNomeMae, tfEndereco;
    private JFormattedTextField tfNascimento, tfCpf, tfCns, tfDose1, tfDose2;
    private JComboBox cbLaboratorio, cbStatus;
    private JButton btSalvar, btCancelar;
    private ImageIcon salvar, cancelar, covid, vacina;
    private JPanel pnFundo;
    

    public FrmAlteraVacinante(JFrame parent, boolean modal, String titulo,
            int largura, int altura, int decorationStyle) {

        setTitle(titulo);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - largura) / 2, (screenSize.height - altura) / 2,
                largura, altura);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(decorationStyle);
        setResizable(false);
        ConstruirTela();
        setModal(modal);
    }

    private void ConstruirTela() {
        inicializarComponentesDaTela();
        definirLayout();
        registrarEventos();
        preencherCampos();
    }

    private void inicializarComponentesDaTela() {
        lbNome = new JLabel("Nome");
        lbNome.setBounds(20, 20, 50, 20);
        tfNome = new JTextField();
        tfNome.setBounds(20, 40, 250, 20);

        lbNascimento = new JLabel("Nascimento");
        lbNascimento.setBounds(290, 20, 90, 20);
        try {
            tfNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException ex) {
            Logger.getLogger(FrmAlteraVacinante.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfNascimento.setFocusLostBehavior(JFormattedTextField.COMMIT);
        tfNascimento.setBounds(290, 40, 90, 20);

        lbIdade = new JLabel("Idade");
        lbIdade.setBounds(400, 20, 40, 20);
        tfIdade = new JTextField();
        tfIdade.setBounds(400, 40, 70, 20);
        tfIdade.setEditable(false);
        tfIdade.setFocusable(false);

        lbIlustracao = new JLabel();
        lbIlustracao.setBounds(520, 20, 24, 24);

        lbNomeMae = new JLabel("Nome da Mãe");
        lbNomeMae.setBounds(20, 70, 100, 20);
        tfNomeMae = new JTextField();
        tfNomeMae.setBounds(20, 90, 250, 20);

        lbCpf = new JLabel("CPF");
        lbCpf.setBounds(290, 70, 100, 20);
        try {
            tfCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
        } catch (ParseException ex) {
            Logger.getLogger(FrmAlteraVacinante.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfCpf.setBounds(290, 90, 110, 20);
        tfCpf.setFocusLostBehavior(JFormattedTextField.COMMIT);

        lbCns = new JLabel("Cartão SUS");
        lbCns.setBounds(420, 70, 100, 20);
        try {
            tfCns = new JFormattedTextField(new MaskFormatter("### #### #### ####"));
        } catch (ParseException ex) {
            Logger.getLogger(FrmAlteraVacinante.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfCns.setBounds(420, 90, 140, 20);
        tfCns.setFocusLostBehavior(JFormattedTextField.COMMIT);

        lbEndereco = new JLabel("Endereço");
        lbEndereco.setBounds(20, 120, 100, 20);
        tfEndereco = new JTextField();
        tfEndereco.setBounds(20, 140, 480, 20);

        lbLaboratorio = new JLabel("Laboratório");
        lbLaboratorio.setBounds(20, 170, 100, 20);
        cbLaboratorio = new JComboBox();
        cbLaboratorio.setBounds(20, 190, 140, 20);
        cbLaboratorio.addItem("Nenhum");
        cbLaboratorio.addItem("Astrazeneca");
        cbLaboratorio.addItem("Coronavac");
        cbLaboratorio.addItem("Pfizer");

        lbDose1 = new JLabel("1ª dose");
        lbDose1.setBounds(180, 170, 100, 20);
        try {
            tfDose1 = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException ex) {
            Logger.getLogger(FrmAlteraVacinante.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfDose1.setBounds(180, 190, 90, 20);
        tfDose1.setFocusLostBehavior(JFormattedTextField.COMMIT);

        lbDose2 = new JLabel("2ª dose");
        lbDose2.setBounds(290, 170, 100, 20);
        try {
            tfDose2 = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException ex) {
            Logger.getLogger(FrmAlteraVacinante.class.getName()).log(Level.SEVERE, null, ex);
        }
        tfDose2.setBounds(290, 190, 90, 20);
        tfDose2.setFocusLostBehavior(JFormattedTextField.COMMIT);

        lbStatus = new JLabel("Status");
        lbStatus.setBounds(400, 170, 100, 20);
        cbStatus = new JComboBox();
        cbStatus.setBounds(400, 190, 160, 20);
        cbStatus.addItem("Não Vacinado");
        cbStatus.addItem("Vacinado");
        cbStatus.addItem("Recusou");
        cbStatus.setSelectedIndex(0);

        salvar = new ImageIcon(this.getClass().getResource("salvar.png"));
        btSalvar = new JButton("Salvar", salvar);
        btSalvar.setBounds(170, 260, 120, 25);

        cancelar = new ImageIcon(this.getClass().getResource("cancelar.png"));
        btCancelar = new JButton("Cancelar", cancelar);
        btCancelar.setBounds(300, 260, 120, 25);
        
        covid = new ImageIcon(this.getClass().getResource("coronavirus24.png"));
        vacina = new ImageIcon(this.getClass().getResource("vacina24.png"));
    }

    private void definirLayout() {
        pnFundo = new JPanel(null);
        pnFundo.add(lbNome);
        pnFundo.add(tfNome);
        pnFundo.add(lbNascimento);
        pnFundo.add(tfNascimento);
        pnFundo.add(lbIdade);
        pnFundo.add(tfIdade);
        pnFundo.add(lbIlustracao);
        pnFundo.add(lbNomeMae);
        pnFundo.add(tfNomeMae);
        pnFundo.add(lbCpf);
        pnFundo.add(tfCpf);
        pnFundo.add(lbCns);
        pnFundo.add(tfCns);
        pnFundo.add(lbEndereco);
        pnFundo.add(tfEndereco);
        pnFundo.add(lbLaboratorio);
        pnFundo.add(cbLaboratorio);
        pnFundo.add(lbDose1);
        pnFundo.add(tfDose1);
        pnFundo.add(lbDose2);
        pnFundo.add(tfDose2);
        pnFundo.add(lbStatus);
        pnFundo.add(cbStatus);
        pnFundo.add(btSalvar);
        pnFundo.add(btCancelar);

        getContentPane().add(pnFundo);

    }

    private void registrarEventos() {
        
        tfNome.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                tfNome.select(0, tfNome.getText().length());
            }

            @Override
            public void focusLost(FocusEvent e) {
                tfNome.setText(Utilidades.iniciaisMaiuscula(tfNome.getText()));
            }
        });
        
        tfNascimento.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                tfNascimento.select(0, tfNascimento.getText().length());
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (dataValida(tfNascimento.getText())) {
                    tfIdade.setText(Integer.toString(Utilidades.calculaIdade(tfNascimento.getText())) + " anos");
                } else {
                    tfNascimento.setText("");
                    tfIdade.setText("");
                }
            }

        });

        tfDose1.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                tfDose1.select(0, tfDose1.getText().length());
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (dataValida(tfDose1.getText())) {
                    cbStatus.setSelectedIndex(1);
                    lbIlustracao.setIcon(vacina);
                } else {
                    cbStatus.setSelectedIndex(0);
                    tfDose1.setText("");
                    lbIlustracao.setIcon(covid);
                }
            }
        });

        tfDose2.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                tfDose2.select(0, tfDose2.getText().length());
                if(tfDose1.getText().equals("  /  /    ")){
                    tfDose1.requestFocus();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!dataValida(tfDose2.getText())) {
                    tfDose2.setText("");
                } 
            }
        });
        
        tfNomeMae.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                tfNomeMae.select(0, tfNomeMae.getText().length());
            }

            @Override
            public void focusLost(FocusEvent e) {
                tfNomeMae.setText(Utilidades.iniciaisMaiuscula(tfNomeMae.getText()));
            }
        });

        tfCpf.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                tfCpf.select(0, tfCpf.getText().length());
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!Utilidades.cpfValido(tfCpf.getText())) {
                    tfCpf.setText("");
                }
            }
        });

        tfCns.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                tfCns.select(0, tfCns.getText().length());
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!Utilidades.cnsValido(tfCns.getText())) {
                    tfCns.setText("");
                }
            }
        });
        
        tfEndereco.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                tfEndereco.select(0, tfEndereco.getText().length());
            }

            @Override
            public void focusLost(FocusEvent e) {
                tfEndereco.setText(Utilidades.iniciaisMaiuscula(tfEndereco.getText()));
            }
        });
        
        btSalvar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                salvar();
            }
        });
        
        btCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void preencherCampos() {
        tfNome.setText(Controle.getVacinante().getNome());
        tfNascimento.setText(Controle.getVacinante().getDtNascimentoStr());
        tfIdade.setText(Integer.toString(Utilidades.calculaIdade(tfNascimento.getText())) + " anos");
        tfNomeMae.setText(Controle.getVacinante().getNomeMae());
        tfCpf.setText(Controle.getVacinante().getCpf());
        tfCns.setText(Controle.getVacinante().getCns());
        tfEndereco.setText(Controle.getVacinante().getEndereco());
        cbLaboratorio.setSelectedItem(Controle.getVacinante().getLaboratorio().getVacina());
        tfDose1.setText(Controle.getVacinante().primeiraDose());
        tfDose2.setText(Controle.getVacinante().segundaDose());
        cbStatus.setSelectedItem(Controle.getVacinante().status());
        if (cbStatus.getSelectedItem().toString().equals("Vacinado")) {
            lbIlustracao.setIcon(vacina);
        } else {
            lbIlustracao.setIcon(covid);
        }
    }

    private boolean dataValida(String data) {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);

        try {
            df.parse(data);
            return true;
        } catch (ParseException ex) {
            //JOptionPane.showMessageDialog(this, "Data inválida");
            return false;
        }
    }
    
    private void salvar(){      
        
        VacinanteBean v = Controle.getVacinante();
        v.setNome(tfNome.getText().trim());
        v.setDtNascimentoStr(tfNascimento.getText());
        v.setNomeMae(tfNomeMae.getText().trim());
        v.setCpf(tfCpf.getText());
        v.setCns(tfCns.getText());
        v.setEndereco(tfEndereco.getText().trim());
        v.setAgente(Controle.agenteSelecionada);
        v.setLaboratorio(cbLaboratorio.getSelectedItem().toString());
        v.setPrimeiraDose(tfDose1.getText());
        v.setSegundaDose(tfDose2.getText());
        v.setStatus(cbStatus.getSelectedIndex());
        
        VacinanteDAO dao = new VacinanteDAO();
        if(dao.update(v)){
            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!"
                    , "Cadastro confirmado",JOptionPane.INFORMATION_MESSAGE,vacina);
            Controle.principal.pesquisar(Controle.principal.modelo, Controle.agenteSelecionada);
            limparFormulario();
            dispose();
            Controle.alteraVacinante = null;
        }
    }

    private void limparFormulario() {
        tfNome.setText("");
        tfNascimento.setText("");
        tfIdade.setText("");
        tfNomeMae.setText("");
        tfCpf.setText("");
        tfCns.setText("");
        tfEndereco.setText("");
        cbLaboratorio.setSelectedIndex(0);
        tfDose1.setText("");
        tfDose2.setText("");
        cbStatus.setSelectedIndex(0);
        if (cbStatus.getSelectedItem().toString().equals("Vacinado")) {
            lbIlustracao.setIcon(vacina);
        } else {
            lbIlustracao.setIcon(covid);
        }
    }
}
