/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vacinacovid.testes;

import vacinacovid.Data;

/**
 *
 * @author maxwell
 */
public class TestaData {
    public static void main(String args[]){
        Data data = new Data();
        data.setData("06/03/2021");
        
        System.out.println(data.getDataSql());
    }
}
