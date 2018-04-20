package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Contrato 
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
	@JsonProperty(value = "id")
	private Long id;
	@JsonProperty(value = "fechainicio")
	private String fechainicio;
	@JsonProperty(value = "fechafin")
	private String fechafin;
	@JsonProperty(value = "fechaCreacion")
	private String fechaCreacion;
	@JsonProperty(value = "precio")
	private Double precio;
	@JsonProperty(value = "finalizado")
	private Integer finalizado;
	@JsonProperty(value = "cantidadPersonas")
	private Integer cantidadPersonas;
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	public Contrato(@JsonProperty(value = "id")Long id, @JsonProperty(value = "fechainicio")String fechainicio, @JsonProperty(value = "fechafin") String fechafin,
			@JsonProperty(value = "fechaCreacion") String fechaCreacion, @JsonProperty(value = "precio") Double precio, @JsonProperty(value = "cantidadPersonas") Integer cantidadPersonas,
			@JsonProperty(value = "finalizado")Integer finalizado)
	{
		this.id = id;
		this.fechainicio = fechainicio;
		this.fechafin = fechafin;
		this.precio= precio;
		this.fechaCreacion = fechaCreacion;
		this.finalizado = finalizado;
		this.cantidadPersonas = cantidadPersonas;
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
	public String getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(String fechainicio) {
		this.fechainicio = fechainicio;
	}
	public String getFechafin() {
		return fechafin;
	}
	public void setFechafin(String fechafin) {
		this.fechafin = fechafin;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Integer getFinalizado() {
		return finalizado;
	}
	public void setFinalizado(Integer finalizado) {
		this.finalizado = finalizado;
	}
	public boolean isFinalizado()
	{
		return finalizado == 1;
	}
	public Integer getCanitadPersonas() {
		return cantidadPersonas;
	}
	public void setCantidadPersonas(Integer cantidadPersonas)
	{
		this.cantidadPersonas = cantidadPersonas;
	}

}
