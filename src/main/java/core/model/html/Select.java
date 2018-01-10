package core.model.html;

import java.util.List;

public class Select extends UIElement {
	List<Option> options;
	// private String name;
	private static final String TAG = "select";

	public Select() {
		setTag(TAG);
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

}
