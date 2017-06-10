/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Departamento;
import beans.Funcionario;
import conecao.ConnectionFactory;
import facede.Facade;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "RelatorioServlet", urlPatterns = {"/RelatorioServlet"})
public class RelatorioServlet extends HttpServlet {

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
        Connection con = null;
        String action = request.getParameter("action");
        Facade facade = new Facade();
        String status = "";
        if ("relConsolidado".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            //Aqui vai verificar se funcionario iniciou uma atividade
            try {
                boolean result = facade.verificaFunc(id);
                //Aqui vai buscar funcionario por id no rhindo, inserindo tabela temporaria
                if (result) {
                    Funcionario func = facade.getfuncionarioID(id);
                    facade.insereFuncTemp(func);
                    //gera relatorio
                    try {
                        con = new ConnectionFactory().getConnection();
                        String jasper = request.getContextPath()
                                + "/RelatorioPorFunc.jasper";
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
                        Logger.getLogger(RelatorioServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        con.close();

                    }

                } else {
                    status = "erro";
                    response.sendRedirect("ListaFuncionarioServlet?action=ListaFuncionarios&status=" + status);
                }

            } catch (ClassNotFoundException ex) {
                status = "Erro no classNotFound";
            } catch (SQLException ex) {
                status = "Erro ao consultar o bando de dados";
            }
            try {
                facade.removeFuncTemp();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(RelatorioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("relDepartamento".equals(action)) {

            int id = Integer.parseInt(request.getParameter("id"));
            String data = (request.getParameter("data"));

            try {
                boolean result = facade.verificaDepart(id);
                if (result) {
                    Departamento depart = facade.getDeptoPorID(id);
                    facade.insereDepartTemp(depart);
                    //Gera Relatorio
                    try {
                        con = new ConnectionFactory().getConnection();
                        String jasper = request.getContextPath()
                                + "/RelatorioPorDepart.jasper";
                        // Host onde o servlet esta executando
                        String host = "http://" + request.getServerName()
                                + ":" + request.getServerPort();
                        // URL para acesso ao relatório
                        URL jasperURL = new URL(host + jasper);
                        // Parâmetros do relatório
                        HashMap params = new HashMap();
                        params.put("data", data);
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
                        Logger.getLogger(RelatorioServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        con.close();

                    }

                } else {
                    status = "erroDepart";
                    response.sendRedirect("ListaFuncionarioServlet?action=ListaFuncionarios&status=" + status);
                }

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(RelatorioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Removendo dados tabela temporaria
            try {
                facade.removeDepartTemp();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(RelatorioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
