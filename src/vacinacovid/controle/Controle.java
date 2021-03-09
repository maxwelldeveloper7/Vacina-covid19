package vacinacovid.controle;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import vacinacovid.visao.FrmSelecionaUbs;
import vacinacovid.visao.Principal;

/**
 *
 * @author maxwell
 */
public class Controle {

    private static Controle instanceSingleton = null;
    public static Principal principal = null;
    public static FrmSelecionaUbs selecionaUbs = null;

    private Controle() {

    }

    public static Controle getInstance() {
        if (instanceSingleton == null) {
            instanceSingleton = new Controle();
        }
        return instanceSingleton;
    }
    
    public static void abrirTelaPrincipal(int modoAcesso, String esf){
        if(principal == null){
            principal = new Principal(modoAcesso, esf);
        }
        principal.setVisible(true);
    }

    public static void inicializar() {
        MetalLookAndFeel laf = new MetalLookAndFeel();
        laf.setCurrentTheme(new OceanTheme());
        
        try {
            UIManager.setLookAndFeel(laf);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Não foi possível estabelecer"
                    + "um look and fell:"+"\n"+ex.getMessage());
            System.exit(0);
        }
        
        if(selecionaUbs == null){
            selecionaUbs = new FrmSelecionaUbs(null, false, "Selecione sua Área", 350, 160, JRootPane.QUESTION_DIALOG);            
        }
        selecionaUbs.setVisible(true);
        
        /*if(principal == null){
            principal = new Principal();
        }
        principal.setVisible(true);*/
    }
    
    public static void tratarErroSQL(String mensagem){
        System.out.println("IMPLEMENTAR TRATAMENTO DE ERRO - personalizar tela de mensagem para o usuário");
    }
}
