package vacinacovid.modelo;

/**
 *
 * @author maxwell
 */
public class AcsBean {
    private Integer id;
    private String nome;
    private UbsBean ubs;
    private String senha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
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
}
