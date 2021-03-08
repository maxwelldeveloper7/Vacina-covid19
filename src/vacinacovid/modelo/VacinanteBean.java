package vacinacovid.modelo;

import java.sql.Date;
import vacinacovid.Utilidades;

/**
 *
 * @author maxwell
 */
public class VacinanteBean {
    private Integer id;
    private String nome;
    private Date dtNascimento;
    private Integer idade;
    private String endereco;
    private String cpf;
    private String cns;
    private String nomeMae;
    private AcsBean agente;
    private Integer status;
    private Date primeiraDose;
    private Date segundaDose;

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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public AcsBean getAgente() {
        return agente;
    }

    public void setAgente(AcsBean agente) {
        this.agente = agente;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }
    
    public String getDtNascimentoStr(){
        return Utilidades.formataDataSTR(dtNascimento);
    }
    
    public void setDtNascimentoStr(String dtNascimento){
        this.dtNascimento = Utilidades.formataDataSQL(dtNascimento);
    }

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public Integer getStatus() {
        return status;
    }
    
    public String status(){
        if(this.status == 1){
            return "Vacinado";
        }
        if(this.status == 2){
            return "Recusou";
        }
        return "Não vacinado";
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public void setStatus(String status) {
        if(status.equals("Não vacinado")){
            this.status = 0;
        }else if(status.equals("Vacinado")){
            this.status = 1;
        }else{
            this.status = 2;
        }
    }

    public Date getPrimeiraDose() {
        return primeiraDose;
    }
    
    public String primeiraDose(){
        return Utilidades.formataDataSTR(primeiraDose);
    }

    public void setPrimeiraDose(Date primeiraDose) {
        this.primeiraDose = primeiraDose;
    }
    
    public void setPrimeiraDose(String primeira) {
        this.primeiraDose = Utilidades.formataDataSQL(primeira);
    }

    public Date getSegundaDose() {
        return segundaDose;
    }
    
    public String segundaDose() {
        return Utilidades.formataDataSTR(segundaDose);
    }

    public void setSegundaDose(Date segundaDose) {
        this.segundaDose = segundaDose;
    }
    
    public void setSegundaDose(String segunda) {
        this.segundaDose = Utilidades.formataDataSQL(segunda);
    }
    
}
