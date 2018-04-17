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
import vos.ListaAlojamientos;
import vos.ListaPersonaNormal;
import vos.PersonaNormal;

@Path ("/personasnormales")
public class PersonaNormalService {

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
	public Response getPersonasNormales()
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		ListaPersonaNormal personasnormales;
		try {
			personasnormales = tm.darPersonasNormales();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(personasnormales).build();
	}
	@GET 
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getPersonaNormal(@javax.ws.rs.PathParam("id") String id)
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		PersonaNormal personasnormales;
		try {
			personasnormales = tm.darPersonaNormal(id);
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(personasnormales).build();
	}
	@PUT
	@Path("/personanormal")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPersonaNormal( PersonaNormal personasnormales) {
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			tm.addPersona(personasnormales);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(personasnormales).build();
	}
}
