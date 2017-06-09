/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Atividade;
import beans.Departamento;
import beans.Funcionario;
import beans.TipoAtividade;
import conecao.ConnectionFactory;
import facede.Facade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JotaWind
 */
public class EdicaoAtividadeDAO {

    private final String insereCorrecao = "INSERT INTO EdicaoAtividade (idAtividade, descricao, statusAprovacao) VALUES (?,?,?);";
    private final String updateCorrecao = "UPDATE EdicaoAtividade SET descricao=?, statusAprovacao=? WHERE idAtividade=?;";
    private final String selectAtividadeID = "SELECT * FROM EdicaoAtividade WHERE idAtividade=?";

    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public void insereCorrecao(Atividade atividade) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();

            stmt = con.prepareStatement(insereCorrecao, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, atividade.getId());
            stmt.setString(2, atividade.getDescricao());
            stmt.setString(3, "PENDENTE");
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

    public void listaEdicoes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
