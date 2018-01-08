package core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({ @JsonSubTypes.Type(value = Input.class, name = "Input")
		// ,

		// @JsonSubTypes.Type(value = Cat.class, name = "Cat")
})
public abstract class UIElement {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UIElement(String name) {
		this.name = name;
	}

	public UIElement() {
		// TODO Auto-generated constructor stub
	}

}
