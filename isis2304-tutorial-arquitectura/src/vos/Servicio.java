package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Servicio 
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	@JsonProperty(value = "id")
	private Long id;
	@JsonProperty(value = "nombre")	
	private String nombre;
	@JsonProperty(value = "descripcion")	
	private String descripcion;
	@JsonProperty(value = "costoAgregado")	
	private Integer costoAgregado;
	@JsonProperty(value = "alojamiento")
	private Alojamiento alojamiento;
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	public Servicio (@JsonProperty(value = "id")Long id,@JsonProperty(value = "nombre")	 String nombre, @JsonProperty(value = "descripcion") String descripcion, 
			@JsonProperty(value = "costoAgregado")	Integer costoAgregado, 	@JsonProperty(value = "alojamiento") Alojamiento alojamiento) 
	{
		this.id = id;
		this.nombre = nombre; 
		this.descripcion = descripcion;
		this.costoAgregado = costoAgregado;
		this.alojamiento = alojamiento;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCostoAgregado() {
		return costoAgregado;
	}

	public void setCostoAgregado(Integer costoAgregado) {
		this.costoAgregado = costoAgregado;
	}


	public Alojamiento getAlojamiento() {
		return alojamiento;
	}


	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}
	
}
