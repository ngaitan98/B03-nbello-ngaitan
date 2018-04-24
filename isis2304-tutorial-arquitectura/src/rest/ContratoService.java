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
import vos.Contrato;
import vos.ListaAlojamientos;
import vos.ListaContrato;

@Path("/clientes/{idCliente}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContratoService {

	@Context
	private ServletContext context;
	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	private String doErrorMessage (Exception e) {
		return "{\"ERROR\": \"" + e.getMessage() + "\"}";
	}
	
	@POST
	@Path("/alojamientos/{idAlojamiento}/contratos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addContrato(@javax.ws.rs.PathParam("idCliente")Long idCliente, @javax.ws.rs.PathParam("idAlojamiento") Long idAlojamiento, Contrato contrato) {
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			tm.addContrato(idCliente, idAlojamiento, contrato);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e) + "Hola").build();
		}
		return Response.status(200).entity(contrato).build();
	}
	@PUT
	@Path("/contratos/{idContrato}")
	public Response cancelarContrato(@javax.ws.rs.PathParam("idContrato") Long id)
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			tm.cancelarReserva(id);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e) + "Hola").build();
		}
		return Response.status(200).build();
	}
}
