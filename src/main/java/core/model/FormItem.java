package core.model;

import core.model.html.UIElement;

public class FormItem {
	private String question;
	private UIElement uiElement;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public UIElement getUiElement() {
		return uiElement;
	}

	public void setUiElement(UIElement uiElement) {
		this.uiElement = uiElement;
	}

}
