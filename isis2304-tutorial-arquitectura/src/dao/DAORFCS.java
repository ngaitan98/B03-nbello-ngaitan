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
	
	public String getDineroProveedores () throws SQLException, Exception
	{
		String sql = String.format("SELECT * FROM \n" + 
				"(SELECT * FROM \n" + 
				"(SELECT D.OPERADOR,D.TIPO, ISIS2304A471810.DUENOVIVIENDA.NOMBRE,D.ALOJAMIENTO,D.DINERO_AÑO_COMPLETO,D.DINERO_AÑO_CORRIDO FROM(\n" + 
				"(SELECT ISIS2304A471810.OFRECEN.ID_OPERADOR as  OPERADOR, ISIS2304A471810.OFRECEN.TIPO AS TIPO,  B.ALOJAMIENTO, B.DINERO_AÑO_COMPLETO,B.DINERO_AÑO_CORRIDO FROM(\n" + 
				"(SELECT NVL(m.ALOJAMIENTO,n.ALOJAMIENTO) AS ALOJAMIENTO, NVL(DINERO_AÑO_COMPLETO,DINERO_AÑO_CORRIDO)AS DINERO_AÑO_COMPLETO, NVL(DINERO_AÑO_CORRIDO,0) AS DINERO_AÑO_CORRIDO FROM(\n" + 
				"(SELECT f.ID_ALOJAMIENTO AS ALOJAMIENTO, g.PRECIO as DINERO_AÑO_COMPLETO FROM(\n" + 
				"(SELECT * FROM ISIS2304A471810.CONTRATOS WHERE ISIS2304A471810.CONTRATOS.FINALIZADO ='0'AND ISIS2304A471810.CONTRATOS.FECHAFIN LIKE'%/18')g\n" + 
				"INNER JOIN (ISIS2304A471810.CONTRATARON)f ON f.ID_CONTRATO = g.ID))m\n" + 
				"full OUTER JOIN (\n" + 
				"SELECT ALOJAMIENTO,  DINERO_AÑO_CORRIDO FROM(\n" + 
				"SELECT j.ID_ALOJAMIENTO AS ALOJAMIENTO, k.PRECIO as DINERO_AÑO_CORRIDO FROM(\n" + 
				"(SELECT * FROM ISIS2304A471810.CONTRATOS WHERE ISIS2304A471810.CONTRATOS.FINALIZADO ='1')k\n" + 
				"INNER JOIN (ISIS2304A471810.CONTRATARON)j ON j.ID_CONTRATO = k.ID)))n ON n.ALOJAMIENTO = m.ALOJAMIENTO))B\n" + 
				"INNER JOIN (ISIS2304A471810.OFRECEN) ON ISIS2304A471810.OFRECEN.ID_ALOJAMIENTO = B.ALOJAMIENTO))D INNER JOIN ISIS2304A471810.DUENOVIVIENDA ON ISIS2304A471810.DUENOVIVIENDA.ID = D.OPERADOR))\n" + 
				"UNION \n" + 
				"SELECT * FROM \n" + 
				"(SELECT F.OPERADOR,F.TIPO, HOSTALES.NOMBRE,F.ALOJAMIENTO,F.DINERO_AÑO_COMPLETO,F.DINERO_AÑO_CORRIDO FROM(\n" + 
				"(SELECT ISIS2304A471810.OFRECEN.ID_OPERADOR as  OPERADOR, ISIS2304A471810.OFRECEN.TIPO AS TIPO,  B.ALOJAMIENTO, B.DINERO_AÑO_COMPLETO,B.DINERO_AÑO_CORRIDO FROM(\n" + 
				"(SELECT NVL(m.ALOJAMIENTO,n.ALOJAMIENTO) AS ALOJAMIENTO, NVL(DINERO_AÑO_COMPLETO,DINERO_AÑO_CORRIDO)AS DINERO_AÑO_COMPLETO, NVL(DINERO_AÑO_CORRIDO,0) AS DINERO_AÑO_CORRIDO FROM(\n" + 
				"(SELECT f.ID_ALOJAMIENTO AS ALOJAMIENTO, g.PRECIO as DINERO_AÑO_COMPLETO FROM(\n" + 
				"(SELECT * FROM ISIS2304A471810.CONTRATOS WHERE ISIS2304A471810.CONTRATOS.FINALIZADO ='0'AND ISIS2304A471810.CONTRATOS.FECHAFIN LIKE'%/18')g\n" + 
				"INNER JOIN (ISIS2304A471810.CONTRATARON)f ON f.ID_CONTRATO = g.ID))m\n" + 
				"full OUTER JOIN (\n" + 
				"SELECT ALOJAMIENTO,  DINERO_AÑO_CORRIDO FROM(\n" + 
				"SELECT j.ID_ALOJAMIENTO AS ALOJAMIENTO, k.PRECIO as DINERO_AÑO_CORRIDO FROM(\n" + 
				"(SELECT * FROM ISIS2304A471810.CONTRATOS WHERE ISIS2304A471810.CONTRATOS.FINALIZADO ='1')k\n" + 
				"INNER JOIN (ISIS2304A471810.CONTRATARON)j ON j.ID_CONTRATO = k.ID)))n ON n.ALOJAMIENTO = m.ALOJAMIENTO))B\n" + 
				"INNER JOIN (ISIS2304A471810.OFRECEN) ON ISIS2304A471810.OFRECEN.ID_ALOJAMIENTO = B.ALOJAMIENTO))F INNER JOIN ISIS2304A471810.HOSTALES ON  ISIS2304A471810.HOSTALES.ID = F.OPERADOR)))\n" + 
				"UNION\n" + 
				"SELECT * FROM \n" + 
				"(SELECT * FROM \n" + 
				"(SELECT A.OPERADOR,A.TIPO, ISIS2304A471810.PERSONASNORMALES.NOMBRE,A.ALOJAMIENTO,A.DINERO_AÑO_COMPLETO,A.DINERO_AÑO_CORRIDO FROM(\n" + 
				"(SELECT ISIS2304A471810.OFRECEN.ID_OPERADOR as  OPERADOR, ISIS2304A471810.OFRECEN.TIPO AS TIPO,  B.ALOJAMIENTO, B.DINERO_AÑO_COMPLETO,B.DINERO_AÑO_CORRIDO FROM(\n" + 
				"(SELECT NVL(m.ALOJAMIENTO,n.ALOJAMIENTO) AS ALOJAMIENTO, NVL(DINERO_AÑO_COMPLETO,DINERO_AÑO_CORRIDO)AS DINERO_AÑO_COMPLETO, NVL(DINERO_AÑO_CORRIDO,0) AS DINERO_AÑO_CORRIDO FROM(\n" + 
				"(SELECT f.ID_ALOJAMIENTO AS ALOJAMIENTO, g.PRECIO as DINERO_AÑO_COMPLETO FROM(\n" + 
				"(SELECT * FROM ISIS2304A471810.CONTRATOS WHERE ISIS2304A471810.CONTRATOS.FINALIZADO ='0'AND ISIS2304A471810.CONTRATOS.FECHAFIN LIKE'%/18')g\n" + 
				"INNER JOIN (ISIS2304A471810.CONTRATARON)f ON f.ID_CONTRATO = g.ID))m\n" + 
				"full OUTER JOIN (\n" + 
				"SELECT ALOJAMIENTO,  DINERO_AÑO_CORRIDO FROM(\n" + 
				"SELECT j.ID_ALOJAMIENTO AS ALOJAMIENTO, k.PRECIO as DINERO_AÑO_CORRIDO FROM(\n" + 
				"(SELECT * FROM ISIS2304A471810.CONTRATOS WHERE ISIS2304A471810.CONTRATOS.FINALIZADO ='1')k\n" + 
				"INNER JOIN (ISIS2304A471810.CONTRATARON)j ON j.ID_CONTRATO = k.ID)))n ON n.ALOJAMIENTO = m.ALOJAMIENTO))B\n" + 
				"INNER JOIN (ISIS2304A471810.OFRECEN) ON ISIS2304A471810.OFRECEN.ID_ALOJAMIENTO = B.ALOJAMIENTO))A INNER JOIN ISIS2304A471810.PERSONASNORMALES ON  ISIS2304A471810.PERSONASNORMALES.ID = A.OPERADOR))\n" + 
				"UNION \n" + 
				"SELECT * FROM \n" + 
				"(SELECT c.OPERADOR,c.TIPO, ISIS2304A471810.hoteles.NOMBRE,c.ALOJAMIENTO,c.DINERO_AÑO_COMPLETO,c.DINERO_AÑO_CORRIDO FROM(\n" + 
				"(SELECT ISIS2304A471810.OFRECEN.ID_OPERADOR as  OPERADOR, ISIS2304A471810.OFRECEN.TIPO AS TIPO,  B.ALOJAMIENTO, B.DINERO_AÑO_COMPLETO,B.DINERO_AÑO_CORRIDO FROM(\n" + 
				"(SELECT NVL(m.ALOJAMIENTO,n.ALOJAMIENTO) AS ALOJAMIENTO, NVL(DINERO_AÑO_COMPLETO,DINERO_AÑO_CORRIDO)AS DINERO_AÑO_COMPLETO, NVL(DINERO_AÑO_CORRIDO,0) AS DINERO_AÑO_CORRIDO FROM(\n" + 
				"(SELECT f.ID_ALOJAMIENTO AS ALOJAMIENTO, g.PRECIO as DINERO_AÑO_COMPLETO FROM(\n" + 
				"(SELECT * FROM ISIS2304A471810.CONTRATOS WHERE ISIS2304A471810.CONTRATOS.FINALIZADO ='0'AND ISIS2304A471810.CONTRATOS.FECHAFIN LIKE'%/18')g\n" + 
				"INNER JOIN (ISIS2304A471810.CONTRATARON)f ON f.ID_CONTRATO = g.ID))m\n" + 
				"full OUTER JOIN (\n" + 
				"SELECT ALOJAMIENTO,  DINERO_AÑO_CORRIDO FROM(\n" + 
				"SELECT j.ID_ALOJAMIENTO AS ALOJAMIENTO, k.PRECIO as DINERO_AÑO_CORRIDO FROM(\n" + 
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
		String sql = String.format(" SELECT COUNT( %1$s.CONTRATARON.ID_CONTRATO) AS VENTAS,  %1$s.CONTRATARON.ID_ALOJAMIENTO AS ALOJAMIENTO FROM %1$s.CONTRATARON WHERE ROWNUM <= 20 group by  %1$s.CONTRATARON.ID_ALOJAMIENTO order by ventas desc",
				USUARIO);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		return rs.getString(0);
	}
	
	public ResultSet IndiceAlojamientos() throws SQLException, Exception
	{
		String sql = String.format("Select %1$s.alojamientoS.Id as Alojamiento, ocupada from  %1$s.alojamientoS",
				USUARIO);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		return rs;
	}
	
	public ResultSet alojamientosdisponibles( String fechainicio, String fechafin, String servicio) throws SQLException, Exception
	{
		String sql = String.format("select * from (" + 
				" select j.*, servicios.nombre as nombre, servicios.costoagregado as costoagregado from (" + 
				" (select h.*, tienen.id_servicio as servicio from (" + 
				" (select %1$s.alojamientos.* from (" + 
				" %1$s.alojamientos inner join (" + 
				" select  %1$s.contrataron.id_alojamiento as alojamiento from(  %1$s.contrataron inner join (SELECT * FROM  %1$s.CONTRATOS WHERE  FECHAFIN < to_date('%2$s', 'dd/mm/yy') OR FECHAINICIO > to_date('%3$s' , 'dd/mm/yy'))" + 
				" f on  %1$s.contrataron.id_contrato = f.id))g on  %1$s.alojamientos.id = g.alojamiento) where ocupada = '0')h inner join" + 
				" %1$s.tienen on h.id =  %1$s.tienen.id_alojamiento))j inner join" + 
				" %1$s.servicios on j.servicio =  %1$s.servicios.id ) ) where nombre like '%%4$s' ;",
				USUARIO, fechainicio,fechafin,servicio);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		return rs;
	}
	public ResultSet UsoAlhoAndesOperarios() throws SQLException, Exception
	{
		String sql = String.format("SELECT * FROM" + 
				"(SELECT * FROM (" + 
				" SELECT %1$s.DUENOVIVIENDA.ID AS OPERADOR%1$s.,OFRECEN.TIPO AS TIPO, %1$s.DUENOVIVIENDA.NOMBRE AS NOMBRE," + 
				" %1$s.OFRECEN.ID_ALOJAMIENTO AS ALOJAMIENTO FROM " + 
				" %1$s.OFRECEN INNER JOIN %1$s.DUENOVIVIENDA ON %1$s.DUENOVIVIENDA.ID=%1$s.OFRECEN.ID_OPERADOR)" + 
				" UNION" + 
				" SELECT * FROM (" + 
				" SELECT %1$s.HOSTALES.ID AS OPERADOR,%1$s.OFRECEN.TIPO AS TIPO, %1$s.HOSTALES.NOMBRE AS NOMBRE," + 
				" %1$s.OFRECEN.ID_ALOJAMIENTO AS ALOJAMIENTO FROM " + 
				" %1$s.OFRECEN INNER JOIN %1$s.HOSTALES ON %1$s.HOSTALES.ID=%1$s.OFRECEN.ID_OPERADOR))" + 
				" UNION" + 
				" SELECT *FROM" + 
				" (SELECT * FROM (" + 
				" SELECT %1$s.HOTELES.ID AS OPERADOR,%1$s.OFRECEN.TIPO AS TIPO, %1$s.HOTELES.NOMBRE AS NOMBRE," + 
				 "%1$s.OFRECEN.ID_ALOJAMIENTO AS ALOJAMIENTO FROM " + 
				" %1$s.OFRECEN INNER JOIN %1$s.HOTELES ON %1$s.HOTELES.ID=%1$s.OFRECEN.ID_OPERADOR)" + 
				" UNION" + 
				" SELECT * FROM (" + 
				" SELECT %1$s.PERSONASNORMALES.ID AS OPERADOR,%1$s.OFRECEN.TIPO AS TIPO, %1$s.PERSONASNORMALES.NOMBRE AS NOMBRE," + 
				" %1$s.OFRECEN.ID_ALOJAMIENTO AS ALOJAMIENTO FROM " + 
				" %1$s.OFRECEN INNER JOIN %1$s.PERSONASNORMALES ON %1$s.PERSONASNORMALES.ID=%1$s.OFRECEN.ID_OPERADOR))",
				USUARIO);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		return rs;
	}
	public void UsoAlhoAndesClientes() throws SQLException, Exception
	{
		String sql = String.format("Select" + 
				"f.ID,f.NOMBRE,f.ALOJAMIENTO,f.CONTRATO, g.FECHAINICIO,g.FECHAFIN,g.PRECIO AS DINERO_PAGADO,g.FINALIZADO,g.CANTIDADPERSONAS" + 
				" FROM" + 
				"((SELECT " + 
				" %1$s.CLIENTES.ID, %1$s.CLIENTES.NOMBRE,  %1$s.CONTRATARON.ID_ALOJAMIENTO AS ALOJAMIENTO, %1$s.CONTRATARON.ID_CONTRATO AS CONTRATO" + 
				" FROM %1$s.CLIENTES INNER JOIN  %1$s.CONTRATARON ON  %1$s.CLIENTES.ID= %1$s.CONTRATARON.ID_CLIENTE) f " + 
				" INNER JOIN ( %1$s.CONTRATOS)g ON f.CONTRATO=g.ID);",
				USUARIO);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		while(rs.next())
		{
			System.out.println(rs.getLong(1));
		}
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
}
