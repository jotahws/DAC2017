/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Atividade;
import beans.Departamento;
import beans.Funcionario;
import beans.TipoAtividade;
import facede.Facade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
 * @author MauMau
 */
@WebServlet(name = "ListaAtividadeServlet", urlPatterns = {"/ListaAtividadeServlet"})
public class ListaAtividadeServlet extends HttpServlet {

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

        if ("ListaAtividades".equals(action)) {
            String statusEdit = "";
            try {
                List<TipoAtividade> tipos = facade.listaAtividades();
                request.setAttribute("tipos", tipos);
                statusEdit = request.getParameter("status");
            } catch (Exception ex) {
                status = "error";
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/atividades/indexG.jsp?status=" + statusEdit);
            rd.forward(request, response);

        } else if ("edit".equals(action)) {
            String statusEdit = "";
            String idTipo = request.getParameter("id");
            try {
                TipoAtividade tipo = facade.getTipoPorID(Integer.parseInt(idTipo));
                request.setAttribute("tipo", tipo);
                statusEdit = request.getParameter("status");
                List<Departamento> deptos = facade.listaDeptos();
                request.setAttribute("deptos", deptos);
            } catch (Exception ex) {
                status = "error";
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/atividades/editar.jsp?status=" + statusEdit);
            rd.forward(request, response);
        } else if ("QuadroAtividade".equals(action)) {
            try {
                HttpSession session = request.getSession(true);
                Funcionario funcionario = (Funcionario) session.getAttribute("funcionarioLogado");
                Atividade atividade = facade.listaAtividadeIniciada(funcionario);
                request.setAttribute("atividadeiniciada", atividade);
                List<TipoAtividade> tipos = facade.listaAtividades();
                request.setAttribute("tipos", tipos);
            } catch (ClassNotFoundException | SQLException ex) {
                status = "error";
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/atividades/listaAtividades.jsp");
            rd.forward(request, response);
        } else if ("Corrigir".equals(action)) {
            try {
                HttpSession session = request.getSession(true);
                Funcionario funcionario = (Funcionario) session.getAttribute("funcionarioLogado");
                Atividade atividade = facade.listaAtividadeIniciada(funcionario);
                request.setAttribute("atividade", atividade);
            } catch (ClassNotFoundException | SQLException ex) {
                status = "error";
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/atividades/corrigir.jsp");
            rd.forward(request, response);
        } else if ("atvMesFunc".equals(action)) {
            String data = request.getParameter("mes");
            try {
                HttpSession session = request.getSession(true);
                Funcionario funcionario = (Funcionario) session.getAttribute("funcionarioLogado");
                List<Atividade> atividades = facade.listaAtividadesPorFunc(funcionario, data);
                request.setAttribute("atividades", atividades);

            } catch (ClassNotFoundException | SQLException ex) {
                status = "error";
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/atividades/historicoAtividades.jsp?mes=" + data);
            rd.forward(request, response);
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
