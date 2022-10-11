package vacinacovid.modelo;

import vacinacovid.Utilidades;

/**
 *
 * @author maxwell
 */
public class AcsBean {
    private Integer id;
    private String nome;
    private UbsBean ubs;
    private String senha;

    public AcsBean() {
    }

    public AcsBean(Integer id, String nome, UbsBean ubs) {
        this.id = id;
        this.nome = nome;
        this.ubs = ubs;
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
    
    public String getNomeIniciasMaiusculas() {
        return Utilidades.iniciaisMaiuscula(nome);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UbsBean getUbs() {
        return ubs;
    }

    public void setUbs(UbsBean ubs) {
        this.ubs = ubs;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "AcsBean{" + "id=" + id + ", nome=" + nome + ", ubs=" + ubs.toString() + ", senha=" + senha + '}';
    }
}
