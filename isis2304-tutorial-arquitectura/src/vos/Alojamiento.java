package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class Alojamiento 
{
	public final static String[] TipoAlojamiento = {"SUITE","SENCILLA","COMPARTIDA","APARTAMENTO", "CASA", "ESTANDAR"};
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
	@JsonProperty(value = "id")
	private long id;
	@JsonProperty(value = "numeroCupos")
	private Integer numeroCupos;
	@JsonProperty(value = "costoBase")
	private Integer costoBase;
	@JsonProperty(value = "ubicacion")
	private String ubicacion;
	@JsonProperty(value = "tipo")
	private String tipo;
	@JsonProperty(value = "ocupado")
	private Integer ocupado;
	@JsonProperty(value = "contratos" )
	private ArrayList<Contrato> contratos;
	@JsonProperty(value = "servicios" )
	private ArrayList<Servicio> servicios;
	@JsonProperty(value = "operador" )
	private Operador operador;
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	public Alojamiento( @JsonProperty(value = "id")Long id, @JsonProperty(value = "numeroCupos")Integer numeroCupos, 
			@JsonProperty(value = "costoBase")Integer costoBase, @JsonProperty(value = "ubicacion") String ubicacion, 	@JsonProperty(value = "tipo") String tipo,
			@JsonProperty(value = "ocupado") Integer ocupado, @JsonProperty(value = "contratos" ) ArrayList<Contrato> contratos,
			@JsonProperty(value = "servicios" ) ArrayList<Servicio> servicios, @JsonProperty(value = "operador" )Operador operador)
	{
		this.id = id;
		this.numeroCupos = numeroCupos;
		this.costoBase=costoBase;
		this.ubicacion = ubicacion;
		this.tipo = tipo;
		this.ocupado = ocupado;
		this.contratos = contratos;
		this.servicios = servicios;
		this.operador = operador;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getNumeroCupos() {
		return numeroCupos;
	}

	public void setNumeroCupos(Integer numeroCupos) {
		this.numeroCupos = numeroCupos;
	}

	public Integer getCostoBase() {
		return costoBase;
	}

	public void setCostoBase(Integer costoBase) {
		this.costoBase = costoBase;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getOcupado() {
		return ocupado;
	}

	public void setOcupado(Integer ocupado) {
		this.ocupado = ocupado;
	}
	public Boolean isOcupado()
	{
		return ocupado == 1;
	}

	public ArrayList<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(ArrayList<Contrato> contratos) {
		this.contratos = contratos;
	}

	public ArrayList<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

}
