package core.model;

import java.io.Serializable;

import core.model.html.UIElement;

public class FormItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6238821216071893403L;
	private String question;
	private UIElement uiElement;
	private String answer;

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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
