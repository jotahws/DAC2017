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

/**
 *
 * @author JotaWind
 */
public class EnderecoDAO {

    private final String selectEndID = "SELECT * FORM Endereco WHERE id=?";

    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public int buscaIdEndereco(Endereco endereco) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Endereco buscaEnderecoPorID(int idEndereco) throws ClassNotFoundException, SQLException {

        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(selectEndID);
            stmt.setInt(1, idEndereco);
            rs = stmt.executeQuery();
            if (rs.next()) {
                // idCidade rua numero cep bairro
                int idCidade = rs.getInt("idCidade");
                String rua = rs.getString("rua");
                int numero = rs.getInt("numero");
                String cep = rs.getString("cep");
                String bairro = rs.getString("bairro");
                CidadeDAO cidadeDAO = new CidadeDAO();
                Cidade cidade = cidadeDAO.buscaCidadePorID(idCidade);
                Endereco endereco = new Endereco(rua, cep, numero, bairro, cidade);
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

}
