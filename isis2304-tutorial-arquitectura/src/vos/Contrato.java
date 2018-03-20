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
		@JsonProperty(value = "precio")
		private Double precio;
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODO CONSTRUCTOR
		//----------------------------------------------------------------------------------------------------------------------------------
		public Contrato(@JsonProperty(value = "id")Long id, @JsonProperty(value = "fechainicio")Date fechainicio, @JsonProperty(value = "fechafin") Date fechafin, @JsonProperty(value = "precio") Double precio)
		{
			this.id = id;
			this.fechainicio = fechainicio;
			this.fechafin = fechafin;
			this.precio= precio;
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
		
		
}
