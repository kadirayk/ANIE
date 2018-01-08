package core.model.html;

public class Input extends UIElement {
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Input(String name, String type) {
		super(name);
		this.type = type;
	}

	public Input() {
		// TODO Auto-generated constructor stub
	}

}
