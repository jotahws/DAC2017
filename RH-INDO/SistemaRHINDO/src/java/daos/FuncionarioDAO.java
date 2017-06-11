/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Cargo;
import beans.Cidade;
import beans.Departamento;
import beans.Endereco;
import beans.Estado;
import beans.Funcionario;
import beans.HorasTrabalhadas;
import conexao.ConnectionFactory;
import facade.Facade;
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
public class FuncionarioDAO {

    private final String insertFunc = "insert into Funcionario (idCargo, idDepartamento, idEndereco, nome, cpf, rg, email, celular, perfil, senha) values(?,?,?,?,?,?,?,?,?,?)";
    private final String insertFuncTEMP = "insert into funcTemp (id, nome, email, cpf, cargo, departamento, salario, horas) values(?,?,?,?,?,?,?,?)";
    private final String listFunc = "SELECT * FROM funcionario f, endereco e, cidade cid, estado est, cargo c, departamento d where f.idEndereco = e.id AND e.idCidade = cid.id AND cid.idEstado = est.id AND f.idCargo = c.id AND f.idDepartamento = d.id ORDER BY f.nome;";
    private final String verificaLogin = "SELECT email, perfil, nome, id FROM funcionario WHERE email=? AND senha=?";
    private final String selectFuncID = "SELECT * FROM funcionario f, endereco e, cidade cid, estado est, cargo c, departamento d where f.idEndereco = e.id AND e.idCidade = cid.id AND cid.idEstado = est.id AND f.idCargo = c.id AND f.idDepartamento = d.id AND f.id=?";
    private final String updateFunc = "UPDATE Funcionario SET idCargo=?, idDepartamento=?, idEndereco=?, nome=?, cpf=?, rg=?, email=?, celular=?, perfil=? WHERE id=?;";
    private final String deleteFunc = "DELETE FROM Funcionario WHERE id=?";
    private final String deleteFuncTemp = "DELETE FROM funcTemp;";
    private final String deleteHoraTemp = "DELETE FROM horasTemp;";
    private final String insertHoraTEMP = "INSERT INTO horasTemp (idFuncionario,nome, mes, horas,email) VALUES (?,?,?,?,?);";
    
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private Facade facade;

