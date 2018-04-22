package vos;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReservaGrupal extends Contrato
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
	@JsonProperty(value = "contratos")
	private ArrayList<Cliente> clientes;
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

	public ReservaGrupal(@JsonProperty(value = "id")Long id, @JsonProperty(value = "fechainicio")String fechainicio, @JsonProperty(value = "fechafin") String fechafin,
			@JsonProperty(value = "fechaCreacion") String fechaCreacion, @JsonProperty(value = "precio") Double precio, @JsonProperty(value = "cantidadPersonas") Integer cantidadPersonas,
			@JsonProperty(value = "finalizado")Integer finalizado, @JsonProperty(value = "clientes")ArrayList<Cliente> contratos,
			@JsonProperty(value = "tipo")String tipo)
	{
		super(id, fechainicio, fechafin, fechaCreacion, precio, cantidadPersonas, finalizado);
		this.clientes = contratos;
		this.tipo = tipo;
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
		
}
