/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsRHINDO;

import beans.Funcionario;
import daos.FuncionarioDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author JotaWind
 */
@Path("funcionarios")
public class FuncionariosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FuncionariosResource
     */
    public FuncionariosResource() {
    }

    /**
     * Retrieves representation of an instance of wsRHINDO.FuncionariosResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList();
        FuncionarioDAO daoF = new FuncionarioDAO();

        try {
            funcionarios = daoF.listaFuncionarios();
        } catch (Exception e) {}

        GenericEntity<List<Funcionario>> lista
                = new GenericEntity<List<Funcionario>>(funcionarios) {
        };

        return Response
                .ok()
                .entity(lista)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Funcionario getFuncionario(@PathParam("id") int id) {
        Funcionario f = new Funcionario();
        FuncionarioDAO daoFunc = new FuncionarioDAO();

        try {
            f = daoFunc.buscaFuncPorID(id);
        } catch (Exception e) {

        }
        return f;
    }

    /**
     * PUT method for updating or creating an instance of FuncionariosResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
