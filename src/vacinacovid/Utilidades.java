/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacinacovid;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import sun.awt.X11.XConstants;

/**
 *
 * @author maxwell
 */
public class Utilidades {

    private static Utilidades instanciaSingleton = null;

    private Utilidades() {
    }

    public static Utilidades getInstancia() {

        if (instanciaSingleton == null) {
            instanciaSingleton = new Utilidades();
        }
        return instanciaSingleton;
    }

    public static Date formataDataSQL(String str) {
        Date dataSql = null;
        String result = getDigitos(str);

        if (!result.equals("")) {
            int ano = Integer.parseInt(result.substring(4, 8)) - 1900;
            int mes = Integer.parseInt(result.substring(2, 4)) - 1;
            int dia = Integer.parseInt(result.substring(0, 2));
            dataSql = new Date(ano, mes, dia);

        }
        return dataSql;
    }

    public static String formataDataSTR(Date dataSql) {
        DateFormat data = new SimpleDateFormat("dd/MM/yyyy");

        if (dataSql == null) {
            return "";
        } else {
            return data.format(dataSql);
        }

    }

    public static String formataTelefoneSQL(String str) {
        String result = getDigitos(str);

        if (result.equals("")) {
            result = "null";
        }

        return result;
    }

    public static String formataTelefoneSTR(String str) {
        String result = getDigitos(str);

        if (result.trim().equals("")) {
            result = "";
        }

        return result;
    }

    public static String formataCepSQL(String str) {
        String result = getDigitos(str);

        if (result.equals("")) {
            result = "null";
        }

        return result;
    }

    public static String formataCepSTR(String str) {
        String result = getDigitos(str);

        if (result.trim().equals("")) {
            result = "";
        }

        return result;
    }

    public static String formataHoraSQL(String str) {
        String result = getDigitos(str);
        if (!result.trim().equals("")) {
            result = result.substring(0, 2) + ":"
                    + result.substring(2, 4) + ":00";
        }
        return result;
    }

