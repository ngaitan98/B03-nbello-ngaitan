package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Habitacion 
{
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
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	public Habitacion( @JsonProperty(value = "id")Long id, @JsonProperty(value = "numeroCupos")Integer numeroCupos, 
			@JsonProperty(value = "costoBase")Integer costoBase, @JsonProperty(value = "ubicacion") String ubicacion)
	{
		this.id = id;
		this.numeroCupos = numeroCupos;
		this.costoBase=costoBase;
		this.ubicacion = ubicacion;
		
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
	

	
	
}
