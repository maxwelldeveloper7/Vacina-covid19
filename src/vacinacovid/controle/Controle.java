package vacinacovid.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import vacinacovid.daos.AcsDAO;
import vacinacovid.modelo.AcsBean;
import vacinacovid.modelo.VacinanteBean;
import vacinacovid.visao.FrmAlteraVacinante;
import vacinacovid.visao.FrmAlteraVacinanteIrrestrito;
import vacinacovid.visao.FrmCadVacinante;
import vacinacovid.visao.FrmCadVacinanteIrrestrito;
import vacinacovid.visao.FrmSelecionaAcs;
import vacinacovid.visao.FrmSelecionaUbs;
import vacinacovid.visao.Principal;
import vacinacovid.visao.PrincipalGestor;

/**
 *
 * @author maxwell
 */
public class Controle {

    private static Controle instanceSingleton = null;
    public static Principal principal = null;
    public static PrincipalGestor principalGestor = null;
    private static FrmSelecionaUbs selecionaUbs = null;
    public static FrmSelecionaAcs selecionaAcs = null;
    public static List<AcsBean> agentes = new ArrayList();
    public static AcsBean agenteSelecionada = new AcsBean();
    public static String esfSelecionado = null;
    public static List<AcsBean> selecionados = new ArrayList();
    public static FrmCadVacinante cadVacinante;
    public static FrmAlteraVacinante alteraVacinante;
    public static FrmCadVacinanteIrrestrito cadVacinanteIrrestrito;
    public static FrmAlteraVacinanteIrrestrito alteraVacinanteIrrestrito;
    private static VacinanteBean vacinante = new VacinanteBean();

    public static VacinanteBean getVacinante() {
        return vacinante;
    }

    public static void setVacinante(VacinanteBean vacinante) {
        Controle.vacinante = new VacinanteBean();
        Controle.vacinante = vacinante;
    }

    private Controle() {

    }

    public static Controle getInstance() {
        if (instanceSingleton == null) {
            instanceSingleton = new Controle();
        }
        return instanceSingleton;
    }

    public static void abrirTelaPrincipal(String esf) {
        if (principal == null) {
            principal = new Principal(esf);
        }
        principal.setVisible(true);
    }
    
    public static void abrirTelaPrincipalGestor() {
        if (principalGestor  == null) {
            principalGestor = new PrincipalGestor();
        }
        principalGestor.setVisible(true);
    }

    public static void abrirTelaSelecionaAcs() {
        if (selecionaAcs == null) {
            selecionaAcs = new FrmSelecionaAcs(null, false, "Selecione o Agente", 350, 160, JRootPane.WARNING_DIALOG);
        }
        selecionaAcs.setVisible(true);
    }

    public static void abrirTelaCadVacinante() {
        if (cadVacinante == null) {
            cadVacinante = new FrmCadVacinante(principal, true, "Cadastro", 590, 340, JRootPane.PLAIN_DIALOG);
        }
        cadVacinante.setVisible(true);
    }

    public static void abrirTelaAlteraVacinante() {

        alteraVacinante = new FrmAlteraVacinante(principal, true, "Atualização de Cadastro", 590, 340, JRootPane.QUESTION_DIALOG);
        alteraVacinante.setVisible(true);
    }
    
    public static void abrirTelaCadVacinanteIrrestrito() {
        if (cadVacinanteIrrestrito == null) {
            cadVacinanteIrrestrito = new FrmCadVacinanteIrrestrito(principal, true, "Cadastro", 590, 370, JRootPane.PLAIN_DIALOG);
        }
        cadVacinanteIrrestrito.setVisible(true);
    }

    public static void abrirTelaAlteraVacinanteIrrestrito() {

        alteraVacinanteIrrestrito = new FrmAlteraVacinanteIrrestrito(principal, true, "Atualização de Cadastro", 590, 370, JRootPane.QUESTION_DIALOG);
        alteraVacinanteIrrestrito.setVisible(true);
    }

    public static void inicializar() {
        MetalLookAndFeel laf = new MetalLookAndFeel();
        laf.setCurrentTheme(new OceanTheme());

        try {
            UIManager.setLookAndFeel(laf);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Não foi possível estabelecer"
                    + "um look and fell:" + "\n" + ex.getMessage());
            System.exit(0);
        }
        AcsDAO dao = new AcsDAO();
        agentes = dao.select();

        if (selecionaUbs == null) {
            selecionaUbs = new FrmSelecionaUbs(null, false, "Selecione sua Área", 350, 160, JRootPane.QUESTION_DIALOG);
        }
        selecionaUbs.setVisible(true);
        //abrirTelaPrincipal("Todos");
    }

    public static void tratarErroSQL(String mensagem) {
        System.out.println("IMPLEMENTAR TRATAMENTO DE ERRO - personalizar tela de mensagem para o usuário");
    }    
    
}
