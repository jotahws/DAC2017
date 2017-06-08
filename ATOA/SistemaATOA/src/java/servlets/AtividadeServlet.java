/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Funcionario;
import facede.Facade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JotaWind
 */
@WebServlet(name = "AtividadeServlet", urlPatterns = {"/AtividadeServlet"})
public class AtividadeServlet extends HttpServlet {

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

        String action = request.getParameter("action");
        Facade facade = new Facade();
        String status = "";

        if ("IniciarAtv".equals(action)) {
            try {
                String idTipo = request.getParameter("id");
                HttpSession session = request.getSession(true);
                Funcionario funcionario = (Funcionario)session.getAttribute("funcionarioLogado");
                facade.encerraAtividade(funcionario);
                facade.iniciaAtividade(Integer.parseInt(idTipo), funcionario);
                response.sendRedirect("ListaAtividadeServlet?action=QuadroAtividade");
            } catch (ClassNotFoundException | SQLException ex) {
                status = "error";
            }
        } else if ("Encerrar".equals(action)) {
            try {
                HttpSession session = request.getSession(true);
                Funcionario funcionario = (Funcionario)session.getAttribute("funcionarioLogado");
                facade.encerraAtividade(funcionario);
                response.sendRedirect("ListaAtividadeServlet?action=QuadroAtividade");
            } catch (ClassNotFoundException | SQLException ex) {
                status = "error";
            }
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
