package vacinacovid.modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import vacinacovid.Utilidades;
import vacinacovid.controle.Controle;

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
    private AcsBean agente = new AcsBean();
    private LaboratorioBean laboratorio = new LaboratorioBean();
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
        this.cpf = Utilidades.getDigitos(cpf);

        if (this.cpf.length() == 0) {
            this.cpf = null;
        }
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

    public AcsBean setAgente(String agente) {
        for (AcsBean acs : Controle.agentes) {
            if (agente.trim().equals("")) {
                UbsBean desconhecida = new UbsBean(0, "");
                AcsBean desconhecido = new AcsBean(0, "", desconhecida);
                this.agente = desconhecido;
            } else {

                if (acs.getNome().equals(agente.toUpperCase())) {
                    this.agente = acs;
                    break;
                }
            }
        }
        return this.agente;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getDtNascimentoStr() {
        return Utilidades.formataDataSTR(dtNascimento);
    }

    public void setDtNascimentoStr(String dtNascimento) {
        this.dtNascimento = Utilidades.formataDataSQL(dtNascimento);
    }

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = Utilidades.getDigitos(cns);

        this.cns = Utilidades.getDigitos(cns);

        if (this.cns.length() == 0) {
            this.cns = null;
        }
    }

    public Integer getStatus() {
        return status;
    }

    public String status() {
        if (this.status == 1) {
            return "Vacinado";
        }
        if (this.status == 2) {
            return "Recusou";
        }
        return "Não vacinado";
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setStatus(String status) {
        switch (status) {
            case "Não vacinado":
                this.status = 0;
                break;
            case "Vacinado":
                this.status = 1;
                break;
            default:
                this.status = 2;
        }
    }

    public Date getPrimeiraDose() {
        return primeiraDose;
    }

    public String primeiraDose() {
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

    public LaboratorioBean getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(LaboratorioBean laboratorio) {
        this.laboratorio = laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        List<LaboratorioBean> laboratorios = new ArrayList();
        LaboratorioBean nenhum = new LaboratorioBean(0, "Nenhum", "Nenhum");
        LaboratorioBean astrazeneca = new LaboratorioBean(1, "Fiocruz", "Astrazeneca");
        LaboratorioBean coronavac = new LaboratorioBean(2, "Butantan", "Coronavac");
        LaboratorioBean pfizer = new LaboratorioBean(3, "Pfizer", "Pfizer");

        laboratorios.add(nenhum);
        laboratorios.add(astrazeneca);
        laboratorios.add(coronavac);
        laboratorios.add(pfizer);

        for (LaboratorioBean l : laboratorios) {
            if (l.getVacina().equals(laboratorio)) {
                this.laboratorio = l;
            }
        }
    }

    public Integer getIdade() {
        return idade;
    }
    
    public String getIdadeAnos(){
        if(idade == 0){
            return "Sem informação";
        }else{
            return idade + " anos.";
        }
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "VacinanteBean{" + "id=" + id + ", nome=" + nome + ", dtNascimento=" + dtNascimento + ", idade=" + idade + ", endereco=" + endereco + ", cpf=" + cpf + ", cns=" + cns + ", nomeMae=" + nomeMae + ", agente=" + agente.getNomeIniciasMaiusculas() + ", laboratorio=" + laboratorio.getVacina() + ", status=" + status + ", primeiraDose=" + primeiraDose + ", segundaDose=" + segundaDose + '}';
    }
}
