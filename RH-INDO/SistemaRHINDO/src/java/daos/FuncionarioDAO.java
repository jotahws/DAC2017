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

    private final String insertFunc = "insert into Funcionario (idCargo, idDepartamento, idEndereco, nome, cpf, rg, email, celular, perfil) values(?,?,?,?,?,?,?,?,?)";
    private final String listFunc = "SELECT * FROM funcionario f, endereco e, cidade cid, estado est, cargo c, departamento d where f.idEndereco = e.id AND e.idCidade = cid.id AND cid.idEstado = est.id AND f.idCargo = c.id AND f.idDepartamento = d.id;";

    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private Facade facade;

    public void inserirFuncionario(Funcionario funcionario) throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(insertFunc, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, facade.getIdCargo(funcionario.getCargo()));
            stmt.setInt(2, facade.getIdDepto(funcionario.getDepartamento()));
            stmt.setInt(3, facade.getIdEndereco(funcionario.getEndereco()));
            stmt.setString(4, funcionario.getNome());
            stmt.setString(5, funcionario.getCpf());
            stmt.setString(6, funcionario.getRg());
            stmt.setString(7, funcionario.getEmail());
            stmt.setString(8, funcionario.getCelular());
            stmt.setString(9, funcionario.getPerfil());
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
                String nome = rs.getString("f.nome");
                String cpf = rs.getString("f.cpf");
                String rg = rs.getString("f.rg");
                String email = rs.getString("f.email");
                String celular = rs.getString("f.celular");
                String perfil = rs.getString("f.perfil");
                //Endereco
                String rua = rs.getString("e.rua");
                int numero = rs.getInt("e.numero");
                String cep = rs.getString("e.cep");
                String bairro = rs.getString("e.bairro");
                //Cidade
                String nomeCidade = rs.getString("cid.nome");
                //Estado
                String nomeEstado = rs.getString("est.nome");
                String uf = rs.getString("est.uf");
                //Depto
                String nomeDepto = rs.getString("d.nome");
                String localizacao = rs.getString("d.localizacao");
                //Cargo
                String nomeCargo = rs.getString("c.nome");
                double salario = rs.getDouble("c.salario");
                String requisitos = rs.getString("c.requisitos");
                int horasMinimas = rs.getInt("c.horasMinimas");
                double descontoImposto = rs.getDouble("c.descontoImposto");

                Estado estado = new Estado(nomeEstado, uf);
                Cidade cidade = new Cidade(nomeCidade, estado);
                Endereco endereco = new Endereco(rua, cep, numero, bairro, cidade);
                Departamento depto = new Departamento(nomeDepto, localizacao);
                Cargo cargo = new Cargo(nomeCargo, salario, requisitos, horasMinimas, descontoImposto);
                Funcionario func = new Funcionario(nome, rg, cpf, celular, email, endereco, cargo, depto, perfil);
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

}
