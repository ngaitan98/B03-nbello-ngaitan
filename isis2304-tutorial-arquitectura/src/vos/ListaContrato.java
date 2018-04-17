package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaContrato {
	
	@JsonProperty (value = "contratos")
	private List<Contrato> contratos;
	
	public ListaContrato ( @JsonProperty(value="contratos")List<Contrato> contratos)
	{
		this.contratos = contratos;
	}

	public List<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;

	}
}
