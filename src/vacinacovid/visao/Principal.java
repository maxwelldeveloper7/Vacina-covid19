package vacinacovid.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
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
    private JPanel pnFundo, pnFundoFiltros;

    public Principal(String esf) {
        super("Vacinação contra Covid-19 <<Secretaria Municipal de Saúde>>");
        URL url = this.getClass().getResource("/vacinacovid/visao/favicon.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
        setIconImage(iconeTitulo);
        construindoBarraDeMenu();
        construirTabela(esf);
        construindoTela(1366, 768);
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
        pnFundoFiltros = new JPanel(null);
        
        barraRolagem.setBounds(2, 100, 1353, 480);
        pnFundoFiltros.setBackground(Color.red);
        
        pnFundo.add(pnFundoFiltros);
        pnFundo.add(barraRolagem);

        getContentPane().add(pnFundo);
    }

    private void construindoBarraDeMenu() {
        menu = new JMenuBar();
        setJMenuBar(menu);

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
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

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

        if (esf.equals("Todas")) {
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

}
