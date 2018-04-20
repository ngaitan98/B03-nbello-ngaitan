package rest;

import java.io.File;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohandesTransactionManager;
import vos.Cliente;
import vos.ListaCliente;

@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteService 
{
	@Context
	private ServletContext context;
	
	private String getPath() {
		if(new File(context.getContextPath()).exists())
		{
			System.out.println("@@@@@@@@@ Existe la ruta @@@@@@@@");
		}
		if(new File(context.getContextPath() +"/conexion.properties").exists())
		{
			System.out.println("@@@@@@@@@ Existe el file @@@@@@@@");
		}
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	private String doErrorMessage (Exception e) {
		return "{\"ERROR\": \"" + e.getMessage() + "\"}";
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCliente( Cliente cliente) {
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			tm.addCliente(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}
}