    public void inserirFuncionario(Funcionario funcionario) throws ClassNotFoundException, SQLException, NullPointerException {
        try {
            EnderecoDAO endDAO = new EnderecoDAO();
            funcionario.setEndereco(endDAO.inserirEndereco(funcionario.getEndereco()));
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(insertFunc, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, funcionario.getCargo().getId());
            stmt.setInt(2, funcionario.getDepartamento().getId());
            stmt.setInt(3, funcionario.getEndereco().getId());
            stmt.setString(4, funcionario.getNome());
            stmt.setString(5, funcionario.getCpf());
            stmt.setString(6, funcionario.getRg());
            stmt.setString(7, funcionario.getEmail());
            stmt.setString(8, funcionario.getCelular());
            stmt.setString(9, funcionario.getPerfil());
            stmt.setString(10, funcionario.getSenha());
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

    public List<Funcionario> listaFuncionarios() throws NullPointerException, ClassNotFoundException, SQLException {

        try {
            List<Funcionario> lista = new ArrayList();
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(listFunc);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //funcionario
                int idFunc = rs.getInt("f.id");
                String nome = rs.getString("f.nome");
                String cpf = rs.getString("f.cpf");
                String rg = rs.getString("f.rg");
                String email = rs.getString("f.email");
                String celular = rs.getString("f.celular");
                String perfil = rs.getString("f.perfil");
                String senha = rs.getString("f.senha");
                //senha = Funcionario.descriptografa(senha);
                //Endereco
                int idEnd = rs.getInt("e.id");
                String rua = rs.getString("e.rua");
                int numero = rs.getInt("e.numero");
                String cep = rs.getString("e.cep");
                String bairro = rs.getString("e.bairro");
                //Cidade
                int idCidade = rs.getInt("cid.id");
                String nomeCidade = rs.getString("cid.nome");
                //Estado
                int idEstado = rs.getInt("est.id");
                String nomeEstado = rs.getString("est.nome");
                String uf = rs.getString("est.uf");
                //Depto
                int idDepto = rs.getInt("d.id");
                String nomeDepto = rs.getString("d.nome");
                String localizacao = rs.getString("d.localizacao");
                //Cargo
                int idCargo = rs.getInt("c.id");
                String nomeCargo = rs.getString("c.nome");
                double salario = rs.getDouble("c.salario");
                String requisitos = rs.getString("c.requisitos");
                int horasMinimas = rs.getInt("c.horasMinimas");
                double descontoImposto = rs.getDouble("c.descontoImposto");

                Estado estado = new Estado(idEstado, nomeEstado, uf);
                Cidade cidade = new Cidade(idCidade, nomeCidade, estado);
                Endereco endereco = new Endereco(idEnd, rua, cep, numero, bairro, cidade);
                Departamento depto = new Departamento(idDepto, nomeDepto, localizacao);
                Cargo cargo = new Cargo(idCargo, nomeCargo, salario, requisitos, horasMinimas, descontoImposto);
                Funcionario func = new Funcionario(idFunc, nome, rg, cpf, celular, email, endereco, cargo, depto, perfil, senha);
                lista.add(func);
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

    public Funcionario fazLogin(String login, String senha) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(verificaLogin,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String perfil = rs.getString("perfil");
                Integer id = rs.getInt("id");
                Funcionario func = new Funcionario();
                func.setNome(nome);
                func.setEmail(email);
                func.setPerfil(perfil);
                func.setId((id));
                return func;
            }
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (Exception ex) {

            }
        }
        return null;
    }

    public Funcionario buscaFuncPorID(int idFunc) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(selectFuncID);
            stmt.setInt(1, idFunc);
            rs = stmt.executeQuery();
            if (rs.next()) {
                //funcionario
                String nome = rs.getString("f.nome");
                String cpf = rs.getString("f.cpf");
                String rg = rs.getString("f.rg");
                String email = rs.getString("f.email");
                String celular = rs.getString("f.celular");
                String perfil = rs.getString("f.perfil");
                String senha = rs.getString("f.senha");
                //Endereco
                int idEnd = rs.getInt("e.id");
                String rua = rs.getString("e.rua");
                int numero = rs.getInt("e.numero");
                String cep = rs.getString("e.cep");
                String bairro = rs.getString("e.bairro");
                //Cidade
                int idCidade = rs.getInt("cid.id");
                String nomeCidade = rs.getString("cid.nome");
                //Estado
                int idEstado = rs.getInt("est.id");
                String nomeEstado = rs.getString("est.nome");
                String uf = rs.getString("est.uf");
                //Depto
                int idDepto = rs.getInt("d.id");
                String nomeDepto = rs.getString("d.nome");
                String localizacao = rs.getString("d.localizacao");
                //Cargo
                int idCargo = rs.getInt("c.id");
                String nomeCargo = rs.getString("c.nome");
                double salario = rs.getDouble("c.salario");
                String requisitos = rs.getString("c.requisitos");
                int horasMinimas = rs.getInt("c.horasMinimas");
                double descontoImposto = rs.getDouble("c.descontoImposto");
                //instanciar
                Estado estado = new Estado(idEstado, nomeEstado, uf);
                Cidade cidade = new Cidade(idCidade, nomeCidade, estado);
                Endereco endereco = new Endereco(idEnd, rua, cep, numero, bairro, cidade);
                Departamento depto = new Departamento(idDepto, nomeDepto, localizacao);
                Cargo cargo = new Cargo(idCargo, nomeCargo, salario, requisitos, horasMinimas, descontoImposto);
                Funcionario func = new Funcionario(idFunc, nome, rg, cpf, celular, email, endereco, cargo, depto, perfil, senha);
                return func;
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

    public void editarFunc(Funcionario funcionario) throws ClassNotFoundException, SQLException, NullPointerException {
        try {
            EnderecoDAO endDAO = new EnderecoDAO();
            funcionario.setEndereco(endDAO.inserirEndereco(funcionario.getEndereco()));
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(updateFunc, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, funcionario.getCargo().getId());
            stmt.setInt(2, funcionario.getDepartamento().getId());
            stmt.setInt(3, funcionario.getEndereco().getId());
            stmt.setString(4, funcionario.getNome());
            stmt.setString(5, funcionario.getCpf());
            stmt.setString(6, funcionario.getRg());
            stmt.setString(7, funcionario.getEmail());
            stmt.setString(8, funcionario.getCelular());
            stmt.setString(9, funcionario.getPerfil());
            //stmt.setString(10, funcionario.getSenha());
            stmt.setInt(10, funcionario.getId());
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

    public void excluirFuncionario(int idFunc) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(deleteFunc, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, idFunc);
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

    public void insereHorasFuncTemp(Funcionario funcionario) throws ClassNotFoundException, SQLException, NullPointerException {
        try {
            EnderecoDAO endDAO = new EnderecoDAO();
            funcionario.setEndereco(endDAO.inserirEndereco(funcionario.getEndereco()));
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(insertFuncTEMP);
            stmt.setInt(1, funcionario.getId());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getEmail());
            stmt.setString(4, funcionario.getCpf());
            stmt.setString(5, funcionario.getCargo().getNome());
            stmt.setString(6, funcionario.getDepartamento().getNome());
            stmt.setDouble(7, funcionario.getCargo().getSalario());
            stmt.setInt(8, funcionario.getDepartamento().getHorastrabalhadas());
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

    public void removeFuncTemporario() throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(deleteFuncTemp);
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

    public void insereHoraTemp(HorasTrabalhadas hora) throws ClassNotFoundException, SQLException, NullPointerException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(insertHoraTEMP);
            stmt.setInt(1, hora.getFunc().getId());
            stmt.setString(2, hora.getFunc().getNome());
            stmt.setString(3, hora.getMes());
            stmt.setInt(4, hora.getHorasTrabalhadas());
            stmt.setString(5, hora.getFunc().getEmail());
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

    public void removeHorasTemp() throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(deleteHoraTemp);
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
