package core.model;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import core.model.html.HTMLConstants;
import core.model.html.UIElement;
import util.ListUtil;

public class State implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -849218511658141465L;
	private String formId;
	private String name;
	private Map<String, String> transition;
	private Form form;

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonAnyGetter
	public Map<String, String> getTransition() {
		return transition;
	}

	@JsonAnySetter
	public void setTransition(Map<String, String> transition) {
		this.transition = transition;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public String toHTML() {
		StringBuilder htmlElement = new StringBuilder();

		if (form != null && ListUtil.isNotEmpty(form.getFormItems())) {
			for (FormItem f : form.getFormItems()) {
				String formQuestion = f.getQuestion();
				if (formQuestion != null) {
					htmlElement.append(HTMLConstants.LINE_BREAK).append(formQuestion).append(HTMLConstants.LINE_BREAK);
				}
				UIElement formUiElement = f.getUiElement();
				if (formUiElement != null) {
					htmlElement.append(formUiElement.toHTML()).append(HTMLConstants.LINE_BREAK).append("\n");
				}
			}
		}

		return htmlElement.toString();
	}

}
