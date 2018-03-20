package dao;

import java.io.File;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.*;

public class DAOHotel 
{
	public final static String USUARIO = "ISIS2304A471810";

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Arraylits de recursos que se usan para la ejecucion de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexion a la base de datos
	 */
	private Connection conn;

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE INICIALIZACION
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo constructor de la clase DaoHotel <br/>
	 */
	public DAOHotel() 
	{
		recursos = new ArrayList<Object>();
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE COMUNICACION CON LA BASE DE DATOS
	//----------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Metodo que agregar la informacion de un nuevo hotel en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param hotel hotel que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addHotel(Hotel hotel) throws SQLException, Exception 
	{
		String sql = String.format("INSERT INTO %1$s.HOTELES (ID, LOGIN, PASSWORD, NOMBRE, CUENTABANCARIA, CORREO, IDENTIFICACION) "
				+ "VALUES (%2$s, '%3$s', '%4$s', '%5$s', '%6s', '%7s', '%8s')", 
				USUARIO, 
				hotel.getId(),
				hotel.getLogin(),
				hotel.getPassword(),
				hotel.getNombre(),
				hotel.getCuentaBancaria(),
				hotel.getCorreo(),
				hotel.getDocumento());
		
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	/**
	 * Metodo que obtiene la informacion del hotel en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
	 * @param id el identificador del hotel
	 * @return la informacion del hotel que cumple con los criterios de la sentecia SQL
	 * 			Null si no existe el hotel con los criterios establecidos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public Hotel findHotelById(Long id) throws SQLException, Exception 
	{
		Hotel hotel = null;

		String sql = String.format("SELECT * FROM %1$s.HOTELES WHERE ID = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) 
		{
			String login = rs.getString(2);
			String password = rs.getString(3);
			String nombre = rs.getString(4);
			String cuentaBancaria = rs.getString(5);
			String correo = rs.getString(6);
			String documento = rs.getString(7);
			hotel = new Hotel(id, login, nombre, correo, password, cuentaBancaria, documento, null, new ArrayList<Alojamiento>(), new ArrayList<File>());
		}
		return hotel;
	}
	/**
	 * Metodo que elimina a un hotel por el id que llega por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param id Id del hotel que se desea eliminar
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void deleteHotel(Long id) throws SQLException, Exception 
	{

		String sql = String.format("DELETE FROM %1$s.HOTELES WHERE ID = %2$d", USUARIO, id);

		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
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
	}

	/**
	 * Metodo que cierra todos los recursos que se encuentran en el arreglo de recursos<br/>
	 * <b>Postcondicion: </b> Todos los recurso del arreglo de recursos han sido cerrados.
	 */
	public void cerrarRecursos() {
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}
}
