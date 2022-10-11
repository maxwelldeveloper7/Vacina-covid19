/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vacinacovid.testes;

import vacinacovid.daos.ConexaoPostgres;

/**
 *
 * @author maxwell
 */
public class TesteDeConexao {
    public static void main(String[] args){
        ConexaoPostgres.conectar();
        ConexaoPostgres.desconectar();
    }
}
