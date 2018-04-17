package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaDuenoVivienda {

	@JsonProperty (value = "duenovivienda")
	private List<DuenoVivienda> duenovivienda;
	
	public ListaDuenoVivienda ( @JsonProperty(value="duenovivienda")List<DuenoVivienda> duenovivienda)
	{
		this.duenovivienda = duenovivienda;
	}

	public List<DuenoVivienda> getDuenosVivienda() {
		return duenovivienda;
	}

	public void setDuenosVivienda(List<DuenoVivienda> duenovivienda) {
		this.duenovivienda = duenovivienda;
	}
}
