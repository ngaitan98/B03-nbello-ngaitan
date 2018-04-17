package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaCliente {

	@JsonProperty (value = "clientes")
	private List<Cliente> clientes;
	
	public ListaCliente ( @JsonProperty(value="clientes")List<Cliente> clientes)
	{
		this.clientes = clientes;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	
}
