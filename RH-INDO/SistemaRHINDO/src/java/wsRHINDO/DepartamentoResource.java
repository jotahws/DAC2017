/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsRHINDO;

import beans.Departamento;
import facade.Facade;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
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
@Path("departamento")
public class DepartamentoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DepartamentoResource
     */
    public DepartamentoResource() {
    }

    /**
     * Retrieves representation of an instance of wsRHINDO.DepartamentoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepto() throws ClassNotFoundException, SQLException {
        Facade facade = new Facade();
        List<Departamento> deptos = facade.carregaListaDeptos();
        GenericEntity<List<Departamento>> lista = new GenericEntity<List<Departamento>>(deptos) {
        };
        return Response
                .ok()
                .entity(lista)
                .build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{idDepto}")
    public Departamento getDeptoPorID(@PathParam("idDepto") int idDepto) throws ClassNotFoundException, SQLException {
        Facade facade = new Facade();
        return facade.buscaDeptoPorID(idDepto);
    }    
}
