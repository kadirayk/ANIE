package core.model.html;

import java.util.List;

public class Select extends UIElement {
	List<Option> options;

	public Select() {
		// TODO Auto-generated constructor stub
	}

	public Select(String name, List<Option> options) {
		super(name);
		this.options = options;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

}
