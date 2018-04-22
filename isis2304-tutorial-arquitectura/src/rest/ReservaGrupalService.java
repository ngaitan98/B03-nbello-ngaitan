package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohandesTransactionManager;
import vos.Contrato;
import vos.ReservaGrupal;

@Path("/ReservasGrupales")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservaGrupalService {
	
	@Context
	private ServletContext context;
	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	private String doErrorMessage (Exception e) {
		return "{\"ERROR\": \"" + e.getMessage() + "\"}";
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addContrato( ReservaGrupal reservagrupal) {
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			tm.addReservaGrupal(reservagrupal);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reservagrupal).build();
	}
	@PUT
	@Path("/reservagrupales/{idreservagrupal}")
	public Response cancelarContrato(@javax.ws.rs.PathParam("idReservaGrupal") Long id)
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			tm.cancelarReservaGrupal(id);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e) + "Hola").build();
		}
		return Response.status(200).build();
		
	}
}
