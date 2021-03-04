/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacinacovid.visao;

import java.awt.BorderLayout;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author maxwell
 */
public class Principal extends JFrame{

    //Declarando objetos
    private JMenuBar menu;
    private JMenu mnuCadastros, mnuRelatorios;
    private JMenuItem mnuEntrada, mnuSaida, mnuManutencao;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private DefaultTableModel modelo = new DefaultTableModel();
    private JPanel pnFundo;
    
    
    
    public Principal() {
        super("Vacinação contra Covid-19 <<Secretaria Municipal de Saúde>>");
        URL url = this.getClass().getResource("/vacinacovid/visao/favicon.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
        setIconImage(iconeTitulo);        
        construindoBarraDeMenu();
        construirTabela();
        construindoTela(1200, 700);
    }
    
    
    private void construindoTela(int largura, int altura) {
        Dimension tamanhoDaTela = Toolkit.getDefaultToolkit().getScreenSize();
        //posicionando tela
        setBounds((tamanhoDaTela.width - largura)/2, (tamanhoDaTela.height - altura)/2, largura, altura);
        //removendo decoração do OS
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        pnFundo = new JPanel(new BorderLayout());
        
        pnFundo.add(BorderLayout.CENTER, barraRolagem);
        
        getContentPane().add(pnFundo);
    }

    private void construindoBarraDeMenu() {        
        menu = new JMenuBar();
        setJMenuBar(menu);        
        
        mnuCadastros = new JMenu("Cadastros");
        menu.add(mnuCadastros);
        mnuEntrada = new JMenuItem("Entrada de Material");
        mnuCadastros.add(mnuEntrada);
        mnuSaida = new JMenuItem("Saída de Material");
        mnuCadastros.add(mnuSaida);
        mnuManutencao = new JMenuItem("Manutenção de Estoque");
        mnuCadastros.add(mnuManutencao);
        
    }

    private void construirTabela() {
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
        modelo.addColumn("Mãe");
        modelo.addColumn("ACS");
        modelo.addColumn("ESF");
        modelo.addColumn("Resp. pelo Preenchimento");
        modelo.addColumn("Cargo");
        
        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(90);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(300);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(110);
        tabela.getColumnModel().getColumn(6).setPreferredWidth(200);
        tabela.getColumnModel().getColumn(7).setPreferredWidth(200);
        tabela.getColumnModel().getColumn(8).setPreferredWidth(200);
        tabela.getColumnModel().getColumn(9).setPreferredWidth(150);
        tabela.getColumnModel().getColumn(9).setPreferredWidth(180);
        tabela.getColumnModel().getColumn(10).setPreferredWidth(100);
        
        
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        barraRolagem = new JScrollPane(tabela);
        
    }
    
    

}
