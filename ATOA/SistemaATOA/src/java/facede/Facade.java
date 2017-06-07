/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facede;

import beans.Departamento;
import beans.TipoAtividade;
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

    public List<TipoAtividade> listaAtividades() throws ClassNotFoundException, SQLException {
        TipoAtividadeDAO dao = new TipoAtividadeDAO();
        return dao.listaTipos();
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

}