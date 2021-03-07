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
    private String nomeMae;
    private AcsBean agente;
    private String nomeRespPreenchimento;
    private String cargoResponsavel;

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

    public String getNomeRespPreenchimento() {
        return nomeRespPreenchimento;
    }

    public void setNomeRespPreenchimento(String nomeRespPreenchimento) {
        this.nomeRespPreenchimento = nomeRespPreenchimento;
    }

    public String getCargoResponsavel() {
        return cargoResponsavel;
    }

    public void setCargoResponsavel(String cargoResponsavel) {
        this.cargoResponsavel = cargoResponsavel;
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
    
}
