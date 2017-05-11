/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import beans.Cargo;
import beans.Departamento;
import beans.Estado;
import daos.CargoDAO;
import daos.DepartamentoDAO;
import daos.EstadoDAO;
import java.sql.SQLException;

/**
 *
 * @author JotaWind
 */
public class Facade {

    public Estado buscaEstado(String uf) throws ClassNotFoundException {
        EstadoDAO estadoDAO = new EstadoDAO();
        return estadoDAO.buscaEstadoPorUF(uf);
    }

    public void insereDepto(Departamento depto) throws ClassNotFoundException, SQLException {
        DepartamentoDAO deptoDAO = new DepartamentoDAO();
        deptoDAO.inserirDepto(depto);

    }

    public void insereCargo(Cargo cargo) throws ClassNotFoundException, SQLException {
        CargoDAO cargoDAO = new CargoDAO();
        cargoDAO.inserirCargo(cargo);
    }

}
