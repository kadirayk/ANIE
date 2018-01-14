package app.model;

import core.model.Interview;

public class Initiator {

	private String content;

	private Interview interview;

	private String interviewHTML;

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
		this.interviewHTML = interview.toHTML();
	}

	public String getInterviewHTML() {
		return interviewHTML;
	}

	public void setInterviewHTML(String interviewHTML) {
		this.interviewHTML = interviewHTML;
	}

}
