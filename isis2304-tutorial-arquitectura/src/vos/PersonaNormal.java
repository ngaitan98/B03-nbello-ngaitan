package vos;

import java.io.File;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class PersonaNormal extends Operador
{
	public enum TipoPersonaNatural{VECINO, EGRESADO, INVITADO, EMPLEADO, ESTUDIANTE, PROFESOR, PROFESOR_INVITADO};
	@JsonProperty(value="certificado")
	private String certificado;
	@JsonProperty(value="tipo")
	private String tipo;
	@JsonProperty(value="viviendas")
	private ArrayList<Vivienda> viviendas;
	public PersonaNormal(@JsonProperty(value="id") Integer id , @JsonProperty(value="login") String login,	@JsonProperty(value="nombre") String nombre, 
			@JsonProperty(value="correo") String correo, @JsonProperty(value="password") String password, 
			@JsonProperty(value="cuentaBancaria")String cuentaBancaria, @JsonProperty(value="documento")String documento, @JsonProperty(value="clienteEquivalente") Cliente clienteEquivalente,
			@JsonProperty(value="habitaciones")ArrayList<Habitacion> habitaciones,@JsonProperty(value="tipo") String tipo,	
			@JsonProperty(value="certificado") String certificado, @JsonProperty(value="viviendas") ArrayList<Vivienda> viviendas) 
	{
		super(id, login, nombre, correo, password, cuentaBancaria, documento, clienteEquivalente, habitaciones);
		this.certificado = certificado;
		this.viviendas = viviendas;
		this.tipo = tipo;
	}
	public String getCertificado() {
		return certificado;
	}
	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public ArrayList<Vivienda> getViviendas() {
		return viviendas;
	}
	public void setViviendas(ArrayList<Vivienda> viviendas) {
		this.viviendas = viviendas;
	}
	
}
