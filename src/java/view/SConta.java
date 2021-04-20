/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ClienteCrud;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cliente;

/**
 *
 * @author Flavio
 */
@WebServlet(name = "SConta", urlPatterns = {"/SConta"})
public class SConta extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        request.getParameter("UTF-8");

        ClienteCrud objCrud;
        Cliente cliente;
        HttpSession sessao;
        String s="";
        try {

            objCrud = new ClienteCrud();
            cliente = new Cliente();
            if (request.getParameter("btnConta").equalsIgnoreCase("Cadastrar")) {

                cliente.setNome(request.getParameter("txtNomeC"));
                cliente.setLogin(request.getParameter("txtLoginC"));
                cliente.setSenha(request.getParameter("txtSenhaC"));
                objCrud.Gravar(cliente);
                s = cliente.getNome()+ " Cadastrado com Sucesso";
            }
            
            else if(request.getParameter("btnConta").equalsIgnoreCase("Logar")){
                
               cliente = objCrud.login(request.getParameter("txtLoginL"), request.getParameter("txtSenhaL"));
                
                if(objCrud.login(request.getParameter("txtLoginL"), request.getParameter("txtSenhaL")) == null){
                    s = "Login Incorreto";
                }
                else{
                    
                    
                    s = "Login Bem Sucedido";
                    sessao=request.getSession(true);
                    sessao.setAttribute("cliente", cliente);
                }
            }

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<link rel='stylesheet' type= 'text/css' href='css/style.css'>\n");
            out.println("<html>");
            out.println("<head>");
            
            out.println(s);
            out.println("</head>");
            out.println("<body>");

            out.println("</body>");
            out.println("</html>");

        } catch (Exception ex) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Página de Erro: SConta</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Erro SConta: " + ex.getMessage() + "</h1>");
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
