package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaAlojamientos {
	
	@JsonProperty (value = "alojamientos")
	private List<Alojamiento> alojamientos;
	
	public ListaAlojamientos ( @JsonProperty(value="alojamientos")List<Alojamiento> alojamientos)
	{
		this.alojamientos = alojamientos;
	}

	public List<Alojamiento> getAlojamientos() {
		return alojamientos;
	}

	public void setAlojamientos(List<Alojamiento> alojamientos) {
		this.alojamientos = alojamientos;
	}
	
	
}
