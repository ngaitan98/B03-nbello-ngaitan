package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaHostal {
	
	@JsonProperty (value = "hostales")
	private List<Hostal> hostales;
	
	public ListaHostal ( @JsonProperty(value="hostales")List<Hostal> hostales)
	{
		this.hostales = hostales;
	}

	public List<Hostal> getHostales() {
		return hostales;
	}

	public void setHostales(List<Hostal> hostales) {
		this.hostales = hostales;
	}

}
