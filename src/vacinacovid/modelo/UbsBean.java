package vacinacovid.modelo;

import vacinacovid.Utilidades;

/**
 *
 * @author maxwell
 */
public class UbsBean {
    private Integer id;
    private String nome;

    public UbsBean() {
    }

    public UbsBean(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    
    public String getNomeSemSigla() {
        return nome = nome.replaceAll("ESF", "");
    }
    
    public String getNomeIniciaisMaiusculaSemSigla() {
        nome = nome.replaceAll("ESF ", "");
        nome = Utilidades.iniciaisMaiuscula(nome);                
        if(nome.equals("Udr")){
            nome = "UDR";
        }
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "UbsBean{" + "id=" + id + ", nome=" + nome + '}';
    }   
    
}
