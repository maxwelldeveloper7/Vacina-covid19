/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vacinacovid.modelo;

/**
 *
 * @author maxwell
 */
public class LaboratorioBean {
    private Integer id;
    private String nome;
    private String vacina;

    public LaboratorioBean(){
        
    }
    
    public LaboratorioBean(Integer id, String nome, String vacina){
        this.id = id;
        this.nome = nome;
        this.vacina = vacina;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVacina() {
        return vacina;
    }

    public void setVacina(String vacina) {
        this.vacina = vacina;
    }

    @Override
    public String toString() {
        return "LaboratorioBean{" + "id=" + id + ", nome=" + nome + ", vacina=" + vacina + '}';
    }    
    
}
