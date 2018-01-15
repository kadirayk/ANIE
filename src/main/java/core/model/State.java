package core.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import core.model.html.HTMLConstants;
import core.model.html.UIElement;

public class State {
	private String name;
	private String question;
	private Map<String, String> transition;
	private List<UIElement> uiElements;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
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

		if (getQuestion() != null) {
			htmlElement.append(HTMLConstants.LINE_BREAK).append(getQuestion()).append(HTMLConstants.LINE_BREAK);
		}

		if (uiElements != null) {
			for (UIElement e : uiElements) {
				htmlElement.append(e.toHTML()).append(HTMLConstants.LINE_BREAK).append("\n");
			}
		}
		return htmlElement.toString();
	}

}
