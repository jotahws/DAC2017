/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facede;

import beans.Atividade;
import beans.Departamento;
import beans.Funcionario;
import beans.TipoAtividade;
import daos.AtividadeDAO;
import daos.EdicaoAtividadeDAO;
import daos.TipoAtividadeDAO;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author MauMau
 */
public class Facade {

    public void insereTipo(TipoAtividade tipoAtv) throws ClassNotFoundException, SQLException {
        TipoAtividadeDAO dao = new TipoAtividadeDAO();
        dao.inserirTipo(tipoAtv);
    }

    public List<Departamento> listaDeptos() {
        Client client = ClientBuilder.newClient();
        Response res = client.target("http://localhost:8084/SistemaRHINDO/webresources/departamento")
                .request(MediaType.APPLICATION_JSON).get();

        return res.readEntity(new GenericType<List<Departamento>>() {
        });
    }
    
    public List<Funcionario> listaFuncionarios() {
        Client client = ClientBuilder.newClient();
        Response res = client.target("http://localhost:8084/SistemaRHINDO/webresources/funcionarios")
                .request(MediaType.APPLICATION_JSON).get();

        return res.readEntity(new GenericType<List<Funcionario>>() {
        });
    }
    
    

    public List<TipoAtividade> listaAtividades() throws ClassNotFoundException, SQLException {
        TipoAtividadeDAO dao = new TipoAtividadeDAO();
        return dao.listaTipos();
    }

    public List<Atividade> listaAtividadesPorTipo(int idTipo) throws ClassNotFoundException, SQLException {
        AtividadeDAO dao = new AtividadeDAO();
        return dao.listaAtividades(idTipo);
    }

    public Departamento getDeptoPorID(int idDepto) {
        Client client = ClientBuilder.newClient();
        Departamento retorno = client.target("http://localhost:8084/SistemaRHINDO/webresources/departamento/" + idDepto)
                .request(MediaType.APPLICATION_JSON)
                .get(Departamento.class);
        return retorno;
    }

    public TipoAtividade getTipoPorID(int idTipo) throws ClassNotFoundException, SQLException {
        TipoAtividadeDAO dao = new TipoAtividadeDAO();
        return dao.buscaTipoPorID(idTipo);
    }

    public void alteraTipo(TipoAtividade tipoAtv) throws ClassNotFoundException, SQLException {
        TipoAtividadeDAO dao = new TipoAtividadeDAO();
        dao.alterarTipo(tipoAtv);
    }

    public void deletaTipo(int id) throws ClassNotFoundException, SQLException {
        TipoAtividadeDAO dao = new TipoAtividadeDAO();
        dao.deletarTipo(id);
    }

    public void iniciaAtividade(int idTipo, Funcionario funcionario) throws ClassNotFoundException, SQLException {
        AtividadeDAO dao = new AtividadeDAO();
        dao.criaAtividade(idTipo, funcionario);
    }

    public void encerraAtividade(Funcionario funcionario) throws ClassNotFoundException, SQLException {
        AtividadeDAO dao = new AtividadeDAO();
        dao.encerraAtividade(funcionario);
    }

    public Atividade listaAtividadeIniciada(Funcionario func) throws ClassNotFoundException, SQLException {
        AtividadeDAO dao = new AtividadeDAO();
        return dao.getAtividadeIniciada(func);
    }

    public Funcionario getfuncionarioID(int idFunc) throws ClassNotFoundException, SQLException {
        Client client = ClientBuilder.newClient();
        Funcionario func = client.target("http://localhost:8084/SistemaRHINDO/webresources/funcionarios/" + idFunc)
                .request(MediaType.APPLICATION_JSON).get(Funcionario.class);
        return func;
    }

    public void encerraTudoPorTipo(int idTipo) throws ClassNotFoundException, SQLException {
        AtividadeDAO dao = new AtividadeDAO();
        dao.encerraTudo(idTipo);
    }

    public void solicitaCorrecao(Atividade atividade) throws ClassNotFoundException, SQLException {
        EdicaoAtividadeDAO dao = new EdicaoAtividadeDAO();
        dao.insereCorrecao(atividade);
    }
    
    public void listaEdicaoAtividade(Atividade atividade) throws ClassNotFoundException, SQLException {
        EdicaoAtividadeDAO dao = new EdicaoAtividadeDAO();
        dao.listaEdicoes();
    }
    public boolean verificaFunc(int idTipo) throws ClassNotFoundException, SQLException {
        AtividadeDAO dao = new AtividadeDAO();
       return dao.verificaFuncionario(idTipo);
    }

    public void insereFuncTemp(Funcionario func) {
        AtividadeDAO dao = new AtividadeDAO();
        dao.insereFuncTemporario(func);
        }
}
