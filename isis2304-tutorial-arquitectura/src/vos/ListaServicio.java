package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaServicio {

	@JsonProperty (value = "servicios")
	private List<Servicio> servicios;
	
	public ListaServicio ( @JsonProperty(value="servicios")List<Servicio> servicios)
	{
		this.servicios = servicios;
	}

	public List<Servicio> getAlojamientos() {
		return servicios;
	}

	public void setAlojamientos(List<Servicio> servicios) {
		this.servicios = servicios;
	}
	
}
