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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
public class PrincipalGestor extends JFrame implements ActionListener {

    //Declarando objetos
    private JTable tabela;
    private JScrollPane barraRolagem;
    public DefaultTableModel modelo;
    private ListSelectionModel lms;
    private DefaultTableCellRenderer direita, centro, esquerda, cor;
    private JPanel pnFundo, pnFiltros, pnFaixaEtaria, pnStatus, pnBuscaRapida, pnLab, pnBotoes, pnRodaPe;
    private JLabel lbEsf, lbAcs, lbTraco, lbTotal, lbLogo;
    public JComboBox cbEsf, cbAcs, cbFaixaEtaria1, cbFaixaEtaria2;
    private TitledBorder bordaFiltro, bordaFaixaEtaria, bordaStatus, bordaBuscaDetalhada, bordaLab, bordaBotoes, bordaRodaPe;
    private JRadioButton chVacinado, chNaoVacinado, chRecusou;
    private JCheckBox chAstrazeneca, chButantan, chPfizer;
    private ButtonGroup statusGroup;
    private JButton btNome, btCpf, btCns, btInserir, btEditar, btExcluir, btGerarRelatorio;
    private ImageIcon logo, add, edit, delete, report, pessoa, cpf, cns;
    private JLabel lbRegistros = new JLabel("0");
    private List<VacinanteBean> lista = new ArrayList();
    public VacinanteBean vacinante;

    public PrincipalGestor() {

        inicializarObjetos();
        construirTela(1366, 768);
        carregarDados();
        registrarEventos();
        Runtime.getRuntime().gc();
    }

    private void inicializarObjetos() {

        pnFundo = new JPanel(null);
        pnFiltros = new JPanel(null);

        lbEsf = new JLabel("ESF:");
        cbEsf = new JComboBox();

        lbAcs = new JLabel("ACS:");
        cbAcs = new JComboBox();

        pnFaixaEtaria = new JPanel(null);
        cbFaixaEtaria1 = new JComboBox();
        lbTraco = new JLabel("-");
        cbFaixaEtaria2 = new JComboBox();

        pnStatus = new JPanel(null);
        chVacinado = new JRadioButton("Vacinado");
        chNaoVacinado = new JRadioButton("Não Vacinado");
        chRecusou = new JRadioButton("Recusou");

        statusGroup = new ButtonGroup();
        statusGroup.add(chVacinado);
        statusGroup.add(chNaoVacinado);
        statusGroup.add(chRecusou);

        pnLab = new JPanel(null);
        chAstrazeneca = new JCheckBox("Astrazeneca");
        chButantan = new JCheckBox("Coronavac");
        chPfizer = new JCheckBox("PFizer");

        pnBuscaRapida = new JPanel(null);
        pessoa = new ImageIcon(this.getClass().getResource("pessoa.png"));
        btNome = new JButton("Nome", pessoa);
        cpf = new ImageIcon(this.getClass().getResource("cpf.png"));
        btCpf = new JButton("CPF", cpf);
        cns = new ImageIcon(this.getClass().getResource("cns.png"));
        btCns = new JButton("CNS", cns);

        pnBotoes = new JPanel(null);
        add = new ImageIcon(this.getClass().getResource("inserir.png"));
        btInserir = new JButton("Inserir", add);
        edit = new ImageIcon(this.getClass().getResource("editar.png"));
        btEditar = new JButton("Editar", edit);
        delete = new ImageIcon(this.getClass().getResource("delete.png"));
        btExcluir = new JButton("Excluir", delete);

        logo = new ImageIcon(this.getClass().getResource("Nanuque-logo.jpeg"));
        lbLogo = new JLabel(logo);

        modelo = new DefaultTableModel();
        tabela = new JTable(modelo);
        direita = new DefaultTableCellRenderer();
        centro = new DefaultTableCellRenderer();
        esquerda = new DefaultTableCellRenderer();
        cor = new DefaultTableCellRenderer();
        barraRolagem = new JScrollPane(tabela);

        pnRodaPe = new JPanel(null);
        lbTotal = new JLabel("Total de Pessoas:");
        report = new ImageIcon(this.getClass().getResource("report.png"));
        btGerarRelatorio = new JButton("Gerar Relatório", report);
    }

