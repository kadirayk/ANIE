package core.model.html;

public class Option extends UIElement {

	private String attrValue;
	private String value;

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	String toHTML() {
		StringBuilder html = new StringBuilder();
		return html.toString();
	}

}
