package rest;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohandesTransactionManager;
import vos.Cliente;

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
	@Produces({MediaType.TEXT_PLAIN})
	public Response ofertasPopulares()
	{
		System.out.println("b1");
		String resp = "";
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			resp =tm.getOfertasPopulares();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		System.out.println(resp);
		System.out.println("b2");
		return Response.status(200).entity(resp).build();
	}
	@GET 
	@Path("indicealojamiento")
	@Produces({MediaType.TEXT_PLAIN})
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
	@Path("alojamientosdisponibles")
	@Produces({MediaType.TEXT_PLAIN})
	public Response alojamientosDisponibles()
	{
		String fechainicio = "23/10/17";
		String fechafin = "23/10/30";
		String servicio = "gimnasio";
		String resp ="";
		System.out.println("d1");
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			resp= tm.getAlojamientosDisponibles(fechainicio, fechafin,servicio);
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		System.out.println("d2");
		return Response.status(200).entity(resp).build();
	}

	@GET 
	@Path("usoalhoandesoperarios")
	@Produces({MediaType.TEXT_PLAIN})
	public Response usoAlhoAndesOperarios()
	{
		System.out.println("e1");
		String resp = "";
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			resp =tm.getUsoAlhoAndesOperarios();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		System.out.println("e2");
		return Response.status(200).entity(resp).build();
	}

	@GET 
	@Path ("usoalhoandesclientes")
	@Produces({MediaType.TEXT_PLAIN})
	public Response usoAlhoAndesClientes()
	{
		System.out.println("f1");
		String resp = "";
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			resp = tm.getUsoAlhoAndesClilentes();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		System.out.println("f2");
		return Response.status(200).entity(resp).build();
	}
	@GET 
	@Path ("clientesfrecuentes/{idAlojamiento}")
	public Response clientesFrecuentes (@PathParam("idAlojamiento")Long id_alojamiento)
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			tm.getClientesFrecuentes(id_alojamiento);
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).build();
	}
	@GET 
	@Path ("consumoAlohandes/{idAlojamiento}/from/{fi}/to/{ff}/orderingBy/{obp}")
	public Response consumoAlojandes (@PathParam("idAlojamiento")Long id_alojamiento, @PathParam("fi")String fechaInicio, @PathParam("ff")String fechaFin, @PathParam("obp")String orderByParams)
	{
		System.out.println("g1");
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			ArrayList<Cliente> clientes = tm.consumoEnAlojandes(id_alojamiento, fechaInicio, fechaFin, orderByParams);
			for(Cliente c  :clientes)
			{
				Response.status(200).entity(c).build();
			}
			return Response.status(200).build();

		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	@GET 
	@Path ("consumoAlohandes2/{idAlojamiento}/from/{fi}/to/{ff}/orderingBy/{obp}")
	public Response consumoAlojandes2 (@PathParam("idAlojamiento")Long id_alojamiento, @PathParam("fi")String fechaInicio, @PathParam("ff")String fechaFin, @PathParam("obp")String orderByParams)
	{
		System.out.println("g1");
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			ArrayList<Cliente> clientes = tm.consumoEnAlojandes2(id_alojamiento, fechaInicio, fechaFin, orderByParams);
			for(Cliente c  :clientes)
			{
				Response.status(200).entity(c).build();
			}
			return Response.status(200).build();

		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
}
