/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Funcionario;
import conecao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author JotaWind
 */
public class AtividadeDAO {

    private final String IniciaAtividade = "INSERT INTO FuncionarioAtividade (idFuncionario, idAtividade, statusAtividade, inicio) VALUES (?,?,?,CURRENT_TIMESTAMP)";

    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public void criaAtividade(int idTipo, Funcionario funcionario) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(IniciaAtividade, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, funcionario.getId());
            stmt.setInt(2, idTipo);
            stmt.setInt(3, 1);
            //stmt.setTimestamp(4, );
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
