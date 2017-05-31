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
import java.io.UnsupportedEncodingException;
import static java.lang.System.out;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Entity;

/**
 *
 * @author MauMau
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

        String action = request.getParameter("action");
        if ("login".equals(action)) {

            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            try {
                Funcionario f = new Funcionario();
                f.setEmail(login);
                f.setSenha(f.criptografa(senha));
                Client client = ClientBuilder.newClient();

                Funcionario retorno = client.target("http://localhost:8084/SistemaRHINDO/webresources/login")
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.json(f), Funcionario.class);

                HttpSession session = request.getSession();
                session.setAttribute("funcionarioLogado", retorno);

                if ("GERENTE-RH".equals(retorno.getPerfil())) {
                    out.println("<h1>oi</h1>");
                    response.sendRedirect("atividades/indexG.jsp");
                } else if (("GERENTE".equals(retorno.getPerfil())) || ("FUNCIONARIO".equals(retorno.getPerfil()))) {
                    response.sendRedirect("atividades/listaAtividades.jsp");
                } else {
                    response.sendRedirect("index.jsp");
                }

            } catch (NullPointerException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("<h1>DADOS INVÁLIDOS</h1>");
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
