package core.model.html;

public class Input extends UIElement {
	private String type;
	private static final String START_TAG = "<Input>";
	private static final String END_TAG = "</Input>";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Input(String name, String type) {
		super(name, START_TAG, END_TAG);
		this.type = type;
	}

	public Input() {
		// TODO Auto-generated constructor stub
	}

	@Override
	String toHTML() {
		StringBuilder html = new StringBuilder();
		return html.toString();
	}

}
