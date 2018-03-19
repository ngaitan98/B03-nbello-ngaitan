package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Vivienda 
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
	
	public enum tipoVivienda {
		apartamento,
		casa
		}	
	
	@JsonProperty(value = "id")
	private Long id;
	@JsonProperty(value = "direccion")
	private String direccion;
	@JsonProperty(value = "tipo")
	private tipoVivienda tipo;
	@JsonProperty(value = "costoBase")
	private Integer costoBase;
	@JsonProperty(value = "ubicacion")
	private String ubicacion;

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	private Vivienda( Long id, String direccion, String tipo, Integer costoBase, String ubicacion) {
		this.id = id;
		this.direccion = direccion;
		this.costoBase = costoBase;
		this.ubicacion = ubicacion;
		if (tipo.equalsIgnoreCase(tipoVivienda.apartamento.toString()))
		{
			this.tipo =tipoVivienda.apartamento; 
		}
		if (tipo.equalsIgnoreCase(tipoVivienda.casa.toString()))
		{
			this.tipo =tipoVivienda.casa; 
		}
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public tipoVivienda getTipo() {
		return tipo;
	}

	public void setTipo(tipoVivienda tipo) {
		this.tipo = tipo;
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
