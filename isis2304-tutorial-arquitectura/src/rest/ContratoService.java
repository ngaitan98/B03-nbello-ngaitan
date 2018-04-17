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
import vos.Contrato;
import vos.ListaAlojamientos;
import vos.ListaContrato;

@Path("/contratos")
public class ContratoService {

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
	public Response getContratos()
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		ListaContrato contratos;
		try {
			contratos = tm.darContratos();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(contratos).build();
	}
	@GET 
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getContrato(@javax.ws.rs.PathParam("id") String id)
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		Contrato contratos;
		try {
			contratos = tm.darContrato(id);
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(contratos).build();
	}
	
	@PUT
	@Path("/contrato")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addContrato(Long idCliente, Long idAlojamiento, Contrato contrato) {
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			tm.addContrato(idCliente, idAlojamiento, contrato);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(contrato).build();
	}
}
