/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Cargo;
import beans.Cidade;
import beans.Departamento;
import beans.Endereco;
import beans.Estado;
import beans.Funcionario;
import facade.Facade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "FuncionarioServlet", urlPatterns = {"/FuncionarioServlet"})
public class FuncionarioServlet extends HttpServlet {

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
                //Cadastrar
                String nome = request.getParameter("nome");
                String cpf = request.getParameter("cpf");
                String rg = request.getParameter("rg");
                String senha = request.getParameter("senha");                
                String celular = request.getParameter("celular");
                String email = request.getParameter("email");
                String depto = request.getParameter("depto");
                String cargo = request.getParameter("cargo");
                String perfilFunc = request.getParameter("perfil");
                String cep = request.getParameter("cep");
                String rua = request.getParameter("rua");
                String numero = request.getParameter("numero");
                String bairro = request.getParameter("bairro");
                String cidade = request.getParameter("cidade");
                String uf = request.getParameter("uf");
                //Criando um endereco com cidade e estado
                Estado estado = facade.buscaEstado(uf);
                Cidade objCidade = new Cidade();
                objCidade.setNome(cidade);
                objCidade.setEstado(estado);
                Endereco endereco = new Endereco();
                endereco.setCep(cep);
                endereco.setNumero(Integer.parseInt(numero));
                endereco.setRua(rua);
                endereco.setBairro(bairro);
                endereco.setCidade(objCidade);
                //criando cargo e departamento
                Cargo objCargo = facade.buscaCargoPorID(Integer.parseInt(cargo));
                Departamento departamento = facade.buscaDeptoPorID(Integer.parseInt(depto));
                //criando funcionario
                Funcionario funcionario = new Funcionario(nome, rg, cpf, celular, email, endereco, objCargo, departamento, perfilFunc, senha);
                facade.insereFuncionario(funcionario);

            } catch (ClassNotFoundException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("classnotfound EXc" + ex.getMessage());
                }
            } catch (NumberFormatException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("Number format exc" + ex.getMessage());
                }
            } catch (SQLException ex) {
                try (PrintWriter out = response.getWriter()) {
                    out.println("Estamos com problemas no Banco de Dados, tente novamente mais tarde" + ex.getMessage());
                }
            }

            response.sendRedirect("CarregaListaFuncServlet?action=register");

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
