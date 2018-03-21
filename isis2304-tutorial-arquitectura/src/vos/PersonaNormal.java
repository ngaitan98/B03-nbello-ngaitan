package vos;

import java.io.File;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class PersonaNormal extends Operador
{
	public enum TipoPersonaNatural{VECINO, EGRESADO, INVITADO, EMPLEADO, ESTUDIANTE, PROFESOR};
	@JsonProperty(value="certificado")
	private String certificado;
	@JsonProperty(value="tipo")
	private TipoPersonaNatural tipo;
	public PersonaNormal(@JsonProperty(value="id") Long id , @JsonProperty(value="login") String login,	@JsonProperty(value="nombre") String nombre, 
			@JsonProperty(value="correo") String correo, @JsonProperty(value="password") String password, 
			@JsonProperty(value="cuentaBancaria")String cuentaBancaria, @JsonProperty(value="documento")String documento, @JsonProperty(value="clienteEquivalente") Cliente clienteEquivalente,
			@JsonProperty(value="habitaciones")ArrayList<Alojamiento> habitaciones,@JsonProperty(value="tipo") String tipo,	
			@JsonProperty(value="certificado") String certificado) 
	{
		super(id, login, nombre, correo, password, cuentaBancaria, documento, clienteEquivalente, habitaciones);
		this.certificado = certificado;
		if (tipo.equalsIgnoreCase(TipoPersonaNatural.EGRESADO.toString()))
		{
			this.tipo = TipoPersonaNatural.EGRESADO;
		}
		else if (tipo.equalsIgnoreCase(TipoPersonaNatural.VECINO.toString()))
		{
			this.tipo = TipoPersonaNatural.VECINO;
		}
		else if (tipo.equalsIgnoreCase(TipoPersonaNatural.INVITADO.toString()))
		{
			this.tipo = TipoPersonaNatural.INVITADO;
		}
		else if (tipo.equalsIgnoreCase(TipoPersonaNatural.EMPLEADO.toString()))
		{
			this.tipo = TipoPersonaNatural.EMPLEADO;
		}
		if (tipo.equalsIgnoreCase(TipoPersonaNatural.ESTUDIANTE.toString()))
		{
			this.tipo = TipoPersonaNatural.ESTUDIANTE;
		}
		if (tipo.equalsIgnoreCase(TipoPersonaNatural.PROFESOR.toString()))
		{
			this.tipo = TipoPersonaNatural.PROFESOR;
		}
	}
	public String getCertificado() {
		return certificado;
	}
	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}
	public String getTipo() {
		return tipo.toString();
	}
	public void setTipo(String tipo) {
		if (tipo.equalsIgnoreCase(TipoPersonaNatural.EGRESADO.toString()))
		{
			this.tipo = TipoPersonaNatural.EGRESADO;
		}
		else if (tipo.equalsIgnoreCase(TipoPersonaNatural.VECINO.toString()))
		{
			this.tipo = TipoPersonaNatural.VECINO;
		}
		else if (tipo.equalsIgnoreCase(TipoPersonaNatural.INVITADO.toString()))
		{
			this.tipo = TipoPersonaNatural.INVITADO;
		}
		else if (tipo.equalsIgnoreCase(TipoPersonaNatural.EMPLEADO.toString()))
		{
			this.tipo = TipoPersonaNatural.EMPLEADO;
		}
		if (tipo.equalsIgnoreCase(TipoPersonaNatural.ESTUDIANTE.toString()))
		{
			this.tipo = TipoPersonaNatural.ESTUDIANTE;
		}
		if (tipo.equalsIgnoreCase(TipoPersonaNatural.PROFESOR.toString()))
		{
			this.tipo = TipoPersonaNatural.PROFESOR;
		}
	}

}
