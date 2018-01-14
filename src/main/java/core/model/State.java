package core.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import core.model.html.UIElement;

public class State {
	private String name;
	private Map<String, String> transition;
	private List<UIElement> uiElements;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UIElement> getUiElements() {
		return uiElements;
	}

	public void setUiElement(List<UIElement> uiElements) {
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

	public String toHTML() {
		StringBuilder htmlElement = new StringBuilder();

		List<UIElement> uiElements = getUiElements();
		if (uiElements != null) {
			for (UIElement e : uiElements) {
				htmlElement.append(e.toHTML()).append("<br/>").append("\n");
			}
		}
		return htmlElement.toString();
	}

}
