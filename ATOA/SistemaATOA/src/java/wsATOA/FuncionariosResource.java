/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsATOA;

import beans.Departamento;
import beans.Funcionario;
import beans.HorasTrabalhadas;
import facede.Facade;
import java.sql.SQLException;
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
     * Retrieves representation of an instance of wsATOA.FuncionariosResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{mes}")
    public Response getFunc(@PathParam("mes") String mes) throws ClassNotFoundException, SQLException {
        Facade facade = new Facade();
        List<Funcionario> funcs = facade.getHorasAtrasadasPorFunc(mes);
        GenericEntity<List<Funcionario>> lista = new GenericEntity<List<Funcionario>>(funcs) {
        };
        return Response
                .ok()
                .entity(lista)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/horasTrabalhadas/{mesde}/{mesate}/{idFunc}")
    public Response getHoras(@PathParam("mesde") String mesDe,
                            @PathParam("mesate") String mesAte,
                            @PathParam("idFunc") int idFunc) throws ClassNotFoundException, SQLException {
        Facade facade = new Facade();
        Funcionario func = facade.getfuncionarioID(idFunc);
        List<HorasTrabalhadas> horas = facade.getHorasPorMes(mesDe, mesAte, func);
        GenericEntity<List<HorasTrabalhadas>> lista = new GenericEntity<List<HorasTrabalhadas>>(horas) {
        };
        return Response
                .ok()
                .entity(lista)
                .build();
    }
    
    /**
     * PUT method for updating or creating an instance of FuncionariosResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
