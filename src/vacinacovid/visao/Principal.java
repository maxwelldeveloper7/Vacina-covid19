package vacinacovid.visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import vacinacovid.Utilidades;
import vacinacovid.controle.Controle;
import vacinacovid.daos.AcsDAO;
import vacinacovid.daos.VacinanteDAO;
import vacinacovid.modelo.AcsBean;
import vacinacovid.modelo.VacinanteBean;

/**
 *
 * @author maxwell
 */
public class Principal extends JFrame implements ActionListener {

    //Declarando objetos
    private JTable tabela;
    private JScrollPane barraRolagem;
    public DefaultTableModel modelo = new DefaultTableModel();
    private ListSelectionModel lms;
    private JPanel pnFundo, pnFiltros, pnFaixaEtaria, pnStatus, pnBuscaRapida, pnLab, pnBotoes, pnRodaPe;
    private JLabel lbEsf, lbAcs, lbTraco, lbTotal, lbLogo;
    public JComboBox cbEsf, cbAcs, cbFaixaEtaria1, cbFaixaEtaria2;
    private TitledBorder bordaFiltro, bordaFaixaEtaria, bordaStatus, bordaBuscaDetalhada, bordaLab, bordaBotoes, bordaRodaPe;
    private JCheckBox chVacinado, chNaoVacinado, chRecusou, chAstrazeneca, chButantan, chPfizer;
    private JButton btNome, btCpf, btCns, btInserir, btEditar, btExcluir, btGerarRelatorio;
    private ImageIcon logo, add, edit, delete, report, pessoa, cpf, cns;
    private JLabel lbRegistros = new JLabel("0");
    private List<VacinanteBean> lista = new ArrayList();
    public VacinanteBean vacinante;

