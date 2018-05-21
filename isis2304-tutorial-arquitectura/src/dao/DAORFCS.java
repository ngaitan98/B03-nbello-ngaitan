package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.*;

public class DAORFCS {

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
	 * Metodo constructor de la clase DaoCliente <br/>
	 */
	public DAORFCS() 
	{
		recursos = new ArrayList<Object>();
	}
	public void setConn(Connection connection){
		this.conn = connection;
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

	public String getDineroProveedores () throws SQLException, Exception
	{
		String sql = String.format("SELECT * FROM " + 
				"(SELECT * FROM " + 
				"(SELECT D.OPERADOR,D.TIPO, ISIS2304A471810.DUENOVIVIENDA.NOMBRE,D.ALOJAMIENTO,D.DINERO_A�O_COMPLETO,D.DINERO_A�O_CORRIDO FROM(" + 
				"(SELECT ISIS2304A471810.OFRECEN.ID_OPERADOR as  OPERADOR, ISIS2304A471810.OFRECEN.TIPO AS TIPO,  B.ALOJAMIENTO, B.DINERO_A�O_COMPLETO,B.DINERO_A�O_CORRIDO FROM(" + 
				"(SELECT NVL(m.ALOJAMIENTO,n.ALOJAMIENTO) AS ALOJAMIENTO, NVL(DINERO_A�O_COMPLETO,DINERO_A�O_CORRIDO)AS DINERO_A�O_COMPLETO, NVL(DINERO_A�O_CORRIDO,0) AS DINERO_A�O_CORRIDO FROM(" + 
				"(SELECT f.ID_ALOJAMIENTO AS ALOJAMIENTO, g.PRECIO as DINERO_A�O_COMPLETO FROM(" + 
				"(SELECT * FROM ISIS2304A471810.CONTRATOS WHERE ISIS2304A471810.CONTRATOS.FINALIZADO ='0'AND ISIS2304A471810.CONTRATOS.FECHAFIN LIKE'%/18')g" + 
				"INNER JOIN (ISIS2304A471810.CONTRATARON)f ON f.ID_CONTRATO = g.ID))m" + 
				"full OUTER JOIN (" + 
				"SELECT ALOJAMIENTO,  DINERO_A�O_CORRIDO FROM(" + 
				"SELECT j.ID_ALOJAMIENTO AS ALOJAMIENTO, k.PRECIO as DINERO_A�O_CORRIDO FROM(" + 
				"(SELECT * FROM ISIS2304A471810.CONTRATOS WHERE ISIS2304A471810.CONTRATOS.FINALIZADO ='1')k" + 
				"INNER JOIN (ISIS2304A471810.CONTRATARON)j ON j.ID_CONTRATO = k.ID)))n ON n.ALOJAMIENTO = m.ALOJAMIENTO))B" + 
				"INNER JOIN (ISIS2304A471810.OFRECEN) ON ISIS2304A471810.OFRECEN.ID_ALOJAMIENTO = B.ALOJAMIENTO))D INNER JOIN ISIS2304A471810.DUENOVIVIENDA ON ISIS2304A471810.DUENOVIVIENDA.ID = D.OPERADOR))" + 
				"UNION " + 
				"SELECT * FROM " + 
				"(SELECT F.OPERADOR,F.TIPO, HOSTALES.NOMBRE,F.ALOJAMIENTO,F.DINERO_A�O_COMPLETO,F.DINERO_A�O_CORRIDO FROM(" + 
				"(SELECT ISIS2304A471810.OFRECEN.ID_OPERADOR as  OPERADOR, ISIS2304A471810.OFRECEN.TIPO AS TIPO,  B.ALOJAMIENTO, B.DINERO_A�O_COMPLETO,B.DINERO_A�O_CORRIDO FROM(" + 
				"(SELECT NVL(m.ALOJAMIENTO,n.ALOJAMIENTO) AS ALOJAMIENTO, NVL(DINERO_A�O_COMPLETO,DINERO_A�O_CORRIDO)AS DINERO_A�O_COMPLETO, NVL(DINERO_A�O_CORRIDO,0) AS DINERO_A�O_CORRIDO FROM(" + 
				"(SELECT f.ID_ALOJAMIENTO AS ALOJAMIENTO, g.PRECIO as DINERO_A�O_COMPLETO FROM(\n" + 
				"(SELECT * FROM ISIS2304A471810.CONTRATOS WHERE ISIS2304A471810.CONTRATOS.FINALIZADO ='0'AND ISIS2304A471810.CONTRATOS.FECHAFIN LIKE'%/18')g\n" + 
				"INNER JOIN (ISIS2304A471810.CONTRATARON)f ON f.ID_CONTRATO = g.ID))m\n" + 
				"full OUTER JOIN (\n" + 
				"SELECT ALOJAMIENTO,  DINERO_A�O_CORRIDO FROM(\n" + 
				"SELECT j.ID_ALOJAMIENTO AS ALOJAMIENTO, k.PRECIO as DINERO_A�O_CORRIDO FROM(\n" + 
				"(SELECT * FROM ISIS2304A471810.CONTRATOS WHERE ISIS2304A471810.CONTRATOS.FINALIZADO ='1')k\n" + 
				"INNER JOIN (ISIS2304A471810.CONTRATARON)j ON j.ID_CONTRATO = k.ID)))n ON n.ALOJAMIENTO = m.ALOJAMIENTO))B\n" + 
				"INNER JOIN (ISIS2304A471810.OFRECEN) ON ISIS2304A471810.OFRECEN.ID_ALOJAMIENTO = B.ALOJAMIENTO))F INNER JOIN ISIS2304A471810.HOSTALES ON  ISIS2304A471810.HOSTALES.ID = F.OPERADOR)))\n" + 
				"UNION\n" + 
				"SELECT * FROM \n" + 
				"(SELECT * FROM \n" + 
				"(SELECT A.OPERADOR,A.TIPO, ISIS2304A471810.PERSONASNORMALES.NOMBRE,A.ALOJAMIENTO,A.DINERO_A�O_COMPLETO,A.DINERO_A�O_CORRIDO FROM(\n" + 
				"(SELECT ISIS2304A471810.OFRECEN.ID_OPERADOR as  OPERADOR, ISIS2304A471810.OFRECEN.TIPO AS TIPO,  B.ALOJAMIENTO, B.DINERO_A�O_COMPLETO,B.DINERO_A�O_CORRIDO FROM(\n" + 
				"(SELECT NVL(m.ALOJAMIENTO,n.ALOJAMIENTO) AS ALOJAMIENTO, NVL(DINERO_A�O_COMPLETO,DINERO_A�O_CORRIDO)AS DINERO_A�O_COMPLETO, NVL(DINERO_A�O_CORRIDO,0) AS DINERO_A�O_CORRIDO FROM(\n" + 
				"(SELECT f.ID_ALOJAMIENTO AS ALOJAMIENTO, g.PRECIO as DINERO_A�O_COMPLETO FROM(\n" + 
				"(SELECT * FROM ISIS2304A471810.CONTRATOS WHERE ISIS2304A471810.CONTRATOS.FINALIZADO ='0'AND ISIS2304A471810.CONTRATOS.FECHAFIN LIKE'%/18')g\n" + 
				"INNER JOIN (ISIS2304A471810.CONTRATARON)f ON f.ID_CONTRATO = g.ID))m\n" + 
				"full OUTER JOIN (\n" + 
				"SELECT ALOJAMIENTO,  DINERO_A�O_CORRIDO FROM(\n" + 
				"SELECT j.ID_ALOJAMIENTO AS ALOJAMIENTO, k.PRECIO as DINERO_A�O_CORRIDO FROM(\n" + 
				"(SELECT * FROM ISIS2304A471810.CONTRATOS WHERE ISIS2304A471810.CONTRATOS.FINALIZADO ='1')k\n" + 
				"INNER JOIN (ISIS2304A471810.CONTRATARON)j ON j.ID_CONTRATO = k.ID)))n ON n.ALOJAMIENTO = m.ALOJAMIENTO))B\n" + 
				"INNER JOIN (ISIS2304A471810.OFRECEN) ON ISIS2304A471810.OFRECEN.ID_ALOJAMIENTO = B.ALOJAMIENTO))A INNER JOIN ISIS2304A471810.PERSONASNORMALES ON  ISIS2304A471810.PERSONASNORMALES.ID = A.OPERADOR))\n" + 
				"UNION \n" + 
				"SELECT * FROM \n" + 
				"(SELECT c.OPERADOR,c.TIPO, ISIS2304A471810.hoteles.NOMBRE,c.ALOJAMIENTO,c.DINERO_A�O_COMPLETO,c.DINERO_A�O_CORRIDO FROM(\n" + 
				"(SELECT ISIS2304A471810.OFRECEN.ID_OPERADOR as  OPERADOR, ISIS2304A471810.OFRECEN.TIPO AS TIPO,  B.ALOJAMIENTO, B.DINERO_A�O_COMPLETO,B.DINERO_A�O_CORRIDO FROM(\n" + 
				"(SELECT NVL(m.ALOJAMIENTO,n.ALOJAMIENTO) AS ALOJAMIENTO, NVL(DINERO_A�O_COMPLETO,DINERO_A�O_CORRIDO)AS DINERO_A�O_COMPLETO, NVL(DINERO_A�O_CORRIDO,0) AS DINERO_A�O_CORRIDO FROM(\n" + 
				"(SELECT f.ID_ALOJAMIENTO AS ALOJAMIENTO, g.PRECIO as DINERO_A�O_COMPLETO FROM(\n" + 
				"(SELECT * FROM ISIS2304A471810.CONTRATOS WHERE ISIS2304A471810.CONTRATOS.FINALIZADO ='0'AND ISIS2304A471810.CONTRATOS.FECHAFIN LIKE'%/18')g\n" + 
				"INNER JOIN (ISIS2304A471810.CONTRATARON)f ON f.ID_CONTRATO = g.ID))m\n" + 
				"full OUTER JOIN (\n" + 
				"SELECT ALOJAMIENTO,  DINERO_A�O_CORRIDO FROM(\n" + 
				"SELECT j.ID_ALOJAMIENTO AS ALOJAMIENTO, k.PRECIO as DINERO_A�O_CORRIDO FROM(\n" + 
				"(SELECT * FROM ISIS2304A471810.CONTRATOS WHERE ISIS2304A471810.CONTRATOS.FINALIZADO ='1')k\n" + 
				"INNER JOIN (ISIS2304A471810.CONTRATARON)j ON j.ID_CONTRATO = k.ID)))n ON n.ALOJAMIENTO = m.ALOJAMIENTO))B\n" + 
				"INNER JOIN (ISIS2304A471810.OFRECEN) ON ISIS2304A471810.OFRECEN.ID_ALOJAMIENTO = B.ALOJAMIENTO))c INNER JOIN ISIS2304A471810.HOTELES ON  ISIS2304A471810.HOTELES.ID = c.OPERADOR)));", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		return rs.toString();
	}

	public String getOfertasPopulares() throws SQLException, Exception
	{
		String sql = String.format("SELECT COUNT(%1$s.CONTRATARON.ID_CONTRATO) AS VENTAS, %1$s.CONTRATARON.ID_ALOJAMIENTO AS ALOJAMIENTO FROM %1$sCONTRATARON WHERE ROWNUM <= 20 group by %1$s.CONTRATARON.ID_ALOJAMIENTO order by ventas desc;",
				USUARIO);
		String resp = "";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		while(rs.next())
		{
			resp = resp + "Ventas: " + rs.getLong(1) + "|| alojamiento:" + rs.getLong(2);
			System.out.println(rs.getLong(2));
		}
		return resp;
	}

	public ResultSet getIndiceAlojamientos() throws SQLException, Exception
	{
		String sql = String.format("Select %1$s.ALOJAMIENTOS.Id as Alojamiento, %1$s.ALOJAMIENTOS.ocupada from alojamientoS;",
				USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		return rs;
	}

	public String getAlojamientosdisponibles( String fechainicio, String fechafin, String servicio) throws SQLException, Exception
	{
		String sql = String.format("select * from (\n" + 
				"select j.*, %1$s.servicios.nombre as nombre, %1$s.servicios.costoagregado as costoagregado from (\n" + 
				"(select h.*, %1$s.tienen.id_servicio as servicio from (\n" + 
				"(select %1$s.alojamientos.* from (\n" + 
				"%1$s.alojamientos inner join (\n" + 
				"select %1$s.contrataron.id_alojamiento as alojamiento from( %1$s.contrataron inner join (SELECT * FROM %1$s.CONTRATOS WHERE  %1$s.CONTRATOS.FECHAFIN < to_date( %2$s, 'dd/mm/yy') OR %1$s.CONTRATOSFECHAINICIO > to_date( %3$s , 'dd/mm/yy'))\n" + 
				"f on  %1$s.contrataron.id_contrato = f.id))g on  %1$s.alojamientos.id = g.alojamiento) where ocupada = '0')h inner join\n" + 
				" %1$s.tienen on h.id =  %1$s.tienen.id_alojamiento))j inner join\n" + 
				" %1$s.servicios on j.servicio =  %1$s.servicios.id ) ) where nombre like '%'+ %4$s. ;",
				USUARIO, fechainicio,fechafin,servicio);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		return rs.toString() ;
	}
	public String getUsoAlhoAndesOperarios() throws SQLException, Exception
	{
		String sql = String.format("SELECT * FROM" + 
				"(SELECT * FROM (" + 
				"SELECT %1$s.DUENOVIVIENDA.ID AS OPERADOR,%1$s.OFRECEN.TIPO AS TIPO, %1$s.DUENOVIVIENDA.NOMBRE AS NOMBRE," + 
				"%1$s.OFRECEN.ID_ALOJAMIENTO AS ALOJAMIENTO FROM " + 
				"%1$s.OFRECEN INNER JOIN %1$s.DUENOVIVIENDA ON %1$s.DUENOVIVIENDA.ID=%1$s.OFRECEN.ID_OPERADOR)" + 
				"UNION" + 
				"SELECT * FROM (" + 
				"SELECT %1$s.HOSTALES.ID AS OPERADOR,%1$s.OFRECEN.TIPO AS TIPO, %1$s.HOSTALES.NOMBRE AS NOMBRE," + 
				"%1$s.OFRECEN.ID_ALOJAMIENTO AS ALOJAMIENTO FROM " + 
				"%1$s.OFRECEN INNER JOIN %1$s.HOSTALES ON %1$s.HOSTALES.ID=%1$s.OFRECEN.ID_OPERADOR))" + 
				"UNION" + 
				"SELECT *FROM" + 
				"(SELECT * FROM (" + 
				"SELECT %1$s.HOTELES.ID AS OPERADOR,%1$s.OFRECEN.TIPO AS TIPO, %1$s.HOTELES.NOMBRE AS NOMBRE," + 
				"%1$s.OFRECEN.ID_ALOJAMIENTO AS ALOJAMIENTO FROM " + 
				"%1$s.OFRECEN INNER JOIN %1$s.HOTELES ON %1$s.HOTELES.ID=%1$s.OFRECEN.ID_OPERADOR)" + 
				"UNION" + 
				"SELECT * FROM (" + 
				"SELECT %1$s.PERSONASNORMALES.ID AS OPERADOR,%1$s.OFRECEN.TIPO AS TIPO, %1$s.PERSONASNORMALES.NOMBRE AS NOMBRE," + 
				"%1$s.OFRECEN.ID_ALOJAMIENTO AS ALOJAMIENTO FROM " + 
				"%1$s.OFRECEN INNER JOIN %1$s.PERSONASNORMALES ON %1$s.PERSONASNORMALES.ID=%1$s.OFRECEN.ID_OPERADOR));" + 
				"",
				USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		return rs.toString();
	}
	public String getUsoAlhoAndesClientes() throws SQLException, Exception
	{
		String sql = String.format("Select" + 
				"f.ID,f.NOMBRE,f.ALOJAMIENTO,f.CONTRATO, g.FECHAINICIO,g.FECHAFIN,g.PRECIO AS DINERO_PAGADO,g.FINALIZADO,g.CANTIDADPERSONAS" + 
				"FROM" + 
				"((SELECT " + 
				"%1$s.CLIENTES.ID,%1$s.CLIENTES.NOMBRE,%1$s. CONTRATARON.ID_ALOJAMIENTO AS ALOJAMIENTO,%1$s.CONTRATARON.ID_CONTRATO AS CONTRATO" + 
				"FROM %1$s.CLIENTES INNER JOIN %1$s.CONTRATARON ON %1$s.CLIENTES.ID=%1$s.CONTRATARON.ID_CLIENTE) f " + 
				"INNER JOIN (%1$s.CONTRATOS)g ON f.CONTRATO=g.ID);",
				USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		while(rs.next())
		{
			System.out.println(rs.getLong(1));
		}
		return rs.toString();
	}
	public void clientesFrecuentes(Long id_alojamiento) throws SQLException, Exception
	{
		String sql = String.format("SELECT UNIQUE * FROM (SELECT UNIQUE %1$s.CLIENTES.* FROM ((SELECT ID AS ID_CONTRATO" + 
				" FROM %1$s.CONTRATOS WHERE FECHAFIN - FECHAINICIO >14) NATURAL JOIN %1$s.CONTRATARON), %1$s.CLIENTES" + 
				" WHERE ID_CLIENTE = CLIENTES.ID AND %1$s.CONTRATARON.ID_ALOJAMIENTO = %2$s" + 
				" UNION ALL" + 
				" SELECT UNIQUE %1$s.CLIENTES.* " + 
				" FROM (SELECT ID_CLIENTE, COUNT(ID_CLIENTE) FROM %1$s.CONTRATARON WHERE ID_ALOJAMIENTO =  %2$s " + 
				" group by ID_CLIENTE HAVING COUNT(ID_CLIENTE) > 2)a INNER JOIN %1$s.CLIENTES" + 
				" ON %1$s.CLIENTES.ID = a.ID_CLIENTE" + 
				" WHERE ID_CLIENTE = CLIENTES.ID)",
				USUARIO, id_alojamiento);
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		while(rs.next())
		{
			System.out.println(rs.getLong(1));
		}
	}
	public ArrayList<Cliente> consumoEnAlojandes(Long idAlojamiento, String fechaInicio, String fechaFin, String orderByParams) throws SQLException 
	{
		Long t1 = System.currentTimeMillis();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		if(idAlojamiento > -1)
		{
			String sql = String.format("SELECT UNIQUE * FROM((SELECT ID_CLIENTE AS ID FROM (SELECT * FROM %1$s.CONTRATOS "
					+ "WHERE FECHAINICIO BETWEEN '%3$s' AND '%4$s' OR FECHAFIN BETWEEN '%3$s' AND '%4$s') "
					+ "a INNER JOIN (SELECT * FROM %1$s.CONTRATARON WHERE ID_ALOJAMIENTO = %2$s) c ON "
					+ "c.ID_CONTRATO = a.ID) NATURAL JOIN %1$s.CLIENTES) order by %5$s"
					, USUARIO, idAlojamiento, fechaInicio, fechaFin, orderByParams);
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next())
			{
				rs2Cliente(rs);
			}
		}
		else
		{
			String sql = String.format("SELECT UNIQUE * FROM((SELECT ID_CLIENTE AS ID FROM (SELECT * FROM %1$s.CONTRATOS "
					+ "WHERE FECHAINICIO BETWEEN '%3$s' AND '%4$s' OR FECHAFIN BETWEEN '%3$s' AND '%4$s') "
					+ "a INNER JOIN (SELECT * FROM %1$s.CONTRATARON) c ON "
					+ "c.ID_CONTRATO = a.ID) NATURAL JOIN %1$s.CLIENTES) order by %5$s"
					, USUARIO, idAlojamiento, fechaInicio, fechaFin, orderByParams);
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next())
			{
				rs2Cliente(rs);
			}
		}
		Long t2 = System.currentTimeMillis();
		System.out.println("Se han gastado " + new Double((t2-t1)/100).toString() + " segundos.");
		return clientes;
	}
	public ArrayList<Cliente> consumoEnAlojande2s(Long idAlojamiento, String fechaInicio, String fechaFin, String orderByParams) throws SQLException 
	{
		Long t1 = System.currentTimeMillis();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		if(idAlojamiento > -1)
		{
			String sql = String.format("SELECT * FROM CLIENTES WHERE ID NOT IN (SELECT UNIQUE ID FROM ((SELECT ID_CLIENTE AS ID FROM (SELECT * FROM %1$s.CONTRATOS "
					+ "WHERE FECHAINICIO BETWEEN '%3$s' AND '%4$s' OR FECHAFIN BETWEEN '%3$s' AND '%4$s') "
					+ "a INNER JOIN (SELECT * FROM %1$s.CONTRATARON WHERE ID_ALOJAMIENTO = %2$s) c ON "
					+ "c.ID_CONTRATO = a.ID) NATURAL JOIN %1$s.CLIENTES)) order by %5$s"
					, USUARIO, idAlojamiento, fechaInicio, fechaFin, orderByParams);
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next())
			{
				rs2Cliente(rs);
			}
		}
		else
		{
			String sql = String.format("SELECT * FROM CLIENTES WHERE ID NOT IN (SELECT UNIQUE ID FROM((SELECT ID_CLIENTE AS ID FROM (SELECT * FROM %1$s.CONTRATOS "
					+ "WHERE FECHAINICIO BETWEEN '%3$s' AND '%4$s' OR FECHAFIN BETWEEN '%3$s' AND '%4$s') "
					+ "a INNER JOIN (SELECT * FROM %1$s.CONTRATARON) c ON "
					+ "c.ID_CONTRATO = a.ID) NATURAL JOIN %1$s.CLIENTES)) order by %5$s"
					, USUARIO, idAlojamiento, fechaInicio, fechaFin, orderByParams);
			System.out.println(sql);
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.toString());
				rs2Cliente(rs);
			}
		}
		Long t2 = System.currentTimeMillis();
		System.out.println("Se han gastado " + new Double((t2-t1)/100).toString() + " segundos.");
		return clientes;
	}
	public void consultarFuncionamiento() throws SQLException
	{
		Date fi = new Date(new Long("1514782800000"));
		Date ff = new Date(new Long("1546318800000"));
		System.out.println(fi);
		int i = 1;
		while(fi.getTime() < ff.getTime())
		{
			Date fit = fi;
			fi = new Date(fi.getTime() + new Long(604800000));
			System.out.println("Semana " + i + "---------------------------------");
			//Para alojamiento mas ocupado
			String sql = String.format("SELECT * FROM (SELECT ID_ALOJAMIENTO AS ID, COUNT(ID_ALOJAMIENTO) AS CONTCONTRATOS FROM %1$s.CONTRATARON NATURAL JOIN (SELECT ID AS ID_CONTRATO, FECHAINICIO "
					+"FROM %1$s.CONTRATOS WHERE FECHAINICIO BETWEEN '%2$s' AND '%3$s' OR FECHAFIN BETWEEN '%2$s' AND '%3$s') WHERE ROWNUM = 1 GROUP BY ID_ALOJAMIENTO ORDER BY CONTCONTRATOS DESC)" 
					+"NATURAL JOIN %1$s.ALOJAMIENTOS", USUARIO, dateParser(fit), dateParser(fi));
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			ResultSet rs = prepStmt.executeQuery();
			System.out.println("Alojamiento Más demandado:");
			if(rs.next())
			{
				System.out.println("ID: " + rs.getLong(1));
			}
			else
			{
				System.out.println("No hay reservas para esta semana ");
			}
			//Para menos ocupado
			sql = String.format("SELECT * FROM (SELECT ID_ALOJAMIENTO AS ID, COUNT(ID_ALOJAMIENTO) AS CONTCONTRATOS FROM %1$s.CONTRATARON NATURAL JOIN (SELECT ID AS ID_CONTRATO, FECHAINICIO "
					+"FROM %1$s.CONTRATOS WHERE FECHAINICIO BETWEEN '%2$s' AND '%3$s' OR FECHAFIN BETWEEN '%2$s' AND '%3$s') WHERE ROWNUM = 1 GROUP BY ID_ALOJAMIENTO ORDER BY CONTCONTRATOS ASC) " 
					+"NATURAL JOIN %1$s.ALOJAMIENTOS", USUARIO, dateParser(fit), dateParser(fi));
			prepStmt = conn.prepareStatement(sql);
			rs = prepStmt.executeQuery();
			System.out.println("Alojamiento menos demandado:");
			if(rs.next())
			{
				System.out.println("ID: " + rs.getLong(1));
			}
			else
			{
				System.out.println("No hay reservas para esta semana ");
			}
			//Menor operador
			sql = String.format("SELECT ID_OPERADOR, TIPO FROM (SELECT ID_ALOJAMIENTO, COUNT(ID_ALOJAMIENTO) AS CONTCONTRATOS FROM %1$s.CONTRATARON " + 
					"NATURAL JOIN (SELECT ID AS ID_CONTRATO, FECHAINICIO " +
					"FROM %1$s.CONTRATOS WHERE FECHAINICIO BETWEEN '%2$s' AND '%3$s' OR FECHAFIN BETWEEN '%2$s' AND '%3$s') WHERE ROWNUM = 1 GROUP BY ID_ALOJAMIENTO ORDER BY CONTCONTRATOS ASC) " +
					"NATURAL JOIN %1$s.OFRECEN", USUARIO, dateParser(fit), dateParser(fi));
			prepStmt = conn.prepareStatement(sql);
			rs = prepStmt.executeQuery();
			System.out.println("Operador menos efectivo");
			if(rs.next())
			{
				if(rs.getString(2).equals("HOTEL") || rs.getString(2).equals("HOSTAL"))
				{		
					sql = String.format("SELECT * FROM %1$s.%2$sES WHERE ID = %3$s", USUARIO, rs.getString(2), rs.getLong(1));
				}
				else if(rs.getString(2).equals("PERSONANORMAL"))
				{
					sql = String.format("SELECT * FROM %1$s.PERSONASNORMALES WHERE ID = %3$s", USUARIO, rs.getString(2), rs.getLong(1));
				}
				else
				{
					sql = String.format("SELECT * FROM %1$s.%2$s WHERE ID = %3$s", USUARIO, rs.getString(2), rs.getLong(1));
				}
				prepStmt = conn.prepareStatement(sql);
				rs = prepStmt.executeQuery();
				if(rs.next())
				{
					System.out.println("ID: " + rs.getLong(1) + "  NOMBRE: "+ rs.getString(4) + " LOGIN: "+ rs.getString(2));
				}
			}
			else
			{
				System.out.println("No hay reservas para esta semana ");
			}
			//Mayor operador
			System.out.println("Operador mas efectivo");
			sql = String.format("SELECT ID_OPERADOR, TIPO FROM (SELECT ID_ALOJAMIENTO, COUNT(ID_ALOJAMIENTO) AS CONTCONTRATOS FROM %1$s.CONTRATARON " + 
					"NATURAL JOIN (SELECT ID AS ID_CONTRATO, FECHAINICIO " +
					"FROM %1$s.CONTRATOS WHERE FECHAINICIO BETWEEN '%2$s' AND '%3$s' OR FECHAFIN BETWEEN '%2$s' AND '%3$s') WHERE ROWNUM = 1 GROUP BY ID_ALOJAMIENTO ORDER BY CONTCONTRATOS DESC) " +
					"NATURAL JOIN %1$s.OFRECEN ", USUARIO, dateParser(fit), dateParser(fi));
			prepStmt = conn.prepareStatement(sql);
			rs = prepStmt.executeQuery();
			if(rs.next())
			{
				if(rs.getString(2).equals("HOTEL") || rs.getString(2).equals("HOSTAL"))
				{					
					sql = String.format("SELECT * FROM %1$s.%2$sES WHERE ID = %3$s", USUARIO, rs.getString(2), rs.getLong(1));
				}
				else if(rs.getString(2).equals("PERSONANORMAL"))
				{
					sql = String.format("SELECT * FROM %1$s.PERSONASNORMALES WHERE ID = %3$s", USUARIO, rs.getString(2), rs.getLong(1));
				}
				else
				{
					sql = String.format("SELECT * FROM %1$s.%2$s WHERE ID = %3$s", USUARIO, rs.getString(2), rs.getLong(1));
				}
				prepStmt = conn.prepareStatement(sql);
				rs = prepStmt.executeQuery();
				if(rs.next())
				{
					System.out.println("ID: " + rs.getLong(1) + "  NOMBRE: "+ rs.getString(4) + " LOGIN: "+ rs.getString(2));
				}
			}
			else
			{
				System.out.println("No hay reservas para esta semana ");
			}
			i++;
		}

	}
	public void buenosClientes() throws SQLException
	{
		String sql = String.format("SELECT UNIQUE * FROM (SELECT * FROM((SELECT ID_CLIENTE AS ID FROM (SELECT * FROM %1$s.CONTRATOS WHERE PRECIO >= 150*3000) " + 
				"a INNER JOIN %1$s.CONTRATARON ON " + 
				"CONTRATARON.ID_CONTRATO = a.ID) NATURAL JOIN %1$s.CLIENTES) " +
				"UNION ALL " +
				"SELECT * FROM((SELECT ID_CLIENTE AS ID FROM (SELECT * FROM %1$s.ALOJAMIENTOS WHERE TIPOALOJAMIENTO = 'SUITE') " + 
				"b INNER JOIN %1$s.CONTRATARON ON " + 
				"CONTRATARON.ID_ALOJAMIENTO = b.ID) NATURAL JOIN %1$s.CLIENTES))", USUARIO);
		PreparedStatement p = conn.prepareStatement(sql);
		ResultSet rs = p.executeQuery();
		while(rs.next())
		{
			rs2Cliente(rs);
		}
	}
	public void loadOfrecen() throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("/Users/pandac/Downloads/OFRECEN.txt")));
		String[] lines = br.readLine().split(";");
		for(String line: lines)
		{
			try {
				PreparedStatement prepStmt = conn.prepareStatement(line);
				prepStmt.executeQuery();
				System.out.println("INSERTA");
			} catch (Exception e) {
			}
		}
	}
	public void rs2Cliente(ResultSet rs) throws SQLException
	{
		Cliente c = new Cliente(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), new ArrayList<Contrato>());
		System.out.println("NOMBRE: " + c.getNombre() + " ID: " + c.getId() + " Correo: " + c.getCorreo());
	}
	private String dateParser(Date d)
	{
		String resp[] = d.toString().split("-");
		return resp[2] + "/" + resp[1] + "/" + resp[0];
	}
}
