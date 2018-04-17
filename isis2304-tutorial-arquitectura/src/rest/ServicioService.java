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
import vos.ListaServicio;
import vos.Servicio;

@Path("servicios")
public class ServicioService {
	
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
	public Response getServicios()
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		ListaServicio servicios;
		try {
			servicios = tm.darServicios();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(servicios).build();
	}
	@GET 
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getServicio(@javax.ws.rs.PathParam("id") String id)
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		Servicio servicios;
		try {
			servicios = tm.darServicio(id);
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(servicios).build();
	}
	@PUT
	@Path("/usuario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addServicio(Long idAlojamiento, Servicio servicios) {
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			tm.addServicio(idAlojamiento, servicios);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(servicios).build();
	}

}
