/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Estado;
import conexao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JotaWind
 */
public class EstadoDAO {

    private final String selectEstadoPorUF = "SELECT * FROM Estado WHERE uf=?";
    private final String selectEstadoPorID = "SELECT * FROM Estado WHERE id=?";
    private final String listEstados = "SELECT * FROM estado";

    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public Estado buscaEstadoPorUF(String uf) throws ClassNotFoundException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(selectEstadoPorUF);
            stmt.setString(1, uf);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                Estado estado = new Estado(nome, uf);
                return estado;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar endereço: " + ex.getMessage());
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

    public Estado buscaEstadoPorID(String idEstado)  throws ClassNotFoundException, SQLException {
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(selectEstadoPorID);
            stmt.setString(1, idEstado);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String uf = rs.getString("uf");
                Estado estado = new Estado(nome, uf);
                return estado;
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

    public List<Estado> listaEstados() throws ClassNotFoundException, SQLException {

        try {
            List<Estado> lista = new ArrayList();
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(listEstados);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String uf = rs.getString("uf");
                Estado estado = new Estado(nome, uf);
                lista.add(estado);
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
