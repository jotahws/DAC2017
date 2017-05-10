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

/**
 *
 * @author JotaWind
 */
public class DepartamentoDAO {

    private final String insertDepto = "INSERT INTO Departamento (nome, localizacao) VALUES (?,?)";

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
}
