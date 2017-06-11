/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.Atividade;
import beans.Departamento;
import beans.Funcionario;
import beans.HorasTrabalhadas;
import beans.TipoAtividade;
import conecao.ConnectionFactory;
import facede.Facade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JotaWind
 */
public class HorasTrabalhadasDAO {

    private final String listaHoras = "Select a.idFuncionario, month(inicio) as mes, sum(TIMESTAMPDIFF(hour,inicio,fim)) as horasTrabalhadas \n"
            + "from FuncionarioAtividade a \n"
            + "where a.inicio between date(?) and date(?) AND a.idFuncionario=?\n"
            + "group by mes;";
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public List<HorasTrabalhadas> listaHoras(String mesDe, String mesAte, Funcionario func) throws ClassNotFoundException, SQLException {

        try {
            List<HorasTrabalhadas> lista = new ArrayList();
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(listaHoras);
            stmt.setString(1, mesDe);
            stmt.setString(2, mesAte);
            stmt.setInt(3, func.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                String mes = rs.getString("mes");
                int horasTrab = rs.getInt("horasTrabalhadas");
                HorasTrabalhadas horas = new HorasTrabalhadas();
                horas.setFunc(func);
                horas.setMes(mes);
                horas.setHorasTrabalhadas(horasTrab);
                lista.add(horas);
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
