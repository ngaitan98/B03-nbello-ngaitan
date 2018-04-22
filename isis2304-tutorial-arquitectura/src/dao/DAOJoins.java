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
		hoteles.addHotel(h);
		
	}
	public void agregarPersonaNormal(PersonaNormal h) throws SQLException, Exception
	{
		personas.addPersonaNormal(h);
		
	}
	public void agregarHostal(Hostal h) throws SQLException, Exception
	{
		hostales.addHostal(h);
	}
	public void agregarDuenoVivienda(DuenoVivienda h) throws SQLException, Exception
	{
		duenos.addDuenoVivienda(h);
	}
	public void agregarAlojamiento(Long idOperador, Alojamiento a) throws SQLException, Exception
	{
		//TODO completar este m�todo para los dem�s DAOs y sus casos, yo me encargo de la l�gica
		ResultSet h = hoteles.findHotelById(idOperador);
		ResultSet p = personas.findPersonaNormalById(idOperador);
		ResultSet d = duenos.findDuenoViviendaById(idOperador);
		ResultSet hl = hostales.findHostalById(idOperador);

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
	}
	public void ocuparAlojamiento(Long id) throws SQLException
	{
		alojamientos.alojar(id);
	}
	public void ocultarAlojamiento(Long idAlojamiento, Date fecha) throws SQLException, Exception
	{
		ResultSet alojamiento = alojamientos.findAlojamientoById(idAlojamiento);
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
	public boolean estaOcupado(Long idAlojamiento, Date fechaInicio, Date fechaFin) throws SQLException, Exception
	{
		ResultSet alojamiento = alojamientos.findAlojamientoById(idAlojamiento);
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
							|| fechaInicio.toString().equals(rs2.getDate(1).toString()) || fechaInicio.toString().equals(rs2.getDate(2).toString())
							|| (fechaInicio.before(rs2.getDate(1)) && fechaFin.after(rs2.getDate(2))))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean existeCorreoOperador(Long id) throws SQLException, Exception
	{
		return hoteles.findHotelById(id).next() || hostales.findHostalById(id).next() || personas.findPersonaNormalById(id).next() || duenos.findDuenoViviendaById(id).next();
	}
	public Date[] getContrato(Long id) throws SQLException, Exception
	{
		ResultSet rs = contratos.findContratoById(id);
		if(rs.next())
		{
			Date[] answ = new Date[2];
			answ[0] = rs.getDate(2);
			answ[1] = rs.getDate(3);
			
			return answ;
		}
		else
		{
			throw new Exception("No existe un contrato con el id ingresado");
		}
	}
	public Date[] getFechaReservas(Long id) throws SQLException, Exception
	{
		ResultSet rs = grupales.findReservaGrupalById(id);
		if(rs.next())
		{
			Date[] answ = new Date[2];
			answ[0] = rs.getDate(2);
			answ[1] = rs.getDate(3);
			
			return answ;
		}
		else
		{
			throw new Exception("No existe un contrato con el id ingresado");
		}
	}
	public void agregarGrupal(ReservaGrupal rg, Date inicio, Date fin) throws Exception
	{
		ArrayList<Long> alojamientosDisponibles = findAlojamientosParaGrupales(rg.getTipo(), rg.getCanitadPersonas(), inicio, fin);
		if(alojamientosDisponibles.size() < rg.getCanitadPersonas())
		{
			throw new Exception("No hay suficientes habitaciones para la cantidad de clientes exigida");
		}
		else
		{
			int i = 0;
			grupales.addReservaGrupal(rg);
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
	}
	public void finalizarReservaGrupal(Long id, Double nuevoPrecio) throws SQLException
	{
		System.out.println("111111");
		ArrayList<Long> contratos = contratosDeReserva(id);
		grupales.finalizar(id);
		for (Long i : contratos) {
			finalizarContrato(i, nuevoPrecio);
		}
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
		String sql = String.format("SELECT MAX(IND) FROM (SELECT MAX(HOTELES.ID) AS IND FROM HOTELES UNION ALL SELECT MAX(HOSTALES.ID) AS IND FROM HOSTALES UNION ALL SELECT MAX(DUENOVIVIENDA.ID) AS IND FROM DUENOVIVIENDA UNION ALL SELECT MAX(PERSONASNORMALES.ID) AS IND FROM PERSONASNORMALES)");
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet s = prepStmt.executeQuery();	
		if(s.next())
		{
			System.out.println(s.getLong(1) + 1);
			return s.getLong(1) + 1;
		}
		System.out.println("ret 0");
		return (long) 0 + 1;
	}
	public Long getCurrentIdAlojamiento() throws SQLException, Exception
	{
		String sql = String.format("SELECT MAX(ID) FROM %1$s.ALOJAMIENTOS", USUARIO);
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet s = prepStmt.executeQuery();	
		if(s.next())
		{
			return s.getLong(1) + 1;
		}
		return (long) 0 + 1;
	}
	public Long getCurrentIdContrato() throws SQLException, Exception
	{
		String sql = String.format("SELECT MAX(ID) FROM %1$s.CONTRATOS", USUARIO);
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet s = prepStmt.executeQuery();	
		if(s.next())
		{
			return s.getLong(1) + 1;
		}
		return (long) 0 + 1;
	}
	public Long getCurrentIdCliente() throws SQLException, Exception
	{
		String sql = String.format("SELECT MAX(ID) FROM %1$s.CLIENTES", USUARIO);
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet s = prepStmt.executeQuery();	
		if(s.next())
		{
			return s.getLong(1) + 1;
		}
		return (long) 0 + 1;
	}
	public Long getCurrentIdServicio() throws SQLException, Exception
	{
		String sql = String.format("SELECT MAX(ID) FROM %1$s.SERVICIOS", USUARIO);
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet s = prepStmt.executeQuery();	
		if(s.next())
		{
			return s.getLong(1) + 1;
		}
		return (long) 0 + 1;
	}
	public Long getCurrentIdReservasGrupales() throws SQLException, Exception
	{
		String sql = String.format("SELECT MAX(ID) FROM %1$s.RESERVASGRUPALES", USUARIO);
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet s = prepStmt.executeQuery();	
		if(s.next())
		{
			return s.getLong(1) + 1;
		}
		return (long) 0 + 1;
	}
	public void setAutoCommitFalse() throws SQLException
	{
		System.out.println("Set autocommit 0");
		conn.setAutoCommit(false);
	}
	public void commit() throws SQLException
	{
		System.out.println("commit");
		conn.commit();
	}
	public void rollBack() throws SQLException
	{
		System.out.println("rollback");
		conn.rollback();
	}
	public Double getPrecioAlojamiento(Long id) throws SQLException
	{
		Double base = 0.0, agregado = 0.0;
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
		return base + agregado;
	}
	public ArrayList<Long> contratosDeReserva(Long idReserva) throws SQLException
	{
		ArrayList<Long> ids = new ArrayList<Long>();
		String sql = String.format("SELECT ID_CONTRATO FROM %1$s.CONTRATOSGRUPALES WHERE ID_RESERVAGRUPAL = %2$s", USUARIO, idReserva);
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet result = prepStmt.executeQuery();
		while(result.next())
		{
			ids.add(result.getLong(1));
		}
		return ids;
	}
	public ArrayList<Long> findAlojamientosParaGrupales(String tipo, Integer cantidad, Date inicio, Date fin) throws Exception, SQLException
	{
		ArrayList<Long> ids = new ArrayList<Long>();
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
		return ids;
	}
}
