package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import vos.*;

public class DAOJoins 
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	public final static String USUARIO = "ISIS2304A471810";

	/**
	 * Atributo que genera la conexion a la base de datos
	 */
	private Connection conn;
	/**
	 * Arraylits de recursos que se usan para la ejecucion de sentencias SQL
	 */
	private ArrayList<Object> recursos;
	/**
	 * Referencias a los otros DAOs
	 */
	private DAOHotel hoteles;
	private DAOAlojamiento alojamientos;
	private DAOCliente clientes;
	private DAOContrato contratos;
	private DAOServicio servicios;

	public DAOJoins()
	{
		hoteles = new DAOHotel();
		alojamientos = new DAOAlojamiento();
		clientes = new DAOCliente();
		contratos = new DAOContrato();
		servicios = new DAOServicio();
		recursos = new ArrayList<Object>();
	}
	public void agregarHotel(Hotel h) throws SQLException, Exception
	{
		hoteles.addHotel(h);
	}
	public void agregarAlojamiento(Long idOperador, Alojamiento a) throws SQLException, Exception
	{
		//TODO completar este m�todo para los dem�s DAOs y sus casos, yo me encargo de la l�gica
		ResultSet h = hoteles.findHotelById(idOperador);
		if(h.next())
		{
			alojamientos.addAlojamiento(a);
			String sql = String.format("INSERT INTO %1s.OFRECEN VALUES (%2$s, %3$s, '%4s')", USUARIO, a.getId(), idOperador, "HOTELES");
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		else
		{
			throw new Exception("No existe el operador con el id " + idOperador);
		}
	}
	public void agregarServicio(Long idAlojamiento, Servicio s) throws SQLException, Exception
	{
		ResultSet a = alojamientos.findAlojamientoById(idAlojamiento);
		if(a.next())
		{
			servicios.addServicio(s);
			String sql = String.format("INSERT INTO %1$s.TIENEN VALUES (%2$s, %3$s)", USUARIO, idAlojamiento, s.getId());
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		else
		{
			throw new Exception("No existe el operador con el id " + idAlojamiento);
		}
	}
	public void agregarCliente(Cliente c) throws SQLException, Exception
	{
		clientes.addCliente(c);
	}
	public void agregarContrato(Long idCliente, Long idAlojamiento, Contrato con) throws SQLException, Exception
	{
		ResultSet a = alojamientos.findAlojamientoById(idAlojamiento);
		ResultSet c = clientes.findClienteById(idCliente);
		if(a.next() && c.next())
		{
			contratos.addContrato(con);
			String sql = String.format("INSERT INTO %1s.CONTRATARON VALUES (%2$s, %3$s, %4$s)", USUARIO, con.getId(), idAlojamiento, idCliente);
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
	}
	public void ocuparAlojamiento(Long id) throws SQLException
	{
		alojamientos.alojar(id);
	}
	public void desOcuparAlojamiento(Long id) throws SQLException
	{
		alojamientos.desAlojar(id);
	}
	public void finalizarContrato(Long id, Double precio) throws SQLException
	{
		contratos.finalizar(id, precio);
	}
	public boolean existeLoginCliente(String login) throws SQLException, Exception
	{
		return clientes.findClienteByLogin(login).next();
	}
	public boolean existeCorreoCliente(String correo) throws SQLException, Exception
	{
		return clientes.findClienteByCorreo(correo).next();
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS AUXILIARES
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo encargado de inicializar la conexion del DAO a la Base de Datos a partir del parametro <br/>
	 * <b>Postcondicion: </b> el atributo conn es inicializado <br/>
	 * @param connection la conexion generada en el TransactionManager para la comunicacion con la Base de Datos
	 */
	public void setConn(Connection connection){
		this.conn = connection;
		alojamientos.setConn(conn);
		servicios.setConn(conn);
		hoteles.setConn(conn);
		clientes.setConn(conn);
		contratos.setConn(conn);
	}

	/**
	 * Metodo que cierra todos los recursos que se encuentran en el arreglo de recursos<br/>
	 * <b>Postcondicion: </b> Todos los recurso del arreglo de recursos han sido cerrados.
	 */
	public void cerrarRecursos() {
		alojamientos.cerrarRecursos();
		servicios.cerrarRecursos();
		hoteles.cerrarRecursos();
		clientes.cerrarRecursos();
		contratos.cerrarRecursos();
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}
	public Long getCurrentId() throws SQLException, Exception
	{
		String sql = String.format("SELECT MAX(ID) FROM %1$s.OFRECEN", USUARIO);
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet s = prepStmt.executeQuery();	
		if(s.next())
		{
			return s.getLong(1);
		}
		return (long) 0;
	}
}
