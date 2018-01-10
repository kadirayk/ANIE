package core.model.html;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({ @JsonSubTypes.Type(value = Input.class, name = "Input"),
		@JsonSubTypes.Type(value = Select.class, name = "Select") })
public abstract class UIElement {
	private String name;
	private final String START_TAG;
	private final String END_TAG;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UIElement(String name, String startTag, String endTag) {
		this.name = name;
		START_TAG = startTag;
		END_TAG = endTag;
	}

	public UIElement() {
		START_TAG = null;
		END_TAG = null;
	}

	abstract String toHTML();

	public String startTag() {
		return START_TAG;
	}

	public String endTag() {
		return END_TAG;
	}

}
