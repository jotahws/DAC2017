/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Departamento;
import conexao.ConnectionFactory;
import facade.Facade;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author JotaWind
 */
@WebServlet(name = "RelatoriosServlet", urlPatterns = {"/RelatoriosServlet"})
public class RelatoriosServlet extends HttpServlet {

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

        Connection con = null;

        if ("relFuncionarios".equals(action)) {

            try {
                con = new ConnectionFactory().getConnection();
                String jasper = request.getContextPath()
                        + "/RelatorioTodosFunc.jasper";
                // Host onde o servlet esta executando
                String host = "http://" + request.getServerName()
                        + ":" + request.getServerPort();
                // URL para acesso ao relatório
                URL jasperURL = new URL(host + jasper);
                // Parâmetros do relatório
                HashMap params = new HashMap();
                // Geração do relatório
                byte[] bytes = JasperRunManager.runReportToPdf(
                        jasperURL.openStream(), params, con);

                if (bytes != null) {
                    // A página será mostrada em PDF
                    response.setContentType("application/pdf");
                    // Envia o PDF para o Cliente
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } catch (ClassNotFoundException ex) {
                System.out.println("Erro ao conectar banco: " + ex.getMessage());
            } catch (JRException ex) {
                Logger.getLogger(RelatoriosServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }

        } else if ("relMes".equals(action)) {
            Facade facade = new Facade();
            try {
                String mes = request.getParameter("mes");
                List<Departamento> deptos = facade.getHorasTrabalhadasPorMes(mes);
                facade.insereHorasDeptoTemp(deptos);
                con = new ConnectionFactory().getConnection();
                String jasper = request.getContextPath()
                        + "/RelatorioHorasPorDepto.jasper";
                // Host onde o servlet esta executando
                String host = "http://" + request.getServerName()
                        + ":" + request.getServerPort();
                // URL para acesso ao relatório
                URL jasperURL = new URL(host + jasper);
                // Parâmetros do relatório
                HashMap params = new HashMap();
                // Geração do relatório
                byte[] bytes = JasperRunManager.runReportToPdf(
                        jasperURL.openStream(), params, con);

                if (bytes != null) {
                    // A página será mostrada em PDF
                    response.setContentType("application/pdf");
                    // Envia o PDF para o Cliente
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } catch (ClassNotFoundException ex) {
                System.out.println("Erro ao conectar banco: " + ex.getMessage());
            } catch (JRException ex) {
                Logger.getLogger(RelatoriosServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(RelatoriosServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
            try{
                facade.deleteHorasDeptoTemp();   
            }catch(Exception ex){
                
            }
        } else if ("RelatoriosGerente".equals(action)) {
            try {
                Facade facade = new Facade();
                List<Departamento> deptos = facade.carregaListaDeptos();
                request.setAttribute("deptos", deptos);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/relatorios.jsp");
                rd.forward(request, response);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(RelatoriosServlet.class.getName()).log(Level.SEVERE, null, ex);
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
