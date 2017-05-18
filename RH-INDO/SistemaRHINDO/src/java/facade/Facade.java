/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import beans.Cargo;
import beans.Departamento;
import beans.Endereco;
import beans.Estado;
import beans.Funcionario;
import daos.CargoDAO;
import daos.DepartamentoDAO;
import daos.EnderecoDAO;
import daos.EstadoDAO;
import daos.FuncionarioDAO;
import java.sql.SQLException;
import java.util.List;
 
/**
 *
 * @author JotaWind
 */
public class Facade {

    public Estado buscaEstado(String uf) throws ClassNotFoundException {
        EstadoDAO estadoDAO = new EstadoDAO();
        return estadoDAO.buscaEstadoPorUF(uf);
    }

    public Cargo buscaCargo(String cargo) {
        CargoDAO cargoDAO = new CargoDAO();
        return cargoDAO.buscaCargoPorNome(cargo);
    }

    public Cargo buscaCargoPorID(int idCargo) throws ClassNotFoundException, SQLException {
        CargoDAO cargoDAO = new CargoDAO();
        return cargoDAO.buscaCargoPorID(idCargo);
    }

    public Departamento buscaDeptoPorID(int idDepto) throws ClassNotFoundException, SQLException {
        DepartamentoDAO deptoDAO = new DepartamentoDAO();
        return deptoDAO.buscaDeptoPorID(idDepto);
    }

    public Endereco buscaEnderecoPorID(int idEndereco) throws ClassNotFoundException, SQLException {
        EnderecoDAO endDAO = new EnderecoDAO();
        return endDAO.buscaEnderecoPorID(idEndereco);
    }

    public void insereDepto(Departamento depto) throws ClassNotFoundException, SQLException {
        DepartamentoDAO deptoDAO = new DepartamentoDAO();
        deptoDAO.inserirDepto(depto);

    }

    public void insereFuncionario(Funcionario funcionario) throws ClassNotFoundException, SQLException {
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        funcDAO.inserirFuncionario(funcionario);
    }

    public void insereCargo(Cargo cargo) throws ClassNotFoundException, SQLException {
        CargoDAO cargoDAO = new CargoDAO();
        cargoDAO.inserirCargo(cargo);
    }

    public List<Cargo> carregaListaCargos() throws ClassNotFoundException, SQLException {
        CargoDAO cargoDAO = new CargoDAO();
        return cargoDAO.listaCargos();
    }

    public List<Departamento> carregaListaDeptos() throws ClassNotFoundException, SQLException {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        return departamentoDAO.listaDepartamentos();
    }

    public List<Estado> carregaListaUF() throws ClassNotFoundException, SQLException {
        EstadoDAO estadoDAO = new EstadoDAO();
        return estadoDAO.listaEstados();
    }

    public List<Funcionario> carregaListaFunc() throws NullPointerException, ClassNotFoundException, SQLException {
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        return funcDAO.listaFuncionarios();
    }

    public int getIdCargo(Cargo cargo) {
        CargoDAO cargoDAO = new CargoDAO();
        return cargoDAO.buscaIdCargo(cargo);
    }

    public int getIdDepto(Departamento departamento) {
        DepartamentoDAO deptoDAO = new DepartamentoDAO();
        return deptoDAO.buscaIdDepto(departamento);
    }

    public int getIdEndereco(Endereco endereco) {
        EnderecoDAO endDAO = new EnderecoDAO();
        return endDAO.buscaIdEndereco(endereco);
    }

    public Funcionario verificaLogin(String login, String senha) throws ClassNotFoundException, SQLException {
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        return funcDAO.fazLogin(login, senha);
    }

}
