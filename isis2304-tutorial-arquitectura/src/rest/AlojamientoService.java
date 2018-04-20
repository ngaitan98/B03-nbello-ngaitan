package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohandesTransactionManager;
import vos.Alojamiento;
import vos.ListaAlojamientos;

@Path("/operadores/{idOperador}/alojamientos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlojamientoService 
{
	@Context
	private ServletContext context;
	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	private String doErrorMessage (Exception e) {
		return "{\"ERROR\": \"" + e.getMessage() + "\"}";
	}
	@GET 
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAlojamientos()
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		ListaAlojamientos alojamientos;
		try {
			alojamientos = tm.darAlojamientos();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(alojamientos).build();
	}
	@GET 
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAlojamiento(@javax.ws.rs.PathParam("id") String id)
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		Alojamiento alojamientos;
		try {
			alojamientos = tm.darAlojamiento(id);
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(alojamientos).build();
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAlojamiento(@javax.ws.rs.PathParam("idOperador")Long IdOperador, Alojamiento alojamiento) {
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			tm.addAlojamiento(IdOperador,alojamiento);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(alojamiento).build();
	}
}
