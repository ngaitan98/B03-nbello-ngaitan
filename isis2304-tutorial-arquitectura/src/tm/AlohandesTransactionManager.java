package tm;

import java.io.File;



import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import dao.DAOJoins;
import vos.*;

/**
 * Clase que representa al Manejador de Transacciones de la Aplicacion (Fachada en patron singleton de la aplicacion)
 * Responsabilidades de la clase: 
 * 		Intermediario entre los servicios REST de la aplicacion y la comunicacion con la Base de Datos
 * 		Modelar y manejar autonomamente las transacciones y las reglas de negocio.
 */
public class AlohandesTransactionManager 
{

	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTANTES
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Constante que contiene el path relativo del archivo que tiene los datos de la conexion
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo estatico que contiene el path absoluto del archivo que tiene los datos de la conexion
	 */
	private static String connectionDataPath;

	/**
	 * Constatne que representa el numero maximo de Bebedores que pueden haber en una ciudad
	 */
	private final static Integer CANTIDAD_MAXIMA = 345;

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
	 */
	private String user;

	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
	 */
	private String driver;

	/**
	 * Atributo que representa la conexion a la base de datos
	 */
	private Connection conn;
	/**
	 * Los DAOs necesarios para realizar transaccion
	 */
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE CONEXION E INICIALIZACION
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * <b>Metodo Contructor de la Clase AlohandesTransactionManager</b> <br/>
	 * <b>Postcondicion: </b>	Se crea un objeto  AlohandesTransactionManager,
	 * 						 	Se inicializa el path absoluto del archivo de conexion,
	 * 							Se inicializna los atributos para la conexion con la Base de Datos
	 * @param contextPathP Path absoluto que se encuentra en el servidor del contexto del deploy actual
	 * @throws IOException Se genera una excepcion al tener dificultades con la inicializacion de la conexion<br/>
	 * @throws ClassNotFoundException 
	 */
	public AlohandesTransactionManager(String contextPathP) {

		try {
			connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
			initializeConnectionData();
		} 
		catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo encargado de inicializar los atributos utilizados para la conexion con la Base de Datos.<br/>
	 * <b>post: </b> Se inicializan los atributos para la conexion<br/>
	 * @throws IOException Se genera una excepcion al no encontrar el archivo o al tener dificultades durante su lectura<br/>
	 * @throws ClassNotFoundException 
	 */
	private void initializeConnectionData() throws IOException, ClassNotFoundException {

		FileInputStream fileInputStream = new FileInputStream(new File(this.connectionDataPath));
		Properties properties = new Properties();

		properties.load(fileInputStream);
		fileInputStream.close();

		this.url = properties.getProperty("url");
		this.user = properties.getProperty("usuario");
		this.password = properties.getProperty("clave");
		this.driver = properties.getProperty("driver");

		//Class.forName(driver);
	}

	/**
	 * Metodo encargado de generar una conexion con la Base de Datos.<br/>
	 * <b>Precondicion: </b>Los atributos para la conexion con la Base de Datos han sido inicializados<br/>
	 * @return Objeto Connection, el cual hace referencia a la conexion a la base de datos
	 * @throws SQLException Cualquier error que se pueda llegar a generar durante la conexion a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("[ALOHANDES APP] Attempting Connection to: " + url + " - By User: " + user);
		return DriverManager.getConnection(url, user, password);
	}


	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS TRANSACCIONALES
	//----------------------------------------------------------------------------------------------------------------------------------

	public void addHotel(Hotel hotel) throws Exception 
	{
		DAOJoins joins = null;;
		try
		{
			this.conn = darConexion();
			joins = new DAOJoins();
			joins.setConn(this.conn);
			hotel.setId(joins.getCurrentIdOperador());
			joins.agregarHotel(hotel);
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				joins.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public void addPersona(PersonaNormal persona) throws Exception 
	{
		DAOJoins joins = null;
		try
		{
			joins = new DAOJoins( );
			this.conn = darConexion();
			joins.setConn(this.conn);
			persona.setId(joins.getCurrentIdOperador());
			joins.agregarPersonaNormal(persona);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				joins.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public void addDueno(DuenoVivienda dueno) throws Exception 
	{
		DAOJoins joins = null;
		try
		{			
			this.conn = darConexion();
			joins = new DAOJoins( );
			joins.setConn(this.conn);
			if(joins.existeOperador(dueno.getId()))
			{
				throw new Exception("Ya existe un operador con el id " + dueno.getId());
			}
		}

		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				dueno.setId(joins.getCurrentIdOperador());
				joins.agregarDuenoVivienda(dueno);
				joins.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public void addHostal(Hostal hostal) throws Exception 
	{

		DAOJoins joins = new DAOJoins( );
		if(joins.existeOperador(hostal.getId()))
		{
			throw new Exception("Ya existe un operador con el id " + hostal.getId());
		}
		try
		{
			//TODO Requerimiento 3D: Obtenga la conexion a la Base de Datos (revise los metodos de la clase)
			this.conn = darConexion();
			//TODO Requerimiento 3E: Establezca la conexion en el objeto DAOJoins (revise los metodos de la clase DAOJoins)
			joins.setConn(this.conn);
			joins.agregarHostal(hostal);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				joins.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public void addAlojamiento(Long idOperador, Alojamiento a) throws Exception 
	{
		DAOJoins joins = new DAOJoins( );
		try
		{
			//TODO Requerimiento 3D: Obtenga la conexion a la Base de Datos (revise los metodos de la clase)
			this.conn = darConexion();
			//TODO Requerimiento 3E: Establezca la conexion en el objeto DAOJoins (revise los metodos de la clase DAOJoins)
			joins.setConn(this.conn);
			joins.agregarAlojamiento(idOperador, a);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				joins.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}

	}

	public void addContrato(Long idCliente,Long idAlojamiento, Contrato contrato) throws Exception 
	{
		Date fi = parseDateTime(contrato.getFechainicio());
		Date ff = parseDateTime(contrato.getFechafin());
		Date fc = getCurrentDate();
		System.out.println(fi);
		System.out.println(ff);
		System.out.println(fc);
		contrato.setFechaCreacion(fc.toString());
		contrato.setFechainicio(fi.toString());
		contrato.setFechafin(ff.toString());

		DAOJoins joins = null;
		try{
			joins = new DAOJoins( );
			this.conn = darConexion();
			//TODO Requerimiento 3E: Establezca la conexion en el objeto DAOJoins (revise los metodos de la clase DAOJoins)
			joins.setConn(this.conn);
			
			if(diferenciaDias(fc, fi) > 0)
			{
				System.err.println("[EXCEPTION] Logic Exception:"   + "la fecha de inicio deber�a ser después de la fecha de creacion del contrato." + diferenciaDias(fi, ff));
				throw new Exception("la fecha de inicio deber�a ser ant�s de la fecha final.");
			}
			if(diferenciaDias(fi, ff) < 0)
			{
				System.err.println("[EXCEPTION] Logic Exception:"   + "la fecha de inicio deber�a ser ant�s de la fecha final." + diferenciaDias(fi, ff));
				throw new Exception("la fecha de inicio deber�a ser ant�s de la fecha final.");
			}
			if(joins.estaOcupado(idAlojamiento, fi,ff))
			{
				//TODO verificar los que sobran y las fechas de fin.
				System.out.println("Holiwis Filters");
				System.err.println("[EXCEPTION] Logic Exception:"   + "Ya hay una reserva para estas fechas.");
				throw new Exception("Ya hay una reserva para estas fechas.");
			}
			joins.agregarContrato(idCliente, idAlojamiento, contrato);
			if(this.conn!=null){
				this.conn.close();					
			}
		}
		catch (SQLException sqlException) {            
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				joins.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public void addServicio(Long idAlojamiento, Servicio s) throws SQLException, Exception
	{
		DAOJoins joins = new DAOJoins( );
		try
		{
			//TODO Requerimiento 3D: Obtenga la conexion a la Base de Datos (revise los metodos de la clase)
			this.conn = darConexion();
			joins.setConn(this.conn);
			joins.agregarServicio( idAlojamiento, s);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				joins.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public void addCliente(Cliente c) throws SQLException, Exception
	{
		try {
			if(!c.getCorreo().split("@")[1].equals("uniandes.edu.co"))
			{
				System.err.println("[EXCEPTION] Logic Exception:" + "Es necesario que envie un documento que demustre su relación con la universidad.");
				throw new Exception("Es necesario que envie un documento que demustre su relación con la universidad.");
			}
		} catch (Exception e) {
			System.err.println("[EXCEPTION] General Exception: " + "El correo no tiene un formato válido");
			throw new Exception("El correo no tiene un formato válido");
		}
		DAOJoins joins;
		try {
			joins = new DAOJoins( );
			this.conn = darConexion();
			joins.setConn(this.conn);
		} catch (Exception e) {
			throw e;
		}
		if(joins.existeCorreoCliente(c.getCorreo()))
		{
			System.err.println("[EXCEPTION] Logic Exception: " + "Ya existe un usuario con el correo: " + c.getCorreo());
			throw new Exception("Ya existe un usuario con el correo: " + c.getCorreo());
		}
		if(joins.existeLoginCliente(c.getLogin()))
		{
			System.err.println("[EXCEPTION] Logic Exception: " + "Ya existe un usuario con el login: " + c.getLogin());
			throw new Exception("Ya existe un usuario con el login: " + c.getLogin());
		}
		try
		{
			c.setId(joins.getCurrentIdCliente());
			joins.agregarCliente(c);

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				joins.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public void retirarOferta(Date fecha, Long idAlojamiento) throws Exception
	{
		DAOJoins joins = new DAOJoins();
		try
		{
			this.conn = darConexion();
			joins.setConn(this.conn);
			joins.ocultarAlojamiento(idAlojamiento, fecha);
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				joins.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public void cancelarReserva(Long idContrato) throws Exception
	{
		DAOJoins joins = new DAOJoins();
		double precio = 0;
		Date[] inicioFin = joins.getContrato(idContrato);
		if(diferenciaDias(inicioFin[0], inicioFin[1]) >= 3)
		{
			if(diferenciaDias(getCurrentDate(), inicioFin[0]) <= 3)
			{
				precio = 0.1;
			}
			else if(diferenciaDias(getCurrentDate(), inicioFin[0]) >= 0)
			{
				precio = 0.3;
			}
			else
			{
				precio = 0.5;
			}
		}
		else if(diferenciaDias(inicioFin[0], inicioFin[1]) >= 7)
		{
			if(diferenciaDias(getCurrentDate(), inicioFin[0]) <= 7)
			{
				precio = 0.1;
			}
			else if(diferenciaDias(getCurrentDate(), inicioFin[0]) >= 0)
			{
				precio = 0.3;
			}
			else
			{
				precio = 0.5;
			}
		}
		try
		{
			this.conn = darConexion();
			joins.setConn(this.conn);
			joins.finalizarContrato(idContrato, precio);
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				joins.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public Date getCurrentDate()
	{		
		Calendar fechaActual = Calendar.getInstance();
		return new Date(fechaActual.getTimeInMillis());
	}
	public static Date parseDateTime(String dateString) {
	    if (dateString == null) return null;
	    DateFormat fmt = new SimpleDateFormat("yyyy-mm-dd");
	    try {
	        return new Date(fmt.parse(dateString).getTime());
	    }
	    catch (ParseException e) {
	    	e.printStackTrace();
	        System.out.println( "Could not parse datetime: " + dateString);
	        return null;
	    }
	}
	public Integer diferenciaDias(Date fecha1, Date fecha2)
	{
		int daysApart = (int)((fecha2.getTime() - fecha1.getTime()) / (1000*60*60*24));
		return daysApart;
	}

	public ListaPersonaNormal darPersonasNormales() {
		// TODO Auto-generated method stub
		return null;
	}

	public PersonaNormal darPersonaNormal(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ListaHotel darHoteles() {
		// TODO Auto-generated method stub
		return null;
	}

	public Hotel darHotel(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ListaHostal darHostales() {
		// TODO Auto-generated method stub
		return null;
	}

	public Hostal darHostal(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ListaDuenoVivienda darDuenosViviendas() {
		// TODO Auto-generated method stub
		return null;
	}

	public DuenoVivienda darDuenoVivienda(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ListaContrato darContratos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Contrato darContrato(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ListaCliente darClientes() {
		// TODO Auto-generated method stub
		return null;
	}
	public ListaServicio darServicios() {
		// TODO Auto-generated method stub
		return null;
	}

	public Servicio darServicio(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ListaAlojamientos darAlojamientos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Alojamiento darAlojamiento(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
