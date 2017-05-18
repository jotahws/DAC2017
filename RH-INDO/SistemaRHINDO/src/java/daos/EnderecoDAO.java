/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Cidade;
import beans.Endereco;
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
public class EnderecoDAO {

    private final String selectEndID = "SELECT * FORM Endereco WHERE id=?";
    private final String insertEndereco = "INSERT INTO endereco (idCidade, rua, numero, cep, bairro) VALUES (?,?,?,?,?)";

    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public Endereco buscaEnderecoPorID(int idEndereco) throws ClassNotFoundException, SQLException {

        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(selectEndID);
            stmt.setInt(1, idEndereco);
            rs = stmt.executeQuery();
            if (rs.next()) {
                // idCidade rua numero cep bairro
                int id = rs.getInt("id");
                int idCidade = rs.getInt("idCidade");
                String rua = rs.getString("rua");
                int numero = rs.getInt("numero");
                String cep = rs.getString("cep");
                String bairro = rs.getString("bairro");
                CidadeDAO cidadeDAO = new CidadeDAO();
                Cidade cidade = cidadeDAO.buscaCidadePorID(idCidade);
                Endereco endereco = new Endereco(id, rua, cep, numero, bairro, cidade);
                return endereco;
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

    public Endereco inserirEndereco(Endereco endereco) throws ClassNotFoundException, SQLException {
        try {
            CidadeDAO cidadeDAO = new CidadeDAO();
            endereco.setCidade(cidadeDAO.inserirCidade(endereco.getCidade()));
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(insertEndereco, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, endereco.getCidade().getId());
            stmt.setString(2, endereco.getRua());
            stmt.setInt(3, endereco.getNumero());
            stmt.setString(4, endereco.getCep());
            stmt.setString(5, endereco.getBairro());
            stmt.executeUpdate();
            final ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                final int id = rs.getInt(1);
                endereco.setId(id);
            }
            return endereco;
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
