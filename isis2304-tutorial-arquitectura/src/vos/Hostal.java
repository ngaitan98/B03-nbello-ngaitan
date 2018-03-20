package vos;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class Hostal extends Operador
{
	@JsonProperty(value="documentos")
	private ArrayList<File> documentos;
	
	@JsonProperty(value="horaInicio")
	private Date horaInicio;
	
	@JsonProperty(value="horaFin")
	private Date horaFin;

	public Hostal(	@JsonProperty(value="id") Long id , 	@JsonProperty(value="login") String login,	@JsonProperty(value="nombre")
	String nombre, @JsonProperty(value="correo") String correo, @JsonProperty(value="password") String password, 
	@JsonProperty(value="cuentaBancaria")String cuentaBancaria, @JsonProperty(value="documento")String documento, @JsonProperty(value="clienteEquivalente") Cliente clienteEquivalente,
	@JsonProperty(value="habitaciones")ArrayList<Alojamiento> habitaciones,@JsonProperty(value="documentos") ArrayList<File> documentos,
	@JsonProperty(value="horaInicio") Date horaInicio, @JsonProperty(value="horaFin") Date horaFin) 
	{
		super(id, login, nombre, correo, password, cuentaBancaria, documento, clienteEquivalente, habitaciones);
		this.documentos = documentos;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	public ArrayList<File> getDocumentos() 
	{
		return documentos;
	}

	public void setDocumentos(ArrayList<File> documentos) {
		this.documentos = documentos;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}
	
}
