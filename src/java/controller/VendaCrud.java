/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Banco;
import model.Venda;

/**
 *
 * @author Gabriel
 */
public class VendaCrud {
    public int gravar(Venda obj) throws Exception
    {
        Banco bb;
        int qtde=0;
        try
        {
            bb = new Banco();
            bb.comando = Banco.conexao.prepareStatement("insert into venda(total,datav,codcli) values(?, ?, ?)");
            bb.comando.setDouble(1, obj.getTotal());
            bb.comando.setTimestamp(2, obj.getDatav());
            bb.comando.setInt(3, obj.getCodcli());
            qtde=bb.comando.executeUpdate(); // retornar quantos foram inserido na base
            Banco.conexao.close(); 
            return(qtde);
        }
        catch(Exception ex){
            throw new Exception("Erro ao gravar venda: "+ex.getMessage());
        }
}
       public int Consultar() throws Exception
   {
       Banco bb;
       try
       {
           bb=new Banco();
           bb.comando=Banco.conexao.prepareStatement("select max(codigo) from venda");
           bb.tabela=bb.comando.executeQuery();
           bb.tabela.next();
           Banco.conexao.close();
           return(bb.tabela.getInt(1));
       }
       catch(Exception ex)
       {   
        throw new Exception ("Erro ao consultar venda: " + ex.getMessage());   
       }
   }
}
