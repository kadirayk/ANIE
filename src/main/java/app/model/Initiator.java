package app.model;

import core.model.Form;
import core.model.FormItem;
import core.model.Interview;
import core.model.State;
import core.model.html.HTMLConstants;
import util.ConfUtil;
import util.ListUtil;

public class Initiator {

	private String content;

	private Interview interview;

	private String interviewHTML;

	private String debugHTML;

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
		this.id = interview.getId();
		this.interviewHTML = interview.getCurrentState().toHTML();

		StringBuilder htmlElement = new StringBuilder();

		if (ConfUtil.getValue(ConfUtil.DEBUG)) {
			htmlElement.append(HTMLConstants.LINE_BREAK).append("States: ");
			for (State state : interview.getStates()) {
				htmlElement.append("<p>State: ").append(state.getName()).append("</p>");
				Form form = state.getForm();
				if (form != null && ListUtil.isNotEmpty(form.getFormItems())) {
					htmlElement.append("<ul>");
					for (FormItem f : form.getFormItems()) {
						htmlElement.append("<li>").append(f.getQuestion()).append(" : ").append(f.getAnswer())
								.append("</li>");
					}
					htmlElement.append("</ul>");
				}
			}
			this.debugHTML = htmlElement.toString();
		}
	}

	public String getInterviewHTML() {
		return interviewHTML;
	}

	public void setInterviewHTML(String interviewHTML) {
		this.interviewHTML = interviewHTML;
	}

	public String getDebugHTML() {
		return debugHTML;
	}

	public void setDebugHTML(String debugHTML) {
		this.debugHTML = debugHTML;
	}

}
