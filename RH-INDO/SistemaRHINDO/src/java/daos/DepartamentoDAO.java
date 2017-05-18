/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Departamento;
import conexao.ConnectionFactory;
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
public class DepartamentoDAO {

    private final String insertDepto = "INSERT INTO Departamento (nome, localizacao) VALUES (?,?)";
    private final String listDeptos = "SELECT * FROM Departamento";
    private final String selectDeptoID = "SELECT * FROM Departamento WHERE id=?";

    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public void inserirDepto(Departamento depto) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(insertDepto, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, depto.getNome());
            stmt.setString(2, depto.getLocalizacao());
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

    public List<Departamento> listaDepartamentos() throws ClassNotFoundException, SQLException {

        try {
            List<Departamento> lista = new ArrayList();
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(listDeptos);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String localizacao = rs.getString("localizacao");
                Departamento depto = new Departamento(id, nome, localizacao);
                lista.add(depto);
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


    public Departamento buscaDeptoPorID(int idDepto) throws ClassNotFoundException, SQLException {

        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(selectDeptoID);
            stmt.setInt(1, idDepto);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String localizacao = rs.getString("localizacao");
                Departamento depto = new Departamento(id, nome, localizacao);
                return depto;
            }
        } finally {
            try {
                stmt.close();
                con.close();
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar parâmetros: " + ex.getMessage());
            }
        }
        return null;
    }

}
