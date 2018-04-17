package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaPersonaNormal {
	
	@JsonProperty (value = "personasnormales")
	private List<PersonaNormal> personasnormales;
	
	public ListaPersonaNormal ( @JsonProperty(value="personasnormales")List<PersonaNormal> personasnormales)
	{
		this.personasnormales = personasnormales;
	}

	public List<PersonaNormal> getPersonasNormales() {
		return personasnormales;
	}

	public void setPersonasNormales(List<PersonaNormal> personasnormales) {
		this.personasnormales = personasnormales;
	}
	

}
