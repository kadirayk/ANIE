package core.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class State {
	private String name;
	private Map<String, String> transition;
	private UIElement[] uiElements;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UIElement[] getUiElements() {
		return uiElements;
	}

	public void setUiElement(UIElement[] uiElements) {
		this.uiElements = uiElements;
	}

	@JsonAnyGetter
	public Map<String, String> getTransition() {
		return transition;
	}

	@JsonAnySetter
	public void setTransition(Map<String, String> transition) {
		this.transition = transition;
	}

}
