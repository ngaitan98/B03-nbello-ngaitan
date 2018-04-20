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
import vos.Hotel;
import vos.ListaAlojamientos;
import vos.ListaHotel;

@Path("/hoteles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HotelService {

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
	public Response getHoteles()
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		ListaHotel hoteles;
		try {
			hoteles = tm.darHoteles();
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(hoteles).build();
	}
	@GET 
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getHotel(@javax.ws.rs.PathParam("id") String id)
	{
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		Hotel hoteles;
		try {
			hoteles = tm.darHotel(id);
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(hoteles).build();
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addHotel(Hotel hoteles) {
		AlohandesTransactionManager tm = new AlohandesTransactionManager(getPath());
		try {
			tm.addHotel(hoteles);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(hoteles).build();
	}
}
