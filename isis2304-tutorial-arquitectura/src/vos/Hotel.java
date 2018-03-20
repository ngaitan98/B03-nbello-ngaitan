package vos;

import java.io.File;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class Hotel extends Operador 
{
	@JsonProperty(value="documentos")
	private ArrayList<File> documentos;
	
	public Hotel(@JsonProperty(value="id") Long id , @JsonProperty(value="login") String login,	@JsonProperty(value="nombre") String nombre, 
	@JsonProperty(value="correo") String correo, @JsonProperty(value="password") String password, 
	@JsonProperty(value="cuentaBancaria")String cuentaBancaria, @JsonProperty(value="documento")String documento, @JsonProperty(value="clienteEquivalente") Cliente clienteEquivalente,
	@JsonProperty(value="habitaciones")ArrayList<Alojamiento> habitaciones,@JsonProperty(value="documentos") ArrayList<File> documentos) 
	{
		super(id, login, nombre, correo, password, cuentaBancaria, documento, clienteEquivalente, habitaciones);
		this.documentos = documentos;
	}

	public ArrayList<File> getDocumentos() 
	{
		return documentos;
	}

	public void setDocumentos(ArrayList<File> documentos) {
		this.documentos = documentos;
	}
	
}
