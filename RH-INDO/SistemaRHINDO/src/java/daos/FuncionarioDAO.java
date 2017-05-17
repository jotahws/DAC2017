/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Cargo;
import beans.Departamento;
import beans.Endereco;
import beans.Funcionario;
import conexao.ConnectionFactory;
import facade.Facade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JotaWind
 */
public class FuncionarioDAO {

    private final String insertFunc = "insert into Funcionario (idCargo, idDepartamento, idEndereco, nome, cpf, rg, email, celular, perfil) values(?,?,?,?,?,?,?,?,?)";
    private final String listFunc = "SELECT * FROM Funcionario";

    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private Facade facade;

    public void inserirFuncionario(Funcionario funcionario) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(insertFunc, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, facade.getIdCargo(funcionario.getCargo()));
            stmt.setInt(2, facade.getIdDepto(funcionario.getDepartamento()));
            stmt.setInt(3, facade.getIdEndereco(funcionario.getEndereco()));
            stmt.setString(4, funcionario.getNome());
            stmt.setString(5, funcionario.getCpf());
            stmt.setString(6, funcionario.getRg());
            stmt.setString(7, funcionario.getEmail());
            stmt.setString(8, funcionario.getCelular());
            stmt.setString(9, funcionario.getPerfil());
            stmt.executeUpdate();
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar parâmetros: " + ex.getMessage());
            }
        }
    }

    public List<Funcionario> listaFuncionarios() throws NullPointerException, ClassNotFoundException, SQLException {

        try {
            List<Funcionario> lista = new ArrayList();
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(listFunc);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idCargo = rs.getInt("idcargo");
                int idDepto = rs.getInt("iddepartamento");
                int idEndereco = rs.getInt("idendereco");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String rg = rs.getString("rg");
                String email = rs.getString("email");
                String celular = rs.getString("celular");
                String perfil = rs.getString("perfil");
                Endereco endereco = facade.buscaEnderecoPorID(idEndereco);
                Departamento depto = facade.buscaDeptoPorID(idDepto);
                Cargo cargo = facade.buscaCargoPorID(idCargo);
                Funcionario func = new Funcionario(nome, rg, cpf, celular, email, endereco, cargo, depto, perfil);
                lista.add(func);
            }
            return lista;
        } finally {
            try {
                con.close();
                stmt.close();
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar parâmetros: " + ex.getMessage());
            }
        }
    }

}
