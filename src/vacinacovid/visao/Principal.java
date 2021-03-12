package vacinacovid.visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import vacinacovid.Utilidades;
import vacinacovid.daos.VacinanteDAO;
import vacinacovid.modelo.VacinanteBean;

/**
 *
 * @author maxwell
 */
public class Principal extends JFrame {

    //Declarando objetos
    private JMenuBar menu;
    private JMenu mnuCadastros, mnuRelatorios;
    private JMenuItem mnuEntrada, mnuSaida, mnuManutencao;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private DefaultTableModel modelo = new DefaultTableModel();
    private ListSelectionModel lms;
    private JPanel pnFundo, pnFiltros, pnFaixaEtaria, pnStatus, pnBuscaRapida, pnLab, pnBotoes, pnRodaPe;
    private JLabel lbEsf, lbAcs, lbTraco, lbTotal, lbRegistros, lbLogo;
    private JComboBox cbEsf, cbAcs, cbFaixaEtaria1, cbFaixaEtaria2;
    private TitledBorder bordaFiltro, bordaFaixaEtaria, bordaStatus, bordaBuscaDetalhada, bordaLab, bordaBotoes, bordaRodaPe;
    private JCheckBox chVacinado, chNaoVacinado, chRecusou, chAstrazeneca, chButantan, chPfizer;
    private JButton btNome, btCpf, btCns, btInserir, btEditar, btExcluir, btGerarRelatorio;
    private ImageIcon logo, add, edit, delete, report, pessoa;
    

