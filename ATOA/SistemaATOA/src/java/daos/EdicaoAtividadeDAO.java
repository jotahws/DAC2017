/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Atividade;
import beans.Departamento;
import beans.EdicaoAtividade;
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
    private final String selectEdicoesPendentes = "SELECT * FROM EdicaoAtividade where statusAprovacao='PENDENTE'";
    private final String selectEdicoes = "SELECT * FROM EdicaoAtividade;";
    private final String listaEdicaoPorID = "select * from EdicaoAtividade e where e.id=?;";
    private final String updateStatusAprovacao = "UPDATE EdicaoAtividade SET statusAprovacao=? WHERE id=?;";

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

    public List<EdicaoAtividade> listaEdicoes() throws ClassNotFoundException, SQLException {

        try {
            List<EdicaoAtividade> lista = new ArrayList();
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(selectEdicoesPendentes);
            rs = stmt.executeQuery();
            Facade facade = new Facade();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idAtividade = rs.getInt("idAtividade");
                Atividade atividade = facade.listaAtividadePorID(idAtividade);
                String descricao = rs.getString("descricao");
                String statusAprovacao = rs.getString("statusAprovacao");
                Date inicio = rs.getDate("inicio");
                Timestamp timestampinicio = rs.getTimestamp("inicio");
                if (timestampinicio != null) {
                    inicio = new java.util.Date(timestampinicio.getTime());
                }
                Date fim = rs.getDate("fim");
                Timestamp timestampfim = rs.getTimestamp("fim");
                if (timestampfim != null) {
                    inicio = new java.util.Date(timestampfim.getTime());
                }
                EdicaoAtividade edicao = new EdicaoAtividade();
                edicao.setId(id);
                edicao.setAtividade(atividade);
                edicao.setDescricao(descricao);
                edicao.setStatusAprovacao(statusAprovacao);
                edicao.setInicio(inicio);
                edicao.setFim(fim);
                lista.add(edicao);
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

    public List<EdicaoAtividade> listaTodasEdicoes() throws ClassNotFoundException, SQLException {

        try {
            List<EdicaoAtividade> lista = new ArrayList();
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(selectEdicoes);
            rs = stmt.executeQuery();
            Facade facade = new Facade();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idAtividade = rs.getInt("idAtividade");
                Atividade atividade = facade.listaAtividadePorID(idAtividade);
                String descricao = rs.getString("descricao");
                String statusAprovacao = rs.getString("statusAprovacao");
                Date inicio = rs.getDate("inicio");
                Timestamp timestampinicio = rs.getTimestamp("inicio");
                if (timestampinicio != null) {
                    inicio = new java.util.Date(timestampinicio.getTime());
                }
                Date fim = rs.getDate("fim");
                Timestamp timestampfim = rs.getTimestamp("fim");
                if (timestampfim != null) {
                    inicio = new java.util.Date(timestampfim.getTime());
                }
                EdicaoAtividade edicao = new EdicaoAtividade();
                edicao.setId(id);
                edicao.setAtividade(atividade);
                edicao.setDescricao(descricao);
                edicao.setStatusAprovacao(statusAprovacao);
                edicao.setInicio(inicio);
                edicao.setFim(fim);
                lista.add(edicao);
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

    public EdicaoAtividade EdicaoPorID(int idEdicao) throws ClassNotFoundException, SQLException {
        try {
            Facade facade = new Facade();
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(listaEdicaoPorID);
            stmt.setInt(1, idEdicao);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String idAtividade = rs.getString("e.idAtividade");
                Atividade atividade = facade.listaAtividadePorID(Integer.parseInt(idAtividade));
                //Dados da Edicao
                String descricaoE = rs.getString("e.descricao");
                String status = rs.getString("e.statusAprovacao");
                //Criando Edicao
                EdicaoAtividade edicao = new EdicaoAtividade();
                edicao.setDescricao(descricaoE);
                edicao.setId(idEdicao);
                edicao.setAtividade(atividade);
                edicao.setStatusAprovacao(status);
                return edicao;
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

    public void aprovarEdicao(EdicaoAtividade edicao) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            Facade facade = new Facade();
            stmt = con.prepareStatement(updateStatusAprovacao);
            stmt.setString(1, "APROVADO");
            stmt.setInt(2, edicao.getId());
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

    public void recusarEdicao(EdicaoAtividade edicao) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(updateStatusAprovacao);
            stmt.setString(1, "RECUSADO");
            stmt.setInt(2, edicao.getId());
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
