package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class Cliente 
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	@JsonProperty(value = "id")
	private long id;

	@JsonProperty(value = "login")
	private String login;

	@JsonProperty (value = "password")
	private String password;

	@JsonProperty(value = "nombre")
	private String nombre;

	@JsonProperty (value = "correo")
	private String correo;

	@JsonProperty(value = "documento")
	private String documento;

	@JsonProperty(value = "operador")
	private Operador operadorEquivalente;

	@JsonProperty(value = "contratos")
	private ArrayList<Contrato> contratos;
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	public Cliente ( @JsonProperty(value = "id") long id, @JsonProperty(value = "login") String login, @JsonProperty (value = "password") String password, 
			@JsonProperty(value = "nombre") String nombre, @JsonProperty (value = "correo") String correo, @JsonProperty(value = "documento") String documento,
			@JsonProperty(value = "operador") Operador operadorEquivalente, @JsonProperty(value = "contratos") ArrayList<Contrato> contratos)
	{
		this.id = id;
		this.login = login;
		this.password = password;
		this.nombre = nombre;
		this.correo = correo;
		this.documento = documento;
		this.operadorEquivalente = operadorEquivalente;
		this.contratos = contratos;
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------

	public long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public String getDocumento() {
		return documento;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Operador getOperadorEquivalente() {
		return operadorEquivalente;
	}

	public void setOperadorEquivalente(Operador operadorEquivalente) {
		this.operadorEquivalente = operadorEquivalente;
	}

	public ArrayList<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(ArrayList<Contrato> contratos) {
		this.contratos = contratos;
	}
}
