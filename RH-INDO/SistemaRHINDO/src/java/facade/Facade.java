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
import beans.HorasTrabalhadas;
import daos.CargoDAO;
import daos.DepartamentoDAO;
import daos.EnderecoDAO;
import daos.EstadoDAO;
import daos.FuncionarioDAO;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author JotaWind
 */
public class Facade {

    public Estado buscaEstado(String uf) throws ClassNotFoundException {
        EstadoDAO estadoDAO = new EstadoDAO();
        return estadoDAO.buscaEstadoPorUF(uf);
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

    public void insereDepto(Departamento depto) throws ClassNotFoundException, SQLException, NullPointerException {
        DepartamentoDAO deptoDAO = new DepartamentoDAO();
        deptoDAO.inserirDepto(depto);
    }

    public void insereFuncionario(Funcionario funcionario) throws ClassNotFoundException, SQLException, NullPointerException {
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

    public Funcionario verificaLogin(String login, String senha) throws ClassNotFoundException, SQLException {
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        return funcDAO.fazLogin(login, senha);
    }

    public void editarDepartamento(Departamento departamento) throws ClassNotFoundException, SQLException {
        DepartamentoDAO deptoDAO = new DepartamentoDAO();
        deptoDAO.editarDepto(departamento);
    }

    public void editarCargo(Cargo cargo) throws SQLException, ClassNotFoundException {
        CargoDAO cargoDAO = new CargoDAO();
        cargoDAO.editarCargo(cargo);
    }

    public void editarFuncionario(Funcionario funcionario) throws ClassNotFoundException, SQLException, NullPointerException {
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        funcDAO.editarFunc(funcionario);
    }

    public void excluirDepartamento(int idDepto) throws SQLException, ClassNotFoundException {
        DepartamentoDAO deptoDAO = new DepartamentoDAO();
        deptoDAO.excluirDepto(idDepto);
    }

    public void excluirCargo(int cargo) throws SQLException, ClassNotFoundException {
        CargoDAO cargoDAO = new CargoDAO();
        cargoDAO.excluirCargo(cargo);
    }

    public void excluirFuncionario(int idFunc) throws ClassNotFoundException, SQLException {
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        funcDAO.excluirFuncionario(idFunc);
    }

    public Funcionario buscaFuncionarioPorID(int idFunc) throws ClassNotFoundException, SQLException {
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        return funcDAO.buscaFuncPorID(idFunc);
    }

    public List<Departamento> getHorasTrabalhadasPorMes(String mes) {
        Client client = ClientBuilder.newClient();
        Response res = client.target("http://localhost:8084/SistemaATOA/webresources/departamento/" + mes)
                .request(MediaType.APPLICATION_JSON).get();

        return res.readEntity(new GenericType<List<Departamento>>() {
        });
    }

    public void insereHorasDeptoTemp(List<Departamento> deptos) throws ClassNotFoundException, SQLException {
        DepartamentoDAO deptoDAO = new DepartamentoDAO();
        for (Departamento depto : deptos) {
            deptoDAO.insereHorasDeptoTemp(depto);
        }
    }

    public void deleteHorasDeptoTemp() throws ClassNotFoundException, SQLException {
        DepartamentoDAO deptoDAO = new DepartamentoDAO();
        deptoDAO.deletaHorasDeptoTemp();
    }

    public List<Funcionario> getFuncHorasAtrasadas(String mes) {
        Client client = ClientBuilder.newClient();
        Response res = client.target("http://localhost:8084/SistemaATOA/webresources/funcionarios/" + mes)
                .request(MediaType.APPLICATION_JSON).get();

        return res.readEntity(new GenericType<List<Funcionario>>() {
        });
    }

    public void insereHorasFuncTemp(List<Funcionario> funcs) throws ClassNotFoundException, SQLException {
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        for (Funcionario func : funcs) {
            funcDAO.insereHorasFuncTemp(func);
        }
    }

    public void deleteHorasFuncTemp() throws ClassNotFoundException, SQLException {
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        funcDAO.removeFuncTemporario();
    }

    public List<HorasTrabalhadas> getHorastrabalhadas(String mesDe, String mesAte, Funcionario func) {
        Client client = ClientBuilder.newClient();
        Response res = client.target("http://localhost:8084/SistemaATOA/webresources/funcionarios/horasTrabalhadas/" + mesDe + "/" + mesAte + "/" + func.getId())
                .request(MediaType.APPLICATION_JSON).get();

        return res.readEntity(new GenericType<List<HorasTrabalhadas>>() {
        });
    }

    public void insereHorasPorMes(List<HorasTrabalhadas> horas) throws ClassNotFoundException, SQLException {
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        for (HorasTrabalhadas hora : horas) {
            funcDAO.insereHoraTemp(hora);
        }
    }

    public void deleteHorasPorMes() throws ClassNotFoundException, SQLException {
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        funcDAO.removeHorasTemp();
    }

    public void fecharFolha(List<HorasTrabalhadas> horas) {
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        for (HorasTrabalhadas item : horas) {
            funcDAO.fecharFolha(item); 
        }
    }
}
