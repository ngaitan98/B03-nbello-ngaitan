package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Hostal;
import vos.PersonaNormal;

public class DAOPersonaNormal {

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
	 * Metodo constructor de la clase DAOHostal <br/>
	 */
	public DAOPersonaNormal() 
	{
		recursos = new ArrayList<Object>();
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE COMUNICACION CON LA BASE DE DATOS
	//----------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Metodo que agrega la informacion de un nuevo personanormal a la base de datos a partir de un parametro que ingresa
	 * <br>
	 * <b> Pre:</b> la conexion a sido inicializada
	 * </br>
	 * @param personanormal personanormal que se desea ingresar 
	 * @throws SQLException SQLExcaption Se lanza una SQLException si hay un error en la base de datos o en la sentencia SQL
	 * @throws Exception Exception si se genera un error dentro del metodo.
	 */
	public void addPersonaNormal (PersonaNormal personanormal) throws SQLException, Exception
	{
		String sentencia = String.format("INSERT INTO %1$s.HOSTALES (ID, LOGIN, PASSWORD, NOMBRE, CUENTABANCARIA, CORREO, IDENTIFICACION, TIPOPERSONANORMAL, CERTIFICADO) "
				+ "VALUES (%2$s, '%3$s', '%4$s', '%5$s', '%6$s', '%7$s', '%8$s','%9$s', %10$s')", 
				USUARIO,
				personanormal.getId(),personanormal.getLogin(),personanormal.getPassword(),personanormal.getNombre(),personanormal.getCuentaBancaria(),personanormal.getCorreo(),personanormal.getDocumento(),personanormal.getTipo(), "Pendiente de aprobación");
		System.out.println(sentencia);
		PreparedStatement prepStmt = conn.prepareStatement(sentencia);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	/**
	 * metodo que obtiene la informacion de un personanormal que se encuentra en la base de datos que tiene el identificador dado por parametro.
	 * <br>
	 * <b> Pre: </b> la conexion ha sido inicializada
	 * </br>
	 * @param id id del personanormal del cual se quiere obtener la informacion
	 * @return un ResultSet que contiene la informacion del personanormal que cumple los criterios de la sentencia SQL
	 * @throws SQLException SQLException se lanza cuando existe un error en la conexion con la base de datos o en la sentencia SQL
	 * @throws Exception Exception que se lanza si hay errores en el codigo.
	 */
	
	public ResultSet findPersonaNormalById (Long id) throws SQLException, Exception
	{
		String sentencia = String.format("SELECT * FROM %1$s.PERSONASNORMALES WHERE ID = %2$d", USUARIO, id); 
		PreparedStatement prepStmt = conn.prepareStatement(sentencia);
		recursos.add(prepStmt);
		ResultSet result = prepStmt.executeQuery();
		return result;
	}
	
	/**
	 * Metodo que elimina un personanormal que tenga el id que llega por parametro de la base de datos
	 * <br>
	 * <b>Pre:</b> la conexion debe haber sido inicializada
	 * </br>
	 * @param id Id del personanormal que se desea eliminar
	 * @throws SQLException SQLException que se lanza cuando hay error de conexion con la base de datos o en la sentencia SQL
	 * @throws Exception Exception que se lanza que cuando hay errores del codigo
	 */
	
	public void deletePersonaNormal(Long id) throws SQLException, Exception
	{
		String sentencia = String.format("DELETE FROM %1$s.PERSONASNORMALES WHERE ID = %2$d", USUARIO, id); 
		
		System.out.println(sentencia);
		PreparedStatement prepStmt = conn.prepareStatement(sentencia);
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
