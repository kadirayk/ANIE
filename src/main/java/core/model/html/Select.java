package core.model.html;

import java.util.List;

public class Select extends UIElement {
	List<Option> options;
	private static final String START_TAG = "<select>";
	private static final String END_TAG = "</select>";

	public Select() {
		// TODO Auto-generated constructor stub
	}

	public Select(String name, List<Option> options) {
		super(name, START_TAG, END_TAG);
		this.options = options;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	@Override
	String toHTML() {
		StringBuilder html = new StringBuilder();
		return html.toString();
	}

}
