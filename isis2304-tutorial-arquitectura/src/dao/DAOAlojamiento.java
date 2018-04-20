package dao;

import java.io.File;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.*;

public class DAOAlojamiento 
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
	 * Metodo constructor de la clase DAOAlojamiento <br/>
	 */
	public DAOAlojamiento() 
	{
		recursos = new ArrayList<Object>();
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE COMUNICACION CON LA BASE DE DATOS
	//----------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Metodo que agregar la informacion de un nuevo alojamiento en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param alojamiento hotel que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addAlojamiento(Alojamiento alojamiento) throws SQLException, Exception 
	{
		System.out.println(alojamiento.getOcupado().toString().trim());
		System.out.println(alojamiento.getUbicacion());
		String sql = String.format("INSERT INTO %1$s.ALOJAMIENTOS (ID, NUMERODECUPOS, TIPOALOJAMIENTO, COSTOBASE, DIRECCION, OCUPADA)"
				+ "VALUES (%2$s, %3$s, '%4$s', %5$s, '%6$s', '%7$s')", 
				USUARIO, 
				alojamiento.getId(),
				alojamiento.getNumeroCupos(),
				alojamiento.getTipo(),
				alojamiento.getCostoBase(),
				alojamiento.getUbicacion(),
				alojamiento.getOcupado().toString().charAt(alojamiento.getOcupado().toString().length() - 1));

				System.out.println(sql);
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
				System.out.println("agrega");
	}
	/**
	 * Metodo que obtiene la informacion del hotel en la Base de Datos que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/> 
	 * @param id el identificador del hotel
	 * @return la informacion del hotel que cumple con los criterios de la sentecia SQL
	 * 			Null si no existe el alojamiento conlos criterios establecidos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ResultSet findAlojamientoById(Long id) throws SQLException, Exception 
	{

		String sql = String.format("SELECT * FROM %1$s.ALOJAMIENTOS WHERE ID = %2$s", USUARIO, id.toString()); 
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		return rs;
	}
	/**
	 * Metodo que elimina a un alojamiento por el id que llega por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param id Id del alojamiento que se desea eliminar
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void deleteAlojamiento(Long id) throws SQLException, Exception 
	{

		String sql = String.format("DELETE FROM %1$s.ALOJAMIENTOS WHERE ID = %2$d", USUARIO, id);

		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	public void alojar(Long id) throws SQLException
	{
		String sql = String.format("UPDATE %1$s.ALOJAMIENTOS SET OCUPADO = 1 WHERE ID = '%2$d'", USUARIO, id);
		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	public void desAlojar(Long id) throws SQLException
	{
		String sql = String.format("UPDATE %1$s.ALOJAMIENTOS SET OCUPADO = 0 WHERE ID = '%2$d'", USUARIO, id);
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
