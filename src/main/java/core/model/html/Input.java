package core.model.html;

public class Input extends UIElement {
	// private String type;
	// private String name;
	private static final String TAG = "input";

	public Input() {
		setTag(TAG);
	}

	@Override
	public String toHTML() {
		StringBuilder html = new StringBuilder();
		return html.toString();
	}

}
