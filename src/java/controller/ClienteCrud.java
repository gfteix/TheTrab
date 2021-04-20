/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Banco;
import model.Cliente;

/**
 *
 * @author aluno
 */
//Gravar e Alterar no cliente
public class ClienteCrud {
    
 public Cliente login(String log, String s) throws Exception{
     Banco bb;
     Cliente obj=null;
     try{
         bb = new Banco();
         bb.comando = Banco.conexao.prepareStatement(
         "select codigo, nome, login, senha from cliente where login=? and senha=md5(?)");
         bb.comando.setString(1, log);
         bb.comando.setString(2, s);
         bb.tabela = bb.comando.executeQuery();
         
         if(bb.tabela.next()){
             obj = new Cliente();
             obj.setCodigo(bb.tabela.getInt(1));
             obj.setNome(bb.tabela.getString(2));
             obj.setLogin(bb.tabela.getString(3));
             obj.setSenha(bb.tabela.getString(4));
         }
         Banco.conexao.close();
         return(obj);
         
     }catch(Exception ex){
         throw new Exception("Erro no Login: "+ ex.getMessage());
     }
 }
 public void Gravar(Cliente obj) throws Exception{
     
            Banco bb;
           
            try
            {
            
                bb = new Banco();
                bb.comando = Banco.conexao.prepareStatement("Insert into cliente(nome,login,senha) values(?, ?, md5(?))");
                bb.comando.setString(1, obj.getNome());
                bb.comando.setString(2, obj.getLogin());
                bb.comando.setString(3, obj.getSenha());
                bb.comando.executeUpdate();     
                Banco.conexao.close();
           
            }catch(Exception ex){
                throw new Exception("Erro ao Gravar CLiente"+ ex.getMessage());
            }


 }
}