    private void construirTela(int largura, int altura) {
        setTitle("Vacinação contra Covid-19 <<Secretaria Municipal de Saúde - Módulo Gestor>> Versão 1.0");
        URL url = this.getClass().getResource("/vacinacovid/visao/vacina.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
        setIconImage(iconeTitulo);
        Dimension tamanhoDaTela = Toolkit.getDefaultToolkit().getScreenSize();
        //posicionando tela
        setBounds((tamanhoDaTela.width - largura) / 2, (tamanhoDaTela.height - altura) / 2, largura, altura);
        //removendo decoração do OS
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Painel principal de fundo
        pnFundo.setBackground(Color.white);

        //Painel de filtros
        pnFiltros.setBackground(Color.white);
        pnFiltros.setBounds(5, 3, 845, 120);//Definindo tamanho e posição
        bordaFiltro = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Filtros");
        pnFiltros.setBorder(bordaFiltro);//Definindo borda

        //Definindo tamanho, posição e adicionando ao painel de filtros
        lbEsf.setBounds(15, 20, 200, 20);

        pnFiltros.add(lbEsf);

        cbEsf.setBounds(15, 40, 300, 20);
        pnFiltros.add(cbEsf);

        lbAcs.setBounds(15, 60, 200, 20);
        pnFiltros.add(lbAcs);

        cbAcs.setBounds(15, 80, 300, 20);
        pnFiltros.add(cbAcs);

        //painel faixa etária
        bordaFaixaEtaria = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Faixa etária");
        pnFaixaEtaria.setBorder(bordaFaixaEtaria);
        pnFaixaEtaria.setBounds(330, 20, 161, 84);
        pnFaixaEtaria.setBackground(Color.white);

        cbFaixaEtaria1.setBounds(20, 35, 50, 20);
        pnFaixaEtaria.add(cbFaixaEtaria1);

        lbTraco.setBounds(77, 32, 10, 20);
        pnFaixaEtaria.add(lbTraco);

        cbFaixaEtaria2.setBounds(90, 35, 50, 20);
        cbFaixaEtaria2.setSelectedItem(110);
        pnFaixaEtaria.add(cbFaixaEtaria2);
        //adiciona painel faixa etária ao painel filtros
        pnFiltros.add(pnFaixaEtaria);

        //painel de status
        bordaStatus = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Status");
        pnStatus.setBorder(bordaStatus);
        pnStatus.setBounds(500, 20, 161, 84);
        pnStatus.setBackground(Color.white);

        chVacinado.setBounds(10, 15, 120, 20);
        chVacinado.setBackground(Color.white);
        pnStatus.add(chVacinado);

        chNaoVacinado.setBounds(10, 35, 130, 20);
        chNaoVacinado.setBackground(Color.white);
        pnStatus.add(chNaoVacinado);

        chRecusou.setBounds(10, 55, 120, 20);
        chRecusou.setBackground(Color.white);
        pnStatus.add(chRecusou);
        //adiciona painel de status ao painel de filtros        
        pnFiltros.add(pnStatus);
        pnFundo.add(pnFiltros);

        //painel de laboratórios        
        bordaLab = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Laboratório");
        pnLab.setBorder(bordaLab);
        pnLab.setBounds(670, 20, 161, 84);
        pnLab.setBackground(Color.white);

        chAstrazeneca.setBounds(10, 15, 120, 20);
        chAstrazeneca.setBackground(Color.white);
        pnLab.add(chAstrazeneca);

        chButantan.setBounds(10, 35, 130, 20);
        chButantan.setBackground(Color.white);
        pnLab.add(chButantan);

        chPfizer.setBounds(10, 55, 120, 20);
        chPfizer.setBackground(Color.white);
        pnLab.add(chPfizer);

        pnFiltros.add(pnLab);

        //painel busca rápida
        pnBuscaRapida.setBackground(Color.white);
        pnBuscaRapida.setBounds(853, 3, 131, 120);
        bordaBuscaDetalhada = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Busca Rápida");
        pnBuscaRapida.setBorder(bordaBuscaDetalhada);

        btNome.setBounds(15, 20, 100, 25);
        pnBuscaRapida.add(btNome);

        btCpf.setBounds(15, 50, 100, 25);
        btCpf.setEnabled(false);
        pnBuscaRapida.add(btCpf);

        btCns.setBounds(15, 80, 100, 25);
        btCns.setEnabled(false);
        pnBuscaRapida.add(btCns);

        pnFundo.add(pnBuscaRapida);

        //Painel de botões
        pnBotoes.setBackground(Color.white);
        pnBotoes.setBounds(988, 3, 131, 120);
        bordaBotoes = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Cadastros");
        pnBotoes.setBorder(bordaBotoes);

        btInserir.setBounds(15, 20, 100, 25);
        btInserir.setEnabled(true);
        pnBotoes.add(btInserir);

        btEditar.setBounds(15, 50, 100, 25);
        btEditar.setEnabled(false);
        pnBotoes.add(btEditar);

        btExcluir.setBounds(15, 80, 100, 25);
        btExcluir.setEnabled(false);
        pnBotoes.add(btExcluir);
        pnFundo.add(pnBotoes);

        //Logo da prefeitura
        lbLogo.setBounds(1130, 9, 210, 110);
        pnFundo.add(lbLogo);

        //tabela
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

        barraRolagem.setBounds(7, 128, 1343, 550);
        lms = tabela.getSelectionModel();
        pnFundo.add(barraRolagem);

        //Rodapé
        pnRodaPe.setBackground(Color.white);
        pnRodaPe.setBounds(6, 682, 1344, 50);
        bordaRodaPe = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        pnRodaPe.setBorder(bordaRodaPe);
        lbTotal.setBounds(20, 20, 135, 20);
        lbRegistros.setBounds(150, 20, 200, 20);
        btGerarRelatorio.setBounds(1170, 10, 163, 30);
        btGerarRelatorio.setEnabled(false);
        pnRodaPe.add(lbTotal);
        pnRodaPe.add(lbRegistros);
        pnRodaPe.add(btGerarRelatorio);
        pnFundo.add(pnRodaPe);

        getContentPane().add(pnFundo);
    }

    private void carregarDados() {

        carregaCbFaixaEtaria();
        carregarCbEsf();
        cbAcs.addItem("Todos");
        cbAcs.setEnabled(false);
        //chVacinado.setSelected(true);
        //chNaoVacinado.setSelected(true);
        //chRecusou.setSelected(true);
        //chAstrazeneca.setSelected(true);
        //chButantan.setSelected(true);
        //chPfizer.setSelected(true);

        cbFaixaEtaria1.setFocusable(false);
        cbFaixaEtaria2.setFocusable(false);
        cbAcs.setFocusable(false);
        cbEsf.setFocusable(false);
        chVacinado.setFocusable(false);
        chNaoVacinado.setFocusable(false);
        chRecusou.setFocusable(false);
        chAstrazeneca.setFocusable(false);
        chButantan.setFocusable(false);
        chPfizer.setFocusable(false);
        tabela.setFocusable(false);

        int resposta = JOptionPane.showConfirmDialog(this,
                "Você deseja exibir todos os paciantes cadastrados?\n"
                + "Clique em não para realizar uma pesquisa personalizada.",
                "Mensagem", JOptionPane.YES_NO_OPTION);
        if (resposta == 0) {
            pesquisar(modelo, "Todos");
        }
    }

    private void registrarEventos() {
        lms.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                tbLinhaSelecionada(tabela);
            }

        });

        btInserir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Controle.abrirTelaCadVacinanteIrrestrito();

            }
        });

        btEditar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Controle.abrirTelaAlteraVacinanteIrrestrito();

            }
        });

        cbEsf.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                carregaCbAcs();
                pesquisarComFiltro();//montarSql();
            }
        });

        cbAcs.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarComFiltro();//montarSql();
            }
        });

        cbFaixaEtaria1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                pesquisarComFiltro();

            }
        });

        cbFaixaEtaria2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (Integer.parseInt(cbFaixaEtaria1.getSelectedItem().toString()) > Integer.parseInt(cbFaixaEtaria2.getSelectedItem().toString())) {
                    int f1 = Integer.parseInt(cbFaixaEtaria1.getSelectedItem().toString());
                    int f2 = Integer.parseInt(cbFaixaEtaria2.getSelectedItem().toString());
                    cbFaixaEtaria2.setSelectedItem(f1);
                    cbFaixaEtaria1.setSelectedItem(f2);
                }
                pesquisarComFiltro();

            }
        });

        chVacinado.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarComFiltro();
            }
        });

        chNaoVacinado.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarComFiltro();
            }
        });

        chRecusou.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarComFiltro();
            }
        });

        chAstrazeneca.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarComFiltro();
            }
        });

        chButantan.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarComFiltro();
            }
        });

        chPfizer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarComFiltro();
            }
        });

        btNome.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String nome;
                nome = JOptionPane.showInputDialog(null, "Informe o nome:");

                pesquisarPorVacinante(modelo, nome);

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

    private void pesquisarPorVacinante(DefaultTableModel modelo, String vacinante) {
        modelo.setNumRows(0);
        VacinanteDAO dao = new VacinanteDAO();

        Integer contador = 0;
        lista.clear();
        lista = dao.selectVacinante(vacinante);
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

    public void pesquisarComFiltro() {
        modelo.setNumRows(0);
        VacinanteDAO dao = new VacinanteDAO();

        Integer contador = 0;
        lista.clear();
        montarSql();
        lista = dao.selectComFiltro(montarSql());

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

    private void carregaCbAcs() {
        AcsDAO dao = new AcsDAO();

        for (int i = 1; i < cbAcs.getItemCount(); i++) {
            cbAcs.removeItemAt(i);
        }

        if (cbEsf.getSelectedItem().toString().equals("Todos")) {
            cbAcs.setEnabled(false);
        } else {
            cbAcs.setEnabled(true);
            for (AcsBean acs : dao.select("ESF " + cbEsf.getSelectedItem().toString())) {
                cbAcs.addItem(acs.getNome());
            }
        }

    }

    private void carregaCbFaixaEtaria() {
        int idade = 0;
        cbFaixaEtaria1.addItem("...");
        while (idade <= 110) {
            cbFaixaEtaria1.addItem(idade);
            cbFaixaEtaria2.addItem(idade);
            idade++;
        }
        cbFaixaEtaria2.setSelectedIndex(cbFaixaEtaria2.getItemCount() - 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btInserir) {
            if (Controle.agenteSelecionada.getNome() != null) {
                Controle.abrirTelaCadVacinante();
            } else {
                Controle.abrirTelaCadVacinanteIrrestrito();
            }

        }
        if (e.getSource() == btEditar) {
            if (Controle.agenteSelecionada.getNome() != null) {
                Controle.abrirTelaAlteraVacinante();
            } else {
                Controle.abrirTelaAlteraVacinanteIrrestrito();
            }
        }
    }

    private String montarSql() {
        String sql = "SELECT * FROM vw_vacinantes";

        if (!cbEsf.getSelectedItem().toString().equals("Todos")) {
            String esf = "ESF " + cbEsf.getSelectedItem().toString();
            sql += " where nomeubs = \'" + esf + "\'";
            if (!cbAcs.getSelectedItem().toString().equals("Todos")) {
                String acs = cbAcs.getSelectedItem().toString();
                sql += " and nomeacs = \'" + acs + "\'";
            }
        }

        if (!cbFaixaEtaria1.getSelectedItem().toString().equals("...") && !cbFaixaEtaria2.getSelectedItem().toString().equals("...")) {
            if (sql.equals("SELECT * FROM vw_vacinantes")) {
                String faixa = cbFaixaEtaria1.getSelectedItem().toString();
                sql += " where idade >= " + faixa;
                faixa = cbFaixaEtaria2.getSelectedItem().toString();
                sql += " and idade <= " + faixa;
            } else {
                String faixa = cbFaixaEtaria1.getSelectedItem().toString();
                sql += " and idade >= " + faixa;
                faixa = cbFaixaEtaria2.getSelectedItem().toString();
                sql += " and idade <= " + faixa;
            }

        }

        if (chNaoVacinado.isSelected()) {
            if (sql.equals("SELECT * FROM vw_vacinantes")) {
                sql += " where status = 0";
            } else {
                sql += " and status = 0";
            }
        }

        if (chVacinado.isSelected()) {
            if (sql.equals("SELECT * FROM vw_vacinantes")) {
                sql += " where status = 1";
            } else {
                sql += " and status = 1";
            }
        }

        if (chRecusou.isSelected()) {
            if (sql.equals("SELECT * FROM vw_vacinantes")) {
                sql += " where status = 2";
            } else {
                sql += " and status = 2";
            }
        }

        if (chAstrazeneca.isSelected()) {
            if (sql.equals("SELECT * FROM vw_vacinantes")) {
                sql += " where nomevacina = \'Astrazeneca\'";
            } else {
                sql += " and nomevacina = \'Astrazeneca\'";
            }
        }

        if (chButantan.isSelected()) {
            if (sql.equals("SELECT * FROM vw_vacinantes")) {
                sql += " where nomevacina = \'Coronavac\'";
            } else {
                sql += " and nomevacina = \'Coronavac\'";
            }
        }

        if (chPfizer.isSelected()) {
            if (sql.equals("SELECT * FROM vw_vacinantes")) {
                sql += " where nomevacina = \'Pfizer\'";
            } else {
                sql += " and nomevacina = \'Pfizer\'";
            }
        }
        sql += " order by nome asc";
        return sql;
    }

}
