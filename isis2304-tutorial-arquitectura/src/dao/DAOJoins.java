package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
	private DAODuenoVivienda duenos;
	private DAOPersonaNormal personas;
	private DAOHostal hostales;
	private DAOReservaGrupal grupales;


	public DAOJoins()
	{
		duenos = new DAODuenoVivienda();
		hostales = new DAOHostal();
		personas = new DAOPersonaNormal();
		hoteles = new DAOHotel();
		alojamientos = new DAOAlojamiento();
		clientes = new DAOCliente();
		contratos = new DAOContrato();
		servicios = new DAOServicio();
		grupales = new DAOReservaGrupal();

		recursos = new ArrayList<Object>();
	}
	public void agregarHotel(Hotel h) throws SQLException, Exception
	{
		try {
			setAutoCommitFalse();
			hoteles.addHotel(h);
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}
	}
	public void agregarPersonaNormal(PersonaNormal h) throws SQLException, Exception
	{

		try {
			setAutoCommitFalse();
			personas.addPersonaNormal(h);
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}
	}
	public void agregarHostal(Hostal h) throws SQLException, Exception
	{

		try {
			setAutoCommitFalse();
			hostales.addHostal(h);
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}
	}
	public void agregarDuenoVivienda(DuenoVivienda h) throws SQLException, Exception
	{

		try {
			setAutoCommitFalse();
			duenos.addDuenoVivienda(h);
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}
	}
	public void agregarAlojamiento(Long idOperador, Alojamiento a) throws SQLException, Exception
	{
		//TODO completar este m�todo para los dem�s DAOs y sus casos, yo me encargo de la l�gica

		ResultSet h = hoteles.findHotelById(idOperador);
		ResultSet p = personas.findPersonaNormalById(idOperador);
		ResultSet d = duenos.findDuenoViviendaById(idOperador);
		ResultSet hl = hostales.findHostalById(idOperador);
		try {
			setAutoCommitFalse();
			if(h.next())
			{
				alojamientos.addAlojamiento(a);
				String sql = String.format("INSERT INTO %1$s.OFRECEN VALUES (%2$s,%3$s,'%4$s')", 
						USUARIO,
						a.getId(), 
						idOperador, 
						"HOTELES");
				System.out.println(sql);
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}
			else if(p.next())
			{
				alojamientos.addAlojamiento(a);
				String sql = String.format("INSERT INTO %1$s.OFRECEN VALUES (%2$s, %3$s, '%4$s')", USUARIO, a.getId(), idOperador, "PERSONASNORMALES");
				System.out.println(sql);
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}
			else if(hl.next())
			{
				alojamientos.addAlojamiento(a);
				String sql = String.format("INSERT INTO %1$s.OFRECEN VALUES (%2$s, %3$s, '%4$s')", USUARIO, a.getId(), idOperador, "HOSTALES");
				System.out.println(sql);
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}
			else if(d.next())
			{
				alojamientos.addAlojamiento(a);
				String sql = String.format("INSERT INTO %1$s.OFRECEN VALUES (%2$s, %3$s, '%4$s')", USUARIO, a.getId(), idOperador, "DUENOVIVIENDA");
				System.out.println(sql);
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}
			else
			{
				throw new Exception("No existe el operador con el id " + idOperador);
			}
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}

	}
	public void agregarServicio(Long idAlojamiento, Servicio s) throws SQLException, Exception
	{
		ResultSet a = alojamientos.findAlojamientoById(idAlojamiento);
		try {
			setAutoCommitFalse();
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
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}

	}
	public void agregarCliente(Cliente c) throws SQLException, Exception
	{

		try {
			setAutoCommitFalse();
			clientes.addCliente(c);
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}
	}
	public void agregarContrato(Long idCliente, Long idAlojamiento, Contrato con) throws SQLException, Exception
	{
		ResultSet a = alojamientos.findAlojamientoById(idAlojamiento);
		ResultSet c = clientes.findClienteById(idCliente);
		try {
			setAutoCommitFalse();
			if(a.next() && c.next())
			{
				con.setPrecio(getPrecioAlojamiento(idAlojamiento));
				contratos.addContrato(con);
				String sql = String.format("INSERT INTO %1$s.CONTRATARON VALUES (%2$s, %3$s, %4$s)", USUARIO, idAlojamiento, con.getId(), idCliente);
				System.out.println(sql);
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				prepStmt.executeQuery();
			}
			else
			{
				throw new Exception("El cliente o el alojamiento no existen en la base de datos.");
			}
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}

	}
	public void ocuparAlojamiento(Long id) throws SQLException
	{

		try {
			setAutoCommitFalse();
			alojamientos.alojar(id);
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}
	}
	public void ocultarAlojamiento(Long idAlojamiento, Date fecha) throws SQLException, Exception
	{
		ResultSet alojamiento = alojamientos.findAlojamientoById(idAlojamiento);
		try {
			setAutoCommitFalse();
			if(alojamiento.next())
			{
				String sql = String.format("SELECT ID_CONTRATO FROM %1$s.CONTRATARON WHERE ID_ALOJAMIENTO = %2$S", 
						USUARIO, 
						idAlojamiento);
				System.out.println(sql);
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs1 = prepStmt.executeQuery();
				while(rs1.next())
				{
					Long id = rs1.getLong(1);
					String sql2 = String.format("SELECT FECHAINICIO,FECHAFIN FROM %1$s.CONTRATOS WHERE ID = %2$S", 
							USUARIO, 
							id);
					System.out.println(sql2);
					PreparedStatement prepStm2 = conn.prepareStatement(sql2);
					recursos.add(prepStm2);
					ResultSet rs2 = prepStm2.executeQuery();getClass();
					if(rs2.next())
					{		
						System.out.println(fecha);
						System.out.println(rs2.getDate(1));
						System.out.println(rs2.getDate(2));
						if((fecha.after(rs2.getDate(1)) && fecha.before(rs2.getDate(2)))||
								fecha.equals(rs2.getDate(1)) || fecha.equals(rs2.getDate(2)))
						{
							throw new Exception("Aún existen reservas o ofertas en curso para la fecha indicada");
						}
					}
				}
			}
			alojamientos.desAlojar(idAlojamiento);
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}

	}
	public void finalizarContrato(Long id, Double precio) throws SQLException
	{

		try {
			setAutoCommitFalse();
			contratos.finalizar(id, precio);
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}
	}
	public boolean existeLoginCliente(String login) throws SQLException, Exception
	{
		boolean respuesta = false;
		try {
			setAutoCommitFalse();
			respuesta =clientes.findClienteByLogin(login).next();
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}
		return respuesta;

	}
	public boolean existeCorreoCliente(String correo) throws SQLException, Exception
	{
		boolean respuesta = false;
		try {
			setAutoCommitFalse();
			respuesta =clientes.findClienteByCorreo(correo).next();
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}
		return respuesta;

	}
	public boolean estaOcupado(Long idAlojamiento, Date fechaInicio, Date fechaFin) throws SQLException, Exception
	{
		ResultSet alojamiento = alojamientos.findAlojamientoById(idAlojamiento);
		boolean respuesta = false;
		try {
			setAutoCommitFalse();
			if(alojamiento.next())
			{
				String sql = String.format("SELECT ID_CONTRATO FROM %1$s.CONTRATARON WHERE ID_ALOJAMIENTO = %2$S", 
						USUARIO, 
						idAlojamiento);
				System.out.println(sql);
				PreparedStatement prepStmt = conn.prepareStatement(sql);
				recursos.add(prepStmt);
				ResultSet rs1 = prepStmt.executeQuery();
				while(rs1.next())
				{
					Long id = rs1.getLong(1);
					String sql2 = String.format("SELECT FECHAINICIO,FECHAFIN FROM %1$s.CONTRATOS WHERE ID = %2$S", 
							USUARIO, 
							id);
					System.out.println(sql2);
					PreparedStatement prepStm2 = conn.prepareStatement(sql2);
					recursos.add(prepStm2);
					ResultSet rs2 = prepStm2.executeQuery();
					if(rs2.next())
					{					
						if((fechaInicio.after(rs2.getDate(1)) && fechaInicio.before(rs2.getDate(2)))||
								(fechaFin.after(rs2.getDate(1)) && fechaFin.before(rs2.getDate(2)))
								|| fechaFin.equals(rs2.getDate(1)) || fechaFin.equals(rs2.getDate(2))
								|| fechaInicio.equals(rs2.getDate(1)) || fechaInicio.equals(rs2.getDate(2)))
						{
							respuesta= true;
						}
					}
				}
			}
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}

		return respuesta;
	}
	public boolean existeCorreoOperador(Long id) throws SQLException, Exception
	{
		boolean respuesta = false;
		try {
			setAutoCommitFalse();
			respuesta= hoteles.findHotelById(id).next() || hostales.findHostalById(id).next() || personas.findPersonaNormalById(id).next() || duenos.findDuenoViviendaById(id).next();

			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}
		return respuesta;
	}
	public Date[] getContrato(Long id) throws SQLException, Exception
	{
		ResultSet rs = contratos.findContratoById(id);
		try {
			setAutoCommitFalse();
			if(rs.next())
			{
				Date[] answ = new Date[2];
				answ[0] = rs.getDate(2);
				answ[1] = rs.getDate(3);
				commit();
				return answ;
			}
			else
			{
				throw new Exception("No existe un contrato con el id ingresado");
			}

		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}
		return null;

	}
	public void agregarGrupal(ReservaGrupal rg, Date inicio, Date fin) throws Exception
	{
		ArrayList<Long> alojamientosDisponibles = findAlojamientosParaGrupales(rg.getTipo(), rg.getCanitadPersonas(), inicio, fin);
		try {
			setAutoCommitFalse();
			if(alojamientosDisponibles.size() < rg.getCanitadPersonas())
			{
				throw new Exception("No hay suficientes habitaciones para la cantidad de clientes exigida");
			}
			else
			{
				grupales.addReservaGrupal(rg);
				int i = 0;
				for(Cliente c: rg.getClientes())
				{
					if(!existeCorreoCliente(c.getCorreo()))
					{
						throw new Exception("Uno de los clientes ingresados no está registrado. Intente Nuevamente.");
					}
					else
					{
						Contrato nuevo = new Contrato(getCurrentIdContrato(), inicio.toString(), fin.toString(), rg.getFechaCreacion(), 0.0, 1, 0);
						agregarContrato(c.getId(), alojamientosDisponibles.get(i), nuevo);

						String sql = String.format("INSERT INTO %1$s.CONTRATOSGRUPALES (ID_RESERVAGRUPAL, ID_CONTRATO) VALUES (%2$s, %3$s)", USUARIO, rg.getId(), nuevo.getId());
						System.out.println(sql);
						PreparedStatement prepStmt = conn.prepareStatement(sql);
						recursos.add(prepStmt);
						prepStmt.executeQuery();

						i++;
					}
				}
			}
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}

	}
	public ArrayList<Long> findAlojamientosParaGrupales(String tipo, Integer cantidad, Date inicio, Date fin) throws Exception, SQLException
	{
		ArrayList<Long> ids = new ArrayList<Long>();
		try {
			setAutoCommitFalse();
			String sql = String.format("SELECT ID FROM %1$s.ALOJAMIENTOS WHERE TIPOALOJAMIENTO = '%2$s' AND NUMERODECUPOS >= %3$s", USUARIO, tipo, cantidad);
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet result = prepStmt.executeQuery();
			while(result.next())
			{
				Long i = result.getLong(1);
				if(!estaOcupado(i, inicio, fin))
				{
					ids.add(i);
				}
			}
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}

		return ids;
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
		System.out.println(conn + "En DAOJOINS");
		alojamientos.setConn(conn);
		servicios.setConn(conn);
		hoteles.setConn(conn);
		clientes.setConn(conn);
		contratos.setConn(conn);
		personas.setConn(conn);
		duenos.setConn(conn);
		hostales.setConn(conn);
		grupales.setConn(conn);
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
		personas.cerrarRecursos();
		duenos.cerrarRecursos();
		hostales.cerrarRecursos();
		grupales.cerrarRecursos();
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}
	public Long getCurrentIdOperador() throws SQLException, Exception
	{
		Long respuesta = new Long(0);
		respuesta = (long) 0 + 1;
		try {
			setAutoCommitFalse();
			String sql = String.format("SELECT MAX(IND) FROM (SELECT MAX(HOTELES.ID) AS IND FROM HOTELES UNION ALL SELECT MAX(HOSTALES.ID) AS IND FROM HOSTALES UNION ALL SELECT MAX(DUENOVIVIENDA.ID) AS IND FROM DUENOVIVIENDA UNION ALL SELECT MAX(PERSONASNORMALES.ID) AS IND FROM PERSONASNORMALES)");
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet s = prepStmt.executeQuery();	
			if(s.next())
			{
				System.out.println(s.getLong(1) + 1);
				respuesta= s.getLong(1) + 1;
			}
			System.out.println("ret 0");
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}

		return respuesta;
	}
	public Long getCurrentIdAlojamiento() throws SQLException, Exception
	{
		Long respuesta = new Long(0);
		respuesta = (long) 0 + 1;
		try {
			setAutoCommitFalse();
			String sql = String.format("SELECT MAX(ID) FROM %1$s.ALOJAMIENTOS", USUARIO);
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet s = prepStmt.executeQuery();	
			if(s.next())
			{
				respuesta= s.getLong(1) + 1;
			}
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}

		return respuesta;
	}
	public Long getCurrentIdContrato() throws SQLException, Exception
	{
		Long respuesta = new Long(0);
		respuesta = (long) 0 + 1;
		try {
			setAutoCommitFalse();
			String sql = String.format("SELECT MAX(ID) FROM %1$s.CONTRATOS", USUARIO);
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet s = prepStmt.executeQuery();	
			if(s.next())
			{
				respuesta= s.getLong(1) + 1;
			}
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}

		return respuesta;
	}
	public Long getCurrentIdCliente() throws SQLException, Exception
	{
		Long respuesta = new Long(0);
		respuesta = (long) 0 + 1;
		try {
			setAutoCommitFalse();
			String sql = String.format("SELECT MAX(ID) FROM %1$s.CLIENTES", USUARIO);
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet s = prepStmt.executeQuery();	
			if(s.next())
			{
				respuesta =  s.getLong(1) + 1;
			}
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}

		return respuesta;
	}
	public Long getCurrentIdServicio() throws SQLException, Exception
	{
		Long respuesta = new Long(0);
		respuesta = (long) 0 + 1;
		try {
			setAutoCommitFalse();
			String sql = String.format("SELECT MAX(ID) FROM %1$s.SERVICIOS", USUARIO);
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet s = prepStmt.executeQuery();	
			if(s.next())
			{
				respuesta =  s.getLong(1) + 1;
			}
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}

		return respuesta;
	}
	public Long getCurrentIdReservasGrupales() throws SQLException, Exception
	{
		Long respuesta = new Long(0);
		respuesta = (long) 0 + 1;
		try {
			setAutoCommitFalse();
			String sql = String.format("SELECT MAX(ID) FROM %1$s.RESERVASGRUPALES", USUARIO);
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet s = prepStmt.executeQuery();	
			if(s.next())
			{
				respuesta = s.getLong(1) + 1;
			}
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}
		
		return respuesta;
	}
	public void setAutoCommitFalse() throws SQLException
	{
		PreparedStatement prepStmt = conn.prepareStatement("SET AUTOCOMMIT 0");
		recursos.add(prepStmt);
		prepStmt.executeQuery();	
	}
	public void commit() throws SQLException
	{
		PreparedStatement prepStmt = conn.prepareStatement("COMMIT");
		recursos.add(prepStmt);
		prepStmt.executeQuery();	
	}
	public void rollBack() throws SQLException
	{
		PreparedStatement prepStmt = conn.prepareStatement("ROLLBACK");
		recursos.add(prepStmt);
		prepStmt.executeQuery();	
	}
	public Double getPrecioAlojamiento(Long id) throws SQLException
	{
		Double base = 0.0, agregado = 0.0;
		try {
			setAutoCommitFalse();
			
			String sql = String.format("SELECT COSTOBASE FROM %1$s.ALOJAMIENTOS WHERE ID = %2$s", USUARIO, id);
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs2 = prepStmt.executeQuery();
			if(rs2.next())
			{
				base += rs2.getDouble(1);
				String sql2 = String.format("SELECT COSTOAGREGADO FROM (SELECT ID AS ID_SERVICIO, COSTOAGREGADO FROM %1$s.SERVICIOS)a NATURAL JOIN %1$s.TIENEN WHERE ID_ALOJAMIENTO = %2$s", USUARIO, id);
				System.out.println(sql2);
				PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
				recursos.add(prepStmt2);
				ResultSet rs = prepStmt.executeQuery();
				while(rs.next())
				{
					agregado += rs.getDouble(1);
				}
			}
			commit();
		} catch (Exception e)
		{
			rollBack();
			e.printStackTrace();
		}

		return base + agregado;
	}
}
