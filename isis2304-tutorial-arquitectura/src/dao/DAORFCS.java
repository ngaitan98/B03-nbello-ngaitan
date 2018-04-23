package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public ResultSet getDineroProveedores () throws SQLException, Exception
	{
		String sql = String.format("SELECT * FROM (SELECT * FROM ("
				+ "SELECT D.OPERADOR,D.TIPO, %1$s.DUENOVIVIENDA.NOMBRE,D.ALOJAMIENTO,D.DINERO_AÑO_COMPLETO,D.DINERO_AÑO_CORRIDO FROM("
				+ "(SELECT %1$s.OFRECEN.ID_OPERADOR as  OPERADOR, %1$s.OFRECEN.TIPO AS TIPO,  B.ALOJAMIENTO, B.DINERO_AÑO_COMPLETO,B.DINERO_AÑO_CORRIDO FROM("
				+ "(SELECT NVL(m.ALOJAMIENTO,n.ALOJAMIENTO) AS ALOJAMIENTO, NVL(DINERO_AÑO_COMPLETO,DINERO_AÑO_CORRIDO)AS DINERO_AÑO_COMPLETO, NVL(DINERO_AÑO_CORRIDO,0) AS DINERO_AÑO_CORRIDO FROM("
				+ "(SELECT f.ID_ALOJAMIENTO AS ALOJAMIENTO, g.PRECIO as DINERO_AÑO_COMPLETO FROM("
				+ "(SELECT * FROM %1$s.CONTRATOS WHERE FINALIZADO ='0'AND FECHAFIN LIKE'%/18')g"
				+ "INNER JOIN (%1$s.CONTRATARON)f ON f.ID_CONTRATO = g.ID))m"
				+ "full OUTER JOIN ("
				+ "SELECT ALOJAMIENTO,  DINERO_AÑO_CORRIDO FROM("
				+ "SELECT j.ID_ALOJAMIENTO AS ALOJAMIENTO, k.PRECIO as DINERO_AÑO_CORRIDO FROM("
				+ "(SELECT * FROM %1$s.CONTRATOS WHERE FINALIZADO ='1')k"
				+ "INNER JOIN (%1$s.CONTRATARON)j ON j.ID_CONTRATO = k.ID)))n ON n.ALOJAMIENTO = m.ALOJAMIENTO))B"
				+ "INNER JOIN (%1$s.OFRECEN) ON%1$s. OFRECEN.ID_ALOJAMIENTO = B.ALOJAMIENTO))D INNER JOIN %1$s.DUENOVIVIENDA ON  %1$s.DUENOVIVIENDA.ID = D.OPERADOR))"
				+ "UNION "
				+ "SELECT * FROM "
				+ "(SELECT F.OPERADOR,F.TIPO, %1$s.HOSTALES.NOMBRE,F.ALOJAMIENTO,F.DINERO_AÑO_COMPLETO,F.DINERO_AÑO_CORRIDO FROM("
				+ "(SELECT %1$s.OFRECEN.ID_OPERADOR as  OPERADOR, %1$s.OFRECEN.TIPO AS TIPO,  B.ALOJAMIENTO, B.DINERO_AÑO_COMPLETO,B.DINERO_AÑO_CORRIDO FROM("
				+ "(SELECT NVL(m.ALOJAMIENTO,n.ALOJAMIENTO) AS ALOJAMIENTO, NVL(DINERO_AÑO_COMPLETO,DINERO_AÑO_CORRIDO)AS DINERO_AÑO_COMPLETO, NVL(DINERO_AÑO_CORRIDO,0) AS DINERO_AÑO_CORRIDO FROM("
				+ "(SELECT f.ID_ALOJAMIENTO AS ALOJAMIENTO, g.PRECIO as DINERO_AÑO_COMPLETO FROM("
				+ "(SELECT * FROM %1$s.CONTRATOS WHERE FINALIZADO ='0'AND FECHAFIN LIKE'%/18')g"
				+ "INNER JOIN (%1$s.CONTRATARON)f ON f.ID_CONTRATO = g.ID))m"
				+ "full OUTER JOIN ("
				+ "SELECT ALOJAMIENTO,  DINERO_AÑO_CORRIDO FROM("
				+ "SELECT j.ID_ALOJAMIENTO AS ALOJAMIENTO, k.PRECIO as DINERO_AÑO_CORRIDO FROM("
				+ "(SELECT * FROM %1$s.CONTRATOS WHERE FINALIZADO ='1')k"
				+ "INNER JOIN (%1$s.CONTRATARON)j ON j.ID_CONTRATO = k.ID)))n ON n.ALOJAMIENTO = m.ALOJAMIENTO))B"
				+ "INNER JOIN (%1$s.OFRECEN) ON %1$s.OFRECEN.ID_ALOJAMIENTO = B.ALOJAMIENTO))F INNER JOIN %1$s.HOSTALES ON  %1$s.HOSTALES.ID = F.OPERADOR)))"
				+ "UNION"
				+ "SELECT * FROM "
				+ "(SELECT * FROM "
				+ "(SELECT A.OPERADOR,A.TIPO, %1$s.PERSONASNORMALES.NOMBRE,A.ALOJAMIENTO,A.DINERO_AÑO_COMPLETO,A.DINERO_AÑO_CORRIDO FROM("
				+ "(SELECT OFRECEN.ID_OPERADOR as  OPERADOR, OFRECEN.TIPO AS TIPO,  B.ALOJAMIENTO, B.DINERO_AÑO_COMPLETO,B.DINERO_AÑO_CORRIDO FROM("
				+ "(SELECT NVL(m.ALOJAMIENTO,n.ALOJAMIENTO) AS ALOJAMIENTO, NVL(DINERO_AÑO_COMPLETO,DINERO_AÑO_CORRIDO)AS DINERO_AÑO_COMPLETO, NVL(DINERO_AÑO_CORRIDO,0) AS DINERO_AÑO_CORRIDO FROM"
				+ "(SELECT f.ID_ALOJAMIENTO AS ALOJAMIENTO, g.PRECIO as DINERO_AÑO_COMPLETO FROM("
				+ "(SELECT * FROM %1$s.CONTRATOS WHERE FINALIZADO ='0'AND FECHAFIN LIKE'%/18')g"
				+ "INNER JOIN (%1$s.CONTRATARON)f ON f.ID_CONTRATO = g.ID))m"
				+ "full OUTER JOIN ("
				+ "SELECT ALOJAMIENTO,  DINERO_AÑO_CORRIDO FROM("
				+ "SELECT j.ID_ALOJAMIENTO AS ALOJAMIENTO, k.PRECIO as DINERO_AÑO_CORRIDO FROM("
				+ "(SELECT * FROM %1$s.CONTRATOS WHERE FINALIZADO ='1')k"
				+ "INNER JOIN (%1$s.CONTRATARON)j ON j.ID_CONTRATO = k.ID)))n ON n.ALOJAMIENTO = m.ALOJAMIENTO))B"
				+ "INNER JOIN (%1$s.OFRECEN) ON %1$s.OFRECEN.ID_ALOJAMIENTO = B.ALOJAMIENTO))A INNER JOIN %1$s.PERSONASNORMALES ON  %1$s.PERSONASNORMALES.ID = A.OPERADOR))"
				+ "UNION "
				+ "SELECT * FROM "
				+ "(SELECT c.OPERADOR,c.TIPO, %1$s.hoteles.NOMBRE,c.ALOJAMIENTO,c.DINERO_AÑO_COMPLETO,c.DINERO_AÑO_CORRIDO FROM("
				+ "(SELECT OFRECEN.ID_OPERADOR as  OPERADOR, OFRECEN.TIPO AS TIPO,  B.ALOJAMIENTO, B.DINERO_AÑO_COMPLETO,B.DINERO_AÑO_CORRIDO FROM("
				+ "(SELECT NVL(m.ALOJAMIENTO,n.ALOJAMIENTO) AS ALOJAMIENTO, NVL(DINERO_AÑO_COMPLETO,DINERO_AÑO_CORRIDO)AS DINERO_AÑO_COMPLETO, NVL(DINERO_AÑO_CORRIDO,0) AS DINERO_AÑO_CORRIDO FROM("
				+ "(SELECT f.ID_ALOJAMIENTO AS ALOJAMIENTO, g.PRECIO as DINERO_AÑO_COMPLETO FROM("
				+ "(SELECT * FROM %1$s.CONTRATOS WHERE FINALIZADO ='0'AND FECHAFIN LIKE'%/18')g"
				+ "INNER JOIN (%1$s.CONTRATARON)f ON f.ID_CONTRATO = g.ID))m"
				+ "full OUTER JOIN ("
				+ "SELECT ALOJAMIENTO,  DINERO_AÑO_CORRIDO FROM("
				+ "SELECT j.ID_ALOJAMIENTO AS ALOJAMIENTO, k.PRECIO as DINERO_AÑO_CORRIDO FROM("
				+ "(SELECT * FROM %1$s.CONTRATOS WHERE FINALIZADO ='1')k"
				+ "INNER JOIN (%1$s.CONTRATARON)j ON j.ID_CONTRATO = k.ID)))n ON n.ALOJAMIENTO = m.ALOJAMIENTO))B"
				+ "INNER JOIN (%1$s.OFRECEN) ON %1$s.OFRECEN.ID_ALOJAMIENTO = B.ALOJAMIENTO))c INNER JOIN %1$s.HOTELES ON  HOTELES.ID = c.OPERADOR)))", USUARIO);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		return rs;
	}
	
	public ResultSet getOfertasPopulares() throws SQLException, Exception
	{
		String sql = String.format("SELECT COUNT( %1$s.CONTRATARON.ID_CONTRATO) AS VENTAS,  %1$s.CONTRATARON.ID_ALOJAMIENTO AS ALOJAMIENTO FROM %1$s.CONTRATARON WHERE ROWNUM <= 20 group by  %1$s.CONTRATARON.ID_ALOJAMIENTO order by ventas desc",
				USUARIO);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		return rs;
	}
	
	public ResultSet IndiceAlojamientos() throws SQLException, Exception
	{
		String sql = String.format("Select %1$s.alojamientoS.Id as Alojamiento, ocupada from  %1$s.alojamientoS;",
				USUARIO);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		return rs;
	}
	
	public ResultSet alojamientosdisponibles( String fechainicio, String fechafin, String servicio) throws SQLException, Exception
	{
		String sql = String.format("select * from (\r\n" + 
				"select j.*, servicios.nombre as nombre, servicios.costoagregado as costoagregado from (\r\n" + 
				"(select h.*, tienen.id_servicio as servicio from (\r\n" + 
				"(select %1$s.alojamientos.* from (\r\n" + 
				" %1$s.alojamientos inner join (\r\n" + 
				"select  %1$s.contrataron.id_alojamiento as alojamiento from(  %1$s.contrataron inner join (SELECT * FROM  %1$s.CONTRATOS WHERE  FECHAFIN < to_date('%2$s', 'dd/mm/yy') OR FECHAINICIO > to_date('%3$s' , 'dd/mm/yy'))\r\n" + 
				"f on  %1$s.contrataron.id_contrato = f.id))g on  %1$s.alojamientos.id = g.alojamiento) where ocupada = '0')h inner join\r\n" + 
				" %1$s.tienen on h.id =  %1$s.tienen.id_alojamiento))j inner join\r\n" + 
				" %1$s.servicios on j.servicio =  %1$s.servicios.id ) ) where nombre like '%%4$s' ;",
				USUARIO, fechainicio,fechafin,servicio);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		return rs;
	}
	public ResultSet UsoAlhoAndesOperarios() throws SQLException, Exception
	{
		String sql = String.format("SELECT * FROM\r\n" + 
				"(SELECT * FROM (\r\n" + 
				"SELECT %1$s.DUENOVIVIENDA.ID AS OPERADOR%1$s.,OFRECEN.TIPO AS TIPO, %1$s.DUENOVIVIENDA.NOMBRE AS NOMBRE,\r\n" + 
				"%1$s.OFRECEN.ID_ALOJAMIENTO AS ALOJAMIENTO FROM \r\n" + 
				"%1$s.OFRECEN INNER JOIN %1$s.DUENOVIVIENDA ON %1$s.DUENOVIVIENDA.ID=%1$s.OFRECEN.ID_OPERADOR)\r\n" + 
				"UNION\r\n" + 
				"SELECT * FROM (\r\n" + 
				"SELECT %1$s.HOSTALES.ID AS OPERADOR,%1$s.OFRECEN.TIPO AS TIPO, %1$s.HOSTALES.NOMBRE AS NOMBRE,\r\n" + 
				"%1$s.OFRECEN.ID_ALOJAMIENTO AS ALOJAMIENTO FROM \r\n" + 
				"%1$s.OFRECEN INNER JOIN %1$s.HOSTALES ON %1$s.HOSTALES.ID=%1$s.OFRECEN.ID_OPERADOR))\r\n" + 
				"UNION\r\n" + 
				"SELECT *FROM\r\n" + 
				"(SELECT * FROM (\r\n" + 
				"SELECT %1$s.HOTELES.ID AS OPERADOR,%1$s.OFRECEN.TIPO AS TIPO, %1$s.HOTELES.NOMBRE AS NOMBRE,\r\n" + 
				"%1$s.OFRECEN.ID_ALOJAMIENTO AS ALOJAMIENTO FROM \r\n" + 
				"%1$s.OFRECEN INNER JOIN %1$s.HOTELES ON %1$s.HOTELES.ID=%1$s.OFRECEN.ID_OPERADOR)\r\n" + 
				"UNION\r\n" + 
				"SELECT * FROM (\r\n" + 
				"SELECT %1$s.PERSONASNORMALES.ID AS OPERADOR,%1$s.OFRECEN.TIPO AS TIPO, %1$s.PERSONASNORMALES.NOMBRE AS NOMBRE,\r\n" + 
				"%1$s.OFRECEN.ID_ALOJAMIENTO AS ALOJAMIENTO FROM \r\n" + 
				"%1$s.OFRECEN INNER JOIN %1$s.PERSONASNORMALES ON %1$s.PERSONASNORMALES.ID=%1$s.OFRECEN.ID_OPERADOR))",
				USUARIO);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		return rs;
	}
	public ResultSet UsoAlhoAndesClientes() throws SQLException, Exception
	{
		String sql = String.format("Select\r\n" + 
				"f.ID,f.NOMBRE,f.ALOJAMIENTO,f.CONTRATO, g.FECHAINICIO,g.FECHAFIN,g.PRECIO AS DINERO_PAGADO,g.FINALIZADO,g.CANTIDADPERSONAS\r\n" + 
				"FROM\r\n" + 
				"((SELECT \r\n" + 
				" %1$s.CLIENTES.ID, %1$s.CLIENTES.NOMBRE,  %1$s.CONTRATARON.ID_ALOJAMIENTO AS ALOJAMIENTO, %1$s.CONTRATARON.ID_CONTRATO AS CONTRATO\r\n" + 
				"FROM %1$s.CLIENTES INNER JOIN  %1$s.CONTRATARON ON  %1$s.CLIENTES.ID= %1$s.CONTRATARON.ID_CLIENTE) f \r\n" + 
				"INNER JOIN ( %1$s.CONTRATOS)g ON f.CONTRATO=g.ID);",
				USUARIO);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		return rs;
	}
}
