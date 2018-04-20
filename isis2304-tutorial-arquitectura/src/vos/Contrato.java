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
	private Date fechainicio;
	@JsonProperty(value = "fechafin")
	private Date fechafin;
	@JsonProperty(value = "fechaCreacion")
	private Date fechaCreacion;
	@JsonProperty(value = "precio")
	private Double precio;
	@JsonProperty(value = "finalizado")
	private Integer finalizado;
	@JsonProperty(value = "cantidadPersonas")
	private Integer cantidadPersonas;
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	public Contrato(@JsonProperty(value = "id")Long id, @JsonProperty(value = "fechainicio")Date fechainicio, @JsonProperty(value = "fechafin") Date fechafin,
			@JsonProperty(value = "fechaCreacion") Date fechaCreacion, @JsonProperty(value = "precio") Double precio, @JsonProperty(value = "cantidadPersonas") Integer cantidadPersonas,
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
	public Date getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}
	public Date getFechafin() {
		return fechafin;
	}
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
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
