/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Cargo;
import facade.Facade;
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
@WebServlet(name = "CargoServlet", urlPatterns = {"/CargoServlet"})
public class CargoServlet extends HttpServlet {

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
            //Cadastrar
            String nome = request.getParameter("nome");
            String salario = request.getParameter("salario");
            String cargaMinima = request.getParameter("carga");
            String requisitos = request.getParameter("requisitos");
            String descImposto = request.getParameter("imposto");

            try {
                Cargo cargo = new Cargo(nome, Double.parseDouble(salario), requisitos, Integer.parseInt(cargaMinima), Double.parseDouble(descImposto));
                facade.insereCargo(cargo);
            } catch (ClassNotFoundException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("class not found: " + ex.getMessage());
                }
            } catch (NumberFormatException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("O formato no número escolhido é inválido");
                }
            } catch (SQLException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("Estamos com problemas no Banco de Dados, tente novamente mais tarde" + ex.getMessage());
                }
            }

            response.sendRedirect("cargos/cadastrar.jsp");

        } else if ("edit".equals(action)) {
            try {
                String idCargo = request.getParameter("idCargo");
                String nome = request.getParameter("nome");
                String imposto = request.getParameter("imposto");
                String salario = request.getParameter("salario");
                String requisitos = request.getParameter("requisitos");
                String carga = request.getParameter("carga");
                Cargo cargo = new Cargo(Integer.parseInt(idCargo), nome, Double.parseDouble(salario), requisitos, Integer.parseInt(carga), Double.parseDouble(imposto));
                facade.editarCargo(cargo);
            } catch (ClassNotFoundException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("class not found: " + ex.getMessage());
                }
            } catch (SQLException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("Estamos com problemas no Banco de Dados, tente novamente mais tarde: " + ex.getMessage());
                }
            }
            response.sendRedirect("CarregaListaCargoServlet?action=listaCargos");

        } else if ("delete".equals(action)) {
            try {
                String idCargo = request.getParameter("idCargo");                
                facade.excluirCargo(Integer.parseInt(idCargo));
            } catch (SQLException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("Estamos com problemas no Banco de Dados, tente novamente mais tarde: " + ex.getMessage());
                }
            } catch (ClassNotFoundException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("Class not Found Ex: " + ex.getMessage());
                }
            }
            response.sendRedirect("CarregaListaCargoServlet?action=listaCargos");
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
