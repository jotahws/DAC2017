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

/**
 *
 * @author JotaWind
 */
public class AtividadeDAO {

    private final String iniciaAtividade = "INSERT INTO FuncionarioAtividade (idFuncionario, idAtividade, statusAtividade, inicio) VALUES (?,?,?,CURRENT_TIMESTAMP)";
    private final String buscaAtividadeIniciada = "select * from TipoAtividade t, funcionarioatividade a where a.idAtividade = t.id AND idFuncionario=? AND statusAtividade=1;";
    private final String EncerraAtividade = "UPDATE FuncionarioAtividade SET statusAtividade=2, fim=CURRENT_TIMESTAMP WHERE idFuncionario=? AND statusAtividade=1;";
    private final String EncerraTudo = "UPDATE FuncionarioAtividade SET statusAtividade=2, fim=CURRENT_TIMESTAMP WHERE idAtividade=? AND statusAtividade=1;";
    private final String listaAtvPorTipo = "SELECT * FROM FuncionarioAtividade WHERE idAtividade=? AND statusAtividade=1 ORDER BY inicio DESC;";
    private final String verFuncionario = "select * FROM FuncionarioAtividade WHERE idFuncionario=?;";
    private final String insereFuncionario = "insert INTO funcTemp (id,nome,email,cpf,cargo,departamento,salario) VALUES (?,?,?,?,?,?,?)";
    private final String listaAtvPorID = "select * from TipoAtividade t, funcionarioatividade a where a.idAtividade = t.id AND a.id=?;";
    private final String updateDescricao = "UPDATE FuncionarioAtividade SET descricao=? WHERE id=?;";

    
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public void criaAtividade(int idTipo, Funcionario funcionario) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(iniciaAtividade, Statement.RETURN_GENERATED_KEYS);
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

    public Atividade getAtividadeIniciada(Funcionario func) throws ClassNotFoundException, SQLException {
        try {
            Facade facade = new Facade();
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(buscaAtividadeIniciada);
            stmt.setInt(1, func.getId());
            rs = stmt.executeQuery();
            if (rs.next()) {
                //Dados da Atividade
                int idAtiv = rs.getInt("a.id");
                String descricao = rs.getString("a.descricao");
                int statusAtividade = rs.getInt("a.statusAtividade");
                Date inicio = rs.getDate("a.inicio");
                Timestamp timestampinicio = rs.getTimestamp("a.inicio");
                if (timestampinicio != null) {
                    inicio = new java.util.Date(timestampinicio.getTime());
                }
                Date fim = rs.getDate("a.fim");
                Timestamp timestampfim = rs.getTimestamp("a.fim");
                if (timestampfim != null) {
                    inicio = new java.util.Date(timestampfim.getTime());
                }
                //Dados do TIPO
                int idTipo = rs.getInt("t.id");
                int idDepto = rs.getInt("t.idDepartamento");
                String nome = rs.getString("t.nome");
                //Criando objetos Tipo
                TipoAtividade tipo = facade.getTipoPorID(idTipo);
                //Criando atividade
                Atividade atividade = new Atividade();
                atividade.setId(idAtiv);
                atividade.setDescricao(descricao);
                atividade.setStatusAtividade(statusAtividade);
                atividade.setFuncionario(func);
                atividade.setTipo(tipo);
                atividade.setInicio(inicio);
                //atividade.setFim(fim);
                return atividade;
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

    public void encerraAtividade(Funcionario funcionario) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(EncerraAtividade);
            stmt.setInt(1, funcionario.getId());
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

    public List<Atividade> listaAtividades(int idTipo) throws ClassNotFoundException, SQLException {

        try {
            List<Atividade> lista = new ArrayList();
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(listaAtvPorTipo);
            stmt.setInt(1, idTipo);
            rs = stmt.executeQuery();
            Facade facade = new Facade();
            while (rs.next()) {

                int idAtiv = rs.getInt("id");
                String descricao = rs.getString("descricao");
                int statusAtividade = rs.getInt("statusAtividade");
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
                Funcionario funcionario = facade.getfuncionarioID(rs.getInt("idFuncionario"));
                TipoAtividade tipo = facade.getTipoPorID(idTipo);
                Atividade atv = new Atividade();
                atv.setId(idAtiv);
                atv.setDescricao(descricao);
                atv.setStatusAtividade(statusAtividade);
                atv.setInicio(inicio);
                atv.setFim(fim);
                atv.setTipo(tipo);
                atv.setFuncionario(funcionario);
                lista.add(atv);
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

    public void encerraTudo(int idTipo) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(EncerraTudo);
            stmt.setInt(1, idTipo);
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

    public boolean verificaFuncionario(int idTipo) throws SQLException, ClassNotFoundException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(verFuncionario);
            stmt.setInt(1, idTipo);
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

    public Atividade listaAtividadesID(int idAtividade) throws ClassNotFoundException, SQLException {
        try {
            Facade facade = new Facade();
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(listaAtvPorID);
            stmt.setInt(1, idAtividade);
            rs = stmt.executeQuery();
            if (rs.next()) {
                //Dados da Atividade
                int idAtiv = rs.getInt("a.id");
                String descricao = rs.getString("a.descricao");
                int statusAtividade = rs.getInt("a.statusAtividade");
                Date inicio = rs.getDate("a.inicio");
                Timestamp timestampinicio = rs.getTimestamp("a.inicio");
                if (timestampinicio != null) {
                    inicio = new java.util.Date(timestampinicio.getTime());
                }
                Date fim = rs.getDate("a.fim");
                Timestamp timestampfim = rs.getTimestamp("a.fim");
                if (timestampfim != null) {
                    inicio = new java.util.Date(timestampfim.getTime());
                }
                int idFunc = rs.getInt("a.idFuncionario");
                //Dados do TIPO
                int idTipo = rs.getInt("t.id");
                int idDepto = rs.getInt("t.idDepartamento");
                String nome = rs.getString("t.nome");
                //Criando objetos Tipo
                TipoAtividade tipo = facade.getTipoPorID(idTipo);
                //Criando atividade
                Atividade atividade = new Atividade();
                atividade.setId(idAtiv);
                atividade.setDescricao(descricao);
                atividade.setStatusAtividade(statusAtividade);
                atividade.setFuncionario(facade.getfuncionarioID(idFunc));
                atividade.setTipo(tipo);
                atividade.setInicio(inicio);
                //atividade.setFim(fim);
                return atividade;
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

    public void insereFuncTemporario(Funcionario func) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(insereFuncionario);
            stmt.setInt(1, func.getId());
            stmt.setString(2, func.getNome());
            stmt.setString(3, func.getEmail());
            stmt.setString(4, func.getCpf());
            stmt.setString(5, func.getCargo().getNome());
            stmt.setString(6, func.getDepartamento().getNome());
            stmt.setDouble(7, func.getCargo().getSalario());
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

    public void setDescricao(EdicaoAtividade edicao) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            Facade facade = new Facade();
            stmt = con.prepareStatement(updateDescricao);
            stmt.setString(1, edicao.getDescricao());
            stmt.setInt(2, edicao.getAtividade().getId());
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
