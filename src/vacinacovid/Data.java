/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vacinacovid;

import java.sql.Date;
import static vacinacovid.Utilidades.getDigitos;

/**
 *
 * @author maxwell
 */
public class Data{
    
    private Date dataSql;
    
    public void setData(String dt){
        String result = getDigitos(dt);
        int ano = Integer.parseInt(result.substring(4, 8)) - 1900;
        int mes = Integer.parseInt(result.substring(2, 4)) - 1;
        int dia = Integer.parseInt(result.substring(0, 2));
        dataSql = new Date(ano, mes, dia);
    }   
    
    public String getData(){
        return Utilidades.formataDataSTR(dataSql);
    }

    public Date getDataSql() {
        return dataSql;
    }

    public void setDataSql(Date dataSql) {
        this.dataSql = dataSql;
    }
    
}
