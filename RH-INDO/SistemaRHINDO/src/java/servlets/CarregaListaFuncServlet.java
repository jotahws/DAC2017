/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Cargo;
import beans.Departamento;
import beans.Estado;
import beans.Funcionario;
import facade.Facade;
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

/**
 *
 * @author JotaWind
 */
@WebServlet(name = "CarregaListaFuncServlet", urlPatterns = {"/CarregaListaFuncServlet"})
public class CarregaListaFuncServlet extends HttpServlet {

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
        Facade facade = new Facade();

        if ("register".equals(action)) {
            try {

                // Faz carregar UF, Cargos e Deptos do BD e faz um forward para cadastrar.jsp, para carregar os comboboxes
                List<Cargo> cargos = facade.carregaListaCargos();
                List<Departamento> deptos = facade.carregaListaDeptos();
                List<Estado> estados = facade.carregaListaUF();

                request.setAttribute("cargos", cargos);
                request.setAttribute("deptos", deptos);
                request.setAttribute("estados", estados);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/funcionarios/cadastrar.jsp");
                rd.forward(request, response);
            } catch (ClassNotFoundException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("Class not found: " + ex.getMessage());
                }
            } catch (SQLException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("Ocorreu um erro no Banco de Dados, tente novamente mais tarde: " + ex.getMessage());
                }
            }
        } else if ("listaFuncionarios".equals(action)) {
            try {
                String status = request.getParameter("status");
                List<Funcionario> funcionarios = facade.carregaListaFunc();
                request.setAttribute("funcs", funcionarios);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/funcionarios/index.jsp?status="+status);
                rd.forward(request, response);
            } catch (ClassNotFoundException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("Class not found: " + ex.getMessage());
                }
            } catch (SQLException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("Ocorreu um erro no Banco de Dados, tente novamente mais tarde: " + ex.getMessage());
                }
            } catch (NullPointerException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("NULL POINTER EXCEPTION: " + ex.getMessage());
                }
            }
        } else if ("edit".equals(action)) {
            try {
                String idFunc = request.getParameter("id");
                String error = request.getParameter("error");
                Funcionario func = facade.buscaFuncionarioPorID(Integer.parseInt(idFunc));
                List<Cargo> cargos = facade.carregaListaCargos();
                List<Departamento> deptos = facade.carregaListaDeptos();
                List<Estado> estados = facade.carregaListaUF();
                request.setAttribute("func", func);
                request.setAttribute("cargos", cargos);
                request.setAttribute("deptos", deptos);
                request.setAttribute("estados", estados);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/funcionarios/editar.jsp");
                rd.forward(request, response);
            } catch (IOException | ClassNotFoundException | NumberFormatException | SQLException | ServletException ex) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/funcionarios/editar.jsp?status=error");
                rd.forward(request, response);
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

//try (PrintWriter out = response.getWriter()) {           