    public Principal(String esf) {
        super("Vacinação contra Covid-19 <<Secretaria Municipal de Saúde>>");
        URL url = this.getClass().getResource("/vacinacovid/visao/vacina.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
        setIconImage(iconeTitulo);
        construindoBarraDeMenu();
        construirTabela(esf);
        construindoTela(1366, 768);
        cbEsf.setSelectedItem(esf);
        cbEsf.setEnabled(esf.equals("Todos"));
    }

    private void construindoTela(int largura, int altura) {
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
        lbEsf.setBounds(15,20,200,20);
        pnFiltros.add(lbEsf);
        
        cbEsf = new JComboBox();
        cbEsf.setBounds(15, 40, 300, 20);
        carregarEsf();
        pnFiltros.add(cbEsf);
        
        lbAcs = new JLabel("ACS:");
        lbAcs.setBounds(15,60,200,20);
        pnFiltros.add(lbAcs);
        
        cbAcs = new JComboBox();
        cbAcs.setBounds(15, 80, 300, 20);
        pnFiltros.add(cbAcs);
        
        //Painel Faixa Etária
        pnFaixaEtaria = new JPanel(null);
        bordaFaixaEtaria  = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Faixa etária");
        pnFaixaEtaria.setBorder(bordaFaixaEtaria);
        pnFaixaEtaria.setBounds(330, 20, 161, 84);
        pnFaixaEtaria.setBackground(Color.white);
        cbFaixaEtaria1 = new JComboBox();
        cbFaixaEtaria1.setBounds(20, 35, 50, 20);
        lbTraco = new JLabel("-");
        lbTraco.setBounds(77, 32, 10, 20);
        cbFaixaEtaria2 = new JComboBox();
        cbFaixaEtaria2.setBounds(90, 35, 50, 20);                
        carregaCbFaixaEtaria();
        cbFaixaEtaria2.setSelectedItem(110);
        pnFaixaEtaria.add(cbFaixaEtaria1);
        pnFaixaEtaria.add(lbTraco);
        pnFaixaEtaria.add(cbFaixaEtaria2);
        
        //Painel Status
        pnStatus = new JPanel(null);
        bordaStatus  = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Status");
        pnStatus.setBorder(bordaStatus);
        pnStatus.setBounds(500, 20, 161, 84);
        pnStatus.setBackground(Color.white);
        chVacinado = new JCheckBox("Vacinado", true);
        chVacinado.setBounds(10, 15, 120, 20);
        chVacinado.setBackground(Color.white);
        chNaoVacinado = new JCheckBox("Não Vacinado", true);
        chNaoVacinado.setBounds(10, 35, 130, 20);
        chNaoVacinado.setBackground(Color.white);
        chRecusou = new JCheckBox("Recusou", true);
        chRecusou.setBounds(10, 55, 120, 20);
        chRecusou.setBackground(Color.white);
        
        pnStatus.add(chVacinado);
        pnStatus.add(chNaoVacinado);
        pnStatus.add(chRecusou);
        
        //Painel Laboratório
        pnLab = new JPanel(null);
        bordaLab  = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Laboratório");
        pnLab.setBorder(bordaLab);
        pnLab.setBounds(670, 20, 161, 84);
        pnLab.setBackground(Color.white);
        chAstrazeneca = new JCheckBox("Astrazeneca", true);
        chAstrazeneca.setBounds(10, 15, 120, 20);
        chAstrazeneca.setBackground(Color.white);
        chButantan = new JCheckBox("Coronavac", true);
        chButantan.setBounds(10, 35, 130, 20);
        chButantan.setBackground(Color.white);
        chPfizer = new JCheckBox("PFizer", true);
        chPfizer.setBounds(10, 55, 120, 20);
        chPfizer.setBackground(Color.white);
        
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
        btCpf = new JButton("CPF", null);
        btCpf.setBounds(15, 50, 100, 25);
        btCns = new JButton("CNS", null);
        btCns.setBounds(15, 80, 100, 25);
        
        pnBuscaRapida.add(btNome);
        pnBuscaRapida.add(btCpf);
        pnBuscaRapida.add(btCns);
        
        
        //Painel de botões
        pnBotoes = new JPanel(null);
        
        pnBotoes.setBackground(Color.white);
        pnBotoes.setBounds( 988, 3, 131, 120);
        bordaBotoes = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Cadastros");
        pnBotoes.setBorder(bordaBotoes);
        add = new ImageIcon(this.getClass().getResource("file-new-16x16.png"));
        btInserir = new JButton("Inserir", add);
        btInserir.setBounds(15, 20, 100, 25);
        edit = new ImageIcon(this.getClass().getResource("edit-16x16.png"));
        btEditar = new JButton("Editar", edit);
        btEditar.setBounds(15, 50, 100, 25);
        delete = new ImageIcon(this.getClass().getResource("edit-delete-16x16.png"));
        btExcluir = new JButton("Excluir", delete);
        btExcluir.setBounds(15, 80, 100, 25);
        
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
        
        lbRegistros = new JLabel("0");
        lbRegistros.setBounds(150, 20, 200, 20);
        
        report = new ImageIcon(this.getClass().getResource("report.png"));
        btGerarRelatorio = new JButton("Gerar Relatório",report);
        btGerarRelatorio.setBounds(1170, 10, 163, 30);
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
    }

    private void construindoBarraDeMenu() {
        menu = new JMenuBar();
        //setJMenuBar(menu);

        mnuCadastros = new JMenu("Menu");
        menu.add(mnuCadastros);
        mnuEntrada = new JMenuItem("Cadastrar Vacinante");
        mnuCadastros.add(mnuEntrada);
        mnuSaida = new JMenuItem("Realizar Backup");
        mnuCadastros.add(mnuSaida);

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
        pesquisar(modelo, esf);
    }

    public void pesquisar(DefaultTableModel modelo, String esf) {
        modelo.setNumRows(0);
        VacinanteDAO dao = new VacinanteDAO();

        if (esf.equals("Todos")) {
            for (VacinanteBean v : dao.select()) {
                modelo.addRow(
                        new Object[]{
                            v.getId() + " ",
                            v.getNome(),
                            v.getDtNascimentoStr(),
                            v.getIdade() + " anos",
                            v.getEndereco(),
                            Utilidades.mascara(v.getCpf(), "###.###.###-##"),
                            Utilidades.mascara(v.getCns(), "### #### #### ####"),
                            v.getNomeMae(),
                            v.getAgente().getNome(),
                            v.getAgente().getUbs().getNome(),
                            v.status(),
                            v.primeiraDose(),
                            v.segundaDose()
                        }
                );
            }
        } else {
            for (VacinanteBean v : dao.select("ESF "+esf)) {
                modelo.addRow(
                        new Object[]{
                            v.getId() + " ",
                            v.getNome(),
                            v.getDtNascimentoStr(),
                            v.getIdade() + " anos",
                            v.getEndereco(),
                            Utilidades.mascara(v.getCpf(), "###.###.###-##"),
                            Utilidades.mascara(v.getCns(), "### #### #### ####"),
                            v.getNomeMae(),
                            v.getAgente().getNome(),
                            v.getAgente().getUbs().getNome(),
                            v.status(),
                            v.primeiraDose(),
                            v.segundaDose()
                        }
                );
            }
        }

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

    private void carregaCbFaixaEtaria() {
        int idade = 0;
        while (idade<=110) {
            cbFaixaEtaria1.addItem(idade);
            cbFaixaEtaria2.addItem(idade);
            idade ++;
        }
    }

}
