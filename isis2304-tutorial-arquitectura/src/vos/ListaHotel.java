package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaHotel {
	
	@JsonProperty (value = "hoteles")
	private List<Hotel> hoteles;
	
	public ListaHotel ( @JsonProperty(value="hoteles")List<Hotel> hoteles)
	{
		this.hoteles = hoteles;
	}

	public List<Hotel> getAlojamientos() {
		return hoteles;
	}

	public void setAlojamientos(List<Hotel> hoteles) {
		this.hoteles = hoteles;
	}
	
}
