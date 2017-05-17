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
                String nome = rs.getString("nome");
                double salario = rs.getDouble("salario");
                String requisitos = rs.getString("requisitos");
                int horasMinimas = rs.getInt("horasMinimas");
                double descontoImposto = rs.getDouble("descontoImposto");
                Cargo cargo = new Cargo(nome, salario, requisitos, horasMinimas, descontoImposto);
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

    public Cargo buscaCargoPorNome(String cargo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int buscaIdCargo(Cargo cargo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Cargo buscaCargoPorID(int idCargo) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(selectCargoID);
            stmt.setInt(1, idCargo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                //nomeV, salarioD, requisitosV, horasminimasI, descontoImpostoD
                String nome = rs.getString("nome");
                Double salario = rs.getDouble("salario");
                String requisitos = rs.getString("requisitos");
                int horasMinimas = rs.getInt("horasminimas");
                Double descontoImposto = rs.getDouble("descontoImposto");
                Cargo cargo = new Cargo(nome, salario, requisitos, horasMinimas, descontoImposto);
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

}
