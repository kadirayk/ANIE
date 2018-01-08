package core.model;

import java.util.Map;

public class State {
	private String name;
	private UIElement[] uiElements;
	private Map<String, String> transition;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UIElement[] getUiElements() {
		return uiElements;
	}

	public void setUiElements(UIElement[] uiElements) {
		this.uiElements = uiElements;
	}

	public Map<String, String> getTransition() {
		return transition;
	}

	public void setTransition(Map<String, String> transition) {
		this.transition = transition;
	}

}
