/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cliente;
import model.Produto;

/**
 *
 * @author aluno
 */
@WebServlet(name = "SComprar", urlPatterns = {"/SComprar"})
public class SComprar extends HttpServlet {
//SComprar vai incluir no carrinho de compras

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao;
        ArrayList<Produto> lista = null;
        Produto obj;
        response.setContentType("text/html;charset=UTF-8");
        request.getParameter("UTF-8");
        PrintWriter out = response.getWriter();
        int y = 0;
        double total = 0;
        String r = "";

        try {
            r = request.getParameter("b1");

            obj = new Produto();
            if ("Adicionar ao Carrinho".equals(r)) {
                sessao = request.getSession(true);
                lista = (ArrayList<Produto>) sessao.getAttribute("lista");
                
                 
                if (lista == null) {
                    lista = new ArrayList<>();
                }
                obj.setCodigo(request.getParameter("txtCodigo")); //txtCodigo do SProduto
                obj.setDescricao(request.getParameter("txtDescricao"));
                obj.setPreco(Double.parseDouble(request.getParameter("txtPreco")));
                obj.setQtde(request.getParameter("txtQtde"));
                obj.setImagem(request.getParameter("txtImagem"));
                lista.add(obj);
                sessao.setAttribute("lista", lista);

            } else {
                sessao = request.getSession(true);
                lista = (ArrayList<Produto>) sessao.getAttribute("lista");
                if (lista == null) {
                    lista = new ArrayList<>();
                }

                sessao.setAttribute("lista", lista);
            }

            out.println("<!DOCTYPE html>");
            out.println("<link rel='stylesheet' type= 'text/css' href='css/style.css'>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SComprar(Carrinho)</title>");
            out.println("</head>");
            out.println("<body>");
            
            if (!lista.isEmpty()) {
                out.println("<div class='carrinho'>");;
                out.println(" <table class='tableCarrinho' cellspacing=0 cellpadding=1>  <thead> <tr class='trC'>");
                out.println("<h2 style='margin-top: 0; margin-bottom: 0'>Carrinho de Compras</h2>");
                out.println("<th class='ThCarrinho'> </th>");
                out.println("<th class='ThCarrinho'  '> </th>");
                out.println("<th class='ThCarrinho'>Quantidade: </th>");
                out.println("<th class='ThCarrinho'>Preço: </th>");
                out.println("<th class='ThCarrinho'> </th>");
                out.println("</tr></thead> ");

                if (lista != null) {
                    for (int i = 0; i < lista.size(); i++) {
                        obj = lista.get(i);

                        out.println(" <tbody class='tbodyCarrinho'> "
                                + "<tr class='trCarrinho'>  "
                             
                                + "<input type='text' name='txtCodigo' hidden value='" + obj.getCodigo() + "'/>");
    
                            
                        out.println("<td class='tdCarrinho5'><img src='imagem/" + obj.getImagem() + "' height='90px' /></td>");
                        out.println("<td class='tdCarrinho1'><strong>" + obj.getDescricao() + "</strong></td>");
                        out.println("<td class='tdCarrinho2' style='text-align: center'>" + obj.getQtde() + "</td>");
                        out.println("<td class='tdCarrinho3'><strong>R$ " + obj.getPreco() + "</strong></td>");
                        out.println("<td class='tdCarrinho4'> "
                                + "<form action='SRemoverP' method='post'> "
                                + "<input type='submit' value='Excluir' name='btnRemover' class='btnRemover'>"
                                + "<input type='text' name='Index' hidden value='"+i+"'/>"
                                        + "</form>"
                                        + "</td>");
                    
                      
                        out.println("</tr>  "
                                + "</tbody> ");

                        total += (obj.getQtde() * obj.getPreco());
                        y++;
                    }

                    out.println("</table>");

                }

                out.println("<div class='parte2'>");
                if (y != 1) {
                    out.println("<p><strong> Subtotal(" + y + " Itens): ");
                } else {
                    out.println("<p><strong> Subtotal(" + y + " Item):");
                }
                out.println("<a class='Atotal'>" + "R$" + String.format("%.2f", total) + "</a></strong></p>");
                out.println("<br>");
                        out.println("<form action='SFinalizar' method='post'><p>"
                        + "<input type='submit' value='Finalizar' name='f' class='buttonF' /> </p>"
                                + " <input type='text' name='txtTotal' hidden value='"+total+"'/> </form>");
                       

                out.println("</div>");
                out.println("</div>");
            } else {
                out.println("<h1 style='text-align: center'>Carrinho Vazio</h1>");
                out.println("<p style='text-align: center; font-size: 32px'>Seu carrinho de compras está vazio, mas você pode continuar suas compras na página inicial.</p>");
            }
            out.println("</body>");
            out.println("</html>");

        } catch (Exception ex) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title> Página de Erro </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Erro SComprar:  " + ex.getMessage() + "</h1>");
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
