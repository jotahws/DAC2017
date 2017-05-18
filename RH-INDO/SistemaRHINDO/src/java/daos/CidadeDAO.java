/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Cidade;
import beans.Estado;
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
public class CidadeDAO {

    private final String selectCidadeID = "SELECT * FORM Cidade WHERE id=?";
    private final String insertCidade = "INSERT INTO cidade (idestado, nome) VALUES (?,?)";

    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public Cidade buscaCidadePorID(int idCidade) throws ClassNotFoundException, SQLException {

        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(selectCidadeID);
            stmt.setInt(1, idCidade);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String idEstado = rs.getString("idEstado");
                EstadoDAO estDAO = new EstadoDAO();
                Estado estado = estDAO.buscaEstadoPorID(idEstado);
                Cidade cidade = new Cidade(id, nome, estado);
                return cidade;
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

    public Cidade inserirCidade(Cidade cidade) throws ClassNotFoundException, SQLException {
        try {
            EstadoDAO estadoDAO = new EstadoDAO();
            cidade.setEstado(estadoDAO.buscaEstadoPorUF(cidade.getEstado().getUf()));
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(insertCidade, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, cidade.getEstado().getId());
            stmt.setString(2, cidade.getNome());
            stmt.executeUpdate();
            final ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                final int id = rs.getInt(1);
                cidade.setId(id);
            }
            return cidade;
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
