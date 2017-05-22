/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Cargo;
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
public class CargoDAO {

    private final String insertCargo = "INSERT INTO cargo (nome, salario, requisitos, horasminimas, descontoimposto) VALUES (?,?,?,?,?)";
    private final String listCargos = "SELECT * FROM cargo";
    private final String selectCargoID = "SELECT * FROM cargo WHERE id=?";
    private final String updateCargo = "UPDATE Cargo SET nome=?, salario=?, requisitos=?, horasMinimas=?, descontoImposto=? WHERE id=?;";
    private final String deleteCargo = "DELETE FROM cargo WHERE id=?;";
    
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public void inserirCargo(Cargo cargo) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(insertCargo, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cargo.getNome());
            stmt.setDouble(2, cargo.getSalario());
            stmt.setString(3, cargo.getRequisitos());
            stmt.setInt(4, cargo.getCargaMinima());
            stmt.setDouble(5, cargo.getDescImposto());
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

    public List<Cargo> listaCargos() throws ClassNotFoundException, SQLException {

        try {
            List<Cargo> lista = new ArrayList();
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(listCargos);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double salario = rs.getDouble("salario");
                String requisitos = rs.getString("requisitos");
                int horasMinimas = rs.getInt("horasMinimas");
                double descontoImposto = rs.getDouble("descontoImposto");
                Cargo cargo = new Cargo(id, nome, salario, requisitos, horasMinimas, descontoImposto);
                lista.add(cargo);
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

    public Cargo buscaCargoPorID(int idCargo) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(selectCargoID);
            stmt.setInt(1, idCargo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                //nomeV, salarioD, requisitosV, horasminimasI, descontoImpostoD
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Double salario = rs.getDouble("salario");
                String requisitos = rs.getString("requisitos");
                int horasMinimas = rs.getInt("horasminimas");
                Double descontoImposto = rs.getDouble("descontoImposto");
                Cargo cargo = new Cargo(id, nome, salario, requisitos, horasMinimas, descontoImposto);
                return cargo;
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

    public void editarCargo(Cargo cargo) throws SQLException, ClassNotFoundException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(updateCargo, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cargo.getNome());
            stmt.setDouble(2, cargo.getSalario());
            stmt.setString(3, cargo.getRequisitos());
            stmt.setInt(4, cargo.getCargaMinima());
            stmt.setDouble(5, cargo.getDescImposto());
            stmt.setInt(6, cargo.getId());
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

    public void excluirCargo(int cargo) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(deleteCargo, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, cargo);
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

}
