/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.curso.controller.movimentoestoque;

import br.com.curso.dao.FuncionarioDAO;
import br.com.curso.dao.MovimentoEstoqueDAO;
import br.com.curso.dao.ProdutoDAO;
import br.com.curso.dao.TipoMovimentoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author maico
 */
@WebServlet(name = "MovimentoEstoqueCarregar", urlPatterns = {"/MovimentoEstoqueCarregar"})
public class MovimentoEstoqueCarregar extends HttpServlet {

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
        response.setContentType("text/html;charset=iso-8859-1");
        try{
            int idMovimento = Integer.parseInt(request.getParameter("idMovimento"));
            
            MovimentoEstoqueDAO oMovimentoEstoqueDAO = new MovimentoEstoqueDAO();
         
            request.setAttribute("movimentoestoque",oMovimentoEstoqueDAO.carregar(idMovimento));
            
            ProdutoDAO oProdutoDAO = new ProdutoDAO();
            request.setAttribute("produtos", oProdutoDAO.listar());
            
            FuncionarioDAO oFuncionarioDAO = new FuncionarioDAO();
            request.setAttribute("funcionarios", oFuncionarioDAO.listar());
            
            TipoMovimentoDAO oTipoMovimentoDAO = new TipoMovimentoDAO();
            request.setAttribute("tipomovimentos", oTipoMovimentoDAO.listar());
            
            
                    
            request.getRequestDispatcher("/cadastros/movimentoestoque/movimentoestoqueCadastrar.jsp").forward(request, response);
        }catch (Exception ex){
            System.out.println("Erro carregar veiculo" +ex.getMessage());
            ex.printStackTrace();
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
