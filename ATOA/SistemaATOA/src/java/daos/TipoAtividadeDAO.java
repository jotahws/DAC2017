/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Atividade;
import beans.Departamento;
import beans.TipoAtividade;
import beans.TipoAtividade;
import conecao.ConnectionFactory;
import facede.Facade;
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
public class TipoAtividadeDAO {

    private final String insertTipo = "INSERT INTO TipoAtividade (nome, idDepartamento) VALUES (?,?)";
    private final String updateTipo = "UPDATE TipoAtividade SET nome=?, idDepartamento=? WHERE id=?;";
    private final String deleteTipo = "DELETE FROM TipoAtividade WHERE id=?;";
    private final String listaTipos = "SELECT * FROM TipoAtividade ORDER BY nome";
    private final String buscaTipoID = "SELECT * FROM TipoAtividade WHERE id=?";
    private final String verDepartamento = "select * from TipoAtividade where idDepartamento =?;";
    private final String horasPorDepartamento = "SELECT t.idDepartamento, sum(TIMESTAMPDIFF(hour,inicio,fim)) as horasTrabalhadas from FuncionarioAtividade a, TipoAtividade t where month(inicio)=? AND a.idAtividade = t.id Group by t.idDepartamento; ";

    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public void inserirTipo(TipoAtividade tipo) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(insertTipo, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, tipo.getNome());
            stmt.setInt(2, tipo.getDepartamento().getId());
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

    public List<TipoAtividade> listaTipos() throws ClassNotFoundException, SQLException {

        try {
            List<TipoAtividade> lista = new ArrayList();
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(listaTipos);
            rs = stmt.executeQuery();
            Facade facade = new Facade();
            while (rs.next()) {
                int id = rs.getInt("id");
                List<Atividade> atividades = facade.listaAtividadesPorTipo(id);
                String nome = rs.getString("nome");
                int idDepto = rs.getInt("idDepartamento");
                Departamento depto = facade.getDeptoPorID(idDepto);
                TipoAtividade tipo = new TipoAtividade();
                tipo.setId(id);
                tipo.setNome(nome);
                tipo.setAtividades(atividades);
                tipo.setDepartamento(depto);
                lista.add(tipo);
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

    public TipoAtividade buscaTipoPorID(int idTipo) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(buscaTipoID);
            stmt.setInt(1, idTipo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int idDepto = rs.getInt("idDepartamento");
                Facade facade = new Facade();
                Departamento depto = facade.getDeptoPorID(idDepto);
                TipoAtividade tipo = new TipoAtividade();
                tipo.setNome(nome);
                tipo.setId(id);
                tipo.setDepartamento(depto);
                return tipo;
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

    public void alterarTipo(TipoAtividade tipo) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(updateTipo);
            stmt.setString(1, tipo.getNome());
            stmt.setInt(2, tipo.getDepartamento().getId());
            stmt.setInt(3, tipo.getId());
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

    public void deletarTipo(int id) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(deleteTipo);
            stmt.setInt(1, id);
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

    public boolean verificaDepartamento(int idDepart) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(verDepartamento);
            stmt.setInt(1, idDepart);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
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
    }

    public List<Departamento> selectHorasTrabalhadasPorMes(String mes) throws ClassNotFoundException, SQLException {

        try {
            List<Departamento> lista = new ArrayList();
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(horasPorDepartamento);
            stmt.setString(1, mes);
            rs = stmt.executeQuery();
            Facade facade = new Facade();
            while (rs.next()) {
                int idDepto = rs.getInt("t.idDepartamento");
                int horasT = rs.getInt("horasTrabalhadas");
                Departamento depto  = facade.getDeptoPorID(idDepto);
                depto.setHorastrabalhadas(horasT);
                lista.add(depto);
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
}
