/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ProdutoCrud;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aluno
 */
@WebServlet(name = "SProduto", urlPatterns = {"/SProduto"})
public class SProduto extends HttpServlet {

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
        ProdutoCrud objCrud;
        String s ;
        String coddep;
        ResultSet tabela;
    
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        int coluna = 0;

        try {
            
            objCrud = new ProdutoCrud();
            coddep = request.getParameter("dep");
            
            
            
            
            tabela = objCrud.listarPorDep(Integer.parseInt(coddep));
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<link rel='stylesheet' type= 'text/css' href='css/style.css'>\n");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SProduto</title>");
            out.println("</head>");
            out.println("<body>");
            if (tabela != null) {
                out.println(" <table border='0'> <tbody><tr>");
                while (tabela.next()) {
                    out.println("<td>"); //SComprar - vai incluir Carrinho de Compras
                    s = "<form action='SComprar' method='post'>"

                            + "<input type='text' name='txtCodigo' hidden value='" + tabela.getInt(1) + "'/>"
                            + "<input type='text' name='txtDescricao' hidden value='" + tabela.getString(2) + "'/>"
                            + "<input type='text' name='txtPreco' hidden value='" + tabela.getDouble(3) + "'/>"
                            + "<input type='text' name='txtImagem' hidden value='" + tabela.getString(5) + "'/>"
                            +"<div class='prodBox'>"
                            + " <img class='prodimg' src= 'imagem/" + tabela.getString(5) + "' height='200'/> "
                          
                            + "<h1 class='descP' >" + tabela.getString(2) + "</h1>"
                            + "<h1 class='precP'> R$" + tabela.getDouble(3) + "</h1>"
                            + "Quantidade: <input type='text' name='txtQtde' value='1' style='width: 20px' />"
                            + "<br><br>" 
                            + "<button type='submit' value='Adicionar ao Carrinho' name= 'b1' class='b1AdcP' />Adicionar ao Carrinho</button>"
                            +"</div>"
                            + "</form>";

                    out.println(s);
                    out.println("</td>");
                    coluna++;
                    if (coluna == 3) {
                        out.println("</tr><tr>");
                        coluna = 0;
                    }
                   out.println("</td>");
                }
                out.println("</tr> </tbody></table>");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Erro </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("Erro SProduto: " + ex.getMessage());
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
