/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacinacovid.visao;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRootPane;

/**
 *
 * @author maxwell
 */
public class Principal extends JFrame{

    //Declarando objetos
    private JMenuBar menu;
    private JMenu mnuCadastros, mnuRelatorios;
    private JMenuItem mnuEntrada, mnuSaida, mnuManutencao;
    
    
    public Principal() {
        super("Vacinação contra Covid-19 <<Secretaria Municipal de Saúde>>");
        URL url = this.getClass().getResource("/vacinacovid/visao/favicon.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
        setIconImage(iconeTitulo);
        construindoTela(800, 600);
        construindoBarraDeMenu();
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

}
