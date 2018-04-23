package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohandesTransactionManager;

@Path("/rfcs/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RFCSService {
	
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
	public Response dineroProveedores()
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			 tm.getdineroProveedores();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).build();
	}
	@GET 
	@Produces({MediaType.APPLICATION_JSON})
	public Response ofertasPopulares()
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			 tm.getOfertasPopulares();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).build();
	}
	@GET 
	@Produces({MediaType.APPLICATION_JSON})
	public Response IndiceAlojamiento()
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			 tm.getIndiceAlojamiento();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).build();
	}
	@GET 
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response alojamientosDisponibles(String fechainicio, String fechafin, String servicio)
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			 tm.getAlojamientosDisponibles(fechainicio, fechafin,servicio);
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).build();
	}
	
	@GET 
	@Produces({MediaType.APPLICATION_JSON})
	public Response usoAlhoAndesOperarios()
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			 tm.getUsoAlhoAndesOperarios();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).build();
	}
	
	@GET 
	@Produces({MediaType.APPLICATION_JSON})
	public Response usoAlhoAndesClientes()
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			 tm.getUsoAlhoAndesClilentes();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).build();
	}
}
