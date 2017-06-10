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
import static java.lang.System.out;
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
                    
                    
                } else {
                    status = "erro";
                    response.sendRedirect("ListaFuncionarioServlet?action=ListaFuncionarios&status=" + status);
                }

            } catch (ClassNotFoundException ex) {
                status = "Erro no classNotFound";
            } catch (SQLException ex) {
                status = "Erro ao consultar o bando de dados";
            }
        
        
        
        
        
        
        } else if ("relDepartamento".equals(action)) {
            //Pegar o dia data e gerar o relatorio de todos os departamentos por dia
            //nome departamento, nome tipoatividade, descricao da atividade, funcionario que trabalhou 
            //quando TipoAtividade.idDepartameneto = bean.departamento.id and status = "ENCERRADO"

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