    public static String formataFlutuante(String str) {
        String result = "";

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '1') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '2') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '3') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '4') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '5') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '6') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '7') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '8') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '9') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == ',') {
                result = result + ".";
            }
        }

        if (result.equals("")) {
            result = "0";
        }

        return result;
    }

    public static BigDecimal formataMonetarioSQL(String str) {
        String result = "";

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '1') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '2') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '3') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '4') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '5') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '6') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '7') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '8') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '9') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == ',') {
                result = result + ".";
            }
        }

        if (result.equals("")) {
            result = "0.00";
        }

        return new BigDecimal(result);
    }

    public static String formataMontetarioSTR(BigDecimal decimal) {
        DecimalFormat df = new DecimalFormat("0.00");
        if (decimal == null) {
            decimal = new BigDecimal("0.00");
        }
        return df.format(decimal);
    }

    public static String formataMoeda(String str) {
        String result;
        Locale local = new Locale("Pt", "Br");
        NumberFormat numForm;
        DecimalFormat decForm = null;

        if (str.equals("")) {
            str = "0.00";
        }

        result = str;
        numForm = NumberFormat.getCurrencyInstance(local);
        decForm = (DecimalFormat) numForm;
        return decForm.format(Double.parseDouble(result));
    }

    public static String formataHoje() {
        java.util.Date hoje = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(hoje);
    }

    public static String getDigitos(String str) {
        String result = "";

        if (str == null) {
            str = " ";
        } else {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '1') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '2') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '3') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '4') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '5') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '6') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '7') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '8') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '9') {
                    result = result + str.charAt(i);
                }
            }
        }
        return result;
    }

    /**
     * Este método verifica vencimento da data de uso do sistema.
     *
     * @param dia (ex: 1 a 31)
     * @param mes (ex: 1 a 12)
     * @param ano (ex: 2009)
     * @return boolean
     */
    public static boolean VenceuDataDeLocacao(int dia, int mes, int ano) {

        Calendar dataAtual = Calendar.getInstance(); //instanciamos data atual
        dataAtual.getTime();
        Calendar dataLicenca = Calendar.getInstance(); //instanciamos data da licença

        dataLicenca.set(ano, mes - 1, dia + 1); //definimos a data de vencimento da licença

        if (dataAtual.before(dataLicenca)) {
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "A licença de uso do sistema"
                    + " expirou em " + dia + "/" + mes + "/" + ano + "." + "\n\n"
                    + "Adquira uma nova licença pelo suporte técnico:   "
                    + "  \n\n"
                    + "Telefone: 33 9104 5946 - 3621 1126\n"
                    + "E-mail: maxwellchaves@hotmail.com\n",
                    "Mensagem do Sistema", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }

    }

    public static String removeCaractere(String s, char c) {

        String r = "";

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                break;
            }
            if (s.charAt(i) != c) {
                r += s.charAt(i);
            }
        }
        r.trim();
        return r;
    }

    public static void realizarBackupSQL() {
        Calendar data = Calendar.getInstance();
        data.setTime(new java.util.Date());
        SimpleDateFormat formatador = new SimpleDateFormat("ddMMyyyyHHmmss");
        String nomeArquivo;
        nomeArquivo = formatador.format(data.getTime());

        try {
            ProcessBuilder pb;
            Process p;
            pb = new ProcessBuilder("pg_dump", "-h", getIpDbServer(), "-p", "5433", "-U", "postgres", "-v", "-f", getPathDirSave() + nomeArquivo + "BKP.sql", "vacina");
            pb.environment().put("PGPASSWORD", "31121992");
            pb.redirectErrorStream(true);
            p = pb.start();

            JOptionPane.showMessageDialog(null, "A cópia de segurança da base de dados foi realizada com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
     Restore de um banco local:
     psql -U usuario -d banco < banco.sql
     pg_restore -d banco banco.sql*/

    public static String getIpDbServer() throws FileNotFoundException, IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./configuracoes.properties");
        props.load(file);
        String host;

        host = props.getProperty("host");

        System.out.println(host);
        return host;
    }

    public static String getPathDirSave() throws FileNotFoundException, IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./configuracoes.properties");
        props.load(file);
        String path;

        path = props.getProperty("dirBkpSave");

        System.out.println(path);
        return path;
    }

    /**
     *
     * @param digitos dados obtidos de uma ClasseBean no formato String. Ex:
     * "25051979" data
     * @param mascara tipo de máscara. Ex: "##/##/####"
     * @return 25/05/1979
     */
    public static String mascara(String digitos, String mascara) {
        MaskFormatter mf;
        String result = "";
        try {
            mf = new MaskFormatter(mascara);
            mf.setValueContainsLiteralCharacters(false);
            result = mf.valueToString(digitos);
        } catch (ParseException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static String iniciaisMaiuscula(String nome) {

        String nomeCompleto = "";

        //converte toda a string em minúsculo
        if(nome == null){
            nome = "";
        }
        
        nome = nome.toLowerCase();

        //laço de repetição para percorrer toda cadeia de caracteres do nome
        for (int i = 0; i < nome.length(); i++) {

            //armazena o caractere para realizar verificações
            String caractere = "";

            //verifica se é o primeiro caractere
            if (i == 0) {
                //armazena o caractere na variável
                caractere += nome.charAt(i);

                //concatena o primeiro caractere transformado em maiúscolo
                nomeCompleto += caractere.toUpperCase();

                //caso contrário
            } else {

                //armazena caractere anterior ao indicando pelo ídice
                String anterior = "";

                //atribui o caractere anterior
                anterior += nome.charAt(i - 1);

                //verifica se o caractere anterior é um espaço
                if (anterior.equals(" ")) {

                    //inicializa a variável de preposicão
                    String pre = "";

                    //verifica se é o ultimo caractere para não ultrapassar a faixa da string
                    if (i < nome.length() - 3) {
                        //se não ultrapassa atribui à variável pre os 2 próximos caracteres
                        pre += nome.charAt(i);
                        pre += nome.charAt(i + 1);
                    }

                    //inicaliza variável
                    boolean abreviacao = true;

                    //verifica se é o ultimo ou primeiro caractere para não ultrapassar a faixa da string
                    if (i < nome.length() - 1 && i != 0) {
                        //verifica se é uma letra cercada por espaços
                        if (nome.charAt(i - 1) == ' ' && nome.charAt(i + 1) == ' ') {
                            abreviacao = false;
                        }
                    }

                    //armazena o caractere na variável
                    caractere += nome.charAt(i);

                    nomeCompleto += caractere.toUpperCase();

                    //caso contrário apenas concatena o caractere ao nome completo
                } else {
                    nomeCompleto += nome.charAt(i);
                }
            }
        }

        nomeCompleto = nomeCompleto.replace(" De ", " de ");
        nomeCompleto = nomeCompleto.replace(" Da ", " da ");
        nomeCompleto = nomeCompleto.replace(" Do ", " do ");
        nomeCompleto = nomeCompleto.replace(" Dos ", " dos ");
        nomeCompleto = nomeCompleto.replace(" Das ", " das ");

        return nomeCompleto;
    }

    public static Boolean cpfValido(String cpf) {
        boolean valido;

        //certifica de o parametro cpf conterá apenas digitos numéricos
        cpf = Utilidades.getDigitos(cpf);

        //considera erro CPF's formados por uma sequencia de numeros iguais
        if (cpf.equals("00000000000")
                || cpf.equals("11111111111")
                || cpf.equals("22222222222")
                || cpf.equals("33333333333")
                || cpf.equals("44444444444")
                || cpf.equals("55555555555")
                || cpf.equals("66666666666")
                || cpf.equals("77777777777")
                || cpf.equals("88888888888")
                || cpf.equals("99999999999")
                || (cpf.length() != 11)) {
            valido = false;

            if (cpf.length() < 11) {
                //JOptionPane.showMessageDialog(null, "Número de CPF inválido!\nDeve possuir 11 dígitos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                //JOptionPane.showMessageDialog(null, "Número de CPF inválido!\n"+Utilidades.mascara(cpf, "###.###.###-##"), "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {

            int primeiroDv = Character.getNumericValue(cpf.charAt(9));
            int segundoDv = Character.getNumericValue(cpf.charAt(10));

            //System.out.println("Primeira validação");
            int sequencia1 = 10;
            int resultado = 0;
            for (int i = 0; i < 9; i++) {
                resultado += Character.getNumericValue(cpf.charAt(i)) * sequencia1;
                //System.out.println(Character.getNumericValue(cpf.charAt(i))+" * "+sequencia1);

                sequencia1 = sequencia1 - 1;
            }

            //System.out.println("Dígitos: "+primeiroDv+segundoDv);
            //System.out.println("resultado: "+resultado);
            int restoV1 = (resultado * 10) % 11;
            if (restoV1 == 10) {
                restoV1 = 0;
            }

            //System.out.println("Primeira verificação: Resto = "+restoV1);
            //System.out.println("Segunda validação");
            int sequencia2 = 11;
            resultado = 0;
            for (int i = 0; i < 10; i++) {
                resultado += Character.getNumericValue(cpf.charAt(i)) * sequencia2;
                //System.out.println(Character.getNumericValue(cpf.charAt(i))+" * "+sequencia2);

                sequencia2 = sequencia2 - 1;
            }

            //System.out.println("Dígitos: "+primeiroDv+segundoDv);
            //System.out.println("resultado: "+resultado);
            int restoV2 = (resultado * 10) % 11;
            if (restoV2 == 10) {
                restoV2 = 0;
            }

            //System.out.println("Primeira verificação: Resto = "+restoV2);
            if ((restoV1 == primeiroDv) && (restoV2 == segundoDv)) {
                valido = true;
            } else {
                valido = false;
                JOptionPane.showMessageDialog(null, "Número de CPF inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }

        return valido;
    }

    public static Boolean cnsValido(String cns) {
        boolean valido;

        //certifica de o parametro cpf conterá apenas digitos numéricos
        cns = Utilidades.getDigitos(cns);

        //considera erro CPF's formados por uma sequencia de numeros iguais
        if (cns.equals("000000000000000")
                || cns.equals("111111111111111")
                || cns.equals("222222222222222")
                || cns.equals("333333333333333")
                || cns.equals("444444444444444")
                || cns.equals("555555555555555")
                || cns.equals("666666666666666")
                || cns.equals("777777777777777")
                || cns.equals("888888888888888")
                || cns.equals("999999999999999")
                || (cns.length() != 15)) {
            valido = false;

            if (cns.length() < 15) {
                //JOptionPane.showMessageDialog(null, "Número de CNS inválido!\nDeve possuir 15 dígitos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                //JOptionPane.showMessageDialog(null, "Número de CNS inválido!\n"+Utilidades.mascara(cns, "  ###  ####  ####  ####"), "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            valido = true;
        }

        return valido;
    }

    public static int calculaIdade(String data) {
        int idade = 0;

        if (Utilidades.formataDataSQL(data) == null) {
            return idade;
        } else {
            Calendar dtn = Calendar.getInstance();
            dtn.setTime(Utilidades.formataDataSQL(data));

            Calendar hoje = Calendar.getInstance();

            idade = hoje.get(Calendar.YEAR) - dtn.get(Calendar.YEAR);

            if (hoje.get(Calendar.MONTH) < dtn.get(Calendar.MONTH)) {
                idade--;
            } else {
                if (hoje.get(Calendar.MONTH) == dtn.get(Calendar.MONTH) && hoje.get(Calendar.DAY_OF_MONTH) < dtn.get(Calendar.DAY_OF_MONTH)) {
                    idade--;
                }
            }
            return idade;
        }
    }

}
