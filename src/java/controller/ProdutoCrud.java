/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import model.Banco;

/**
 *
 * @author aluno
 */
public class ProdutoCrud {
    public ResultSet listarPorDep(int coddep) throws Exception{
        Banco bb;
        try{
            
            bb= new Banco();
            bb.comando = Banco.conexao.prepareStatement(
            "select codigo,descricao,preco,qtde,imagem,coddep from produto where coddep=?");
            bb.comando.setInt(1,coddep);
            bb.tabela=bb.comando.executeQuery();
            Banco.conexao.close();
            return(bb.tabela);
            
        }catch(Exception ex){
            throw new Exception("Erro ao listar produto por departamento"+ex.getMessage());
        }
       
    }
}
