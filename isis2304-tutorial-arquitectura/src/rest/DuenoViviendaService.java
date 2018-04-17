package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohandesTransactionManager;
import vos.Alojamiento;
import vos.DuenoVivienda;
import vos.ListaAlojamientos;
import vos.ListaDuenoVivienda;

@Path ("/duenoviviendas")
public class DuenoViviendaService 
{
	@Context
	private ServletContext context;
	
	private String getPath() {
		return context.getRealPath("WEB_INF/ConnectionData");
	}
	private String doErrorMessage (Exception e) {
		return "{\"ERROR\": \"" + e.getMessage() + "\"}";
	}
	@GET 
	@Produces({MediaType.APPLICATION_JSON})
	public Response getDuenosviviendas()
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		ListaDuenoVivienda duenosviviendas;
		try {
			duenosviviendas = tm.darDuenosViviendas();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(duenosviviendas).build();
	}
	@GET 
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getDuenoVivienda(@javax.ws.rs.PathParam("id") String id)
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		DuenoVivienda duenosviviendas;
		try {
			duenosviviendas = tm.darDuenoVivienda(id);
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(duenosviviendas).build();
	}
	@PUT
	@Path("/duenovivienda")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addDuenoVivienda(DuenoVivienda duenosviviendas) {
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			tm.addDueno(duenosviviendas);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(duenosviviendas).build();
	}

}
