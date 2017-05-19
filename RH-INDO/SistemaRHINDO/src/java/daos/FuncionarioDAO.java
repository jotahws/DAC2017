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
    private final String listFunc = "SELECT * FROM funcionario f, endereco e, cidade cid, estado est, cargo c, departamento d where f.idEndereco = e.id AND e.idCidade = cid.id AND cid.idEstado = est.id AND f.idCargo = c.id AND f.idDepartamento = d.id;";
    private final String verificaLogin = "SELECT email, perfil, nome FROM funcionario WHERE email=? AND senha=?";

    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private Facade facade;

    public void inserirFuncionario(Funcionario funcionario) throws ClassNotFoundException, SQLException {
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
            stmt = con.prepareStatement(verificaLogin);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String perfil = rs.getString("perfil");
                Funcionario func = new Funcionario();
                func.setNome(nome);
                func.setEmail(email);
                func.setPerfil(perfil);
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

}
