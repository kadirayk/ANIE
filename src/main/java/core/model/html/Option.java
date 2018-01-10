package core.model.html;

import java.util.Map;

public class Option extends UIElement {

	// private String attrValue;
	// private String value;
	private static final String TAG = "option";

	/**
	 * empty constructor needed for YAML parser
	 */
	public Option() {
		setTag(TAG);
	}

	public Option(String content, Map<String, String> attributes) {
		setTag(TAG);
		setContent(content);
		setAttributes(attributes);
	}

	// public String getAttrValue() {
	// return attrValue;
	// }
	//
	// public void setAttrValue(String attrValue) {
	// this.attrValue = attrValue;
	// }
	//
	// public String getValue() {
	// return value;
	// }
	//
	// public void setValue(String value) {
	// this.value = value;
	// }

}
