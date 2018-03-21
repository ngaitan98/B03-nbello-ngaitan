package vos;

import java.io.File;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class Hostal extends Operador
{
	@JsonProperty(value="documentos")
	private ArrayList<File> documentos;
	
	@JsonProperty(value="horaInicio")
	private Integer horaInicio;
	
	@JsonProperty(value="horaFin")
	private Integer horaFin;

	public Hostal(	@JsonProperty(value="id") Long id , 	@JsonProperty(value="login") String login,	@JsonProperty(value="nombre")
	String nombre, @JsonProperty(value="correo") String correo, @JsonProperty(value="password") String password, 
	@JsonProperty(value="cuentaBancaria")String cuentaBancaria, @JsonProperty(value="documento")String documento, @JsonProperty(value="clienteEquivalente") Cliente clienteEquivalente,
	@JsonProperty(value="habitaciones")ArrayList<Alojamiento> habitaciones,@JsonProperty(value="documentos") ArrayList<File> documentos,
	@JsonProperty(value="horaInicio") Integer horaInicio, @JsonProperty(value="horaFin") Integer horaFin) 
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

	public Integer getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Integer horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Integer getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Integer horaFin) {
		this.horaFin = horaFin;
	}
}