    public Principal(String esf) {
        super("Vacinação contra Covid-19 <<Secretaria Municipal de Saúde>> Versão 1.0");
        URL url = this.getClass().getResource("/vacinacovid/visao/vacina.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
        setIconImage(iconeTitulo);
        construirTabela(esf);
        construindoTela(1366, 768, esf);
        cbEsf.setSelectedItem(esf);
        cbEsf.setEnabled(esf.equals("Todos"));
    }

    private void construindoTela(int largura, int altura, String esf) {
        Dimension tamanhoDaTela = Toolkit.getDefaultToolkit().getScreenSize();
        //posicionando tela
        setBounds((tamanhoDaTela.width - largura) / 2, (tamanhoDaTela.height - altura) / 2, largura, altura);
        //removendo decoração do OS
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pnFundo = new JPanel(null);

        //Painel de Filtros
        pnFiltros = new JPanel(null);

        pnFundo.setBackground(Color.white);
        pnFiltros.setBackground(Color.white);
        pnFiltros.setBounds(5, 3, 845, 120);
        bordaFiltro = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Filtros");
        pnFiltros.setBorder(bordaFiltro);

        lbEsf = new JLabel("ESF:");
        lbEsf.setBounds(15, 20, 200, 20);
        pnFiltros.add(lbEsf);

        cbEsf = new JComboBox();
        cbEsf.setBounds(15, 40, 300, 20);
        carregarCbEsf();
        pnFiltros.add(cbEsf);

        lbAcs = new JLabel("ACS:");
        lbAcs.setBounds(15, 60, 200, 20);
        pnFiltros.add(lbAcs);

        cbAcs = new JComboBox();
        cbAcs.setBounds(15, 80, 300, 20);
       

        pnFiltros.add(cbAcs);

        //Painel Faixa Etária
        pnFaixaEtaria = new JPanel(null);
        bordaFaixaEtaria = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Faixa etária");
        pnFaixaEtaria.setBorder(bordaFaixaEtaria);
        pnFaixaEtaria.setBounds(330, 20, 161, 84);
        pnFaixaEtaria.setBackground(Color.white);
        cbFaixaEtaria1 = new JComboBox();
        cbFaixaEtaria1.setBounds(20, 35, 50, 20);
        cbFaixaEtaria1.setEnabled(false);
        lbTraco = new JLabel("-");
        lbTraco.setBounds(77, 32, 10, 20);
        cbFaixaEtaria2 = new JComboBox();
        cbFaixaEtaria2.setBounds(90, 35, 50, 20);
        carregaCbFaixaEtaria();
        cbFaixaEtaria2.setSelectedItem(110);
        cbFaixaEtaria2.setEnabled(false);
        pnFaixaEtaria.add(cbFaixaEtaria1);
        pnFaixaEtaria.add(lbTraco);
        pnFaixaEtaria.add(cbFaixaEtaria2);

        //Painel Status
        pnStatus = new JPanel(null);
        bordaStatus = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Status");
        pnStatus.setBorder(bordaStatus);
        pnStatus.setBounds(500, 20, 161, 84);
        pnStatus.setBackground(Color.white);
        chVacinado = new JCheckBox("Vacinado");
        chVacinado.setBounds(10, 15, 120, 20);
        chVacinado.setBackground(Color.white);
        chVacinado.setEnabled(false);
        chNaoVacinado = new JCheckBox("Não Vacinado");
        chNaoVacinado.setBounds(10, 35, 130, 20);
        chNaoVacinado.setBackground(Color.white);
        chNaoVacinado.setEnabled(false);
        chRecusou = new JCheckBox("Recusou");
        chRecusou.setBounds(10, 55, 120, 20);
        chRecusou.setBackground(Color.white);
        chRecusou.setEnabled(false);

        pnStatus.add(chVacinado);
        pnStatus.add(chNaoVacinado);
        pnStatus.add(chRecusou);

        //Painel Laboratório
        pnLab = new JPanel(null);
        bordaLab = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Laboratório");
        pnLab.setBorder(bordaLab);
        pnLab.setBounds(670, 20, 161, 84);
        pnLab.setBackground(Color.white);
        chAstrazeneca = new JCheckBox("Astrazeneca");
        chAstrazeneca.setBounds(10, 15, 120, 20);
        chAstrazeneca.setEnabled(false);
        chAstrazeneca.setBackground(Color.white);
        chButantan = new JCheckBox("Coronavac");
        chButantan.setBounds(10, 35, 130, 20);
        chButantan.setBackground(Color.white);
        chButantan.setEnabled(false);
        chPfizer = new JCheckBox("PFizer");
        chPfizer.setBounds(10, 55, 120, 20);
        chPfizer.setBackground(Color.white);
        chPfizer.setEnabled(false);

        pnLab.add(chAstrazeneca);
        pnLab.add(chButantan);
        pnLab.add(chPfizer);

        pnFiltros.add(pnFaixaEtaria);
        pnFiltros.add(pnStatus);
        pnFiltros.add(pnLab);

        //Painel de busca detalhada
        pnBuscaRapida = new JPanel(null);

        pnBuscaRapida.setBackground(Color.white);
        pnBuscaRapida.setBounds(853, 3, 131, 120);
        bordaBuscaDetalhada = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Busca Rápida");
        pnBuscaRapida.setBorder(bordaBuscaDetalhada);
        pessoa = new ImageIcon(this.getClass().getResource("pessoa.png"));
        btNome = new JButton("Nome", pessoa);
        btNome.setBounds(15, 20, 100, 25);
        btNome.setEnabled(false);
        cpf = new ImageIcon(this.getClass().getResource("cpf.png"));
        btCpf = new JButton("CPF", cpf);
        btCpf.setBounds(15, 50, 100, 25);
        btCpf.setEnabled(false);
        cns = new ImageIcon(this.getClass().getResource("cns.png"));
        btCns = new JButton("CNS", cns);
        btCns.setBounds(15, 80, 100, 25);
        btCns.setEnabled(false);

        pnBuscaRapida.add(btNome);
        pnBuscaRapida.add(btCpf);
        pnBuscaRapida.add(btCns);

        //Painel de botões
        pnBotoes = new JPanel(null);

        pnBotoes.setBackground(Color.white);
        pnBotoes.setBounds(988, 3, 131, 120);
        bordaBotoes = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Cadastros");
        pnBotoes.setBorder(bordaBotoes);
        add = new ImageIcon(this.getClass().getResource("inserir.png"));
        btInserir = new JButton("Inserir", add);
        btInserir.setBounds(15, 20, 100, 25);
        btInserir.setEnabled(true);
        edit = new ImageIcon(this.getClass().getResource("editar.png"));
        btEditar = new JButton("Editar", edit);
        btEditar.setBounds(15, 50, 100, 25);
        btEditar.setEnabled(false);
        delete = new ImageIcon(this.getClass().getResource("delete.png"));
        btExcluir = new JButton("Excluir", delete);
        btExcluir.setBounds(15, 80, 100, 25);
        btExcluir.setEnabled(false);

        pnBotoes.add(btInserir);
        pnBotoes.add(btEditar);
        pnBotoes.add(btExcluir);

        logo = new ImageIcon(this.getClass().getResource("Nanuque-logo.jpeg"));
        lbLogo = new JLabel(logo);
        lbLogo.setBounds(1130, 9, 210, 110);

        //adicionando barra de rolagem
        barraRolagem.setBounds(7, 128, 1343, 550);

        pnRodaPe = new JPanel(null);
        pnRodaPe.setBackground(Color.white);
        pnRodaPe.setBounds(6, 682, 1344, 50);
        bordaRodaPe = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        pnRodaPe.setBorder(bordaRodaPe);

        lbTotal = new JLabel("Total de Pessoas:");
        lbTotal.setBounds(20, 20, 135, 20);

        lbRegistros.setBounds(150, 20, 200, 20);

        report = new ImageIcon(this.getClass().getResource("report.png"));
        btGerarRelatorio = new JButton("Gerar Relatório", report);
        btGerarRelatorio.setBounds(1170, 10, 163, 30);
        btGerarRelatorio.setEnabled(false);
        pnRodaPe.add(lbTotal);
        pnRodaPe.add(lbRegistros);
        pnRodaPe.add(btGerarRelatorio);

        pnFundo.add(pnFiltros);
        pnFundo.add(pnBuscaRapida);
        pnFundo.add(pnBotoes);
        pnFundo.add(lbLogo);
        pnFundo.add(barraRolagem);
        pnFundo.add(pnRodaPe);

        getContentPane().add(pnFundo);

        cbEsf.addActionListener(this);
        btInserir.addActionListener(this);
        btEditar.addActionListener(this);
    }

    private void construirTabela(String esf) {
        tabela = new JTable(modelo);

        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer cor = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        cor.setBackground(Color.LIGHT_GRAY);

        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Nascmento");
        modelo.addColumn("Idade");
        modelo.addColumn("Endereço");
        modelo.addColumn("CPF");
        modelo.addColumn("Cartão SUS");
        modelo.addColumn("Mãe");
        modelo.addColumn("ACS");
        modelo.addColumn("ESF");
        modelo.addColumn("Status");
        modelo.addColumn("Laboratório");
        modelo.addColumn("1ª Dose");
        modelo.addColumn("2ª Dose");

        tabela.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(90);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(300);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(110);
        tabela.getColumnModel().getColumn(6).setPreferredWidth(140);
        tabela.getColumnModel().getColumn(7).setPreferredWidth(170);
        tabela.getColumnModel().getColumn(8).setPreferredWidth(170);
        tabela.getColumnModel().getColumn(9).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(10).setPreferredWidth(90);

        tabela.getColumnModel().getColumn(0).setCellRenderer(direita);
        tabela.getColumnModel().getColumn(2).setCellRenderer(centro);
        tabela.getColumnModel().getColumn(3).setCellRenderer(centro);
        tabela.getColumnModel().getColumn(5).setCellRenderer(centro);
        tabela.getColumnModel().getColumn(6).setCellRenderer(centro);
        tabela.getColumnModel().getColumn(10).setCellRenderer(centro);

        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        barraRolagem = new JScrollPane(tabela);

        lms = tabela.getSelectionModel();
        lms.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                tbLinhaSelecionada(tabela);
            }

        });

    }

    private void tbLinhaSelecionada(JTable tabela) {
        if (tabela.getSelectedRow() != -1) {
            btEditar.setEnabled(true);
            Controle.setVacinante(lista.get(tabela.getSelectedRow()));
        } else {
            btEditar.setEnabled(false);
            Controle.setVacinante(new VacinanteBean());
        }
    }

    public void pesquisar(DefaultTableModel modelo, String esf) {
        modelo.setNumRows(0);
        VacinanteDAO dao = new VacinanteDAO();
        Integer contador = 0;

        lista.clear();

        if (esf.equals("Todos")) {
            lista = dao.select();
            for (int i = 0; i < lista.size(); i++) {
                modelo.addRow(
                        new Object[]{
                            lista.get(i).getId() + " ",
                            lista.get(i).getNome(),
                            lista.get(i).getDtNascimentoStr(),
                            lista.get(i).getIdadeAnos(),
                            lista.get(i).getEndereco(),
                            Utilidades.mascara(lista.get(i).getCpf(), "###.###.###-##"),
                            Utilidades.mascara(lista.get(i).getCns(), "### #### #### ####"),
                            lista.get(i).getNomeMae(),
                            lista.get(i).getAgente().getNomeIniciasMaiusculas(),
                            lista.get(i).getAgente().getUbs().getNomeIniciaisMaiusculaSemSigla(),
                            lista.get(i).status(),
                            lista.get(i).getLaboratorio().getVacina(),
                            lista.get(i).primeiraDose(),
                            lista.get(i).segundaDose()
                        }
                );
                contador++;

            }
        } else {
            lista = dao.select("ESF " + esf);
            for (int i = 0; i < lista.size(); i++) {
                modelo.addRow(
                        new Object[]{
                            lista.get(i).getId() + " ",
                            lista.get(i).getNome(),
                            lista.get(i).getDtNascimentoStr(),
                            lista.get(i).getIdadeAnos(),
                            lista.get(i).getEndereco(),
                            Utilidades.mascara(lista.get(i).getCpf(), "###.###.###-##"),
                            Utilidades.mascara(lista.get(i).getCns(), "### #### #### ####"),
                            lista.get(i).getNomeMae(),
                            lista.get(i).getAgente().getNomeIniciasMaiusculas(),
                            lista.get(i).getAgente().getUbs().getNomeIniciaisMaiusculaSemSigla(),
                            lista.get(i).status(),
                            lista.get(i).getLaboratorio().getVacina(),
                            lista.get(i).primeiraDose(),
                            lista.get(i).segundaDose()
                        }
                );
                contador++;

            }
        }
        lbRegistros.setText(Integer.toString(contador));

    }

    public void pesquisar(DefaultTableModel modelo, AcsBean acs) {
        modelo.setNumRows(0);
        VacinanteDAO dao = new VacinanteDAO();

        Integer contador = 0;
        lista.clear();
        lista = dao.select(acs);
        for (int i = 0; i < lista.size(); i++) {
            modelo.addRow(
                    new Object[]{
                        lista.get(i).getId() + " ",
                        lista.get(i).getNome(),
                        lista.get(i).getDtNascimentoStr(),
                        lista.get(i).getIdadeAnos(),
                        lista.get(i).getEndereco(),
                        Utilidades.mascara(lista.get(i).getCpf(), "###.###.###-##"),
                        Utilidades.mascara(lista.get(i).getCns(), "### #### #### ####"),
                        lista.get(i).getNomeMae(),
                        lista.get(i).getAgente().getNomeIniciasMaiusculas(),
                        lista.get(i).getAgente().getUbs().getNomeIniciaisMaiusculaSemSigla(),
                        lista.get(i).status(),
                        lista.get(i).getLaboratorio().getVacina(),
                        lista.get(i).primeiraDose(),
                        lista.get(i).segundaDose()
                    }
            );
            contador++;

        }

        lbRegistros.setText(Integer.toString(contador));

    }

    private void carregarCbEsf() {
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

    private void carregaCbAcs(String esf) {
        AcsDAO dao = new AcsDAO();

        cbAcs.removeAllItems();

        if (Controle.agenteSelecionada.getNome() == null) {
            cbAcs.addItem("Todos");
            if (cbEsf.getSelectedItem().toString().equals("Todos")) {
                for (AcsBean acs : dao.select()) {
                    cbAcs.addItem(acs.getNome());
                }
            } else {
                for (AcsBean acs : dao.select("ESF " + cbEsf.getSelectedItem().toString())) {
                    cbAcs.addItem(acs.getNome());
                }
            }
            pesquisar(modelo, esf);
        } else {
            cbAcs.addItem(Controle.agenteSelecionada.getNome());
            pesquisar(modelo, Controle.agenteSelecionada);
        }
    }

    private void carregaCbFaixaEtaria() {
        int idade = 0;
        while (idade <= 110) {
            cbFaixaEtaria1.addItem(idade);
            cbFaixaEtaria2.addItem(idade);
            idade++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbEsf) {
            carregaCbAcs(cbEsf.getSelectedItem().toString());
        }
        if (e.getSource() == btInserir) {
            if (Controle.agenteSelecionada.getNome() != null) {
                Controle.abrirTelaCadVacinante();
            }else{
                Controle.abrirTelaCadVacinanteIrrestrito();
            }

        }
        if (e.getSource() == btEditar) {
            if (Controle.agenteSelecionada.getNome() != null) {
                Controle.abrirTelaAlteraVacinante();
                
                
            }else{
                Controle.abrirTelaAlteraVacinanteIrrestrito();                
            }
        }
    }

}
