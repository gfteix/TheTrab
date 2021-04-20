/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Banco;
import model.Item;

/**
 *
 * @author Gabriel
 */
public class ItemCrud {
     public int gravar(Item obj) throws Exception
    {
        Banco bb;
        int qtde=0;
        try
        {
            bb = new Banco();
            bb.comando = Banco.conexao.prepareStatement("insert into item(qtde,precounit,codproduto,codvenda) values(?,?,?,?)");
            bb.comando.setInt(1, obj.getQtde());
            bb.comando.setDouble(2, obj.getPrecounit());
            bb.comando.setInt(3, obj.getCodproduto());
            bb.comando.setInt(4, obj.getCodvenda());
            qtde=bb.comando.executeUpdate(); // retornar quantos foram inserido na base
            Banco.conexao.close(); 
            return(qtde);
        }
        catch(Exception ex){
            throw new Exception("Erro ao gravar item: "+ex.getMessage());
        }
    }
}
