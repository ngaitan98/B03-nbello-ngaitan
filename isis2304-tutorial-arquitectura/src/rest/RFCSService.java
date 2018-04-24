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
	@Path("dineroproveedores")
	@Produces({MediaType.TEXT_PLAIN})
	public Response dineroProveedores()
	{
		String resp ="";
		System.out.println("a1");
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			 resp =tm.getdineroProveedores();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		System.out.println("a2");
		return Response.status(200).entity(resp).build();
	}
	@GET 
	@Path("ofertaspopulares")
	@Produces({MediaType.APPLICATION_JSON})
	public Response ofertasPopulares()
	{
		System.out.println("b1");
		String resp = "";
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			tm.getOfertasPopulares();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		System.out.println("b2");
		return Response.status(200).build();
	}
	@GET 
	@Path("indiceAlojamiento")
	@Produces({MediaType.APPLICATION_JSON})
	public Response IndiceAlojamiento()
	{
		System.out.println("c1");
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			 tm.getIndiceAlojamiento();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		System.out.println("c2");
		return Response.status(200).build();
		
	}
	@GET 
	@Path("alojamientosDisponibles")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response alojamientosDisponibles(String fechainicio, String fechafin, String servicio)
	{
		System.out.println("d1");
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			 tm.getAlojamientosDisponibles(fechainicio, fechafin,servicio);
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		System.out.println("d2");
		return Response.status(200).build();
	}
	
	@GET 
	@Path("usoalhoandesoperarios")
	@Produces({MediaType.APPLICATION_JSON})
	public Response usoAlhoAndesOperarios()
	{
		System.out.println("e1");
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			 tm.getUsoAlhoAndesOperarios();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		System.out.println("e2");
		return Response.status(200).build();
	}
	
	@GET 
	@Path ("usoalhoandesclientes")
	@Produces({MediaType.APPLICATION_JSON})
	public Response usoAlhoAndesClientes()
	{
		System.out.println("f1");
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			 tm.getUsoAlhoAndesClilentes();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		System.out.println("f2");
		return Response.status(200).build();
	}
	@GET 
	@Path ("clientesfrecuentes/{idAlojamiento}")
	public Response clientesFrecuentes (@javax.ws.rs.PathParam("idAlojamiento")Long id_alojamiento)
	{
		System.out.println("g1");
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			 tm.getClientesFrecuentes(id_alojamiento);
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		System.out.println("g2");
		return Response.status(200).build();
	}
}
