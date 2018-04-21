package vos;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReservaGrupal {
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
	@JsonProperty(value = "id")
	private Long id;
	@JsonProperty(value = "cantidad")
	private Integer cantidad;
	@JsonProperty(value = "contratos")
	private ArrayList<Contrato> contratos;
	@JsonProperty(value = "tipo")
	private String tipo;
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public ReservaGrupal (@JsonProperty(value = "id")Long id, 
			@JsonProperty(value = "cantidad")Integer cantidad,
			@JsonProperty(value = "contratos")ArrayList<Contrato> contratos,
			@JsonProperty(value = "tipo")String tipo)
	{
		this.id = id;
		this.cantidad = cantidad;
		this.contratos = contratos;
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

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public ArrayList<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(ArrayList<Contrato> contratos) {
		this.contratos = contratos;
	}
		
}
