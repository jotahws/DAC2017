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

/**
 *
 * @author JotaWind
 */
public class CargoDAO {

    private final String insertCargo = "INSERT INTO cargo (nome, salario, requisitos, horasminimas, descontoimposto) VALUES (?,?,?,?,?)";

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

}
