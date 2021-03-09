package vacinacovid.visao;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

/**
 *
 * @author maxwell
 */
public abstract class GenericJDialog extends JDialog implements ActionListener{
    
    public JPanel pnFundo;
    public JPanel pnBotoes;
    public JPanel pnDados;
    
    
    public GenericJDialog(JFrame parent, boolean modal, String titulo,
            int largura, int altura, int decorationStyle){
        setTitle(titulo);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - largura)/2, (screenSize.height - altura)/2,
                largura, altura);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(decorationStyle);        
        setResizable(false);
    }
}
