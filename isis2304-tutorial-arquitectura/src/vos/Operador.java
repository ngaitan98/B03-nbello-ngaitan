package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public abstract class Operador 
{
	@JsonProperty(value="id")
	protected Long id;
	@JsonProperty(value="login")
	protected String login;
	@JsonProperty(value="nombre")
	protected String nombre;
	@JsonProperty(value="correo")
	protected String correo; 
	@JsonProperty(value="password")
	protected String password; 
	@JsonProperty(value="cuentaBancaria")
	protected String cuentaBancaria;
	@JsonProperty(value="documento")
	protected String documento;
	@JsonProperty(value="clienteEquivalente")
	protected Cliente clienteEquivalente;
	@JsonProperty(value="habitaciones")
	protected ArrayList<Alojamiento> habitaciones;
	
	protected Operador(	@JsonProperty(value="id") Long id , 	@JsonProperty(value="login") String login,	@JsonProperty(value="nombre")
	String nombre, @JsonProperty(value="correo") String correo, @JsonProperty(value="password") String password, 
	@JsonProperty(value="cuentaBancaria")String cuentaBancaria, @JsonProperty(value="documento")String documento, @JsonProperty(value="clienteEquivalente") Cliente clienteEquivalente,
	@JsonProperty(value="habitaciones")ArrayList<Alojamiento> habitaciones) 
	{
		this.id = id;
		this.login = login;
		this.nombre = nombre;
		this.correo = correo;
		this.password = password;
		this.cuentaBancaria = cuentaBancaria;
		this.documento = documento;
		this.clienteEquivalente = clienteEquivalente;
		this.habitaciones = habitaciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Cliente getClienteEquivalente() {
		return clienteEquivalente;
	}

	public void setClienteEquivalente(Cliente clienteEquivalente) {
		this.clienteEquivalente = clienteEquivalente;
	}

	public ArrayList<Alojamiento> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(ArrayList<Alojamiento> habitaciones) {
		this.habitaciones = habitaciones;
	}
}
