/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Funcionario;
import facade.Facade;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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

        Facade facade = new Facade();
        String action = request.getParameter("action");

        if ("login".equals(action)) {

            String login = request.getParameter("login");
            String senha = request.getParameter("senha");

            try {
                Funcionario funcionario = new Funcionario();
                senha = funcionario.criptografa(senha);
                Funcionario func = facade.verificaLogin(login, senha);

                HttpSession session = request.getSession();
                session.setAttribute("funcionarioLogado", func);

                if ("GERENTE-RH".equals(func.getPerfil())) {
                    response.sendRedirect("CarregaListaFuncServlet?action=listaFuncionarios");
                } else if (("GERENTE".equals(func.getPerfil())) || ("FUNCIONARIO".equals(func.getPerfil()))) {
                    response.sendRedirect("relatoriosF.jsp");
                } else {
                    response.sendRedirect("index.jsp");
                }

            } catch (ClassNotFoundException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("ERRO NO CLASS NOT FOUND" + ex.getMessage());
                }
            } catch (SQLException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("ERRO NO SQL" + ex.getMessage());
                }
            } catch (NullPointerException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("<h1>DADOS INVÁLIDOS</h1>");
                }
            } catch (NoSuchAlgorithmException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("<h1>No such algorithm");
                }
            }
        } else if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("index.jsp");
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

//        try (PrintWriter out = response.getWriter()) {
