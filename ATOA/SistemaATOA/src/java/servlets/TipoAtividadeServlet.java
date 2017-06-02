/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Departamento;
import beans.TipoAtividade;
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

/**
 *
 * @author JotaWind
 */
@WebServlet(name = "TipoAtividadeServlet", urlPatterns = {"/TipoAtividadeServlet"})
public class TipoAtividadeServlet extends HttpServlet {

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
        String status = "";

        if ("register".equals(action)) {
            try {
                String nome = request.getParameter("nome");
                String idDepto = request.getParameter("departamento");

                Departamento departamento = new Departamento();
                departamento.setId(Integer.parseInt(idDepto));

                TipoAtividade tipoAtv = new TipoAtividade(nome, departamento);
                Facade facade = new Facade();
                facade.insereTipo(tipoAtv);
                status = "success";
            } catch (Exception ex) {
                status = "error";
            }
            response.sendRedirect("ListaDepartamentosServlet?action=register&status=" + status);
        } else if ("edit".equals(action)) {
            String id = request.getParameter("id");
            try {
                String nome = request.getParameter("nome");
                String idDepto = request.getParameter("departamento");
                Departamento departamento = new Departamento();
                departamento.setId(Integer.parseInt(idDepto));
                TipoAtividade tipoAtv = new TipoAtividade(nome, departamento);
                tipoAtv.setId(Integer.parseInt(id));
                Facade facade = new Facade();
                facade.alteraTipo(tipoAtv);
                status = "successEdit";
                response.sendRedirect("ListaAtividadeServlet?action=ListaAtividades&status=" + status);
            } catch (Exception ex) {
                status = "error";
                response.sendRedirect("ListaAtividadeServlet?action=edit&id=" + id + "&status=" + status);
            }
        } else if ("delete".equals(action)) {
            String id = request.getParameter("id");
            try {
                Facade facade = new Facade();
                facade.deletaTipo(Integer.parseInt(id));
                status = "successDelete";
                response.sendRedirect("ListaAtividadeServlet?action=ListaAtividades&status=" + status);
            } catch (Exception ex) {
                status = "error";
                response.sendRedirect("TipoAtividadeServlet?action=delete&id=" + id + "&status=" + status);
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
