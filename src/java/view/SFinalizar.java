/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ItemCrud;
import controller.VendaCrud;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cliente;
import model.Item;
import model.Produto;
import model.Venda;

/**
 *
 * @author Flavio
 */
@WebServlet(name = "SFinalizar", urlPatterns = {"/SFinalizar"})
public class SFinalizar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession sessao;
        ArrayList<Produto> lista=null;
        
        Venda objV;
        VendaCrud objVcrud;
        ItemCrud objIcrud;
        Item objI;
        Produto objP;
        Cliente cliente;
        double total;
        try {
            sessao=request.getSession(true);
            lista = (ArrayList<Produto>) sessao.getAttribute("lista");
            cliente = (Cliente) sessao.getAttribute("cliente");
            
            objV= new Venda();
            objI = new Item();
            //objC= new Cliente();
            objVcrud = new VendaCrud();
            objIcrud = new ItemCrud();
            Timestamp data;
            int x=0;
            total=Double.parseDouble(request.getParameter("txtTotal"));
           
            
           data = new Timestamp(System.currentTimeMillis()); 
           
            
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
            if(sessao.getAttribute("cliente")==null){
                out.println("<br><br><br><div align='center'><a>Para finalizar a compra é necessário fazer </a><a href=\"Conta.html\" target=\"ifPrincipal\">LOGIN</a></div><br><br>");
                out.println("<div align='center'><form action=\"SComprar\" method=\"post\"><button class='b1AdcP' type='submit' name='b1'>Voltar para o Carrinho</button></form></div>");
            }
            else{
                
                if(!(lista.isEmpty())){
                    out.println("<title>Finalizar</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    
                    
                    lista = (ArrayList<Produto>)sessao.getAttribute("lista");
           
                     objV.setCodcli(cliente.getCodigo());
                     objV.setTotal(total);
                     objV.setDatav(data);
                     objVcrud.gravar(objV);
                   
                    for(int i=0;i<lista.size();i++){
                       
                        objP=lista.get(i);
                        objI.setCodproduto(objP.getCodigo());
                        objI.setPrecounit(objP.getPreco());
                        objI.setQtde(objP.getQtde());
                        objI.setCodvenda(objVcrud.Consultar());
                        x = objIcrud.gravar(objI);
                    }
                    
                    
                    
                    out.println("<div align='center'><h1>Compra finalizada com sucesso!</h1><br><a>Obrigado por comprar conosco,"+cliente.getNome()+".</a></div>");
                    sessao.setAttribute("lista", null);
                    out.println("</body>");
                    out.println("</html>");
                }
                else
                     out.println("<div align='center'><h1>Carrinho vazio!</h1><br><a>Por favor adicione algum item para finalizar a compra.</a></div>");
            }
        }
        catch(Exception ex){
            out.println("<!DOCTYPE html>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/TCSS.css\">");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SProduto</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Erro: "+ex.getMessage()+"</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
